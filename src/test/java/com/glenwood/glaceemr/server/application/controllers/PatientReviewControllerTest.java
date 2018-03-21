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
@DatabaseSetup("PatientReviewData.xml")
@WebAppConfiguration
public class PatientReviewControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private Logger logger = Logger.getLogger(PatientReviewControllerTest.class);
	
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	/*
	 * Test Fetching reviewed information
	 */
	@Test
	@Ignore
	public void fetchReviewedInfo() {
		logger.debug("testing for fetching reviewed information\n");

		int statusCode=given().param("chartId", "1344").
				when().post("PatientReview/getReviewInfo").getStatusCode();

		if(statusCode==200){
			given().param("chartId", "1344").when().post("PatientReview/getReviewInfo").then().
				statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}else if(statusCode==400){
			logger.debug("Bad Request Error while fetching reviewed information");
		}else if(statusCode==408){
			logger.debug("Time out Error while fetching reviewed information");
		}else if(statusCode==204){
			logger.debug("No content Error while fetching reviewed information");
		}
				
	}

	/*
	 * Test Saving reviewed information
	 */
	@Test
	@Ignore
	public void saveReviewedInfo() {
		logger.debug("testing for saving reviewed information\n");

		int statusCode=given().param("chartId", "1344").
				when().post("PatientReview/saveReviewInfo").getStatusCode();

		if(statusCode==200){
			given().param("chartId", "1344").param("userId","1").param("patientId","7497").param("encounterId","8806").
			when().post("PatientReview/saveReviewInfo").then().
				statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}else if(statusCode==400){
			logger.debug("Bad Request Error while saving reviewed information");
		}else if(statusCode==408){
			logger.debug("Time out Error while saving reviewed information");
		}else if(statusCode==204){
			logger.debug("No content Error while saving reviewed information");
		}
				
	}
	
}
