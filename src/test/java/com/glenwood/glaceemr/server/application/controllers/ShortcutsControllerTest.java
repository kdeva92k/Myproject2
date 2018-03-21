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
import com.glenwood.glaceemr.server.application.repositories.ReferralRepository;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("ShortcutsData.xml")
@WebAppConfiguration
public class ShortcutsControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	ReferralRepository referralRepository;
	
	private Logger logger = Logger.getLogger(ShortcutsControllerTest.class);
	
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	/*
	 * Test Adding shortcut
	 */
	@Test
	@Ignore
	public void addShortcut() {
		logger.debug("testing for adding shortcuts started\n");

		int statusCode=given().param("tabId", "2511").param("elementId", "element_0000100301100002002_text").param("data", "maculopaplular rash").
				when().post("Shortcuts.Action/AddShortcut").getStatusCode();

		if(statusCode==200){
			given().param("tabId", "2511").param("elementId", "element_0000100301100002002_text").param("data", "maculopaplular rash").
			when().post("Shortcuts.Action/AddShortcut").then().
			statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}else if(statusCode==400){
			logger.debug("Bad Request Error while adding shortcut");
		}else if(statusCode==408){
			logger.debug("Time out Error while adding shortcut");
		}else if(statusCode==204){
			logger.debug("No content Error while adding shortcut");
		}
				
	}
	

	/*
	 * Test Deleting shortcut
	 */
	@Test
	@Ignore
	public void deleteShortcut() {
		logger.debug("testing for delete shortcut started\n");

		int statusCode=given().param("tabId", "2511").param("elementId", "element_0000100301100002002_text").param("shortcutId", "2").
				when().post("Shortcuts.Action/deleteShortcut").getStatusCode();

		if(statusCode==200){
			given().param("tabId", "2511").param("elementId", "element_0000100301100002002_text").param("shortcutId", "2").
			when().post("Shortcuts.Action/deleteShortcut").then().
			statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}else if(statusCode==400){
			logger.debug("Bad Request Error while deleting shortcut");
		}else if(statusCode==408){
			logger.debug("Time out Error while deleting shortcut");
		}else if(statusCode==204){
			logger.debug("No content Error while deleting shortcut");
		}
				
	}
	
	
	
}
