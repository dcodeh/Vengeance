import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JPanel {

	public static final long start = System.currentTimeMillis();
	public static long end;
	
	Input i = new Input();
	
	public static String getSecondsElapsed(){
		long now = System.currentTimeMillis();
        return String.format("%.1f", ((now - start) / 1000.0));
	}
	
	public void stopTimer(){
		end = System.currentTimeMillis();
	}
	
	public static void main(String [] args) throws InterruptedException{
		JFrame frame = new JFrame("Vengeance HUD");
		Game window = new Game();
		frame.add(window);
		frame.setAlwaysOnTop(true);
		
		//set the window icon
		BufferedImage icon;
		try {
			icon = ImageIO.read(new File("src/vengeance.jpg"));
			frame.setIconImage(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setSize(685, 565); //after a lot of back and forth, i've decided on a pleasing window size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //make it open in the center, like everything ought to
		
		
		Thread console = new Thread(new Input());
		console.start();
		
		while(true){
			//This is for graphics
			frame.repaint();
			Thread.sleep(100);
		}
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D gd = (Graphics2D) g;
		gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, 700, 600);
		
		try {
			BufferedImage img;
			img = ImageIO.read(new File("src/finishedBG.png"));
			gd.drawImage(img, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gd.setFont(new Font("Arial", Font.ITALIC, 26));
		gd.setColor(Color.black);
		gd.drawString(getSecondsElapsed(), 0, 26);
	
		gd.drawString(String.valueOf(i.getEnterprise().nearWarpDrive.size()), 195, 124);
		gd.drawString(String.valueOf(i.getEnterprise().nearShieldGen.size()), 276, 181);
		gd.drawString(String.valueOf(i.getEnterprise().nearPhotons.size()), 438, 94);
		gd.drawString(String.valueOf(i.getEnterprise().nearPhasers.size()), 438, 271);
		gd.drawString(String.valueOf(i.getEnterprise().nearBridge.size()), 438, 174);
		gd.drawString(String.valueOf(i.getEnterprise().nearSensors.size()), 558, 179);
		
	}
	
}
