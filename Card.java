import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Card implements Comparable{
	private Button cardUI;
	private String name;
	public boolean selected;
	public Card(String name,int x, int y) throws SlickException, FileNotFoundException
	{
		this.name = name;
		this.cardUI = new Button(new Image(getPathbyName()),x , y);
	}
	
	public Card(Card other)
	{
		this.name = other.name;
		this.cardUI = other.cardUI;
		this.selected = other.selected;
	}
	
	public Card(String name) throws FileNotFoundException, SlickException
	{
		this.name = name;
		this.cardUI = new Button(new Image(getPathbyName()),0 , 0);
	}
	
	public Button getUI()
	{
		return cardUI;
	}
	
	public void select()
	{
		this.selected = !this.selected;
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
	
	public void showImage()
	{
		this.cardUI.show();
	}
	
	public void showImage(int y)
	{
		this.cardUI.getImage().draw((this.cardUI.getX()), 
				y - this.cardUI.getHeight()/2);
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
	public int compareTo(Object o) {
		Card temp = (Card)o;
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
