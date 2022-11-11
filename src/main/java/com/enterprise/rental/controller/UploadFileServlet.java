package com.enterprise.rental.controller;

import com.enterprise.rental.exception.DataException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.MAIN;

@WebServlet("/upload")
public class UploadFileServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(UploadFileServlet.class);
    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
    private ServletFileUpload uploader = null;

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        if (fileName == null || fileName.equals("")) {
            throw new DataException("File Name can't be null or empty");
        }
        File file = new File(String.format("%s%s%s", request.getAttribute("FILES_DIR"), File.separator, fileName));
        if (!file.exists()) {
            throw new DataException("File doesn't exists on server.");
        }
        log.info("File location on server::" + file.getAbsolutePath());
        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read = 0;
        while ((read = fis.read(bufferData)) != -1) {
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        log.info("File downloaded at client successfully");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new DataException("Content type is not multipart/form-data");
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head></head><body>");
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
                log.info("FieldName=" + fileItem.getFieldName());
                log.info("FileName=" + fileItem.getName());
                log.info("ContentType=" + fileItem.getContentType());
                log.info("Size in bytes=" + fileItem.getSize());

                File file = new File(request.getAttribute("FILES_DIR") + File.separator + fileItem.getName());
                log.info("Absolute Path at server=" + file.getAbsolutePath());
                fileItem.write(file);
                out.write("File " + fileItem.getName() + " uploaded successfully.");
                out.write("<br>");
                out.write("<a href=\"upload?fileName=" + fileItem.getName() + "\">Download " + fileItem.getName() + "</a>");
            }
        } catch (FileUploadException e) {
            out.write("Exception in uploading file.");
        } catch (Exception e) {
            out.write("Exception in uploading file.");
        }
        out.write("</body></html>");
        response.sendRedirect(MAIN);
    }
}
