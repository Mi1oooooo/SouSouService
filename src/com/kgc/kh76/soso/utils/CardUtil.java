package com.kgc.kh76.soso.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.kgc.kh76.soso.entity.ConsumInfo;
import com.kgc.kh76.soso.entity.MobileCard;
import com.kgc.kh76.soso.entity.NetPackage;
import com.kgc.kh76.soso.entity.Scene;
import com.kgc.kh76.soso.entity.ServicePackage;
import com.kgc.kh76.soso.entity.SuperPackage;
import com.kgc.kh76.soso.entity.TalkPackage;

public class CardUtil {
	//cards [定义所有手机卡的列表]:Map<String, MobileCard> 
	//consumInfos [定义所有手机卡消费记录的列表]:Map<String, List<ConsumInfo>> 
	//scenes [定义手机使用场景列表]:List<Scene>
	
	Map<String,MobileCard> cards = new HashMap<String,MobileCard>();
	
	Map<String,List<ConsumInfo>> consumInfos = new HashMap<String,List<ConsumInfo>>();
	
	List<Scene> scenes = new ArrayList<Scene>();
	
	
	//(1) init() [初始化手机用户] : void 
	public void init(){
		MobileCard card1 = new MobileCard("13912345678", "刀少", "123456", new TalkPackage(), 60, 100, 0, 0, 0);
		MobileCard card2 = new MobileCard("13912345677", "小胖", "123456", new NetPackage(), 70, 30, 0, 0, 0);
		MobileCard card3 = new MobileCard("13912345679", "张五", "123456", new SuperPackage(), 90, 10, 0, 0, 0);
		
		cards.put(card1.getCardNumber(), card1);
		cards.put(card2.getCardNumber(), card2);
		cards.put(card3.getCardNumber(), card3);
		
		saveXml();
	}
	
	//解析xml文件
	public void praseXml(){
		SAXReader reader = new SAXReader();
		//通过DocumentHelper工厂类创建Document文档
		Document doc = DocumentHelper.createDocument();
		try {
			doc = reader.read("MobileCard.xml");
			Element root = doc.getRootElement();
			List<Element> elements = root.elements();
			for (Element element : elements) {
				String cardNumber = element.elementText("cardNumber");
				String userName = element.elementText("userName");
				String passWord = element.elementText("passWord");
				String servicePackage = element.elementText("servicePackage");
				String consumAmount = element.elementText("consumAmount");
				String money = element.elementText("money");
				String realTalkTime = element.elementText("realTalkTime");
				String realSMSCount = element.elementText("realSMSCount");
				String realFlow = element.elementText("realFlow");
				
				int packageId = Integer.valueOf(servicePackage);
				
				MobileCard card = new MobileCard(cardNumber, userName, passWord, createPack(packageId), Double.parseDouble(consumAmount), 
						Double.parseDouble(money),Integer.parseInt(realTalkTime), Integer.parseInt(realSMSCount), Integer.parseInt(realFlow));
				
				cards.put(card.getCardNumber(), card);
			}
			
			
		} catch (DocumentException e) {
			init();
		}
	}
	
	//将集合数据保存到xml中
	public void saveXml(){
		//通过DocumentHelper工厂类创建Document文档
		Document doc = DocumentHelper.createDocument();
		//根据Document文档创建根节点
		Element root = doc.addElement("phones");
		
		//循环遍历maps集合对象
		Collection<MobileCard> mobilesCards = cards.values();
		for (MobileCard m : mobilesCards) {
			phoneXml(root, m);	
		}
		opeXml(doc);
	}

