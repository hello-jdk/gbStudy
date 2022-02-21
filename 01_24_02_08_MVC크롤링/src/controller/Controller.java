package controller;

import java.util.ArrayList;

import model.Mv_DAO;
import model.Mv_VO;
import model.Users_DAO;
import model.Users_VO;
import view.View;

public class Controller {

	Mv_DAO mvdao;
	Users_DAO udao;
	View view;

	public Controller() {
		mvdao = new Mv_DAO();
		udao = new Users_DAO();
		view = new View();
	}

	// 시작
	public void startApp() {

		// 프로그램 시작
		afterdeleteUser: while (true) {
			int startact = view.menuLogin();

			// 1.로그인
			if (startact == 1) {

				// 회원정보받기
				Users_VO vo = view.login();
				// uDAO
				boolean flag = udao.login(vo);

				// 로그인성공
				if (flag) {

					// 회원정보
					vo = udao.selectOne(vo);
					// 결과View
					view.loginSucc(vo);

					while (true) {

						// 메뉴 선택
						int menuSact = view.menuService();

						// 0.로그아웃
						if (menuSact == 0) {
							view.logout(vo);
							break;
						}

						// 1.전체목록
						else if (menuSact == 1) {

							while (true) {

								// 전체 영화 리스트 보여주기
								ArrayList<Mv_VO> datas = mvdao.selectAll(new Mv_VO());
								view.showMVList(datas);

								// 1. 상세보기 2.에매하기 0.이젠페이지
								int buyact = view.menuBuy();

								// 이전페이지
								if (buyact == 0) {
									break;// 이전페이지로
								}
								// 상세보기
								else if (buyact == 1) {

									// Controller <- View
									int selectact = view.selectMV();

									// 목록의 범위 이외일 경우 -> 실패
									if (datas.size() < selectact || selectact <= 0) {
										view.buyFail(3);
									} else {
										// Controller
										Mv_VO vo1 = datas.get(selectact - 1);
										// Controller -> View
										view.showMVInfo(vo1);
									}

									continue;
								}

								// 예매하기
								else if (buyact == 2) {
									int selectact = view.selectMV();

									// 목록의 범위 이외일 경우 -> 실패
									if (datas.size() < selectact || selectact <= 0) {
										view.buyFail(3);
									}
									// 목록 범위에 있을 경우
									else {
										// 영화 확정
										Mv_VO vo_sale = datas.get(selectact - 1);

										// 관람가 이하!
										if (vo_sale.getrAgeInt() > vo.getuAge()) {
											view.buyFail(2);
											continue;
										}

										// 유저데이터
										Users_VO vo_u = new Users_VO();
										vo_u.setuPk(vo.getuPk());
										vo_u.setuPoint(-1 * vo_sale.getPrice());

										// 영화데이터
										Mv_VO vo_c = new Mv_VO();
										vo_c.setmNum(vo_sale.getmNum());

										// 재고 확인
										boolean flag1 = mvdao.updateStock(vo_c);
										
										// 재고 YES
										if (flag1) {

											// 포인트 확인
											boolean flag2 = udao.updatePoint(vo_u);
											
											// 포인트 YES
											if (flag2) {
												// 구매성공
												vo_sale.setmName(vo_sale.getmName().replaceAll(" ", ""));
												vo_sale.setmName(vo_sale.getmName().replaceAll("\t", ""));
												view.buySucc(vo, vo_sale);
												
												// 정보업데이트
												vo = udao.selectOne(vo_u);

												// 구매이후?
												int afteract = view.buyAfter();

												// 1. 계속구매
												if (afteract == 1) {
													continue;
												}
												// 2. 로그아웃
												else if (afteract == 0) {
													view.logout(vo);
													continue afterdeleteUser;
												}
											}

											// 포인트 NO
											else {
												vo_c.setStock(1);
			                                    mvdao.updateStock(vo_c);
			                                    view.buyFail(4);
			                                 }
										}
										// 재고 NO
										else {
											view.buyFail(1);
										}
									} // 목록에있는영화
								} // 예매하기
							} // 전체목록 while
						} // 1전체목록

						// 2.예매율목록
						else if (menuSact == 2) {
							// 영화리스트 예매율순 보기
							ArrayList<Mv_VO> datas = mvdao.selectAll_rank(new Mv_VO());
							view.showMVList(datas);

						}
						// 내정보보기
						else if (menuSact == 3) {

							// 권한?
							int uAdmin = udao.selectOne(vo).getuAdmin();

							// 일반유저모드
							if (uAdmin == 0) {

								while (true) {

									// 유저입력
									int useract = view.menuUser(vo);

									// 0. 이전페이지
									if (useract == 0) {
										break;
									}

									// 1.포인트입금
									else if (useract == 1) {

										// 포인트입력
										int upoint = view.inputPoint();

										// 가공
										Users_VO pvo = new Users_VO();
										pvo.setuPk(vo.getuPk());
										pvo.setuPoint(upoint);

										// uDAO
										boolean pflag = udao.updatePoint(pvo);

										// 포인트 입금결과 View
										if (pflag) {
											pvo = udao.selectOne(pvo);
											view.resultPoint(pvo);
											vo.setuPoint(pvo.getuPoint());
										}

									}

									// 2.정보수정
									else if (useract == 2) {

										// 사용자입력
										// 1이름변경 2비밀번호변경 3나이변경 0이전페이지
										int menuact = view.menuInfo();

										//
										Users_VO uvo = new Users_VO();
										uvo.setuPk(vo.getuPk());
										boolean uflag = false;

										// 0. 이전페이지
										if (menuact == 0) {
											continue;
										}

										// 1. 이름변경
										else if (menuact == 1) {

											// 이름 입력
											String nact = view.inputName();

											// 이전페이지
											if (nact.equals("0")) {
												continue;
											}

											// 가공, uDAO
											uvo.setuName(nact);
											uflag = udao.updateUser(uvo);

											// 결과View
											if (uflag) {
												vo.setuName(uvo.getuName());
												view.resultInfo(vo);
											}

										}
										// 2. 비밀번호 변경
										else if (menuact == 2) {

											// 비밀번호 입력
											String pact = view.inputPW();

											// 이전페이지
											if (pact.equals("0")) {
												continue;
											}

											// 가공, uDAO
											uvo.setuPw(pact);
											uflag = udao.updateUser(uvo);

											// 결과View
											if (uflag) {
												vo.setuPw(uvo.getuPw());
												view.resultInfo(vo);
											}

										}
										// 3. 나이 변경
										else if (menuact == 3) {

											// 나이 입력
											int aact = view.inputAge();

											// 이전페이지
											if (aact == 0) {
												continue;
											}

											// 가공, uDAO
											uvo.setuAge(aact);
											uflag = udao.updateUser(uvo);

											// 결과View
											if (uflag) {
												vo.setuAge(uvo.getuAge());
												view.resultInfo(vo);
											}
										}

									}
									// 3. 계정탈퇴
									else if (useract == 3) {

										// 탈퇴확인View
										int deleteact = view.checkQuit();

										// 1. 탈퇴
										if (deleteact == 1) {

											// 탈퇴진행 유저정보
											Users_VO dvo = new Users_VO();
											dvo.setuPk(vo.getuPk());
											dvo.setuName(vo.getuName());

											// uDAO
											boolean dflag = false;
											dflag = udao.deleteUser(dvo);

											// 결과View
											if (dflag) {
												view.printQuit(vo);
												continue afterdeleteUser;
											}
										}
									} // 3. 계정탈퇴
								} // 일반유저while
							} // 일반유저 권한==0

							// 관리자모드
							if (uAdmin == 1) {

								while (true) {
									// 관리자메뉴
									int adminact = view.menuAdmin();

									// DB에서 영화자료 가져오기
									ArrayList<Mv_VO> datas_admin = new ArrayList<Mv_VO>();
									datas_admin = mvdao.selectAll(new Mv_VO());
									if (adminact == 0) {
										break;
										// 관리자메뉴 나가기
									}
									// 1. 재고수정
									else if (adminact == 1) {

										// 영화보여주기(관리자)
										view.showMVList_admin(datas_admin);

										// 인덱스값 받기
										int num_st = view.inputPK();

										// 범위 이외의 값
										if (num_st - 1 > datas_admin.size()) {
											continue;
										}

										// 뒤로가기
										if (num_st == 0) {
											continue;
										}

										// 가공 (영화결정)
										Mv_VO mvo_admin = datas_admin.get(num_st - 1);

										// 변경 재고값 받기
										int stock_st = view.inputStock(mvo_admin);

										// 가공 (추가재고값 결정)
										if (stock_st == 0) {
											continue;
										} else {
											mvo_admin.setStock(stock_st);
										}
										// mvDAO
										boolean flag_st = mvdao.updateStock(mvo_admin);

										// 결과View
										view.resultStock(flag_st);

									}

									// 2. 가격수정
									else if (adminact == 2) {

										// 가격값 받기
										int price_pri = view.inputPrice(datas_admin.get(0));

										// 뒤로가기
										if (price_pri == 0) {
											continue;
										}

										// 가공 (가격넣기)
										Mv_VO mvo_pri = new Mv_VO();
										mvo_pri.setPrice(price_pri);

										// mvDAO
										boolean flag_pri = mvdao.updatePrice(mvo_pri);

										// 결과View
										view.resultPrice(flag_pri);
									}

									// 3. 모든유저정보보기
									else if (adminact == 3) {
										// userDAO
										ArrayList<Users_VO> udatas_admin = new ArrayList<Users_VO>();
										udatas_admin = udao.selectAll(new Users_VO());

										// 결과View
										view.showUserList(udatas_admin);

									}

								} // 관리자모드while
							} // 관리자모드 권한==1
						} // 내정보보기 act==3
					} // 로그인성공while
				} // 로그인성공if

				// 로그인실패
				else {
					view.loginFail();
					continue;
				}
			}
			// 2. 회원가입
			else if (startact == 2) {
				// 회원가입정보 받기
				Users_VO vo = view.createUser();
				// uDAO
				int flag = udao.insertUser(vo);

				// 결과View
				// 0 성공
				if (flag == 0) {
					view.createUserSucc(vo);
				}
				// 1 아이디중복
				else if (flag == 1) {
					view.createUserFail(1);
				}
				// 2 관리자요청
				else if (flag == 2) {
					view.createUserFail(2);
				}
			}

			// 0. 종료
			else if (startact == 0) {
				// 프로그램 종료
				view.menuOut();
				break;
			}

		} // 프로그램시작while
	}// startApp()
}// Controller