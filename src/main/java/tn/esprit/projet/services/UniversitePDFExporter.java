package tn.esprit.projet.services;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.esprit.projet.entities.Universite;
 
@Service
public class UniversitePDFExporter {
    private List<Universite> listFactures;
     
    public UniversitePDFExporter(List<Universite> listFactures) {
        this.listFactures = listFactures;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Universite ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Nom Universite", font));
        table.addCell(cell);
         
     
         
    }
     
    private void writeTableData(PdfPTable table) {
        for (Universite facture : listFactures) {
            table.addCell(String.valueOf(facture.getId()));
            table.addCell(String.valueOf(facture.getNomUniv()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Factures", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}