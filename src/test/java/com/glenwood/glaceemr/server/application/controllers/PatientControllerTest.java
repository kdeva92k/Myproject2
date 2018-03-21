package com.glenwood.glaceemr.server.application.controllers;


import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.equalTo;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
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

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
//@DbUnitConfiguration(dataSetLoader = ColumnSensingReplacementDataSetLoader.class)
@DatabaseSetup("testCaseData.xml")
@WebAppConfiguration
public class PatientControllerTest {

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
	public void findByLastNameTest() throws Exception {

        given().
        param("lastName", "Smith").when().get("/PatientController.Action/ByLastName").then().
                statusCode(HttpServletResponse.SC_OK).
                contentType("application/json").
                body(equalTo("[{\"patientId\":1,\"patientLName\":\"Smith\",\"patientFName\":\"Theodre\",\"patientGender\":0,\"patientDob\":\"12/01/1956 00:00 AM EST\",\"isActive\":true,\"encounterTable\":null,\"patientInsuranceTable\":null,\"pAddressTable\":null},{\"patientId\":2,\"patientLName\":\"Smith\",\"patientFName\":\"Joe\",\"patientGender\":0,\"patientDob\":\"09/03/2006 00:00 AM EDT\",\"isActive\":true,\"encounterTable\":null,\"patientInsuranceTable\":null,\"pAddressTable\":null},{\"patientId\":4,\"patientLName\":\"Smith\",\"patientFName\":\"test1\",\"patientGender\":1,\"patientDob\":\"04/12/1980 00:00 AM EST\",\"isActive\":true,\"encounterTable\":null,\"patientInsuranceTable\":null,\"pAddressTable\":null},{\"patientId\":7,\"patientLName\":\"Smith\",\"patientFName\":\"Kishore\",\"patientGender\":0,\"patientDob\":\"03/26/2015 00:00 AM EDT\",\"isActive\":true,\"encounterTable\":null,\"patientInsuranceTable\":null,\"pAddressTable\":null}]"))
               .body ("size()", equalTo (4))
                .body ("[1].patientId",equalTo (2)).
                body("patientId", hasSize(4));
	}
	
	@Test 
	@Ignore
	public void getPatientsBylastNameAndDobTest() throws Exception {
		given(). param("lastName", "Smith").param("dob", "1980-04-12").when().get("/PatientController.Action/ByLastNameAndDob").then().
        statusCode(HttpServletResponse.SC_OK).
        contentType("application/json").body(equalTo("[{\"patientId\":4,\"patientLName\":\"Smith\",\"patientFName\":\"test1\",\"patientGender\":1,\"patientDob\":\"04/12/1980 00:00 AM EST\",\"isActive\":true,\"encounterTable\":null,\"patientInsuranceTable\":null,\"pAddressTable\":null}]"));
	}
	
	@Test
	@Ignore
	public void updateByPatientTest() throws Exception {
		given(). param("patient","{\"patientId\":4,\"patientLName\":\"Smith\",\"patientFName\":\"updated\",\"patientGender\":1,\"patientDob\":\"1980-04-12\",\"isActive\":true,\"encounterTable\":null,\"patientInsuranceTable\":null,\"pAddressTable\":null}").when()
		.post("/PatientController.Action/updateByPatient").then().
        statusCode(HttpServletResponse.SC_OK);
	}
}
