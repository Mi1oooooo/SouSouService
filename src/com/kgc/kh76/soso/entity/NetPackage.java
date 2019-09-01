package com.kgc.kh76.soso.entity;

import com.kgc.kh76.soso.service.NetService;

public class NetPackage extends ServicePackage implements NetService {
	private int flow;//[��������(MB)]:int

	public NetPackage() {
		this.flow = 3072;
		super.setPrice(68);
	}
	
	public NetPackage(double price, int flow) {
		super(price);
		this.flow = flow;
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
	public void showInfo() {
		System.out.println("�����ײ�:��������Ϊ"+this.getFlow()+"MB/�£��ʷ�Ϊ"+this.getPrice()+"Ԫ/��");
	}

}
