import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question1 {
	private static String word;
	private static int pushNum;
	private static char ch;

	static void pushWord(boolean check) {
		StringBuilder sb = new StringBuilder();
		if (check) {
			sb.append(word.substring(pushNum, word.length()));
			sb.append(word.substring(0, pushNum));
		} else {
			sb.append(word.substring(word.length() - pushNum, word.length()));
			sb.append(word.substring(0, word.length() - pushNum));
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] oneLine = br.readLine().split(" ");
		word = oneLine[0];
		pushNum = Integer.parseInt(oneLine[1]);
		ch = oneLine[2].charAt(0);

		if (pushNum < 0) {
			pushNum *= -1;
			pushNum %= word.length();
			if (ch == 'R' || ch == 'r') {
				pushWord(true);
			} else if (ch == 'L' || ch == 'l') {
				pushWord(false);
			}
		} else {
			pushNum %= word.length();
			if (ch == 'R' || ch == 'r') {
				pushWord(false);
			} else if (ch == 'L' || ch == 'l') {
				pushWord(true);
			}
		}

	}
}
