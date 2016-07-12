import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logika.Metode;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgramWebPreporuka extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblUnesiteNazivFajla;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramWebPreporuka frame = new ProgramWebPreporuka();
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
	public ProgramWebPreporuka() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Pokreni upit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String unos = ("/upiti/").concat(textField.getText().trim());
				if(!Metode.postoji(unos)){
					textField_1.setText("probajte opet sa postojecim fajlovima");
				}
				else
				{
				String rezultat = Algoritmi.vratiMinHillClimbing("upiti/"+(textField.getText().trim()));
				textField_1.setText(rezultat);
				}
			}
		});
		button.setBounds(263, 132, 117, 29);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(235, 92, 176, 28);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 186, 398, 28);
		contentPane.add(textField_1);
		
		lblUnesiteNazivFajla = new JLabel("Unesite naziv fajla koji će predstavljati Vaš upit. Na primer - treciUpit.txt");
		lblUnesiteNazivFajla.setBounds(58, 64, 544, 16);
		contentPane.add(lblUnesiteNazivFajla);
		
		lblNewLabel = new JLabel("Upiti -  folder upiti, tekstovi - folder konacniFajlovi");
		lblNewLabel.setBounds(38, 250, 522, 16);
		contentPane.add(lblNewLabel);
	}

}
