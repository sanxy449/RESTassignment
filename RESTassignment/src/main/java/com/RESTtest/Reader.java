package com.RESTtest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Reader {

String Read(String link) {
		
		String str="";
		try
		{
			URL url = new URL(link);
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			 
			conn.setRequestMethod("GET");
			conn.connect();
			//API response msg
			int responsecode = conn.getResponseCode();
			//debugging response code
			//System.out.println("Response code is: " +responsecode);
			
			
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{
				//reading from de stream
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					str+=sc.nextLine();
				}
				
				//Close teh stream 
				sc.close();
			}
			
			
			//Disconnect from da stream
			conn.disconnect();
			
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return str;
		
	}
	
	
}
