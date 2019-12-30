package sankaskapp;

import java.util.*;

public class RunGame {
	private static Scanner scan = new Scanner(System.in);
	protected static String[][][] grid1;
	protected static String[][][] grid2;
	protected static int player1;
	protected static int player2;
	protected static List<BattleShip> ships1 = new ArrayList<>();
	protected static List<BattleShip> ships2 = new ArrayList<>();
	protected static List<String> player1Shots = new ArrayList<String>();
	protected static List<String> player2Shots = new ArrayList<String>();
	protected static List<String> validPlacements = new ArrayList<String>();
	protected static BattleShip ship;

	public static void computerPlaceShips(BattleShip ship) {
		boolean validP = false;
		Random rand = new Random();

		while (!validP) {
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
			int r = rand.nextInt(2);
			boolean shipCol = false;

			if (r == 0) {
				ship.rotateShipClockwise(ship);
			}

			String s = String.valueOf(x) + String.valueOf(y);

			if (validPlacement(s, ship)) {
				outerloop: for (int q = y; q < y + ship.getShipY(); q++) {
					for (int p = x; p < x + ship.getShipX(); p++) {
						if (shipCollision(p, q)) {
							shipCol = true;
							break outerloop;
						}
					}
				}

				if (!shipCol) {
					int w = 0;
					for (int l = y; l < y + ship.getShipY(); l++) {
						int z = 0;
						for (int k = x; k < x + ship.getShipX(); k++) {
							String n = String.valueOf(k) + String.valueOf(l);
							markBoard(n, ship.getArea()[w][z], 0);
							if (ship.getArea()[w][z].equals("#")) {
								ship.setShipC(n);
							}
							validP = true;
							z++;
						}
						w++;
					}
				}
			} else {
				if (r == 0) {
					ship.rotateShipCounterClockwise(ship);
				}
			}
		}
	}
	
	public static int checkSize(String[][] array, int x, int y) {
		int size = 0;
		for(int k = 0; k < y; k++) {
			for(int l = 0; l < x; l++) {
				if(array[k][l].equals("#")) {
					size++;
				}
			}
		}
		return size;
	}

	public static void specialShips() {
		int size = 0;
		int shipX = 0;
		int shipY = 0;
		
		//Skapa det första special skeppet
		//Ändra shipX/Y för att ändra arean som skeppet ska få plats i.
		//Gör du arean för stor så kan det vara svårt att placera skeppet
		//Om andra skepp ligger nära.
		shipX = 5;
		shipY = 2;
		String[][] ship1 = new String[shipY][shipX];
		for (int y = 0; y < shipY; y++) {
			for (int x = 0; x < shipX; x++) {
				ship1[y][x] = "~";
			}
		}
		//Genom att ändra dessa [y][x] koordinater så kommer skeppet att 
		//se olika ut. Koordinaterna nedan skapar exempelskeppet i pdf:n.
		//Genom att lägga till eller ta bort rader nedan så ändras den
		//totala storleken på skeppet.
		ship1[1][0] = "#";
		ship1[1][1] = "#";
		ship1[1][2] = "#";
		ship1[1][3] = "#";
		ship1[0][3] = "#";
		ship1[0][4] = "#";
		//Sedan löser resterande 5 rader att skeppet läggs till där
		//du har placerat det. Fortsätt att ändra de andra skeppen om 
		//så önskas.
		size = checkSize(ship1, shipX, shipY);
		ship = new BattleShip("Testskepp", size, ship1);
		playerShips().add(ship);
		ship.setShipX(shipX);
		ship.setShipY(shipY);

		//Skapa skepp nr 2...
		shipX = 5;
		shipY = 1;
		String[][] ship2 = new String[shipY][shipX];
		for (int y = 0; y < shipY; y++) {
			for (int x = 0; x < shipX; x++) {
				ship2[y][x] = "~";
			}
		}
		ship2[0][0] = "#";
		ship2[0][1] = "#";
		ship2[0][2] = "#";
		ship2[0][3] = "#";
		ship2[0][4] = "#";
		size = checkSize(ship2, shipX, shipY);
		ship = new BattleShip("Regalskeppet Vasa", size, ship2);
		playerShips().add(ship);
		ship.setShipX(shipX);
		ship.setShipY(shipY);
		
		//Skepp nr 3
		shipX = 5;
		shipY = 1;
		String[][] ship3 = new String[shipY][shipX];
		for (int y = 0; y < shipY; y++) {
			for (int x = 0; x < shipX; x++) {
				ship3[y][x] = "~";
			}
		}
		ship3[0][0] = "#";
		ship3[0][1] = "#";
		ship3[0][2] = "#";
		ship3[0][3] = "#";
		ship3[0][4] = "#";
		size = checkSize(ship3, shipX, shipY);
		ship = new BattleShip("Regalskeppet Vasa", size, ship3);
		playerShips().add(ship);
		ship.setShipX(shipX);
		ship.setShipY(shipY);
		
		//Skepp nr 4
		shipX = 5;
		shipY = 1;
		String[][] ship4 = new String[shipY][shipX];
		for (int y = 0; y < shipY; y++) {
			for (int x = 0; x < shipX; x++) {
				ship4[y][x] = "~";
			}
		}
		ship4[0][0] = "#";
		ship4[0][1] = "#";
		ship4[0][2] = "#";
		ship4[0][3] = "#";
		ship4[0][4] = "#";
		size = checkSize(ship4, shipX, shipY);
		ship = new BattleShip("Regalskeppet Vasa", size, ship4);
		playerShips().add(ship);
		ship.setShipX(shipX);
		ship.setShipY(shipY);
		
		//Skepp nr 5
		shipX = 5;
		shipY = 1;
		String[][] ship5 = new String[shipY][shipX];
		for (int y = 0; y < shipY; y++) {
			for (int x = 0; x < shipX; x++) {
				ship5[y][x] = "~";
			}
		}
		ship5[0][0] = "#";
		ship5[0][1] = "#";
		ship5[0][2] = "#";
		ship5[0][3] = "#";
		ship5[0][4] = "#";
		size = checkSize(ship5, shipX, shipY);
		ship = new BattleShip("Regalskeppet Vasa", size, ship5);
		playerShips().add(ship);
		ship.setShipX(shipX);
		ship.setShipY(shipY);
	}

