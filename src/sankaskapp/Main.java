package sankaskapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements MenuItem {

	private static Scanner scan = new Scanner(System.in);
	protected String title;
	List<MenuItem> items;

	public Main(String title) {
		items = new ArrayList<>();
		this.title = title;
	}

	public void add(MenuItem item) {
		items.add(item);
	}

	public String getTitle() {
		return title;
	}

	public void execute() {
		MenuItem toExecute = null;
		int index = 0;
		System.out.println(getTitle());
		System.out.println("=========");

		for (MenuItem item : items) {
			if (item.getTitle().equals(this.getTitle())) {
				toExecute = item;
			}

			System.out.println(index + ". " + item.getTitle());
			index++;

		}
		System.out.print("Ditt val: ");
		int choice = scan.nextInt();
		toExecute = items.get(choice);
		toExecute.execute();
	}

	public static void main(String[] args) {
		Main mainMenu = new Main("HUVUDMENY");
		Main playMenu = new Main("SPELMENY");

		mainMenu.add(new AbstractMenuItem("Avsluta") {

			public void execute() {
				System.exit(0);
			}
		});
		mainMenu.add(new AbstractMenuItem("Spela spelet") {
			public void execute() {

				playMenu.execute();
			}
		});
		mainMenu.add(new AbstractMenuItem("Spelarstatistik") {
			public void execute() {
				Compare.main(args);
				System.out.println();
				mainMenu.execute();
			}
		});

		playMenu.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
				System.out.println();
				mainMenu.execute();
			}

		});
		playMenu.add(new AbstractMenuItem("Spela mot en vän") {
			public void execute() {
				PvP.main(args);
				mainMenu.execute();
			}

		});
		playMenu.add(new AbstractMenuItem("Spela mot datorn") {
			public void execute() {
				PvE.main(args);
				mainMenu.execute();
				System.out.println();

			}

		});

		mainMenu.execute();

	}
}