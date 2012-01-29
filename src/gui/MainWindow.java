package gui;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButton;

import main.Reader;

public class MainWindow {

	private boolean ldfi = false;
	private boolean n = false;
	private boolean field1 = false;
	private boolean field2 = false;
	private Reader wr;
	private File f1, f2;
	private JFormattedTextField PathToIntput, PathToOuput;
	private JButton btnChoosePath, StartTranslating_Button;
	private JRadioButton ldif_Button;
	private JRadioButton N_Button;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setTitle("vCardWriter 0.1");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 588, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		PathToIntput = new JFormattedTextField();
		PathToIntput.setBounds(12, 130, 364, 19);
		frame.getContentPane().add(PathToIntput);

		btnChoosePath = new JButton("Open");
		btnChoosePath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser test = new JFileChooser();

				test.showOpenDialog(btnChoosePath);
				// test.getApproveButtonText();
				if (test.getSelectedFile() != null) {
					f1 = new File(test.getSelectedFile().toString());
					PathToIntput.setText(test.getSelectedFile().toString());
					field1 = true;
				}
			}
		});

		btnChoosePath.setBounds(426, 127, 107, 25);

		frame.getContentPane().add(btnChoosePath);

		PathToOuput = new JFormattedTextField();
		PathToOuput.setBounds(12, 207, 364, 19);
		frame.getContentPane().add(PathToOuput);

		JButton Save_Button = new JButton("Save");
		Save_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser sv = new JFileChooser();

				sv.showOpenDialog(btnChoosePath);
				// test.getApproveButtonText();
				if (sv.getSelectedFile() != null) {
					System.err.println(sv.getSelectedFile());
					f2 = sv.getSelectedFile();
					PathToOuput.setText(sv.getSelectedFile().toString());
					field2 = true;
				}
			}
		});
		Save_Button.setBounds(426, 204, 107, 25);
		frame.getContentPane().add(Save_Button);

		ldif_Button = new JRadioButton("LDIF");
		N_Button = new JRadioButton("N");

		ldif_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ldfi = true;
				n = false;
				N_Button.setSelected(false);

			}
		});
		ldif_Button.setBounds(49, 262, 134, 23);
		frame.getContentPane().add(ldif_Button);

		N_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				n = true;
				ldfi = false;
				ldif_Button.setSelected(false);
			}
		});
		N_Button.setBounds(49, 304, 134, 23);
		frame.getContentPane().add(N_Button);

		StartTranslating_Button = new JButton("Start Translating");
		StartTranslating_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (n && field1 && field2) {
					try {
						wr = new Reader(f1, f2);
						System.err.println(f2.toString());
						wr.scanMyFormat();
						wr.writeVCard();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (ldfi && field1 && field2) {
					try {
						wr = new Reader(f1, f2);
						wr.scanLDIF();
						wr.writeVCard();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		StartTranslating_Button.setBounds(321, 283, 181, 25);
		frame.getContentPane().add(StartTranslating_Button);

	}
}