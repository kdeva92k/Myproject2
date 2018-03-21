package com.glenwood.glaceemr.server.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface GlaceRepository extends Serializable  {


	/**
	 * @param requiredProperties : List of the Properties which we are excepting from the returned list of the query returning
	 * @param listofReturnedObjects :List of objects returned by the query
	 * @return objects along with required property names assigned
	 * This method return the List of Maps which act as a Data Transfer Object
	 */
	public List<Map<String, Object>> CreateCustomQueryDTO(List<String> requiredProperties,List<Object> listofReturnedObjects);	

	/**
	 * @param listofReturnedObjects:List of objects returned by the query
	 * @return size of list nothing but the count of objects returned by the query
	 * This method returns the count of the returned list by the Query
	 */
	public long getQueryCount(List<Object> listofReturnedObjects);

	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public List<Map<String, Object>> CustomQuery(List<String> Listofprop,String Query);

	public List<Map<String, Object>> CustomQuery(List<String> Listofprop,String Query,int limit,int offset);

	public long CustomQueryCount(String Query) ;




}