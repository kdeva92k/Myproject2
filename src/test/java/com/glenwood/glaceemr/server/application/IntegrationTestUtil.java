package com.glenwood.glaceemr.server.application;


import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;

 
public class IntegrationTestUtil {
 
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
 
  /*  public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion(SerializationFeature.WRITE_NULL_MAP_VALUES.enabledByDefault());
        return mapper.writeValueAsBytes(object);
    }*/
}
