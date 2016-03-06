package cma.cards;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class Commands implements CommandMarker {

	@Autowired
	private PdfService pdfService;

	@CliAvailabilityIndicator({ "test" })
	public boolean isCommandAvailable() {
		return true;
	}

	@CliCommand("card")
	public void simple(@CliOption(mandatory = true, key = "") String text) {
		try {
			pdfService.createPdf(text, "/tmp/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
