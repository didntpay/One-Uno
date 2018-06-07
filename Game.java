import java.io.FileNotFoundException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {
	public final int GAMEHEIGHT = 900;
	
	private Player[] players;
	
	private GameControl gc;
	private gameState state;
	
	private Image menubackground;
	private Image playbackground;
	private Image About_Us;
	
	public Card currentCard;
	public Card picked;
	
	private Button startbutton = null;
	private Button putdown;
	
	private static AppGameContainer game;
	public Game(String name) {
		super(name);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		state = gameState.Meau;		
		menubackground = new Image("Images/menubackground.jpg");
		startbutton = new Button(new Image("Images/startbutton.png"), 540, 450);
		

		
	}	
	
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		if(state == gameState.Meau)
		{			
			menubackground.draw(540 - menubackground.getWidth()/2,450-menubackground.getHeight()/2);
			startbutton.show();
			try
			{
				if(startbutton.clicked(arg0.getHeight()))
				{
					menubackground.destroy();
					startbutton = null;
					playbackground = new Image("Images/playbackground.png");					
					putdown = new Button(new Image("Images/putdownbutton.png"),670,590);
					currentCard = null;
					picked = null;
					gc = new GameControl(new Player(), new Player());
					players = gc.getPlayerList();
					gc.CardInit();
					gc.Start();						
					gc.Passing();					
					state = gameState.Play;
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
		else if(state == gameState.Play)
		{			
			playbackground.draw(540 - playbackground.getWidth()/2,450-playbackground.getHeight()/2);
			putdown.getImage().draw(670,590);
			if(currentCard != null)
				currentCard.showImage(540, 450);
			
			/*Input in = new Input(arg0.getHeight());
			if(in.isMouseButtonDown(0))
				System.out.println("x " + in.getMouseX() + ", y " + in.getMouseY());*/
			gc.listCard();
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
			
			for(int i = 0; i < players.length; i++)
			{
				if(players[i].contain(currentCard))//go through players and let them pick card
				{
					picked = chooseOne(players[0],picked);	
					if(picked != null && picked.compareTo(currentCard) == -1){//problem might be in here					
						putdown.disable();
					}
					else{						
						putdown.enable();
						try //after they choose(PS: button will be disable if type doesn't match)
						{
							if(putdown.clicked(GAMEHEIGHT) && !picked.getName().equals(currentCard.getName()))
							{
								System.out.println("hah");
								currentCard = picked;
								players[i].played(picked);
								//implement the case of plus four card, after implementing
								//a smarter logic for AI
								won();
								
							}				
						}
						catch (InterruptedException e) {e.printStackTrace();}
					}
				}
				else//if player doesn't have matching card, draw one from deck
					gc.passOne(i);
								
				
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

	public Card chooseOne(Player picking, Card lastcard)//might be too much work for computer.
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
					if(tmp.getUI().clicked(GAMEHEIGHT))//fix click method and this logic will work
					{
						System.out.println(true);
						tmp.select();
						tmp.select();
						if(picking.contain(lastcard) && lastcard != null)
						{		
							if(!lastcard.getName().equals(tmp.getName()))
							{
								lastcard.select();
								System.out.println(lastcard.selected);
							}
						}						
						
						return tmp;
					}
				}
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
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
