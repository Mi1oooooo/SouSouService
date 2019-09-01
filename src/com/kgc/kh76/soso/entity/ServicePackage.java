package com.kgc.kh76.soso.entity;
/**
 * 
 * @author Administrator
 *
 */
public abstract class ServicePackage {
	private double price;						//[套餐月资费(元)]:double

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public ServicePackage() {
		// TODO Auto-generated constructor stub
	}
	
	public ServicePackage(double price) {
		super();
		this.price = price;
	}

	//[显示套餐数据]:void
	public abstract void showInfo();
	
}
