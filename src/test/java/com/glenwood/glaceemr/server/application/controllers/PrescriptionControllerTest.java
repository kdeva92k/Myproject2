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
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })


@WebAppConfiguration
public class PrescriptionControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	private Logger logger = Logger.getLogger(UserGroupControllerTest.class);
	
	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test 
	@Ignore
	public void getactivemdsupplies(){
		logger.debug("Getting data for medical supplies for the selected patient");
		given().
		when().get("Prescription.Action/patientid").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		logger.debug("Success of getting data for medical supplies for the selected patient");
	}

}
