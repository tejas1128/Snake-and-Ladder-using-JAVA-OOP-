package play;

import java.util.Random;
import java.util.Scanner;

public class Players {
	String name;
	int place;
	int snakeHead[] = {17, 62, 87, 54, 64, 98, 95, 93};
	int snakeTail[] = {7, 19, 24, 34, 60, 79, 75, 73};
	int ladderStart[] = {1, 4, 9, 21, 28, 51, 80, 71};
	int ladderEnd[] = {38, 14, 31, 42, 84, 67, 100, 91};
	
	Scanner sc = new Scanner(System.in);
	
	public Players() {}
	
	public Players(String name, int place)
	{
		this.name = name;
		this.place = place;
		System.out.println("You will be as " + "\"" + name + "\"");
	}
	
	public int makeMove()
	{
		Random random = new Random();
		int num = random.nextInt(6) + 1;
		
		if(num == 1)
		{
			System.out.println("---------");
			System.out.println("|       |");
			System.out.println("|   *   |");
			System.out.println("|       |");
			System.out.println("---------");
		}
		else if(num == 2)
		{
			System.out.println("---------");
			System.out.println("|   *   |");
			System.out.println("|       |");
			System.out.println("|   *   |");
			System.out.println("---------");
		}
		else if(num == 3)
		{
			System.out.println("---------");
			System.out.println("|   *   |");
			System.out.println("|   *   |");
			System.out.println("|   *   |");
			System.out.println("---------");
		}
		else if(num == 4)
		{
			System.out.println("---------");
			System.out.println("| *   * |");
			System.out.println("|       |");
			System.out.println("| *   * |");
			System.out.println("---------");
		}
		else if(num == 5)
		{
			System.out.println("---------");
			System.out.println("| *   * |");
			System.out.println("|   *   |");
			System.out.println("| *   * |");
			System.out.println("---------");
		}
		else if(num == 6)
		{
			System.out.println("---------");
			System.out.println("| *   * |");
			System.out.println("| *   * |");
			System.out.println("| *   * |");
			System.out.println("---------");
		}
		return num;
	}
	
	public int playerTurn(int place, String name, int i)
	{
		Random random = new Random();
		System.out.println("\n\nYour Turn : " + name);
		int nameLength = name.length();
		boolean guessCorrect = false;
		System.out.println("Current position : " + place);
		
		while(!guessCorrect)
		{
			int temp = random.nextInt(nameLength);
			char t = name.charAt(temp);
			String tempLetter = Character.toString(t);
			System.out.print("Enter " + (temp+1) + "th letter of your name: ");
			String tempInput = sc.nextLine();
			
			if(tempInput.equalsIgnoreCase(tempLetter)) {
				guessCorrect = true;
			}
			
			else {
				guessCorrect = false;
				System.out.println(" ====== Wrong Guess ======");
			}
		}

		int num = makeMove();
		boolean snakePresent = false;
		boolean ladderPresent = false;
		
		int x = this.place + num;
		
		int inc = 0;
		
		int snakePresentPosition = -1;
		for(int k=0;k<8;k++)
		{
			if(x == snakeHead[k])
			{
				snakePresent = true;
				snakePresentPosition = k;
			}
		}
		
		int ladderPresentPosition = -1;
		for(int k=0;k<8;k++)
		{
			if(x == ladderStart[k])
			{
				ladderPresent = true;
				ladderPresentPosition = k;
			}
		}
		
		if(num == 6)
		{
			i-=1;
			inc = 1;
			System.out.println("The next turn is also your's");
		}
		
		if(snakePresent)
		{
			System.out.println("OHH oooohh. You are bitten by the sanke :(");
			this.place = snakeTail[snakePresentPosition];
			System.out.println("You are now at : " + this.place);
		}
		
		else if(ladderPresent)
		{
			System.out.println("Wah, You climed the ladder :) ");
	        this.place =  ladderEnd[ladderPresentPosition];
	        System.out.println("You are now at : " + this.place);
	        
	        if(inc != 1)
	        {
	        	System.out.println("The next turn is also your's");
	        	i -= 1;
	        }
		}
		
		else if(x > 100)
		{
//			this.place = this.place;
		}
		
		else
		{
			this.place = x;
			System.out.println("You are now at : " + this.place);
		}
		
		return i;
		
	}
	
	public void play(int num_of_players)
	{
		Players players[] = new Players[num_of_players];
		
		for(int i=0;i<num_of_players;i++)
		{
			System.out.println("Enter player" + (i+1) + " name");
			String N = sc.nextLine();
			N = N.strip();
			String[] temp = N.split(" ");
			players[i] = new Players(temp[0].toUpperCase(), 0);
		}
		
		boolean notWin = true;
		int i=0;
		
		while (notWin)
		{
			for(i=0;i<num_of_players;i++)
			{
				i = players[i].playerTurn(players[i].place, players[i].name, i);
				for(int j=0;j<num_of_players;j++)
				{
					if(players[j].place == 100)
					{
						notWin = false;
						System.out.println("\n\n******************************\n\nThe winner is " + players[j].name + "\n\n******************************\n");
						return;
					}
				}
			}
		}
	}
}
