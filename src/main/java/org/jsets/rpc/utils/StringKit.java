package org.jsets.rpc.utils;

import java.util.UUID;

public class StringKit {
	/**
	 * UUID
	 */
	public static String getUUID()
	{
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	public static String getUUID(String fix)
	{
		UUID uuid=UUID.randomUUID();
		return fix+"_"+(uuid.toString().replaceAll("-", ""));
	}
	
	public static boolean isEmpty(String string){
		if(string==null||"".equals(string)||string == "null"){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static boolean isNotEmpty(String string){
		return !isEmpty(string);
	}
}
