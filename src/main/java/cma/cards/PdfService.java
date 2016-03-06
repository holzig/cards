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

	void createPdf(String text) throws IOException {
		PDDocument document = new PDDocument();
	
		PDRectangle a3 = PDRectangle.A3;
	
		PDRectangle mediaBox = new PDRectangle(a3.getWidth() / 2, a3.getHeight());
		// Create a new blank page and add it to the document
		PDPage page = new PDPage(mediaBox);
		document.addPage(page);
	
		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;
	
		// Start a new content stream which will "hold" the to be created
		// content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
	
		int fontSize = 240; // Or whatever font size you want.
		float textWidth = font.getStringWidth(text) / 1000 * fontSize;
		PDRectangle fontBoundingBox = font.getFontDescriptor().getFontBoundingBox();
		float textHeight = fontBoundingBox.getHeight() / 1000 * fontSize * 0.60f;
		
	
		float pageWidth = page.getMediaBox().getWidth();
		float tx = pageWidth - (pageWidth - textHeight) / 2;
		float ty = (page.getMediaBox().getHeight() - textWidth) /2;
		Matrix matrix = Matrix.getRotateInstance(Math.PI / 2, tx, ty );
		
		// Define a text content stream using the selected font, moving the
		// cursor and drawing the text "Hello World"
		contentStream.beginText();
		contentStream.setTextMatrix(matrix);
		contentStream.setFont(font, fontSize);
		contentStream.setNonStrokingColor(Color.RED);
	
		contentStream.showText(text);
		contentStream.endText();
	
		// Make sure that the content stream is closed:
		contentStream.close();
	
		// Save the newly created document
		String filename = text;
		document.save("//tmp/" + filename + ".pdf");
	
		// finally make sure that the document is properly
		// closed.
		document.close();
	}
	
}