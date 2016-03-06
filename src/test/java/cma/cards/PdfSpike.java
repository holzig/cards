package cma.cards;

import java.awt.geom.AffineTransform;

import org.apache.fontbox.util.BoundingBox;
import org.junit.Test;

public class PdfSpike {

	private static final float POINTS_PER_INCH = 72;
	private static final float MM_PER_INCH = 1 / (10 * 2.54f) * POINTS_PER_INCH;

	private PdfService willi = new PdfService(); 
	
	@Test
	public void testName() throws Exception {
		
		String text = "Hanna";
		// Create a new empty document
		willi.createPdf(text);
	}
	
	private double ptToMm(float width) {
		return width * 25.4 / 72;
	}

}
