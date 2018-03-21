package com.glenwood.glaceemr.server.application.controllers;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

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
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("GroupTherapy.xml")
@WebAppConfiguration
public class GroupTherapyControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Get list of place of services
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void getDefaultValuesTest() throws Exception {
		given().when().get("/GroupTherapy.Action/ListDefaultValues").then().
		statusCode(HttpServletResponse.SC_OK);
	}
	/**
	 * To get the list of patients which belongs to the particular Group 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void getListGroupDataTest() throws Exception {
		given().param("groupId","1").when().get("/GroupTherapy.Action/ListGroupData").then().
		statusCode(HttpServletResponse.SC_OK);
	}
	
	
		
}
