package com.glenwood.glaceemr.server.application.services.chart.print;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Text Case Converter & Name Formatter
 * @author chandrahas
 *
 */
@Component
public class TextFormatter {

	/**
	 * Convert to lower case
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 * Example : input - HeLLo 
	 * 			 result- hello
	 */
	public String getLowerText(String inputString) throws Exception {

		if (inputString != null)
			return inputString.toLowerCase();
		else
			return "";
	}

	/**
	 * Convert to Upper Case
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 * Example : input - HeLLo 
	 *           result - HELLO
	 */
	public String getUpperText(String inputString) throws Exception {

		if (inputString != null)
			return inputString.toUpperCase();
		else
			return "";
	}

	/**
	 * Convert to First letter UpperCase
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 *             Example : input - HeLLo result- Hello
	 */
	public String getFirstLetterCapsText(String inputString) throws Exception {

		if (inputString != null && inputString.length() > 0) {
			return inputString.substring(0, 1).toUpperCase()
					+ inputString.substring(1).toLowerCase();
		}
		return "";

	}

	/**
	 * Convert to First letter LowerCase
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 * Example : input - HeLLo 
	 *           result- hELLO
	 */
	public String getFirstLetterCapsToggleText(String inputString)
			throws Exception {

		if (inputString != null && inputString.length() > 0) {
			return inputString.substring(0, 1).toLowerCase()
					+ inputString.substring(1).toUpperCase();
		}
		return "";

	}

	/**
	 * Convert to Toggle Case
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 * Example : input  - HeLLo java. hello WORlD. 
	 * 			 result - hEllO JAVA. HELLO worLd.
	 */
	public String getToggleCaseText(String inputString) throws Exception {

		StringBuffer result = new StringBuffer("");
		if (inputString != null) {
			for (int i = 0; i < inputString.length(); i++) {
				char currentChar = inputString.charAt(i);

				if (Character.isUpperCase(currentChar)) { // Upper Case
					char currentCharToLowerCase = Character
							.toLowerCase(currentChar);
					result.append(currentCharToLowerCase);
				} else if (Character.isLowerCase(currentChar)) { // Lower Case
					char currentCharToUpperCase = Character
							.toUpperCase(currentChar);
					result.append(currentCharToUpperCase);
				} else { // Not a letter
					result.append(currentChar);
				}
			}
		}

		return result.toString();
	}

	/**
	 * Convert to Camel Case
	 * 
	 * @param textList
	 * @return
	 * @throws Exception
	 */
	public String getCamelCaseText(List<String> textList) throws Exception {

		StringBuffer result = new StringBuffer("");

		for (int i = 0; i < textList.size(); i++) {
			result.append(getFirstLetterCapsText(textList.get(i)));
		}

		return result.toString();

	}

	/**
	 * Convert to Camel Case
	 * 
	 * @param textList
	 * @param needSpace
	 * @return
	 * @throws Exception
	 */
	public String getCamelCaseText(List<String> textList, boolean needSpace)
			throws Exception {

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < textList.size(); i++) {
			if (needSpace == true) {
				result.append(getFirstLetterCapsText(textList.get(i))).append(
						" ");
			} else {
				result.append(getFirstLetterCapsText(textList.get(i)));
			}
		}

