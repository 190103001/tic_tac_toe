import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPosition = new ArrayList<>();
    static ArrayList<Integer> cpuPosition = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        char[][] board = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        intro();
        while (true) {
            printBoard(board);

            System.out.print("Enter your placement (1-9): ");
            int playerPos = in.nextInt();
            if (playerPos > 0 && playerPos <= 9) {
                while (playerPosition.contains(playerPos) || cpuPosition.contains(playerPos)) {
                    System.out.println("Position taken! Enter a correct Possition");
                    playerPos = in.nextInt();
                }
                switchTeam(board, playerPos, "player");
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (cpuPosition.contains(cpuPos) || playerPosition.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            switchTeam(board, cpuPos, "cpu");

            String win = checkWinner();
            if (win.isEmpty() == false) {
                System.out.println(win);
                printBoard(board);
                break;
            }
        }
    }
    public static void intro() {
        System.out.println("==============================");
        System.out.println("||  Welcome to Tic-Tac-Toe  ||");
        System.out.println("==============================");
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void switchTeam(char[][] board, int possition, String user) {
        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPosition.add(possition);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPosition.add(possition);
        }

        switch (possition) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l: winning) {
            if (playerPosition.containsAll(l)) {
                return "|| Congratulations you won! ||";
            } else if (cpuPosition.containsAll(l)) {
                return "|| CPU win! ||";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "|| Draw! ||";
            }
        }

        return "";
    }
}
