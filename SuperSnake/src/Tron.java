import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Tron extends JPanel implements KeyListener
{
	TronPlayer p1 ;
	TronPlayer p2 ;
	static final int Speed=60;
	boolean TieFlag,another;
	
	public Tron()
	{
		another=TieFlag=false;
		setBackground(Color.BLACK);
		Point h1 = new Point(900,300),h2 = new Point(100,300);
		p2=new TronPlayer(this,Speed,h2,p2.RIGHT) ; //a new player
		p1=new TronPlayer(this,Speed,h1,p1.LEFT) ; //a new player
		setFocusable(true);
		addKeyListener(this);
		p2.start();
		p1.start();
		
		
	}
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();
		int j = e.getKeyCode();
		if ((i == KeyEvent.VK_LEFT) && p1.direction != p1.RIGHT)
		{
			p1.direction = p1.LEFT;
		}

		if ((i == KeyEvent.VK_RIGHT) && p1.direction != p1.LEFT)
		{
			p1.direction = p1.RIGHT;
		}

		if ((i == KeyEvent.VK_UP) && p1.direction != p1.DOWN)
		{
			p1.direction = p1.UP;
		}

		if ((i == KeyEvent.VK_DOWN) && p1.direction != p1.UP)
		{
			p1.direction = p1.DOWN;
		}
		if ((j == KeyEvent.VK_D) && p2.direction != p2.LEFT)
		{
			p2.direction = p2.RIGHT;
		}

		if ((j == KeyEvent.VK_S) && p2.direction != p2.UP)
		{
			p2.direction = p2.DOWN;
		}

		if ((j == KeyEvent.VK_A) && p2.direction != p2.RIGHT)
		{
			p2.direction = p2.LEFT;
		}

		if ((j == KeyEvent.VK_W) && p2.direction != p2.DOWN)
		{
			p2.direction = p2.UP;
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		p1.drawPlayer(g,Color.BLUE);
		p1.body.add(getLocation());
		p2.drawPlayer(g,Color.RED);
		p2.body.add(getLocation());
		setBackground(Color.BLACK);
		g.setColor(Color.BLACK);
		g.fillOval(0, 0, p2.SIZE, p2.SIZE);
		if(TieFlag)
		{
			drawTie(g,Color.YELLOW);
		}
		if(p2.bluewins)
		{
			drawTie(g,Color.BLUE);
		}
	}
	public void drawTie(Graphics g,Color c)
	{
		g.setColor(c);
		g.fillOval(p2.head.x,p2.head.y,p1.SIZE,p1.SIZE);
	}
	public void Tie()
	{
		if(p1.head.equals(p2.head))
		{
			p1.isAlive=false;
			p2.isAlive=false;
			TieFlag=true;
		}
		else if(p1.Walls()&&p2.Walls())
		{
			p1.isAlive=false;
			p2.isAlive=false;
			TieFlag=true;
		}
	}
	public boolean RedinBlue()
	{
		for(Point part : p1.body)
		{
			if(p2.head.equals(part))
			{
				return true;
			}
		}
		return false;
	}
	public boolean Collision(Point h)
	{
		for(int i=1;i<p1.body.size();i++)
		{
			if(p1.body.get(i).equals(h))
			{
				return true;
			}
		}
			for(int i=1;i<p2.body.size();i++)
			{
				if(p2.body.get(i).equals(h))
				{
					return true;
				}
		}
		return false; 
	}
	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
