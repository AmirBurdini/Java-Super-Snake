import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class IceShot extends Thread {

	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3,SIZE=10;
	final int  direction;
	Point pos;
	boolean isAlive;
	MultiBoard panel;
	Point Col;
	
	public IceShot(int dir,Point pos,MultiBoard panel)
	{
		this.pos=new Point(pos.x,pos.y);
		direction=dir;
		isAlive=true;
		this.panel=panel;
		Col=null;
	}
	public void drawCol(Graphics g,Point p)
	{
		if(p!=null)
		{
		g.setColor(Color.BLACK);
		g.fillOval(p.x,p.y,SIZE,SIZE);	
		}
	}
	public void drawShot(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(pos.x,pos.y,SIZE,SIZE);
	}
	public void run(){
		
		while(isAlive)
		{
		if(direction==0){pos.y-=SIZE;}
		if(direction==1){pos.y+=SIZE;}	
		if(direction==2){pos.x-=SIZE;}	
		if(direction==3){pos.x+=SIZE;}
		panel.repaint();
		for(Point part : panel.p1.body)
		{
			if(pos.equals(part))
			{
				panel.p1.health--;
				panel.p1.BoomSound();
				Col=pos;
				panel.repaint();
				isAlive=false;
			}
				
		}
		for(Point part : panel.p2.body)
		{
			if(pos.equals(part))
			{
				panel.p2.health--;
				panel.p2.BoomSound();
				Col=pos;
				panel.repaint();
				isAlive=false;
			}
		}
		if(pos.x<0||pos.y<0||pos.x>panel.getWidth()||pos.y>panel.getHeight())
		{
			isAlive=false;
		}
		try {
			sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
}
