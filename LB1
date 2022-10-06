import static java.lang.Math.*;
public class LB1 {
	public static void main(String[] args) {
		int c[];
		c = new int[21];
		for(int i = 0; i <= 20; i++) {
			c[i] = i + 4;
		}
		
		
		float x[] = new float[12];
		float min = (float) -2.0;
		float max = (float) 4.0;
		for(int i = 0; i <= 11; i++) {
			x[i] = (float)(random()*((max-min)+1)+min);
		}
		
		
		float k[][] = new float[11][12];
		int tr[] = new int[] {6, 10, 12, 14, 16};
		for(int i = 0; i < 11; i++) {
			boolean bool = false;
			for(int f : tr) {
				if(c[i] == f) {
					bool = true;
				}
			}
			for(int j = 0; j < 12; j++) {
				if(c[i] == 18) {
					k[i][j] = (float) log(pow(abs(x[j]), 0.5));
					k[i][j] = (float) pow(0.75/(3 + k[i][j]), 2);
					}
				else if(bool) {
					k[i][j] = (float) cos(pow(E, atan((x[j]+1)/6)));
				}
				else {
					k[i][j] = (float) pow(0.25*(0.75 - tan(atan(cos(x[j])))), 3);
				}
			}
		}
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 12; j++) {
				System.out.format("%.3f", k[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
