package clients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Requests {
	String requesturl;
	public Requests(String requesturl) {
		this.requesturl = requesturl;
	}
	public Requests() {
		 requesturl = "http://localhost:8080/";
	}
	public String get(String id) throws IOException {
		//Read from Servlet
		URL url = new URL( requesturl+id );
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
	public String post(String id, String msg) throws IOException {
		String urlParameters = "id="+id+"&message=\""+msg+"\"";		
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
	public String delete(String id) throws IOException, MalformedURLException{
		URL  url = new URL(requesturl+id);
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
	public String put(String id, String msg) throws IOException {
		String urlParameters = "id="+id+"&message=\""+msg+"\"";		
		URL url = new URL(requesturl);
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
