package src.model;

public class WorkerGroupTileAssociation
{
	WorkerGroup workergroup;
	HexTile location;
	
	public WorkerGroupTileAssociation(WorkerGroup wg, HexTile h)
	{
		workergroup = wg;
		location = h;
	}
	
	public WorkerGroup getWorkerGroup()
	{
		return workergroup;
	}
	
	public HexTile getHexTile()
	{
		return location;
	}
}
