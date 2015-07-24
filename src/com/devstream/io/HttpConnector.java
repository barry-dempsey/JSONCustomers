package com.devstream.io;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

/**
 * Connects to a URL via the HttpConnection class
 * and reads back the response as a String.
 * @author Barry Dempsey
 *
 */

public class HttpConnector implements Callable<String> {
	private HttpURLConnection httpcon;
	private String url;
	private OutputStream os;
	
	public HttpConnector(String url) {
		this.url = url;
	}
	
	/**
	 * read the response from the URL
	 * @return the response String
	 * @throws IOException
	 */
	private String readTheResponse() throws IOException {
		InputStream is = httpcon.getInputStream();
		StringBuilder sb = new StringBuilder();
		int ch = 0;
		while((ch = is.read()) != -1) {
			sb.append((char)ch);
		}
		String result = sb.toString();
		httpcon.disconnect();
		return result;
	}

	/**
	 * call method of Callable runs on
	 * a worker thread and tries to connect
	 * to the URL. Get 200 for connection
	 * and 404 for error.
	 */
	@Override
	public String call() throws Exception {
		try {
			httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
			URLEncoder.encode(url, "ISO-8859-1");
			httpcon.setDoOutput(true);
			httpcon.setRequestMethod("GET");
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.connect();
			if(httpcon.getResponseCode() == 200) {
				return readTheResponse();
			}
		} catch (IOException e) {
			System.out.println("Unknown Host Exception. "
					+ "\nCheck the URL and Network Connection");
			//e.printStackTrace();
		} finally {
			httpcon.disconnect();
		}
		return url;
	}
}
