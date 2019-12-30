package sankaskapp;

import java.util.ArrayList;

public class BattleShip extends Ship {
	private int hits;
	private boolean sinked = false;
	private String[] shipC = new String[25];
	private ArrayList<String> coordList = new ArrayList<String>();
	private int shipX;
	private int shipY;
	//private String[][] shipArea;

	public BattleShip(String name, int size, String[][] shipArea) {
		super(name, size, shipArea);
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

	int getSize() {
		return size;
	}

	String[][] getArea() {
		return area;
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
		area = s;
	}

}