	public static void standardShips() {
		String[][] Regal = new String[1][5];
		ship = new BattleShip("Regalskeppet Vasa", 5, fillArray(Regal));
		playerShips().add(ship);
		ship.setShipX(5);
		ship.setShipY(1);

		for (int i = 0; i < 2; i++) {
			String[][] Milit = new String[1][4];
			ship = new BattleShip("en militärbåt", 4, fillArray(Milit));
			playerShips().add(ship);
			ship.setShipX(4);
			ship.setShipY(1);
		}
		for (int i = 0; i < 2; i++) {
			String[][] Segel = new String[1][3];
			ship = new BattleShip("en segelbåt", 3, fillArray(Segel));
			playerShips().add(ship);
			ship.setShipX(3);
			ship.setShipY(1);
		}
		for (int i = 0; i < 4; i++) {
			String[][] Jolle = new String[1][2];
			ship = new BattleShip("en jolle", 2, fillArray(Jolle));
			playerShips().add(ship);
			ship.setShipX(2);
			ship.setShipY(1);
		}
	}

	public static String[][] fillArray(String[][] s) {
		for (int i = 0; i < s.length; i++) {
			s[0][i] = "#";
		}
		return s;
	}

	public static void fillGrid() {
		grid1 = new String[2][10][10];
		grid2 = new String[2][10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				grid2[1][i][j] = "~";
				grid2[0][i][j] = "~";
				grid1[1][i][j] = "~";
				grid1[0][i][j] = "~";
			}
		}
	}

	public static boolean allShipsSinked() {
		int k = 0;
		for (BattleShip s : opponentShips()) {
			if (s.sinked()) {
				k++;
			}
		}
		if (k == opponentShips().size()) {
			return true;
		}
		return false;
	}

	public static boolean validShot(String s) {
		s = ctd(s);
		if (playerShots().contains(s) && s.matches("[0-9]" + "[0-9]") && s.length() == 2) {
			//playerShots().remove(s);
			return true;
		}
		return false;
	}

	public static void createList() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				player1Shots.add(String.valueOf(j) + String.valueOf(i));
				player2Shots.add(String.valueOf(j) + String.valueOf(i));
				validPlacements.add(String.valueOf(j) + String.valueOf(i));
			}
		}
	}

	public static void printBoard(int l) {
		System.out.format(" ");
		for (char i = 'A'; i < 'K'; i++) {
			System.out.format("%2c", i);
		}
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.print(i);
			for (int j = 0; j < 10; j++) {
				System.out.format("%2s", playerGrid()[l][i][j]);
			}
			System.out.print("\n");
		}
		System.out.println();
	}

	public static void hitRatio(String s) {
		int hits = ship.getHits();
		int totalShots = 0;
		double ratio = hits / totalShots;

		System.out.print(ratio);
	}

	public static void markBoard(String s, String m, int p) {
		s = ctd(s);
		int x = Character.getNumericValue(s.charAt(0));
		int y = Character.getNumericValue(s.charAt(1));
		playerGrid()[p][y][x] = m;
	}

	public static void markOpponentBoard(String s, String m, int p) {
		s = ctd(s);
		int x = Character.getNumericValue(s.charAt(0));
		int y = Character.getNumericValue(s.charAt(1));
		opponentGrid()[p][y][x] = m;
	}

	public static boolean computerShoot() {
		Random rand = new Random();
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		String s = String.valueOf(y) + String.valueOf(x);

		while (!validShot(s)) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			s = String.valueOf(y) + String.valueOf(x);
		}
		if (isHit(s) != null) {
			isHit(s).setHits();
			System.out.println("Datorn träffade " + isHit(s).getName() + "!");
			System.out.println();
			if (isHit(s).getHits() == isHit(s).getSize()) {
				isHit(s).isSinked();
				System.out.println(isHit(s).getName() + " sjönk!");
				System.out.println();
				if (allShipsSinked()) {
					markOpponentBoard(s, "x", 0);
					return false;
				}
			}
			markOpponentBoard(s, "x", 0);
			return true;
		}
		markOpponentBoard(s, "o", 0);
		System.out.println("Datorn bommade.");
		System.out.println();
		return false;
	}

	public static boolean shoot() {
		System.out.print("Vilken koordinat vill du skjuta på?: ");
		String s = scan.next();
		scan.nextLine();
		System.out.println();
		while (!validShot(s) && !validC(s)) {
			System.out.println("Felaktigt skott, försök igen");
			s = scan.next();
			scan.nextLine();
		}
		if (isHit(s) != null) {
			markOpponentBoard(s, "x", 0);
			markBoard(s, "x", 1);
			isHit(s).setHits();
			if (isHit(s).getHits() == isHit(s).getSize()) {
				isHit(s).isSinked();
				System.out.println("Du sänkte " + isHit(s).getName() + "!");
				System.out.println();
				if (allShipsSinked()) {
					return true;
				}
				System.out.println("Du får skjuta igen.");
				System.out.println();
				printBoard(1);
			} else {
				markOpponentBoard(s, "x", 0);
				markBoard(s, "x", 1);
				System.out.println();
				System.out.println("Träff, du får skjuta igen.");
				System.out.println();
				printBoard(1);
			}
			return false;
		} else {
			markOpponentBoard(s, "o", 0);
			markBoard(s, "o", 1);
			System.out.println("Bom");
			System.out.println();
			printBoard(1);
			System.out.println("Nästa spelares tur.");
			System.out.println();
			return true;
		}
	}

	public static BattleShip isHit(String s) {
		s = ctd(s);
		for (BattleShip ship : opponentShips()) {
			for (String coordinates : ship.getShipC()) {
				if (s.equals(coordinates)) {
					return ship;
				}
			}
		}
		return null;
	}

	public static String[][][] playerGrid() {
		if (player1 == 1) {
			return grid1;
		}
		return grid2;
	}

	public static String[][][] opponentGrid() {
		if (player1 == 1) {
			return grid2;
		}
		return grid1;
	}

	public static List<BattleShip> playerShips() {
		if (player1 == 1) {
			return ships1;
		}
		return ships2;
	}

	public static List<BattleShip> opponentShips() {
		if (player1 == 1) {
			return ships2;
		}
		return ships1;
	}

	public static List<String> playerShots() {
		if (player1 == 1) {
			return player1Shots;
		}
		return player2Shots;
	}

	public static boolean validPlacement(String s, BattleShip ship) {
		int x = Character.getNumericValue(s.charAt(0));
		int y = Character.getNumericValue(s.charAt(1));
		for (int k = x; k < x + ship.getShipX(); k++) {
			for (int l = y; l < y + ship.getShipY(); l++) {
				if (!validPlacements.contains(String.valueOf(k) + String.valueOf(l))) {
					return false;
				}
			}
		}
		return true;
	}

	public static void placeShip(BattleShip ship) {
		boolean validP = false;
		
		while (!validP) {
			for (int y = 0; y < ship.getShipY(); y++) {
				for (int x = 0; x < ship.getShipX(); x++) {
					if (ship.getArea()[y][x].equals("~")) {
						System.out.print(" ");
					} else {
						System.out.print(ship.getArea()[y][x]);
					}
				}
				System.out.println();
			}
			System.out.println();
			System.out.print("Skriv in vilken koordinat du vill placera " + ship.getName() + " på: ");
			String s = scan.next();
			scan.nextLine();
			
			while (!validShot(s) && validC(s)) {
				System.out.print("Felaktigt koordinat, försök igen: ");
				s = scan.next();
				scan.nextLine();
			}
			s = ctd(s);
			System.out.println("Vilken riktining vill du att " + ship.getName() + " skall ha?");
			System.out.print("H för horisontell, L för lodrät: ");
			char r = scan.next().charAt(0);
			scan.nextLine();
			System.out.println();
			
			while(r != 'H' && r != 'h' && r != 'L' && r != 'l') {
				System.out.print("Vänligen skriv in H eller L:");
				r = scan.next().charAt(0);
				//scan.nextLine();
				System.out.println();
			}
			int i = Character.getNumericValue(s.charAt(0)); // x
			int j = Character.getNumericValue(s.charAt(1)); // y
			boolean shipCol = false;

			if (r == 'L' || r == 'l') {
				ship.rotateShipClockwise(ship);
			}
			if (validPlacement(s, ship)) {
				outerloop: for (int p = i; p < i + ship.getShipX(); p++) {
					for (int q = j; q < j + ship.getShipY(); q++) {
						if (shipCollision(p, q)) {
							shipCol = true;
							System.out.println(ship.getName() + " ligger för nära ett annat skepp.");
							System.out.println("Försök igen.");
							System.out.println();
							break outerloop;
						}
					}
				}
				if (!shipCol) {
					int x = 0;
					for (int k = i; k < i + ship.getShipX(); k++) {
						int y = 0;
						for (int l = j; l < j + ship.getShipY(); l++) {
							String n = String.valueOf(k) + String.valueOf(l);
							markBoard(n, ship.getArea()[y][x], 0);
							if (ship.getArea()[y][x].equals("#")) {
								ship.setShipC(n);
								playerShots().remove(n);
							}
							validP = true;
							y++;
						}
						x++;
					}
				}
			} else {
				System.out.println("Felaktig placering, försök igen.");
				System.out.println();
				if (r == 'L' || r == 'l') {
					ship.rotateShipCounterClockwise(ship);
				}
			}
		}
	}

	public static boolean shipCollision(int x, int y) {
		for (int p = x - 1; p < x + 2; p++) {
			for (int q = y - 1; q < y + 2; q++) {
				for (BattleShip ship : playerShips()) {
					for (String s : ship.getShipC()) {
						if (s != null) {
							String c = String.valueOf(p) + String.valueOf(q);
							if (s.equals(c)) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean validC(String s) {
		if (Character.toString(s.charAt(0)).matches("[a-j]") || Character.toString(s.charAt(0)).matches("[A-J]")) {
			return true;
		}
		return false;
	}

	public static String ctd(String s) {
		if (s.length() != 2) {
			return s;
		}
		char c = s.charAt(0);
		int i = Character.getNumericValue(s.charAt(1));
		String r = null;
		switch (c) {
		case 'a':
		case 'A':
			r = "0" + i;
			break;
		case 'b':
		case 'B':
			r = "1" + i;
			break;
		case 'c':
		case 'C':
			r = "2" + i;
			break;
		case 'd':
		case 'D':
			r = "3" + i;
			break;
		case 'e':
		case 'E':
			r = "4" + i;
			;
			break;
		case 'f':
		case 'F':
			r = "5" + i;
			break;
		case 'g':
		case 'G':
			r = "6" + i;
			break;
		case 'h':
		case 'H':
			r = "7" + i;
			break;
		case 'i':
		case 'I':
			r = "8" + i;
			break;
		case 'j':
		case 'J':
			r = "9" + i;
			break;
		default:
			return s;
		}
		return r;
	}
}