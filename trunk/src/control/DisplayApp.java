package src.control;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class DisplayApp extends JApplet{

	private static final long serialVersionUID = 5757460493237222765L;

	static Graphics2D g;
	static BufferedImage imageBuffer;
	DisplayApp(){
		
       imageBuffer = new BufferedImage( 800, 100, BufferedImage.TYPE_INT_ARGB );
        g = imageBuffer.createGraphics();
        
		Font f1 = new Font( "Times Roman", Font.BOLD, 14 );
        g.setFont( f1 );
	}

	public void paintComponent(Graphics g)
	{
		//super.paintComponent(g);
	}
	public void drawText(int index, String s)
	{
		g.drawString(s, 30*index, 30);
		displayBufferedImage(imageBuffer);
	}
    // method used to actually display the image
    private void displayBufferedImage( BufferedImage image )
    {
        this.setContentPane( new JLabel( new ImageIcon( image ) ) );
        this.validate();
    }
    
}

