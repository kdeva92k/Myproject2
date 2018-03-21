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
import com.glenwood.glaceemr.server.application.repositories.AssessmentRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationTestContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DatabaseSetup("ReferralData.xml")
@WebAppConfiguration
public class AssessmentControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	ProblemListRepository problemListRepository;
	
	@Autowired 
	AssessmentRepository assessmentRepository;
	
	private Logger logger = Logger.getLogger(AssessmentControllerTest.class);
	
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	/*
	 * Test case for current problems
	 */
	@Test
	@Ignore
	public void getCurrentDiagnosis() {
		 logger.debug("Fetching current problems- Test");
		
		 given().param("patientId", "6806").param("encounterId","8431").
		 		 when().get("/Assessment.Action/CurrentDiagnosis").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("[{\"h611001\":87,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"053.9\",\"h611006\":\"HERPES ZOSTER WITHOUT MENTION OF COMPLICATION\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":0,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null},{\"h611001\":88,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"075\",\"h611006\":\"INFECTIOUS MONONUCLEOSIS\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":1,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null},{\"h611001\":89,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"038.9\",\"h611006\":\"UNSPECIFIED SEPTICEMIA\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":2,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null},{\"h611001\":90,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"110.1\",\"h611006\":\"DERMATOPHYTOSIS OF NAIL\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":3,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null},{\"h611001\":91,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"054.9\",\"h611006\":\"HERPES SIMPLEX WITHOUT MENTION OF COMPLICATION\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":4,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null},{\"h611001\":92,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"041.86\",\"h611006\":\"HELICOBACTER PYLORI [H. PYLORI] INFECTION\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":5,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null}]"));
	}
	
	/*
	 * Test case for active problems
	 */
	@Test
	@Ignore
	public void activeProblems() {
		 logger.debug("Fetching active problems- Test");
		
		 given().param("patientId", "7472").
		 		 when().get("/Assessment.Action/ActiveProblems").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("[{\"problemListId\":2322,\"problemListPatientId\":7472,\"problemListLastModOn\":\"04/28/2016 19:30 PM IST\",\"problemListDxCode\":\"789.00\",\"problemListIsactive\":true,\"h555555\":null,\"problemListOnsetDate\":null,\"problemListCreatedby\":1,\"problemListCreatedon\":\"04/28/2016 19:30 PM IST\",\"problemListUnknown2\":null,\"problemListUnknown1\":null,\"problemListIschronic\":null,\"problemListResolvedDate\":null,\"problemListResolvedBy\":null,\"problemListDxDescp\":\"ABDOMINAL PAIN, UNSPECIFIED SITE\",\"h063015\":-1,\"problemListChronicity\":-1,\"problemListIsresolved\":false,\"problemListComments\":\"\",\"problemListInactiveDate\":null,\"problemListInactivatedBy\":null,\"problemListCodingSystem\":\"2.16.840.1.113883.6.104\"},{\"problemListId\":2333,\"problemListPatientId\":7472,\"problemListLastModOn\":\"05/04/2016 20:15 PM IST\",\"problemListDxCode\":\"A00.0\",\"problemListIsactive\":true,\"h555555\":null,\"problemListOnsetDate\":null,\"problemListCreatedby\":1,\"problemListCreatedon\":\"05/04/2016 20:14 PM IST\",\"problemListUnknown2\":null,\"problemListUnknown1\":null,\"problemListIschronic\":null,\"problemListResolvedDate\":null,\"problemListResolvedBy\":null,\"problemListDxDescp\":\"Cholera due to Vibrio cholerae 01, biovar cholerae\",\"h063015\":-1,\"problemListChronicity\":-1,\"problemListIsresolved\":false,\"problemListComments\":\"A00.0 notes testing\",\"problemListInactiveDate\":null,\"problemListInactivatedBy\":null,\"problemListCodingSystem\":\"2.16.840.1.113883.6.90\"},{\"problemListId\":2331,\"problemListPatientId\":7472,\"problemListLastModOn\":\"05/11/2016 08:03 AM IST\",\"problemListDxCode\":\"O33.1\",\"problemListIsactive\":true,\"h555555\":null,\"problemListOnsetDate\":\"2016-05-10\",\"problemListCreatedby\":1,\"problemListCreatedon\":\"05/04/2016 20:02 PM IST\",\"problemListUnknown2\":null,\"problemListUnknown1\":null,\"problemListIschronic\":null,\"problemListResolvedDate\":null,\"problemListResolvedBy\":null,\"problemListDxDescp\":\"Maternal care for disproportion due to generally contracted pelvis\",\"h063015\":-1,\"problemListChronicity\":-1,\"problemListIsresolved\":false,\"problemListComments\":\"O33.1 dx comments\",\"problemListInactiveDate\":null,\"problemListInactivatedBy\":null,\"problemListCodingSystem\":\"2.16.840.1.113883.6.90\"}]"));
	}
	
	/*
	 * Test case to fetch edit page data
	 */
	@Test
	@Ignore
	public void loadEditData() {
		 logger.debug("Fetching active problems- Test");
		
		 given().param("patientId", "6806").param("encounterId","8431").param("dxCode", "053.9").param("problemId", "87").
		 		 when().get("/Assessment.Action/EditFetch").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("[{\"h611001\":87,\"h611002\":8431,\"h611003\":6806,\"h611004\":\"03/21/2016 00:00 AM IST\",\"h611005\":\"053.9\",\"h611006\":\"HERPES ZOSTER WITHOUT MENTION OF COMPLICATION\",\"h611007\":\"\",\"h611008\":false,\"h555555\":1,\"h611009\":0,\"h611010\":0,\"h611011\":\"05/13/2016 10:14 AM IST\",\"h611012\":1,\"h611013\":\"05/13/2016 10:17 AM IST\",\"h611014\":1,\"h611015\":\"\",\"h611016\":-2,\"h611CodingSystemid\":\"2.16.840.1.113883.6.104\",\"assessmentDxcodesystem\":null,\"planNotes\":null}]"));
	}
}
