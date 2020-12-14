
public class Question3 {

	static char[][][] cube = { { { 'B', 'B', 'B' }, { 'B', 'B', 'B' }, { 'B', 'B', 'B' } },
			{ { 'W', 'W', 'W' }, { 'W', 'W', 'W' }, { 'W', 'W', 'W' } },
			{ { 'O', 'O', 'O' }, { 'O', 'O', 'O' }, { 'O', 'O', 'O' } },
			{ { 'G', 'G', 'G' }, { 'G', 'G', 'G' }, { 'G', 'G', 'G' } },
			{ { 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y' }, { 'Y', 'Y', 'Y' } },
			{ { 'R', 'R', 'R' }, { 'R', 'R', 'R' }, { 'R', 'R', 'R' } } };;

	public static void main(String[] args) {
		// WBGYOR
		printCube();
		
	}

	static void printCube() {
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
	
	
	static char[][] turnRight(char[][] oneFace) {
		char[][] temp = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				temp[col][Math.abs(row-2)] = oneFace[row][col];	
			}
		}
		return temp;
	}
	
	static char[][] turnLeft(char[][] oneFace) {
		char[][] temp = new char[3][3];
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				temp[Math.abs(col-2)][row] = oneFace[row][col];	
			}
		}
		return temp;
	}
	
	
	static void turnFront() {
		cube[2] = turnRight(cube[2]);
		
	}
}














