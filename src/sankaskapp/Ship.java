package sankaskapp;

public abstract class Ship {
	protected String name;
	protected int size;
	protected String[][] area;
	
	abstract int getHits();
	abstract boolean sinked();
	abstract String[] getShipC();
	abstract String getName();
	abstract int getSize();
	//abstract String[][] shipArea();
	
	public Ship(String name, int size ,String[][] area) {
		this.name = name;
		this.size = size;
		this.area = area;
	}
}