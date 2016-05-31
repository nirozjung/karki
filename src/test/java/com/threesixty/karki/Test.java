package com.threesixty.karki;

import com.threesixty.server.HttpClientSendForm;

public class Test {
	public static void main(String[] args) throws Exception {

		HttpClientSendForm smp = new HttpClientSendForm();
		
		smp.startServer();
		smp.startClient();
		smp.stopClientServer();

	}
}
