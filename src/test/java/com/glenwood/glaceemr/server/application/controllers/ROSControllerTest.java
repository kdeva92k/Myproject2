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
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("ROSTestData.xml")

@WebAppConfiguration
public class ROSControllerTest {
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test 
	@Ignore
	public void getROSBean() throws Exception {
		given(). param("patientId", "3221").param("chartId", "7217").param("encounterId", "14908").param("templateId", "3744").param("clientId", "300").when().get("/ROSElements/ROSElements").then().
        statusCode(HttpServletResponse.SC_OK).
        contentType("application/json").body(equalTo("[{\"systemId\":27,\"systemName\":\"Constitutional\",\"deferredGWID\":\"\",\"roselements\":[{\"elementGWID\":\"0000100427001008000\",\"elementName\":\"Fever\",\"elementPrintText\":\"fever\",\"dataType\":3,\"value\":\"false\"}],\"eandMType\":\"Constitutional\"}]"));
	}
	
	@Test 
	@Ignore
	public void getROSNotes() throws Exception {
		given(). param("patientId", "3221").param("encounterId", "14908").when().get("/ROSElements/ROSNotes").then().
        statusCode(HttpServletResponse.SC_OK).
        contentType("application/json").body(equalTo("\"Data\""));
	}
	
	
}
