
/*
 * LightStemmer2 finds the word's stem by:
 * 
 *   1- removing the conjunction و (and) if the remainder of the word 
 *      is 3 or more characters long
 *   2- removing any of the definite articles if this leaves 2 
 *      or more characters.
 *   3- removing any of the suffixes (هـ,ة) that are found at the end 
 *      of the word if this leaves 2 or more characters
 *   
 * See Larkey et al. (2002)
 * "Improving Stemming for Arabic Information Retrieval:Light 
 *           Stemming and Co-occurrence Analysis"
 * 
 * 
 */

package com.AQAS.question_processessing.utilities;

public class LightStemmer3 {
 
	public String findStem(String token)
	{
		String stem="";
		token=token.trim();
		String[] txtToken=token.split("\\s");
		if (txtToken.length > 1)
		{
			System.out.println("error: findStem function finds a stem for one token each time!");
            return stem;
		}
		else
		{
			if (token.length() >= 4 && token.charAt(0) == '\u0648')
			{
				token = token.substring(1);
			}
			if (token.length() >= 4 && token.charAt(0) == '\u0627' && token.charAt(1) == '\u0644')
			{
				token = token.substring(2);
			}
			
	        if (token.length() >= 5 && 
	           ((token.charAt(0) == '\u0628' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')
	             ||(token.charAt(0) == '\u0648' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')
			     ||(token.charAt(0) == '\u0643' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')
			     ||(token.charAt(0) == '\u0641' && token.charAt(1) == '\u0627' && token.charAt(2) == '\u0644')))
	         {
		        token = token.substring(3);
	         }
	        
	        if (token.length()>=3 && token.charAt(token.length()-1) == '\u0647')
	         {
	        	token = token.substring(0,token.length()-1);
	         }
	        
	        if (token.length()>=3 && token.charAt(token.length()-1) == '\u0629')
	         {
	        	token = token.substring(0,token.length()-1);
	         }
			
	        stem=token;
			return stem;
		}
	}
	
}
