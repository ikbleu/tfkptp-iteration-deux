package src.control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Listener extends KeyAdapter{

	//nested Wheels better.
	//(nested looping lists) lists of lists of lists. 
	
	ArrayList<String> groups = new ArrayList<String>();
	Listener()
	{
		groups.addAll(Arrays.asList(tgroups)); 
	}

	String[] tgroups = {"Units","Armies","Structures","Rally Points"};
	String[] tUnitTypes = {"Ranged","Melee","Colonist","Explorer"};
	String[] tStructureTypes = {"Base","Tower","Barracks","University"};
	//String[] ArmyTypes = {"1","2","3","4"};
	//String[] RallyPointTypes = {"RP 1","RP 2","RP 3","RP 4"};
	
	String[] tUnitInstances = {"Melee1","Archer1","Melee2","Colonist1"};
	String[] tStructureInstances = {"Base1","Tower1","Barracks3","University7"};
	String[] tArmyInstances = {"A1","A2","A3","A4"};
	String[] tRallyPointInstances = {"RP 1","RP 2","RP 3","RP 4"};
	
	String[] tRallyPointCommands = {"move", "attack","defend","powerdown"};
	String[] tArmyCommands ={"add unit","disband","decomm","remove"};
	
	//Unit Commands
	String[] tMeleeCommands = {"fight","defend","power down","power up"};
	String[] tArcherCommands = {"fight","defend","power down","power up"};
	String[] tColonistCommands = {"walk","harvest","sing","move"};
	String[] tExplorerCommands = {"explore","see","jump","move"};
	
	//Structure Commands
	String[] tBaseCommands = {"make unit", "heal","powerup","powerdown"};
	String[] tUniversityCommands ={"research","clean","talk","live"};
	String[] tTowerCommands = {"attack", "shoot","see","powerdown"};
	String[] tBarracksCommands = {"make unit", "heal","powerup","powerdown"};

	boolean ctrl =false;
	
	int stage=0;
	int g=0;
	int t=0;
	int i=0;
	int c=0;
	
	//invoked when key pressed and released
	public void keyTyped(KeyEvent e){
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_CONTROL)
			ctrl = true;
	}

	public void keyReleased(KeyEvent e){
		System.out.println(Translator.toString(e));
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_CONTROL)
			ctrl = false;
		if(ctrl)//if control pressed
		{
			switch(key){
			case KeyEvent.VK_UP:
				g=((g+1)%4);
				break;
			case KeyEvent.VK_DOWN:
				g=((g+3)%4);
				break;
			case KeyEvent.VK_LEFT:
				
			break;
			case KeyEvent.VK_RIGHT:
				break;
			}
		}
		else
		{
			switch(key){
			case KeyEvent.VK_LEFT:
				break;
			case KeyEvent.VK_RIGHT:
				//System.out.println("Cycled instance right.");
				break;
			case KeyEvent.VK_UP:
				//System.out.println("Cycled command up.");
				break;
			case KeyEvent.VK_DOWN:
				//System.out.println("Cycled command down.");
			}
			
		}
		
	}

}
