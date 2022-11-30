package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 5,      // 5 MB
        maxRequestSize = 1024 * 1024 * 10   // 10 MB
)
@WebServlet("/upload")
public class UploadFileServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UploadFileServlet.class);
    private static final CarService carService = new CarService();

    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        log.info(filesDir.getAbsolutePath() + "/" + filesDir.getPath());
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String queryString = request.getQueryString();
        log.info(queryString);

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

//        String file_name = "";
//        String file_name2 = "";
//        String path = request.getQueryString();
        long carId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;

        String[] reqFields = {"filename", "newPrice", "newName", "newBrand", "newCost", "newModel"};

        String[] reqs = new String[7];

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

        if (!isMultipartContent) {
            return;
        }

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fields = upload.parseRequest(request);
            Iterator<FileItem> items = fields.iterator();
            if (!items.hasNext()) {
                return;
            }

            getNext(reqFields, reqs, items);

            log.info(Arrays.toString(reqs));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Car car = new Car.Builder()
                    .id(carId)
                    .path(reqs[0])
                    .name(reqs[2])
                    .brand(reqs[3])
                    .model(reqs[5])
//                    .price(Double.valueOf(reqFields[1]))
//                    .cost(Double.valueOf(reqFields[4]))
                    .created(timestamp.toLocalDateTime())
                    .price(100.0)
                    .cost(10000.0)
                    .year(2022)
                    .rent(false)
                    .build();
            boolean save = carService.save(car);
            log.info(String.format("Saved: %s, %s", save, car));
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        } finally {
//            response.sendRedirect(MAIN);
            writer.println("<script type='text/javascript'>");
            writer.printf("window.location.href='/'%n");
//            writer.println("window.location.href='/upload?" + file_name2 + "'");
            writer.println("</script>");
            writer.close();
        }

    }

    private static void getNext(String[] reqFields, String[] reqs, Iterator<FileItem> items) throws Exception {

        String filename;
        while (items.hasNext()) {
            FileItem fileItem = items.next();

            if (fileItem.isFormField()) {
                IntStream.range(1, reqFields.length)
                        .filter(i -> fileItem.getFieldName().equals(reqFields[i]))
                        .forEachOrdered(i -> reqs[i] = (fileItem.getString()));

            } else {
                if (fileItem.getSize() > 0) {
                    //    fileItem.write(new File("E:\\uploaded_files\\" + fileItem.getName()));
                    filename = fileItem.getName();
                    reqs[0] = (String.format("/upload?%s", filename));
                    File file = new File("src/main/webapp/WEB-INF/classes/templates/img/" + filename);
                    if (!file.exists()) {
                        fileItem.write(file);
                    } else {
                        log.info("File is exists, Rename the file");
                        throw new FileAlreadyExistsException(file.getAbsolutePath());
                    }
                }
            }
        }
    }
}