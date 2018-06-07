import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Card extends Object implements Comparable<Card>{
	private Button cardUI;
	private String name;
	public boolean selected;
	public Card(String name,int x, int y) throws SlickException, FileNotFoundException
	{
		
		this.name = name;
		this.cardUI = new Button(new Image(getPathbyName(this.name)),x,y);
		this.selected = false;
	}
	
	public Card(Card other)
	{
		this.name = other.name;
		this.cardUI = other.cardUI;
		this.selected = other.selected;
	}
	

	
	public Button getUI()
	{
		return cardUI;
	}
	
	public void select()
	{
		this.selected = !this.selected;
	}
	
	private String getPathbyName(String givenname) throws FileNotFoundException
	{
		File dir = new File("Images");
		File[] files = dir.listFiles();
		for(File tmp : files)
		{
			if(tmp.getName().equals(givenname + ".PNG"))
			{
				return tmp.getPath();
			}
			
		}
		throw new FileNotFoundException("file not found");		
	}
	
	public void showImage(int x, int y)
	{
		this.cardUI.getImage().draw(x, y);
	}

	public String getName()
	{
		return this.name;
	}

	
	
	
	public boolean click(int height) throws InterruptedException
	{
		
		return this.cardUI.clicked(height);
	}

	@Override
	public int compareTo(Card o) {
		Card temp = o;
		if(o == null)
		{
			return 0;
		}
		Scanner other = new Scanner(temp.getName());
		Scanner ori = new Scanner(this.getName());
		if(other.next().equals(ori.next()))
		{
			other.close();
			ori.close();
			return 0;
			
		}
		else if(other.next().equals(ori.next()))
		{
			other.close();
			ori.close();
			return 1;
		}
		other.close();
		ori.close();
		return -1;
	}
	
}
