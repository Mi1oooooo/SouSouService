package com.kgc.kh76.soso.entity;

public class ConsumInfo {
	private String cardNumber;		// [卡号]:String
	private String type;					// [消费类型：通话、发短信、上网]: String
	private int consumData;		// [消费数据 通话:分钟 发短信:条 上网:MB]: int
	
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
