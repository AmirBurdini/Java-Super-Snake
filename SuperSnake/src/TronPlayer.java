import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;

public class TronPlayer extends Thread 
{	
	Point head;
	Point next,curr=new Point();
	ArrayList<Point> body;
	int direction,x,y,velocity;
	boolean isAlive,bluewins;
	Tron panel;
	Random r=new Random() ; //random number for cherry location
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3,SIZE=10;
	
	public TronPlayer(Tron b,int velocity,Point head,int dir){
		this.velocity=velocity;
		body =new ArrayList<Point>();
		this.head = head;
		body.add(this.head);
		direction=dir;
		panel = b;
		isAlive = true;
		bluewins=false;	
	}
	public synchronized void drawPlayer(Graphics g,Color c)
	{
		for(Point part : body)
		{
			g.setColor(c);
			g.fillOval(part.x,part.y,SIZE,SIZE);
		}
		g.setColor(Color.BLACK);
		g.drawOval(0, 0, SIZE, SIZE);
	}
	public boolean Walls()
	{
		if(head.x>1000||head.x<0){
			return true;
		}
		else if(head.y>600||head.y<0){
			return true;
		}
		return false;
	}
	
	
	public void run()
	{	
		try {
			sleep(1200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(isAlive)
		{
			if(head.x>=0&&head.x<=2&&head.y>=0&&head.y<=2){
			System.exit(0);
		}
				next = new Point(head);
				if(direction==DOWN)
				{
					head.y+=SIZE;
				}
				else if(direction==UP)
				{
					head.y-=SIZE;
				}
				else if(direction==RIGHT)
				{
					head.x+=SIZE;
				}
				else if(direction==LEFT)
				{
					head.x-=SIZE;
				}
				for(int i=1;i<body.size();i++){
					curr= new Point(body.get(i));
					body.set(i, next);
					next=new Point(curr);
				}
				panel.Tie();
				if((panel.Collision(head))||(Walls()))
				{
					isAlive = false;
					break;
				}
				panel.repaint();
			try {
				sleep(velocity);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		if(!panel.p1.isAlive&&!panel.p2.isAlive&&panel.TieFlag&&!panel.another)
		{	
			if(this==panel.p1)
			{
			JOptionPane.showMessageDialog(null, "no winner");
			}
			panel.another=true;
		}
		else if(!panel.p1.isAlive||!panel.p2.isAlive)
		{
			if(panel.p1.isAlive&&!panel.p2.isAlive){
				
				panel.p1.isAlive=false;
				panel.another=true;
				if(panel.RedinBlue())
				bluewins=true;
				JOptionPane.showMessageDialog(null, "Blue wins");
			}
			else if (panel.p2.isAlive&&!panel.p1.isAlive)
			{
				panel.p2.isAlive=false;
				panel.another=true;
				JOptionPane.showMessageDialog(null, "Red wins");
			}
		}
	}
}


