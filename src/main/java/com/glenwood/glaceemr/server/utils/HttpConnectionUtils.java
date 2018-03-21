package com.glenwood.glaceemr.server.utils;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class HttpConnectionUtils {
	public static final int HTTP_CONNECTION_MODE=1;
	public static final int HTTPS_CONNECTION_MODE=2;

	/**
	 * This will make HTTP/HTTPS connection to the source URL and download the response from the server.
	 * @param sourceURL
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	public static String downloadData(String sourceURL, int mode)throws Exception{
		InputStream is = null;
		StringBuilder responseFromServer = new StringBuilder();
		try{
			if(mode==HttpConnectionUtils.HTTPS_CONNECTION_MODE){
				SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
				sslContext.init(null, null, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			}
			
			URL accountURL = new URL(sourceURL);
			URLConnection urlConnection = accountURL.openConnection();
			
			is = urlConnection.getInputStream();
			byte[] data = new byte[1024];
			
			int nRead;
			while((nRead = is.read(data, 0, data.length))!=-1){
				if (nRead < data.length) {
					responseFromServer.append(new String(HttpConnectionUtils.transferData(data, 0, nRead)));
				} else {
					responseFromServer.append(new String(data));
				}
			}
			return responseFromServer.toString();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		finally{
			is.close();
		}
	}
	
	
	public static String postData(String connectionURL, String postData, int mode) throws Exception{
		URL u = new URL(connectionURL);
		HttpURLConnection conn = null;
		BufferedReader reader  = null;
		StringBuffer response = new StringBuffer();
		try{
			conn = (HttpURLConnection) u.openConnection();
			conn.setDoOutput(true);
	        conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			
			DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
	        outStream.writeBytes(postData);
	        outStream.flush();
	        outStream.close();
	
	        
	        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            response.append(line);
	        }
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(reader!=null){
			reader.close();       
			}
			if(conn!=null){
				conn.disconnect();
			}
		}
        return response.toString();
	}
	
	
	
	
	
	/**
	 * This function will trim the byte array.
	 * @param data
	 * @param startPosition
	 * @param endPosition
	 * @return
	 */
	private static byte[] transferData(byte[] data, int startPosition, int endPosition) {
		byte[] newData = new byte[endPosition - startPosition];
		int newDataIdx = 0;
		for (int i = startPosition; i < endPosition; i++) {
			newData[newDataIdx] = data[i];
			newDataIdx++;
		}
		return newData;
	}
}

