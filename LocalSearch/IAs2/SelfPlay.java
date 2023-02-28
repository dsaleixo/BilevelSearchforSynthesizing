package IAs2;

import java.util.ArrayList;
import java.util.List;

import AIs.Interpreter;
import CFG.Control;
import CFG.Factory;
import CFG.Node;
import EvaluateGameState.Playout;
import EvaluateGameState.SimplePlayout;
import LS_CFG.Empty_LS;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import LS_CFG.S_LS;
import ai.core.AI;
import rts.GameState;
import rts.units.UnitTypeTable;

public class SelfPlay {

	Search2 sc;
	Evaluation ava;
	long tempo_ini;


	
	
	
	public SelfPlay(Search2 sc,Evaluation ava) {
		// TODO Auto-generated constructor stub
		this.ava=ava;
		this.sc = sc;
	
	}
	
	
	
	
	
	public void run(GameState gs,int max) throws Exception {
		
		
		while(true) {
			long paraou = System.currentTimeMillis()-this.tempo_ini;
			
			Node_LS j = ava.getIndividuo();
		
			
			Node_LS  c0 = sc.run(gs, max, j, ava);

			double r0 = ava.evaluation(gs, max, c0);
			double r1 = ava.evaluation(gs, max, j);
			
			if(r0>r1)ava.update(gs, max, c0);
			
		}
		
		
	}


	
		
		
}


	


