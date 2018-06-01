
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class Button{
	private Image background;
	private int x;
	private int y;
	private int length;
	private int width;
	private boolean selected;
	Button(Image image, int x, int y)
	{
		this.background = image;		
		this.length = this.background.getWidth();
		this.width = this.background.getHeight();
		this.x = x - length/2;
		this.y = y - width/2;	
		
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Image getImage()
	{
		return background;
	}
	
	public boolean clicked(int height)throws InterruptedException
	{
		Input in = new Input(height);
	
		if(in.isMouseButtonDown(in.MOUSE_LEFT_BUTTON))
		{
			Thread.sleep(100);
			int mouX = in.getMouseX();
			int mouY = in.getMouseY();
			
			Rectangle uncovered = new Rectangle(x,y,length,width);
			return uncovered.contains(mouX, mouY);
		}
		return false;
	}

}
