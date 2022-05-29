package com.ui.model.request;

public class InvoiceModel {
	MaskModel pink;
	MaskModel blue;
	MaskModel yellow;
	MaskModel black;
	Double productCost;
	Double shippingCost;
	Double totalCost;
	String destination;
	public String getDestination() {
		return destination;
	}
	public MaskModel getPink() {
		return pink;
	}
	public void setPink(MaskModel pink) {
		this.pink = pink;
	}
	public MaskModel getBlue() {
		return blue;
	}
	public void setBlue(MaskModel blue) {
		this.blue = blue;
	}
	public MaskModel getYellow() {
		return yellow;
	}
	public void setYellow(MaskModel yellow) {
		this.yellow = yellow;
	}
	public MaskModel getBlack() {
		return black;
	}
	public void setBlack(MaskModel black) {
		this.black = black;
	}
	public Double getProductCost() {
		return productCost;
	}
	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}
	public Double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
}
