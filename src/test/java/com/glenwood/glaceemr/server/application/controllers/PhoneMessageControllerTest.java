package com.glenwood.glaceemr.server.application.controllers;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

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
@DatabaseSetup("PhoneMessageData.xml")
@WebAppConfiguration
public class PhoneMessageControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private Logger logger = Logger.getLogger(PhoneMessageControllerTest.class);

	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	/**
	 * Testing for getting phone encounter details.
	 */
	@Ignore
	@Test 
	public void getPhoneEncounters() throws Exception {

		logger.debug("phone message getPhoneEncounters testing starts.\n");
		String url="PhoneMessages/getPhoneEncounters?patientid=7777&startdate=11/3/2015&enddate=11/4/2015";

		int statusCode=given().when().get(url).getStatusCode();

		if(statusCode==200){
			given().when().
			get("PhoneMessages/getPhoneEncounters?patientid=7777&startdate=11/3/2015&enddate=11/4/2015").then().
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
	public void getEncounterDetails() throws Exception {

		logger.debug("phone message getEncounters testing starts.\n");
		String url="PhoneMessages/getEncounterDetails?encounterid=111";

		int statusCode=given().when().get(url).getStatusCode();

		if(statusCode==200){
		given().
		when().get("PhoneMessages/getEncounterDetails?encounterid=111").then().
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
	 * Testing for sendreply phone encounter .
	 */
	@Test 
	@Ignore
	public void sendreply() throws Exception {

		logger.debug("sendreply phone encounter testing starts.\n");
		given().
		when().get("PhoneMessages/sendreply?encounterid=111&replymsg=test_msg&status=2&modifiedby=12&severity=1").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}
	/**
	 * Testing for update phone encounter .
	 */
	@Test 
	@Ignore
	public void update() throws Exception {

		logger.debug("update phone encounter testing starts.\n");
		given().
		when().get("PhoneMessages/update?encounterid=111&response=test_msg&status=2&modifiedby=12&servicedr=12").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}
	
	
}
