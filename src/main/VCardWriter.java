package main;
import java.io.File;
import test.Test;

public class VCardWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		
		/*
		 * For Testing use this:
		 * 
		 * Test t = new Test();
		 * t.test_LDIF_FILE();
		 * t.test_N_FILE();
		 * t.stop()
		 * 
		 * you need to check if the ouputfiles are correct.
		 * 
		 * TODO: Auto verify of .vcard files by comparing them with Input Files
		 */
		

		if (args.length < 3) {
			System.out
					.println("VCardWriter [Type. LDIF or N] [input-File] [output-File-Name]");
			System.exit(1);
		}

		String end = ".vcf";
		String type = args[0];
		File f = new File(args[1]);
		File b = new File(args[2] + end);

		try {
			Reader test = new Reader(f, b);
			if (type.equals("LDIF"))
				test.scanLDIF();

			else {
				test.scanMyFormat();
			}
			test.writeVCard();
			System.out.println("Done. Look At " + args[2] + end);

		} catch (Exception e) {
			System.out
					.println("Somethin went wrong, with Input or Output File");
			e.printStackTrace();
		}

	}

}
