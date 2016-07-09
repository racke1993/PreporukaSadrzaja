import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestCloudFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestCloudFrame frame = new TestCloudFrame();
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
	public void ubaciTagCloud(){
		
//		String[] WORDS = { "art", "australia", "baby", "beach", "birthday", "blue", "bw", "california", "canada", "canon",
//	            "cat", "chicago", "china", "christmas", "city", "dog", "england", "europe", "family", "festival", "flower", "flowers", "food",
//	            "france", "friends", "fun", "germany", "holiday", "india", "italy", "japan", "london", "me", "mexico", "music", "nature",
//	            "new", "newyork", "night", "nikon", "nyc", "paris", "park", "party", "people", "portrait", "sanfrancisco", "sky", "snow",
//	            "spain", "summer", "sunset", "taiwan", "tokyo", "travel", "trip", "uk", "usa", "vacation", "water", "wedding" };
//		System.out.println(WORDS.length);
		String[] nizPredefinanihUpita = new String[9];
		nizPredefinanihUpita[0] = "world news global terrorists europe asia africa australia middle east";
		nizPredefinanihUpita[1] = "sport football basketball soccer volleyball rugby golf cycling racing mma";
		nizPredefinanihUpita[2] = "culture film tv radio music games books arts design classical pop rap metal rock";
		nizPredefinanihUpita[3] = "business stocks shares money banking retail market payments investments risk";
		nizPredefinanihUpita[4] = "lifestyle health fitness recipes advices clothing love family women home food";
		nizPredefinanihUpita[5] = "fashion dress suits jacket bags denim luxurious blogs style trend tattoo hair";
		nizPredefinanihUpita[6] = "environment wildlife flood climate change energy pollution global warming";
		nizPredefinanihUpita[7] = "technology it ios android windows tablets laptops games microsoft apple facebook twitter";
		nizPredefinanihUpita[8] = "travel holiday hotels hostel airbnb sea lake mountain explore tourism";
		
		
		String[] WORDS = prebaciStringUNiz(nizPredefinanihUpita);
		
		
        JPanel panel = new JPanel();
        Cloud cloud = new Cloud();
        Random random = new Random();
        for (String s : WORDS) {
            for (int i = random.nextInt(108); i > 0; i--) {
                cloud.addTag(s);
            }
        }

        for (Tag tag : cloud.tags()) {
            final JLabel label = new JLabel(tag.getName());
            label.setForeground(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
            label.setOpaque(false);
            label.setFont(label.getFont().deriveFont((float) tag.getWeight() * 10));
            panel.add(label);
        }
        getContentPane().add(panel,BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setSize(1200, 900);
        this.setVisible(true);
	}
	
	public int procesirajUpit(String[] niz, String upit){
		
		int indeks = -1;
		
		for(int i = 0;i<niz.length;i++){
			
			for(int j = 0 ; j<niz[i].split(" ").length;j++){
				
				if(upit.equals(niz[i].split(" ")[j])){
					return i;
				}
				
			}
			
		}
		
		return indeks;
		
	}
	
	public String[] prebaciStringUNiz(String[] nizPredefinanihUpita){
		String[] niz = new String[108];
		
		int brojac = 0;
		//niz = (String[]) niz2.toArray();
		for(int i=0;i<nizPredefinanihUpita.length;i++){
				for(int j = 0 ; j<nizPredefinanihUpita[i].split(" ").length;j++){
				
				niz[brojac++] = nizPredefinanihUpita[i].split(" ")[j];
				
			}
		}
		
		return niz;
	}
	
	public TestCloudFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
        JLabel labelUpit = new JLabel("Unesite kljucne reci Vase pretrage"); 
        JTextField textInput = new JTextField(SwingConstants.CENTER);
        textInput.setPreferredSize( new Dimension( 400, 24 ) );
        JButton realizujUpit = new JButton(); 
        
        realizujUpit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        realizujUpit.setText("Pokreni upit");
        
		JPanel subPanel = new JPanel();
		subPanel.add(labelUpit);
	    subPanel.add(textInput);
	    subPanel.add(realizujUpit);
	    getContentPane().add(subPanel,BorderLayout.PAGE_START);
	    
		ubaciTagCloud();
		
		
	}

}
