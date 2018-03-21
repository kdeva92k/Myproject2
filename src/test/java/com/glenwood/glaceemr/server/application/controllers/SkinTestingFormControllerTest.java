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
@DatabaseSetup("SkinTestData.xml")
@WebAppConfiguration
public class SkinTestingFormControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
		 RestAssuredMockMvc.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/*
	 * Using rest Assured
	 */
	@Ignore
	@Test 
	public void loadAllergensTest() throws Exception {
		given().when().get("/SkinTestingForms/loadAllergens").
		then().statusCode(HttpServletResponse.SC_OK);
	}

	@Ignore
	@Test
	public void getSkinTestSheetsTest() {
		given().when().get("SkinTestingForms/getSkinTestSheets").
		then().statusCode(HttpServletResponse.SC_OK);
	}
	
	@Ignore
	@Test
	public void createSkinTestSheetTest() throws Exception {
		String otherDetails = "{\"ieValue\":true,\"peValue\":true,\"ppValue\":true,\"ipValue\":true,\"iwValue\":true,\"scoringNotes\":\"\",\"ifValue\":true,\"notes\":\"\",\"pfValue\":true,\"pwValue\":true}";
		String data = "[{\"1\":[{\"2\":2},{\"3\":3},{\"4\":4}]}]";
		String categoryOrder = "[{\"1\":1}]";
		given().param("otherDetails",otherDetails).param("sheetName", "UnitTestingForm").param("data", data).param("categoryOrder", categoryOrder).param("loginId", "1").when().get("/SkinTestingForms.Action/createSkinTestSheet").then().statusCode(HttpServletResponse.SC_OK);
		
	}
	
	@Ignore
	@Test
	public void getAllTechniciansTest() throws Exception {
		given().when().get("SkinTestingForms/getAllUsers").
		then().statusCode(HttpServletResponse.SC_OK);
	}
	
	@Ignore
	@Test
	public void getAllPosTest() throws Exception {
		given().when().get("SkinTestingForms/getAllPos").
		then().statusCode(HttpServletResponse.SC_OK);
	}
	
	/*@Test
	public void saveSkinTestOrderTest() throws Exception {
		String dataToSave = "{\"loginId\":\"1\",\"isOrderEntry\":false,\"patientId\":6600,\"skinTestShortcutId\":113,\"status\":1,\"startDate\":\"05/10/2016\",\"technician\":\"1\",\"prickFlag\":true,\"intradermalFlag\":false,\"concentrationList\":[],\"testDetailId\":-1,\"dxCode1\":\"J30.1\",\"dxDesc1\":\"Allergic rhinitis due to pollen\",\"dxCode2\":null,\"dxDesc2\":null,\"dxCode3\":null,\"dxDesc3\":null,\"dxCode4\":null,\"dxDesc4\":null,\"dxCode5\":null,\"dxDesc5\":null,\"dxCode6\":null,\"dxDesc6\":null,\"dxCode7\":null,\"dxDesc7\":null,\"dxCode8\":null,\"dxDesc8\":null,\"dxCode9\":null,\"dxDesc9\":null,\"dxCode10\":null,\"dxDesc10\":null,\"dxCode11\":null,\"dxDesc11\":null,\"dxCode12\":null,\"dxDesc12\":null,\"dxCodeSystem\":\"2.16.840.1.113883.6.90\",\"serviceDoctor\":\"1\",\"pos\":\"2\",\"orderNotes\":\"test\"}";
		given().param("skinTestOrderSaveJSON",dataToSave).contentType("application/json").when().post("/SkinTestingForms.Action/saveSkinTestOrder").then().statusCode(HttpServletResponse.SC_OK);
	}*/
	
	@Ignore
	@Test
	public void getSkinTestOrdersTest() throws Exception {
		given().param("patientId","7647").when().get("/SkinTestingForms.Action/getSkinTestOrders").then().
		statusCode(HttpServletResponse.SC_OK).
		body(equalTo("{\"login\":null,\"success\":null,\"isAuthorizationPresent\":null,\"canUserAccess\":null,\"data\":[{\"skinTestOrderId\":100,\"skinTestOrderPatientId\":7647,\"skinTestOrderSkinTestFormShortcutId\":100,\"skinTestOrderStartDate\":\"05/04/2016\",\"skinTestOrderStatus\":1,\"skinTestOrderNotes\":null,\"skinTestOrderTechnician\":1,\"skinTestOrderEncounterId\":-1,\"skinTestOrderDxCode1\":\"J30.1\",\"skinTestOrderDxDesc1\":\"Allergic rhinitis due to pollen\",\"skinTestOrderDxCode2\":null,\"skinTestOrderDxDesc2\":null,\"skinTestOrderDxCode3\":null,\"skinTestOrderDxDesc3\":null,\"skinTestOrderDxCode4\":null,\"skinTestOrderDxDesc4\":null,\"skinTestOrderDxCode5\":null,\"skinTestOrderDxDesc5\":null,\"skinTestOrderDxCode6\":null,\"skinTestOrderDxDesc6\":null,\"skinTestOrderDxCode7\":null,\"skinTestOrderDxDesc7\":null,\"skinTestOrderDxCode8\":null,\"skinTestOrderDxDesc8\":null,\"skinTestOrderDxCode9\":null,\"skinTestOrderDxDesc9\":null,\"skinTestOrderDxCode10\":null,\"skinTestOrderDxDesc10\":null,\"skinTestOrderDxCode11\":null,\"skinTestOrderDxDesc11\":null,\"skinTestOrderDxCode12\":null,\"skinTestOrderDxDesc12\":null,\"skinTestOrderDxCodeSystemId\":\"2.16.840.1.113883.6.90\",\"skinTestOrderMappingTestDetailId\":-1,\"skinTestOrderOrderingPhysician\":1,\"skinTestOrderOrderingLocation\":1,\"skinTestOrderCreatedBy\":1,\"skinTestOrderCreatedOn\":\"05/09/2016 22:26 PM IST\",\"skinTestOrderLastModifiedBy\":1,\"skinTestOrderLastModifiedOn\":\"05/09/2016 22:26 PM IST\",\"skinTestOrderCompletedBy\":1,\"skinTestOrderCompletedOn\":\"05/10/2016 22:26 PM IST\",\"skinTestOrderReviewedBy\":1,\"skinTestOrderReviewedOn\":\"05/11/2016 22:26 PM IST\",\"skinTestOrderEvaluationText\":\"test evaluation\",\"skinTestOrderResultComments\":\"test result comments\",\"orderStatus\":{\"skinTestOrderStatusId\":1,\"skinTestOrderStatusName\":\"Pending\",\"skinTestOrderStatusDesc\":\"Pending\"},\"skinTestOrderEntries\":null,\"skinTestFormShortcut\":{\"skinTestFormShortcutId\":100,\"skinTestFormShortcutName\":\"Aero Allergen Skin Testing Form\",\"skinTestFormShortcutPrickWhealNeeded\":true,\"skinTestFormShortcutPrickFlareNeeded\":true,\"skinTestFormShortcutPrickErythemaNeeded\":true,\"skinTestFormShortcutPrickPseudopodiaNeeded\":true,\"skinTestFormShortcutIntradermalWhealNeeded\":true,\"skinTestFormShortcutIntradermalFlareNeeded\":true,\"skinTestFormShortcutIntradermalErythemaNeeded\":true,\"skinTestFormShortcutIntradermalPseudopodiaNeeded\":true,\"skinTestFormShortcutScoringNotes\":null,\"skinTestFormShortcutNotes\":null,\"skinTestFormShortcutCreatedBy\":null,\"skinTestFormShortcutCreatedOn\":null,\"skinTestFormShortcutLastModifiedBy\":null,\"skinTestFormShortcutLastModifiedOn\":null,\"skinTestFormShortcutCategoryDetails\":null},\"technician\":{\"empProfileEmpid\":1,\"encounterServiceDr\":null},\"orderingPhysician\":{\"empProfileEmpid\":1,\"encounterServiceDr\":null},\"reviewedBy\":{\"empProfileEmpid\":1,\"encounterServiceDr\":null},\"completedBy\":{\"empProfileEmpid\":1,\"encounterServiceDr\":null}}]}"));
	}
	
}
