package assignment4;

public class ArvinCritter2 extends Critter {

	private int dir;
	private boolean hasMoved;

	@Override 
	public String toString() { return "B"; }

	public ArvinCritter2() {
		dir = Critter.getRandomInt(2);
	}

	public boolean fight(String opponent) {
		if (!hasMoved) {
			if (opponent.equals("project4.ArvinCritter2")) { walk(dir); return false; }
		}
		return true;
	} 

	@Override 
	public void doTimeStep() {
		
		walk(dir);
		hasMoved = true;
		
	
		if (getEnergy() > 10) {
			ArvinCritter2 child = new ArvinCritter2();
			reproduce(child, Critter.getRandomInt(8));
		}	

			int new_dir = Critter.getRandomInt(2);
			switch(new_dir) {
				case 0: 
					dir = 2; 
				case 1: 
					dir = 6;
			}
			
	}

	public static void runStats(java.util.List<Critter> list) {
		int num_crits = 0;
		int avg_energy = 0;
		for (Object obj : list) {
			num_crits ++;
			ArvinCritter2 r = (ArvinCritter2) obj;
			avg_energy += r.getEnergy();
		}
		avg_energy /= num_crits;
		System.out.println("" + num_crits + " total ArvinCritter2       ");
		System.out.print("Average energy: " + avg_energy + "\n");
	}


}