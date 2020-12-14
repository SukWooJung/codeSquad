import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Question3 {

	static char[][][] cube = { { { 'B', 'B', 'B' }, { 'B', 'B', 'B' }, { 'B', 'B', 'B' } },
			{ { 'W', 'W', 'W' }, { 'W', 'W', 'W' }, { 'W', 'W', 'W' } },
			{ { 'O', 'O', 'O' }, { 'O', 'O', 'O' }, { 'O', 'O', 'O' } },
			{ { 'G', 'G', 'G' }, { 'G', 'G', 'G' }, { 'G', 'G', 'G' } },
			{ { 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y' } },
			{ { 'R', 'R', 'R' }, { 'R', 'R', 'R' }, { 'R', 'R', 'R' } } };;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// F:1 R:3 U:5 L:7
		map.put('F', 1);
		map.put('R', 3);
		map.put('U', 5);
		map.put('L', 7);

		outer: while (true) {
			System.out.print("CUBE> ");
			String commands = br.readLine();
			int loop = 1;

			System.out.println();
			for (int i = 0; i < commands.length(); i++) {
				char ch = commands.charAt(i);
				if (ch == '\'') {
					continue;
				}
				if (Character.isDigit(ch)) {
					loop = ch - 48;
					continue;
				}
				if (ch == 'Q') {
					System.out.println("Bye~");
					break outer;
				}
				if(loop > 1) {
					System.out.printf("%d", loop);
				}
				int commandIndex = map.get(ch);
				if (i + 1 < commands.length() && commands.charAt(i + 1) == '\'') {
					System.out.println("" + ch + '\'');
					commandIndex++;
				} else {
					System.out.println(ch);
				}
				for (int j = 0; j < loop; j++) {
					runCommand(commandIndex);
				}
				loop = 1;
				printCube();
			}
		}
	}

	private static void runCommand(int commandIndex) {
		switch (commandIndex) {
		case 1: // F
			turnFront();
			break;
		case 2: // F'
			turnReverseFront();
			break;
		case 3: // R
			turnRight();
			break;
		case 4: // R'
			turnReverseRight();
			break;
		case 5: // U
			turnUp();
			break;
		case 6: // U'
			turnReverseUp();
			break;
		case 7: // L
			turnLeft();
			break;
		case 8: // L'
			turnReverseLeft();
			break;
		default:
			break;
		}

	}

	static void printCube() {
		System.out.println();
		for (int row = 0; row < cube[0].length; row++) {
			System.out.printf("%13c", ' ');
			for (int col = 0; col < cube[0][0].length; col++) {
				System.out.printf("%c ", cube[0][row][col]);
			}
			System.out.println();
		}

		System.out.println();

		for (int row = 0; row < cube[0].length; row++) {
			for (int face = 1; face < 5; face++) {
				for (int col = 0; col < cube[0][0].length; col++) {
					System.out.printf("%c ", cube[face][row][col]);
				}
				System.out.print("   ");
			}
			System.out.println();
		}

		System.out.println();

		for (int row = 0; row < cube[0].length; row++) {
			System.out.printf("%13c", ' ');
			for (int col = 0; col < cube[0][0].length; col++) {
				System.out.printf("%c ", cube[5][row][col]);
			}
			System.out.println();
		}

	}

	static char[][] turnOneFaceRight(char[][] oneFace) { // 한면에서 90도 오른쪽으로 돌렸을 때를 갱신
		char[][] temp = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				temp[col][Math.abs(row - 2)] = oneFace[row][col];
			}
		}
		return temp;
	}

	static char[][] turnOneFaceLeft(char[][] oneFace) { // 한면에서 90도 왼쪽으로 돌렸을 때 갱신
		char[][] temp = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				temp[Math.abs(col - 2)][row] = oneFace[row][col];
			}
		}
		return temp;
	}

	static char[] getRowArray(int faceNum, int rowNum) {// 해당 면의 행 한줄을 char[]형태로 가져옴
		char temp[] = cube[faceNum][rowNum].clone();
		return temp;
	}

	static char[] getColArray(int faceNum, int colNum) {// 해당 면의 열 한줄을 char[]형태로 가져옴
		char temp[] = new char[3];
		for (int i = 0; i < 3; i++) {
			temp[i] = cube[faceNum][i][colNum];
		}
		return temp;
	}

	// 해당 면의 행 한줄에 char[]형태로 삽입
	static void insertRowArray(int faceNum, int rowNum, char[] temp) {
		for (int i = 0; i < 3; i++) {
			cube[faceNum][rowNum][i] = temp[i];
		}
	}

	// 해당 면의 열 한줄에 char[]형태로 삽입
	static void insertColArray(int faceNum, int colNum, char[] temp) {
		for (int i = 0; i < 3; i++) {
			cube[faceNum][i][colNum] = temp[i];
		}
	}

	static void turnFront() { // F
		cube[2] = turnOneFaceRight(cube[2]);
		char temp[] = getRowArray(0, 2);
		insertRowArray(0, 2, getColArray(1, 2));
		insertColArray(1, 2, getRowArray(5, 0));
		insertRowArray(5, 0, getColArray(3, 0));
		insertColArray(3, 0, temp);
	}

	static void turnReverseFront() { // F'
		cube[2] = turnOneFaceLeft(cube[2]);
		char temp[] = getRowArray(0, 2);
		insertRowArray(0, 2, getColArray(3, 0));
		insertColArray(3, 0, getRowArray(5, 0)); // 1 2
		insertRowArray(5, 0, getColArray(1, 2));
		insertColArray(1, 2, temp);
	}

	static void turnRight() { // R
		cube[3] = turnOneFaceRight(cube[3]);
		char temp[] = getColArray(0, 2);
		insertColArray(0, 2, getColArray(2, 2));
		insertColArray(2, 2, getColArray(5, 2));
		insertColArray(5, 2, getColArray(4, 0));
		insertColArray(4, 0, temp);
	}

	static void turnReverseRight() { // R'
		cube[3] = turnOneFaceLeft(cube[3]);
		char temp[] = getRowArray(0, 2);
		insertRowArray(0, 2, getColArray(1, 2));
		insertColArray(1, 2, getRowArray(5, 0)); // 1 2
		insertRowArray(5, 0, getColArray(3, 0));
		insertColArray(3, 0, temp);
	}

	static void turnLeft() { // L
		cube[1] = turnOneFaceRight(cube[1]);
		char temp[] = getColArray(0, 0);
		insertColArray(0, 0, getColArray(4, 2));
		insertColArray(4, 2, getColArray(5, 0));
		insertColArray(5, 0, getColArray(2, 0));
		insertColArray(2, 0, temp);
	}

	static void turnReverseLeft() { // L'
		cube[1] = turnOneFaceLeft(cube[1]);
		char temp[] = getColArray(0, 0);
		insertColArray(0, 0, getColArray(2, 0));
		insertColArray(2, 0, getColArray(5, 0));
		insertColArray(5, 0, getColArray(4, 2));
		insertColArray(4, 2, temp);
	}

	static void turnUp() { // U
		cube[0] = turnOneFaceRight(cube[0]);
		char temp[] = getRowArray(2, 0);
		insertRowArray(2, 0, getRowArray(3, 0));
		insertRowArray(3, 0, getRowArray(4, 0));
		insertRowArray(4, 0, getRowArray(1, 0));
		insertRowArray(1, 0, temp);

	}

	static void turnReverseUp() { // U
		cube[0] = turnOneFaceLeft(cube[0]);
		char temp[] = getRowArray(2, 0);
		insertRowArray(2, 0, getRowArray(1, 0));
		insertRowArray(1, 0, getRowArray(4, 0));
		insertRowArray(4, 0, getRowArray(3, 0));
		insertRowArray(3, 0, temp);

	}
}
