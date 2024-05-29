// Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class MyFrame extends JFrame implements ActionListener {
    // Adding these outside of MyFrame() so they're global
    JButton resetButton, algoSolveButton, updateButton;
    JTextField discInput, moveInput;
    JTextArea stepsDisplay;
    JScrollPane scrollPane;
    int numDiscs;
    Tower towers;
    TowerPanel towerPanel;

    MyFrame() {
        this.setTitle("Tower of Hanoi");    // Changes the title of the program.
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Instead of still running in background when x is pressed it will now close the program.
        this.setSize(800, 600); // Setting size of the frame.
        this.setVisible(true);

        // Changing the image of the program. Image is shown on top left of the program and on the taskbar.
        ImageIcon image = new ImageIcon("tower_Of_Hanoi_Logo.png"); // Choosing where to get image from via directories.
        this.setIconImage(image.getImage());

        resetButton = new JButton("Reset"); // Creating new button 'resetButton' and making its text 'Reset'.
        resetButton.setBounds(50, 100, 100, 50);    // Placing it on x:50, y:100, and making button 100x50 pixels.
        resetButton.addActionListener(this);    // Adding action listener so it does appropriate actions when clicked.
        resetButton.setFocusable(false);    // Setting focusable to false to get rid of line around button when it is clicked.
        resetButton.setFont(new Font("Comic Sans", Font.BOLD, 15)); // Setting font to comic sans, bold, and size 15.
        this.add(resetButton);  // This finally adds 'resetButton' button.

        algoSolveButton = new JButton("Algorithm Solver");  // Creating new button 'algoSolveButton' and making its text to 'Algorithm Solver'.
        algoSolveButton.setBounds(200, 100, 175, 50);   // Placing it on x:200, y:100, and making button 175x50 pixels.
        algoSolveButton.addActionListener(this);    // Adding action listener so it does appropriate actions when clicked.
        algoSolveButton.setFocusable(false);    // Setting focusable to false to get rid of line around button when it is clicked.
        algoSolveButton.setFont(new Font("Comic Sans", Font.BOLD, 15)); // Setting font to comic sans, bold, and size 15.
        this.add(algoSolveButton);  // This finally adds 'algoSolveButton' button.

        updateButton = new JButton("Update");   // Creating new button 'updateButton' and making its text to "Update".
        updateButton.setBounds(620, 100, 100, 50);  // Placing it on x:620, y:100, and making button 100x50 pixels.
        updateButton.addActionListener(this);   //  Adding action listener so it does appropriate actions when clicked.
        updateButton.setFocusable(false);   // Setting focusable to false to get rid of line around button when it is clicked.
        updateButton.setFont(new Font("Comic Sans", Font.BOLD, 15));    // Setting the font to comic sans, bold, and size 15.
        this.add(updateButton); // This finally adds 'updateButton' button.

        JLabel discLabel = new JLabel("Number of Discs:");  // Creating new label 'discLabel' and making its text to 'Number of Discs:'.
        discLabel.setBounds(400, 100, 150, 50); // Placing it on x:400, y:100, and making label 150x50 pixels.
        discLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));   // Setting the font to comic sans, bold, and size 15.
        this.add(discLabel);    // This finally adds 'discLabel' label.

        discInput = new JTextField("3");    // This will be default value in the disInput.
        discInput.setBounds(550, 100, 50, 50); // discInput will be on x:550, y:100, and will be 50x50 pixels.
        this.add(discInput);    // This finally adds 'discInput' text field.

        JLabel moveLabel = new JLabel("Move topmost disc from peg A to peg C (A C):");  // Creating new label 'moveLabel' and setting its text to 'Move topmost disc from peg A to peg c (A C):'
        moveLabel.setBounds(50, 180, 350, 50);  // Placing label at x:50, y:180, and making it 350x50 pixels.
        moveLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));   // Setting the font to comic sans, bold, and size 15.
        this.add(moveLabel);    // This finally adds 'moveLabel' label.

        // This is where user will be able to type what move they want to make
        moveInput = new JTextField();
        moveInput.setBounds(400, 180, 100, 50); // Placing text field at x:400, y:180, and making it 100x50 pixels.
        moveInput.addActionListener(e -> makeMove());   // Adding action listener so when user puts in their action the program will know what to do with said information.
        this.add(moveInput);    // This finally adds 'moveInput' text field.

        // This is where the steps will be displayed
        stepsDisplay = new JTextArea(); // Creating new text area 'stepsDisplay'.
        stepsDisplay.setFont(new Font("Comic Sans", Font.PLAIN, 14));   // Setting the font to comic sans, plain, and size 14 for the text that will reside inside text box.
        stepsDisplay.setEditable(false);    // This is making 'stepsDisplay' un-editable. User will not be able to type anything in this text area.

        // This will make the text area scrollable
        scrollPane = new JScrollPane(stepsDisplay); // Creating new scroll pane 'scrollPane' and "linking" it to 'stepsDisplay'.
        scrollPane.setBounds(50, 250, 700, 100);    // Text area will be at x:50, y:250, and 700x100 pixels.
        this.add(scrollPane);   // This will finally add 'scrollPane'.

        // This is where the peg and discs will be located
        towerPanel = new TowerPanel();
        towerPanel.setBounds(50, 350, 700, 200); // Will be at x:50, y:350, and 700x200 pixels.
        this.add(towerPanel);   // This will finally add 'towerPanel'.

        initializeGame();   // This starts game. We add this at end so we have our needed components beforehand.
    }
    // Initializes the game with the number of discs specified by the user
    // If the number of discs is greater than 7, a warning is displayed and the number is set to 7
    private void initializeGame() {
        try {
            numDiscs = Integer.parseInt(discInput.getText());
            if (numDiscs > 7) {
                JOptionPane.showMessageDialog(this, "Warning: Maximum number of discs is 7.", "Warning", JOptionPane.WARNING_MESSAGE);
                numDiscs = 7;
                discInput.setText("7");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of discs. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            numDiscs = 3;
            discInput.setText("3");
        }
        stepsDisplay.setText("");
        towers = new Tower(numDiscs);
        towerPanel.setTowers(towers);
        updateDisplay();
    }

    // Executes a move based on the users input
    // Validates the move and updates the display accordingly
    private void makeMove() {
        String move = moveInput.getText().trim();
        if (move.length() == 3 && move.charAt(1) == ' ') {
            char from = move.charAt(0);
            char to = move.charAt(2);
            if (isValidMove(from, to)) {
                towers.moveDisc(from, to);
                stepsDisplay.append("Moved disc from " + from + " to " + to + "\n");
                updateDisplay();

                if (towers.isPuzzleSolved()) {
                    stepsDisplay.append("\n--- Puzzle Solved! ---\n");
                }
            } else {
                stepsDisplay.append("Invalid move!\n");
            }
        } else {
            stepsDisplay.append("Invalid input format! Use 'A C' format.\n");
        }
        moveInput.setText("");
        algoSolveButton.setEnabled(false);
        updateButton.setEnabled(false);
    }

    // This will check if the move from one peg to another is valid
    private boolean isValidMove(char from, char to) {
        return towers.isValidMove(from, to);
    }

    // Solves the Hanoi puzzle by calling solveHanoiRecursion
    // Will clear display before starting the solution
    private void solveHanoi(int n, char from, char to, char aux) {
        stepsDisplay.setText("");
        updateDisplay();
        solveHanoiRecursive(n, from, to, aux);

        if (towers.isPuzzleSolved()) {
            stepsDisplay.append("\n--- Puzzle Solved! ---\n");
        }
    }

    // Recursively solves the puzzle
    private void solveHanoiRecursive(int n, char from, char to, char aux) {
        if (n == 1) {
            stepsDisplay.append("Move disc 1 from " + from + " to " + to + "\n");
            towers.moveDisc(from, to);
            updateDisplay();
            return;
        }
        solveHanoiRecursive(n - 1, from, aux, to);
        stepsDisplay.append("Move disc " + n + " from " + from + " to " + to + "\n");
        towers.moveDisc(from, to);
        updateDisplay();
        solveHanoiRecursive(n - 1, aux, to, from);
    }

    // Updates the display to show the current state of the towers
    private void updateDisplay() {
        stepsDisplay.append("\nCurrent State:\n" + towers.toString() + "\n");
        towerPanel.repaint();
    }

    // These are the actions of the butons when clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) { // If 'Reset' button is clicked then game restarts and 'Algorithm Solver' as well as 'Update' button are enabled.
            initializeGame();
            algoSolveButton.setEnabled(true);
            updateButton.setEnabled(true);
        } else if (e.getSource() == algoSolveButton) {  // If 'Algorith Solver' button is clicked then program solves puzzle for user
            solveHanoi(numDiscs, 'A', 'C', 'B');
        } else if (e.getSource() == updateButton) { // If 'Update' button is clicked then game is "Refreshed" in a way to display the specified number of discs by user
            initializeGame();
        }
    }

    class TowerPanel extends JPanel {
        private Tower towers;
        private final Color[] discColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE}; // The colors we will use for the discs

        // Sets the Tower object to be used for drawing
        public void setTowers(Tower towers) {
            this.towers = towers;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (towers != null) {
                drawTowers(g);
            }
        }

        // Draws the towers and discs on the panel
        private void drawTowers(Graphics g) {
            // Setting the dimensions for the pegs and discs
            int pegWidth = 10;
            int pegHeight = 150;
            int baseWidth = getWidth() / 3;
            int baseHeight = 10;
            int discHeight = 20;

            for (int i = 0; i < 3; i++) {
                int pegX = baseWidth / 2 + i * baseWidth;
                int pegY = getHeight() - baseHeight - pegHeight;

                g.setColor(Color.BLACK);
                g.fillRect(pegX - pegWidth / 2, pegY, pegWidth, pegHeight);
                g.fillRect(pegX - baseWidth / 2, getHeight() - baseHeight, baseWidth, baseHeight);

                Stack<Integer> peg = towers.getPeg(i);
                int discY = getHeight() - baseHeight - discHeight;
                for (int disc : peg) {
                    int discWidth = disc * 30;
                    int discX = pegX - discWidth / 2;

                    g.setColor(discColors[disc - 1]);
                    g.fillRect(discX, discY, discWidth, discHeight);
                    discY -= discHeight;
                }
            }
        }
    }
}

