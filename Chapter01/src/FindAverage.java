import java.util.Scanner;

// First algorithm example: the average of three numbers. Independent of the input size, O(1).
// Instead of Scanner(System.in) from the slides, it reads from a fixed string (no keyboard wait).
public class FindAverage {

	public static void main(String[] args) {
		Scanner input = new Scanner("10 20 45");
		System.out.println("Enter the first number: ");
		double number1 = input.nextDouble();
		System.out.println("Enter the second number: ");
		double number2 = input.nextDouble();
		System.out.println("Enter the third number: ");
		double number3 = input.nextDouble();
		double average = (number1 + number2 + number3) / 3;
		System.out.println("Average of the numbers: "
				+ average);              // 25.0
		input.close();
	}
}

/* Expected output:
Enter the first number:
Enter the second number:
Enter the third number:
Average of the numbers: 25.0
*/
