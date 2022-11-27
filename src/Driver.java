import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

/**
 * Main driver class of the program. Handles creating and updating the GUI.
 * Written in AWT/Swing using WindowBuilder. WORK IN PROGRESS
 * 
 * @author Aditya Kerhalkar
 *
 */
public class Driver extends JFrame {

	private static final long serialVersionUID = 1L;

	// Calculation variables.
	private RSA rsa;
	private ElGamal elgamal;

	private JPanel contentPane;
	private JTextField txtP;
	private JTextField txtNp;
	private JTextField txtPhi;
	private JTextField txtQ;
	private JTextField txtE;
	private JTextField txtD;
	private JTextField txtEncryptedM;
	private JTextField txtDecryptedM;
	private JTextField txtP2;
	private JTextField txtG;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtEGM;
	private JTextField txtM;
	private JTextField txtR;
	private JTextField txtK;
	private JTextField txtC1;
	private JTextField txtC2;
	private JTextField txtKInverse;
	private JTextField txtDecryptedEGM;

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
		elgamal = new ElGamal();

		setTitle("Encryption GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane selectionPane = new JTabbedPane(JTabbedPane.TOP);
		selectionPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectionPane.setBounds(10, 10, 491, 304);
		contentPane.add(selectionPane);

		populateRSA(selectionPane);

		JPanel panelPaillier = new JPanel();
		selectionPane.addTab("Paillier", null, panelPaillier, null);

		JPanel panelElGamal = new JPanel();
		selectionPane.addTab("ElGamal", null, panelElGamal, null);
		panelElGamal.setLayout(null);

		JLabel lblY = new JLabel("Public key parameter (y):");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblY.setEnabled(false);
		lblY.setBounds(236, 71, 159, 19);
		panelElGamal.add(lblY);

		txtP2 = new JTextField();
		txtP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtP2.setColumns(10);
		txtP2.setBounds(124, 39, 64, 21);
		panelElGamal.add(txtP2);

		JLabel lblP2 = new JLabel("Prime (p):");
		lblP2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblP2.setBounds(10, 40, 64, 19);
		panelElGamal.add(lblP2);

		JLabel lblR = new JLabel("Random (r):");
		lblR.setEnabled(false);
		lblR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblR.setBounds(10, 145, 78, 19);
		panelElGamal.add(lblR);

		txtR = new JTextField();
		txtR.setEditable(false);
		txtR.setEnabled(false);
		txtR.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtR.setColumns(10);
		txtR.setBounds(124, 144, 64, 21);
		panelElGamal.add(txtR);

		JLabel lblK = new JLabel("k:");
		lblK.setEnabled(false);
		lblK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblK.setBounds(214, 145, 12, 19);
		panelElGamal.add(lblK);

		txtC1 = new JTextField();
		txtC1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtC1.setEnabled(false);
		txtC1.setEditable(false);
		txtC1.setColumns(10);
		txtC1.setBounds(236, 175, 240, 21);
		panelElGamal.add(txtC1);

		txtC2 = new JTextField();
		txtC2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtC2.setEnabled(false);
		txtC2.setEditable(false);
		txtC2.setColumns(10);
		txtC2.setBounds(236, 205, 240, 21);
		panelElGamal.add(txtC2);

		JLabel lblC1 = new JLabel("C1:");
		lblC1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblC1.setEnabled(false);
		lblC1.setBounds(204, 176, 22, 19);
		panelElGamal.add(lblC1);

		JLabel lblC2 = new JLabel("C2:");
		lblC2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblC2.setEnabled(false);
		lblC2.setBounds(204, 206, 22, 19);
		panelElGamal.add(lblC2);

		JLabel lblKInverse = new JLabel("k^-1:");
		lblKInverse.setEnabled(false);
		lblKInverse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKInverse.setBounds(10, 243, 36, 19);
		panelElGamal.add(lblKInverse);

		txtKInverse = new JTextField();
		txtKInverse.setEditable(false);
		txtKInverse.setEnabled(false);
		txtKInverse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKInverse.setColumns(10);
		txtKInverse.setBounds(56, 242, 132, 21);
		panelElGamal.add(txtKInverse);

		txtDecryptedEGM = new JTextField();
		txtDecryptedEGM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDecryptedEGM.setEnabled(false);
		txtDecryptedEGM.setEditable(false);
		txtDecryptedEGM.setColumns(10);
		txtDecryptedEGM.setBounds(236, 241, 240, 21);
		panelElGamal.add(txtDecryptedEGM);

		JLabel lblDecryptedEGM = new JLabel("m:");
		lblDecryptedEGM.setBounds(214, 243, 17, 19);
		panelElGamal.add(lblDecryptedEGM);
		lblDecryptedEGM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDecryptedEGM.setEnabled(false);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setEnabled(false);
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setR(txtR.getText())) {
					lblK.setEnabled(true);
					lblC1.setEnabled(true);
					lblC2.setEnabled(true);
					lblKInverse.setEnabled(true);
					lblDecryptedEGM.setEnabled(true);
					txtK.setEnabled(true);
					txtC1.setEnabled(true);
					txtC2.setEnabled(true);
					txtKInverse.setEnabled(true);
					txtDecryptedEGM.setEnabled(true);

					String kInfo = String.format("%d^%d mod %d = %d", elgamal.getY(), elgamal.getR(), elgamal.getP(),
							elgamal.getK());
					txtK.setText(kInfo);

					String c1Info = String.format("%d^%d mod %d = %d", elgamal.getG(), elgamal.getR(), elgamal.getP(),
							elgamal.getC1());
					txtC1.setText(c1Info);

					String c2Info = String.format("(%d * %d) mod %d = %d", elgamal.getM(), elgamal.getK(),
							elgamal.getP(), elgamal.getC2());
					txtC2.setText(c2Info);

					String kInverseInfo = String.format("%d^-1 mod %d = %d", elgamal.getK(), elgamal.getP(),
							elgamal.getKInverse());
					txtKInverse.setText(kInverseInfo);

					String decryptedEGMInfo = String.format("(%d * %d) mod %d = %d", elgamal.getKInverse(),
							elgamal.getC2(), elgamal.getP(), elgamal.decryptM());
					txtDecryptedEGM.setText(decryptedEGMInfo);
				}
			}
		});
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDecrypt.setBounds(10, 191, 136, 34);
		panelElGamal.add(btnDecrypt);

		JButton btnGenY = new JButton("Calculate y");
		btnGenY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setElGamalValues(txtEGM.getText(), txtP2.getText(), txtG.getText(), txtX.getText())) {

					lblY.setEnabled(true);
					txtY.setEnabled(true);

					String yInfo = String.format("%d^%d mod %d = %d", elgamal.getG(), elgamal.getX(), elgamal.getP(),
							elgamal.getY());
					txtY.setText(yInfo);

					lblR.setEnabled(true);
					txtR.setEnabled(true);
					txtR.setEditable(true);
					btnDecrypt.setEnabled(true);
				}
			}

		});
		btnGenY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenY.setBounds(340, 10, 136, 34);
		panelElGamal.add(btnGenY);

		JLabel lblG = new JLabel("Generator (g):");
		lblG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblG.setBounds(10, 71, 94, 19);
		panelElGamal.add(lblG);

		txtG = new JTextField();
		txtG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtG.setColumns(10);
		txtG.setBounds(124, 70, 64, 21);
		panelElGamal.add(txtG);

		txtX = new JTextField();
		txtX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtX.setColumns(10);
		txtX.setBounds(124, 100, 64, 21);
		panelElGamal.add(txtX);

		JLabel lblX = new JLabel("Private key (x):");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX.setBounds(10, 101, 97, 19);
		panelElGamal.add(lblX);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 131, 466, 1);
		panelElGamal.add(separator_2);

		txtY = new JTextField();
		txtY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtY.setEnabled(false);
		txtY.setEditable(false);
		txtY.setColumns(10);
		txtY.setBounds(236, 100, 240, 21);
		panelElGamal.add(txtY);

		JLabel lblEGM = new JLabel("Numeric message (m):");
		lblEGM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEGM.setBounds(10, 13, 143, 17);
		panelElGamal.add(lblEGM);

		txtEGM = new JTextField();
		txtEGM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEGM.setColumns(10);
		txtEGM.setBounds(163, 10, 96, 23);
		panelElGamal.add(txtEGM);

		txtK = new JTextField();
		txtK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtK.setEnabled(false);
		txtK.setEditable(false);
		txtK.setColumns(10);
		txtK.setBounds(236, 144, 240, 21);
		panelElGamal.add(txtK);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(10, 235, 466, 1);
		panelElGamal.add(separator_2_1);

	}

	private void populateRSA(JTabbedPane selectionPane) {
		JPanel panelRSA = new JPanel();
		selectionPane.addTab("RSA", null, panelRSA, null);
		panelRSA.setLayout(null);

		JLabel lblQ = new JLabel("q:");
		lblQ.setBounds(94, 72, 19, 19);
		panelRSA.add(lblQ);
		lblQ.setFont(Constants.TAHOMA_BASIC);

		txtP = new JTextField();
		txtP.setFont(Constants.TAHOMA_BASIC);
		txtP.setBounds(39, 71, 45, 21);
		panelRSA.add(txtP);
		txtP.setColumns(10);

		JLabel lblP = new JLabel("p:");
		lblP.setBounds(10, 72, 19, 19);
		panelRSA.add(lblP);
		lblP.setFont(Constants.TAHOMA_BASIC);

		JLabel lblTwoPrimes = new JLabel("Two prime numbers (p and q)");
		lblTwoPrimes.setBounds(10, 43, 191, 19);
		panelRSA.add(lblTwoPrimes);
		lblTwoPrimes.setFont(Constants.TAHOMA_BASIC);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 165, 466, 2);
		panelRSA.add(separator);

		JLabel lblNp = new JLabel("n (p * q):");
		lblNp.setEnabled(false);
		lblNp.setFont(Constants.TAHOMA_BASIC);
		lblNp.setBounds(308, 43, 62, 19);
		panelRSA.add(lblNp);

		txtNp = new JTextField();
		txtNp.setFont(Constants.TAHOMA_BASIC);
		txtNp.setEditable(false);
		txtNp.setEnabled(false);
		txtNp.setColumns(10);
		txtNp.setBounds(380, 42, 96, 21);
		panelRSA.add(txtNp);

		JLabel lblPhi = new JLabel("\u03D5(n) (p-1 * q-1):");
		lblPhi.setFont(Constants.TAHOMA_BASIC);
		lblPhi.setEnabled(false);
		lblPhi.setBounds(260, 72, 110, 19);
		panelRSA.add(lblPhi);

		txtPhi = new JTextField();
		txtPhi.setFont(Constants.TAHOMA_BASIC);
		txtPhi.setEnabled(false);
		txtPhi.setEditable(false);
		txtPhi.setColumns(10);
		txtPhi.setBounds(380, 71, 96, 21);
		panelRSA.add(txtPhi);

		JLabel lblECandidates = new JLabel("<html>e-candidates: <br/> (coprime to \u03D5)</html>");
		lblECandidates.setFont(Constants.TAHOMA_BASIC);
		lblECandidates.setEnabled(false);
		lblECandidates.setBounds(274, 110, 96, 45);
		panelRSA.add(lblECandidates);

		JLabel lblE = new JLabel("e:");
		lblE.setEnabled(false);
		lblE.setFont(Constants.TAHOMA_BASIC);
		lblE.setBounds(10, 177, 19, 19);
		panelRSA.add(lblE);

		JLabel lblD = new JLabel("Private key (d):");
		lblD.setEnabled(false);
		lblD.setFont(Constants.TAHOMA_BASIC);
		lblD.setBounds(156, 177, 98, 19);
		panelRSA.add(lblD);

		txtD = new JTextField();
		txtD.setEditable(false);
		txtD.setEnabled(false);
		txtD.setFont(Constants.TAHOMA_BASIC);
		txtD.setColumns(10);
		txtD.setBounds(260, 177, 45, 21);
		panelRSA.add(txtD);

		JComboBox<String> cbxECandidates = new JComboBox<String>();
		cbxECandidates.setEnabled(false);
		cbxECandidates.setBounds(380, 130, 96, 21);
		cbxECandidates.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				txtE.setText(e.getItem().toString());

			}
		});
		panelRSA.add(cbxECandidates);

		JLabel lblEncryptedM = new JLabel("Encrypted m:");
		lblEncryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEncryptedM.setEnabled(false);
		lblEncryptedM.setBounds(166, 214, 84, 19);
		panelRSA.add(lblEncryptedM);

		JLabel lblDecryptedM = new JLabel("Decrypted m:");
		lblDecryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDecryptedM.setEnabled(false);
		lblDecryptedM.setBounds(164, 243, 90, 19);
		panelRSA.add(lblDecryptedM);

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
		btnSetE.setBounds(10, 206, 110, 34);
		panelRSA.add(btnSetE);

		JButton btnSetValues = new JButton("Set values");
		btnSetValues.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // On clicking to set values...

				if (setRSAValues(txtM.getText(), txtP.getText(), txtQ.getText())) {

					// enable display
					lblNp.setEnabled(true);
					txtNp.setEnabled(true);
					lblPhi.setEnabled(true);
					txtPhi.setEnabled(true);
					lblECandidates.setEnabled(true);
					cbxECandidates.setEnabled(true);
					lblE.setEnabled(true);
					btnSetE.setEnabled(true);
					txtE.setEditable(true);

					// set values
					txtNp.setText(rsa.getN().toString());
					txtPhi.setText(rsa.getPhi().toString());

					// displaying in the box (but reset first)
					cbxECandidates.removeAllItems();

					for (BigInteger bi : rsa.getECandidates()) {
						cbxECandidates.addItem(bi.toString());
					}

				}

			}
		});
		btnSetValues.setFont(Constants.TAHOMA_BASIC);
		btnSetValues.setBounds(10, 121, 110, 34);
		panelRSA.add(btnSetValues);

		txtQ = new JTextField();
		txtQ.setFont(Constants.TAHOMA_BASIC);
		txtQ.setColumns(10);
		txtQ.setBounds(123, 71, 45, 21);
		panelRSA.add(txtQ);

		txtE = new JTextField();
		txtE.setFont(Constants.TAHOMA_BASIC);
		txtE.setEditable(false);
		txtE.setColumns(10);
		txtE.setBounds(39, 177, 45, 21);
		panelRSA.add(txtE);

		txtEncryptedM = new JTextField();
		txtEncryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEncryptedM.setEnabled(false);
		txtEncryptedM.setEditable(false);
		txtEncryptedM.setColumns(10);
		txtEncryptedM.setBounds(260, 213, 216, 21);
		panelRSA.add(txtEncryptedM);

		txtDecryptedM = new JTextField();
		txtDecryptedM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDecryptedM.setEnabled(false);
		txtDecryptedM.setEditable(false);
		txtDecryptedM.setColumns(10);
		txtDecryptedM.setBounds(260, 242, 216, 21);
		panelRSA.add(txtDecryptedM);

		txtM = new JTextField();
		txtM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtM.setColumns(10);
		txtM.setBounds(163, 10, 96, 23);
		panelRSA.add(txtM);

		JLabel lblM = new JLabel("Numeric message (m):");
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblM.setBounds(10, 13, 143, 17);
		panelRSA.add(lblM);
	}

	protected boolean setRSAValues(String m, String p, String q) {
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

	protected boolean setElGamalValues(String m, String p, String g, String x) {
		// Setting the values for ElGamal calculation (prime, generator and private
		// key).

		try {
			BigInteger mInt = new BigInteger(m);
			BigInteger pInt = new BigInteger(p);
			BigInteger gInt = new BigInteger(g);
			BigInteger xInt = new BigInteger(x);

			elgamal.setValues(mInt, pInt, gInt, xInt);
			return true;

		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid format");
		}
		return false;
	}

	protected boolean setR(String r) {

		try {
			BigInteger rInt = new BigInteger(r);

			elgamal.setR(rInt);
			return true;

		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid format");
		}
		return false;

	}
}