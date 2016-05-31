package com.threesixty.client;

import java.math.BigDecimal;

/**
 * my sample object class to upload
 * @author nirozjungkarki
 *
 */
public class Market {
	String symbol; //for example "EUR/USD"
	String bank;   //source bank who published the price
	BigDecimal price; //current price update
	double roundTripTime;

	public Market() {

	}
	
	public Market(String symbol, String bank, BigDecimal price) {
		super();
		this.symbol = symbol;
		this.bank = bank;
		this.price = price;
	}

	public double getRoundTripTime() {
		return roundTripTime;
	}

	public void setRoundTripTime(double roundTripTime) {
		this.roundTripTime = roundTripTime;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
