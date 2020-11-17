package com.example.demo.utils;

import org.springframework.stereotype.Component;

@Component
public class BasicEncryption {
	
	String key = "22121997";
	
	
	public String encryptPlainText(String plainText) {
		
		StringBuffer password = new StringBuffer();
		char[] pwd = plainText.toCharArray();
		for(int i=0; i< pwd.length;i++)
		{
			String hexString = Integer.toHexString(pwd[i]);
			password.append(hexString);
		}
		String result = password.toString();
		String encrypted = result + key;
		return encrypted;
        
    }

	public String decryptCipherText(String cipherText)
	{
		String decryptPassword = new String();
		if(cipherText.contains(key))
		{
			String password = cipherText.replace(key, "");
			char[] hexToString = password.toCharArray();
			for(int i=0;i<hexToString.length; i=i+2)
			{
				String st = ""+hexToString[i]+""+hexToString[i+1];
		         char ch = (char)Integer.parseInt(st, 16);
		         decryptPassword = decryptPassword + ch;
			}
			
			return decryptPassword;
		}
		
		return "Decryption failed";
	}
}
