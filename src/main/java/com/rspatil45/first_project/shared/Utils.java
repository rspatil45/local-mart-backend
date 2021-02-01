package com.rspatil45.first_project.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random random = new SecureRandom();
	private final String alphabet= "01abcd45ABCdLOKGDTV89567323liebawmasdfjklmzwe013";
	public String generateUserId(int length) {
		return generatedRandomString(length);
	}
	
	private String generatedRandomString(int length)
	{
		StringBuilder returnValue= new StringBuilder(length);
		
		for(int i = 0;i<length; i++)
		{
			returnValue.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		
		return new String(returnValue);
		
	}
}
