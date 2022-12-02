package com.enterprise.rental.utils;

import com.enterprise.rental.exception.DataException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class InvoiceEmail {
    private static final Logger log = Logger.getLogger(InvoiceEmail.class);
    public static final String DEST = "/letter.pdf";

    public static void createPdf() {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Pdf Writer", font);
            document.add(chunk);
            document.close();
            log.info("Created PDF document");

        } catch (DocumentException | FileNotFoundException e) {
            throw new DataException(e.getMessage());
        }
    }
}