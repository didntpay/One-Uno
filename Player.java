
import java.util.ArrayList;



public class Player {
	private ArrayList<Card> playerhand;
	public boolean AI;
	private int count;
	
	public Player(){
		playerhand = new ArrayList<Card>();
		count = playerhand.size();
		AI = false;
	}
	
	//Adds card to player's hand
	public void add(Card card)
	{
		try {
			this.playerhand.add(card);
			this.count++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setAI()
	{
		this.AI = true;
	}
	
	public int getCount()
	{
		
		return this.count;
	}
	
	//A compare to methof that checks whether a card in the nad matches in either number or color
	public boolean contain (Card currentCard)
	{    

		for(int  i = 0; i < this.count; i++)
		{
			if(this.playerhand.get(i).compareTo(currentCard) != -1)
			{
				return true;
			}
			else if(this.playerhand.get(i).getName().equals("Plus4"))
				return true;
			else
				return false;
		}
		return false;
	}
	
	
	public ArrayList<Card> getList()
	{
		return this.playerhand;
	}
	
	public void played(Card picked) {
		this.playerhand.remove(picked);
		this.count--;
		
	}
	
}