		return result.toString();
	}

	/**
	 * Convert to Camel Case
	 * 
	 * @param inputString
	 * @return
	 * @throws Exception
	 * Example : input  - HeLLo java. hello WORlD. 
	 * 			 result - Hello Java. Hello World.
	 */
	public String getCamelCaseText(String inputString) throws Exception {

		StringBuffer result = new StringBuffer("");
		if (inputString.length() == 0) {
			return result.toString();
		}
		char firstChar = inputString.charAt(0);
		char firstCharToUpperCase = Character.toUpperCase(firstChar);
		result.append(firstCharToUpperCase);
		for (int i = 1; i < inputString.length(); i++) {
			char currentChar = inputString.charAt(i);
			char previousChar = inputString.charAt(i - 1);
			if (previousChar == ' ') {
				char currentCharToUpperCase = Character
						.toUpperCase(currentChar);
				result.append(currentCharToUpperCase);
			} else {
				char currentCharToLowerCase = Character
						.toLowerCase(currentChar);
				result.append(currentCharToLowerCase);
			}
		}
		return result.toString();
	}

	/**
	 * Convert to Title/Sentence Case
	 * 
	 * @param inputString
	 * @return 
	 * Example : input  - HeLLo java. hello WORlD. 
	 * 			 result - Hello java. Hello world.
	 */
	public static String getTitleCaseText(String inputString) {

		StringBuffer result = new StringBuffer("");
		if (inputString.length() == 0) {
			return result.toString();
		}
		char firstChar = inputString.charAt(0);
		char firstCharToUpperCase = Character.toUpperCase(firstChar);
		result.append(firstCharToUpperCase);

		boolean terminalCharacterEncountered = false;
		char[] terminalCharacters = { '.', '?', '!' };
		for (int i = 1; i < inputString.length(); i++) {
			char currentChar = inputString.charAt(i);
			if (terminalCharacterEncountered) {
				if (currentChar == ' ') {
					result.append(currentChar);
				} else {
					char currentCharToUpperCase = Character
							.toUpperCase(currentChar);
					result.append(currentCharToUpperCase);
					terminalCharacterEncountered = false;
				}
			} else {
				char currentCharToLowerCase = Character
						.toLowerCase(currentChar);
				result.append(currentCharToLowerCase);
			}
			for (int j = 0; j < terminalCharacters.length; j++) {
				if (currentChar == terminalCharacters[j]) {
					terminalCharacterEncountered = true;
					break;
				}
			}
		}
		return result.toString();

	}

	/**
	 * Get Formatted Name
	 * 
	 * @param fName
	 * @param mName
	 * @param lName
	 * @param credentials
	 * @return
	 * @throws Exception 
	 */
	public String getFormattedName(String fName, String mName, String lName,
			String credentials) throws Exception {

		StringBuffer result = new StringBuffer("");

		result.append(getFirstLetterCapsText(fName).trim()).append(" ");

		if (mName != null && !mName.trim().isEmpty())
			if (mName.length() == 1)
				result.append(mName.toUpperCase().trim()).append(". ");
			else if (mName.length() > 1)
				result.append(getFirstLetterCapsText(mName).trim()).append(" ");

		if (lName != null && !lName.trim().isEmpty())
			result.append(getFirstLetterCapsText(lName).trim());

		if (credentials != null && !credentials.trim().isEmpty()) {
			for (int i = 0; i < credentials.length(); i++) {
				if (credentials.charAt(credentials.length() - 1) == '.')
					credentials = credentials.substring(0,
							credentials.length() - 1);
				else
					break;
			}
			result.append(", ");
			result.append(credentials.trim().toUpperCase());
		}

		return result.toString().trim();
	}
	
	/**
	 * Calculate Age from Date of Birth
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getAge(String dob) {
		
		Date d = new Date();
		String[] temp = dob.split("/");
		int curr_month = d.getMonth()+1;
		int curr_year = d.getYear()+1900;
		int curr_day=d.getDate();
		int d_month= Integer.parseInt(temp[0]);
		int d_day= Integer.parseInt(temp[1]);
		int d_year= Integer.parseInt(temp[2]);
		int resYear=0, resMonth=0, resDay=0;
		String ageMessage="";
		if(curr_month>= d_month)
		{
			resYear=curr_year-d_year;
			resMonth=curr_month-d_month;
		}
		else
		{
			resYear=curr_year-d_year-1;
			resMonth=12-d_month+curr_month;	
		}
		if(curr_day > d_day)
			resDay=curr_day-d_day;
		else{
			if(curr_month==1 || curr_month==2 || curr_month == 4 || curr_month == 6 || curr_month == 8 || curr_month==9 || curr_month==11)
			 resDay= curr_day-d_day +31;
			else if(curr_month== 5 || curr_month==7 || curr_month==10 || curr_month==12 )
				resDay= curr_day-d_day +30;
			else if(curr_month== 3)
				resDay= curr_day-d_day +28;
			else 
				resDay= curr_day-d_day +30;
		}
		if(resYear ==0){
			if(resMonth == 0){
				ageMessage = resDay+" d";
			}
			else{
				ageMessage = resMonth+" m"+((resDay==0)?"":(" "+resDay+" d"));
			}
		}else{
			ageMessage =resYear+" y"+((resMonth==0)? "" :(" "+resMonth+" m"));
		}
		System.out.println("ageMessage::"+ageMessage);
		return ageMessage;
	}

	/**
	 * Get Formatted Address
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	public String getAddress(String address1,
			String address2, String city,
			String state, String zip) {
		
		StringBuffer resultAddress = new StringBuffer("");
		int addressNotEmpty = 0;
		if(address1!= null && !address1.trim().isEmpty()) {
			addressNotEmpty =1;
			resultAddress.append(address1.trim());
		}
		if(address2 != null && ! address2.trim().isEmpty()) {
			if(addressNotEmpty == 1) {
				resultAddress.append(", ").append(address2.trim());
			} else {
				resultAddress.append(address2.trim());
			}
			addressNotEmpty =1;
		}
		if(city != null && !city.trim().isEmpty()) {
			if(addressNotEmpty == 1) {
				resultAddress.append(", ").append(city.trim());
			} else {
				resultAddress.append(city.trim());
			}
			addressNotEmpty =1;
		}
		if(state != null && !state.trim().isEmpty()) {
			if(addressNotEmpty == 1) {
				resultAddress.append(", ").append(state.trim());
			} else {
				resultAddress.append(state.trim());
			}
			addressNotEmpty =1;
		}
		if(zip != null && !zip.trim().isEmpty()) {
			if(addressNotEmpty == 1) {
				resultAddress.append(", ").append(zip.trim());
			} else {
				resultAddress.append(zip.trim());
			}
			addressNotEmpty =1;
		}
		
		if(addressNotEmpty==1)
			resultAddress.append(".");
		
		return resultAddress.toString();
	}
	
	/**
	 * Convert date to required format
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public String getFormattedDate(Date date) throws ParseException {

		return formatDateFromOnetoAnother(date,
				"yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy");
	}

	/**
	 * Convert timestamp to required date format
	 * @param timestamp
	 * @return
	 */
	public String getFormattedDate(Timestamp timestamp) {

		Date date = new Date(timestamp.getTime());
		return formatDateFromOnetoAnother(date,
				"yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy");
	}
	
	/**
	 * Convert date format from ? to ?
	 * @param date
	 * @param givenformat
	 * @param resultformat
	 * @return
	 */
	public static String formatDateFromOnetoAnother(Date date,
			String givenformat, String resultformat) {
		String result = "";
		SimpleDateFormat sdf;

		try {
			sdf = new SimpleDateFormat(resultformat);
			result = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			sdf = null;
		}

		return result;
	}

	/**
	 * Method to format phone number
	 * @param input
	 * @return
	 */
	public String getFormattedPhoneNum(String input) {
		try{
			if(input == null)
				return "";
			else if(input.indexOf("-")!=-1){
				String[] arr= 	input.split("-");
				if(arr.length == 3)
					input = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
			}else if(input.trim().length()==10){
				input = "("+ input.substring(0, 3) + ") " + input.substring(3, 6) + "-" + input.substring(6, 10);
			}
			return input;
		}catch(Exception e){
			return "";
		}
	}

	@SuppressWarnings("deprecation")
	public int getAgeInYear(Date date) {

		int daysInMon[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // Days in month
		int days, month, year;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		days = cal.get(Calendar.DAY_OF_MONTH);

		Date d = new Date();

		days = daysInMon[month - 1] - days + 1;

		/* Calculating the num of year, month and date */
		days = days + d.getDate();
		month = (12 - month) + d.getMonth();
		year = (d.getYear() + 1900) - year - 1;

		if (month >= 12) {
			year = year + 1;
			month = month - 12;
		}

		return year;

	}
	
	/**
	 * Convert date to other format
	 * @param date
	 * @param SOURCE_FORMAT
	 * @param TARGET_FORMAT
	 * @return
	 */
	public Date convertDate(Date date, final String SOURCE_FORMAT, final String TARGET_FORMAT){
		
		try{
			SimpleDateFormat sdf= new SimpleDateFormat(SOURCE_FORMAT);						
			date= sdf.parse(sdf.format(date));
			sdf.applyPattern(TARGET_FORMAT);			
			return sdf.parse(sdf.format(date));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Convert date string to other format string
	 * @param date
	 * @param SOURCE_FORMAT
	 * @param TARGET_FORMAT
	 * @return
	 */
	public String convertDate(final String date, final String SOURCE_FORMAT, final String TARGET_FORMAT){
		
		try{
			SimpleDateFormat sdf= new SimpleDateFormat(SOURCE_FORMAT);						
			Date dobDate= sdf.parse(date);
			sdf.applyPattern(TARGET_FORMAT);			
			return sdf.format(dobDate);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

