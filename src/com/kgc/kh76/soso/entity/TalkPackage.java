package com.kgc.kh76.soso.entity;

import com.kgc.kh76.soso.service.CallService;
import com.kgc.kh76.soso.service.SendService;

public class TalkPackage extends ServicePackage implements CallService,SendService{
	private int talkTime;		//[ͨ��ʱ��]:int 
	private int smsCount;		//[��������]:int
	
	public TalkPackage() {
		//��ʼ�������ײ�
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
		System.out.println("�����ײ�:ͨ��ʱ��Ϊ"+this.getTalkTime()+"����/�£���������Ϊ"+this.getSmsCount()+"��/�£��ʷ�Ϊ"+this.getPrice()+"Ԫ/��");
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
