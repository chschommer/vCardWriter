package test;

import java.io.File;

import main.Reader;

public class Test {
	
	
	public void test_N_FILE(){

			File n = new File("test/nTest");
		
			File b = new File("test/nTest.vCard");
			try {
				Reader test = new Reader(n, b);
				test.scanMyFormat();
				test.writeVCard();

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Debug: Done N-TEST");
		}
	
	
	public void test_LDIF_FILE(){
		File f = new File("test/test.ldif");		
		File b = new File("test/ldif_Test.vCard");
		try {
			Reader test = new Reader(f, b);
            test.scanLDIF();
			test.writeVCard();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Debug: Done LDIF-TEST");
	}
		
	public void stop(){
		System.exit(0);
	}
	

}
