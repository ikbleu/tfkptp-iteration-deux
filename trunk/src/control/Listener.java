package src.control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener{
	
	//invoked when key pressed and released
	public void keyTyped(KeyEvent e){
	}
	public void keyPressed(KeyEvent e){
	}
	public void keyReleased(KeyEvent e){
		Translator t = new Translator();
		System.out.println(t.toString(new KeyCodeAndModifiers(e.getKeyCode(),e.getModifiers())));
		
	}

}
