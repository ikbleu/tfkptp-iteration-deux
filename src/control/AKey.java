package src.control;

import src.control.interfaces.InterpretableKeyConfig;
import src.control.interfaces.KeyMapInterface;
import src.control.interfaces.KeyMapVisitor;

public class AKey implements InterpretableKeyConfig{

	private KeyCodeAndModifiers key;
	private KeyMapInterface map;
	
	AKey(KeyCodeAndModifiers key, KeyMapInterface map){
		this.key = key;
		this.map = map;
	}
	
	public void interpret(String context, KeyMapVisitor visitor) {
		map.getMeaning(context, visitor, key);
	}

}
