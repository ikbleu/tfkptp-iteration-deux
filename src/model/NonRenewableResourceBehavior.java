/**
 * 
 */
package src.model;

import src.model.interfaces.ResourceBehavior;

/**
 * 
 * Used by resources that refill over time
 * 
 * @author Adam
 *
 */
public class NonRenewableResourceBehavior implements ResourceBehavior
{
	private final int MAX_RESOURCES = 999;
	private int currentResources;
	
	public NonRenewableResourceBehavior(int startVal)
	{
		if (startVal > MAX_RESOURCES)
			currentResources = MAX_RESOURCES;
		else 
			currentResources = startVal;
	}
	
	@Override
	public int harvest(int n)
	{
		if (currentResources >= n)
			currentResources -= n;
		else
		{
			n = currentResources;
			currentResources = 0;
		}
		
		return n;
	}
	
	public int getAmount() {
		// TODO Auto-generated method stub
		return currentResources;
	}
}
