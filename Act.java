package ru.play;

/*										(Кратко о игре)
 * Данный код представляет собой всеми известный игру "крестики-нолики".
 * Я с ним возился где-то 3 дня и у меня что-то получилось. 
 * Причина такого долгого программирования понятна — злоебучие баги.
 * Изначально были две баги: с опозданием выводился звёздочка
 * и невозможно было зациклить цикл для того, чтобы далось новое значение переменному.  
 * Второй баг полностью исправлен, а второй изначально был исправлен, но когда был решен
 * первый (баг), то появился он снова (второй баг); однако его редко можно встретить, что
 * как-то радует.
 * 
 * Играть очень просто: первый ввод означает ширину поли, второй ввод колонну поли.
 * Например 1 2 будет так:
 * 
 * | || || |
 * | || || |
 * | ||•|| |
 * 
 * Отсчёт начинается с нуля 
 */

// Комментирование кода
//import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Act {
	//Данный массив выступает в роли поли
	public static char[][] query = { 
			{ ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ' }};
	
	public static void field() {
		Scanner s = new Scanner(System.in);
		//Проверка для того, чтобы выйти из игры 
		if(s.hasNext("stop")) System.exit(0);
		
		while (true)
			try {
				if(s.hasNextShort()) {
				// width — ширина поли,
				// column — колонна
				short width = s.nextShort();
				short column = s.nextShort();
				
				for (int i = 0; i < query.length; i++) {
					for (int j = 0; j < query.length; j++) {
						if(query[column][width] != '*')
						query[column][width] = '•';
					}
					System.out.println();
				}
				// вызов два метода
				roboPlayer();
				rulesOfPlay();
				//Очищает консоль, не работает именно в IDE
				System.out.flush();
				} else System.exit(0);
				
			} catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
				System.out.println("Вы ввели число (или символ)"
						+ ", которое превышает границы!");
			}
		
	}
	//Робот-игркок, где ты с ним играешь, на самом деле с Random, но да ладно
	private static void roboPlayer() {
		Random r = new Random();
		boolean tmp = true; 
		int width = r.nextInt(3);
        int column = r.nextInt(3);
		
		for (int i = 0; i < query.length; i++) {
			for (int j = 0; j < query.length; j++) {
				if(query[column][width] == ' ' && tmp == true) {
					query[column][width] = '*';
					tmp = false;
				} else {
					// Каждый заход цикла меняет значения каждый раз, хорошо, что это
					// небольшой проект, а иначе сожрал бы памяти достаточно.
					width = r.nextInt(3);
					column = r.nextInt(3);
				}
				System.out.print("" + "|" + query[i][j] + "|");
		}
			System.out.println();
	}
//		System.out.println("\n" + Arrays.deepToString(query));
}
	//Метод, который проверяет расположение символов в клетках и делает решения — ты выиграл или нет
	private static void rulesOfPlay() {
		//Основные правила "крестики-нолики", спасибо, что не тормозит 
		if(query[0][0] == '•' && query[0][1] == '•' && query[0][2] == '•'
		|| query[1][0] == '•' && query[1][1] == '•' && query[1][2] == '•'
		|| query[2][0] == '•' && query[2][1] == '•' && query[2][2] == '•'
		|| query[0][0] == '•' && query[1][1] == '•' && query[2][2] == '•'
		|| query[0][2] == '•' && query[1][1] == '•' && query[2][0] == '•'
		|| query[0][0] == '•' && query[1][0] == '•' && query[2][0] == '•'
		|| query[0][1] == '•' && query[1][1] == '•' && query[2][1] == '•'
		|| query[0][2] == '•' && query[1][2] == '•' && query[2][2] == '•') {
			System.out.println("Вы выиграли!");
			System.exit(0);
		}
		if(query[0][0] == '*' && query[0][1] == '*' && query[0][2] == '*'
		|| query[1][0] == '*' && query[1][1] == '*' && query[1][2] == '*'
		|| query[2][0] == '*' && query[2][1] == '*' && query[2][2] == '*'
		|| query[0][0] == '*' && query[1][1] == '*' && query[2][2] == '*'
		|| query[0][2] == '*' && query[1][1] == '*' && query[2][0] == '*'
		|| query[0][0] == '*' && query[1][0] == '*' && query[2][0] == '*'
		|| query[0][1] == '*' && query[1][1] == '*' && query[2][1] == '*'
		|| query[0][2] == '*' && query[1][2] == '*' && query[2][2] == '*') {
			System.out.println("Вы проиграли!");
			System.exit(0);
		}
		//Нужная часть кода, чтобы завершать те моменты, которые нигде нельзя поставить
		//знак, то бишь ничья
		int tmp = 0;
		for (int i = 0; i < query.length; i++) {
			for (int j = 0; j < query.length; j++) {
				if(query[i][j] != ' ') tmp++; 
			}
		}
		if(tmp == 9) { 
			System.out.println("Ничья!");
			System.exit(0);
		}
	}
}
