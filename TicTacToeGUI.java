import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private JButton[][] buttons;
    private char currentPlayer;

    public TicTacToeGUI
() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        initializeBoard();
        initializeButtons();
    }

    private void initializeBoard() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        currentPlayer = PLAYER_X;
    }

    private void initializeButtons() {
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button);
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player ||
                board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player ||
            board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private void announceWinner(char winner) {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        initializeBoard();
        updateButtons();
    }

    private void announceDraw() {
        JOptionPane.showMessageDialog(this, "It's a draw!");
        initializeBoard();
        updateButtons();
    }

    private void updateButtons() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (board[row][col] == EMPTY_CELL) {
                board[row][col] = currentPlayer;
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (checkWin(currentPlayer)) {
                    announceWinner(currentPlayer);
                } else if (isBoardFull()) {
                    announceDraw();
                } else {
                    switchPlayer();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI
         game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
