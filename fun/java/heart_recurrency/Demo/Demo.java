import java.io.*;

public class Demo {
	/* writing tools */
	static int WRITEDELAY;
	static void write(String s, int i, int n) {
		if(i==n) return;
		sleep(WRITEDELAY);
		System.out.print(s);
		write(s,i+1,n);
	}

	static int LINEDELAY;
	static void newline() {
		System.out.println();	
		sleep(LINEDELAY);
	}

	static String FILL="* ";
	static String EMPTY="  ";

	/* shapes */
	static void pyramid(int i, int s, int n, int m) { // s - step, n - lower boundary, m - upper boundary
		if(i>n/2||i<m) return;
		write(EMPTY, 0, i);
		write(FILL,i,n-i);
		write(EMPTY,n-i,n);
		newline();
		pyramid(i+s,s,n,m);
	}
	static void rect(int h, int w) {
		if(h==0) return;
		write(FILL,0,w);
		newline();
		rect(h-1, w);
	}
	static void upperPart(int i, int s, int n, int m) {
		if(i==m) return;	
		write(EMPTY,0,i);
		write(FILL,i,n/2-i+1);
		write(EMPTY,n/2-i+1,n/2+i);
		write(FILL,n/2+i,n-i);
		write(EMPTY,n-i,n);
		newline();
		upperPart(i+s,s,n,m);
	}
	static void heart(int i, int n, boolean upsidedown) {
		newline();
		if(upsidedown) {
			pyramid(n/2,-1,n,1);
			rect(3,n);
			upperPart(1,+1,n, i+1);
		} else {
			upperPart(i,-1,n, 0);
			rect(3,n);
			pyramid(1,1,n,0);
		
		}
	}

	/* animations */
	static void flyingHearts(int i, int d, boolean upsidedown) {
		if(LINEDELAY<=10) {
			i++; 
			if(i==10) return;
		} 
		LINEDELAY=d;
	
		if(upsidedown)
			heart(3,25, true);
		else
			heart(3,25, false);
		flyingHearts(i, d-(int)(d*0.1), upsidedown);
		LINEDELAY=d;
	}
	static void flipflopFlyingHearts(int i, int d, boolean firstUpsidedown, int j, int n) {
		if(j>n) return;
		flyingHearts(i,d,firstUpsidedown);
		flipflopFlyingHearts(i,d,!firstUpsidedown,j+1,n);
	}
	static void flipflopHeart(int i, int n, boolean upsidedown, int t, boolean randomize) {
		if(i==n) return;
		if(randomize) { 
			randomize();
			t-=t*0.1;
		}
		heart(3,25, upsidedown);
		sleep(t);
		//cls();

		flipflopHeart(i+1,n,!upsidedown,t, randomize);
	}

	public static void main(String[] args) {
		LINEDELAY=0;
		WRITEDELAY=0;

		//LINEDELAY=80;
		//WRITEDELAY=40;
		if( (int)(Math.random()*20) == 0)
			heart(3,25, true);
		else
			heart(3,25, false);
	

		//WRITEDELAY=0;
		switch( (int)(Math.random()*10) ) {
			case 0:	flipflopFlyingHearts(0, LINEDELAY, false, 0, 1); break; // 1. normal 2. upsidedown
			case 1: flipflopFlyingHearts(0, LINEDELAY, true, 0, 1); break; // 1. upsidedown 2. normal
			default: flipflopFlyingHearts(0, LINEDELAY, false, 0, 1); break; // normal
		}
	
		//LINEDELAY=10; 
		//cls();
		//int flipflopdelay=500;
		int flipflopdelay=0;
		flipflopHeart(0,10,false,flipflopdelay,false);
		flipflopHeart(0,50,false,flipflopdelay,true);
		
		
		main(new String[0]);
	}

	/* randomization */
	enum Special { 
		RANDOMFILL 
	}
	static void randomize() {
		int r = (int)(Math.random()*FILLS.length);
		if(r==FILLS.length) {
			Special special = Special.values()[((int)(Math.random()*Special.values().length))];	
			switch(special) {
				case RANDOMFILL: FILL=randomAlphabeticFill();	
			}
		} else {
			FILL=FILLS[r];
		}
	}
	static String randomAlphabeticFill() {
		char[] out = new char[2];
		for(int i=0; i<out.length; i++) {
			int r = (int)(Math.random()*100);
			if(r < 26) 
				out[i] = (char)(r+'a');
			else if(r >= 26*2 && out[0] != ' ') 
				out[i] = ' ';
			else
				out[i] = (char)(r-26+'A');
		}
		return new String(out);
	}

	static String[] FILLS;
	static {
		String[] s = { // single character + whitespace
			"- ", "+ ", "| ", "\\ ", "/ ", "= ", "$ ", "! ", "# ", "^ ", 
			"o ", "y ", "v ", "z ",
				"Y ", "T ", "V ", "Z ", "I "
		};
		String[] d = { // double character
			"fF", "wW", "II",
			"/\\", "__", "||", "!!", "##", "++"  
		};
		FILLS = new String[s.length*2+d.length];
		for(int i=0; i<FILLS.length; i++) {
			if(i<s.length) {
				FILLS[i]=s[i];	
			} else if(i<s.length+d.length) {
				FILLS[i]=d[i-s.length];
			} else {
				int off = s.length+d.length;
				FILLS[i]=new String(new char[] {s[i-off].charAt(1), s[i-off].charAt(0)}); // inverted
			}
		}
	}
	// to do
	// specials:
		// each line random fill
		// each write random fill
		// each another line inverted
		// random roman numerals
	// zastapic switcha, recursive function?
	// odhardcodowac SIZE czyli wielkosc serca
	
	/* utils */
	static void cls() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	static void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

