package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.impl.DefaultCarService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.enterprise.rental.dao.jdbc.Constants.CARS;

/**
 * Java class that represent an Upload File service for update images.
 * <p>
 * A subclass of <code>UploadFileServlet</code> override two methods:
 * <code>doGet</code>, for HTTP GET requests (read image)
 * <code>doPost</code>, for HTTP POST requests (save image)
 *
 * @author Pasha Pollack
 */
public class UploadFileServlet extends Servlet {
    private static final Logger log = Logger.getLogger(UploadFileServlet.class);
    private static final CarService carService = new DefaultCarService();
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "/WEB-INF/classes/templates/img/";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 1;  // 1MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10; // 10MB

    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;

    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    /**
     * Get image from server to the page
     * Is used to process GET request.
     *
     * @param request  Http Servlet Request
     * @param response Http Servlet Response
     * @throws IOException If the target resource throws this exception
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String queryString = request.getQueryString();
        ServletContext servletContext = getServletContext();

        try (InputStream inputStream = servletContext
                .getResourceAsStream(String.format("%s%s", UPLOAD_DIRECTORY, queryString))) {
            OutputStream outputStream = response.getOutputStream();
            if (inputStream != null) {
                response.setContentType("image/jpeg");
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } else {
                response.setContentType("text/plain");
                outputStream.write("Failed to send image".getBytes());
            }
        }
    }

    /**
     * Upon receiving file upload submission, parses the request,
     * read upload data and saves the file on disk.
     * <p>
     * Is used to process POST request.
     *
     * @param request  Http Servlet Request
     * @param response Http Servlet Response
     * @throws ServletException If the target resource throws ServletException
     * @throws IOException      If the target resource throws IOException
     * @see HttpServlet#doPost(HttpServletRequest request,
     * HttpServletResponse response)
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        String path = String.format("src/main/webapp%s", UPLOAD_DIRECTORY);
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);

            long carId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
            String[] requestFields = {"filename", "newPrice", "newName", "newBrand", "newCost", "newModel"};
            String[] requests = new String[7];


            formItems.forEach(fileItem -> IntStream.range(1, requestFields.length)
                    .filter(i -> fileItem.getFieldName().equals(requestFields[i]))
                    .forEach(i -> requests[i] = (fileItem.getString())));

            if (formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String filePath = String.format("%s%s%s",
                                uploadPath,
                                File.separator,
                                new File(item.getName())
                                        .getName());

                        File storeFile = new File(filePath);

                        String file_name2 = item.getName();
                        requests[0] = (String.format("/upload?%s", file_name2));
                        item.write(new File(path + file_name2));

                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message", "Upload has been done successfully!");
                    }
                }
            }
            log.info(requests);

            Car car = new Car.Builder()
                    .id(carId)
                    .path(requests[0])
                    .year(2022)
                    .date(new Timestamp(System.currentTimeMillis()))
                    .rent(false)
                    .name(requests[2])
                    .brand(requests[3])
                    .price(Double.valueOf(requests[1]))
                    .cost(Double.valueOf(requests[4]))
                    .model(requests[5])
                    .build();

            boolean save = carService.save(car);
            log.info(String.format("Saved: %s, %s", save, car));
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        dispatch(request, response, CARS);
    }
}
