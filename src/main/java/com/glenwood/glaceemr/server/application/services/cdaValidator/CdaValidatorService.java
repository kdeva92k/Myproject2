package com.glenwood.glaceemr.server.application.services.cdaValidator;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Service Interface for CDAValidation
 * @author Nithya
 *
 */
public interface CdaValidatorService {
JSONObject doValidation(String filaPath,String fileId,String isData) throws IOException, JSONException;
}
