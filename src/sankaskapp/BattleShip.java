package sankaskapp;

import java.util.ArrayList;

public class BattleShip {
	private String name;
	private int size;
	private String[][] shipArea;
	private int hits;
	private boolean sinked = false;
	private String[] shipC = new String[25];
	private ArrayList<String> coordList = new ArrayList<String>();
	private int shipX;
	private int shipY;

	public BattleShip(String name, int size, String[][] shipArea) {
		setName(name);
		setSize(size);
		setShipArea(shipArea);
	}
	
	void setShipArea(String[][] shipArea) {
		this.shipArea = shipArea;
	}

	int getHits() {
		return hits;
	}

	boolean sinked() {
		return sinked;
	}

	public String[] getShipC() {
		return shipC;
	}

	void setHits() {
		hits++;
	}

	void isSinked() {
		sinked = true;
	}

	void setShipC(String c) {
		coordList.add(c);
		shipC = coordList.toArray(shipC);
	}

	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}

	int getSize() {
		return size;
	}
	
	void setSize(int size) {
		this.size = size;
	}

	String[][] getArea() {
		return shipArea;
	}
	
	void setShipX(int x) {
		shipX = x;
	}
	
	void setShipY(int y) {
		shipY = y;
	}
	
	int getShipX() {
		return shipX;
	}
	
	int getShipY() {
		return shipY;
	}
	
	void rotateShipClockwise(BattleShip ship) {
		String[][] rotated = new String[ship.getShipX()][ship.getShipY()];
		for(int i = 0; i < ship.getShipX(); i++) {
			for(int j = 0; j < ship.getShipY(); j++){
				rotated[i][j] = ship.getArea()[ship.getShipY()-j-1][i];
			}
		}
		ship.setArea(rotated);
		int x = getShipX();
		int y = getShipY();
		setShipX(y);
		setShipY(x);
	}
	
	void rotateShipCounterClockwise(BattleShip ship) {
		String[][]counterRotated = new String[ship.getShipX()][ship.getShipY()];
		for(int i = 0; i < ship.getShipX(); i++) {
			for(int j = 0; j < ship.getShipY(); j++){
				counterRotated[i][j] = ship.getArea()[j][ship.getShipX()-i-1];
			}
		}
		ship.setArea(counterRotated);
		int x = getShipX();
		int y = getShipY();
		setShipX(y);
		setShipY(x);
	}
	
	void setArea(String[][] s) {
		shipArea = s;
	}

}

