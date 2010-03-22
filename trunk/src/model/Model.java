package src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import src.model.exceptions.YoureDoingItWrongException;
import src.model.instances.rallypoints.RallyPointInitializer;
import src.model.instances.structures.StructureInitializer;
import src.model.instances.units.UnitInitializer;
import src.model.interfaces.Clock;
import src.util.HandFactory;

public class Model {
	public Player p; // TODO: remove (testing purposes)
	private Clock c;
	private GameMap map; 
	
	public final int TICK_LENGTH = 1000;
	
	public Model( HandFactory hFact )
	{
		List<String> orderList = new ArrayList<String>();
		orderList.add("Model");
		orderList.add("VisUpdate");
		orderList.add("View");
		
		try {
			c = new DethKlok(orderList, TICK_LENGTH);
		} catch (YoureDoingItWrongException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		map = new GameMap(this);

                ItemManager im = ItemManager.getInstance();
                im.setMap(map);

                HashMap<String, Integer> startRsc = new HashMap<String, Integer>();
                startRsc.put("rscFood", 999);
                startRsc.put("rscMetal", 200);
                startRsc.put("rscEnergy", 200);

                ResourceManager rm = new ResourceManager(startRsc);
		
		human = new Player( true, hFact, map, map.getStartingLocation1(), rm, c);
                rm.setPlayer(human);
		p = human; // TODO: remove
		// TODO: ai? map?

                WorkerManager wm = new WorkerManager(human, rm);
		
		UnitInitializer.initialize( human );
		StructureInitializer.initialize( human );
		RallyPointInitializer.initialize( human );
	}
	
	public Clock getClock()
	{
		return c;
	}
	
	public Player human()
	{
		return human;
	}
	private Player human;
}
