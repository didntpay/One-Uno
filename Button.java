/*Button Object that gives the basic methods to be used for various things within
the game such as the cards, the start button, and so on
*/
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Button{
	private Image background;
	
	private boolean disable;
	
	private int x;
	private int y;
	private int length;
	private int width;
	
//Sets the image for button at x, y
	Button(Image image, int x, int y)
	{
		
		this.background = image;		
		this.length = this.background.getWidth();
		this.width = this.background.getHeight();
		this.x = x;
		this.y = y;	
		this.disable = false;
	}

	//Sets y to new y, used with UNO cards, when after selected, cards shift up
	public void setY(int newy)
	{
		this.y = newy;
	}

	public Image getImage()
	{	return background;}
	
	public int getWidth()
	{	return this.length;}
	
	public int getHeight()
	{	return this.width;}
	
	public void show()
	{	this.background.draw(this.x, this.y);}
	
	//Returns a boolean depending on whether the button is clicked or not
	public boolean clicked(int height)throws InterruptedException
	{
		if(disable)
			return false;
		Input in = new Input(height);
		
		if(in.isMouseButtonDown(in.MOUSE_LEFT_BUTTON))//called too many times
		{			
			Thread.sleep(50);
			int mouX = in.getMouseX();
			int mouY = in.getMouseY();
			Rectangle uncovered = new Rectangle(x,y,length,width);
			return uncovered.contains(mouX, mouY);
		}
		return false;
	}
	
	public void disable()
	{
		this.disable = true;
	}
	
	public void enable()
	{
		this.disable = false;
	}
	
	

}
