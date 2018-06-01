import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameControl {
	public boolean running;
	private Player[] playerlist;
	private int indexof;
	private boolean init;
	private String[] cardlist;
	private Image winningscreen;
	GameControl(Player player1, Player player2) throws SlickException
	{
		this.init = false;
		this.playerlist = new Player[2];
		playerlist[0] = player1;
		playerlist[1] = player2;
		this.cardlist = new String[20];
		winningscreen = new Image("hahaha");
	}
	
	public void Start()
	{
		if(!this.init)
			return;
		this.running = true;
	}
	
	public void CardInit()
	{
		this.init = true;
	}
	
	public void Passing()
	{
		if(!this.init)
			return;
		for(int i = 0; i < 14; i += 2)
		{
			playerlist[0].add(cardlist[i]);
			playerlist[1].add(cardlist[i+1]);
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
	
	public void win(int playerindex, int x, int y)
	{
		if(!this.running)
			return;
		this.winningscreen.draw(x, y);
	}
}
