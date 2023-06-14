package TutorialMahdieh;

import CFG_Actions.*;
import ai.core.AI;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;
import AIs.Interpreter;
import CFG.*;

public class example2 extends example1 {

	public static Node getScript0() {//let's create a tree piece by piece, note that the command order matters
		C c0 = new C(new Attack(new OpponentPolicy("Closest")));
		C c1 = new C(new Train(new Type("Worker"),new Direction("EnemyDir"),new N("10")));
		C c2 = new C(new Harvest(new N("3")));
		S_S ss0 = new S_S(new S(c2),new S(c1));
		S_S ss1 = new S_S(new S(ss0),new S(c0));
		For_S for_s = new For_S(new S(ss1));
		return new S(for_s);
		
	}

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Control control= new Control();//the main function of this class is to transform an AST (abstract sytax tree) 
									//into a string and vice versa
		control.PrintGrmmar();
		System.out.println("");
		System.out.println("");
		Node script =getScript0();
		System.out.println("First Script");
		System.out.println(script.translate());//print the program in one line
		System.out.println("");
		System.out.println("");
		System.out.println("print the indented code, it's easier to understand");
		System.out.println(script.translateIndentation(0));
		System.out.println("print the indented code, it's easier to understand");
		System.out.println(script.translateIndentation(0));
		System.out.println("");
		System.out.println("");
		System.out.println("to save a program we need to transform it into a string that represents the sequence of derivations \n"
				+ "that generate the tree and for that we use the Control class");
		String trace= Control.salve(script);
		System.out.println(trace);
		System.out.println("");
		System.out.println("");
		System.out.println("we will also use the control class to rebuild");
		FactoryBase f=new FactoryBase();//this is a design pattern that helps us build the DSL so that other people can modify it
		//in a simpler and more robust way,
		//you don't need to worry about that now
		Node script2 = Control.load(trace, f);
		System.out.println(script2.translate());//print the program in one line
		System.out.println("");
		System.out.println("");
		System.out.println("Now let's transform the AST into an \"AI\" to play the game, for that we will need an automaton \n"
				+ "that is implemented in the Interpreter class");
		UnitTypeTable utt = new UnitTypeTable(2);//unit settings such as hp, range, speed and attack
		AI j= new Interpreter(utt,script2);
		AI adv= getAI("0", utt);
		String path_map = getMap("1");//
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		System.out.println("uncomment the last lines to see the match");
		double r = match(gs2,utt,0,max,j,adv,true);
		//System.out.println("AI0 = "+r);
		//System.out.println("AI0 = "+(1-r));

	}

}

