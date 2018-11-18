package hackerearth;

import java.util.Scanner;
import java.util.stream.IntStream;

public class GokiAndHisBreakup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int X = in.nextInt();

		IntStream.range(0, N)
		.forEach(i -> in.nextInt());
		
		IntStream.range(0, N).forEach(i -> {
			if (in.nextInt() >= X) 
				System.out.println("YES");
			else
				System.out.println("NO");
		});
	}

}
