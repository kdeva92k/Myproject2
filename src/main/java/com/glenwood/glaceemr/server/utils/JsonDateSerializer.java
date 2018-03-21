package com.glenwood.glaceemr.server.utils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonSerializer<Date>{
	
	
	

	@Override
	public void serialize(Date date, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		String date1[] = date.toString().split("\\.");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateval=null;
		
		try {
			 dateval = sdf.parse(date1[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");
		generator.writeString(form.format(dateval));
		/*String date[] = timestamp.toString().split("\\.");
		gen.writeString(date[0].toString());*/

		
		
		
//		generator.writeString(date.toString());
		
	}

}