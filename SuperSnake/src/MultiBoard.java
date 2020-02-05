import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class MultiBoard extends JPanel implements KeyListener
	{
		Timer t;
		int sec;
		String time,winner;
		SuperPlayer p1,p2;
		Point Follower;
		JFrame f;
		Point[] Apples;
		static final int speed=50;
		
		public MultiBoard(JFrame f)
		{
			sec=91;
			setBackground(Color.BLACK);
			this.f=f;
			addKeyListener(this);
			Apples=new Point[5];
			p1=new SuperPlayer(this,Color.ORANGE,speed,new Point(900,300));
			p2=new SuperPlayer(this,Color.CYAN,speed,new Point(100,300));
			for(int i=0;i<5;i++)
			{
				Apples[i] = (FillApple());
			}
			t = new Timer(1000,new AL());
			time = "";
			winner="";
			setFocusable(true);
			p1.start();
			p2.start();
			t.start();
		}
		class AL implements ActionListener
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(sec>0){sec--; winner();}
				
				else 
				{
					p1.isAlive=false;
					p2.isAlive=false;
					
				}
				time=""+sec;
				repaint();
			}
			
		}
		public void drawTime(Graphics g)
		{
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Times New Roman",Font.BOLD,15));
			if(sec>0&&p1.isAlive&&p2.isAlive)
			{
			g.drawString("Time : "+time,10,20);
			}
			else
			{
				winner();
				g.drawString("Time's up!",10,20);
				g.drawString(winner,10,35);
			}
		}
		@Override
		public void keyPressed(KeyEvent e) {
			int i = e.getKeyCode();
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
			
			if ((i == KeyEvent.VK_A) && p2.direction != p2.RIGHT)
			{
				p2.direction = p2.LEFT;
			}

			if ((i == KeyEvent.VK_D) && p2.direction != p2.LEFT)
			{
				p2.direction = p2.RIGHT;
			}

			if ((i == KeyEvent.VK_W) && p2.direction != p2.DOWN)
			{
				p2.direction = p2.UP;
			}

			if ((i == KeyEvent.VK_S) && p2.direction != p2.UP)
			{
				p2.direction = p2.DOWN;
			}
			if (i == KeyEvent.VK_P)
			{
				p1.Shoot();
			}
			if (i == KeyEvent.VK_T)
			{
				p2.Shoot();
			}
			if(i == KeyEvent.VK_J)
			{
				sec=91;
				setBackground(Color.BLACK);
				this.f=f;
				addKeyListener(this);
				Apples=new Point[5];
				p1=new SuperPlayer(this,Color.ORANGE,speed,new Point(900,300));
				p2=new SuperPlayer(this,Color.CYAN,speed,new Point(100,300));
				for(int x=0;x<5;x++)
				{
					Apples[x] = (FillApple());
				}
				t = new Timer(1000,new AL());
				time = "";
				winner = "";
				setFocusable(true);
				p1.start();
				p2.start();
				t.start();
			}
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(!p1.shots.isEmpty())
			{
				for(IceShot i : p1.shots)
				{
					i.drawShot(g);
					i.drawCol(g, i.Col);
				}
			} // draw all player's shots
			
			if(!p2.shots.isEmpty())
			{
				for(IceShot i : p2.shots)
				{
					i.drawShot(g);
					i.drawCol(g, i.Col);
				}
			} // draw all player's shots
			
			p1.drawSuperPlayer(g); //draw a player
			p2.drawSuperPlayer(g);	//draw a player
			
			p1.drawString(g,10,50); 
			p2.drawString(g,10,65); //draw scoreboard
			
			p1.drawHealth(g, 75, 40);
			p2.drawHealth(g, 75, 55);
			
			if(!p1.action.isEmpty()&&p1.aux!=null)
			{
			p1.drawAction(g);
			}
			if(!p2.action.isEmpty()&&p2.aux!=null)
			{
			p2.drawAction(g);
			}
			drawTime(g);
			drawApples(g);	
		}
		public void winner()
		{
				if(p2.health<1)
				{
					winner = "Orange Player is the Winner";
				}
				else if(p1.health<1)
				{
					winner = "Cyan Player is the Winner";
				}
				else if(sec<=0)
				{
				winner = "it's a Tie";
				}
		}
		public Point FillApple()
		{
			Random r = new Random();
			boolean NotGood;
			Point h;
				do
				{
					h = new Point(r.nextInt(100)*p1.SIZE,r.nextInt(60)*p1.SIZE);
					NotGood=false;
					
					for(Point part : p1.body)
					{
						if(h.equals(part))
						{
							NotGood=true;
						}
					}
					for(Point part : p2.body)
					{
						if(h.equals(part))
						{
							NotGood=true;
						}
					}
				}while(NotGood);
				
			return h;
		}
		
		public void drawApples(Graphics g)
		{
			g.setColor(Color.RED);
			for(int i=0;i<5;i++)
			{
				g.fillOval(Apples[i].x,Apples[i].y,p2.SIZE,p2.SIZE);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
}
