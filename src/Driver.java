import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * Main driver class of the program. Handles creating and updating the GUI.
 * Written in AWT/Swing using WindowBuilder. WORK IN PROGRESS
 * 
 * @author Aditya Kerhalkar
 *
 */
public class Driver extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtM;
	private JTextField txtP;
	private JTextField txtNp;
	private JTextField txtPhi;
	private JTextField txtQ;
	private JTextArea txtECandidates;

	// Calculation variables.
	private RSA rsa;
	private JTextField txtE;
	private JTextField txtD;
	private JTextField txtEncryptedM;
	private JTextField txtDecryptedM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver frame = new Driver();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Driver() {
		rsa = new RSA();

		setTitle("Encryption GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRsa = new JLabel("RSA Encryption");
		lblRsa.setBounds(10, 10, 110, 17);
		lblRsa.setFont(Constants.TAHOMA_HEADER);
		contentPane.add(lblRsa);

		JPanel panel = new JPanel();
		panel.setBounds(10, 37, 391, 330);
		contentPane.add(panel);
		panel.setLayout(null);

		txtM = new JTextField();
		txtM.setFont(Constants.TAHOMA_BASIC);
		txtM.setBounds(163, 6, 96, 23);
		panel.add(txtM);
		txtM.setColumns(10);

		JLabel lblNumMessage = new JLabel("Numeric message (m):");
		lblNumMessage.setBounds(10, 9, 143, 17);
		panel.add(lblNumMessage);
		lblNumMessage.setFont(Constants.TAHOMA_BASIC);

		JLabel lblQ = new JLabel("q:");
		lblQ.setBounds(94, 67, 19, 19);
		panel.add(lblQ);
		lblQ.setFont(Constants.TAHOMA_BASIC);

		txtP = new JTextField();
		txtP.setFont(Constants.TAHOMA_BASIC);
		txtP.setBounds(39, 66, 45, 21);
		panel.add(txtP);
		txtP.setColumns(10);

		JLabel lblP = new JLabel("p:");
		lblP.setBounds(10, 67, 19, 19);
		panel.add(lblP);
		lblP.setFont(Constants.TAHOMA_BASIC);

		JLabel lblTwoPrimes = new JLabel("Two prime numbers (p and q)");
		lblTwoPrimes.setBounds(10, 39, 191, 19);
		panel.add(lblTwoPrimes);
		lblTwoPrimes.setFont(Constants.TAHOMA_BASIC);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 371, 2);
		panel.add(separator);

		JLabel lblNp = new JLabel("n (p * q):");
		lblNp.setEnabled(false);
		lblNp.setFont(Constants.TAHOMA_BASIC);
		lblNp.setBounds(10, 112, 62, 19);
		panel.add(lblNp);

		txtNp = new JTextField();
		txtNp.setFont(Constants.TAHOMA_BASIC);
		txtNp.setEditable(false);
		txtNp.setEnabled(false);
		txtNp.setColumns(10);
		txtNp.setBounds(104, 111, 64, 21);
		panel.add(txtNp);

		JLabel lblPhi = new JLabel("\u03D5(n) (p-1 * q-1):");
		lblPhi.setFont(Constants.TAHOMA_BASIC);
		lblPhi.setEnabled(false);
		lblPhi.setBounds(197, 112, 110, 19);
		panel.add(lblPhi);

		txtPhi = new JTextField();
		txtPhi.setFont(Constants.TAHOMA_BASIC);
		txtPhi.setEnabled(false);
		txtPhi.setEditable(false);
		txtPhi.setColumns(10);
		txtPhi.setBounds(317, 111, 64, 21);
		panel.add(txtPhi);

		JLabel lblECandidates = new JLabel("e-candidates: (coprime to \u03D5)");
		lblECandidates.setFont(Constants.TAHOMA_BASIC);
		lblECandidates.setEnabled(false);
		lblECandidates.setBounds(10, 141, 191, 19);
		panel.add(lblECandidates);

		JLabel lblE = new JLabel("e:");
		lblE.setEnabled(false);
		lblE.setFont(Constants.TAHOMA_BASIC);
		lblE.setBounds(288, 141, 19, 19);
		panel.add(lblE);

		JLabel lblD = new JLabel("Private key (d):");
		lblD.setEnabled(false);
		lblD.setFont(Constants.TAHOMA_BASIC);
		lblD.setBounds(10, 240, 103, 19);
		panel.add(lblD);

		txtD = new JTextField();
		txtD.setEditable(false);
		txtD.setEnabled(false);
		txtD.setFont(Constants.TAHOMA_BASIC);
		txtD.setColumns(10);
		txtD.setBounds(123, 239, 45, 21);
		panel.add(txtD);

		JLabel lblEncryptedM = new JLabel("Encrypted m:");
		lblEncryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEncryptedM.setEnabled(false);
		lblEncryptedM.setBounds(10, 269, 96, 19);
		panel.add(lblEncryptedM);

		JLabel lblDecryptedM = new JLabel("Decrypted m:");
		lblDecryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDecryptedM.setEnabled(false);
		lblDecryptedM.setBounds(10, 300, 96, 19);
		panel.add(lblDecryptedM);

		JButton btnSetE = new JButton("Set e");
		btnSetE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Time to set e

				if (setE(txtE.getText())) {

					if (rsa.getD().equals(BigInteger.ZERO)) {
						System.out.println("Error: Invalid e (must be coprime to phi)");
					} else {
						lblD.setEnabled(true);
						txtD.setEnabled(true);
						txtD.setText(rsa.getD().toString());

						lblEncryptedM.setEnabled(true);
						txtEncryptedM.setEnabled(true);
						lblDecryptedM.setEnabled(true);
						txtDecryptedM.setEnabled(true);

						// getting the final results
						String encryptInfo = String.format("%d^%d mod %d = %d", rsa.getM(), rsa.getE(), rsa.getN(),
								rsa.encryptM());
						txtEncryptedM.setText(encryptInfo);

						String decryptInfo = String.format("%d^%d mod %d = %d", rsa.encryptM(), rsa.getD(), rsa.getN(),
								rsa.decryptM());
						txtDecryptedM.setText(decryptInfo);
					}
				}
			}
		});
		btnSetE.setEnabled(false);
		btnSetE.setFont(Constants.TAHOMA_BASIC);
		btnSetE.setBounds(271, 184, 110, 34);
		panel.add(btnSetE);

		JButton btnSetValues = new JButton("Set values");
		btnSetValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // On clicking to set values...

				if (setValues(txtM.getText(), txtP.getText(), txtQ.getText())) {

					// enable display
					lblNp.setEnabled(true);
					txtNp.setEnabled(true);
					lblPhi.setEnabled(true);
					txtPhi.setEnabled(true);
					lblECandidates.setEnabled(true);
					txtECandidates.setEnabled(true);
					lblE.setEnabled(true);
					btnSetE.setEnabled(true);
					txtE.setEditable(true);

					// set values
					txtNp.setText(rsa.getN().toString());
					txtPhi.setText(rsa.getPhi().toString());

					// displaying in the box (but reset first)
					txtECandidates.setText(null);

					for (BigInteger bi : rsa.getECandidates()) {
						txtECandidates.append(bi.toString());
						txtECandidates.append(" ");
					}

				}

			}
		});
		btnSetValues.setFont(Constants.TAHOMA_BASIC);
		btnSetValues.setBounds(271, 59, 110, 34);
		panel.add(btnSetValues);

		txtQ = new JTextField();
		txtQ.setFont(Constants.TAHOMA_BASIC);
		txtQ.setColumns(10);
		txtQ.setBounds(123, 66, 45, 21);
		panel.add(txtQ);

		txtECandidates = new JTextArea();
		txtECandidates.setWrapStyleWord(true);
		txtECandidates.setFont(Constants.TAHOMA_BASIC);
		txtECandidates.setEnabled(false);
		txtECandidates.setEditable(false);
		txtECandidates.setLineWrap(true);
		txtECandidates.setBounds(10, 170, 158, 48);
		panel.add(txtECandidates);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 228, 371, 2);
		panel.add(separator_1);

		txtE = new JTextField();
		txtE.setFont(Constants.TAHOMA_BASIC);
		txtE.setEditable(false);
		txtE.setColumns(10);
		txtE.setBounds(317, 140, 64, 21);
		panel.add(txtE);

		txtEncryptedM = new JTextField();
		txtEncryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEncryptedM.setEnabled(false);
		txtEncryptedM.setEditable(false);
		txtEncryptedM.setColumns(10);
		txtEncryptedM.setBounds(123, 268, 258, 21);
		panel.add(txtEncryptedM);

		txtDecryptedM = new JTextField();
		txtDecryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDecryptedM.setEnabled(false);
		txtDecryptedM.setEditable(false);
		txtDecryptedM.setColumns(10);
		txtDecryptedM.setBounds(123, 299, 258, 21);
		panel.add(txtDecryptedM);

	}

	protected boolean setValues(String m, String p, String q) {
		// Setting the values for RSA calculation. (m = message, p and q = prime
		// numbers)

		try {
			BigInteger mInt = new BigInteger(m);
			BigInteger pInt = new BigInteger(p);
			BigInteger qInt = new BigInteger(q);

			rsa.setValues(mInt, pInt, qInt);
			return true;

		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid format");
		}

		return false;
	}

	protected boolean setE(String e) {

		try {
			BigInteger eInt = new BigInteger(e);

			rsa.setE(eInt);
			return true;

		} catch (NumberFormatException nfe) {
			System.out.println("Error: Invalid format");
		}
		return false;
	}
}
