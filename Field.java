import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Field extends JPanel {
    private final int width;
    private final int height;
    private final int pixelSize;
    private Snake snake;
    private Apple apple;
    private Timer timer;

    private boolean isAlive = true;
    private boolean isStarted = false; //variable changes when game is started first time

    private int score = 0;

    private FieldParent parent;

    public Field(int width, int height, int pixelSize)
    {

        this.width = width;
        this.height = height;
        this.pixelSize = pixelSize;
        apple = new Apple(width, height);

        setPreferredSize(new Dimension(width*pixelSize, height*pixelSize));
        setFocusable(true);
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_DOWN) {
                    snake.turnDown();
                }
                else if(key == KeyEvent.VK_UP){
                    snake.turnUp();
                }
                else if(key == KeyEvent.VK_LEFT) {
                    snake.turnLeft();
                }
                else if(key == KeyEvent.VK_RIGHT)
                {
                    snake.turnRight();
                }
            }
        }); //setting KeyListener

        setBackground(Color.BLACK);

        timer = new Timer(100, event ->
        {
           if(!snake.isCollision())
           {
               if(snake.getXPos(0) == apple.getX() && snake.getYPos(0) == apple.getY())
               {
                   snake.growUp();
                   apple.generateApple(snake);
                   score += 1;
                   parent.updateScore(score*10);
               }
               snake.move();
               repaint();
           }
           else
           {
               isAlive = false;
               repaint();
               timer.stop();
           }
        });

        requestFocus();
    }
    
    public void setFieldParent(FieldParent parent) {
    	this.parent = parent;
    }

    public void reset() //preparing field for new game
    {
        timer.stop();
        isAlive = false;
        snake = new Snake(width, height, width/2,height/2);
        apple.generateApple(snake);
        score = 0;
        parent.updateScore(score);
    }

    public void start() //starting new game
    {
        isStarted = true;
        isAlive = true;
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isStarted) {
            if (isAlive) {
                g.setColor(Color.RED);
                g.fillRect(apple.getX() * pixelSize, apple.getY() * pixelSize, pixelSize, pixelSize);
                g.setColor(Color.GREEN);
                int size = snake.getSize();
                for (int i = 0; i < size; i++) {
                    g.fillRect(snake.getXPos(i) * pixelSize, snake.getYPos(i) * pixelSize, pixelSize, pixelSize);
                }
            } else {
                g.setFont(new Font("Serif", Font.BOLD, pixelSize));
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 6 * pixelSize, 9 * pixelSize);
            }
            Toolkit.getDefaultToolkit().sync();
        }
    }
}