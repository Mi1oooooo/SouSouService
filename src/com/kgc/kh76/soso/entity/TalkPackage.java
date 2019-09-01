package com.kgc.kh76.soso.entity;

import com.kgc.kh76.soso.service.CallService;
import com.kgc.kh76.soso.service.SendService;

public class TalkPackage extends ServicePackage implements CallService,SendService{
	private int talkTime;		//[通话时长]:int 
	private int smsCount;		//[短信条数]:int
	
	public TalkPackage() {
		//初始化话痨套餐
		this.talkTime = 500;
		this.smsCount=30;
		super.setPrice(58);
	}
	
	public TalkPackage(double price, int talkTime, int smsCount) {
		super(price);
		this.talkTime = talkTime;
		this.smsCount = smsCount;
	}


	public int getTalkTime() {
		return talkTime;
	}


	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}


	public int getSmsCount() {
		return smsCount;
	}


	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}


	public  void showInfo(){
		System.out.println("话痨套餐:通话时长为"+this.getTalkTime()+"分钟/月，短信条数为"+this.getSmsCount()+"条/月，资费为"+this.getPrice()+"元/月");
	}

	@Override
	public int send(int count, MobileCard card) {
		return 0;
	}

	@Override
	public int call(int minCount, MobileCard card) {
		return 0;
	}
}
