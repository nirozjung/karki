package com.threesixty.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.threesixty.client.Market;

/**
 * 
 * @author nirozjungkarki
 *
 */
public class FileUtils {
	
	// print marketdata in CSV style
	public void printLine(Market marketObj, PrintWriter pW) {
		pW.println(marketObj.getSymbol()+","+marketObj.getBank()+","+marketObj.getPrice()+","+marketObj.getRoundTripTime());
	}
	//create a file
	public PrintWriter createPrintWriter(File file) {
		PrintWriter print = null;
		try {
			print = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return print;
	}
	// save file
	public void saveFile(Market marketObj, String fileName, int i) {
		File newMarketData = new File("marketData/"+i+".txt");
		if(newMarketData.exists())
			newMarketData.delete();
		
		PrintWriter newMkData = createPrintWriter(newMarketData);
		printLine(marketObj, newMkData);
	}
	
}