package controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;

import utils.JSONArrayBuilder;

import com.devstream.io.DataHolder;
import com.devstream.io.HttpConnector;

/**
 * CustomerController makes a call
 * to the remote server on a worker thread
 * and returns the response.
 * @author Barry Dempsey
 *
 */

public class CustomerController {
	private String url;
	
	public CustomerController(String url) {
		this.url = url;
	}
	
	public void contactServer() {
		/*
		 * ExecutorService interface is use to manage 
		 * multi-threading and Future<> will return the result
		 * of the background operation. future.get() contains
		 * the actual result.
		 */
		ExecutorService service = Executors.newCachedThreadPool();
		Callable<String>callable = 
				new HttpConnector(url);
		Future<String>future = service.submit(callable);
		service.shutdown();
		DataHolder.getDataHolder().setConnected(false);
		
		try {
			/*
			 * response is a String of JSONObjects
			 * each separated by a new line.
			 * so we split this and convert to an 
			 * array and pass this as parameter
			 * to JSONArray
			 */
			String[] objects = future.get().split("\n");
			System.out.println("Response" + future.get());
			JSONArray jArray = JSONArrayBuilder.constructJSONArrayFromArray(objects);
			JSONParserController.parseTheResponse(jArray);
		} catch (NullPointerException npe){
			System.out.println("We couldn't connect so the result is not JSON\n"
					+ "Shutting down thread(s). \n"
					+ "Closing the application");
			if(!service.isShutdown())
				service.shutdown();
			System.exit(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}  	
	}
}
