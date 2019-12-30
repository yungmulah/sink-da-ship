package sankaskapp;

import java.util.Scanner;

public class PvE extends RunGame {
	private static Scanner scan = new Scanner(System.in);
	private static boolean gameEnded;
	private static boolean playerWon;
	private static double playerShots;
	private static double computerShots;
	private static String playerName;
	protected static long p1Time;

	public static void main(String[] args) {
		fillGrid();
		createList();
		playerPlaceShips();
		compPlaceShips();
		System.out.println();

		while (!gameEnded) {
			playerTurn();
			if (allShipsSinked()) {
				System.out.println("Du har sänkt alla datorns skepp!");
				playerWon = true;
				break;
			}
			computerTurn();
			if (allShipsSinked()) {
				break;
			}
		}
		if (playerWon) {
			System.out.println("Du vann över datorn!");
			System.out.println("Du slog datorn på " + Math.round(playerShots) + " rundor!");
			System.out.println();
		} else {
			System.out.println("Datorn vann över dig på " + Math.round(computerShots) + " rundor!");
		}
	}

	public static void playerPlaceShips() {
		player1 = 1;
		System.out.print("Namn på spelaren: ");
		playerName = scan.nextLine();
		System.out.println(playerName + "'s tur att placera ut sina skepp.");
		System.out.println("===========================");
		standardShips();
		System.out.println("Vill du placera ut båtarna själv? Ja/Nej");
		boolean svar = false;
		while(!svar) {
			switch(scan.next()) {
			case "ja":
			case "Ja":
				for (BattleShip ship : playerShips()) {
					printBoard(0);
					placeShip(ship);
				}
				svar = true;
				break;
			case "nej":
			case "Nej":
				for(BattleShip ship : playerShips()) {
					computerPlaceShips(ship);
				}
				svar = true;
				System.out.println("Dina båtar är placerade såhär:");
				printBoard(0);
				break;
			default:
				System.out.println("Vänligen svara ja eller nej.");
			}
		}
		scan.nextLine();
		System.out.println("Tryck på Enter för att fortsätta");
		scan.nextLine();
		System.out.println();
	}

	public static void compPlaceShips() {
		player1 = 2;
		System.out.println("Datorn placerar ut sina skepp.");
		standardShips();
		for (BattleShip ship : playerShips()) {
			computerPlaceShips(ship);
		}
		//Raderna nedan skall bort.
		System.out.println("DATORNS: ");
		printBoard(0);

	}

	public static void playerTurn() {
		System.out.println("Tryck på Enter för att starta din omgång.");
		scan.nextLine();
		player1 = 1;
		long t = System.currentTimeMillis();
		System.out.println("Ditt bräde:");
		printBoard(0);
		System.out.println();
		System.out.println("Motståndarens bräde:");
		printBoard(1);
		System.out.println();
		boolean shot = false;
		playerStats();

		while (!shot) {
			shot = shoot();
			printBoard(1);
			playerShots++;

			if (allShipsSinked()) {
				gameEnded = true;
				break;
			}
		}
		System.out.println("Tryck på Enter för att avsluta din omgång.");
		scan.nextLine();
		addPlayerTime(t - System.currentTimeMillis());
	}

	public static void computerTurn() {
		player1 = 2;
		boolean shot = true;
		while (shot) {
			shot = computerShoot();
			computerShots++;
			if (allShipsSinked()) {
				gameEnded = true;
				break;
			}
		}
	}

	public static void playerStats() {
		int shipsHit = 0;
		double totalShips = 0;

		for (BattleShip ship : opponentShips()) {
			shipsHit = shipsHit + ship.getHits();
			totalShips = totalShips + ship.getSize();

		}
		double hitP = (shipsHit / playerShots) * 100;
		if (playerShots == 0) {
			hitP = 0;
		}
		double dmgP = (shipsHit / totalShips) * 100;
		double shotTime = p1Time/playerShots;
		
		if(playerShots != 0) {
			System.out.println(playerName + "'s träffprocent är: " + String.format("%.2f", hitP) + "%");
			System.out.println("Du har gjort " + String.format("%.2f", dmgP) + "% skada på datorn!");
			System.out.println("Du snittar "+ shotTime + " sekunder per omgång.");
		}
	}
	
	public static void addPlayerTime(long t) {
		p1Time = p1Time + t;	
	}
}