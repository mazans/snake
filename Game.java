import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements FieldParent {
    private JTextField scoreField = new JTextField(4);
    private static final String HELP = "Arrow keys - controlling Snake";

    public Game(Field field)
    {
        //Field field = new Field(20, 20,25, this);
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem exit = new JMenuItem("Exit");

        JLabel scoreLabel = new JLabel("Score:");

        setLayout(new FlowLayout());

        menuBar.add(newGame);
        menuBar.add(help);
        menuBar.add(exit);
        setJMenuBar(menuBar);

        newGame.addActionListener(event ->
        {
            field.reset();
            field.start();
        });
        help.addActionListener(event ->
        {
            JOptionPane.showMessageDialog(this, HELP, "Help", JOptionPane.INFORMATION_MESSAGE);
        });
        exit.addActionListener(event ->
        {
            System.exit(0);
        });

        scoreField.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scoreLabel);
        panel.add(scoreField);

        add(field);
        add(panel);

        pack();
        setResizable(false);
    }

    @Override
    public void updateScore(int sc) {
        scoreField.setText("" + sc);
    }
}
