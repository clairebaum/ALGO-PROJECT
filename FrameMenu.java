import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;

public class FrameMenu extends JFrame implements ActionListener {

	// attributes of class frameGame
	public JPanel animPanel;
	public JButton next;
	public int clicked;
	public static FrameGame mainFrame;
	public JLabel text;
	public JLabel controls;
	public JLabel animl0, animl1, animl2, animl3, animl4, animl5, animl6 ;
	public ImageIcon anim0, anim1, anim2, anim3, anim4, anim5, anim6 ;
	
	public FrameMenu(String name, int width, int height) {
		super(name);
		this.setSize(width, height);
		this.setLocation(200, 5);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.BLACK); 
		this.setLayout(null);
		
		// JPanel that contains the animation frame
		animPanel = new JPanel();
		animPanel.setBounds(250, 30, 500, 375);
		animPanel.setBackground(Color.BLACK);
		this.add(animPanel);
		
		//JLabel for the explanation text 
		text = new JLabel (" ",SwingConstants.CENTER);
		text.setBounds(250,420,520,40);
		text.setForeground(Color.WHITE);
		this.add(text);
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		text.setBorder(border);
		text.setFont(new Font("Monospaced", Font.ITALIC, 14));
		text.setVisible(false);
		
		//control JLabel
		controls = new JLabel ("<html> CONTROLS <br/> <br/> HERO : arrows <br/> <br/> UFO : Z Q S D ",SwingConstants.CENTER);
		controls.setBounds(250,500,500,160);
		controls.setForeground(Color.WHITE);
		this.add(controls);
		controls.setVisible(false);
		
		// JButton to get to the next animation
		next = new JButton("NEXT...");
		next.setSize(100,40);
		next.setLocation(460,680);
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.addActionListener(this);
		text.setFont(new Font("Monospaced", Font.ITALIC, 14)); 
		this.add(next);
		
		
		//variable to change animation frame
		clicked = 0; 
		
		
		//animation Panel
		anim0 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/0.jpg");
		anim1 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/1.jpg");
		anim2 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/2.jpg");
		anim3 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/3.jpg");
		anim4 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/4.jpg");
		anim5 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/5.jpg");
		anim6 = new ImageIcon("C:/Users/gadis/Desktop/INSA Lyon/FIMI 2A/ALGO/projet v2/ALGO-PROJECT-April_version/animation/6.jpg");
		animl0 = new JLabel(anim0);
		animl0.setBounds(0, 0, 500, 375);
		animPanel.add(animl0);
		animl1 = new JLabel(anim1);
		animl1.setBounds(0, 0, 500, 375);
		animl1.setVisible(false);
		animPanel.add(animl1);
		animl2 = new JLabel(anim2);
		animl2.setBounds(0, 0, 500, 375);
		animl2.setVisible(false);
		animPanel.add(animl2);
		animl3 = new JLabel(anim3);
		animl3.setBounds(0, 0, 500, 375);
		animl3.setVisible(false);
		animPanel.add(animl3);
		animl4 = new JLabel(anim4);
		animl4.setBounds(0, 0, 500, 375);
		animl4.setVisible(false);
		animPanel.add(animl4);
		animl5 = new JLabel(anim5);
		animl5.setBounds(0, 0, 500, 375);
		animl5.setVisible(false);
		animPanel.add(animl5);
		animl6 = new JLabel(anim6);
		animl6.setBounds(0, 0, 500, 375);
		animl6.setVisible(false);
		animPanel.add(animl6);
		
		//main frame
		mainFrame = new FrameGame("UFO ATTACK", 1000, 800); //creates the game window
		mainFrame.mainPanel.gameThread.start(); //launches the game
		mainFrame.setVisible(false);
		mainFrame.requestFocusInWindow(); //the java KeyListener only works when the window is focused   
		this.addKeyListener(new KeyHandler());
		
		
				
		
		this.setVisible(true);
		this.setFocusable(true);

	}
	
	public void actionPerformed(ActionEvent e){
		clicked ++; 
		System.out.println(clicked);
		
		switch (clicked){
			default :
			break;
			case 1: 
				animl0.setVisible(false);
				animl1.setVisible(true);
				text.setVisible(true);
				text.setText("What a beautiful night...");
				
			break;
			case 2: 
				animl1.setVisible(false);
				animl2.setVisible(true);
				text.setText("HUH?!");
			break;
			case 3:
				animl2.setVisible(false);
				animl3.setVisible(true);
				text.setText(" What's this weird light?");
			break;
			case 4:
				animl3.setVisible(false);
				animl4.setVisible(true);
				text.setVisible(false);
			break;
			case 5:
				animl4.setVisible(false);
				animl5.setVisible(true);
				
			break;
			case 6:
				animl5.setVisible(false);
				animl6.setVisible(true);
				text.setVisible(true);
				text.setText("AN UFO ?!!");
			break;
			case 7:
				text.setText("Quick! Make sur the Hero returns home before the UFO catch them!");
				controls.setVisible(true);
			break ;
			case 8:
				this.setVisible(false);
				mainFrame.setVisible(true);
			break;
		}
	}
}
