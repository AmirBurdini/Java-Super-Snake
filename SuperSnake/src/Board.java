import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Board extends JPanel implements KeyListener
{
	Player p;
	JFrame f;
	int x;
	boolean display;
	
	public Board(JFrame f,int i,boolean scoreDisplay){
		setBackground(Color.BLACK);
		
		this.f=f;
		Random r = new Random();
		Point h = new Point(r.nextInt(60)*p.SIZE,r.nextInt(45)*p.SIZE);
		p=new Player(this,Color.GREEN,60,h,i) ; //a new player
		setFocusable(true);
		addKeyListener(this);
		p.start();
		display=scoreDisplay;
		x=290;
	}
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();
		if ((i == KeyEvent.VK_LEFT) && p.direction != p.RIGHT)
		{
			p.direction = p.LEFT;
		}

		if ((i == KeyEvent.VK_RIGHT) && p.direction != p.LEFT)
		{
			p.direction = p.RIGHT;
		}

		if ((i == KeyEvent.VK_UP) && p.direction != p.DOWN)
		{
			p.direction = p.UP;
		}

		if ((i == KeyEvent.VK_DOWN) && p.direction != p.UP)
		{
			p.direction = p.DOWN;
		}
		
		if((i == KeyEvent.VK_I))
		{
			p.velocity-=2;
			if(p.velocity<0)
			{
				p.velocity=100;
			}
		}
		if((i == KeyEvent.VK_U))
		{
			p.velocity+=2;
		}
		if((i == KeyEvent.VK_P))
		{
			p.pause=!p.pause;
			if(p.pause){
				p.details="paused";
				repaint();
			}
			else p.details= "score : "+p.score+"";
			repaint();
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		p.drawPlayer(g);
		p.drawApple(g);
		if(display)
		{
		p.drawString(g,x,10);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
}

