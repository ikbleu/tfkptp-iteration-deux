package src.model;

import src.model.interfaces.Clock;
import src.model.interfaces.Resource;

class RenewableResource implements Resource
{
	RenewableResourceBehavior behavior;
	String type;
	
	public RenewableResource(String rType, int startValue, Clock clock)
	{
		behavior = new RenewableResourceBehavior(startValue, 5, clock);
		type = rType;
	}
	
	@Override
	public String getResourceType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public int harvest(int i) {
		// TODO Auto-generated method stub
		return behavior.harvest(i);
	}

	public int getAmount() {
		// TODO Auto-generated method stub
		return behavior.getAmount();
	}
}
