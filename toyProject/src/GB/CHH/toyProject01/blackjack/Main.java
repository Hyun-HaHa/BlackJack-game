package GB.CHH.toyProject01.blackjack;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//게임 시작시 player의 이름 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		
		Player p = new Player(sc.nextLine());
		
		System.out.println(p.getName() + "님 게임을 시작하겠습니다.");
		System.out.println("-----------------------------------------");
		
		//-----------------------------------------------------------------------------------
		// 52장의 카드 생성
		int suit_num = 4;
		int rank_num = 13;
		int total_card = suit_num * rank_num;
		
		Card[] c = new Card[total_card];
		
		for (int i = 0; i < suit_num; i++) {
			for (int j = 0; j < rank_num; j++) {
				c[i * rank_num + j] = new Card(i, j);
			}
		}
		//-------------------------------------------------------------
		// 게임 시작 시 랜덤으로 카드 두장 받기 (딜러의 카드 두장의 값이 16이하일때 카드 추가)
		Random r = new Random();
		int temp = r.nextInt(total_card);
		int temp2 = r.nextInt(total_card);
		int temp3 = r.nextInt(total_card);
		
		int dealer = c[temp].cardPoint() + c[temp2].cardPoint();
		String dealerPoint = "Dealer의 카드 : " + c[temp] + " " + c[temp2];
		
		// dealer의 받은 두 장의 카드 점수가 16점 이하일 때 카드 추가
		if (dealer < 17) {
			for (int i = 0; i < 1; i++) {
				temp3 = r.nextInt(total_card);
				dealer += c[temp3].cardPoint();
				System.out.println("Dealer의 카드 : [?] " + c[temp2] + " " + c[temp3]);
				dealerPoint += " " + c[temp3];				
			}
 		} else {
			System.out.println("Dealer의 카드 : [?] " + c[temp2]);
		}
		
		// Ace카드 두장이 나왔을 경우 Ace카드 한장의 점수는 1점 처리
		if (dealer > 21 && (c[temp].cardPoint() == 11 || c[temp2].cardPoint() == 11 || c[temp3].cardPoint() == 11)) {
			dealer -= 10;
		}
		
		// 플레이어 랜덤카드 받기
		temp = r.nextInt(total_card);
		temp2 = r.nextInt(total_card);
		System.out.println(p.getName()+ "님의 카드 : " + c[temp] + " " + c[temp2]);
		p.setPoint(c[temp].cardPoint() + c[temp2].cardPoint());
		
		if (p.getPoint() > 21 && (c[temp].cardPoint() == 11 || c[temp2].cardPoint() == 11)) {
			p.setPoint(p.getPoint() - 10);
		}
		
		// 딜러가 카드를 더 받았을 때 21점 보다 많을 경우 dealer bust
		if (dealer > 21) {
			System.out.println("-------------------------------------------");
			System.out.println(dealerPoint);
			System.out.println("dealer의 점수 : " + dealer);
			System.out.println("\nDealer Bust");
			System.out.println("결과 : " + p.getName() + "WIN");
			
			System.exit(0);
		}
		
		// 반복문을 돌기위해 play 변수 선언
		boolean play = true;
		
		// 플레이어의 카드 두 장이 21점일때 블랙잭
		if (p.getPoint() == 21) {
			System.out.println("---------------------------------------------");
			System.out.println("dealer 카드 점수 : " + dealer);
			System.out.println(p.getName() + "님의 카드 점수: " + p.getPoint() + "점");
			System.out.println("--------Blackjack--------\nplayer WIN");
			play = false;
			return;
		}
		
		System.out.println(p.getName() + "님의 카드 점수: " + p.getPoint() + "점");
		System.out.println();

		//---------------------------------------------------------------------------------
		// HIT, STAND를 입력받아 게임 구현
		while (play) {
			
			try {
				System.out.println("1. HIT \t 2. STAND");
				System.out.println("---------------------------------------------");
				
				switch(sc.nextInt()) {
				// HIT(카드 한 장 받기)
				case 1:
					for (int i = 0; i < 1; i++) {
						temp3 = r.nextInt(total_card);
						System.out.println(c[temp3] + " 카드를 얻었습니다.");
						p.setPoint(p.getPoint() + c[temp3].cardPoint());
						
						// Ace 카드가 두 장 이상일때 Ace카드의 점수 1점처리
						if (p.getPoint() > 21 && (c[temp].cardPoint() == 11 || c[temp2].cardPoint() == 11)) {
							p.setPoint(p.getPoint() - 10);
						} 
						
						if (p.getPoint() > 21 && (c[temp].cardPoint() == 11 || c[temp3].cardPoint() == 11) ) {
							p.setPoint(p.getPoint() - 10);
						}
						
						if (p.getPoint() > 21 && (c[temp2].cardPoint() == 11 || c[temp3].cardPoint() == 11)) {
							p.setPoint(p.getPoint() - 10);
						}
						
					}
					
					// player가 HIT했을 때 21점이 넘어간 경우 bust
					if(p.getPoint() > 21) {
						System.out.println(p.getName()+ "님의 점수 : " + p.getPoint());
						System.out.println("\n" + p.getName() + "님 Bust");
						System.out.println("결과 : Dealer WIN");
						play = false;
						break;
					} else {
						System.out.println(p.getName()+ "님의 점수 : " + p.getPoint());
						break;
					}
					
				// STAND -> 결과 보기	
				case 2: 
					if (p.getPoint() > dealer) {				// player가 이길 때
						System.out.println(dealerPoint);
						System.out.println(p.getName()+ "님의 점수 : " + p.getPoint() 
											+ "\ndealer의 점수 : " + dealer);
						System.out.println("--------------------------------------------");
						System.out.println("결과 : " + p.getName() + "님 WIN");
						play = false;
						break;
						
					} else if (p.getPoint() == dealer) {		// player와 dealer의 점수가 같을 때
						System.out.println(dealerPoint);
						System.out.println(p.getName()+ "님의 점수 : " + p.getPoint() 
											+ "\ndealer의 점수 : " + dealer);
						System.out.println("--------------------------------------------");
						System.out.println("결과 : 무승부 입니다.");
						play = false;
						break;
						
					} else {							// dealer가 이길 때 
						System.out.println(dealerPoint);
						System.out.println(p.getName()+ "님의 점수 : " + p.getPoint()
											+ "\ndealer의 점수 : " + dealer);
						System.out.println("--------------------------------------------");
						System.out.println("결과 : Dealer WIN");
						play = false;
						break;
					}
				// 1, 2가 아닌 다른 숫자를 입력했을 때 반복문 처음으로 가기
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
				
			} catch (Exception e) {			// 숫자가 아닌 값을 입력했을 때 예외처리
				System.out.println("잘못 입력하셨습니다.");
				// scanner의 버그로 인해 다시 초기화
				sc = new Scanner(System.in);
			}
			
			
		}
				
		
		
	}

}
