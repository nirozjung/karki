package com.threesixty.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * @author nirozjungkarki
 *
 */
public class FileUtils {

	// print marketdata in CSV style
	public void printLine(Market marketObj, FileWriter pW) throws IOException {

		pW.append(String.valueOf(marketObj.getSymbol()) + "," + String.valueOf(marketObj.getBank()) + ","
				+ String.valueOf(marketObj.getPrice()) + "," + String.valueOf(marketObj.getRoundTripTime()));
		System.out.println("printing csv now");
	}

	// create a file
	public FileWriter createPrintWriter(File file) throws IOException {
		FileWriter print = null;
		try {
			print = new FileWriter(file);
			System.out.println("creating printwriter");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return print;
	}

	// save file
	public void saveFile(Market marketObj) throws IOException {
		// File newMarketData = new File("marketData/"+i+".txt");
		File newMarketData = new File(System.getProperty("user.home") + "/market.csv");
		// String fileName=System.getProperty("user.home")+"/student.csv";
		// if(newMarketData.exists())
		// newMarketData.delete();

		FileWriter newMkData = createPrintWriter(newMarketData);
		printLine(marketObj, newMkData);
		System.out.println("Successfully out of FileUtils");
	}

	// complete new method that prints the arraylist in csv style
	public void writeArrayListToCsv(ArrayList<Market> mkd){
		FileWriter fileWriter=null;
		try {
			File filename = new File(System.getProperty("user.home") + "/newmarket.csv"); // hard coding the filename and path
			fileWriter=new FileWriter(filename);
			
			for(Market mk:mkd){
				fileWriter.append(String.valueOf(mk.getSymbol()) + "," + String.valueOf(mk.getBank()) + ","
						+ String.valueOf(mk.getPrice()) + "," + String.valueOf(mk.getRoundTripTime()));
			}// end for
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error in writeArrayListToCsv !!!");
			e.printStackTrace();
		}
		
		
	}// end method writeArrayListToCsv

}