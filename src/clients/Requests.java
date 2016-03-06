package clients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Requests {
	String requesturl;
	public Requests(String requesturl) {
		this.requesturl = requesturl;
	}
	public Requests() {
		 requesturl = "http://localhost:8080";
	}
	public String get(String param) throws IOException {
		String requestWithParams = requesturl;
		if (!param.equals("") && param!=null) {
			requestWithParams += "/"+param;
		}
		URL url = new URL(requestWithParams);

		HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "GET" );
		conn.setUseCaches( false );
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine = null;
		StringBuffer resp = new StringBuffer();
		while ((inputLine = reader.readLine()) != null) {
			resp.append(inputLine);
		}
		conn.disconnect();
		return resp.toString();
	}
	public String post(HashMap<String, String> params) throws IOException {
		String urlParameters = "";
		int count = 1;
		for (HashMap.Entry<String, String> entry : params.entrySet()) {
		    String key = entry.getKey().toString();
		    String value = entry.getValue().toString();
		    if (count < params.size() && count != 1) {
		    	urlParameters += "&";
		    }
		    urlParameters += key+"="+value;
		    count++;
		}
		URL url = new URL(requesturl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		conn.disconnect();
		return response.toString();
	}
	public String delete(String param) throws IOException, MalformedURLException{
		String requestWithParams = requesturl;
		if (!param.equals("") && param!=null) {
			requestWithParams += "/"+param;
		}
		URL url = new URL(requestWithParams);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestMethod("DELETE");
	    //System.out.println(conn.getResponseCode());
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	    conn.disconnect();
		return response.toString();
	}
	public String put(HashMap<String, String> params) throws IOException {
		String urlParameters = "";
		int count = 1;
		for (HashMap.Entry<String, String> entry : params.entrySet()) {
		    String key = entry.getKey().toString();
		    String value = entry.getValue().toString();
		    if (count < params.size() && count != 1) {
		    	urlParameters += "&";
		    }
		    urlParameters += key+"="+value;
		    count++;
		}		URL url = new URL(requesturl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestMethod("PUT");
		con.setDoOutput(true);
		con.setDoInput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		con.disconnect();
		return response.toString();
	}
}
