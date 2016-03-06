package cma.cards;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import org.springframework.stereotype.Component;

@Component
public class PdfService{

	void createPdf(String text, String targetPath) throws IOException {
		PDDocument document = new PDDocument();
	
		PDRectangle mediaBox = new PDRectangle(PDRectangle.A3.getWidth() / 2, PDRectangle.A3.getHeight());
		PDPage page = new PDPage(mediaBox);
		document.addPage(page);
	
		PDFont font = PDType1Font.HELVETICA_BOLD;
	
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
	
		int fontSize = 240;
		float textWidth = font.getStringWidth(text) / 1000 * fontSize;
		PDRectangle fontBoundingBox = font.getFontDescriptor().getFontBoundingBox();
		float textHeight = fontBoundingBox.getHeight() / 1000 * fontSize * 0.60f;
		
	
		float pageWidth = page.getMediaBox().getWidth();
		float tx = pageWidth - (pageWidth - textHeight) / 2;
		float ty = (page.getMediaBox().getHeight() - textWidth) /2;
		Matrix matrix = Matrix.getRotateInstance(Math.PI / 2, tx, ty );
		
		contentStream.beginText();
		contentStream.setTextMatrix(matrix);
		contentStream.setFont(font, fontSize);
		contentStream.setNonStrokingColor(Color.RED);
	
		contentStream.showText(text);
		contentStream.endText();
	
		contentStream.close();
	
		String filename = text;
		document.save(targetPath + filename + ".pdf");
	
		document.close();
	}
	
}