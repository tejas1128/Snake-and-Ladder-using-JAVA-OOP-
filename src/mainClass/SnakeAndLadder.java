package mainClass;

import java.util.Scanner;
import play.Players;

public class SnakeAndLadder {

	public static void main(String[] args) 
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of players :) ");
		int num_of_players = sc.nextInt();
		
		Players Admin = new Players();
		Admin.play(num_of_players);
		
		sc.close();
		
	}
}
