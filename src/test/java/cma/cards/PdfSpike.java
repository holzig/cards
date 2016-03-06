package cma.cards;

import org.junit.Test;

public class PdfSpike {

	private PdfService service = new PdfService();

	@Test
	public void testName() throws Exception {
		String text = "Kuh";
		service.createPdf(text, "/tmp/", 240, false);
	}


}
