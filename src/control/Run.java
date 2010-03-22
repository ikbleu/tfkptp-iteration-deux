package src.control;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import src.control.interfaces.KeyEventInterpreter;

public class Run {
	
    static Listener l;
    static JFrame frame = new JFrame();
    static DisplayApp app = new DisplayApp();
    static KeyEventInterpreter kei;
    
	public static void main(String[] args)
	{
		KeyMap m = new KeyMap();
		l = new Listener(kei,m);
		//need to initialize kei
		BufferedReader r;
		try{
		r = new BufferedReader(new FileReader("controller config files/test.txt"));
		FileHandler.readFile(m,r);
		}catch (IOException ex) {
        ex.printStackTrace();
		}	
		//System.out.println(m);
		//System.out.println("Change group CTRL+UP to U. (keycode 85)");
		//m.bindMeaning("Group", "SpinNext", new KeyCodeAndModifiers(85, 0));
		//System.out.println(m.getMeaning(context, visitor, key))?
		try{
			BufferedWriter w = new BufferedWriter(new FileWriter("controller config files/controlsWritten.txt"));
			FileHandler.writeFile(m,w);
		}catch (IOException ex){
			ex.printStackTrace();
		}
		
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
