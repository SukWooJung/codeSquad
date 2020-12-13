import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Question2 {

	public static void main(String[] args) throws IOException {
		Question2 Q2 = new Question2();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// U:1 R:3 L:5 B:7
		map.put('U', 1);
		map.put('R', 3);
		map.put('L', 5);
		map.put('B', 7);

		char[][] cube = { { 'R', 'R', 'W' }, { 'G', 'C', 'W' }, { 'G', 'B', 'B' } };
		Q2.printCube(cube);

		outer: while (true) {
			System.out.print("CUBE> ");
			String commands = br.readLine();

			System.out.println();
			for (int i = 0; i < commands.length(); i++) {
				char ch = commands.charAt(i);
				if (ch == '\'') {
					continue;
				}
				if (ch == 'Q') {
					System.out.println("Bye~");
					break outer;
				}
				int index = map.get(ch);
				if (i + 1 < commands.length() && commands.charAt(i + 1) == '\'') {
					System.out.println("" + ch + '\'');
					index++;
				} else {
					System.out.println(ch);
				}

				Q2.runCommand(cube, index);
				Q2.printCube(cube);
			}
		}
	}

	private void runCommand(char[][] cube, int index) {
		switch (index) {
		case 1:
			changeCol(cube[0], true);
			break;
		case 2:
			changeCol(cube[0], false);
			break;
		case 3:
			changeRow(cube, 2, true);
			break;
		case 4:
			changeRow(cube, 2, false);
			break;
		case 5:
			changeRow(cube, 0, false);
			break;
		case 6:
			changeRow(cube, 0, true);
			break;
		case 7:
			changeCol(cube[2], false);
			break;
		case 8:
			changeCol(cube[2], true);
			break;
		default:
			break;
		}
	}

	private void changeCol(char[] cubeSeg, boolean check) {
		char temp;
		if (check) {// 왼쪽밀기, 위쪽밀기
			temp = cubeSeg[0];
			cubeSeg[0] = cubeSeg[1];
			cubeSeg[1] = cubeSeg[2];
			cubeSeg[2] = temp;
		} else { // 오른쪽밀기, 아래밀기
			temp = cubeSeg[2];
			cubeSeg[2] = cubeSeg[1];
			cubeSeg[1] = cubeSeg[0];
			cubeSeg[0] = temp;
		}
	}

	private void changeRow(char[][] cube, int colIndex, boolean check) {
		char[] temp = { cube[0][colIndex], cube[1][colIndex], cube[2][colIndex] };
		changeCol(temp, check);
		for (int i = 0; i < temp.length; i++) {
			cube[i][colIndex] = temp[i];
		}
	}

	void printCube(char[][] cube) {
		for (int row = 0; row < cube.length; row++) {
			for (int col = 0; col < cube[0].length; col++) {
				System.out.print(cube[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
