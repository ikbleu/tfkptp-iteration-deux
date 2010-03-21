/**
 * 
 */
package src.model;

import src.model.interfaces.Clock;
import src.model.interfaces.ResourceBehavior;
import src.model.interfaces.Tickable;

/**
 * 
 * Used by resources that refill over time
 * 
 * @author Adam
 *
 */
public class RenewableResourceBehavior implements ResourceBehavior, Tickable
{
	private final int MAX_RESOURCES = 999;
	private final int RENEWAL_RATE;
	private int currentResources;
	
	public RenewableResourceBehavior(int startVal, int renewalRate, Clock clock)
	{
		if (startVal > MAX_RESOURCES)
			currentResources = MAX_RESOURCES;
		else 
			currentResources = startVal;
		
		RENEWAL_RATE = renewalRate;
		if (clock != null)
			clock.register(this);
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
	@Override
	public void tick()
	{
		currentResources += RENEWAL_RATE;
		if (currentResources > MAX_RESOURCES)
			currentResources = MAX_RESOURCES;
	}

	public int getAmount() {
		// TODO Auto-generated method stub
		return currentResources;
	}
}
