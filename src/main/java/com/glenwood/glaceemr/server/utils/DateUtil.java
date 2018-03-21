/**
 *
 * This class contains all the date utility functions. This contains wrapper methods
 * over java.util.Date and java.util.Calendar methods for ease of programming.
 *
 * @author SBJ
 * @since 22-Apr-2003
 * @version 1.0
 *
 */

package com.glenwood.glaceemr.server.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class DateUtil{

    // Interval Type Constants
  public static final int SECOND = 1;
  public static final int MINUTE = 2;
  public static final int HOUR   = 3;
  public static final int DATE   = 4;
  public static final int MONTH  = 5;
  public static final int YEAR   = 6;

    // Format Type Constants
  public static final int DATE_ONLY = 1;
  public static final int TIME_ONLY = 2;
  public static final int DATE_TIME = 3;
    // Arrays containing Month and Day names
  final static String[] monthNames   = {"January","February","March","April","May","June","July","August","September","October","November","December"};
  final static String[] weekDayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    /**
     *
     * The Function gives the difference between the two date objects passed
     *
     * @param intervalType   One of the values of Interval Type Constants.
     * @param dateObjStart   Date Object Containing the Start Date     
     * @param dateObjEnd     Date Object Containing the End Date
     *
     * @return               The difference between the two date objects passed.
     *                       If dateObjStart < dateObjEnd , the value will be > 0.
     */

  public static int dateDiff( int intervalType , java.util.Date dateObjStart , java.util.Date dateObjEnd ) {

      long divisor = 1000;
      long diff = 0;
      // Calculate the divisor value for converting to the required type

      if ( intervalType == DateUtil.SECOND ) divisor = 1000; // 1000
      else if ( intervalType == DateUtil.MINUTE ) divisor = 60000; // 1000 * 60
      else if ( intervalType == DateUtil.HOUR ) divisor = 3600000; // 1000 * 60 * 60
      else if ( intervalType == DateUtil.DATE ) divisor = 86400000; // 1000 * 60 * 60 * 24
      
      if ( intervalType == DateUtil.MONTH )  //1000 * 60 * 60 * 24 * 30.4375
	  diff = ( long )Math.abs( (dateObjEnd.getTime() - dateObjStart.getTime()) / ( 86400000 * 30.4375 ) );
      else
          diff = (dateObjEnd.getTime() - dateObjStart.getTime()) / divisor;

      return ( (int)diff );
  }
  
  @SuppressWarnings("deprecation")
public static int[] dateDiff( Date fromDate , Date toDate ){
	  int Years, Months, Days,Hour,Minute;
	  if(fromDate.before(toDate)){
		  Date temp = fromDate;
		  fromDate = toDate;
		  toDate = temp;
	  }			

	  Minute = fromDate.getMinutes() - toDate.getMinutes();

	  if(Minute<0){
		  Hour = fromDate.getHours() - toDate.getHours()-1;
		  Minute += 60;
	  }
	  else 
		  Hour = fromDate.getHours() - toDate.getHours();

	  if(Hour<0){
		  Days = fromDate.getDate() - toDate.getDate()-1;
		  Hour +=24;
	  }
	  else
		  Days = fromDate.getDate() - toDate.getDate();

	  if(Days<0){
		  Months = fromDate.getMonth() - toDate.getMonth()-1;
		  int [] daysInMonths = {31,(((fromDate.getYear()%4)==0)?29:28),31,30,31,30,31,31,30,31,30,31};			
		  Days += daysInMonths[(fromDate.getMonth()+11)%12] ;			
	  }
	  else
		  Months = fromDate.getMonth() - toDate.getMonth();

	  if(Months<0){
		  Years = fromDate.getYear() - toDate.getYear()-1;
		  Months += 12;
	  }
	  else
		  Years = fromDate.getYear() - toDate.getYear();	

	  int dateDiff[]= {Years,Months,Days,Hour,Minute};
	  return dateDiff;
  }
  
  /**
  * The Function gives the difference between the two dates passed as String
     *
     * @param intervalType   One of the values of Interval Type Constants.
     * @param dateObjStart   String Containing the Start Date     
     * @param dateObjEnd     String Containing the End Date
     *
     *
     @return               The difference between the two dates passed.
     *                       If dateObjStart < dateObjEnd , the value will be > 0.
  */
  
  public static int dateDiff( int intervalType , String dateObjStart , String dateObjEnd ){
    return DateUtil.dateDiff( intervalType , DateUtil.getDate( dateObjStart ) , DateUtil.getDate( dateObjEnd ) ) ;
  }
  /**/

  
    /**
     *
     * The Function adds the interval to the passed date object.
     *
     * @param intervalType   One of the values of Interval Type Constants.
     * @param interval       Interval to be added to the date object passed
     * @param dateObj        Date Object passed
     *
     * @return               The date object to which the interval is added.
     */

  public static java.util.Date dateAdd( int intervalType , int interval , java.util.Date dateObj ){

      // Create a gregorian calendar object.
      GregorianCalendar gCal;
      gCal = (GregorianCalendar)Calendar.getInstance();
      // Set the value to calendar object from the passed date object.
      gCal.setTime( dateObj );
      // Get the equivalent constant from Calendar Class.
      if ( intervalType == DateUtil.HOUR ) intervalType = Calendar.HOUR;
      else if ( intervalType == DateUtil.MINUTE ) intervalType = Calendar.MINUTE;
      else if ( intervalType == DateUtil.SECOND ) intervalType = Calendar.SECOND;
      else if ( intervalType == DateUtil.DATE ) intervalType = Calendar.DATE;
      else if ( intervalType == DateUtil.MONTH ) intervalType = Calendar.MONTH;
      else if ( intervalType == DateUtil.YEAR ) intervalType = Calendar.YEAR;

      // Add the required interval
      gCal.add(intervalType,interval);
      dateObj = gCal.getTime();

      return (dateObj);
  }

  /**
     * The Function adds the interval to the date passsed as String.
     *
     * @param intervalType   One of the values of Interval Type Constants.
     * @param interval       Interval to be added to the date object passed
     * @param dateObj        Date passed as String
     *
     * @return               Date as String having date added with interval.
  */
  public static String dateAdd( int intervalType , int interval , String dateStr ){
    return DateUtil.formatDateTime( DateUtil.dateAdd( intervalType , interval , DateUtil.getDate( dateStr ) ) , DateUtil.TIME_ONLY );
  }
  
  /**/
  
    /**
     *
     * The Function formats the date object passed according to Format Type.
     *
     * @param dateObj        Date Object passed
     * @param formatType     One of the values of Format Type Constants.
     *
     * @return               The formatted string representation of the date object passed.
     */

  public static String formatDateTime(java.util.Date dateObj , int formatType  ){

    GregorianCalendar gCal;
    String formattedString = "";
    int hourOfDay=0;
    gCal = (GregorianCalendar)Calendar.getInstance();
    gCal.setTime( dateObj );

    if ( formatType == DateUtil.DATE_ONLY || formatType == DateUtil.DATE_TIME ) //Getting Date Part
      formattedString = formatNumber((gCal.get(Calendar.MONTH)+1),2) + "/" + formatNumber(gCal.get(Calendar.DATE),2) + "/" + formatNumber(gCal.get(Calendar.YEAR),4) ;

    if ( formatType == DateUtil.TIME_ONLY || formatType == DateUtil.DATE_TIME){ //Getting Time Part
	//formattedString = formattedString + " " + gCal.get(Calendar.HOUR) + ":" + gCal.get(Calendar.MINUTE) + ":" + gCal.get(Calendar.SECOND ) + " " + 
	hourOfDay = gCal.get(Calendar.HOUR_OF_DAY);
	hourOfDay = HUtil.IIF(hourOfDay==0,12,hourOfDay);
	formattedString = formattedString + " " + formatNumber(HUtil.IIF(hourOfDay>12 ,hourOfDay-12,hourOfDay),2) + ":" + formatNumber(gCal.get(Calendar.MINUTE),2) + ":" + formatNumber(gCal.get(Calendar.SECOND ),2) + " " + 
       ( (gCal.get(Calendar.AM_PM)==Calendar.AM ) ? "AM":"PM" ) ;
    }
    return ( formattedString.trim() );
    }

    /**
     *
     * This functions returns the Name of month when the month index is passed.
     *
     * @param monthIndex     The month index passed.
     *
     * @return               The equivalent month name of the month index passed.
     *                       Eg: March is returned when 3 is passed.
     */
    public static String getMonthName( int monthIndex ){ //monthIndex starts from 1
      return ( monthNames[ monthIndex-1 ] );
    }

    /**
     *
     * This functions returns the Name of Week Day when the day index is passed.
     *
     * @param dayIndex       The week day index passed.(Starts from 1).
     *
     * @return               The equivalent week day name of the week day index passed.
     *                       Eg: Sunday is returned when 1 is passed.
     */
    public static String getWeekDayName( int weekDayIndex ){
      return ( weekDayNames[ weekDayIndex-1 ] );
    }

    //Returns the Month Names as an string array
    public static String[] getMonthNames( ){ 
      return ( monthNames );
    }

    //Returns the Week Day Names as an string array
    public static String[] getWeekDayNames( ){ 
      return ( weekDayNames );
    }

    // Returns the Date object for the given String representation of the date.
    public static java.util.Date getDate(String dateStr ){
      GregorianCalendar gCal;
      int               xYear,xMonth,xDate,xHour,xMinute,xSecond;
      String            timePart = "",sessionValue="";
      String[]          dateArray,fullDateTime,timeArray;
      dateStr = dateStr.trim();
      gCal = ( GregorianCalendar )Calendar.getInstance();
      if (dateStr.indexOf(" ") > 0){
        fullDateTime = dateStr.split(" ");
        timePart = fullDateTime[1];
	sessionValue = fullDateTime[2];
	sessionValue = sessionValue.toUpperCase();
        timePart = timePart.trim();
        timeArray = timePart.split(":");
	dateArray = fullDateTime[0].split("/");
	xYear = Integer.parseInt( dateArray[2] );
	xMonth = Integer.parseInt( dateArray[0] )-1 ;
	xDate = Integer.parseInt( dateArray[1] );
	xHour = Integer.parseInt( timeArray[0] );

	if ( sessionValue.equals("PM") && xHour != 12 ) xHour += 12;
        //if (sessionValue.equals("PM")) xHour += 12 ;
        if (( xHour == 12 ) && ( sessionValue.equals("AM") )) xHour = 0;
	xMinute = Integer.parseInt( timeArray[1] ) ;
	xSecond = Integer.parseInt( timeArray[2] );
	gCal.set( xYear , xMonth , xDate , xHour , xMinute , xSecond);
      }
      else{
        dateArray = dateStr.split("/");
        xYear = Integer.parseInt( dateArray[2] );
        xMonth = Integer.parseInt( dateArray[0] )-1 ;
        xDate = Integer.parseInt( dateArray[1] );
        gCal.set( xYear , xMonth , xDate,0,0,0);
      }
      return   (gCal.getTime() );
    }


    /* Returns Day Name of given Date Object */
    public static String getDayName ( java.util.Date dateObj )
    {
      GregorianCalendar gCal;
      gCal = (GregorianCalendar)Calendar.getInstance();
      gCal.setTime( dateObj );
      return getWeekDayName ( gCal.get ( Calendar.DAY_OF_WEEK ) );
    }
    /* Returns Day Name of given Date String */
    public static String getDayName ( String dateString ) 
    {
      GregorianCalendar gCal;
      gCal = (GregorianCalendar)Calendar.getInstance();
      java.util.Date dateObj = getDate ( dateString );
      gCal.setTime( dateObj );
      return getWeekDayName ( gCal.get ( Calendar.DAY_OF_WEEK ) );
    }
    public static String formatNumber(int xNumber,int maxDigits){
      String formattedNumber = xNumber + "";
      int len = formattedNumber.length();
        for ( int i = len ; i < maxDigits ; i++ ){
          formattedNumber = "0" + formattedNumber;
        }
        return ( formattedNumber );
    }

    /**
     * This function can be used to convert the string into a Date object
     * @param xDate The date String that is to be converted to Date object
     * @returns The Date object for the given date
     */
    public static Date convertToDate(String xDate,String format,boolean withSeconds) throws Exception{
	String          temp[]     = {"","","","12","00","00","AM"};
	StringTokenizer toks       = new StringTokenizer(format,"~");
	int             type       = Integer.parseInt(toks.nextToken().toString());
	String          seperator  = toks.nextToken().toString();
	StringTokenizer datetime   = new StringTokenizer(xDate," "+((char)160));
	String          AmPm       = "";
	StringTokenizer date       = null;
	StringTokenizer time       = null;
	if(datetime.hasMoreElements()) date = new StringTokenizer(datetime.nextToken(),seperator);
	if(datetime.hasMoreElements()) time = new StringTokenizer(datetime.nextToken(),":");
	if(datetime.hasMoreElements()) AmPm = datetime.nextToken();
	int i       = 0;
	int index[] = new int[3];
	int count   = 0;
	int Month   = 0;
	int Date    = 0;
	int Year    = 0;
	int Hour    = 0;
	int Min     = 0;
	int Sec     = 0;
	getIndex(index,type);
	count = date.countTokens();
	if(date.countTokens() < 3) throw new Exception("ParseException Invalid Date");
	i = 0;  
	while(date.hasMoreElements()) temp[i++]=(String)date.nextElement();
	i = 3;
	if(time != null)
	    while(time.hasMoreElements()) temp[i++]=(String)time.nextElement();
	Month = Integer.parseInt(temp[index[0]]);
	Date  = Integer.parseInt(temp[index[1]]);
	Year  = Integer.parseInt(temp[index[2]]);
	if(Year >= 0   && Year < 50)   Year = Year+2000;
	if(Year >= 50  && Year < 100)  Year = Year+1900;
	if(Year >  100 && Year < 1753) throw new Exception("Parse Exception:Invalid year ");
	Hour   = Integer.parseInt(temp[3]);
	Min    = Integer.parseInt(temp[4]);
	Sec    = (withSeconds)?Integer.parseInt(temp[5]):0;
	if(Hour < 1 || Hour > 12 ) throw new Exception("Parse Exception Invalid Hour");
	Hour   = handleAmPm(AmPm,Hour);   
	Date c = new Date(Year-1900,Month-1,Date,Hour,Min,Sec);
	return c;
    }
    
    public static Date convertToDate(String xDate,String format)throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.parse(xDate);
    }
    
    /**
       This function gives 0 or 1 or -1 based on the date difference
       @param A string 'datestr1' containing the first date is passed
       @param A string 'datestr2' containing the second date is passed
       @return A byte value to tell the time difference will be returned
    */
    public static byte dateDiffVal(String dateStr1, String dateStr2) throws Exception{
	GregorianCalendar calendar1 = new GregorianCalendar();
	GregorianCalendar calendar2 = new GregorianCalendar();
	java.util.Date date1 = convertToDate(dateStr1,"1~/",false);
	java.util.Date date2 = convertToDate(dateStr2,"1~/",false);
	calendar1.setTime(date1);
	calendar2.setTime(date2);
	if(calendar1.before(calendar2))
	    return -1;
	else if(calendar1.after(calendar2))
	    return 1;
	else
	    return 0;
    }//end of dateDiff()
    
  private static int handleAmPm(String xAmPm,int Hour){
    String AmPm = xAmPm.toUpperCase();
    if(AmPm.equals("AM"))
    {
      if(Hour==12) Hour=24;
    }
    if(AmPm.equals("PM"))
    {
      if(Hour==12) Hour =0 ;
      Hour += 12;
    }
    Hour%=24;
    return Hour;
  }

  private static void getIndex(int[] index,int type){
    switch(type){
      case 1: case 4://for mm dd yy
        index[0] =0;index[1] =1;index[2] =2;break;
      case 2: case 5://dd mm yy
        index[0] =1;index[1] =0;index[2] =2;break;
      case 3:        // yy mm dd
        index[0] =1;index[1] =2;index[2] =0;break;
    }
  }
    
    /**
       This function gives datepart/monthpart/yearpart for a given date object.
       Mode specifies the value(1-date/2-month/3-year) will be returned
    */
    public static int datePart(java.util.Date dateObj, int mode) throws Exception{
	GregorianCalendar gCal = new GregorianCalendar();
	gCal.setTime(dateObj);
	if(mode == 1)
	    return gCal.get(Calendar.DATE);
	else if(mode == 2)
	    return gCal.get(Calendar.MONTH)+1;
	else
	    return gCal.get(Calendar.YEAR);
    }

    /**
       This function gives datepart/monthpart/yearpart for a given date string.
       Mode specifies the value(1-date/2-month/3-year) will be returned
    */
    public static int datePart(String dateStr, int mode) throws Exception{
	GregorianCalendar gCal = new GregorianCalendar();
	java.util.Date dateObj = convertToDate(dateStr,"1~/",false);
	gCal.setTime(dateObj);
	if(mode == 1)
	    return gCal.get(Calendar.DATE);
	else if(mode == 2)
	    return gCal.get(Calendar.MONTH)+1;
	else
	    return gCal.get(Calendar.YEAR);
    }
    
    public static String calculateAge(Date dob,boolean timeSpecific){
    	String DiscreteAge="";
    	try{
    		int dateDiff[] = DateUtil.dateDiff(new Date(),dob);
    		int ageinyear = 0,ageinmonth=0,ageinday=0,ageinhour,ageinminute;
    		ageinyear = dateDiff[0];
    		ageinmonth = dateDiff[1];
    		ageinday = dateDiff[2];
    		ageinhour = dateDiff[3];
    		ageinminute = dateDiff[4];

    		//GlaceOutLoggingStream.console("ageinyear="+ageinyear+"  ageinmonth="+ageinmonth+"  ageinday="+ageinday+"  ageinhour="+ageinhour+"  ageinminute="+ageinminute);
    		if(ageinyear>=2){
    			DiscreteAge = ageinyear+" y "+ ((ageinmonth>0)?ageinmonth+" m":"");
    		}
    		else if(ageinmonth>=4 || ageinyear>0){
    			DiscreteAge = (ageinyear*12+ ageinmonth)+" m";
    		}
    		else if(ageinday>=28 || ageinmonth>0){
    			int totaldaysdiff = (int)((DateUtil.dateDiff( DateUtil.DATE , dob , new java.util.Date() )%365));
    			DiscreteAge = (int)(totaldaysdiff/7)+" w "+ (((totaldaysdiff%7)==0)?"":(totaldaysdiff%7)+" d");
    		}
    		else if(ageinday>=4 || !timeSpecific){
    			DiscreteAge = ageinday+" d";
    		}
    		else if(ageinhour>0){
    			DiscreteAge = ((ageinday*24)+ageinhour)+" h ";//+ageinminute+"min";
    		}
    		else{
    			DiscreteAge = ageinminute+" min(s)";
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return DiscreteAge;
    }

    public static String calculateAge(Date dob,Date Encounterdate,boolean timeSpecific){
    	String DiscreteAge="";
    	try{
    		int dateDiff[] = DateUtil.dateDiff(Encounterdate,dob);
    		int ageinyear = 0,ageinmonth=0,ageinday=0,ageinhour,ageinminute;
    		ageinyear = dateDiff[0];
    		ageinmonth = dateDiff[1];
    		ageinday = dateDiff[2];
    		ageinhour = dateDiff[3];
    		ageinminute = dateDiff[4];
    		//GlaceOutLoggingStream.console("ageinyear="+ageinyear+"  ageinmonth="+ageinmonth+"  ageinday="+ageinday+"  ageinhour="+ageinhour+"  ageinminute="+ageinminute);
    		if(ageinyear>=2){
    			DiscreteAge = ageinyear+" y "+ ((ageinmonth>0)?ageinmonth+" m":"");
    		}
    		else if(ageinmonth>=4 || ageinyear>0){
    			DiscreteAge = (ageinyear*12+ ageinmonth)+" m";
    		}
    		else if(ageinday>=28 || ageinmonth>0){
    			int totaldaysdiff = (int)((DateUtil.dateDiff( DateUtil.DATE , dob , Encounterdate )%365));
    			DiscreteAge = (int)(totaldaysdiff/7)+" w "+ (((totaldaysdiff%7)==0)?"":(totaldaysdiff%7)+" d");
    		}
    		else if(ageinday>=4 || !timeSpecific){
    			DiscreteAge = ageinday+" d";
    		}
    		else if(ageinhour>0){
    			DiscreteAge = ((ageinday*24)+ageinhour)+" h ";//+ageinminute+"min";
    		}
    		else{
    			DiscreteAge = ageinminute+" min(s)";
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return DiscreteAge;
    }
}

