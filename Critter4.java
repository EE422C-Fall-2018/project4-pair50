package assignment4;

public class Critter4 extends Critter{

	@Override
	public void doTimeStep() {
		
		walk(8);
		
	}

	@Override
	public boolean fight(String opponent) {
		
		return true;
	}

	public String toString() {
		return "4";
	}
}
