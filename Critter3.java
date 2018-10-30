package assignment4;

public class Critter3 extends Critter{

	@Override
	public void doTimeStep() {
		walk(0);

	}

	@Override
	public boolean fight(String opponent) {
		return true;
	}

	public String toString() {
		return "3";
	}
	
}
