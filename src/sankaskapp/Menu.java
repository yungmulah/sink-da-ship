package sankaskapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuItem {
	/**
	 * Skapar en tom meny med den givna rubriken.
	 */
	private static Bok book = new Bok();
	private static Film film = new Film();
	private static Scanner scan = new Scanner(System.in);
	protected String title;
	List<MenuItem> items;

	public Menu(String title) {
		items = new ArrayList<>();
		this.title = title;
	}

	/**
	 * Lägger till ett menyval till menyn.
	 */
	public void add(MenuItem item) {
		items.add(item);
	}

	public String getTitle() {
		return title;
	}

	/**
	 * Exekverar menyn enligt loopen definierad i punkterna (1) till (4). (1)
	 * Skriver ut menyns rubrik med stora bokstäverunderstruket med ’=’. Därefter
	 * följer en numrerad lista över alla menyelement i denna meny, numrerade från
	 * 0. (2) Användaren får sedan välja ett av alternativen genom att ange talet
	 * framför menyvalet. Vad händer om man inte anger ett giltigt tal? Användarens
	 * menyval exekveras. (3) Om menyval 0 valts så returnerar metoden. 0 motsvarar
	 * alltså alltid av avsluta/tillbaka/återgå. (4) gå till (1)
	 */
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

			// ta in input.

			// Item toExecute = items.get(val)
			// toExecute.execute();
		}
		System.out.print("Ditt val: ");
		int choice = scan.nextInt();
		toExecute = items.get(choice);
		toExecute.execute();
	}

	public static void main(String[] args) {
		Menu mainMenu = new Menu("HUVUDMENY");
		Menu stockMenu = new Menu("LÄGG TILL NY VARA");
		Menu cartMenu = new Menu("VARULISTA");

		mainMenu.add(new AbstractMenuItem("Avsluta") { // anonymkla​ ss

			public void execute() { // Gör ingenting.
			}
		});
		mainMenu.add(new AbstractMenuItem("Varulista") { // anonymkla​ ss
			public void execute() {
				System.out.println();
				System.out.println("Varunummer: " + book.getVarunummer());
				System.out.println("Titel: " + book.getTitel());
				System.out.println("Författaree: " + book.getForfattare());
				System.out.println("Pris: " + book.getPris());
				System.out.println("Miljömärkt: " + book.getMiljomarkt());
				System.out.println();

				mainMenu.execute();
			}
		});
		mainMenu.add(new AbstractMenuItem("Lägg till ny vara") {
			public void execute() {
				System.out.println();
				stockMenu.execute();
			}
		});
		stockMenu.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
				System.out.println();
				mainMenu.execute();
			}

		});
		stockMenu.add(new AbstractMenuItem("Bok") {
			public void execute() {
				System.out.println();
				System.out.print("Varunummer: ");
				int number = scan.nextInt();
				scan.nextLine();

				System.out.print("Titel: ");
				String titel = scan.nextLine();

				System.out.print("Författare: ");
				String author = scan.nextLine();

				System.out.print("Pris: ");
				double price = scan.nextDouble();
				scan.nextLine();

				System.out.print("Miljoemarkt: ");
				String miljoM = scan.nextLine();
				
				System.out.println();

				book = new Bok(number, titel, author, price, miljoM);
				stockMenu.execute();

			}

		});
		stockMenu.add(new AbstractMenuItem("Film") {
			public void execute() {
				System.out.println();
				System.out.print("Varunummer: ");
				int number = scan.nextInt();
				scan.nextLine();

				System.out.print("Titel: ");
				String titel = scan.nextLine();

				System.out.print("Regissör: ");
				String regi = scan.nextLine();

				System.out.print("Pris: ");
				double price = scan.nextDouble();
				scan.nextLine();

				System.out.print("Miljoemarkt: ");
				String miljoM = scan.nextLine();
				
				System.out.println();

				film = new Film(number, titel, regi, price, miljoM);
				stockMenu.execute();
			}

		});
		stockMenu.add(new AbstractMenuItem("Klaeder") {
			public void execute() {
			}

		});
		stockMenu.add(new AbstractMenuItem("Mat") {
			public void execute() {
			}

		});
		cartMenu.add(new AbstractMenuItem("Tillbaka") {
			public void execute() {
				System.out.println();
				mainMenu.execute();
			}

		});

		// mainMenu.add(stockMenu); // Wow, en cirkulär meny!
		mainMenu.execute();

	}
}


