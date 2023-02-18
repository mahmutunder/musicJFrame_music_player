import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class musicOpen extends JFrame {
	
	
	
	JFileChooser chose;
	File file;
	JLabel title;
	JButton open;
	JPanel titlePanel;
	JPanel btnPanel;
	JButton play;
	JButton stop;
	String getInfo;
	AudioInputStream audioStream;
	Clip clip;
	public musicOpen() {
		
		titlePanel= new JPanel();
		btnPanel= new JPanel();
		title= new JLabel("title");
		title.setFont(new Font("MV Boli", Font.PLAIN,30));
		open= new JButton("open");
		play= new JButton("Play");
		stop= new JButton("Stop");
		
		open.addActionListener(new openFile());
		play.addActionListener(new openFile());
		stop.addActionListener(new openFile());
		
		titlePanel.add(title);
		btnPanel.add(open);
		btnPanel.add(play);
		btnPanel.add(stop);
		
		
		this.add(titlePanel);
		this.add(btnPanel);
		

		this.setTitle("Music player");
		this.setLayout(new GridLayout(2,1));
		this.setSize(400,400);
		this.setLocation(600,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
	public class openFile implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==open) {
				chose= new JFileChooser();
				chose.setCurrentDirectory(new File("C:\\Users\\mahmut\\Desktop"));
				int res=chose.showOpenDialog(null);
				
				if(res==JFileChooser.APPROVE_OPTION) {
					 getInfo=chose.getSelectedFile().getName();

					title.setText(getInfo);
					System.out.println(getInfo);
				}
			}
			
			if(e.getSource()==play) {
				file = new File(getInfo);
				try {
					 audioStream =AudioSystem.getAudioInputStream(file);
					clip=AudioSystem.getClip();
					clip.open(audioStream);
					
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {

					e1.printStackTrace();
				}

				clip.start();
				title.setText("Playing "+getInfo);
				
			}
			if(e.getSource()==stop) {
				clip.stop();
				title.setText("Stoped "+getInfo);
			}
			
		}
		
	}
	
	

	public static void main(String[] args) {


		new musicOpen();
	}

}
