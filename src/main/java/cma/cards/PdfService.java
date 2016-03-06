package cma.cards;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.util.Matrix;
import org.springframework.stereotype.Component;

@Component
public class PdfService {

	private float MAGIC_CORRECTION = 0.60f;;

	void createPdf(String text, String targetPath, int fontSize, boolean print) throws IOException, PrinterException {
		PDDocument document = new PDDocument();

		PDRectangle mediaBox = new PDRectangle(PDRectangle.A3.getWidth() / 2, PDRectangle.A3.getHeight());
		PDPage page = new PDPage(mediaBox);
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);


		PDFont font = PDType1Font.HELVETICA_BOLD;
		float textWidth = font.getStringWidth(text) / 1000 * fontSize;
		PDRectangle fontBoundingBox = font.getFontDescriptor().getFontBoundingBox();
		float textHeight = fontBoundingBox.getHeight() / 1000 * fontSize * MAGIC_CORRECTION;

		float pageWidth = page.getMediaBox().getWidth();
		float tx = pageWidth - (pageWidth - textHeight) / 2;
		float ty = (page.getMediaBox().getHeight() - textWidth) / 2;
		Matrix matrix = Matrix.getRotateInstance(Math.PI / 2, tx, ty);

		contentStream.beginText();
		contentStream.setTextMatrix(matrix);
		contentStream.setFont(font, fontSize);
		contentStream.setNonStrokingColor(Color.RED);

		contentStream.showText(text);
		contentStream.endText();

		contentStream.close();

		String filename = text;
		String fileName2 = targetPath + filename + ".pdf";
		document.save(fileName2);
		document.close();

		if (print) {
			PDDocument pdf = PDDocument.load(new File(fileName2));
			PrinterJob job = PrinterJob.getPrinterJob();
			PDFPageable document2 = new PDFPageable(pdf);
			job.setPageable(document2);
			job.print();
		}
	}
	
}