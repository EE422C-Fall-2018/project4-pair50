package assignment4;

public class Critter1 extends Critter{
	@Override
	public String toString() {return "1";}

	private int dir;
	private int sugma;
	private boolean hasMoved;


	public Critter1() {
		dir = Critter.getRandomInt(8);
		hasMoved = false;
		sugma = 0;
	}
	
	public boolean fight(String opponent) {
		if (hasMoved == false) {
			if(getEnergy() >= 100) {
				return true;
			}
			else if(getEnergy() < 100) {
				run(dir);
				return false;
			}
		}
		return true; 
	}


	public void doTimeStep() {

		hasMoved = false;
		if(getEnergy() < 500) { 
			walk(dir);
			hasMoved = true;
		}

		if(getEnergy() >= 500) {
			walk(dir);
			hasMoved = true;
			Critter1 baby = new Critter1();
			reproduce(baby, Critter.getRandomInt(8));
			sugma++;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public static void runStats(java.util.List<Critter> list) {
		int bofa = 0 ;
		for(Object obj : list) {
			Critter1 crit = (Critter1) obj;
			bofa += crit.sugma;
		}
		System.out.println(list.size() + "total Critter1s");
		System.out.println("Total number of offspring: " + bofa);
	}
}
