package TwoLevelSearch;

import java.util.Random;

import AIs.Interpreter;
import CFG.Factory;
import EvaluateGameState.BehavioralFeature;
import EvaluateGameState.FeatureFactory;
import EvaluateGameState.FeatureFactory1;
import EvaluateGameState.Feature;
import EvaluateGameState.SimplePlayout;
import LS_CFG.FactoryLS;
import LS_CFG.Node_LS;
import ai.mayari;
import ai.coac.CoacAI;
import ai.core.AI;
import rts.GameState;
import rts.units.UnitTypeTable;
import util.Pair;

public class SketchBehavioral implements Level1 {
	
	FeatureFactory fn;
	int op;
	Factory f;
	public SketchBehavioral(int op) {
		// TODO Auto-generated constructor stub
		this.op =op;
		this.fn = new FeatureFactory1();
		this.f = new FactoryLS();
		
	}

	
	
	@Override
	public Pair<Feature, Node_LS> getSeed() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	public Feature getSeed(GameState gs,UnitTypeTable utt,  int max_cycle,Node_LS n) throws Exception {
		// TODO Auto-generated method stub
		SimplePlayout playout =new SimplePlayout();
		Random r=new Random();;
		int lado = r.nextInt(2);
		
		AI ai2 = new  Interpreter(utt,n.Clone(this.f));
		AI ai= getAI(utt,n);
		
		
		Pair<Double,BehavioralFeature> aux0 = playout.run(gs,utt, lado, max_cycle, ai, ai2, false);
		
		return fn.create(aux0.m_b);
	}

	private AI getAI(UnitTypeTable utt,Node_LS n) {
		// TODO Auto-generated method stub
		if(op==0)return new CoacAI(utt);
		if(op==1)return new mayari(utt);
		if(op==2)return new  Interpreter(utt,n.Clone(this.f));
		return null;
	}



	@Override
	public void update(Node_LS j, Feature nov, double reward) {
		// TODO Auto-generated method stub

	}

	@Override
	public FeatureFactory getFN() {
		// TODO Auto-generated method stub
		return this.fn;
	}

	@Override
	public void imprimir() {
		// TODO Auto-generated method stub

	}

}
