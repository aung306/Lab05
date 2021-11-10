import java.util.Scanner;
public class ConnectFour<gameContinue> {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Here the program is asking for the dimensions of the board.
        System.out.println("What would you like the height of the board to be? ");
        int height = scanner.nextInt();
        System.out.println("What would you like the length of the board to be? ");
        int length = scanner.nextInt();
        //This creates the board with the given dimensions.
        char[][] blankBoard = new char[height][length];

        initializeBoard(blankBoard);
        printBoard(blankBoard);
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");

        boolean continueGame = true;
        int playerNum = 1;
        char playerChip = 'x';
        int columnInput;
        int rowInput;

        while (continueGame == true) {
            System.out.println("Player " + playerNum + ": Which column would you like to choose? ");
            columnInput = scanner.nextInt();
            rowInput = insertChip(blankBoard, columnInput, playerChip);


            blankBoard[rowInput][columnInput] = playerChip;
            printBoard(blankBoard);

            //this checks if the board is full or not
            if(boardIsFull(blankBoard) == true) {
                System.out.print("Draw. Nobody wins.");
                continueGame = false;
            }
            //this checks if someone wins
            if(checkIfWinner(blankBoard, columnInput, rowInput, playerChip) == true) {
                System.out.println("Player " + playerNum + " won the game!");
                continueGame = false;
            }

            //This alternates the players.
            if (playerNum == 1) {
                playerNum = 2;
                playerChip = 'o';
            } else {
                playerNum = 1;
                playerChip = 'x';
            }
        }
    }


    public static void printBoard(char[][] board) {
        for (int row = board.length-1; row >= 0; row--) {
            for(int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println("");
        }
    }
    public static void initializeBoard(char[][]array) {
        for (int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                array[i][j] = '-';
            }
        }
    }
    public static int insertChip(char[][]array, int col, char chipType) {
        int row = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == '-') {
                row = i;
                break;
            }
        }
        array[row][col] = chipType;
        return row;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int count = 0;
        for (int i = 0; i < array.length-1; i++) {
            if ((array[i][col] == array[i + 1][col]) && (array[i][col] == chipType) && (array[i+1][col] == chipType)) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int j = 0; j < array[0].length-1; j++) {
            if ((array[row][j] == array[row][j + 1]) && (array[row][j] == chipType)&& (array[row][j + 1] == chipType)) {
                count++;
                if (count == 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public static boolean boardIsFull(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}

