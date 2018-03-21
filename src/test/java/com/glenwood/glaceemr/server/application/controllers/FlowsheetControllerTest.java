package com.glenwood.glaceemr.server.application.controllers;


import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.equalTo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("FlowsheetData.xml")
@WebAppConfiguration
public class FlowsheetControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/*
	 * Using rest Assured
	 */
	@Test 
	@Ignore
	public void getFlowsheetsTest() throws Exception {
        given().
        param("flowsheetType", 1).when().get("/Flowsheet/Flowsheets").then().
                statusCode(HttpServletResponse.SC_OK).
                contentType("application/json").
                body(equalTo("[{\"flowsheetId\":56,\"flowsheetName\":\"Annual Wellness Visit\",\"flowsheetType\":1,\"flowsheetIsactive\":true},{\"flowsheetId\":57,\"flowsheetName\":\"Coumadin/PT/INR\",\"flowsheetType\":1,\"flowsheetIsactive\":true}]"))
               .body ("size()", equalTo (2))
               .body ("[0].flowsheetType",equalTo (1))
               .body ("[1].flowsheetType",equalTo (1));
	}
}
