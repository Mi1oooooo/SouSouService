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
	//cards [���������ֻ������б�]:Map<String, MobileCard> 
	//consumInfos [���������ֻ������Ѽ�¼���б�]:Map<String, List<ConsumInfo>> 
	//scenes [�����ֻ�ʹ�ó����б�]:List<Scene>
	
	Map<String,MobileCard> cards = new HashMap<String,MobileCard>();
	
	Map<String,List<ConsumInfo>> consumInfos = new HashMap<String,List<ConsumInfo>>();
	
	List<Scene> scenes = new ArrayList<Scene>();
	
	
	//(1) init() [��ʼ���ֻ��û�] : void 
	public void init(){
		MobileCard card1 = new MobileCard("13912345678", "����", "123456", new TalkPackage(), 60, 100, 0, 0, 0);
		MobileCard card2 = new MobileCard("13912345677", "С��", "123456", new NetPackage(), 70, 30, 0, 0, 0);
		MobileCard card3 = new MobileCard("13912345679", "����", "123456", new SuperPackage(), 90, 10, 0, 0, 0);
		
		cards.put(card1.getCardNumber(), card1);
		cards.put(card2.getCardNumber(), card2);
		cards.put(card3.getCardNumber(), card3);
		
		saveXml();
	}
	
	//����xml�ļ�
	public void praseXml(){
		SAXReader reader = new SAXReader();
		//ͨ��DocumentHelper�����ഴ��Document�ĵ�
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
	
	//���������ݱ��浽xml��
	public void saveXml(){
		//ͨ��DocumentHelper�����ഴ��Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		//����Document�ĵ��������ڵ�
		Element root = doc.addElement("phones");
		
		//ѭ������maps���϶���
		Collection<MobileCard> mobilesCards = cards.values();
		for (MobileCard m : mobilesCards) {
			phoneXml(root, m);	
		}
		opeXml(doc);
	}

	//����XML�ļ��ķ���
	public void opeXml(Document doc) {
		//����xmlWriter
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

	//��ӿ���Ϣ��doc�ķ���
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
		
		
		//�������ɵı�ǩԪ�ؽ��и���Ӧ��ֵ
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
	
	
	//(2) isExistCard(number:String, passWord:String) [�ж��Ƿ���ڴ˿��û�] : boolean
	public boolean isExistCard(String number, String passWord){
		boolean flag = false;
		if(isExistCard(number)){
			if(cards.get(number).getPassWord().equals(passWord)){
				System.out.println("��½�ɹ���");
				flag = true;
			}else{
				System.out.println("�������");
			}
		}else{
			System.out.println("�û������ڣ�");
		}
		return flag;
	}
	
	
	//(3) getNewNumbers(count:int) [ָ��������ɼ����ֻ����б�]: String[] 
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
	
	
	//(4) createNumber() [������ 139 ��ͷ ������ֻ�����]: String 
	public String creatNumber(){
		Random random = new Random();
		StringBuffer number = new StringBuffer("139");
		for(int i = 0; i<8 ;i++){
			int num = random.nextInt(10);
			number.append(num);
		}
		return number.toString();
	}
	
	
	//(5) createPack(packageId:int)[ʹ�ö�̬��ȡ�ײ����͵ķ���]: ServicePackage 
	public ServicePackage createPack(int packageId){
		ServicePackage[] newSp = {new TalkPackage(),new NetPackage(),new SuperPackage()};
		return newSp[packageId-1];
	}
	
	
	//(6) addCard(card:MobileCard)[�����¿�]: void
	public void addCard(MobileCard card){
		cards.put(card.getCardNumber(), card);
		System.out.println("ע��ɹ���");
		card.showMesg();
		saveXml();
	}
	
	
	//(7) showAmountDetail(moblieNumber:String)[�����˵���ѯ]: void 
	public void showAmountDetail(String mobileNumber){
		MobileCard ca =cards.get(mobileNumber);
		System.out.println("���Ŀ��ţ�"+mobileNumber+"�����˵���");
		System.out.println("�ײ��ʷѣ�"+ca.getServicePackage().getPrice()+"Ԫ");
		System.out.println("�ϼƣ�"+ca.getConsumAmount()+"Ԫ");
		System.out.println("�˻���"+ca.getMoney()+"Ԫ");
	}
	
	
	//(8) showRemainDetail(moblieNumber:String)[�ײ�������ѯ]: void
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
		System.out.println("���Ŀ��ţ�"+mobileNumber+"�ײ���ʣ�ࣺ");
		System.out.println("ͨ��ʱ����"+(talkTimeRest<0 ? 0 : talkTimeRest)+"����");
		System.out.println("����������"+(smsCountRest<0 ? 0 : smsCountRest)+"��");
		System.out.println("����������"+(((flowRest<0 ? 0 : flowRest) %1024) ==0 ? ((flowRest<0 ? 0 : flowRest) /1024) : (flowRest<0 ? 0 : flowRest)) 
									+(((flowRest<0 ? 0 : flowRest) %1024) ==0 ? "GB" : "MB"));
	}
	
	
	//(9) initConsumInfos()[��ʼ��������Ϣ��]: void 
	public void initConsumInfos(){
		ConsumInfo cons1 = new ConsumInfo("13912345678", "ͨ��", 60);
		ConsumInfo cons2 = new ConsumInfo("13912345678", "ͨ��", 90);
		List<ConsumInfo> list1 = new ArrayList<ConsumInfo>();
		list1.add(cons1);
		list1.add(cons2);
		consumInfos.put(cons1.getCardNumber(), list1);
		
	}
	
	
	//(10)printConsumInfo(moblieNumber:String)[��ӡ�����굥]: void 
	public void printConsumInfo(String moblieNumber){
		if(consumInfos.containsKey(moblieNumber)){
			List<ConsumInfo> arrList = consumInfos.get(moblieNumber);
			System.out.println("���\t"+"����\t"+"����(ͨ�������ӣ�/������MB��/���ţ�����)");
			for(int i =0;i<arrList.size();i++){
				ConsumInfo cons = arrList.get(i);
				System.out.println((i+1)+"\t"+cons.getType()+"\t"+cons.getConsumData());
			}
		}else{
			System.out.println("�Բ��𡣲����ڴ˺�������Ѽ�¼�����ܴ�ӡ��");
		}
		
	}
	
	
	//(11)changingPack(moblieNumber:String , packNum:int)[�ײͱ���ķ���]: void 
	public void changingPack(String moblieNumber , int packNum){
		MobileCard card = cards.get(moblieNumber);
		ServicePackage[] newSp = {new TalkPackage(),new NetPackage(),new SuperPackage()};
		if(card.getServicePackage().getClass() == newSp[packNum-1].getClass() ){
			System.out.println("�Բ������Ѿ��Ǹ��ײ��û�����������ײ�!");
			return;
		}else{
			if(card.getMoney() < newSp[packNum-1].getPrice() ){
				System.out.println("�Բ�������������֧�����ײͱ����ʷѣ����ֵ�Ժ��ٰ�������ײ�ҵ��");
				return;
			}else{
				card.setServicePackage(newSp[packNum-1]);
				card.setConsumAmount(card.getConsumAmount()+newSp[packNum-1].getPrice());
				card.setMoney(card.getMoney()-newSp[packNum-1].getPrice());
				card.setRealFlow(0);
				card.setRealSMSCount(0);
				card.setRealTalkTime(0);
				System.out.print("�ײ͸����ɹ���");
				card.getServicePackage().showInfo();
			}
		}
	}
	
	
	//(12)delCard(moblieNumber:String)[ָ�����Ű�������]: void
	public void delCard(String moblieNumber){
		String num = cards.get(moblieNumber).getCardNumber();
		cards.remove(moblieNumber);
		consumInfos.remove(moblieNumber);
		System.out.println("����"+num+"���������ɹ���");
		saveXml();
		System.out.println("ллʹ�ã�");
	}
	
	
	//(13)isExistCard(number:String )[�жϸ��ֻ������Ƿ��Ѿ�ע��]: boolean 
	public boolean isExistCard(String number){
		if(cards.containsKey(number)){
			return true;
		}else{
			return false;			
		}
	}
	
	
	//(14)ininScene()[��ʼ��ʹ�ó�������Ϣ]: void 
	public void ininScene(){
		Scene scene1 = new Scene("ͨ��", 90," �ʺ�ͻ���˭֪������Ѳ� ͨ�� 90 ����");
		Scene scene2 = new Scene("ͨ��", 30, "ѯ����������״�� ����ͨ�� 30 ����");
		Scene scene3 = new Scene("����", 5, "���뻷������ʵʩ�����ʾ���� ���Ͷ��� 5 ��");
		Scene scene4 = new Scene("����", 50, "֪ͨ�����ֻ����ţ����Ͷ��� 50 ��");
		Scene scene5 = new Scene("����", 1024, "��Ů��΢����Ƶ���� ʹ������ 1G");
		Scene scene6 = new Scene("����", 2048, "�����ֻ����߿����磬������˯������ ʹ������ 2G");
		scenes.add(scene1);
		scenes.add(scene2);
		scenes.add(scene3);
		scenes.add(scene4);
		scenes.add(scene5);
		scenes.add(scene6);
	}
	
	
	//(15)useSoso(moblieNumber:String)[ʹ����]: void 
	public void useSoso(String mobileNumber){
		Random rand = new Random();
		int sceneNum = rand.nextInt(6);
		Scene scene = scenes.get(sceneNum);
		
		
	}
	
	
	//(16)addConsumInfo(moblieNumber:String, consumInfo:ConsumInfo)[���ָ���������Ѽ�¼]: void 
	public void addConsumInfo(String moblieNumber, ConsumInfo consumInfo){
		List<ConsumInfo> cons = consumInfos.get(moblieNumber);
		cons.add(consumInfo);
	}
	
	
	//(17)rechargeMoney(moblieNumber:String , recharge:double)[Ϊָ���ֻ����ų�ֵ]: void 
	public void rechargeMoney(String moblieNumber ,double recharge){
		if(isExistCard(moblieNumber)){
			MobileCard card = cards.get(moblieNumber);
			card.setMoney(card.getMoney()+recharge);
			System.out.println("��ֵ�ɹ�����ǰ���Ϊ"+card.getMoney()+"Ԫ");
		}else{
			System.out.println("����ĺ��벻���ڣ����������롣");
		}
	}
	
	
	//(18)showDescription()[��ʾ�ʷ�˵���ķ���]: void
	public void showDescription(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream("�ײ��ʷ�˵��.txt");
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
