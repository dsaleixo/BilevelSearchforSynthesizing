package IAs2;

import EvaluateGameState.Feature;
import LS_CFG.Node_LS;
import TwoLevelSearch.Level1;
import TwoLevelSearch.Level2;
import rts.GameState;
import util.Pair;

public interface Evaluation {
	Pair<Double,Double> evaluation(GameState gs,int max,Node_LS n,Feature oraculo,Level1 l1) throws Exception;
	double evaluation(GameState gs,int max,Node_LS n) throws Exception;
	void update(GameState gs,int max,Node_LS n) throws Exception;
	Node_LS getIndividuo();
	Node_LS getBest();
	boolean stoppingCriterion(double d);
}
