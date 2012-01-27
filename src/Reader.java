import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.io.FileWriter;


public class Reader {

	private File file;
	private File dest;
	private Scanner sc;
	private FileWriter wr;
	private String name, givenName, mail;
	private Vector<Entry> Input;

	public Reader(File data, File save) throws IOException {
		file = data;
		dest = save;
		Input = new Vector<Entry>();
		sc = new Scanner(file);
		wr = new FileWriter(dest, false);
	}

	public void scanLDIF() {
		int times = 0;
		boolean first = true;
		while (sc.hasNext()) {

			if (sc.findInLine("givenName:") != null) {
				name = sc.nextLine();
			}

			if (sc.findInLine("sn:") != null) {
				givenName = sc.nextLine();
			}

			sc.nextLine();

			if (sc.findInLine("mail:") != null) {
				mail = sc.nextLine();

			}

			if (name != null && givenName != null && mail != null) {
				Entry a = new Entry(name, givenName, mail);

				if ((!Input.isEmpty() && !Input.get(times - 1).equals(a))
						|| first) {
					Input.add(a);
					first = false;
					times++;
				}

			}

		}
	}

	public void scanMyFormat() {

		while (sc.hasNext()) {
			if (sc.hasNext("Name:")) {
				sc.next();
				name = sc.nextLine();
			}
			if (sc.hasNext("Vorname:") || sc.hasNext("GivenName:")) {
				sc.next();
				givenName = sc.nextLine();
			}
			if (sc.hasNext("Mail:")) {
				sc.next();
				mail = sc.nextLine();
			}

			Input.add(new Entry(name, givenName, mail));
		}
	}

	public void writeVCard() throws IOException {
		if (Input.isEmpty())
			throw new IllegalStateException("Need to run a scan~ function before");
		
		
		String header = "BEGIN:VCARD\n" + "VERSION:3.0\n";
		String N = "N:";
		String FN = "FN:";
		String Mail = "EMAIL;TYPE=INTERNET:";
		String End = "END:VCARD\n";

		for (Entry e : Input) {
			wr.write(header);
			wr.write(N + e.getName() + ";" + e.getGivenName() + "\n");
			wr.write(FN + e.getGivenName() + " " + e.getName() + "\n");
			wr.write(Mail + e.getMail() + "\n");
			wr.write(End);
			wr.flush();
		}

		wr.close();
	}

}
