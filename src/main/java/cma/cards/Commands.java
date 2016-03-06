package cma.cards;

import java.awt.print.PrinterException;
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

	@CliAvailabilityIndicator({ "card" })
	public boolean isCommandAvailable() {
		return true;
	}

	@CliCommand("card")
	public void card(@CliOption(mandatory = true, key = "") String text,
			@CliOption(key = "print", specifiedDefaultValue = "true", unspecifiedDefaultValue = "false") boolean print)
			throws IOException, PrinterException {
		pdfService.createPdf(text, "/tmp/", 240, print);
	}

}
