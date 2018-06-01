import java.io.FileNotFoundException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	
	
	private gameState state;
	private Image meaubackground;
	private Image playbackground;
	private Button startbutton;
	Card haha = null;
	private static AppGameContainer game;
	public Game(String name) {
		super(name);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		state = gameState.Meau;
		
		
		
		
		
	}	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
	}
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		
		
	}
	
	public static void main(String[] args) throws SlickException {
		

		try
		{			
			game = new AppGameContainer(new Game("Uno"));
			game.setDisplayMode(1080, 900, false);
			game.start();
			
		}
		catch(Exception e)
		{
			if(game != null)
				game.destroy();
		}

	}




	



	





}
