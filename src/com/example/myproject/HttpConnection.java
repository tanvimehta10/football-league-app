package com.example.myproject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class HttpConnection {

	private HttpURLConnection connection;
	private String response;
	private static final int TIMEOUT = 10 * 1000 * 60 * 5;

	private static volatile HttpConnection instance = null;

	private HttpConnection() {

	}

	public static HttpConnection getInstance() {
		if (instance == null) {
			synchronized (HttpConnection.class) {
				// double check
				if (instance == null) {
					instance = new HttpConnection();
				}
			}
		}
		return instance;
	}

	public String Get(URL url) {
		try {
			
			System.out.println(url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(TIMEOUT);
			connection.connect();
			InputStream in = new BufferedInputStream(connection.getInputStream());
			response = readStream(in);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.disconnect();
		}
		return response;
	}
	
	public String Get(URL url, List<NameValuePair> params) {
		try {
			String paramString = URLEncodedUtils.format(params, "utf-8");
			String urlString = url.toString();
			url = new URL(urlString + "?" + paramString);
			System.out.println(url.toString());
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(TIMEOUT);
			connection.connect();
			InputStream in = new BufferedInputStream(connection.getInputStream());
			response = readStream(in);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			connection.disconnect();
		}
		return response;
	}

	
	public static String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {

				sb.append(line);

			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return sb.toString();
	}


}
