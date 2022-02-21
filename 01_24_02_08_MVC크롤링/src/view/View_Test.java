package view;

import java.util.ArrayList;

import model.Mv_VO;
import model.Users_VO;

public class View_Test {

	public static void main(String[] args) {
		
//		System.out.println("\t|");
//		System.out.println("--------");
//		System.out.println("--------".getBytes().length);
//		System.out.println("극장판 안녕 자두야: 제주도의 ");
//		System.out.println("극장판 안녕 자두야: 제주도의 ".getBytes().length);
//		System.out.println("극장판 안녕 자두야: 제주도의 ".length());
//		
			
		View v = new View();
		
		Users_VO vo = new Users_VO();
		vo.setuName("테스트용");
		
		ArrayList<Mv_VO> datas = new ArrayList<Mv_VO>();
		
		Mv_VO vo2 = new Mv_VO();
		//1
		/*
		v.menuLogin();
		
		v.menuOut();
		
		v.login();
		
		v.loginSucc(vo);
		
		v.loginFail();
		
		v.createUser();
		
		v.createUserSucc(vo);
		
		v.createUserFail(1);
		v.createUserFail(2);
		
		//2
		v.menuService();
		
		v.logout(vo);
		*/
		v.showMVList(datas);
		
		v.menuBuy();
		
		v.selectMV();
		
	
		v.showMVInfo(vo2);
		
		v.buySucc(vo, vo2);
		
		for(int i = 1 ; i < 5 ; i++)
			v.buyFail(i);
		
		v.buyAfter();
		
		
		//3
		v.menuUser(vo);
		
		v.inputPoint();
		
		v.resultPoint(vo);
		
		v.menuInfo();
		
		v.inputName();
		
		v.inputPW();
		
		v.inputAge();
		
		v.resultInfo(vo);
		
		v.checkQuit();
		
		v.printQuit(vo);
		
		
		//4
		v.menuAdmin();
		
		v.inputPK();
		
		v.inputStock(vo2);
		
		v.resultStock(true);
		v.resultStock(false);
		
		v.inputPrice(vo2);
		
		v.resultPrice(true);
		v.resultPrice(false);
		
		ArrayList<Users_VO> datas2 = new ArrayList<Users_VO>();
		datas2.add(vo);
		v.showUserList(datas2);
		
		
		
		
		
	}
}
