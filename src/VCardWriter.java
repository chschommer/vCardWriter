import java.io.File;

public class VCardWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * / Debug Test f is a ldif File => test.scanLDIF
		 * 
		 * n is a file in my format => test.scanAdressFile()
		 * 
		 * set debug to true for run Tests
		 */

		boolean debug = true;
		if (debug) {
			File n = new File("test/nTest");
		//	File f = new File("test/test.ldif");
			File b = new File("test/ldif.vCard");
			try {
				Reader test = new Reader(n, b);
			//	test.scanLDIF();
				test.scanMyFormat();
				test.writeVCard();

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Debug: Done");
			System.exit(0);
		}

		if (args.length < 3) {
			System.out
					.println("CSVWRiter [Type. LDIF or N] [input-File] [output-File-Name]");
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
