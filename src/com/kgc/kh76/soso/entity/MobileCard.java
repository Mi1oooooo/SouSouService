package com.kgc.kh76.soso.entity;

public class MobileCard {
	private String cardNumber; 			// [卡号]:String
	private String userName; 				// [用户名]:String
	private String passWord; 				// [密码]:String
	private ServicePackage servicePackage; // [所属套餐]:ServicePackage
	private double consumAmount; 	// [当月消费金额 ]: double
	private double money; 				// [账户余额 ]:double
	private int realTalkTime; 				// [当月实际通话时长 ]:int
	private int realSMSCount;				 // [当月实际发送短信条数]:int
	private int realFlow;					 // [当月实际上网流量]:int
	
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
	 * [显示卡号信息的方法] : void
	 */
	public void showMesg(){
		System.out.println("卡号：" + cardNumber + ", 用户名：" + userName  + ", 当前余额：" + money+"元");
		servicePackage.showInfo();
	}	
	
	
	
}
