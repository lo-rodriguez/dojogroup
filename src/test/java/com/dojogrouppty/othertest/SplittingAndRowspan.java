/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.othertest;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Bruno Lowagie (iText Software)
 */
public class SplittingAndRowspan {
    public static final String DEST = "D:/workspace/appBZK/receipts/splitting_and_rowspan.pdf";
 
    public static void main(String[] args) throws IOException,
            DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SplittingAndRowspan().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document(new Rectangle(300, 150));
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(new Paragraph("Table with setSplitLate(true):"));
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(10);
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph("G"));
        cell.addElement(new Paragraph("R"));
        cell.addElement(new Paragraph("P"));
        cell.setRowspan(3);
        table.addCell(cell);
        table.addCell("row 1");
        table.addCell("row 2");
        table.addCell("row 3");
        document.add(table);
        document.add(new Paragraph("Table with setSplitLate(false):"));
        table.setSplitLate(false);
        document.add(table);
        document.close();
    }
}