	//保存XML文件的方法
	public void opeXml(Document doc) {
		//创建xmlWriter
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream("MobileCard.xml"),format);
			xmlWriter.write(doc);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				xmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//添加卡信息到doc的方法
	public void phoneXml(Element root, MobileCard m) {
		Element elePhone = root.addElement("phone");
		Element eleCardNumber = elePhone.addElement("cardNumber");
		Element eleUserName = elePhone.addElement("userName");
		Element elePassWord = elePhone.addElement("passWord");
		Element eleServicePackage = elePhone.addElement("servicePackage");
		Element eleConsumAmount = elePhone.addElement("consumAmount");
		Element eleMoney = elePhone.addElement("money");
		Element eleRealTalkTime = elePhone.addElement("realTalkTime");
		Element eleRealSMSCount = elePhone.addElement("realSMSCount");
		Element eleRealFlow = elePhone.addElement("realFlow");
		
		
		//根据生成的标签元素进行赋对应的值
		eleCardNumber.addText(m.getCardNumber());
		eleUserName.addText(m.getUserName());
		elePassWord.addText(m.getPassWord());
		String pack = "";
		if(m.getServicePackage() instanceof TalkPackage){
			pack = "1";
		}else if(m.getServicePackage() instanceof NetPackage){
			pack = "2";
		}else{
			pack = "3";
		}
		eleServicePackage.addText(pack);
		eleConsumAmount.addText(m.getConsumAmount()+"");
		eleMoney.addText(m.getMoney()+"");
		eleRealTalkTime.addText(m.getRealTalkTime()+"");
		eleRealSMSCount.addText(m.getRealSMSCount()+"");
		eleRealFlow.addText(m.getRealFlow()+"");
	}
	
	
	//(2) isExistCard(number:String, passWord:String) [判断是否存在此卡用户] : boolean
	public boolean isExistCard(String number, String passWord){
		boolean flag = false;
		if(isExistCard(number)){
			if(cards.get(number).getPassWord().equals(passWord)){
				System.out.println("登陆成功！");
				flag = true;
			}else{
				System.out.println("密码错误！");
			}
		}else{
			System.out.println("用户不存在！");
		}
		return flag;
	}
	
	
	//(3) getNewNumbers(count:int) [指定随机生成几个手机号列表]: String[] 
	public String[] getNewNumbers(int count){
		String[] numbers = new String[count];
		for(int i = 0;i<count;){
			String number = creatNumber();
			if(isExistCard(number)){
				continue;
			}
			numbers[i] = number;
			i++;
		}
		return numbers ;
	}
	
	
	//(4) createNumber() [生成以 139 开头 的随机手机卡号]: String 
	public String creatNumber(){
		Random random = new Random();
		StringBuffer number = new StringBuffer("139");
		for(int i = 0; i<8 ;i++){
			int num = random.nextInt(10);
			number.append(num);
		}
		return number.toString();
	}
	
	
	//(5) createPack(packageId:int)[使用多态获取套餐类型的方法]: ServicePackage 
	public ServicePackage createPack(int packageId){
		ServicePackage[] newSp = {new TalkPackage(),new NetPackage(),new SuperPackage()};
		return newSp[packageId-1];
	}
	
	
	//(6) addCard(card:MobileCard)[保存新卡]: void
	public void addCard(MobileCard card){
		cards.put(card.getCardNumber(), card);
		System.out.println("注册成功！");
		card.showMesg();
		saveXml();
	}
	
	
	//(7) showAmountDetail(moblieNumber:String)[本月账单查询]: void 
	public void showAmountDetail(String mobileNumber){
		MobileCard ca =cards.get(mobileNumber);
		System.out.println("您的卡号："+mobileNumber+"当月账单：");
		System.out.println("套餐资费："+ca.getServicePackage().getPrice()+"元");
		System.out.println("合计："+ca.getConsumAmount()+"元");
		System.out.println("账户余额："+ca.getMoney()+"元");
	}
	
	
	//(8) showRemainDetail(moblieNumber:String)[套餐余量查询]: void
	public void showRemainDetail(String mobileNumber){
		MobileCard ca =cards.get(mobileNumber);
		ServicePackage sp = ca.getServicePackage();
		int talkTimeRest = 0; 
		int smsCountRest = 0; 
		int flowRest = 0; 
		if(sp instanceof TalkPackage){
			TalkPackage newSp = (TalkPackage)sp;
			talkTimeRest = newSp.getTalkTime()-ca.getRealTalkTime();
			smsCountRest = newSp.getSmsCount()-ca.getRealSMSCount();
		}else if(sp instanceof SuperPackage){
			SuperPackage newSp = (SuperPackage)sp;
			talkTimeRest = newSp.getTalkTime()-ca.getRealTalkTime();
			smsCountRest = newSp.getSmsCount()-ca.getRealSMSCount();
			flowRest = newSp.getFlow()-ca.getRealFlow();
		}else{
			NetPackage newSp = (NetPackage)sp;
			flowRest = newSp.getFlow()-ca.getRealFlow();
		}
		System.out.println("您的卡号："+mobileNumber+"套餐内剩余：");
		System.out.println("通话时长："+(talkTimeRest<0 ? 0 : talkTimeRest)+"分钟");
		System.out.println("短信条数："+(smsCountRest<0 ? 0 : smsCountRest)+"条");
		System.out.println("上网流量："+(((flowRest<0 ? 0 : flowRest) %1024) ==0 ? ((flowRest<0 ? 0 : flowRest) /1024) : (flowRest<0 ? 0 : flowRest)) 
									+(((flowRest<0 ? 0 : flowRest) %1024) ==0 ? "GB" : "MB"));
	}
	
	
	//(9) initConsumInfos()[初始化消费信息表]: void 
	public void initConsumInfos(){
		ConsumInfo cons1 = new ConsumInfo("13912345678", "通话", 60);
		ConsumInfo cons2 = new ConsumInfo("13912345678", "通话", 90);
		List<ConsumInfo> list1 = new ArrayList<ConsumInfo>();
		list1.add(cons1);
		list1.add(cons2);
		consumInfos.put(cons1.getCardNumber(), list1);
		
	}
	
	
	//(10)printConsumInfo(moblieNumber:String)[打印消费详单]: void 
	public void printConsumInfo(String moblieNumber){
		if(consumInfos.containsKey(moblieNumber)){
			List<ConsumInfo> arrList = consumInfos.get(moblieNumber);
			System.out.println("序号\t"+"类型\t"+"数据(通话（分钟）/上网（MB）/短信（条）)");
			for(int i =0;i<arrList.size();i++){
				ConsumInfo cons = arrList.get(i);
				System.out.println((i+1)+"\t"+cons.getType()+"\t"+cons.getConsumData());
			}
		}else{
			System.out.println("对不起。不存在此号码的消费记录，不能打印！");
		}
		
	}
	
	
	//(11)changingPack(moblieNumber:String , packNum:int)[套餐变更的方法]: void 
	public void changingPack(String moblieNumber , int packNum){
		MobileCard card = cards.get(moblieNumber);
		ServicePackage[] newSp = {new TalkPackage(),new NetPackage(),new SuperPackage()};
		if(card.getServicePackage().getClass() == newSp[packNum-1].getClass() ){
			System.out.println("对不起，您已经是该套餐用户，无需跟换套餐!");
			return;
		}else{
			if(card.getMoney() < newSp[packNum-1].getPrice() ){
				System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值以后再办理跟换套餐业务！");
				return;
			}else{
				card.setServicePackage(newSp[packNum-1]);
				card.setConsumAmount(card.getConsumAmount()+newSp[packNum-1].getPrice());
				card.setMoney(card.getMoney()-newSp[packNum-1].getPrice());
				card.setRealFlow(0);
				card.setRealSMSCount(0);
				card.setRealTalkTime(0);
				System.out.print("套餐跟换成功，");
				card.getServicePackage().showInfo();
			}
		}
	}
	
	
	//(12)delCard(moblieNumber:String)[指定卡号办理退网]: void
	public void delCard(String moblieNumber){
		String num = cards.get(moblieNumber).getCardNumber();
		cards.remove(moblieNumber);
		consumInfos.remove(moblieNumber);
		System.out.println("卡号"+num+"办理退网成功！");
		saveXml();
		System.out.println("谢谢使用！");
	}
	
	
	//(13)isExistCard(number:String )[判断该手机卡号是否已经注册]: boolean 
	public boolean isExistCard(String number){
		if(cards.containsKey(number)){
			return true;
		}else{
			return false;			
		}
	}
	
	
	//(14)ininScene()[初始化使用场景的信息]: void 
	public void ininScene(){
		Scene scene1 = new Scene("通话", 90," 问候客户，谁知其如此难缠 通话 90 分钟");
		Scene scene2 = new Scene("通话", 30, "询问妈妈身体状况 本地通话 30 分钟");
		Scene scene3 = new Scene("短信", 5, "参与环境保护实施方案问卷调查 发送短信 5 条");
		Scene scene4 = new Scene("短信", 50, "通知朋友手机换号，发送短信 50 条");
		Scene scene5 = new Scene("上网", 1024, "和女友微信视频聊天 使用流量 1G");
		Scene scene6 = new Scene("上网", 2048, "晚上手机在线看韩剧，不留神睡着啦！ 使用流量 2G");
		scenes.add(scene1);
		scenes.add(scene2);
		scenes.add(scene3);
		scenes.add(scene4);
		scenes.add(scene5);
		scenes.add(scene6);
	}
	
	
	//(15)useSoso(moblieNumber:String)[使用嗖嗖]: void 
	public void useSoso(String mobileNumber){
		Random rand = new Random();
		int sceneNum = rand.nextInt(6);
		Scene scene = scenes.get(sceneNum);
		
		
	}
	
	
	//(16)addConsumInfo(moblieNumber:String, consumInfo:ConsumInfo)[添加指定卡的消费记录]: void 
	public void addConsumInfo(String moblieNumber, ConsumInfo consumInfo){
		List<ConsumInfo> cons = consumInfos.get(moblieNumber);
		cons.add(consumInfo);
	}
	
	
	//(17)rechargeMoney(moblieNumber:String , recharge:double)[为指定手机卡号充值]: void 
	public void rechargeMoney(String moblieNumber ,double recharge){
		if(isExistCard(moblieNumber)){
			MobileCard card = cards.get(moblieNumber);
			card.setMoney(card.getMoney()+recharge);
			System.out.println("充值成功，当前余额为"+card.getMoney()+"元");
		}else{
			System.out.println("输入的号码不存在，请重新输入。");
		}
	}
	
	
	//(18)showDescription()[显示资费说明的方法]: void
	public void showDescription(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream("套餐资费说明.txt");
			isr = new InputStreamReader(is,"GBK");
			br = new BufferedReader(isr);
			
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
