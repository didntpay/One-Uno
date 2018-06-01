import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.io.File;
import java.io.FileNotFoundException;
public class Card {
	private Button cardUI;
	private String name;
	private boolean selected;
	public Card(String name,int x, int y) throws SlickException, FileNotFoundException
	{
		this.name = name;
		this.cardUI = new Button(new Image(getPathbyName()),x , y);
	}
	
	public Button getUI()
	{
		return cardUI;
	}
	
	private String getPathbyName() throws FileNotFoundException
	{
		File dir = new File("Images");
		File[] files = dir.listFiles();
		System.out.println(files[0].getName());
		for(File tmp : files)
		{
			if(tmp.getName().equals(this.name + ".PNG"))
			{
				return tmp.getPath();
			}
			
		}
		throw new FileNotFoundException("file not found");
		
	}
	
	public void showImage(int x, int y)
	{
		this.cardUI.getImage().draw(x,y);
	}
	
	public void select()
	{
		this.selected = !this.selected;
	}	
	
	public void Card(Card other)
	{
		this.name = other.name;
		this.cardUI = other.cardUI;
		this.selected = other.selected;
	}
	
}
