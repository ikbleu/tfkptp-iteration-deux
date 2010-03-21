package src.model.exceptions;

public class YoureDoingItWrongException extends Exception
{
	private static final long serialVersionUID = -2569742692851643643L;
	
	public YoureDoingItWrongException(String message)
	{
		super(message);
	}
}
