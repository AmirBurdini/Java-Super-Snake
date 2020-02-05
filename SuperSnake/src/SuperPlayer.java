import java.awt.*;
import java.io.File;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SuperPlayer extends Thread 
{
	Point head,next,curr,tail=new Point(),aux;
	ArrayList<Point> body;
	ArrayList<IceShot>shots;
	int direction,x,y,velocity,score,ammo,health,cnt;
	Color c;
	boolean isAlive;
	MultiBoard panel;
	String details,action;
	Random r=new Random() ; //random number for cherry location
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3,SIZE=10;
	
	public SuperPlayer(MultiBoard b,Color g,int v,Point h){
		c=g ;
		velocity=v ;
		body = new ArrayList<Point>() ;
		shots = new ArrayList<IceShot>() ;
		head = h ;
		
		body.add(head) ;
		for(int i=1;i<5;i++){
			Point p = new Point(h.x,h.y-i*SIZE) ;
			body.add(p) ;
		}
		direction=DOWN ;
		panel = b ;
		score = 0 ;
		details = "score : "+score+"" ;
		action = " ";
		isAlive = true ;
		ammo = 0 ;
		health = 6 ;
		cnt=0;
		aux=null;
	}

	public void drawSuperPlayer(Graphics g)
	{
		for(Point part : body){
			g.setColor(c);
			g.fillOval(part.x,part.y,SIZE,SIZE);
		}
		
	}
	public void drawAction(Graphics g)
	{
		g.setColor(c);
		g.setFont(new Font("Times New Roman",Font.BOLD,11));
		g.drawString(action, aux.x, aux.y);
	}
	public void drawString(Graphics g,int x, int y)
	{
		g.setColor(c);
		g.setFont(new Font("Times New Roman",Font.BOLD,13));
		g.drawString(details,x,y);
	}
	public void drawHealth(Graphics g,int x,int y)
	{
		g.setColor(c);
		g.fillRect(x, y, SIZE*health, SIZE);
	}
	public boolean collisionEasy(Point h)
	{
		if(head.x>panel.getWidth())
		{
			head.x=0;
		}
		if(head.x<0)
		{
			head.x=1000;
		}
		if(head.y<0)
		{
			head.y=600;
		}
		if(head.y>panel.getHeight())
		{
			head.y=0;
		}
	return false;
	}
	public void BoomSound() {
		
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/MUSIKA/Boom.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	    
	}
	public boolean Eat()
	{
		for(int i=0;i<panel.Apples.length;i++)
		{
		if((head.x == panel.Apples[i].x)&&(head.y ==panel.Apples[i].y)){
			score+=3;
			if(action.equals(""))
			{
			aux = new Point(head.x,head.y);
			}
			details = "score : "+score+"";
			panel.Apples[i]=(panel.FillApple());
			return true;
		}
		}
		return false;
	}
	public void Shoot()
	{
		if(score>=10)
		{
		shots.add(new IceShot(direction,head,panel));
		ammo++;
		score-=10;
		details = "score : "+score+"";
		shots.get(ammo-1).start();
		}
	}
	
	public void Grow(int dir)
	  {
		tail=body.get(body.size()-1);
		
		if(dir==UP)
		{
			tail=new Point(tail.x,tail.y-SIZE);
			body.add(tail);
		}
		if(dir==DOWN)
		{
			tail=new Point(tail.x,tail.y+SIZE);
			body.add(tail);
		}
		if(dir==RIGHT)
		{
			tail=new Point(tail.x+SIZE,tail.y);
			body.add(tail);
		}
		if(dir==LEFT)
		{
			tail=new Point(tail.x-SIZE,tail.y);
			body.add(tail);
		}

	}
	public void Arcade(int n)
	{
		switch(n)
		{
		case(0): velocity-=5; action="faster!"; break;
		case(1): if(this==panel.p1){panel.p2.velocity+=5;}else panel.p1.velocity+=5; action="enemy slower!"; break;
		case(2): if(this==panel.p1){panel.p2.velocity-=5;}else panel.p1.velocity-=5;  action="enemy faster!"; break;
		case(3): if(this==panel.p1){panel.p2.head.setLocation(new Point(r.nextInt(100)*10,r.nextInt(61)*10));}else panel.p1.head.setLocation(new Point(r.nextInt(100)*10,r.nextInt(61)*10));  action="enemy random location!"; break;
		case(4): velocity+=5;  action="slower!"; break;
		case(5): score+=15;  action="score + 15!"; break;
		case(6): if(this==panel.p1){panel.p1.head.setLocation(new Point(r.nextInt(100)*10,r.nextInt(61)*10));}else panel.p2.head.setLocation(new Point(r.nextInt(100)*10,r.nextInt(61)*10));  action="random location!"; break;
		case(7): score-=15;  action="score - 15!"; break;
		case(8): if(this==panel.p1){panel.p2.score+=9;}else panel.p1.score+=9;  action="enemy score + 9"; break;
		case(9): if(this==panel.p1){panel.p2.score-=9;}else panel.p1.score-=9;  action="enemy score - 9"; break;
		}
		if(n<10){cnt=panel.sec;}
	}
	public void run()
	{	
		try {
			sleep(1200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(isAlive&&panel.sec>0)
		{
			if(cnt!=0)
			{
				if(cnt-panel.sec>1)
				{
					action= "";
				}
			}
			if(health<1)
			{
				isAlive=false;
				
			}
			if(collisionEasy(head)){
				break;
			}
			next=new Point(head);
			
			if(direction==DOWN)
			{
				head.y+=SIZE;
			}
			if(direction==UP)
			{
				head.y-=SIZE;
			}
			if(direction==RIGHT)
			{
				head.x+=SIZE;
			}
			if(direction==LEFT)
			{
				head.x-=SIZE;
			}
			
			for(int i=1;i<body.size();i++){
				curr= new Point(body.get(i));
				body.get(i).setLocation(next);
				next=new Point(curr);
			}
			if(Eat())
			{
				Grow(direction);
				Random r = new Random();
				int n = r.nextInt(100);
				Arcade(n);
			}
			panel.repaint();
			try {
				sleep(velocity);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
												}
		}
		panel.repaint();
		panel.t.stop();
		if(panel.p1==this)
		{
			panel.p2.isAlive=false;
		}
		else panel.p1.isAlive=false;
	}
}


