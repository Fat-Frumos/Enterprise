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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.MAIN;

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

        ServletContext sc = getServletContext();

        try (InputStream is = sc.getResourceAsStream(String.format("/WEB-INF/classes/templates/%s", queryString))) {
            OutputStream os = response.getOutputStream();
            if (is != null) {
                response.setContentType("image/jpeg");
                byte[] buffer = new byte[1024];

                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {

                    os.write(buffer, 0, bytesRead);
                }
            } else {
                response.setContentType("text/plain");
                os.write("Failed to send image".getBytes());
            }
        }
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String file_name = "";
        String file_name2 = "";
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
            while (items.hasNext()) {
                FileItem fileItem = items.next();

                boolean isFormField = fileItem.isFormField();
                if (isFormField) {

                    for (int i = 1; i < reqFields.length; i++) {
                        String field = reqFields[i];
                        if (fileItem.getFieldName().equals(field)) {
                            reqs[i] = (fileItem.getString());
                        }
                    }

                } else {
                    if (fileItem.getSize() > 0) {
                        //    fileItem.write(new File("E:\\uploaded_files\\" + fileItem.getName()));

                        file_name2 = fileItem.getName();
                        reqs[0] = (String.format("/upload?%s", file_name2));
                        fileItem.write(new File("src/main/webapp/WEB-INF/classes/templates/" + file_name2));
                    }
                }
            }
            log.info(Arrays.toString(reqs));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            price, cost, year, date, rent
            Car car = new Car.Builder()
                    .id(carId)
                    .path(reqs[0])
                    .price(100.0)
                    .cost(10000.0)
                    .year(2022)
                    .year(2022)
                    .rent(false)
                    .created(timestamp.toLocalDateTime())
                    .name(reqs[2])
                    .brand(reqs[3])
//                    .price(Double.valueOf(reqFields[1]))
//                    .cost(Double.valueOf(reqFields[4]))
                    .model(reqs[5])
                    .build();
            boolean save = carService.save(car);
            log.info(String.format("Saved: %s, %s", save, car));
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        } finally {
            writer.println("<script type='text/javascript'>");
            writer.println("window.location.href='/upload?" + file_name2 + "'");
            writer.println("</script>");
            writer.close();
        }
        response.sendRedirect(MAIN);
    }
}


//
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.write("<html><head></head><body>");
//        try {
//            List<FileItem> fileItemsList = uploader.parseRequest(request);
//            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
//            while (fileItemsIterator.hasNext()) {
//                FileItem fileItem = fileItemsIterator.next();
//                log.info("FieldName=" + fileItem.getFieldName());
//                log.info("FileName=" + fileItem.getName());
//                log.info("ContentType=" + fileItem.getContentType());
//                log.info("Size in bytes=" + fileItem.getSize());
//
//                File file = new File(request.getAttribute("FILES_DIR") + File.separator + fileItem.getName());
//                log.info("Absolute Path at server=" + file.getAbsolutePath());
//                fileItem.write(file);
//                out.write("File " + fileItem.getName() + " uploaded successfully.");
//                out.write("<br>");
//                out.write("<a href=\"upload?fileName=" + fileItem.getName() + "\">Download " + fileItem.getName() + "</a>");
//            }
//        } catch (FileUploadException e) {
//            out.write("Exception in uploading file.");
//        } catch (Exception e) {
//            out.write("Exception in uploading file.");
//        }
//        out.write("</body></html>");
//        response.sendRedirect(MAIN);


//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String fileName = request.getParameter("fileName");
//        if (fileName == null || fileName.equals("")) {
//            throw new DataException("File Name can't be null or empty");
//        }
//        File file = new File(String.format("%s%s%s", request.getAttribute("FILES_DIR"), File.separator, fileName));
//        if (!file.exists()) {
//            throw new DataException("File doesn't exists on server.");
//        }
//        log.info("File location on server::" + file.getAbsolutePath());
//        ServletContext ctx = getServletContext();
//        InputStream fis = new FileInputStream(file);
//        String mimeType = ctx.getMimeType(file.getAbsolutePath());
//        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
//        response.setContentLength((int) file.length());
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//
//        ServletOutputStream os = response.getOutputStream();
//        byte[] bufferData = new byte[1024];
//        int read = 0;
//        while ((read = fis.read(bufferData)) != -1) {
//            os.write(bufferData, 0, read);
//        }
//        os.flush();
//        os.close();
//        fis.close();
//        log.info("File downloaded at client successfully");
//    }
//

