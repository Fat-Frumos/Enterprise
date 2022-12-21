package com.enterprise.rental.service.email;

import com.enterprise.rental.exception.DataException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * The InvoicePdf class describes the functions of a created attachment in format PDF.
 *
 * @author Pasha Pollack
 */
public class InvoicePdf {
    private static final Logger log = Logger.getLogger(InvoicePdf.class);
    public static final String DEST = "/letter.pdf";

    /**
     * The created document in format PDF.
     *
     * @author Pasha Pollack
     */
    public static void createPdf() {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Pdf Writer", font);
            document.add(chunk);
            document.close();
            log.debug("Created PDF document");

        } catch (DocumentException | FileNotFoundException e) {
            throw new DataException(e.getMessage());
        }
    }
}