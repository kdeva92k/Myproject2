package com.glenwood.glaceemr.server.application.controllers;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("InternalMessageData.xml")
@WebAppConfiguration
public class InternalMessageControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private Logger logger = Logger.getLogger(InternalMessageControllerTest.class);
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	/**
	 * Testing for getting phone encounter details.
	 */
	
	@Test 
	@Ignore
	public void getEncounters() throws Exception {

		logger.debug("Internal message getEncounters testing starts.\n");
		String url="InternalMessages/getEncounters?patientid=7777";

		int statusCode=given().when().get(url).getStatusCode();

		if(statusCode==200){
			given().when().
			get("InternalMessages/getEncounters?patientid=7777").then().
			statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}else if(statusCode==400){
			System.out.println("Bad Request Error>>>"+url);
			logger.debug("Bad Request Error>>>"+url);
		}else if(statusCode==408){
			System.out.println("Time out Error>>>"+url);
			logger.debug("Time out Error>>>"+url);
		}else if(statusCode==204){
			System.out.println("No content Error>>>"+url);
			logger.debug("No content Error>>>"+url);
		}
	}
	/**
	 * Testing for getting encounter details.
	 */
	@Ignore
	@Test 
	public void getInternalEncounterDetails() throws Exception {

		logger.debug("Internal message getInternalEncounterDetails testing starts.\n");
		String url="InternalMessages/getEncounterDetails?encounterid=111";

		int statusCode=given().when().get(url).getStatusCode();

		if(statusCode==200){
		given().
		when().get("InternalMessages/getEncounterDetails?encounterid=111").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}else if(statusCode==400){
			System.out.println("Bad Request Error>>>"+url);
			logger.debug("Bad Request Error>>>"+url);
		}else if(statusCode==408){
			System.out.println("Time out Error>>>"+url);
			logger.debug("Time out Error>>>"+url);
		}else if(statusCode==204){
			System.out.println("No content Error>>>"+url);
			logger.debug("No content Error>>>"+url);
		}
	}
	
	/**
	 * Testing for update Internal encounter .
	 */
	@Test 
	@Ignore
	public void update() throws Exception {

		logger.debug("update Internal encounter testing starts.\n");
		given().
		when().get("InternalMessages/update?encounterid=111&patientid=7777&chartid=123&userid=12&servicedoctor=12&message=test_msg&status=2&encountertype=1&severity=3").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}
	
	
}
