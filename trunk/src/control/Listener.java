package src.control;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.control.interfaces.KeyEventInterpreter;
import src.control.interfaces.KeyMapInterface;
public class Listener implements KeyListener{
	
	KeyEventInterpreter root;
	KeyMapInterface keymap;
	
	Listener(KeyEventInterpreter root, KeyMapInterface keymap){
		this.root = root;
		this.keymap = keymap;
	}
	public void keyTyped(KeyEvent e){
	}
	public void keyPressed(KeyEvent e){
	}
	public void keyReleased(KeyEvent e){
		root.interpret(new AKey(new KeyCodeAndModifiers(e.getKeyCode(),e.getModifiers()),keymap));
		//Translator t = new Translator();
		//For Configuration: System.out.println(t.toString(new KeyCodeAndModifiers(e.getKeyCode(),e.getModifiersEx())));
		
		
	}

}
