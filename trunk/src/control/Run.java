package src.control;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import javax.swing.*;

import src.control.interfaces.KeyEventInterpreter;
import src.view.View;

public class Run {
	
    static Listener l;
    static JFrame frame = new JFrame();
    static DisplayApp app = new DisplayApp();
    
	public static void main(String[] args)
	{
		KeyMap m = new KeyMap();
		KeyEventInterpreter kei = ControllerTestConfigurer.root();
		l = new Listener(kei,m);
		BufferedReader r;
		try{
            System.out.println(new File("bllalkakak").getAbsoluteFile());
            
		r = new BufferedReader(new FileReader("controller config files/test.txt"));
		FileHandler.readFile(m,r);
		}catch (IOException ex) {
        ex.printStackTrace();
		}	
		//System.out.println(m);
		//System.out.println("Change group CTRL+UP to U. (keycode 85)");
		//m.bindMeaning("Group", "SpinNext", new KeyCodeAndModifiers(85, 0));
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
