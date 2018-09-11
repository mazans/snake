import java.util.Random;

public class Apple
{
    int[] coords = new int[2];
    int maxX;
    int maxY;

    public Apple(int maxX, int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void generateApple(Snake snake) //method which generate new position of apple, position can't overlap snake's position
    {
        int size = snake.getSize();
        Random rand = new Random();
        boolean collision;
        do
        {
            coords[0] = rand.nextInt(maxX);
            coords[1] = rand.nextInt(maxY);
            collision = false;
            for(int i = 0; i < size; i++)
            {
                if(coords[0] == snake.getXPos(i) || coords[1] == snake.getYPos(i))
                    collision = true;
            }
        }while(collision);
    }

    public int getX()
    {
        return coords[0];
    }

    public int getY()
    {
        return coords[1];
    }
}