class Tower {
    private Stack<Integer>[] pegs;

    // A constructor that initializes the tower with specific number of discs
    // numDiscs is the number of discs to initialize the Tower with
    Tower(int numDiscs) {
        pegs = new Stack[3];
        for (int i = 0; i < 3; i++) {
            pegs[i] = new Stack<>();
        }
        // Push discs onto the first peg in descending order of size
        for (int i = numDiscs; i > 0; i--) {
            pegs[0].push(i);
        }
    }

    // Checks if a move from one peg to another is valid
    // 'from' parameter is the peg to move a disc from ('A', 'B', or 'C')
    // 'to' parameter is the peg to move a disc to ('A', 'B', or 'C')
    // isValidMove is true if the move is valid, and false otherwise
    public boolean isValidMove(char from, char to) {
        int fromIndex = from - 'A';
        int toIndex = to - 'A';
        // Checks if the 'from' peg is empty
        if (pegs[fromIndex].isEmpty()) return false;
        // Checks if the 'to' peg is empty or if the disc is larger than the disc being moved
        if (pegs[toIndex].isEmpty()) return true;
        return pegs[fromIndex].peek() < pegs[toIndex].peek();
    }

    // Moves the top disc from one peg to another
    // 'from' parameter is the peg to move a disc from ('A', 'B', or 'C')
    // 'to' parameter is the peg to move a disc to ('A', 'B', 'C')
    public void moveDisc(char from, char to) {
        int fromIndex = from - 'A';
        int toIndex = to - 'A';
        // Pops the top disc from 'from' peg and pushes it onto 'to' peg
        pegs[toIndex].push(pegs[fromIndex].pop());
    }

    // Checks if the puzzle is solved
    // isPuzzleSolved with be true if solved, and false otherwise
    public boolean isPuzzleSolved() {
        // Checks if second and third pegs are empty. (A solved Hanoi Tower MUST only have discs on the last peg)
        return pegs[0].isEmpty() && pegs[1].isEmpty();
    }

    //  Returns the stack of discs on a specific peg
    // 'index' parameter is the index of a peg ('A' = 0, 'B' = 1, 'C'=2)
    // Returns the stack of discs of the specified peg
    public Stack<Integer> getPeg(int index) {
        return pegs[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Constructs a string representation of the current state of each peg
        for (int i = 0; i < 3; i++) {
            sb.append((char) ('A' + i)).append(": ").append(pegs[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
