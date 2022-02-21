package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Mv_VO;
import model.Users_VO;

public class View {
	Scanner sc = new Scanner(System.in);

	//// 시작화면
	public int menuLogin() {
		int act = -1; // 초기입력값 -1
		while (true) {
			System.out.println("====================널뛰기영화관================");
			System.out.println("1.로그인 2.회원가입 0.종료하기");
			System.out.print(">> ");

			try {
				act = sc.nextInt();

				if (act < 0 || act > 2) { // 0,1,2이외의 입력
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) { // 문자열입력시
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return act;
	}

	//// 종료화면
	public void menuOut() {// 종료시화면
		System.out.println("=============================================");
		System.out.println("이용해주셔서 감사합니다 안녕히가세요.");
		System.out.println("=============================================");
	}

	//// 로그인
	public Users_VO login() {// 로그인시화면 비밀번호,아이디 둘다 String값
		Users_VO vo = new Users_VO();
		System.out.println("===================로그인=====================");
		System.out.println("(공백없이 기입부탁드립니다)");
		System.out.print("아이디>> ");
		String id = sc.next();
		System.out.print("비밀번호>> ");
		String pw = sc.next();
		vo.setuPk(id);
		vo.setuPw(pw);

		return vo;
	}

	// 성공
	public void loginSucc(Users_VO vo) { // 로그인 성공시 이름출력
		System.out.println("=============================================");
		System.out.println(">> " + vo.getuName() + "님 환영합니다!! <<");
	}

	// 실패
	public void loginFail() {// 로그인 실패시 안내문구
		System.out.println("=============================================");
		System.out.println(">> 비밀번호 혹은 아이디를 확인해주세요 <<");
	}

	//// 회원가입
	public Users_VO createUser() {
		System.out.println("============회원가입(반드시공백없이기입)============");

		// 아이디
		System.out.print("아이디>> ");
		String id = sc.next();

		// 비밀번호
		String pw1;
		while (true) {
			System.out.print("비밀번호>> ");
			pw1 = sc.next();

			System.out.print("비밀번호확인>> ");
			String pw2 = sc.next();

			if (pw1.equals(pw2)) {
				break;
			} else {
				System.out.println(">> 비밀번호가 일치하지않습니다. <<");
				continue;
			}
		}

		// 이름
		System.out.print("이름>> ");
		String name = sc.next();

		// 나이
		int age = 0;
		while (true) {
			try {
				System.out.print("나이(0초과정수)>> ");
				age = sc.nextInt();
				if (age < 1 || age > 200) {
					System.out.println("1이상 200이하만 사람입니다.");
					continue;
				} else {
					break;
				}
			} catch (Exception e) { // 문자열입력시
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();

			}
		}

		Users_VO vo = new Users_VO();
		vo.setuPk(id);
		vo.setuPw(pw1);
		vo.setuName(name);
		vo.setuAge(age);

		return vo;
	}

	// 성공
	public void createUserSucc(Users_VO vo) {// 가입성공시 안내문구
		System.out.println("=============================================");
		System.out.println(">> " + vo.getuName() + "님 가입을 환영합니다!! <<");
	}

	// 실패
	public void createUserFail(int flag) {// 가입실패시 안내문구 아이디중복
		System.out.println("=============================================");
		if (flag == 1) {
			System.out.println(">> 아이디가 중복입니다!! <<");
		} else if (flag == 2) {
			System.out.println(">> 알수없는 이유입니다. 관리자를 호출해 주세요 <<");
		}

	}

	//// 메인화면
	public int menuService() {
		int act = -1;
		while (true) {
			System.out.println("=================널뛰기영화관====================");
			System.out.println("1. 전체목록보기");
			System.out.println("2. 예매율순보기");
			System.out.println("3. 내 정보보기");
			System.out.println("0. 로그아웃");
			System.out.print(">>");

			try {
				act = sc.nextInt();

				if (act < 0 || act > 3) { // 0,1,2 이외입력시
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) { // 문자열입력시
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}

		return act;
	}

	// 로그아웃
	public void logout(Users_VO vo) {// 로그아웃시
		System.out.println("=============================================");
		System.out.println(">> " + vo.getuName() + " 님의 로그아웃 <<");
	}

	// 전체,예매율순 목록보기
	public void showMVList(ArrayList<Mv_VO> datas) { // 목록보여주기 전체목록,예매율목록 공통
		int i = 1;
		System.out.println("=============================================");
		System.out.println("[번호]\t[제목]");
		for (Mv_VO v : datas) {

			// datas자르기
			int count = 0;
			for (int index = 0; index < v.getrAge().length(); index++) {
				if (v.getrAge().charAt(index) == ' ') {
					count++;
					if (count == 3) {
						v.setrAge(v.getrAge().substring(0, index));
						break;
					}
				}
			}

			// 간격맞추기
			v.setrAge(v.getrAge() + "   ");

			System.out.println("[" + i++ + "]\t" + v.getmName());
		}
	}

	// 관리자용영화보기
	public void showMVList_admin(ArrayList<Mv_VO> datas) {
		int i = 1;
		System.out.println("=============================================");
		System.out.println("번호\t재고\t영화이름");
		for (Mv_VO v : datas) {
			System.out.printf("[%d]\t%d\t%s\n", i++, v.getStock(), v.getmName());
		}
	}

	// 구매,상세 선택화면
	public int menuBuy() { // 전체목록출력후 메서드
		int act = -1;
		while (true) {
			System.out.println();
			System.out.println("1. 상세보기");
			System.out.println("2. 예매하기");
			System.out.println("0. 이전페이지");
			System.out.print(">>");
			try {
				act = sc.nextInt();

				if (act < 0 || act > 2) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}

		return act;
	}

	// 영화인덱스받기
	public int selectMV() { // 상세보기, 예매하기 입력시
		int act = -1;
		System.out.println("=============================================");
		while (true) {
			System.out.print("영화번호를 입력해주세요>> ");

			try {
				act = sc.nextInt();

				if (act == -1) {
					// 1. 목록의 범위 이외일경우 -> 실패
					// datas = 범위 이외 일경우
					// 2. 사용자의 나이가 관람가 미만일 경우 -> 실패
					// 해당 영화의 vo를 꺼내서 rAgeInt 이거랑 vo 유저나이를 꺼낸다음에 비교
					// 3. 포인트가 부족할경우 -> 실패
					// dao.updatePoint == false 실패
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}

		return act;
	}

	// 상세정보출력
	public void showMVInfo(Mv_VO vo) { // 상세정보 출력
		// 예매율 순위 String

		System.out.println("====================상세정보===================");
		System.out.println("[" + vo.getOnAir() + "]\t" + vo.getmName());
		System.out.println();
		System.out.println("관람가:\t" + vo.getrAge() + "\t상영시간:" + vo.getShowTime());
		System.out.println("장르:\t" + vo.getGenre());
		System.out.println("나라:\t" + vo.getNation() + "\n개봉일:\t" + vo.getRelease());
		System.out.println("감독이름:\t" + vo.getdName() + "\n출연자:\t" + vo.getActors());
		System.out.println("--줄거리--");
		System.out.println(vo.getStory());
		System.out.println("=============================================");
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();

	}

	// 구매성공
	public void buySucc(Users_VO vo1, Mv_VO vo2) { // 구매성공시
		System.out.println("====================예매결과===================");
		System.out.println("" + vo1.getuName() + "님의");
		System.out.println("" + vo2.getmName() + " 의(가) 예매완료되었습니다.");
	}

	// 구매실패
	public void buyFail(int err) { // 구매실패시
		System.out.println("=============================================");
		if (err == 1) {
			System.out.println(">> 해당영화 자리가 없습니다 <<");
		} else if (err == 2) {
			System.out.println(">> 나이가 너무 어립니다. << ");
			System.out.println(">> 해당영화 관람가를 확인해주세요 <<");
		} else if (err == 3) {
			System.out.println(">> 범위 외 입력을 하셨습니다. <<");
		} else if (err == 4) {
			System.out.println(">> 돈이 부족합니다.. <<");
		} else {
			System.out.println(">> 알수없는 에러입니다. 관리자에게 문의해주십시오 <<");
		}

		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();
	}

	// 계속구매여부
	public int buyAfter() { // 구매이후 바로오는 화면
		int act = -1;
		while (true) {
			System.out.println("===================계속구매?===================");
			System.out.println("1.계속구매 0.로그아웃");
			System.out.print(">> ");
			try {
				act = sc.nextInt();

				if (act < 0 || act > 1) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return act;
	}

	//// 내정보화면
	// 관리자 권한 없는 화면
	public int menuUser(Users_VO vo) { // 내정보 출력후 입력
		System.out.println("=====================내정보====================");
		System.out.println("아이디:\t" + vo.getuPk());
		System.out.println("이름:\t" + vo.getuName());
		System.out.println("나이:\t" + vo.getuAge());
		System.out.println("포인트:\t" + vo.getuPoint());
		System.out.println();
		int act = -1;
		while (true) {
			System.out.println("1.포인트입금 2.정보수정 3.계정탈퇴 0.이전페이지");
			System.out.print(">>");

			try {
				act = sc.nextInt();

				if (act < 0 || act > 3) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return act;
	}

	// 포인트입금
	public int inputPoint() { // 포인트입금시
		int money = -1;
		System.out.println("=============================================");
		System.out.println("입금할 포인트를 입력해주세요");
		while (true) {
			System.out.print(">> ");
			try {
				money = sc.nextInt();

				if (money < 0) {
					System.out.println(">> 0이상 정수를 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return money;
	}

	// 포인트결과
	public void resultPoint(Users_VO vo) { // 포인트입금결과출력화면
		System.out.println("=============================================");
		System.out.println(">> " + vo.getuName() + "님의 포인트 :" + vo.getuPoint());
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();
	}

	// 정보수정
	public int menuInfo() { // 정보수정시
		int act = -1;
		System.out.println("=============================================");
		System.out.println("1. 이름변경 2.비밀번호변경 3.나이변경 0.이전페이지");
		while (true) {
			System.out.print(">> ");
			try {
				act = sc.nextInt();

				if (act < 0 || act > 3) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return act;
	}

	// 이름변경
	public String inputName() { // 이름변경시
		System.out.println("=============================================");
		System.out.print("변경할 이름(취소:0입력)>> ");
		String name = sc.next();
		return name;
	}

	// 비밀번호변경
	public String inputPW() { // 비밀번호변경시
		System.out.println("=============================================");
		while (true) {
			System.out.print("변경할 비밀번호(취소:0입력)>> ");
			String pw1 = sc.next();

			if (pw1.equals("0")) {
				return pw1;
			}

			System.out.print("변경할 비밀번호 확인>> ");
			String pw2 = sc.next();

			if (pw1.equals(pw2)) {
				return pw2;
			} else {
				System.out.println(">> 비밀번호가 일치하지 않습니다 <<");
				continue;
			}
		}
	}

	// 나이변경
	public int inputAge() { // 나이변경시
		int age = -1;
		System.out.println("=============================================");
		while (true) {
			System.out.print("변경할 나이(취소:0입력)>> ");
			try {
				age = sc.nextInt();

				if (age < 0) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return age;
	}

	// 정보수정결과화면
	public void resultInfo(Users_VO vo) { // 정보수정이후 결과출력
		System.out.println("================= 정보수정완료 =================");
		System.out.println("아이디:\t" + vo.getuPk());
		System.out.println("이름:\t" + vo.getuName());
		System.out.println("나이:\t" + vo.getuAge());
		System.out.println("포인트:\t" + vo.getuPoint());
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();

	}

	// 계정탈퇴확인화면
	public int checkQuit() { // 게정탈퇴시
		System.out.println("=============================================");
		System.out.println("포인트는 소멸됩니다 정말로 탈퇴하시겠습니까?");
		System.out.println("0.아니요 1.예");
		int act = -1;
		while (true) {
			try {
				System.out.print(">>");
				act = sc.nextInt();

				if (act < 0 || act > 1) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return act;
	}

	// 계정탈퇴안내문구
	public void printQuit(Users_VO vo) { // 계정탈퇴결과 이후 바로 초기화면으로
		System.out.println("=============================================");
		System.out.println(vo.getuName() + " 님의 탈퇴가 완료되었습니다...");
		System.out.println("소멸포인트: " + vo.getuPoint());
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();
	}

	//// 관리자화면
	public int menuAdmin() { // 권한 확인후 출력화면
		int act = -1;
		System.out.println("====================관리자용===================");
		System.out.println("1.재고수정 2.가격수정 3.모든유저정보 0.이전페이지");
		while (true) {
			try {
				System.out.print(">>");
				act = sc.nextInt();

				if (act < 0 || act > 3) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return act;
	}

	// pk입력받기
	public int inputPK() {
		System.out.println("=============================================");
		int pk = -1;
		while (true) {
			try {
				System.out.print("변경할영화번호(취소:0입력)>>");
				pk = sc.nextInt();

				if (pk < 0) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return pk;
	}

	// 재고수정
	public int inputStock(Mv_VO vo) {
		System.out.println("=============================================");
		System.out.println("영화이름:\t" + vo.getmName());
		System.out.println("현재재고:\t" + vo.getStock());

		int num = -1;
		while (true) {
			try {
				System.out.print("추가할재고입력(취소:0입력)>>");
				num = sc.nextInt();

				if (num < 0) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return num;
	}

	// 재고수정결과
	public void resultStock(boolean flag) {
		System.out.println("=============================================");
		if (flag) {
			System.out.println(">> 재고수정완료 <<");
		} else {
			System.out.println(">> 재고수정실패 <<");
		}
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();
	}

	// 가격수정
	public int inputPrice(Mv_VO vo) {
		System.out.println("=============================================");
		System.out.println("현재가격>> " + vo.getPrice());
		int num = -1;
		while (true) {
			try {
				System.out.print("변경할가격입력(취소:0입력)>>");
				num = sc.nextInt();

				if (num < 0) {
					System.out.println(">> 범위내에서 입력해주세요 <<");
					continue;
				}

				break;
			} catch (Exception e) {
				System.out.println(">> 정수를 입력해주세요 <<");
				sc.nextLine();
			}
		}
		return num;
	}

	// 가격수정결과
	public void resultPrice(boolean flag) {
		System.out.println("=============================================");
		if (flag) {
			System.out.println(">> 가격수정완료 <<");
		} else {
			System.out.println(">> 가격수정실패 <<");
		}
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();
	}

	// 모든유저정보
	public void showUserList(ArrayList<Users_VO> datas) {
		System.out.println("=======\t모든유저정보 ");
		System.out.println("아이디\t\t비밀번호\t이름\t\t나이\t포인트\t권한\t");
		for (Users_VO v : datas) {
			System.out.println(v.getuPk() + "\t\t" + v.getuPw() + "\t" + v.getuName() + "\t\t" + v.getuAge() + "\t"
					+ v.getuPoint() + "\t" + v.getuAdmin());
		}
		System.out.print("진행하시려면 엔터를 눌러주세요.");
		sc = new Scanner(System.in);
		sc.nextLine();
	}

}
