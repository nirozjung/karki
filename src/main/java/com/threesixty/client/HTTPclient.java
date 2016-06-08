package com.threesixty.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.Fields.Field;

import com.threesixty.server.HttpClientSendForm;

/**
 * @Example from http://zetcode.com/java/jetty/httpclient/
 * @author nirozjungkarki
 *
 */

/**
 * @author nirozjungkarki
 *
 */
/**
 * @author nirozjungkarki
 *
 */
public class HTTPclient {
	private HttpClient client;
	private ArrayList<Market> newMarketDataList;

	public void startClient() throws Exception {
		// Instantiate HttpClient
		client = new HttpClient();
		client.start();
		System.out.println("Here! started client!!");

		// create random 10,000 Market data objects and sending it to jetty
		// server
		Market[] market = new Market[1];
		for (int i = 0; i < market.length; i++) {

			String randomSymbol = Settings.symbol[new Random().nextInt(Settings.symbol.length)];
			String randomBank = Settings.bank[new Random().nextInt(Settings.bank.length)];

			BigDecimal randomAmount = generateRandomBigDecimalFromRange(
					new BigDecimal(-1.21).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(9999.28).setScale(2, BigDecimal.ROUND_HALF_UP));

			// creating number of random market objects from the Arraylists in
			// Settings
			market[i] = new Market(randomSymbol, randomBank, randomAmount);
			Field symbol = new Field("symbol", market[i].getSymbol());
			Field bank = new Field("bank", market[i].getBank());
			Field price = new Field("price", market[i].getPrice().toString());
			Fields fields = new Fields();
			fields.put(symbol);
			fields.put(bank);
			fields.put(price);

			long sendtimeStamp = System.nanoTime();
			// Content length is limited to 2MB by default
			ContentResponse res = client.FORM("http://localhost:8080", fields);
			long roundTripTime = (System.nanoTime() - sendtimeStamp);

			System.out.print(res.getContentAsString());
			System.out.println("RoundTrip Time:" + roundTripTime / 1E9 + " seconds" + "\n");

			String responseServer = res.getContentAsString();
			String[] lines = responseServer.split("\\r?\\n");
			
			Market newMarketData = new Market();
			newMarketData.setRoundTripTime(roundTripTime);
			for (int x = 0; x < lines.length; x++) {
				String[] d = lines[x].split(":");
				if (d.length == 2) {
					String key = d[0];
					String val = d[1];

					if (key.equals("symbol")) {
						newMarketData.setSymbol(val);
						System.out.println("Value found:" + val);
					} else if (key.equals("bank")) {
						newMarketData.setBank(val);
						System.out.println("Bank is" + val);
					} else {
						System.out.println("Price is" + val);
						BigDecimal valPrice = new BigDecimal(Double.parseDouble(val));
						newMarketData.setPrice(valPrice);
						System.out.println("Price is" + valPrice);
					}
				}
			} // end for
			newMarketDataList.add(newMarketData); // storing new MarketObjects
													// into Arraylist to write
													// into csv later
			System.out.println(newMarketData.getSymbol() + "," + newMarketData.getBank() + ","
					+ newMarketData.getPrice() + "," + newMarketData.getRoundTripTime());
			// FileUtils fu=new FileUtils();
			// fu.saveFile(newMarketData);
		} // end loop after sending all the market Data to Server
			// write the list of market objects to csv file
			FileUtils fu=new FileUtils();
			fu.writeArrayListToCsv(newMarketDataList);

	} // end method start client

	public void stopClient() throws Exception {
		client.stop();

	}

	// method to generate random Bigdecimal number
	public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public void csvWriter(String whatever) {

	}

	// testing only main method as cleint
	public static void main(String[] args) throws Exception {

		HTTPclient client = new HTTPclient();
		client.startClient();
		client.stopClient();

	}

}
