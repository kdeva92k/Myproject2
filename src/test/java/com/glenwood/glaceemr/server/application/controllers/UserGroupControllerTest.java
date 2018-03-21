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
import com.glenwood.glaceemr.server.application.repositories.UserGroupRepository;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("testCaseData.xml")
@WebAppConfiguration
public class UserGroupControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	UserGroupRepository UserGroupRepository;

	private Logger logger = Logger.getLogger(UserGroupControllerTest.class);

	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}


	/**
	 * Testing for getting UserGroup details.
	 */
	@Test 
	@Ignore
	public void getUserGroupDetails() throws Exception {
		long UserGroupCount = UserGroupRepository.count();
		logger.debug("User Group getUserGroupDetails testing starts.\nUserGroupCount count is "+UserGroupCount);
		given().
		when().get("UserGroup.Action/getusergroup").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
		body("size", equalTo(4)).
		body("[0].groupname", equalTo("test group")).
		body("[3].userid", equalTo(22)).
		body("[2].username", equalTo("nurse"));
	}


	/**
	 * Testing for getting UserGroup details By GroupId.
	 */
	@Test 
	@Ignore
	public void getUserGroupDetailsByGroupId() throws Exception {
		long UserGroupCount = UserGroupRepository.count();
		logger.debug("User Group getUserGroupDetailsByGroupId testing starts.\nUserGroupCount count is "+UserGroupCount);
		given().param("groupid","1").
		when().get("UserGroup.Action/getusergroupbyid").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
		body("[0].groupname", equalTo("test tmp")).
		body("[0].userid", equalTo(21)).
		body("[1].groupname", equalTo("test tmp")).
		body("[1].username", equalTo("demo nurse"));
	}


	/**
	 * Testing for create UserGroup
	 */
	@Test 
	@Ignore
	public void createUserGroup() throws Exception {
		long UserGroupCount = UserGroupRepository.count();
		logger.debug("User Group createUserGroup testing starts.\nUserGroupCount count is "+UserGroupCount);
		given().param("groupname","test jk").param("userid","45,42").param("username","jeyanth~~kumar").
		when().get("UserGroup.Action/create").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}


	/**
	 * Testing for update UserGroup details
	 */
	@Test 
	@Ignore
	public void updateUserGroup() throws Exception {
		long UserGroupCount = UserGroupRepository.count();
		logger.debug("User Group updateUserGroup testing starts.\nUserGroupCount count is "+UserGroupCount);
		given().param("groupid","1").param("groupname","test tmp").param("userid","31,32").param("username","nurse roja~~test nurse").
		when().get("UserGroup.Action/update").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
		}


	/**
	 * Testing for delete UserGroup details
	 */
	@Test 
	@Ignore
	public void deleteUserGroup() throws Exception {
		long UserGroupCount = UserGroupRepository.count();
		logger.debug("User Group deleteUserGroup testing starts.\nUserGroupCount count is "+UserGroupCount);
		given().param("groupid","1").
		when().get("UserGroup.Action/delete").then().
		statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON);
	}
}
