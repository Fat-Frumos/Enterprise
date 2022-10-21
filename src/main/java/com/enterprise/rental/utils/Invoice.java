//package com.enterprise.rental.utils;
//
//import com.enterprise.rental.exception.DataException;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;
//import org.apache.log4j.Logger;
//
//import java.io.FileNotFoundException;
//
//public class Invoice {
//    private static final Logger log = Logger.getLogger(Invoice.class);
//    public static final String DEST = "/letter.pdf";
//
//    public void createPdf() {
//        try {
//            PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
//            Document document = new Document(pdf);
//            String line = "Welcome to iTextPdf";
//            document.add(new Paragraph(line));
//            document.close();
//            log.info("Awesome PDF just got created");
//        } catch (FileNotFoundException e) {
//            throw new DataException(DEST, e);
//        }
//    }
//}