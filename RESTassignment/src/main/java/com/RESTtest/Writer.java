package com.RESTtest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
	
	public void Write(String data) {
		try(FileWriter fw = new FileWriter("TheOrders.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter pw = new PrintWriter(bw)){
			pw.println(data);
			pw.println("-------------------------------------");
		      //pw.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR");
		      e.printStackTrace();
		    }	
	}

}
