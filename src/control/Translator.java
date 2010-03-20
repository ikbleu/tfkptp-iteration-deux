package src.control;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Translator 
{
private static Map< String, KeyCodeAndModifiers > repToKey = new Hashtable< String, KeyCodeAndModifiers >();

public static KeyCodeAndModifiers toKeyCodeAndModifiers(String keyRepresentation)
{
	if(repToKey.containsKey(keyRepresentation))
	{
		return repToKey.get(keyRepresentation);
	}
	else
	{
		System.out.println(keyRepresentation +"not initialized.");
		return null;
	}	
}

/**
 * 
 * @param key is a KeyEvent to convert to a string
 * @return a string representation of the keyEvent of the form
 * Left or 1 or a or Up or Ctrl+Up or Shift+Ctrl+Up etc. 
 */
public static String toString(KeyEvent key) {

	int m = key.getModifiersEx();
	String keyText = KeyEvent.getKeyText(key.getKeyCode());
	String modifiers = KeyEvent.getModifiersExText(key.getModifiersEx());
	String toReturn;
	
	if(m !=0)//there are 1 or more modifiers
		toReturn=modifiers +"+"+keyText;
	else
		toReturn= keyText;
	
	KeyCodeAndModifiers k = new KeyCodeAndModifiers(key.getKeyCode(),key.getModifiers());
	//if never seen this key before, then put it in the map. 
	if(!repToKey.containsValue(k))
		repToKey.put(toReturn,k);
	
	//save this map to a file in controller config files
	writeObject(repToKey,"controller config files/repToKey.dat");
	return toReturn;
  }

private static void writeObject(Object o, String filename) {
    
    ObjectOutputStream outputStream = null;
    try {
        //Construct the LineNumberReader object
        outputStream = new ObjectOutputStream(new FileOutputStream(filename));
        outputStream.writeObject(o);       
        
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        //Close the ObjectOutputStream
        try {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
}
