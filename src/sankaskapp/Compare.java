package sankaskapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Compare extends Player {

	public Compare(String name, String shots, String percent, String playertime, String ownPercent) {
		super(name, shots, percent, playertime, ownPercent);
	}

	protected static List<Player> player = new ArrayList<Player>();
	protected static Scanner scan = new Scanner(System.in);

	public static void reader() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("/Users/administrator/eclipse-workspace/sankaskapp/src/sankaskapp/Highscore"));

			String currentLine = reader.readLine();
			String name, shots, percent, playertime, ownPercent;

			while (currentLine != null) {
				String[] storeSplit = currentLine.split(",");
				name = storeSplit[0];
				shots = storeSplit[1];
				percent = storeSplit[2];
				playertime = storeSplit[3];
				ownPercent = storeSplit[4];
				player.add(new Player(name, shots, percent, playertime, ownPercent));
				currentLine = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void shotSort() {
		reader();
		Collections.sort(player, new sortbyShots());
		System.out.println();
		for (Player player : player) {
			System.out.println(player);
		}
		player.removeAll(player);
		System.out.println();
		System.out.println("Tryck Enter för att gå tillbaka.");
		scan.nextLine();
	}
	
	public static void percentSort() {
		reader();
		Collections.sort(player, new sortbyPercent());
		System.out.println();
		for (Player player : player) {
			System.out.println(player);
		}
		player.removeAll(player);
		System.out.println();
		System.out.println("Tryck Enter för att gå tillbaka.");
		scan.nextLine();
	}

	public static void timeSort() {
		reader();
		Collections.sort(player, new sortbyPlayertime());
		System.out.println();
		for (Player player : player) {
			System.out.println(player);
		}
		player.removeAll(player);
		System.out.println();
		System.out.println("Tryck Enter för att gå tillbaka.");
		scan.nextLine();
	}
	
	public static void damageSort() {
		reader();
		Collections.sort(player, new sortbyOwnP());
		System.out.println();
		for (Player player : player) {
			System.out.println(player);
		}
		player.removeAll(player);
		System.out.println();
		System.out.println("Tryck Enter för att gå tillbaka.");
		scan.nextLine();
	}
}
