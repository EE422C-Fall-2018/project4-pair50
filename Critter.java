package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	public static HashMap <Key, List<Critter>> positions  = new HashMap<Key, List<Critter> >();
	public static HashSet <String> c = new HashSet<String>();
	
	
	
	
	
	
	
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	private boolean moved;
	private boolean inFight;
	
	private final int step(Character type, int steps, int initVal) {
		
		if(type.equals('x')) {
			System.out.println("steps: x " + "step size: " + steps+ "initval" + initVal);
			initVal = initVal + steps;
//			if(initVal >= Params.world_width || initVal < 0) {
//				initVal = Math.abs(initVal % Params.world_width);
//			}
			if(initVal == -1) {
				initVal = Params.world_width-1;
			}
			if(initVal == -2) {
				initVal = Params.world_width-2;
			}
			if(initVal == Params.world_width) {
				initVal = 0;
			}
			if(initVal == Params.world_width+1) {
				initVal = 1;
			}
			
		}
		if(type.equals('y')) {
			System.out.println("steps: y " + "step size: " + steps+ " initval " + initVal);
			initVal = initVal + steps;
//			if(initVal >= Params.world_height || initVal < 0) {
//				initVal = Math.abs(initVal % Params.world_height);
//			}
			if(initVal == -1) {
				initVal = Params.world_width-1;
			}
			if(initVal == -2) {
				initVal = Params.world_width-2;
			}
			if(initVal == Params.world_width) {
				initVal = 0;
			}
			if(initVal == Params.world_width+1) {
				initVal = 1;
			}
			
		}
		System.out.println("new pos" + initVal);
		return initVal;
	}
	
	private final boolean hasCritterthere(int x, int y) {
		System.out.println("hascritterthere");
		Key pos = new Key(x,y);
//		Integer [] pos = new Integer [2];
//		pos[0] = x;
//		pos[1] = y;
		
		if(positions.get(pos).size() > 0){
			return true;
		}else {
			return false;
		}
	}
	
	
	protected final void walk(int direction) {//TEST
		int xpos;
		int ypos;
		this.energy = this.energy - Params.walk_energy_cost;
		if(!this.moved) {
			switch(direction) {
				case 0:
					
					xpos = step('x', 1, this.x_coord);
					ypos = this.y_coord;
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
						}
					}else {
						this.x_coord = xpos;
					}
					break;
					
				case 1:
					xpos = step('x', 1, this.x_coord); 
					ypos = step('y', -1, this.y_coord);
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 2:
					xpos = this.x_coord;
					ypos = step('y', -1, this.y_coord);
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 3:
					xpos = step('x', -1, this.x_coord);
					ypos = step('y', -1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 4:
					xpos = step('x', -1, this.x_coord);
					ypos = this.y_coord;
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 5:
					xpos = step('x', -1, this.x_coord); 
					ypos = step('y', 1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 6:
					xpos = this.x_coord;
					ypos = step('y', 1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 7:
					xpos = step('x', 1, this.x_coord);
					ypos = step('y', 1, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
					
			}
			this.moved = true;
		}
		
		
	}
	
	protected final void run(int direction) {
		int xpos;
		int ypos;
		this.energy = this.energy - Params.run_energy_cost;
		if(!this.moved) {
			switch(direction) {
				case 0:
					xpos = step('x', 2, this.x_coord);
					ypos = this.y_coord;
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 1:
					xpos = step('x', 2, this.x_coord); 
					ypos = step('y', -2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 2:
					xpos = this.x_coord;
					ypos = step('y', -2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 3:
					xpos = step('x', -2, this.x_coord); 
					ypos = step('y', -2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 4:
					xpos = step('x', -2, this.x_coord); 
					ypos = this.y_coord;
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 5:
					xpos = step('x', -2, this.x_coord); 
					ypos = step('y', 2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 6:
					xpos = this.x_coord;
					ypos = step('y', 2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
				case 7:
					xpos = step('x', 2, this.x_coord); 
					ypos = step('y', 2, this.y_coord);
					
					if(this.inFight) {
						if(!hasCritterthere(xpos,ypos)) {
							this.x_coord = xpos;
							this.y_coord = ypos;
						}
					}else {
						this.x_coord = xpos;
						this.y_coord = ypos;
					}
					break;
			}
			this.moved = true;
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		System.out.println("reproducing");
		if(!(this.energy > Params.min_reproduce_energy)) {
			return;
		}
		offspring.energy = this.energy/2;
		this.energy = (int)Math.ceil(this.energy/2);
		switch(direction) {
		case 0:
			
			offspring.x_coord = step('x', 1, this.x_coord);
			offspring.y_coord = this.y_coord;
			break;
		case 1:
			offspring.y_coord = step('y', -1, this.y_coord);
			offspring.x_coord = step('x', 1, this.x_coord); 			
			break;
		case 2:
			offspring.y_coord = step('y', -1, this.y_coord);
			offspring.x_coord = this.x_coord;
			break;
		case 3:
			offspring.y_coord = step('y', -1, this.y_coord);
			offspring.x_coord = step('x', -1, this.x_coord); 
			break;
		case 4:
			offspring.x_coord = step('x', -1, this.x_coord);
			offspring.y_coord = this.y_coord;
			break;
		case 5:
			offspring.y_coord = step('y', 1, this.y_coord);
			offspring.x_coord = step('x', -1, this.x_coord); 
			break;
		case 6:
			offspring.y_coord = step('y', 1, this.y_coord);
			offspring.x_coord = this.x_coord;
			break;
		case 7:
			offspring.y_coord = step('y', 1, this.y_coord);
			offspring.x_coord = step('x', 1, this.x_coord);
			break;
	}
		
		babies.add(offspring);
		
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String opponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		if(c.isEmpty()) {
			c.add("Algae");
			c.add("Craig");
			c.add("MyCritter1");
			/*c.add("MyCritter2");
			c.add("MyCritter3");
			c.add("MyCritter4");
			c.add("MyCritter5");*/
			c.add("MyCritter6");
			c.add("MyCritter7");
			c.add("ArvinCritter1");
		}
		
		//error no capital letter
		Class<?> fefe = null;
		if(!c.contains(critter_class_name)) {
			throw new InvalidCritterException(critter_class_name);
		}
		
		try {
			fefe  = Class.forName(Critter.myPackage + "." + critter_class_name);
			Constructor<?> construct = fefe.getConstructor();
			Object newcrit = construct.newInstance();
			
			((Critter) newcrit).x_coord = getRandomInt(Params.world_width);
			((Critter) newcrit).y_coord = getRandomInt(Params.world_height);
			((Critter) newcrit).energy = Params.start_energy;
			((Critter) newcrit).moved = false;
			System.out.println("new crit x coord:" + ((Critter)newcrit).x_coord);
			System.out.println("new crit y coord:" + ((Critter)newcrit).y_coord);
			population.add((Critter) newcrit);
			updatePositions();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		Class<?> crit = null;
		try {
			crit = Class.forName(Critter.myPackage +"." + critter_class_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Critter> instances = new java.util.ArrayList<Critter>();
 		for (Critter a : population) {
			if (crit.isInstance(a)) {
				instances.add(a);
			}
		}
		return instances;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {///?? do we need to update
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
			
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() { //???
		population.clear();
	}
	
	public static void updatePositions() {
		positions.clear();
		System.out.println("updating world");
		//Set<Integer[]> keys = positions.keySet();
		for(Critter c: population) {
			Key po = new Key(c.x_coord,c.y_coord);
//			Integer [] po = new Integer [2];
//			po[0]=c.x_coord; po[1] = c.y_coord;
			
			if(!(positions.containsKey(po))) {
				List <Critter> clist = new ArrayList<Critter>();
				clist.add(c);
				positions.put(po, clist);
				System.out.println("doesn't contain key");
			}else {
				System.out.println("contains key");
				List <Critter> clist = positions.get(po);
				clist.add(c);
				positions.put(po, clist);
			}
		}
	}
	
	public static void worldTimeStep() {
		
		for(Critter c : population) {
			c.moved = false;
			c.doTimeStep();
		}
		
		updatePositions();
		for(List<Critter> crit: positions.values()) {
			boolean aFight;
			boolean bFight;
			int aDice;
			int bDice;
			if(crit.size() > 1) {
				int nextOp = 1;
				Critter a = crit.get(0);
				Critter b = crit.get(nextOp);
				if((a.energy > 0) && (b.energy > 0) && (a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)){
					System.out.println("Fighting");
					System.out.println("a energy " + a.energy + " b energy " +b.energy);
					while(nextOp < crit.size()) {
						b = crit.get(nextOp);
						aFight = a.fight(b.toString());
						bFight = b.fight(a.toString());
						if(!aFight) {
							aDice = 0;
						}else {
							aDice = Critter.getRandomInt(a.energy);
						}
						if(!bFight) {
							bDice = 0;
						}else {
							bDice = Critter.getRandomInt(b.energy);
						}
						if(aDice >= bDice) { //remove loser?
							System.out.println("aDice is " + aDice + " bDice is " + bDice + "loser is b");
							a.energy = a.energy + (b.energy/2);
							b.energy = 0;
							System.out.println("a energy " + a.energy + "b energy " +b.energy);
						}
						if(bDice > aDice) {
							System.out.println("aDice is " + aDice + " bDice is " + bDice + "loser is a");
							b.energy = b.energy + (a.energy/2);
							a.energy = 0;
							System.out.println("a energy " + a.energy + " b energy " +b.energy);
							a = b;
							
						}
						nextOp ++;
					}
				}
			}
			
		}
		for(Critter c: population) {
			c.energy = c.energy - Params.rest_energy_cost;
		}
		for(Critter c: babies) {
			population.add(c);
		}
		babies.clear();
		
		for(int i=0;i<population.size();i++) {
			if(population.get(i).energy <= 0) {
				population.remove(i);
			}
		}
//		for(int i=0;i<Params.refresh_algae_count;i++) {
//			try {
//				Critter.makeCritter("Algae");//??? correct
//				
//			}
//			catch(InvalidCritterException e) {
//				e.printStackTrace();
//			}
//			
//		}
	}
	
	public static void displayWorld() { //multiple on one position
		System.out.print("+");
		for(int i = 0; i<Params.world_width;i++) {
			System.out.print("-");
		}
		System.out.println("+");
		//Set<Integer[]> keys = positions.keySet();
		for(int row=0;row<Params.world_height;row++) {
			System.out.print("|");
			//Integer [] pos = new Integer [2];
			
			for(int col=0; col < Params.world_width; col++) {
				Key pos = new Key(col,row);
//				pos[0]=col;
//				pos[1]=row;
				if(positions.containsKey(pos)) {
					List <Critter> c = positions.get(pos);
					Critter cr = c.get(0);
					System.out.print(cr.toString());
				}else {
					System.out.print(" ");
				}
				
//				boolean printed = false;
//				for(Critter c: population) {
//					if (c.x_coord==col && c.y_coord==row &&!printed) {
//						System.out.print(c.toString());
//						printed = true;
//					}
//				}
//				if(!printed) {
//					System.out.print(" ");
//				}
			}		
			System.out.println("|");
		}
		System.out.print("+");
		for(int i = 0; i<Params.world_width;i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}
}
