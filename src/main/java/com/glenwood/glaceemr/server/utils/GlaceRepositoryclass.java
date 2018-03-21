package com.glenwood.glaceemr.server.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;


@Repository
public class GlaceRepositoryclass implements Serializable, GlaceRepository {

	
	@Autowired
	EntityManagerFactory emf ;

	
	/**
	 * @param requiredProperties : List of the Properties which we are excepting from the returned list of the query returning
	 * @param listofReturnedObjects :List of objects returned by the query
	 * @return objects along with required property names assigned
	 * This method return the List of Maps which act as a Data Transfer Object
	 */
	public List<Map<String, Object>> CreateCustomQueryDTO(List<String> requiredProperties,List<Object> listofReturnedObjects) 
	{
		EntityManager em = emf.createEntityManager();
		try{
			Iterator iter= listofReturnedObjects.iterator();
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			while ( iter.hasNext() ) {
				Object[] row = (Object[]) iter.next();
				
				Map<String,Object> ReturningList=  new HashMap<String,Object>();
				
					for(int i=0;i<requiredProperties.size();i++){
						ReturningList.put(requiredProperties.get(i),(Object)row[i]);
				}
				resultList.add(ReturningList);

			}
				return resultList;
		}catch(Exception e)
		{
			
			e.printStackTrace();
			return null;
		}finally{
			em.close();
		}
		
		
	}
	
	
	
	/**
	 * @param listofReturnedObjects:List of objects returned by the query
	 * @return size of list nothing but the count of objects returned by the query
	 * This method returns the count of the returned list by the Query
	 */
	public long getQueryCount(List<Object> listofReturnedObjects) 
	{
		try{
			long count= listofReturnedObjects.size();
			
				return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}finally
		{
		}
		
	}
	/**
	 * @Query : Takes query as input and returns the count of the objects
	 * @return size of list nothing but the count of objects returned by the query
	 * This method returns the count of the returned list by the Query
	 */
	public long getQueryCount(String Query) 
	{
		EntityManager em = emf.createEntityManager();
		try{
			String query =Query;
			
			long count= em.createQuery(query.toString()).getResultList().size();
			
				return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}finally
		{
			em.close();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/* Method to execute the custom queries */
	public List<Map<String, Object>> CustomQuery(List<String> listinorder,String Query) 
	{
		try{
			String query =Query;
			Map<String, Class<?>> propertie =new HashMap<String, Class<?>>();

			EntityManager em = emf.createEntityManager();
			Iterator iter= em.createQuery(query).getResultList().iterator();
			
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			while ( iter.hasNext() ) {
				Object[] row = (Object[]) iter.next();
				Map<String,Object> newMap=  new HashMap<String,Object>();
				
					for(int i=0;i<listinorder.size();i++){
					newMap.put(listinorder.get(i),(Object)row[i]);
				}
				resultList.add(newMap);

			}
				return resultList;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Map<String, Object>> CustomQuery(List<String> listinorder,String Query,int limit,int offset) 
	{
		EntityManager em = emf.createEntityManager();
		try{
			String query =Query;
			Map<String, Class<?>> propertie =new HashMap<String, Class<?>>();

			
			Iterator iter= em.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList().iterator();
			
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			while ( iter.hasNext() ) {
				Object[] row = (Object[]) iter.next();
				
				Map<String,Object> newMap=  new HashMap<String,Object>();
				
					for(int i=0;i<listinorder.size();i++){
					newMap.put(listinorder.get(i),(Object)row[i]);
				}
				resultList.add(newMap);

			}
				return resultList;
		}catch(Exception e)
		{
			
			e.printStackTrace();
			return null;
		}finally{
			em.close();
		}
		
		
	}
	public long CustomQueryCount(String Query) 
	{
		EntityManager em = emf.createEntityManager();
		try{
			String query =Query;
			
			long count= em.createQuery(query.toString()).getResultList().size();
			
				return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}finally
		{
			em.close();
		}
		
	}
	
	
	public static boolean throwException(String... values)
	{
		for (String string : values) {
			Assert.notNull(string.trim(), "The given String not be null!");
			Assert.hasLength(string.trim());
		}

		return true;
	}


	public static boolean isEmpty(String values)
	{
		if(values.trim().equalsIgnoreCase(""))
			return true;
		else
			return false;

	}

	public String firstLetterCaps(String firstlettercaps)
	{

		char[] stringArray = firstlettercaps.trim().toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		String str = new String(stringArray);
		return str;

	}

}