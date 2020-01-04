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
		int choice;
		
		do {
			System.out.println();
			System.out.print("Välj ett av alternativen: ");
			while(!scan.hasNextInt()) {
				System.out.print("Välj ett av alternativen: ");
				scan.next();
				System.out.println();
			}
			choice = scan.nextInt();
		} while(choice < 0 || choice > index-1);
		
		System.out.println();
		toExecute = items.get(choice);
		toExecute.execute();
	}

	public static void main(String[] args) {
		Main mainMenu = new Main("HUVUDMENY");
		Main playMenu = new Main("SPELMENY");
		Main statMenu = new Main("SORTERINGSALTERNATIV");

		mainMenu.add(new AbstractMenuItem("Avsluta") {
			public void execute() {
				System.out.println();
				System.out.println("Utvecklat av Axel Karlström och David Envall, IT1.");
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
				statMenu.execute();
			}
		});

		playMenu.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
				mainMenu.execute();
			}

		});
		playMenu.add(new AbstractMenuItem("Spela mot en vän") {
			public void execute() {
				System.out.println();
				PvP.main(args);
				mainMenu.execute();
			}

		});
		playMenu.add(new AbstractMenuItem("Spela mot datorn") {
			public void execute() {
				System.out.println();
				PvE.main(args);
				mainMenu.execute();
			}
		});
		
		statMenu.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
				mainMenu.execute();
			}
		});
		statMenu.add(new AbstractMenuItem("Lägst antal skott") {
			public void execute() {
				Compare.shotSort();
				statMenu.execute();
			}
		});
		statMenu.add(new AbstractMenuItem("Högst träffprocent") {
			public void execute() {
				Compare.percentSort();
				statMenu.execute();
			}
		});
		statMenu.add(new AbstractMenuItem("Minst tid per runda") {
			public void execute() {
				Compare.timeSort();
				statMenu.execute();
			}
		});
		statMenu.add(new AbstractMenuItem("Lägst andel av egna flottan som var beskjuten") {
			public void execute() {
				Compare.damageSort();
				statMenu.execute();
			}
		});
		
		mainMenu.execute();

	}
}
