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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.CARS;
import static com.enterprise.rental.dao.jdbc.Constants.MAIN;

@WebServlet("/upload")
public class UploadFileServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UploadFileServlet.class);
    private static final CarService carService = new DefaultCarService();
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";

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
        log.debug(filesDir);
        log.debug(filesDir.getAbsolutePath() + "/" + filesDir.getPath());
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String queryString = request.getQueryString();
        log.debug(queryString);

        ServletContext servletContext = getServletContext();

        try (InputStream inputStream = servletContext
                .getResourceAsStream(String.format("/WEB-INF/classes/templates/img/%s", queryString))) {
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

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        /**
         * Upon receiving file upload submission, parses the request to read
         * upload data and saves the file on disk.
         */
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }
        String path = "src/main/webapp/WEB-INF/classes/templates/img/";
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
            String[] reqFields = {"filename", "newPrice", "newName", "newBrand", "newCost", "newModel"};
            String[] reqs = new String[7];


            for (FileItem fileItem : formItems) {
                for (int i = 1; i < reqFields.length; i++) {
                    String field = reqFields[i];
                    if (fileItem.getFieldName().equals(field)) {
                        reqs[i] = (fileItem.getString());
                        log.info(reqs[i]);
                    }
                }
            }

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        String file_name2 = item.getName();
                        reqs[0] = (String.format("/upload?%s", file_name2));
                        log.info(reqs[0]);
                        item.write(new File(path + file_name2));
                        // saves the file on disk
                        item.write(storeFile);
                        request.setAttribute("message",
                                "Upload has been done successfully!");
                    }
                }
            }
            Car car = new Car.Builder()
                    .id(carId)
                    .path(reqs[0])
                    .price(100.0)
                    .cost(10000.0)
                    .year(2022)
                    .year(2022)
                    .rent(false)
                    .name(reqs[2])
                    .brand(reqs[3])
//                    .price(Double.valueOf(reqFields[1]))
//                    .cost(Double.valueOf(reqFields[4]))
                    .model(reqs[5])
                    .build();
            boolean save = carService.save(car);
            log.info(String.format("Saved: %s, %s", save, car));
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }
        // redirects client to message page
        getServletContext().getRequestDispatcher(CARS).forward(
                request, response);
    }
}

//
//        String file_name = "";
//        String file_name2 = "";
////        String path = request.getQueryString();
//        long carId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
//
//        String[] reqFields = {"filename", "newPrice", "newName", "newBrand", "newCost", "newModel"};
//
//        String[] reqs = new String[7];
//
//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
//        if (!isMultipartContent) {
//            return;
//        }
//        FileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        try {
//            List<FileItem> fields = upload.parseRequest(request);
//            Iterator<FileItem> items = fields.iterator();
//            if (!items.hasNext()) {
//                return;
//            }
//            while (items.hasNext()) {
//                FileItem fileItem = items.next();
//
//                boolean isFormField = fileItem.isFormField();
//                if (isFormField) {
//
//                    for (int i = 1; i < reqFields.length; i++) {
//                        String field = reqFields[i];
//                        if (fileItem.getFieldName().equals(field)) {
//                            log.info(reqs[i]);
//                            reqs[i] = (fileItem.getString());
//                        }
//                    }
//
//                } else {
//                    if (fileItem.getSize() > 0) {
////                            fileItem.write(new File("E:\\uploaded\\" + fileItem.getName()));
//
//                        file_name2 = fileItem.getName();
//                        reqs[0] = (String.format("/upload?%s", file_name2));
//                        fileItem.write(new File("src/main/webapp/WEB-INF/classes/templates/" + file_name2));
//                    }
//                }
//            }
//            log.info(Arrays.toString(reqs));
////            price, cost, year, date, rent
//            Car car = new Car.Builder()
//                    .id(carId)
//                    .path(reqs[0])
//                    .price(100.0)
//                    .cost(10000.0)
//                    .year(2022)
//                    .year(2022)
//                    .rent(false)
//                    .name(reqs[2])
//                    .brand(reqs[3])
////                    .price(Double.valueOf(reqFields[1]))
////                    .cost(Double.valueOf(reqFields[4]))
//                    .model(reqs[5])
//                    .build();
//            boolean save = carService.save(car);
//            log.info(String.format("Saved: %s, %s", save, car));
//        } catch (Exception e) {
//            throw new DataException(e.getMessage());
//        } finally {
//            writer.println("<script type='text/javascript'>");
//            writer.println("window.location.href='/upload?" + file_name2 + "'");
//            writer.println("</script>");
//            writer.close();
//        }
//        response.sendRedirect(MAIN);
//    }
//}
