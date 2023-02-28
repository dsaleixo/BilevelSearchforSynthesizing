package Tests;

import java.util.Random;

import CFG.Factory;
import EvaluateGameState.FeatureFactory;

import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;

import IAs.Search;

import IAs2.SelfPlay;
import IAs2.Evaluation;
import IAs2.EvaluatorSP;

import IAs2.SA;
import TwoLevelSearch.BehavioralCloning;
import TwoLevelSearch.Level1;
import TwoLevelSearch.Level2;
import TwoLevelSearch.NaiveSampling;
import TwoLevelSearch.SketchBehavioral;
import TwoLevelSearch.SketchSearch;
import TwoLevelSearch.TwoLevelsearch;
import ai.coac.CoacAI;
import ai.core.AI;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;

public class MainTest {
	static int max=6000;
	static  int time=2000;
	static int change_NS =24;
	public MainTest() {
		// TODO Auto-generated constructor stub
	}

	public static String getMap(String s) {
		if(s.equals("0")) return "./maps/16x16/TwoBasesBarracks16x16.xml";
		if(s.equals("1")) return "maps/24x24/basesWorkers24x24A.xml";
		if(s.equals("2")) return "maps/32x32/basesWorkers32x32A.xml";
	    if(s.equals("3")) {change_NS=24;time=5000; max =15000;;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
	   return null;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable(2);
		String path_map = getMap(args[0]);
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
	
		
	
		TwoLevelsearch se =null;
		
		
		if(args[1].equals("0")) {
			SelfPlay se2 = new SelfPlay(new SA(time,2000,0.9,0.5), new EvaluatorSP(1,null));
			se2.run(gs2, max);
		}
				
		if(args[1].equals("1")) {
			SelfPlay se2 = new SelfPlay(new SA(time,2000,0.9,0.5), new EvaluatorSP(1000,null));
			se2.run(gs2, max);
		}
		
		if(args[1].equals("2")) {
			Level1 l1= new NaiveSampling(0.3,0.3,0.6,change_NS,"r1/out_2"+"_"+args[1]+"_"+args[2]+".txt");
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1,l1.getFN());	
			se = new TwoLevelsearch(l1,l2,ava);
			se.run(gs2, max);
		}
				
		if(args[1].equals("3")) {
			Level1 l1= new NaiveSampling(0.3,0.3,0.6,change_NS,"r1/out_2"+"_"+args[1]+"_"+args[2]+".txt");
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1000,l1.getFN());	
			se = new TwoLevelsearch(l1,l2,ava);
			se.run(gs2, max);
		}
		
		if(args[1].equals("4")) {
			SketchBehavioral SB = new SketchBehavioral(0);	//coac
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1,SB.getFN());	//SP
			SketchSearch SS = new SketchSearch(SB,l2,ava);
			SS.run(gs2, max);
			
		}
		if(args[1].equals("5")) {
			SketchBehavioral SB = new SketchBehavioral(0);	//coac
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1000,SB.getFN());	//FP
			SketchSearch SS = new SketchSearch(SB,l2,ava);
			SS.run(gs2, max);
			
		}
		
		if(args[1].equals("6")) {
			SketchBehavioral SB = new SketchBehavioral(1);	//mayari
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1,SB.getFN());	//SP
			SketchSearch SS = new SketchSearch(SB,l2,ava);
			SS.run(gs2, max);
			
		}
		if(args[1].equals("7")) {
			SketchBehavioral SB = new SketchBehavioral(1);	//mayari
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1000,SB.getFN());	//FP
			SketchSearch SS = new SketchSearch(SB,l2,ava);
			SS.run(gs2, max);
			
		}
		if(args[1].equals("8")) {
			SketchBehavioral SB = new SketchBehavioral(2);	//self
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1,SB.getFN());	//SP
			SketchSearch SS = new SketchSearch(SB,l2,ava);
			SS.run(gs2, max);
			
		}
		if(args[1].equals("9")) {
			SketchBehavioral SB = new SketchBehavioral(2);	//self
			Level2 l2 = new BehavioralCloning(time,2000,0.9,0.5,true);
			Evaluation ava = new EvaluatorSP(1000,SB.getFN());	//FP
			SketchSearch SS = new SketchSearch(SB,l2,ava);
			SS.run(gs2, max);
			
		}
		
		
	}

}
