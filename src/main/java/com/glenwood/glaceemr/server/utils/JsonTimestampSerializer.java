package com.glenwood.glaceemr.server.utils;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonTimestampSerializer extends JsonSerializer<Timestamp>{

@Override
public void serialize(Timestamp timestamp, JsonGenerator generator,
		SerializerProvider provider) throws IOException, JsonProcessingException {
//	String date[] = timestamp.toString().split("\\.");
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//	java.util.Date dateval=null;
//	
//	try {
//		 dateval = sdf.parse(date[0]);
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//	
//	SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
//	generator.writeString(form.format(dateval));
	/*String date[] = timestamp.toString().split("\\.");
	gen.writeString(date[0].toString());*/
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a z");
	generator.writeString(formatter.format(timestamp));
}
 
}