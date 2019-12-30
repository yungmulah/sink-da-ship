package sankaskapp;

import java.util.Comparator;

public class Player {
	String name;
	String shots;
	String percent;
	String playertime;
	String ownPercent;

	public Player(String name, String shots, String percent, String playertime, String ownPercent) {
		this.name = name;
		this.shots = shots;
		this.percent = percent;
		this.playertime = playertime;
		this.ownPercent = ownPercent;
	}

	public String toString() {
		return "Namn: " + this.name + " Antal skott: " + this.shots + " Träffprocent: " + this.percent + "%"
				+ " Tid per runda: " + this.playertime + "s" + " Andel beskjuten " + this.ownPercent + "%";
	}
}

class sortbyShots implements Comparator<Player> {
	public int compare(Player a, Player b) {
		return Integer.valueOf(a.shots) - Integer.valueOf(b.shots);
	}

}

class sortbyPercent implements Comparator<Player> {
	public int compare(Player a, Player b) {
		return Integer.valueOf(b.percent) - Integer.valueOf(a.percent);
	}
}

class sortbyPlayertime implements Comparator<Player> {
	public int compare(Player a, Player b) {
		return Integer.valueOf(a.playertime) - Integer.valueOf(b.playertime);
	}
}

class sortbyOwnP implements Comparator<Player> {
	public int compare(Player a, Player b) {
		return Integer.valueOf(a.ownPercent) - Integer.valueOf(b.ownPercent);
	}
}



