package com.threesixty.client;

import java.math.BigDecimal;
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
public class HTTPclient {
	private HttpClient client;

	public void startClient() throws Exception {
		// Instantiate HttpClient
		client = new HttpClient();
		client.start();
		System.out.println("Here! started client!!");

		// create random 10,000 Market data objects and streaming it to jett server
		Market[] market = new Market[10];
		for (int i = 0; i < market.length; i++) {
			
			String randomSymbol = Settings.symbol[new Random().nextInt(Settings.symbol.length)];
			String randomBank = Settings.bank[new Random().nextInt(Settings.bank.length)];

			BigDecimal randomAmount = generateRandomBigDecimalFromRange(
					new BigDecimal(-1.21).setScale(2, BigDecimal.ROUND_HALF_UP),
					new BigDecimal(9999.28).setScale(2, BigDecimal.ROUND_HALF_UP));
			
			// create number of random market objects from the Arraylists in Settings
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
			long roundTripTime = System.nanoTime() - sendtimeStamp;
			
			System.out.print(res.getContentAsString());
			System.out.println("RoundTrip Time:" + roundTripTime / 1E9 + " seconds"+"\n");
			
			String responseServer=res.getContentAsString();
			String[] lines = responseServer.split("\\r?\\n");
					  for (int x=0;x<lines.length;x++)
				        {
				            String[] d = lines[x].split(":");
				            if(d.length == 2)
				            {
				                String key = d[0];
				                String val = d[1];
				                if(key.equals("yyy"))
				                { 
				                    System.out.println("Value found:"+val);
				                }
				            }
				        } // end for
			
		}
	} // end loop

	public void stopClient() throws Exception {
		client.stop();

	}

	// method to generate Bigdecimal random number
	public static BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public String extractCSVValue(String responseServer){
		String[] line = responseServer.split("\\r?\\n");
		for (int x=0;x<line.length;x++)
        {
            String[] d = line[x].split(":");
            if(d.length == 2)
            {
                // String key = d[0];
                String val = d[1];
                responseServer=val;
            } 
        } // end for
		return responseServer;
	}
	// testing only main method as cleint
	public static void main(String[] args) throws Exception {

		
		HTTPclient client = new HTTPclient();
		client.startClient();
		client.stopClient();

	}

}
