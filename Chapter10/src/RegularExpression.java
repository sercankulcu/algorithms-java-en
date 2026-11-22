import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Regular expressions in Java: compile with Pattern, search with Matcher, take the piece with group.
// The equivalent regular expression of a URL grammar is tried as well.
public class RegularExpression {

	public static void main(String[] args) {
		String input = "Name: Ali Veli, Age: 30, Email: ali.veli@example.com";
		Pattern p = Pattern.compile("Age: (\\d+),");  // compile
		Matcher m = p.matcher(input);
		if (m.find()) {                          // find the first match
			int age = Integer.parseInt(m.group(1)); // group 1: "30"
			System.out.println("Age: " + age);     // Age: 30
		}

		Matcher name = Pattern.compile("Name: (.*?),").matcher(input);      // lazy .*?
		if (name.find()) System.out.println(name.group(1));                 // Ali Veli
		Matcher email = Pattern.compile("Email: (.*?)$").matcher(input);    // up to the end of the line
		if (email.find()) System.out.println(email.group(1));               // ali.veli@example.com

		// url ::= 'http://' host ':' port '/' -> http://([a-z]+\.)*[a-z]+:[0-9]+/
		String url = "http://([a-z]+\\.)*[a-z]+:[0-9]+/";
		System.out.println("http://didit.csail.mit.edu:4949/".matches(url));  // true
		System.out.println("http://didit.csail.mit.edu/".matches(url));       // false
	}
}

/* Expected output:
Age: 30
Ali Veli
ali.veli@example.com
true
false
*/
