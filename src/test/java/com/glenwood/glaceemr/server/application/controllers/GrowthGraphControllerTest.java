package com.glenwood.glaceemr.server.application.controllers;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.servlet.http.HttpServletResponse;

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
@DatabaseSetup("GrowthGraphData.xml")
@WebAppConfiguration
public class GrowthGraphControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/*
	 * Testing get default graph id.
	 */
	@Test 
	@Ignore
	public void getDefaultGraphId() {
		 
		 given().
	        param("patientid", "12").when().get("/GrowthGraph/getdefaultgraphid").then().
	                statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).body(equalTo("\"1\""));
	}
	
	/*
	 * Testing get PatientInfo.
	 */
	@Test 
	@Ignore
	public void getPatientInfo() {
		 
		 given().
	        param("patientid", "12").when().get("/GrowthGraph/getpatientinfo").then().
	                statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}
	
	/*
	 * Testing get graphlist.
	 */
	@Test 
	@Ignore
	public void getGraphList() {
		 
		 given().
	        param("patientid", "12").when().get("/GrowthGraph/getgraphlist").then().
	                statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}
	
}
