package utils;

import java.util.Comparator;

import org.apache.lucene.search.ScoreDoc;

public class Recommendation {

	private String label;
	private int counter;
	
	public Recommendation(String label, int counter){
		this.label = label;
		this.counter = counter;
	}
	
	
	public String getLabel(){
		return label;
	}
	
	public int getCounter(){
		return counter;
	}
	

	public static Comparator<Recommendation> CounterComparator = new Comparator<Recommendation>() {

		@Override
		public int compare(Recommendation r1, Recommendation r2) {
			return r2.getCounter() - r1.getCounter();
		}
	};
	
	public static int scoreCompare(ScoreDoc hit1, ScoreDoc hit2) {
		return (int)Math.signum((hit2.score - hit1.score));
	}
	
}