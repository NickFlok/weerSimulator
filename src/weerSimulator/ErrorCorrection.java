package weerSimulator;


import java.util.LinkedList;
import java.util.Queue;

public class ErrorCorrection {
	
	
	// testing purpose
	public static void main(String argv[]) {
		
		Queue<String> q = new LinkedList<String>();
		
		for (int i=0; i < 30; i++ ) {
			int getal = 10 + i;
			String s = Integer.toString(getal);
			q.add(s);
		}
		
		String check = errorCheck(q, "");
		System.out.print(check);
		
	
	}
	
	
	public static String errorCheck(Queue<String> q, String waarde) {
	
		float[] a = naarArray(q);
		
		float som = calcSom(a);
		float gemiddelde = gemiddelde(som, q);
		String sGemiddelde = Float.toString(gemiddelde);
	
		float fWaarde = Float.parseFloat(waarde);
		boolean fout = checkFout(gemiddelde, fWaarde);
		if (fout == true) {
				return sGemiddelde;
			}				
		
		if (q.size() >= 30) {
			q.remove();
		}
		
		return waarde;		
	}
	
	// parsed het naar een array of floats
	public static float[] naarArray(Queue<String> q) {
		float[] a = new float[30];
		int c = 0;
	
		for (int i=0; i < q.size(); i++) {
			String element = q.remove();
			float fElement = Float.parseFloat(element);
			a[c] = fElement;
			c ++;
			q.add(element);
		}	
		return a;
		
	}
	// checked als er wel een waarde is
	 public static boolean checkMiss(String waarde) {
		 
		if (waarde.isEmpty()) {	
			
			return true;
		}
		return false;
		
	}
	
	
	// checked als de waarde te groot of te klein is dan gemiddeld
	public static boolean checkFout(float gemiddelde, float waarde) {
		 
		 float teGroot = (float) (gemiddelde * 1.20);
		 float teKlein = (float) (gemiddelde * 0.80);
		 
		 if (waarde > teGroot || waarde < teKlein) {
			 return true;
		 }
		return false;
		 
	}
	
	 
	 
	// rekent het totaal van de dertig waardes uit
	public static float calcSom(float dertigW[]) {
		float som = 0;
		
		
		for(float i:dertigW ) {
			som += i;
		}
		
		return som;
		
	}
	
	// rekent het gemiddelde van de dertig waardes uit
	public static float gemiddelde(float som, Queue<String> q) {
		
		float gemiddelde = som / q.size();
		
		return gemiddelde;
	
	}	

}

