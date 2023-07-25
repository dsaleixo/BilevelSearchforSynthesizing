package TutorialMahdieh;

import EvaluateGameState.BehavioralFeature;
import EvaluateGameState.SimplePlayout;
import ai.abstraction.HeavyRush;
import ai.abstraction.RangedRush;
import ai.core.AI;
import rts.GameState;
import rts.PhysicalGameState;
import rts.units.UnitTypeTable;
import util.Pair;

public class exemple4 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//this example shows how the feature vector works
		String path_map = "maps/32x32/basesWorkers32x32A.xml";
		UnitTypeTable utt = new UnitTypeTable(2);//unit settings such as hp, range, speed and attack
	
		PhysicalGameState pgs = PhysicalGameState.load(path_map, utt);
		GameState gs2 = new GameState(pgs, utt);
		
		AI ai0 = new RangedRush(utt);
		AI ai1 = new HeavyRush(utt);

		// simple playout function does all the work
		SimplePlayout playout = new SimplePlayout();
		//it always returns the characteristics of the ai0 player
		Pair<Double, BehavioralFeature> r =  playout.run(gs2, utt, 0, 10000, ai0, ai1, false);
		r.m_b.imprimir();
	}

}
