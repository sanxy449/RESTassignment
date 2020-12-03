package com.RESTtest;

import org.json.*;
import java.util.concurrent.ConcurrentHashMap;

public class CoffeeShop {

	public static void main(String[] args) {
		String pricing = "";
		String order="";
		Reader read=new Reader();
		
		
		//reading daily prices 
		pricing=read.Read("http://5e28257b120f820014bf415a.mockapi.io/api/v1/beverage");
		
			
			
		ConcurrentHashMap<String,Double> productPricing = new ConcurrentHashMap<>();
		String code="";
		double price=0.0;
			
		JSONArray jsonarray1 = new JSONArray(pricing);
			
		for (int i = 0; i < jsonarray1.length(); i++) {
			JSONObject jsonobject1 = jsonarray1.getJSONObject(i);
			code = jsonobject1.getString("code");
			price= jsonobject1.getDouble("cost");
			productPricing.put(code, price);  //placing key and the value on a hashtable
		}
		
		
		//pricing for the day debugging
		/*for (String i : productPricing.keySet()) {
			System.out.println("code: " + i + " cost: " + productPricing.get(i));
		}*/
			
			
		//reading daily orders
		order=read.Read("http://5e28257b120f820014bf415a.mockapi.io/api/v1/order");
			
		JSONArray jsonarray2 = new JSONArray(order);
		
		String id="";
		boolean espresso_classic=false;
		boolean espresso_arabica=false;
		boolean espresso_decaf=false;
		boolean milk=false;
		boolean soy=false;
		boolean caramel_syrop=false;
		boolean almond_milk=false;
		double order_cost=0;
		double total_cost=0;
		Writer writer=new Writer();
		for (int i = 0; i < jsonarray2.length(); i++) {
			JSONObject jsonobject2 = jsonarray2.getJSONObject(i);
			id = jsonobject2.getString("id");
			
			espresso_classic = jsonobject2.getBoolean("espresso_classic");
			if(espresso_classic) {
				order_cost+=productPricing.get("espresso_classic");
				
			}
			
			espresso_arabica = jsonobject2.getBoolean("espresso_arabica");
			if(espresso_arabica) {
				order_cost+=productPricing.get("espresso_arabica");
				
			}
			
			espresso_decaf = jsonobject2.getBoolean("espresso_decaf");
			if(espresso_decaf) {
				order_cost+=productPricing.get("espresso_decaf");
				
			}
			
			milk = jsonobject2.getBoolean("milk");
			if(milk) {
				order_cost+=productPricing.get("milk");
				
			}
			
			soy = jsonobject2.getBoolean("soy");
			if(soy) {
				order_cost+=productPricing.get("soy");
				
			}
			
			caramel_syrop = jsonobject2.getBoolean("caramel_syrop");
			if(caramel_syrop) {
				order_cost+=productPricing.get("caramel_syrop");
				
			}
			
			almond_milk = jsonobject2.getBoolean("almond_milk");
			if(almond_milk) {
				order_cost+=productPricing.get("almond_milk");
				
			}
			
			//debugging
			//System.out.println(id+espresso_classic+espresso_arabica+espresso_decaf+milk+soy+caramel_syrop+almond_milk);
			
			order_cost=Math.round(order_cost * 100.0) / 100.0; //round needed , float operations messed by binary system 
			total_cost+=order_cost;
			total_cost=Math.round(total_cost * 100.0) / 100.0;
			writer.Write("order id: "+id+"   order cost: "+order_cost);
			//System.out.println(id+" "+order_cost); //debugging
			order_cost=0;
		}
		writer.Write(System.lineSeparator()+System.lineSeparator()+"TOTAL INCOME: "+String.valueOf(total_cost));
		//debugging
		System.out.println(total_cost);

	}

}
