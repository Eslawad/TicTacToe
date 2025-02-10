import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons;  // Array to store the buttons for the grid
    private char currentPlayer; // Keeps track of the current player (X or O)
    private int moveCount;      // Counter to track the number of moves
    
    public TicTacToe() {
        // Set up the JFrame window
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        currentPlayer = 'X'; // X starts the game
        moveCount = 0;

        // Create a panel for the game grid (3x3)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // Initialize the buttons array
        buttons = new JButton[9];
        
        // Create buttons for each cell of the grid
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        
        add(panel); // Add the grid to the JFrame
        
        setVisible(true); // Make the window visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        
        // If the clicked button is already marked, do nothing
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // Mark the button with the current player's symbol
        clickedButton.setText(String.valueOf(currentPlayer));
        moveCount++;

        // Check if there is a winner
        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
            resetGame();
        } else if (moveCount == 9) {
            // Check if the game is a draw (board is full)
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        } else {
            // Switch to the next player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean checkWinner() {
        // Check horizontal and vertical lines
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i * 3 + 1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i * 3 + 2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i + 3].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i + 6].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[4].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[8].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[4].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[6].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        // Reset the game board and start a new game
        for (JButton button : buttons) {
            button.setText("");
        }
        moveCount = 0;
        currentPlayer = 'X'; // X starts the new game
    }

    public static void main(String[] args) {
        new TicTacToe(); // Create and start the game
    }
}
