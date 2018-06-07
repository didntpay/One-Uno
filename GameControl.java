import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;

public class GameControl {
	public final int startx = 160;//starting coordinate of the listing
	public final int starty = 682;
	public final int xOffset = 110;
	private int indexof;
	
	public boolean running;
	private boolean init;
	
	private Player[] playerlist;	
	private Card[] cardlist;
	GameControl(Player player1, Player player2) throws SlickException
	{
		this.init = false;
		this.playerlist = new Player[2];
		playerlist[0] = player1;
		playerlist[1] = player2;
		playerlist[1].setAI();
		this.cardlist = new Card[20];
	}
	
	public void Start()
	{
		if(!this.init)
			return;
		this.running = true;
	}
	
	public Player[] getPlayerList(){
		return playerlist;
	}
	
	public void CardInit()
	{
		Random r = new Random();
		String[] names = {"green 0", "green 1", "green 2", "green 3", "red 0", "red 1", 
				"red 2", "red 3","blue 0", "blue 1", "blue 2", "blue 3",
				"yellow 0", "yellow 1", "yellow 2", "yellow 3", 
				"plus 4","plus 4","plus 4","plus 4"};
		
		for(int i = 0; i < names.length; i++)
		{
			int tmp = r.nextInt(20);
			if(tmp == i && tmp != names.length - 1)
				tmp++;
			String storage = names[i];
			names[i] = names[tmp];
			names[tmp] = storage;
		}
		
		for(int i = 0; i < names.length; i++)
		{
			if(this.cardlist[i] == null)
			{
				try
				{
					this.cardlist[i] = new Card(names[i], startx + i * xOffset, starty);					
					//***DO NOT PUT IN A POS WHEN INITALIZED, DO THAT WHEN PLAYER HAS*** 
					//***HIS OWN HAND***
				}
				catch(Exception e)
				{
					//e.printStackTrace();
					i--;
				}
			}
		}
		this.init = true;
	}
	
	public void Passing()
	{
		if(!this.init)
			return;
		for(int i = 0; i < 14; i += 2)
		{
			playerlist[0].add(cardlist[i]);
			cardlist[i] = null;
			playerlist[1].add(cardlist[i + 1]);
			cardlist[i+1] = null;
		}
		this.indexof = 14;		
	}
	
	public void Stop()
	{
		this.running = false;
		this.init = false;
	}
	
	public void passOne(int playerindex)
	{
		if(!this.running)
			return;
		if(this.indexof < this.cardlist.length)
		{
			playerlist[playerindex].add(cardlist[this.indexof]);
			cardlist[this.indexof] = null;
			this.indexof++;
			deckFinished();
		}
	}
	
	public void deckFinished()
	{
		if(cardlist[cardlist.length-1] == null)
		{
			
			this.CardInit();
			
		}
	}
	
	public int win()
	{
		if(!this.running)
			return -1;
		for(int i = 0; i < 2; i++)
		{
			if(playerlist[i].getCount() == 0)
			{
				return i;
			}
		}
		return -1;
	}
	
	public void listCard()
	{
		if(!running)
			return;
		ArrayList<Card> tmp = playerlist[0].getList();
		for(int i = 0; i < tmp.size(); i++)
		{
			if(!tmp.get(i).selected)
			{
				tmp.get(i).showImage(startx + i * xOffset, starty);
				tmp.get(i).getUI().setY(starty);
				
			}
				
			else
			{
				tmp.get(i).showImage(startx + i * xOffset, starty - 30);
				tmp.get(i).getUI().setY(starty - 30);
			}
		}
	}
	

	
	
}
