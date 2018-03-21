package com.glenwood.glaceemr.server.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataTransferObject {

	/**
	 * @param test :List of the returned objects from the query
	 * @param requiredCols :List of the required values
	 * @return : will return a DataObject 
	 * @throws Exception
	 * 
	 * This method will return the required property list of object from by taking the list of entity returned 
	 * and list of required properties.
	 */
	public static  List<Map<String, Object>> getRequiredProperties(List<Object> test,String[] requiredCols)throws Exception
	{

		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<test.size();i++){
			Map<String,Object> newMap=  new HashMap<String,Object>();
			for(int j=0; j<requiredCols.length;j++){

				Method method = test.get(i).getClass().getDeclaredMethod(requiredCols[j]);
				newMap.put(requiredCols[j].replaceFirst("get", ""), method.invoke(test.get(i)));
			}
			resultList.add(newMap);
		}  
		return resultList;
	}
	
}
