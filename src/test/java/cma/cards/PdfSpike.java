package cma.cards;

import org.junit.Test;

public class PdfSpike {

	private PdfService service = new PdfService();

	@Test
	public void testName() throws Exception {
		service.createPdf("Test", "/tmp/");
	}

}
