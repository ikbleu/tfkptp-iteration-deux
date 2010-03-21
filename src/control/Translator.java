package src.control;

import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Map;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class Translates from key to string representation of that key and vice versa. 
 * @author Chris
 *
 */
public class Translator 
{
//This stores all keys that have been entered for toString and their corresponding string representation. 
private static Map< String, KeyCodeAndModifiers > repToKey = new Hashtable< String, KeyCodeAndModifiers >();
private RepToKey r;

//initialize repToKey with all key<->stringRepresentations saved when 
//the game was last run.  
Translator(){
	r = readObject("controller config files/repToKey.dat");
	repToKey = r.repToKey();
}

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
	
	writeObject(new RepToKey(repToKey),"controller config files/repToKey.dat");
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
/**
 * 
 * @param filename
 * @return RepToKey wrapper object holding the hash table. If failed, returns null.
 */
private static RepToKey readObject(String filename){
	        
			RepToKey r = null;
	        ObjectInputStream inputStream = null;
	        
	        try {
	            
	            //Construct the ObjectInputStream object
	            inputStream = new ObjectInputStream(new FileInputStream(filename));
	            
	            Object obj = null;
	            
	            while ((obj = inputStream.readObject()) != null) {
	                
	                if (obj instanceof RepToKey) {
	                	r = (RepToKey) obj;
	                }
	                
	            }

	        } catch (EOFException ex) { //This exception will be caught when EOF is reached
	            System.out.println("End of file reached.");
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the ObjectInputStream
	            try {
	                if (inputStream != null) {
	                    inputStream.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        return r;
	    
}

}
