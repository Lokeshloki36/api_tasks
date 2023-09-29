package com.mayuratech.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mayuratech.api.entity.UserDetails;


@Service
public class PDFGenerator {
	
	private static Logger logger = LogManager.getLogger(PDFGenerator.class);
	
	public ByteArrayInputStream generatePdf(List<UserDetails> users) {
		
		Document document = new Document();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			logger.info("Pdf generation started!");
		PdfWriter.getInstance(document, output);
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.BLUE);
		Paragraph para = new Paragraph("Customers Accounts",font);
		para.setAlignment(Element.ALIGN_CENTER);
		document.add(para);
		document.add(Chunk.NEWLINE);
		
		PdfPTable table = new PdfPTable(4);
		Stream.of("Id","Name","Account Number","Balance").
		forEach(headerTitle->{
			PdfPCell headerCell=new PdfPCell();
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			headerCell.setBackgroundColor(BaseColor.CYAN);
			headerCell.setBorderWidth(2);
			headerCell.setPhrase(new Phrase(headerTitle,headerFont));
			headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(headerCell);
		});
		
		for(UserDetails user : users) {
			PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(user.getId())));
			idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			idCell.setVerticalAlignment(Element.ALIGN_CENTER);
			idCell.setPaddingLeft(4);
			table.addCell(idCell);
			
			PdfPCell nameCell = new PdfPCell(new Phrase(user.getName()));
			nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			nameCell.setVerticalAlignment(Element.ALIGN_CENTER);
			nameCell.setPaddingLeft(4);
			table.addCell(nameCell);
			
			PdfPCell accountNumberCell = new PdfPCell(new Phrase(user.getAccountNumber()));
			accountNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			accountNumberCell.setVerticalAlignment(Element.ALIGN_CENTER);
			accountNumberCell.setPaddingLeft(4);
			table.addCell(accountNumberCell);
			
			PdfPCell balanceCell = new PdfPCell(new Phrase(String.valueOf(user.getBalance())));
			balanceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			balanceCell.setVerticalAlignment(Element.ALIGN_CENTER);
			balanceCell.setPaddingLeft(4);
			table.addCell(balanceCell);
		}
		document.add(table);
		logger.info("Pdf generation successful!");
		document.close();
		} catch (DocumentException e) {
			logger.error("something went wrong while creating pdf : "+e.toString());
		}
		return new ByteArrayInputStream(output.toByteArray());
	}
}
