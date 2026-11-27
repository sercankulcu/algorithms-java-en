import java.util.Scanner;

// First algorithm example: the average of three numbers. Independent of the input size, O(1).
// Instead of Scanner(System.in) from the slides, it reads from a fixed string (no keyboard wait).
public class FindAverage {

	public static void main(String[] args) {
		Scanner in = new Scanner("10 20 45");
		System.out.println("Enter the first number: ");
		double x1 = in.nextDouble();
		System.out.println("Enter the second number: ");
		double x2 = in.nextDouble();
		System.out.println("Enter the third number: ");
		double x3 = in.nextDouble();
		double avg = (x1 + x2 + x3) / 3;
		System.out.println("Average of the numbers: "
				+ avg);              // 25.0
		in.close();
	}
}

/* Expected output:
Enter the first number:
Enter the second number:
Enter the third number:
Average of the numbers: 25.0
*/
