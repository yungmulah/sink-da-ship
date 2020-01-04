package sankaskapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PvP extends RunGame {
	private static Scanner scan = new Scanner(System.in);
	protected static boolean gameEnded;
	protected static boolean player1Won;
	protected static String p1Name;
	protected static String p2Name;
	protected static double p1Shots;
	protected static double p2Shots;
	protected static long p1Time;
	protected static long p2Time;
	protected static double player1Rounds = 0;
	protected static double player2Rounds = 0;

	public static void main(String[] args) {
		fillGrid();
		createList();
		for (int p = 1; p <= 2; p++) {
			playerPlaceShip(p);
		}

		System.out.println();
		System.out.println("Nu börjar spelet!");

		while (!gameEnded) {
			for (int i = 1; i <= 2; i++) {
				playerTurn(i);
				if (allShipsSinked()) {
					System.out.println("Du har sänkt alla " + opponentName(i) + "'s skepp!");
					playerWon(i);
					gameEnded = true;
					break;
				}
			}
		}
		if (player1Won) {
			System.out.println(p1Name + " är vinnaren!");
		}else {
			System.out.println(p2Name + " är vinnaren!");
		}
		System.out.println("Din statistik för detta spelet: " );
		playerStats(currentPlayer);
		writeHighscore(currentPlayer);
		
	}

	public static void playerWon(int p) {
		if (p == 1) {
			player1Won = true;
		}
	}

	public static String playerName(int p) {
		if (p == 1) {
			return p1Name;
		} else {
			return p2Name;
		}
	}

	public static String opponentName(int p) {
		if (p == 1) {
			return p2Name;
		} else {
			return p1Name;
		}
	}

	public static double playerShots(int p) {
		if (p == 1) {
			return p1Shots;
		} else {
			return p2Shots;
		}
	}
	
	public static void addPlayerRounds(int p) {
		if (p == 1) {
			player1Rounds++;
		} else {
			player2Rounds++;
		}
	}
	
	public static double playerRounds(int p) {
		if (p == 1) {
			return player1Rounds;
		} else {
			return player2Rounds;
		}
	}
	
	public static double opponentShots(int p) {
		if (p == 1) {
			return p2Shots;
		} else {
			return p1Shots;
		}
	}
	
	public static void addPlayerTime(int p, long t) {
		if (p == 1) {
			p1Time = p1Time + t;
		}
		else {
			p2Time = p2Time + t;
		}
	}
	
	public static long playerTime(int p){
		if (p == 1) {
			return p1Time / 1000;
		}
		else {
			return p2Time / 1000;
		}
	}

	public static void playerPlaceShip(int p) {
		currentPlayer = p;
		System.out.print("Namn på spelare: ");
		if (currentPlayer == 1) {
			p1Name = scan.nextLine();
		} else {
			p2Name = scan.nextLine();
		}
		System.out.println(playerName(p) + " placerar ut sina skepp.");
		System.out.println("===========================");
		//standardShips();
		specialShips();
		System.out.println("Vill du placera ut båtarna själv? Ja/Nej");
		boolean svar = false;
		while (!svar) {
			switch (scan.next()) {
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
				for (BattleShip ship : playerShips()) {
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
		System.out.println("Tryck på Enter när du vill lämna över till nästa spelare.");
		scan.nextLine();
		for (int i = 0; i <= 50; i++) {
			System.out.println();
		}
	}

	public static void addShots(int p) {
		if (p == 1) {
			p1Shots++;
		} else {
			p2Shots++;
		}
	}
	

	public static void playerTurn(int p) {
		System.out.println("Är " + playerName(p) + " redo? Tryck på Enter.");
		scan.nextLine();
		long t = System.currentTimeMillis();
		currentPlayer = p;
		System.out.println("Ditt bräde:");
		printBoard(0);
		System.out.println("Motståndarens bräde:");
		printBoard(1);
		boolean shot = false;
		playerStats(p);

		while (!shot) {
			shot = shoot();
			printBoard(1);
			addShots(p);
			if (allShipsSinked()) {
				gameEnded = true;
			}
		}
		System.out.println("Tryck på Enter för att avsluta din omgång.");
		scan.nextLine();
		addPlayerRounds(p);
		addPlayerTime(p, System.currentTimeMillis() - t);
		for (int i = 0; i <= 50; i++) {
			System.out.println();
		}
	}

	public static void playerStats(int p) {
		int shipsHit = 0;
		double totalShips = 0;

		for (BattleShip ship : opponentShips()) {
			shipsHit = shipsHit + ship.getHits();
			totalShips = totalShips + ship.getSize();

		}
		double hitP = (shipsHit / playerShots(p)) * 100;
		if (playerShots(p) == 0) {
			hitP = 0;
		}

		double dmgP = (shipsHit / totalShips) * 100;
		
		if(playerRounds(p) != 0) {
			System.out.println(playerName(p) + "'s träffprocent är: " + String.format("%.2f", hitP) + "%");
			System.out.println(
				"Du har gjort " + String.format("%.2f", dmgP) + "% skada på din motståndare " + opponentName(p) + "!");
			System.out.println("Du snittar "+ (int)(playerTime(p)/playerRounds(p)) + " sekunder per omgång.");
		}
		System.out.println();
	}
	
	public static void writeHighscore (int p) {
		int shipsHit = 0;
		
		for (BattleShip ship : opponentShips()) {
			shipsHit = shipsHit + ship.getHits();
		}
		double hitP = (shipsHit / playerShots(p) * 100);

		double ownShipsHit = 0;
		double ownShipsSize = 0;
		for (BattleShip ship : playerShips()) {
			ownShipsHit = ownShipsHit + ship.getHits();
			ownShipsSize = ownShipsSize + ship.getSize();
		}
		double ownHitP = (ownShipsHit / ownShipsSize * 100 );
		
		try {
		BufferedWriter writer = new BufferedWriter(new FileWriter
				("/Users/administrator/eclipse-workspace/sankaskapp/src/sankaskapp/Highscore", true));
		writer.write(playerName(p) + ",");
		writer.write(String.valueOf((int)playerShots(p)) + ",");
		writer.write(String.valueOf((int)hitP) + ",");
		writer.write(String.valueOf((int)(playerTime(p)/playerRounds(p))) + ",");
		writer.write(String.valueOf((int)ownHitP));
		writer.newLine();
		writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}
