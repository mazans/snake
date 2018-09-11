public class App {
	
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;
	private static final int PIXEL_SIZE = 20;
	
	public static void main(String[] args) {
		Field field = new Field(WIDTH, HEIGHT, PIXEL_SIZE);
		Game game = new Game(field);
		field.setFieldParent(game);
		game.setVisible(true);
	}
}
