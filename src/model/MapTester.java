package src.model;

import src.model.interfaces.GameTile;

public class MapTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameMap map = new GameMap();
		
		GameTile orig = map.getOrigin();
		GameTile sLoc1 = map.getStartingLocation1();
		GameTile sLoc2 = map.getStartingLocation2();
		
		System.out.println("Origin: " + orig.getTerrainType().type
							+ " (" + orig.getX() + ", " + orig.getY() + ", "
							+ orig.getZ() + ")");
		
		System.out.println("S. Loc. 1: " + sLoc1.getTerrainType().type
				+ " (" + sLoc1.getX() + ", " + sLoc1.getY() + ", "
				+ sLoc1.getZ() + ")");
		
		System.out.println("S. Loc. 2: " + sLoc2.getTerrainType().type
				+ " (" + sLoc2.getX() + ", " + sLoc2.getY() + ", "
				+ sLoc1.getZ() + ")");
	}

}