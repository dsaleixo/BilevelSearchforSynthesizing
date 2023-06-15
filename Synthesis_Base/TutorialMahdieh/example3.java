package TutorialMahdieh;

import AIs.Interpreter;
import CFG.C;
import CFG.Direction;
import CFG.FactoryBase;
import CFG.For_S;
import CFG.N;
import CFG.Node;
import CFG.OpponentPolicy;
import CFG.S;
import CFG.S_S;
import CFG.Type;
import CFG_Actions.Attack;
import CFG_Actions.Harvest;
import CFG_Actions.Train;
import ai.core.AI;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class example3 extends example2 {

	public static Node getScript2() {//let's create a tree piece by piece, note that the command order matters
		C c2 = new C(new Attack(new OpponentPolicy("Closest")));
		C c1 = new C(new Train(new Type("Worker"),new Direction("EnemyDir"),new N("10")));
		C c0 = new C(new Harvest(new N("3")));
		S_S ss0 = new S_S(new S(c2),new S(c1));
		S_S ss1 = new S_S(new S(ss0),new S(c0));
		For_S for_s = new For_S(new S(ss1));
		return new S(for_s);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Node n = getScript0();
		System.out.println("Let the script below be called script0:");
		System.out.println(n.translateIndentation(1));
		System.out.println("script0 description");
		System.out.println("");
		System.out.println("\nthe \"for\" command is used to go through all the units in the game,\n"
				+ " thus being able to assign an action to each one independently, \n"
				+ " if a unit already has an action assigned, the current command will be ignored."
				);
		System.out.println("");
		System.out.println("the \"harvest(3)\" command assigns the resource collection command to workers until there are 3 units harvesting");
		System.out.println("");
		System.out.println("the command \"u.train(Worker,Enemy Dir,10)]\" assigns the command to create a worker unit to the base type unit, "
				+ "if it has enough resources"
				+ "	");
		System.out.println("");
		System.out.println("the u.attack(Closest) command tells all units that can attack and have no action assigned yet, attack the Closest enemy"
				+ "	");
		System.out.println("");
		System.out.println("run example2 to see its behavior in practice");
		System.out.println("");
		System.out.println("");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("to illustrate that order matters, let's swap the \"attack\" command with the \"harvest\" command");
		
		System.out.println("so we have script 1");
		Node script1  = getScript2();
		System.out.println(script1.translateIndentation(1));
		System.out.println("note that the \"harvest\" command will never be assigned to a \"worker\" unit, as the \"attack\" command has already been assigned");
		
		UnitTypeTable utt = new UnitTypeTable(2);//unit settings such as hp, range, speed and attack
		AI j= new Interpreter(utt,script1);
		AI adv= getAI("0", utt);
		String path_map = getMap("1");//
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		System.out.println("uncomment the last lines to see the match");
		double r = match(gs2,utt,0,max,j,adv,true);
		System.out.println("another useful command is to clean the script, it removes commands that are not being used. \n"
				+ "but for that you need to run the script first");
		FactoryBase f=new FactoryBase();
		script1.clear(null, f);
		System.out.println("so we have cleaning script 1");
		System.out.println(script1.translateIndentation(1));
		
		
	}
	
}
