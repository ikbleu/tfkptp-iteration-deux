package src.model;

import src.model.interfaces.Resource;

class NonRenewableResource implements Resource
{
	NonRenewableResourceBehavior behavior;
	String type;
	
	public NonRenewableResource(String rType, int startValue)
	{
		behavior = new NonRenewableResourceBehavior(startValue);
		type = rType;
	}
	
	@Override
	public String getResourceType()
	{
		return type;
	}

	@Override
	public int harvest(int i)
	{
		return behavior.harvest(i);
	}

}
