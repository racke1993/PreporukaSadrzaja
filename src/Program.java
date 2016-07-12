import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logika.Metode;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Program extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnPokreniUpit;
	private JTextField textField_1;
	private JLabel lblUpitiSeNalaze;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
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
	public Program() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUnesiteNazivTeksta = new JLabel("Unesite naziv teksta koji predstavlja Vaš upit");
		lblUnesiteNazivTeksta.setBounds(68, 88, 290, 16);
		contentPane.add(lblUnesiteNazivTeksta);
		
		textField = new JTextField();
		textField.setBounds(126, 116, 176, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnPokreniUpit = new JButton("Pokreni upit");
		btnPokreniUpit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String unos = ("/probniUpiti/").concat(textField.getText().trim());
				if(!Metode.postoji(unos)){
					textField_1.setText("probajte opet sa postojecim fajlovima");
				}
				else
				{
				String rezultat = Algoritmi2.vratiMinHillClimbing("probniUpiti/"+textField.getText().trim());
				textField_1.setText(rezultat);
				}
			}
		});
		btnPokreniUpit.setBounds(154, 144, 117, 29);
		contentPane.add(btnPokreniUpit);
		
		textField_1 = new JTextField();
		textField_1.setBounds(23, 216, 398, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblUpitiSeNalaze = new JLabel("Upiti se nalaze u folderu probniUpiti");
		lblUpitiSeNalaze.setBounds(6, 6, 278, 16);
		contentPane.add(lblUpitiSeNalaze);
	}
}
