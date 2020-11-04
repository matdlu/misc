public class Main {
	static void write(String s, int i, int n) {
		if(i==n) return;
		System.out.print(s);
		write(s,i+1,n);
	}
	public static final String FILL="* ";
	public static final String EMPTY="  ";
	static void triangle(int i, int n)  {
		if(i>n/2) return;
		write(EMPTY, 0, i);
		write(FILL,i,n-i);
		write(EMPTY,n-i,n);
		System.out.println();
		triangle(i+1,n);
	}
	static void rect(int h, int w) {
		if(h==0) return;
		write(FILL,0,w);
		System.out.println();
		rect(h-1, w);
	}
	static void magic(int i, int n) {
		System.out.println();
		if(i==0) {
			rect(3,n);
			triangle(1,n);
			return;
		} else {
			write(EMPTY,0,i);
			write(FILL,i,n/2-i+1);
			write(EMPTY,n/2-i+1,n/2+i);
			write(FILL,n/2+i,n-i);
			write(EMPTY,n-i,n);
			i--;
		}
		magic(i,n);
	}

	public static void main(String[] args) throws InterruptedException {
		magic(3,25);
	}
}
