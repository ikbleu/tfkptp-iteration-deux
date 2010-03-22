package src.model;

import java.util.List;

import src.model.enums.Direction;
import src.model.interfaces.GameTile;

public class MapTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GameMap map = new GameMap(null);
		
		GameTile orig = map.getOrigin();
		GameTile sLoc1 = map.getStartingLocation1();
		GameTile sLoc2 = map.getStartingLocation2();
		
		
		
		System.out.println("Origin: " + orig);
		
		System.out.println("S. Loc. 1: " + sLoc1);
		
		System.out.println("S. Loc. 2: " + sLoc2);
		
		System.out.println("\nSurrounding tile test:");
		List<GameTile> l = orig.getTilesAround(2);
		
		System.out.println(l.size());
		for (int i = 0; i < l.size(); i++)
		{
			GameTile n = l.get(i);
			System.out.println("Tile " + n);
		}
		
		System.out.println("\nSurrounding tile test 2:");
		l = orig.getTilesAround(2);
		
		System.out.println(l.size());
		for (int i = 0; i < l.size(); i++)
		{
			GameTile n = l.get(i);
			System.out.println("Tile " + n);
		}
		
		System.out.println("\nSurrounding tile test 3:");
		
		l = sLoc1.getTilesAround(2);
		
		System.out.println(l.size());
		for (int i = 0; i < l.size(); i++)
		{
			GameTile n = l.get(i);
			System.out.println("Tile" + n);
		}
		
		System.out.println("\nDirections: ");
		List<Direction> l2 = orig.getDirectionsTo(orig.getNeighbor(Direction.N).getNeighbor(Direction.NE).getNeighbor(Direction.NE).getNeighbor(Direction.N).getNeighbor(Direction.N));
		
		for (int i = 0; i < l2.size(); i++)
			System.out.println(l2.get(i).direction);
		
		System.out.println("\nDirections 2: ");
		l2 = orig.getDirectionsTo(orig.getNeighbor(Direction.N).getNeighbor(Direction.NW).getNeighbor(Direction.NW).getNeighbor(Direction.N).getNeighbor(Direction.NW));
		
		for (int i = 0; i < l2.size(); i++)
			System.out.println(l2.get(i).direction);
		
		System.out.println("\nDirections 3: ");
		l2 = orig.getDirectionsTo(orig.getNeighbor(Direction.S).getNeighbor(Direction.SW).getNeighbor(Direction.SW).getNeighbor(Direction.S).getNeighbor(Direction.SW));
		
		for (int i = 0; i < l2.size(); i++)
			System.out.println(l2.get(i).direction);
		
		System.out.println("\nDirections 3: ");
		l2 = orig.getDirectionsTo(orig.getNeighbor(Direction.S).getNeighbor(Direction.SE).getNeighbor(Direction.S).getNeighbor(Direction.S).getNeighbor(Direction.SE));
		
		for (int i = 0; i < l2.size(); i++)
			System.out.println(l2.get(i).direction);
		
		System.out.println("\nDirection tiles test:");
		
		l = orig.getTilesInDirection(Direction.SE, 4);
		for (int i = 0; i < l.size(); i++)
			System.out.println(l.get(i));
		
		System.out.println("\nDirection tiles test 2:");
		
		l = sLoc2.getTilesInDirection(Direction.SE, 4);
		for (int i = 0; i < l.size(); i++)
			System.out.println(l.get(i));
		
	}

}
