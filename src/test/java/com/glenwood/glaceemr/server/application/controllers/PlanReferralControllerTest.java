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
import com.glenwood.glaceemr.server.application.repositories.ReferralRepository;
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
public class PlanReferralControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	ReferralRepository referralRepository;
	
	private Logger logger = Logger.getLogger(PlanReferralControllerTest.class);
	
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	/*
	 * Test Get Referral list.
	 */
	@Test
	@Ignore
	public void getReferralList() {
		 logger.debug("Getting referral list- Test");		 
		 given().param("encounterId", "6701").param("chartId", "1196").
		 		 when().get("/ReferralSummary.Action/listReferral").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("[{\"h413001\":12171,\"h413002\":1196,\"h413003\":6701,\"h413004\":\"08/01/2015 04:12 AM\",\"h413005\":\"Smith SrDr\",\"h413006\":\"Test test\",\"h413007\":\"Cardiac Surgeon\",\"h413008\":\"test test GU\",\"h413009\":\"4554455554$$\",\"h413010\":\"7257473247\",\"h413011\":\"test\",\"h413012\":\"test\",\"h413013\":\"notes\",\"h413014\":0,\"h413015\":0,\"h413016\":\"test\",\"h413017\":\"08/03/2015 05:22 AM\",\"h413018\":\"0\",\"h413019\":\"test\",\"h413020\":\"test\",\"h413021\":0,\"h413022\":\"08/03/2015 05:22 AM\",\"h413023\":\"test\",\"h413024\":\"0\",\"h413025\":\"test\",\"h413026\":0,\"h413027\":\"test\",\"h413028\":0,\"h413029\":\"08/03/2015 05:22 AM\",\"h413030\":0,\"h413031\":\"08/03/2015 05:22 AM\",\"h413032\":\"49\",\"h413033\":\"test\",\"h413034\":-1,\"h413035\":7352,\"h413036\":-1,\"h413037\":\"knee pain\",\"h413038\":\"$$$2179^^4104^^2482^^SoapNotesPrint.Action$$$2153^^4103^^2482^^SoapNotesPrint.Action$$$2213^^4102^^2482^^SoapNotesPrint.Action\",\"h413039\":\"test\",\"h413040\":-1,\"referreddoctorLoginid\":51,\"h413041\":1,\"referralOrderBy\":49,\"referralOrderOn\":\"08/03/2015 05:22 AM\",\"referralCancelBy\":-1,\"referralCancelOn\":\"08/03/2015 05:22 AM\",\"referralReceiveBy\":-1,\"referralReceiveOn\":\"08/03/2015 05:22 AM\",\"referralReviewedBy\":-1,\"referralReviewedOn\":\"08/03/2015 05:22 AM\",\"referralHospitalname\":\"test\",\"referralGuarantorname\":\"test\",\"referralNoVisits\":2,\"referralExpirationDate\":\"08/03/2015 05:22 AM\",\"referralReferredtoid\":1,\"summaryCareRecordProvided\":true,\"summaryCareRecordProvidedElectronic\":null,\"empprofileTable\":null,\"patientRegistrationTable\":null},{\"h413001\":12172,\"h413002\":1196,\"h413003\":6701,\"h413004\":\"08/03/2015 05:22 AM\",\"h413005\":\"Smith SrDr\",\"h413006\":\"Ref test\",\"h413007\":\"Cardiac Surgeon\",\"h413008\":\"test test GU\",\"h413009\":\"4554455554$$\",\"h413010\":\"7257473247\",\"h413011\":\"test\",\"h413012\":\"test\",\"h413013\":\"notes\",\"h413014\":0,\"h413015\":0,\"h413016\":\"test\",\"h413017\":\"08/03/2015 05:22 AM\",\"h413018\":\"0\",\"h413019\":\"test\",\"h413020\":\"test\",\"h413021\":0,\"h413022\":\"08/03/2015 05:22 AM\",\"h413023\":\"test\",\"h413024\":\"0\",\"h413025\":\"test\",\"h413026\":0,\"h413027\":\"test\",\"h413028\":0,\"h413029\":\"08/03/2015 05:22 AM\",\"h413030\":0,\"h413031\":\"08/03/2015 05:22 AM\",\"h413032\":\"49\",\"h413033\":\"test\",\"h413034\":-1,\"h413035\":7352,\"h413036\":-1,\"h413037\":\"knee pain\",\"h413038\":\"$$$2179^^4104^^2482^^SoapNotesPrint.Action$$$2153^^4103^^2482^^SoapNotesPrint.Action$$$2213^^4102^^2482^^SoapNotesPrint.Action\",\"h413039\":\"test\",\"h413040\":-1,\"referreddoctorLoginid\":51,\"h413041\":1,\"referralOrderBy\":49,\"referralOrderOn\":\"08/03/2015 05:22 AM\",\"referralCancelBy\":-1,\"referralCancelOn\":\"08/03/2015 05:22 AM\",\"referralReceiveBy\":-1,\"referralReceiveOn\":\"08/03/2015 05:22 AM\",\"referralReviewedBy\":-1,\"referralReviewedOn\":\"08/03/2015 05:22 AM\",\"referralHospitalname\":\"test\",\"referralGuarantorname\":\"test\",\"referralNoVisits\":2,\"referralExpirationDate\":\"08/03/2015 05:22 AM\",\"referralReferredtoid\":1,\"summaryCareRecordProvided\":true,\"summaryCareRecordProvidedElectronic\":null,\"empprofileTable\":null,\"patientRegistrationTable\":null}]"));
	}
	

	/*
	 * Test Get Referral data 
	 */
	@Test 
	@Ignore
	public void getReferral() {
		logger.debug("Getting referral details- Test");
		 given().param("referralId", "12172").
		 		 when().get("/ReferralSummary.Action/GetReferral").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("{\"h413001\":12172,\"h413002\":1196,\"h413003\":6701,\"h413004\":\"08/03/2015 05:22 AM\",\"h413005\":\"Smith SrDr\",\"h413006\":\"Ref test\",\"h413007\":\"Cardiac Surgeon\",\"h413008\":\"test test GU\",\"h413009\":\"4554455554$$\",\"h413010\":\"7257473247\",\"h413011\":\"test\",\"h413012\":\"test\",\"h413013\":\"notes\",\"h413014\":0,\"h413015\":0,\"h413016\":\"test\",\"h413017\":\"08/03/2015 05:22 AM\",\"h413018\":\"0\",\"h413019\":\"test\",\"h413020\":\"test\",\"h413021\":0,\"h413022\":\"08/03/2015 05:22 AM\",\"h413023\":\"test\",\"h413024\":\"0\",\"h413025\":\"test\",\"h413026\":0,\"h413027\":\"test\",\"h413028\":0,\"h413029\":\"08/03/2015 05:22 AM\",\"h413030\":0,\"h413031\":\"08/03/2015 05:22 AM\",\"h413032\":\"49\",\"h413033\":\"test\",\"h413034\":-1,\"h413035\":7352,\"h413036\":-1,\"h413037\":\"knee pain\",\"h413038\":\"$$$2179^^4104^^2482^^SoapNotesPrint.Action$$$2153^^4103^^2482^^SoapNotesPrint.Action$$$2213^^4102^^2482^^SoapNotesPrint.Action\",\"h413039\":\"test\",\"h413040\":-1,\"referreddoctorLoginid\":51,\"h413041\":1,\"referralOrderBy\":49,\"referralOrderOn\":\"08/03/2015 05:22 AM\",\"referralCancelBy\":-1,\"referralCancelOn\":\"08/03/2015 05:22 AM\",\"referralReceiveBy\":-1,\"referralReceiveOn\":\"08/03/2015 05:22 AM\",\"referralReviewedBy\":-1,\"referralReviewedOn\":\"08/03/2015 05:22 AM\",\"referralHospitalname\":\"test\",\"referralGuarantorname\":\"test\",\"referralNoVisits\":2,\"referralExpirationDate\":\"08/03/2015 05:22 AM\",\"referralReferredtoid\":1,\"summaryCareRecordProvided\":true,\"summaryCareRecordProvidedElectronic\":null,\"empprofileTable\":null,\"patientRegistrationTable\":null}"));
	}
	

	/*
	 * Test Save Referral data
	 */
	@Test 
	@Ignore
	public void saveReferral()  {
		logger.debug("Saving referral details- Test");
		 given().param("referralId", "12172").param("reason", "knee pain").param("notes", "not able to walk").
		 		 when().get("/ReferralSummary.Action/SaveReferral").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("\"success\""));
	}
	

	/*
	 * Test Cancel Referral
	 */
	@Test 
	@Ignore
	public void cancelReferral() {
		logger.debug("Cancelling referral details- Test");
		 given().param("referralId", "12172").
		 		 when().get("/ReferralSummary.Action/CancelReferral").then().
	             statusCode(HttpServletResponse.SC_OK).contentType(ContentType.JSON).
	             body(equalTo("\"success\""));
	}
	
}
