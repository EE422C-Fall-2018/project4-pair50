package assignment4;

public class ArvinCritter1 extends Critter{
	@Override
	public String toString() {return "A";}

	private int dir;
	private int numFights;
	private boolean hasMoved;


	public ArvinCritter1() {
		dir = Critter.getRandomInt(8);
		hasMoved = false;
		numFights = 0;
	}
	
	public boolean fight(String opponent) {
		if (opponent.equals("project4.Alage")) { 
			return true;
		}
		if (hasMoved == false) {
			if(getEnergy() >= 100) {
				numFights += 1;
				return true;
			}
			else if(getEnergy() < 100) {
				run(dir);
				return false;
			}
		}
		numFights += 1;
		return true; 
	}


	public void doTimeStep() {

		hasMoved = false;
		if(getEnergy() < 10) { 
			walk(dir);
			hasMoved = true;
		}

		if (getEnergy() >= 10 && getEnergy() < 50) {
			walk(dir);
			hasMoved = true;
		}

		if(getEnergy() >= 50) {
			walk(dir);
			hasMoved = true;
			ArvinCritter1 baby = new ArvinCritter1();
			reproduce(baby, Critter.getRandomInt(8));
		}
		dir = getEnergy() * Critter.getRandomInt(8) % 8;
	}
	
	public static void runStats(java.util.List<Critter> list) {
		int total_fights = 0 ;
		for(Object obj : list) {
			ArvinCritter1 crit = (ArvinCritter1) obj;
			total_fights += crit.numFights;
		}
		System.out.println("Total number of ArvinCritter1s: " + list.size());
		System.out.println("Total number of fights: " + total_fights);
	}
}
