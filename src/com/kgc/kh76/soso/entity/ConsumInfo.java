package com.kgc.kh76.soso.entity;

public class ConsumInfo {
	private String cardNumber;		// [����]:String
	private String type;					// [�������ͣ�ͨ���������š�����]: String
	private int consumData;		// [�������� ͨ��:���� ������:�� ����:MB]: int
	
	public ConsumInfo() {
		// TODO Auto-generated constructor stub
	}

	public ConsumInfo(String cardNumber, String type, int consumData) {
		this.cardNumber = cardNumber;
		this.type = type;
		this.consumData = consumData;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getConsumData() {
		return consumData;
	}

	public void setConsumData(int consumData) {
		this.consumData = consumData;
	}
	
	
}
