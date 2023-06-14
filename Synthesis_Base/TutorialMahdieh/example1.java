package TutorialMahdieh;

import javax.swing.JFrame;

import CFG.Factory;
import CFG.FactoryBase;
import LS_CFG.FactoryLS;
import ai.RandomBiasedAI;
import ai.mayari;
import ai.abstraction.HeavyRush;
import ai.abstraction.RangedRush;
import ai.abstraction.partialobservability.POLightRush;
import ai.abstraction.partialobservability.POWorkerRush;
import ai.core.AI;
import ai.evaluation.SimpleSqrtEvaluationFunction3;
import ai.mcts.naivemcts.NaiveMCTS;
import gui.PhysicalGameStatePanel;
import rts.GameState;
import rts.PhysicalGameState;
import rts.PlayerAction;
import rts.units.UnitTypeTable;

public class example1 {
	static int max=7000; //duration of match
	FactoryBase f=new FactoryBase();//this is a design pattern that helps us build the DSL so that other people can modify it
									//in a simpler and more robust way,
									//you don't need to worry about that now
	
	public static AI getAI(String s,UnitTypeTable utt) throws Exception {
			//function to choose player, these are very simple players, but they will help you to understand  how the game works
			
			if(s.equals("0")) return new RandomBiasedAI();
			if(s.equals("1")) return new NaiveMCTS(100, -1, 100,10,0.3f, 1.0f, 0.0f, 1.0f, 0.4f, 1.0f, new RandomBiasedAI(), new SimpleSqrtEvaluationFunction3(),false);				
			if(s.equals("2")) return new POWorkerRush(utt);
			if(s.equals("3")) return new POLightRush(utt);
			if(s.equals("4")) return new RangedRush(utt);
			if(s.equals("5")) return new HeavyRush(utt);
			
			
			return null;
	}
	
	
	public static double match(GameState gs,UnitTypeTable utt, int player, int max_cycle, AI ai1, AI ai2, boolean show_mach) throws Exception {
			
			ai1.reset(utt);
			ai2.reset(utt);
			GameState gs2 = gs.cloneChangingUTT(utt);
			boolean gameover = false;
			JFrame w=null;
			if(show_mach) w = PhysicalGameStatePanel.newVisualizer(gs2,640,640,false,PhysicalGameStatePanel.COLORSCHEME_BLACK);
			
		    do {
		    	PlayerAction pa1=null;
		    		try {
		    			pa1 = ai1.getAction(player, gs2);
		    		}catch(Exception e) {
		    			pa1 = new PlayerAction();
		    		}
		    	
		    		PlayerAction pa2=null;
		    		try {
		    			pa2 = ai2.getAction(1-player, gs2);
		    		}catch(Exception e) {
		    			pa2 = new PlayerAction();
		    		}
		            
		    	 gs2.issueSafe(pa1);
		    	 gs2.issueSafe(pa2);
		        
		            if(show_mach) {
		            	w.repaint();
		            	Thread.sleep(10);//you can increase it if the game is too fast and you can't see it
		            }
		            
		            gameover = gs2.cycle();
		           
		            
		
		    } while (!gameover && (gs2.getTime() < max_cycle));
			
		    if (gs2.winner()==player)return 1.0;
		    if (gs2.winner()==-1)return 0.5;
		    return 0;
		
	}
	
	
	public static String getMap(String s) {
		//there are several other maps, but I particularly like these, feel free to choose others
		
		if(s.equals("0")) return "maps/8x8/basesWorkers8x8A.xml";
		if(s.equals("1")) return "maps/24x24/basesWorkers24x24A.xml";
		if(s.equals("2")) return "maps/32x32/basesWorkers32x32A.xml";
	    if(s.equals("3")) { max =15000;;return "maps/BroodWar/(4)BloodBath.scmB.xml";}
			return null;
		}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UnitTypeTable utt = new UnitTypeTable(2);//unit settings such as hp, range, speed and attack
		String path_map = getMap("1");//
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
		AI ai0 = getAI("2",utt);
		AI ai1 = getAI("0",utt);
		boolean show_mach=true;//turn on the graphic part
		int player =0;// 0 or 1
		
		double r = match(gs2,utt,player,max,ai0,ai1,show_mach);
		System.out.println("AI0 = "+r);
		System.out.println("AI1 = "+(1-r));

	}

}
