package com.ling.backend.util;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class Hashing {
	static String plainText = "";
	
	public static void main(String [] arg) throws FileNotFoundException, IOException, ParseException {
	
			String example = "testing.10.testing";
			ArrayList<String> arraylist= new ArrayList<>();
			arraylist.add("accountSection.connectedParty.19.connectStatus");
			arraylist.add("accountSection.connectedParty.19.designation");
			arraylist.add("accountSection.connectedParty.19.designationCode");
			arraylist.add("accountSection.connectedParty.19.isExcludeDOBForAppointmentHolder");
			arraylist.add("accountSection.connectedParty.19.mandatoryInBeneficialOwnerGender");
			arraylist.add("accountSection.connectedParty.19.mandatoryInBeneficialOwnerRace");
			arraylist.add("accountSection.connectedParty.2.connectStatus");
			arraylist.add("accountSection.connectedParty.2.designation");
			arraylist.add("accountSection.connectedParty.2.designationCode");
			arraylist.add("accountSection.connectedParty.2.isExcludeDOBForAppointmentHolder");
			arraylist.add("accountSection.connectedParty.2.mandatoryInBeneficialOwnerGender");
			arraylist.add("accountSection.connectedParty.2.mandatoryInBeneficialOwnerRace");
			arraylist.add("accountSection.connectedParty.20.connectStatus");
			arraylist.add("accountSection.connectedParty.20.designation");
			arraylist.add("accountSection.connectedParty.20.designationCode");
			arraylist.add("accountSection.connectedParty.20.isExcludeDOBForAppointmentHolder");
			arraylist.add("accountSection.connectedParty.20.mandatoryInBeneficialOwnerGender");
			arraylist.add("accountSection.connectedParty.20.mandatoryInBeneficialOwnerRace");
			
//			 Collections.sort(arraylist);
//			 
//			 for(String aa:arraylist) {
//					System.out.println(aa);
//			 }
			 
			 sortNumerically(arraylist);
			
//			for(String key : arraylist) {
//				char[] ch = key.toCharArray();
//				StringBuilder strbuild = new StringBuilder();
//				for(char c : ch){
//				if(Character.isDigit(c)){
//				strbuild.append(c);
//				}
//				}
//			}
//			
//			
//			
//			
//			char[] ch = example.toCharArray();
//			StringBuilder strbuild = new StringBuilder();
//			for(char c : ch){
//			if(Character.isDigit(c)){
//			strbuild.append(c);
//			}
//			}
//			System.out.println(strbuild);
			
	}
		
	public String hashCheckPrimaryJSON(String jsonString) throws ParseException, IOException {

		JSONObject primaryJSON = null;
		try {
			primaryJSON = new JSONObject(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		Map<String,Object> mappedJSON = null;
		try {
			mappedJSON = jsonObjtoMap(primaryJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ArrayList<String> arrayList = new ArrayList<>();
		Map<String,Object> sortedMap = new TreeMap<>();
		sortedMap.putAll(mappedJSON);
		
		
		System.out.println("Start sorting --------------");
		
		for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                         ", Value = " + entry.getValue());       
            arrayList.add(entry.getValue().toString());

		}
		System.out.println("End of sortring --------------");


		
		Collections.sort(arrayList);
		for(String temp : arrayList) {
			plainText += !temp.equals("null") ? temp.replace("\n", "")
					.replace("\r", "") : "";
			
		}
		  
	    plainText = plainText.replaceAll("\",\"" , "")
				.replace("\"", "")
				;
		String fileName = "C:\\Users\\Kaung Myat Linn\\Downloads\\Telegram Desktop\\primaryJson.txt";
		String hashedCode = DigestUtils.sha256Hex(plainText);

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.write(plainText + " ================== hashed Code : " + hashedCode);
		} catch (IOException e) {
			e.printStackTrace();
		}

		writer.close();
		
		System.out.println("plainText : "+ plainText);
		
		System.out.println("Hashed code " + hashedCode);
		return hashedCode;
		
	}
	
	public static ArrayList<String> sortNumerically(ArrayList<String> arraylist){
		
		Map<Integer,String> intArr= new HashMap<>();
		ArrayList<Integer> sortedInt = new ArrayList<>();
		int keys[] = {};
		
		for(String key : arraylist) {
		
			String tempo = "";
			char[] ch = key.toCharArray();
			StringBuilder strbuild = new StringBuilder();
			for(char c : ch){
				if(Character.isDigit(c)){
					strbuild.append(c);
				}
			}
			tempo = strbuild.toString();
			intArr.put(Integer.parseInt(tempo), key);
			sortedInt.add(Integer.parseInt(tempo));
			
		}
		System.out.println("Int Arr size" + intArr.size()+ " sorted int size" + sortedInt.size());
		
		
		Map<Integer, String> treeMap = new TreeMap<Integer, String>(intArr);
		Set s = intArr.entrySet();
        Iterator it = s.iterator();
        while (it.hasNext()) {
           Map.Entry entry = (Map.Entry) it.next();
           Integer key = (Integer) entry.getKey();
           String value = (String) entry.getValue();
           System.out.println(key + " => " + value);
        } 
        System.out.println("========================");
    
		
		for(Integer keey : treeMap.keySet()) {
			System.out.println(keey +" => "+ treeMap.get(keey));
			
		}
		
		System.out.println("----------------");

		Collections.sort(sortedInt);
		
		for(Integer keey :sortedInt) {
			System.out.println(keey);
			
		}
			
		return null;
	}
	
	   
	
	
	public static Map<String, Object> jsonObjtoMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);
	        
	         if(value instanceof JSONObject) {
	            value = jsonObjtoMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	


	
		
	
}
