package tests;

import java.net.UnknownHostException;

import controllers.CustomerController;

public class ApplicationTester {

	public static void main(String[] args) {
		String url = "https://gist.githubusercontent.com/brianw/"
				+ "19896c50afa89ad4dec3/raw/"
				+ "6c11047887a03483c50017c1d451667fd62a53ca/"
				+ "gistfile1.txt";
		CustomerController application = new CustomerController(url);
		application.contactServer();
	}
}