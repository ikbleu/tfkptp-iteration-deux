package src.control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import src.control.interfaces.*;
import src.model.control.BindingMapBuilder;
import src.model.control.BindingMapDirector;

public class FileHandler {
	
	public static void main(String[] args)
	{
		KeyMap m = new KeyMap();
		BufferedReader r;
		try{
		r = new BufferedReader(new FileReader("control config files/controls.txt"));
		readFile(m,r);
		}catch (FileNotFoundException ex) {
        ex.printStackTrace();
		}catch (IOException ex) {
        ex.printStackTrace(); 
        System.out.println(m);
	}
    
	}
	
	public static final String BEGINCONTEXT = "begincontext";
	public static final String ENDCONTEXT = "endcontext";

	public void writeFile(BindingMapDirector director, BufferedWriter writer)
	{
	}
	public static void readFile(BindingMapBuilder builder, BufferedReader reader)
	{
		 try {
	          	String line = null;
	            
	            while ((line = reader.readLine()) != null) {
	                //Print for debug
	                System.out.println(line);
	                //Process data.  
	                if(line.compareTo(BEGINCONTEXT)==0)
	                {
	                	builder.context(reader.readLine());
	                }
	                else if(line.compareTo(ENDCONTEXT)==0)
	                {
	                	//do nothing
	                }
	                else //a possible complete binding
	                {
	                	String bindingLine = null;
	                	String meaning, stringRepresentation;
	                	StringTokenizer st = new StringTokenizer(bindingLine);
	                	
	                	if(st.countTokens()==2) // complete binding
	                	{
	                		meaning = st.nextToken();
	                		stringRepresentation = st.nextToken();
		                	builder.binding(Translator.toKeyCodeAndModifiers(stringRepresentation), meaning);
	                	}
	                	else{} //Incomplete binding or Unbound meaning
	                		//invalid. Cannot add a binding unless binding is complete. 
	                		//every binding must be complete.  (had key and meaning) 
	                		//simply don't add it to KeyMap.                  		
	                }
	            }
	            
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