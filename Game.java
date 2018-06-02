import java.io.FileNotFoundException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	public final int gameHeight = 900;
	
	private Player[] players;
	
	private GameControl gc;
	private gameState state;
	
	private Image meaubackground;
	private Image playbackground;
	private Image About_Us;
	
	public Card currentCard;
	
	private Button startbutton = null;
	private Button putdown;
	
	private static AppGameContainer game;
	public Game(String name) {
		super(name);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		state = gameState.Meau;
		meaubackground = new Image("Images/WeChat Image_20180601165904.jpg");
		startbutton = new Button(new Image("Images/startbutton.PNG"), 540, 450);		
	}	
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		if(state == gameState.Meau)
		{
			meaubackground.draw(540 - meaubackground.getWidth()/2,450-meaubackground.getHeight()/2);
			startbutton.show();
			try
			{
				if(startbutton.clicked(arg0.getHeight()))
				{
					meaubackground.destroy();
					startbutton = null;
					playbackground = new Image("Images/WeChat Image_20180601165904.jpg");
					state = gameState.Play;
					putdown = new Button(new Image("Images/startbutton.PNG"),0,0);
					this.currentCard = null;
					gc = new GameControl(new Player(), new Player());
					players = gc.getPlayerList();
					gc.Start();
					gc.CardInit();
					gc.Passing();
				}
				
			}
			catch(Exception e){}
		}		
		else if(state == gameState.Play)
		{			
			playbackground.draw(540 - playbackground.getWidth()/2,450-playbackground.getHeight()/2);			
			gc.listCard(0);
		}
		else if(state == gameState.About_us)
		{
			About_Us.draw(540 - playbackground.getWidth()/2,450-playbackground.getHeight()/2);
		}
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		if(state == gameState.Play)
		{
			Card picked = null;
			
			for(int i = 0; i < players.length; i++)
			{
				if(players[i].contain(currentCard))//go through players and let them pick card
				{
					picked = chooseOne(players[i]);	
					if(picked.compareTo(currentCard) == -1)
						putdown.disable();
					else
						putdown.enable();
				}
				else//if player doesn't have matching card, draw one from deck
					gc.passOne(i);
				try //after they choose(PS: button will be disable if type doesn't match)
				{
					if(putdown.clicked(gameHeight) && !picked.getName().equals(currentCard.getName()))
					{
						currentCard = picked;
						players[i].played();//should put in card as param
						//implement the case of plus four card, after implementing
						//a smarter logic for AI
						won();
					}				
				}				
				catch (InterruptedException e) {e.printStackTrace();}
			}
			
		}
		
	}
	
	public void won()
	{
		int num = gc.win();
		if(num != -1)
		{
			state = gameState.About_us;
			try {
				this.About_Us = new Image("fasdf");
			} catch (SlickException e) {}
		}
	}

	public Card chooseOne(Player picking)
	{	
		for(Card tmp : picking.getList())
		{
			if(picking.AI)
			{
				if(tmp.compareTo(currentCard) != -1)
				{
					return tmp;
				}
			}
			else
			{
				try 
				{
					if(tmp.click(gameHeight))
					{
						tmp.select();							
						return tmp;
					}
				}
				catch (InterruptedException e) {}
			} 
		}
		
		return null;
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
