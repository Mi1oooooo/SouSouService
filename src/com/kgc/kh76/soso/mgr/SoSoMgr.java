package com.kgc.kh76.soso.mgr;

import java.util.Scanner;

import com.kgc.kh76.soso.entity.MobileCard;
import com.kgc.kh76.soso.entity.ServicePackage;
import com.kgc.kh76.soso.utils.CardUtil;

public class SoSoMgr {
	// 创建一个Scanenr键盘输入对象
	Scanner sc = new Scanner(System.in);
	CardUtil util = new CardUtil();
	/**
	 * 嗖嗖的主菜单
	 */
	public void mainMenu() {
		//调用初始化手机集合序列的方法
		util.praseXml();
		//调用初始化场景集合序列方法
		util.ininScene();
		do {
			System.out.println("\n欢迎使用嗖嗖移动大厅业务");
			System.out.print("1.用户登录\t\t");
			System.out.print("2.用户注册\t\t");
			System.out.print("3.使用嗖嗖\t\t");
			System.out.print("4.话费充值\t\t");
			System.out.print("5.资费说明\t\t");
			System.out.println("6.退出系统\t\t");
			System.out.print("请选择：");
			int choose = sc.nextInt();
			switch(choose){
			case	1:
				System.out.println("执行用户登录");
				//验证手机号和密码
				System.out.print("请输入手机号：");
				String number = sc.next();
				System.out.print("请输入密码：");
				String passWord = sc.next();
				//调用验证方法
				boolean isCorrect = util.isExistCard(number, passWord);
				//验证通过进入二级菜单，不通过提示错误
				if(isCorrect){
					System.out.println("输入正确，进入二级菜单");
					secMenu(number,passWord);
				}else{
					System.out.println("输入错误，重新输入");
				}
				break;
			case	2:
				System.out.println("执行用户注册");
				//调用号码生成方法定义一个数组接收指定个数的数组
				String[] numbers = util.getNewNumbers(9);
				for (int i =0; i<numbers.length;i++) {
						System.out.print((i+1)+"."+numbers[i]+"\t");
						if((i+1)%3 == 0){
							System.out.println();
						}
				}
				//获取信息
				System.out.print("请选择卡号（输入1-9的序号）：");
				int chooseNum = sc.nextInt();
				String cardNumber = numbers[chooseNum-1];
				System.out.print("1.话痨套餐  2.网虫套餐  3.超人套餐  请选择(序号)：");
				int packageId = sc.nextInt();
				ServicePackage sp = util.createPack(packageId);
				System.out.print("请输入姓名：");
				String name = sc.next();
				System.out.print("请输入密码：");
				String password = sc.next();
				System.out.print("请输入预存话费金额：");
				double money = sc.nextDouble();
				//判断充值金额是否足以支付本月套餐资费
				while(money < sp.getPrice()){
					System.out.print("您预存的花费不足以支付本月固定套餐资费，请重新充值：");
					money = sc.nextDouble();
				}			
				//生成MobileCard对象
				MobileCard newCard = new MobileCard(cardNumber, name, password, sp, sp.getPrice(), money-sp.getPrice(), 0, 0, 0);
				//调用保存卡号信息方法
				util.addCard(newCard);
				break;
			case	3:
				System.out.println("执行使用嗖嗖");
				System.out.print("请输入手机卡号：");
				String mobileNumber = sc.next();
				util.useSoso(mobileNumber);
				
				break;
			case	4:
				System.out.println("执行话费充值");
				System.out.print("请输入手机卡号：");
				String moblieNumber = sc.next();
				System.out.print("请输入充值金额(最低充值50元)：");
				double recharge = sc.nextDouble();
				if(recharge >= 50){
					util.rechargeMoney(moblieNumber, recharge);
				}else{
					System.out.println("充值失败，最低充值50元！！");
				}
				break;
			case 5:
				System.out.println("执行资费说明");
				util.showDescription();
				break;
			case	6:
				System.out.println("谢谢使用，程序退出！");
				util.saveXml();
				System.exit(0);
				break;
			default:
				System.out.println("输入有误，程序退出！");
				util.saveXml();
				System.exit(0);
			}
		} while (true);
	}
	
	public void secMenu(String phone,String passWord){
		util.initConsumInfos();
		do{
			System.out.println("**********嗖嗖移动用户菜单**********");
			System.out.println("1.本月账单查询");
			System.out.println("2.套餐余量查询");
			System.out.println("3.打印消费详单");
			System.out.println("4.套餐变更");
			System.out.println("5.办理退网");
			System.out.print("请选择（请输入1-5，输入其他键返回上一级）：");
			int choose = sc.nextInt();
			switch(choose){
			case 1:
				System.out.println("******执行本月账单查询功能******");
				util.showAmountDetail(phone);
				break;
			case 2:
				System.out.println("******执行套餐余量查询功能******");
				util.showRemainDetail(phone);
				break;
			case 3:
				System.out.println("******执行打印消费详单功能******");
				util.printConsumInfo(phone);
				break;
			case 4:
				System.out.println("******执行套餐变更功能******");
				System.out.print("1.话痨套餐  2.网虫套餐  3.超人套餐  请选择(序号)：");
				int packNum = sc.nextInt();
				util.changingPack(phone, packNum);
				break;
			case 5:
				System.out.println("******执行办理退网功能******");
				System.out.print("请输入密码确认操作：");
				String ensureDel = sc.next();
				if(ensureDel.equals(passWord)){
					util.delCard(phone);
					System.exit(0);
				}else{
					System.out.println("输入的密码有误！");
					continue;
				}
				
			default:
				return;
			}
		}while(true);
	}
}
