package com.threesixty.client;

import java.io.FileWriter;
import java.io.IOException;

// Source example: https://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/

/* Requirements:
 * Step 1: When httpresponse received, parse it into CSV values. 
 * 			Step1 has to be done within the HTTPclient class. and the method for csvWriter class can be called in  
 * Step 2: Write into file
 * 
 * Problem Statement: How to parse the textual HTTP response into some object that represents it so that it would be
 * easy to pass into
 * */

public class CsvFileWriter {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "symbol, bank, currency, roundtripTime";

	public static void writeCsvFile(String fileName) {
		// create new market objects which is already created in HttpClient
		FileWriter fileWriter = null;

		// Write CSV File header
		try {
			fileWriter = new FileWriter(fileName);

			// Write CSV file header
			fileWriter.append(FILE_HEADER.toString());

			fileWriter.append(NEW_LINE_SEPARATOR);
			try{
				fileWriter.append("Somthing");
			}catch(IOException e){
				System.out.println("Error in CSVFileWriter");
			}
		} catch (Exception e) {
			System.out.println("Error in CSvFileWriter!!!");
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();

			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		} // end finally

	} // end method CsvFileWriter
}// end class CsvFileWriter
