package app.common;


public class KeyGenerator
{

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/**
	 * Generates random AlphaNumberic string value
	 * @param size of return value 
	 * @return String
	 */
	public static final String randomAlphaNumeric(int size) 
	{
		StringBuilder builder = new StringBuilder();
		
		while (size-- != 0) 
		{
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		
		return builder.toString();
	
	}	
	
}
