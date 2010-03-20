package src.control;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class Run {
	
    static Listener l = new Listener();
    static JFrame frame = new JFrame();
    static DisplayApp app = new DisplayApp();
    
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				frame.setTitle("Sandbox!");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Dimension d = new Dimension(800, 100);
				frame.setSize(d);
				
				frame.addKeyListener(l);
				frame.setVisible(true);
				frame.add(app);
			}
		} );
		
	}

}
