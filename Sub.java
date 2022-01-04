package day10;

public class Sub {
	
	//추상클래스 Beverage
	//name, PK, stock
	//toString으로 제품 화면 출력
	
	//자식클래스 
	//Jiuce, CarbonatedDrink, SportDrink

	//+관리자모드
	//추상클래스에 구현
	//1. Getter, Setter를 통한 전체 정보 확인 (PK제외)
	//2. 수량변경
	//3. 종료

	public static void main(String[] args) {
		//음료수[] data = 음료수 배열[크기]
		//기본제공음료
		
		//변수
		//소지금, 영수증, 시크릿코드
		
		while() 
		{
			//첫화면
			//버튼 : 돈투입, 제품구입, 종료
			
			//1 돈투입
			if(돈) 
			{
				
				//돈 입금 받기
				//안내문구 (현재잔액)
				//첫화면으로
			}
			//2 제품구입
			else if(제품) 
			{
				while() 
				{
					//제품 목록 표시
					//제품번호 받기
						switch(제품 번호) //if로도 가능 
						{ 
							case 첫화면:
								//첫화면
							case 제품번호:
								if(품절) 
								{
									//안내문구
									//제품목록화면
								}
								else 
								{
									//재고--
									//영수증에 추가
									//안내문구(남은금액)
									//제품목록화면
								}
							case 시크릿코드:
								//관리자모드 실행
								//제품목록화면
						}
				}
			}
			//3 종료 및 예외성 입력
			else if(종료) 
			{
				//안내문구 (ex.이용해주셔서 감사합니다)
				//영수증 출력
			}
			else 
			{ 
				//안내문구 (유효성검사)
				break;
			}
			
		}
		
		
		
		
	}
}


//고급 (콘솔에서 모든입력에대해 오류방지)
//유효성 검사
/*
1.버퍼제거
while(sc.hasNext())			
	sc.nextLine();
	
2.입력받기
String str = sc.nextLine();

3.검사
빈공간 = if(strPw.trim().isEmpty() || strPw == null)
소수 = for(str.charAt(i))
		'.'이 존재하다면 소수
문자열 = for(v:str.toUpperCase().charAt(i))
		'A' <= v-'A' <= 'Z' 이 한개라도 있으면 문자열
else
정수 = Intger.parseInt(str)
*/




