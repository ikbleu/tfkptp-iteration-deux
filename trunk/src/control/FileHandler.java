package src.control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import src.control.interfaces.*;

public class FileHandler {
	
	public static void main(String[] args)
	{
		KeyMap m = new KeyMap();
		BufferedReader r;
		try{
		r = new BufferedReader(new FileReader("controller config files/controls.txt"));
		readFile(m,r);
		}catch (IOException ex) {
        ex.printStackTrace();
		}	
		System.out.println(m);
		System.out.println("Change group CTRL+UP to U. (keycode 85)");
		m.bindMeaning("Group", "SpinNext", new KeyCodeAndModifiers(85, 0));
		try{
			BufferedWriter w = new BufferedWriter(new FileWriter("controller config files/controlsWritten.txt"));
			writeFile(m,w);
		}catch (IOException ex){
			ex.printStackTrace();
		}
		
	}
	
	public static final String BEGINCONTEXT = "begincontext";
	public static final String ENDCONTEXT = "endcontext";

	public static void writeFile(BindingMapDirector director, BufferedWriter writer)
	{
        try {//bad. This assumes a director is a KeyMap with a valid toString method.  
            writer.write(director.toString());
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedWriter
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	/**
	 * This method builds the BindingMapBuilder (KeyMap) object from a file specified in reader. 
	 * @param builder
	 * @param reader
	 */
	public static void readFile(BindingMapBuilder builder, BufferedReader reader)
	{
		 try {
	          	String line = null;
	            builder.start();
	            while ((line = reader.readLine()) != null) {
	            	//System.out.println("Building new file line.");
	                //Print for debug
	                //System.out.println(line);
	                //Process data.  
	                if(line.compareTo(BEGINCONTEXT)==0)
	                {
	                	String temp = reader.readLine();
	                	builder.context(temp);
	                	//System.out.println("Set Builder context: "+temp);
	                }
	                else if(line.compareTo(ENDCONTEXT)==0)
	                {
	                	//do nothing
	                }
	                else //a possible complete binding
	                {
	                	String meaning, stringRepresentation;
	                	StringTokenizer st = new StringTokenizer(line);
	                	
	                	if(st.countTokens()==2) // complete binding
	                	{
	                		meaning = st.nextToken();
	                		stringRepresentation = st.nextToken();
	                		Translator trans = new Translator();
		                	builder.binding(trans.toKeyCodeAndModifiers(stringRepresentation), meaning);
		                	//System.out.println("Set binding "+meaning+" "+stringRepresentation);
		                	//System.out.println("stringRepresentation is:"+trans.toKeyCodeAndModifiers(stringRepresentation));
	                	}
	                	else{
	                		//System.out.println("This line did not have two tokens. Did nothing. ");
	                		//System.out.println("Incomplete binding or Unbound meaning: "+line);
	                	}
	                		//invalid. Cannot add a binding unless binding is complete. 
	                		//every binding must be complete.  (had key and meaning) 
	                		//simply don't add it to KeyMap.                  		
	                }
	            }
	            builder.end();
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            //Close the BufferedReader
	            try {
	                if (reader != null)
	                    reader.close();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
		
	}
}
