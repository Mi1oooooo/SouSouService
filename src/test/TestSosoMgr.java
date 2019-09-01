package test;

import org.junit.Test;

import com.kgc.kh76.soso.mgr.SoSoMgr;

public class TestSosoMgr {
	//@Test注解可理解成main()程序入口
	@Test
	public void test() {
		//创建sosoMgr
		SoSoMgr mgr = new SoSoMgr();
		mgr.mainMenu();
	}

}
