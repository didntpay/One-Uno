import java.util.ArrayList;

public class Player {
	private ArrayList<Card> playerhand;
	public boolean AI;
	private int count;
	
	public Player(){
		playerhand;
		count = playerhand.size();
		
	}
	
	public void add(String name)
	{
		playerhand.add(new Card(name));
	}
	public int getCount()
	{
		
		return count;
	}
	public boolean contain(Card currentCard)
	{    
		for(int i = 0; i < playerhand.size(); i++){
			if(playerhand.get(i).compareTo(currentCard) != -1){
				return false;
			}else{
				return true;
			}
		}
	}
	public ArrayList<Card> getList()
	{
		return playerhand;
	}
	public void played(Card picked) {

		playerhand.remove(picked);
		
	}
	
}
