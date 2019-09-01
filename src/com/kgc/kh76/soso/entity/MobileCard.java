package com.kgc.kh76.soso.entity;

public class MobileCard {
	private String cardNumber; 			// [����]:String
	private String userName; 				// [�û���]:String
	private String passWord; 				// [����]:String
	private ServicePackage servicePackage; // [�����ײ�]:ServicePackage
	private double consumAmount; 	// [�������ѽ�� ]: double
	private double money; 				// [�˻���� ]:double
	private int realTalkTime; 				// [����ʵ��ͨ��ʱ�� ]:int
	private int realSMSCount;				 // [����ʵ�ʷ��Ͷ�������]:int
	private int realFlow;					 // [����ʵ����������]:int
	
	public MobileCard() {
	}

	public MobileCard(String cardNumber, String userName, String passWord, ServicePackage servicePackage,
			double consumAmount, double money, int realTalkTime, int realSMSCount, int realFlow) {
		super();
		this.cardNumber = cardNumber;
		this.userName = userName;
		this.passWord = passWord;
		this.servicePackage = servicePackage;
		this.consumAmount = consumAmount;
		this.money = money;
		this.realTalkTime = realTalkTime;
		this.realSMSCount = realSMSCount;
		this.realFlow = realFlow;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public ServicePackage getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public double getConsumAmount() {
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getRealTalkTime() {
		return realTalkTime;
	}

	public void setRealTalkTime(int realTalkTime) {
		this.realTalkTime = realTalkTime;
	}

	public int getRealSMSCount() {
		return realSMSCount;
	}

	public void setRealSMSCount(int realSMSCount) {
		this.realSMSCount = realSMSCount;
	}

	public int getRealFlow() {
		return realFlow;
	}

	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
	
	/**
	 * [��ʾ������Ϣ�ķ���] : void
	 */
	public void showMesg(){
		System.out.println("���ţ�" + cardNumber + ", �û�����" + userName  + ", ��ǰ��" + money+"Ԫ");
		servicePackage.showInfo();
	}	
	
	
	
}
