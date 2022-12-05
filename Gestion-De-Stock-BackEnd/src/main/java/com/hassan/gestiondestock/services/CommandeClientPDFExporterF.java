package com.hassan.gestiondestock.services;

import com.hassan.gestiondestock.model.CommandeFournisseurs;
import com.hassan.gestiondestock.model.Fournisseur;
import com.hassan.gestiondestock.model.LigneCommandeFournisseurs;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CommandeClientPDFExporterF {

    private CommandeFournisseurs commandeFournisseur;
    private Optional<Fournisseur> fourisseur;
    private List<LigneCommandeFournisseurs> ligneCommandeFournisseur;


    public CommandeClientPDFExporterF(CommandeFournisseurs commandeFournisseur, Optional<Fournisseur> fourisseur, List<LigneCommandeFournisseurs> ligneCommandeFournisseur) {
        this.commandeFournisseur = commandeFournisseur;
        this.fourisseur = fourisseur;
        this.ligneCommandeFournisseur = ligneCommandeFournisseur;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Article", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantité", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Prix", font));
        table.addCell(cell);
        table.completeRow();

    }

    private void writeTableData(PdfPTable table) {
        for (LigneCommandeFournisseurs ligne : ligneCommandeFournisseur) {
            table.addCell(ligne.getArticleIdCC());
            table.addCell(String.valueOf(ligne.getQuantite()));
            table.addCell(String.valueOf(ligne.getPrixTotale()));
            /*table.addCell(user.getRoles().toString());
            table.addCell(String.valueOf(user.isEnabled()));*/
            table.completeRow();
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(10);
        font.setColor(Color.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(18);
        font2.setColor(Color.BLACK);
        Paragraph date = new Paragraph(" Date : "+commandeFournisseur.getDateCommande(), font);
        date.setAlignment(Paragraph.ALIGN_RIGHT);
        Paragraph nom = new Paragraph(" "+fourisseur.get().getNom(), font);
        date.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph prenom = new Paragraph(" "+fourisseur.get().getPrenom(), font);
        date.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(date);
        document.add(nom);
        document.add(prenom);
        Paragraph gmail = new Paragraph(" "+fourisseur.get().getMail(), font);
        date.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(gmail);
        Paragraph adresse = new Paragraph(" "+fourisseur.get().getAdress1(), font);
        date.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(adresse);
        Paragraph ville = new Paragraph(" "+fourisseur.get().getVille()+"-"+fourisseur.get().getPays(), font);
        date.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(ville);
        Paragraph codePostale = new Paragraph(" "+fourisseur.get().getCodePostale(), font);
        date.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(codePostale);
        Paragraph p = new Paragraph("Facture N°"+commandeFournisseur.getCode(), font2);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        Font font6 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font6.setSize(6);
        font6.setColor(Color.WHITE);
        Paragraph rien3 = new Paragraph("-------", font6);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(rien3);
        document.add(rien3);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f, 3.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        Font font3 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(15);
        document.add(rien3);
        document.add(rien3);
        document.add(rien3);

        Paragraph totaleHT = new Paragraph(" Prix HT      : "+commandeFournisseur.getPrixTotale(), font3);
        date.setAlignment(totaleHT.ALIGN_CENTER);
        document.add(totaleHT);
        Long tva=((commandeFournisseur.getPrixTotale()*20)/100);
        String tvaS= String.valueOf(tva);
        Paragraph tvaP = new Paragraph(" TVA  20 %   : "+tvaS, font3);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(tvaP);
        Long prixT=commandeFournisseur.getPrixTotale()-tva;
        Paragraph prixTotale = new Paragraph(" Prix Totale : "+prixT+" $", font3);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(prixTotale);
        Font font5 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font5.setSize(6);
        font5.setColor(Color.BLACK);
        Font font7 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font7.setSize(6);
        font7.setColor(Color.WHITE);
        Paragraph rien = new Paragraph("-------", font6);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph rien2 = new Paragraph("-------", font6);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(rien);
        document.add(rien2);
        document.bottomMargin();
        Paragraph TEXT = new Paragraph("La loi n°92/1442 du 31 décembre 1992 nous fait l’obligation de vous indiquer que le non-respect des conditions de paiement entraine des intérêts de retard suivant modalités et taux défini par la loi. Une indemnité forfaitaire de 40€ sera due pour frais de recouvrement en cas de retard de paiement.", font5);
        date.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        document.add(TEXT);

        document.close();

    }
}