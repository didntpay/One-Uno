import java.io.FileNotFoundException;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameControl {
	public final int x = 0;//starting coordinate of the listing
	public final int y = 0;
	public boolean running;
	private Player[] playerlist;
	private int indexof;
	private boolean init;
	private Card[] cardlist;
	GameControl(Player player1, Player player2) throws SlickException
	{
		this.init = false;
		this.playerlist = new Player[2];
		playerlist[0] = player1;
		playerlist[1] = player2;
		this.cardlist = new Card[28];
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
	
	public void CardInit() throws FileNotFoundException, SlickException
	{
		Random r = new Random();
		String[] names = {"green 0", "green 1", "green 2", "green 3", "green 4",
						  "green 5", "red 0", "red 1", "red 2", "red 3", "red 4",
						  "red 5","blue 0", "blue 1", "blue 2", "blue 3", "blue 4",
						  "blue 5","yellow 0", "yellow 1", "yellow 2", "yellow 3", 
						  "yellow 4","yellow 5", "PlusFour","PlusFour","PlusFour","PlusFour"};
		for(int i = 0; i < names.length; i++)
		{
			int tmp = r.nextInt(28);
			if(tmp == i)
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
					this.cardlist[i] = new Card(names[i],x + i * 50,y);
				}
				catch(Exception e)
				{
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
			playerlist[0].add(cardlist[i].getName());
			playerlist[1].add(cardlist[i+1].getName());
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
		if(this.indexof < this.cardlist.length)
		{
			playerlist[playerindex].add(cardlist[this.indexof].getName());
			cardlist[this.indexof] = null;
			this.indexof++;
			deckFinished();
		}
	}
	
	public void deckFinished()
	{
		if(cardlist[cardlist.length-1] == null)
		{
			try {
				this.CardInit();
			} catch (FileNotFoundException | SlickException e) {
				e.printStackTrace();
			}
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
	
	public void listCard(int startY)
	{
		Card[] tmp =(Card[]) playerlist[0].getList().toArray();
		for(Card c : tmp)
		{
			if(!c.selected)
			{
				c.showImage();
			}
			else
				c.showImage(startY);
		}
	}
	

	
	
}
