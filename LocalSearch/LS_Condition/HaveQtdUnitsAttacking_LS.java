package LS_Condition;

import java.util.List;
import java.util.Random;

import CFG.N;
import CFG_Condition.HaveQtdUnitsAttacking;
import LS_CFG.Node_LS;

public class HaveQtdUnitsAttacking_LS extends HaveQtdUnitsAttacking implements Node_LS {

	public HaveQtdUnitsAttacking_LS() {
		// TODO Auto-generated constructor stub
	}

	public HaveQtdUnitsAttacking_LS(N n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sample(int budget) {
		// TODO Auto-generated method stub
		
		N n = new N();
	
		Random gerador = new Random();
	
		List<String> l2 = n.Rules();
		int g = gerador.nextInt(l2.size());
		n.setN(l2.get(g));
		this.setN(n);
	}

	@Override
	public void countNode(List<Node_LS> list) {
		// TODO Auto-generated method stub
		list.add(this);
	}
	@Override
	public void mutation(int node_atual, int budget,boolean desc) {
		// TODO Auto-generated method stub
		
		if(desc) {
			System.out.println("Mutacao \t "+this.getName());
			System.out.println("Anterior \t"+this.translate());
		}
		this.sample(budget);
		
		if(desc) {
			System.out.println("Atual \t"+this.translate());
		}
	}
}
