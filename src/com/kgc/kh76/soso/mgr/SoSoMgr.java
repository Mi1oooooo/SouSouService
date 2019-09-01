package com.kgc.kh76.soso.mgr;

import java.util.Scanner;

import com.kgc.kh76.soso.entity.MobileCard;
import com.kgc.kh76.soso.entity.ServicePackage;
import com.kgc.kh76.soso.utils.CardUtil;

public class SoSoMgr {
	// ����һ��Scanenr�����������
	Scanner sc = new Scanner(System.in);
	CardUtil util = new CardUtil();
	/**
	 * �ವ����˵�
	 */
	public void mainMenu() {
		//���ó�ʼ���ֻ��������еķ���
		util.praseXml();
		//���ó�ʼ�������������з���
		util.ininScene();
		do {
			System.out.println("\n��ӭʹ�����ƶ�����ҵ��");
			System.out.print("1.�û���¼\t\t");
			System.out.print("2.�û�ע��\t\t");
			System.out.print("3.ʹ����\t\t");
			System.out.print("4.���ѳ�ֵ\t\t");
			System.out.print("5.�ʷ�˵��\t\t");
			System.out.println("6.�˳�ϵͳ\t\t");
			System.out.print("��ѡ��");
			int choose = sc.nextInt();
			switch(choose){
			case	1:
				System.out.println("ִ���û���¼");
				//��֤�ֻ��ź�����
				System.out.print("�������ֻ��ţ�");
				String number = sc.next();
				System.out.print("���������룺");
				String passWord = sc.next();
				//������֤����
				boolean isCorrect = util.isExistCard(number, passWord);
				//��֤ͨ����������˵�����ͨ����ʾ����
				if(isCorrect){
					System.out.println("������ȷ����������˵�");
					secMenu(number,passWord);
				}else{
					System.out.println("���������������");
				}
				break;
			case	2:
				System.out.println("ִ���û�ע��");
				//���ú������ɷ�������һ���������ָ������������
				String[] numbers = util.getNewNumbers(9);
				for (int i =0; i<numbers.length;i++) {
						System.out.print((i+1)+"."+numbers[i]+"\t");
						if((i+1)%3 == 0){
							System.out.println();
						}
				}
				//��ȡ��Ϣ
				System.out.print("��ѡ�񿨺ţ�����1-9����ţ���");
				int chooseNum = sc.nextInt();
				String cardNumber = numbers[chooseNum-1];
				System.out.print("1.�����ײ�  2.�����ײ�  3.�����ײ�  ��ѡ��(���)��");
				int packageId = sc.nextInt();
				ServicePackage sp = util.createPack(packageId);
				System.out.print("������������");
				String name = sc.next();
				System.out.print("���������룺");
				String password = sc.next();
				System.out.print("������Ԥ�滰�ѽ�");
				double money = sc.nextDouble();
				//�жϳ�ֵ����Ƿ�����֧�������ײ��ʷ�
				while(money < sp.getPrice()){
					System.out.print("��Ԥ��Ļ��Ѳ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
					money = sc.nextDouble();
				}			
				//����MobileCard����
				MobileCard newCard = new MobileCard(cardNumber, name, password, sp, sp.getPrice(), money-sp.getPrice(), 0, 0, 0);
				//���ñ��濨����Ϣ����
				util.addCard(newCard);
				break;
			case	3:
				System.out.println("ִ��ʹ����");
				System.out.print("�������ֻ����ţ�");
				String mobileNumber = sc.next();
				util.useSoso(mobileNumber);
				
				break;
			case	4:
				System.out.println("ִ�л��ѳ�ֵ");
				System.out.print("�������ֻ����ţ�");
				String moblieNumber = sc.next();
				System.out.print("�������ֵ���(��ͳ�ֵ50Ԫ)��");
				double recharge = sc.nextDouble();
				if(recharge >= 50){
					util.rechargeMoney(moblieNumber, recharge);
				}else{
					System.out.println("��ֵʧ�ܣ���ͳ�ֵ50Ԫ����");
				}
				break;
			case 5:
				System.out.println("ִ���ʷ�˵��");
				util.showDescription();
				break;
			case	6:
				System.out.println("ллʹ�ã������˳���");
				util.saveXml();
				System.exit(0);
				break;
			default:
				System.out.println("�������󣬳����˳���");
				util.saveXml();
				System.exit(0);
			}
		} while (true);
	}
	
	public void secMenu(String phone,String passWord){
		util.initConsumInfos();
		do{
			System.out.println("**********���ƶ��û��˵�**********");
			System.out.println("1.�����˵���ѯ");
			System.out.println("2.�ײ�������ѯ");
			System.out.println("3.��ӡ�����굥");
			System.out.println("4.�ײͱ��");
			System.out.println("5.��������");
			System.out.print("��ѡ��������1-5������������������һ������");
			int choose = sc.nextInt();
			switch(choose){
			case 1:
				System.out.println("******ִ�б����˵���ѯ����******");
				util.showAmountDetail(phone);
				break;
			case 2:
				System.out.println("******ִ���ײ�������ѯ����******");
				util.showRemainDetail(phone);
				break;
			case 3:
				System.out.println("******ִ�д�ӡ�����굥����******");
				util.printConsumInfo(phone);
				break;
			case 4:
				System.out.println("******ִ���ײͱ������******");
				System.out.print("1.�����ײ�  2.�����ײ�  3.�����ײ�  ��ѡ��(���)��");
				int packNum = sc.nextInt();
				util.changingPack(phone, packNum);
				break;
			case 5:
				System.out.println("******ִ�а�����������******");
				System.out.print("����������ȷ�ϲ�����");
				String ensureDel = sc.next();
				if(ensureDel.equals(passWord)){
					util.delCard(phone);
					System.exit(0);
				}else{
					System.out.println("�������������");
					continue;
				}
				
			default:
				return;
			}
		}while(true);
	}
}
