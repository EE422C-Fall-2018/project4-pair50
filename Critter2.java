package assignment4;

public class Critter2 extends Critter {

	private int dir;
	private boolean hasMoved;

	@Override 
	public String toString() { return "2"; }

	public  Critter2() {
		dir = Critter.getRandomInt(2);
	}

	public boolean fight(String opponent) {
		if (!hasMoved) {
			if (opponent.equals("project4. Critter2")) { walk(dir); return false; }
		}
		return true;
	} 

	@Override 
	public void doTimeStep() {
		
		walk(dir);
		hasMoved = true;
		
	
		if (getEnergy() > 10) {
			 Critter2 child = new  Critter2();
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

	


}