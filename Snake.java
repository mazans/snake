public class Snake {
	
    private final int fieldHeight;
    private final int fieldWidth; //variables needed for checking Collision

    private int[] xPos; //table which contains x coordinates of Snake elements
    private int[] yPos; //table which contains coordinates of Snake elements
    private Direction dir = Direction.UP;
    private int size = 3; //At the beginning Snake has 3 elements

    public Snake(int fieldWidth, int fieldHeight, int startXPos, int startYPos) {
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;

        int maxSize = fieldHeight * fieldWidth;

        xPos = new int[maxSize];
        yPos = new int[maxSize];

        for (int i = 0; i < maxSize; i++) {
            xPos[i] = startXPos;
            yPos[i] = startYPos - i;
        } //initialazing snake position
    }

    public void growUp() {
        size += 1;
    } //incrementing size

    //methods responsible for changing Snake's direction

    public void turnRight() {
        if (dir != Direction.LEFT)
            dir = Direction.RIGHT;
    }

    public void turnLeft() {
        if (dir != Direction.RIGHT)
            dir = Direction.LEFT;

    }

    public void turnUp() {
        if (dir != Direction.DOWN)
            dir = Direction.UP;

    }

    public void turnDown() {
        if (dir != Direction.UP)
            dir = Direction.DOWN;

    }

    //method responsible for updating snake position on the field

    public void move(){
        if(dir == Direction.RIGHT)
        {
            for(int i=size; i > 0; i--)
            {
                xPos[i] = xPos[i-1];
                yPos[i] = yPos[i-1];
            }
            xPos[0] = xPos[0] + 1;
        }
        else if(dir == Direction.LEFT)
        {
            for(int i=size; i > 0; i--)
            {
                xPos[i] = xPos[i-1];
                yPos[i] = yPos[i-1];
            }
            xPos[0] = xPos[0] - 1;
        }
        else if(dir == Direction.UP)
        {
            for(int i=size; i > 0; i--)
            {
                xPos[i] = xPos[i-1];
                yPos[i] = yPos[i-1];
            }
            yPos[0] = yPos[0] - 1;
        }
        else if(dir == Direction.DOWN)
        {
            for(int i=size; i > 0; i--)
            {
                xPos[i] = xPos[i-1];
                yPos[i] = yPos[i-1];
            }
            yPos[0] = yPos[0] + 1;

        }
    }

    //checking if there is Snake's collision with itself or with walls

    public boolean isCollision()
    {
        if(xPos[0] < 0 || xPos[0] >= fieldWidth || yPos[0] < 0 || yPos[0] >= fieldHeight)
            return true;
        boolean collision = false;
        for(int i = 4; i < size; i++)
        {
            if(xPos[i] == xPos[0] && yPos[i] == yPos[0])
                collision = true;
        }
        return collision;
    }

    public int getXPos(int i)
    {
        return xPos[i];
    }

    public int getYPos(int i)
    {
        return yPos[i];
    }

    public int getSize()
    {
        return size;
    }
}

enum Direction
{
    RIGHT,
    LEFT,
    UP,
    DOWN,
}
