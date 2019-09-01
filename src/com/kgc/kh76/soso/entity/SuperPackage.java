package com.kgc.kh76.soso.entity;

import com.kgc.kh76.soso.service.CallService;
import com.kgc.kh76.soso.service.NetService;
import com.kgc.kh76.soso.service.SendService;

public class SuperPackage extends ServicePackage implements SendService, CallService, NetService {
	private int talkTime; // [ͨ��ʱ��]:int
	private int smsCount; // [��������]:int
	private int flow; // [��������(MB)]:int

	public SuperPackage() {
		this.talkTime = 200;
		this.smsCount = 50;
		this.flow = 1024;
		super.setPrice(78);
	}
	
	
	public SuperPackage(double price, int talkTime, int smsCount, int flow) {
		super(price);
		this.talkTime = talkTime;
		this.smsCount = smsCount;
		this.flow = flow;
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

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	@Override
	public int netPlay(int flow, MobileCard card) {
		return 0;
	}

	@Override
	public int call(int minCount, MobileCard card) {
		return 0;
	}

	@Override
	public int send(int count, MobileCard card) {
		return 0;
	}

	@Override
	public void showInfo() {
		System.out.println("�����ײ�:ͨ��ʱ��Ϊ"+this.getTalkTime()+"����/�£���������Ϊ"+this.getSmsCount()
		+"��/�£���������Ϊ"+this.getFlow()+"MB/�£��ʷ�Ϊ"+this.getPrice()+"Ԫ/��");
	}


}
