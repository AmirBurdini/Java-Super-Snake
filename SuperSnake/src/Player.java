import java.awt.*;
import java.io.File;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Player extends Thread 
{
	Point head;
	Point next,curr,tail=new Point();
	Point Apple;
	ArrayList<Point> body;
	int direction,x,y,velocity,change,score;
	Color c;
	boolean pause,game=true;
	Board panel;
	String details;
	Clip clip;
	Random r=new Random() ; //random number for cherry location
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3,SIZE=10;
	
	public Player(Board b,Color g,int v,Point h,int dif){
		x=r.nextInt(60)*SIZE;
		y=r.nextInt(45)*SIZE;
		Apple=new Point(x,y);
		c=g;
		change=dif;
		velocity=v;
		body =new ArrayList<Point>();
		head = h;
		body.add(head);
		for(int i=1;i<5;i++){
			Point p = new Point(h.x,h.y-i*SIZE);
			body.add(p);
		}
		direction=DOWN;
		panel = b;
		pause=false;
		score=0;
		details="score : "+score+"";
	}

	public void drawPlayer(Graphics g)
	{
		for(Point part : body){
			g.setColor(c);
			g.fillOval(part.x,part.y,SIZE,SIZE);
		}
		
	}
	
	public void drawApple(Graphics g)
	{
		g.setColor(Color.RED) ;
		g.fillOval(Apple.x,Apple.y,SIZE,SIZE);
	}
	
	public void drawString(Graphics g,int x, int y){
		g.setColor(Color.WHITE);
		g.drawString(details,x,y);
	}
	public boolean CollisionHard(Point h){
		for(int i=1;i<body.size();i++){
			if(body.get(i).equals(h)){
				return true;
			}
		}
		if((h.x>600)||(h.x<0)||(h.y>450)||(h.y<0))
		{
			return true;
		}
		return false;
	}
	
	public boolean collisionEasy(Point h){
		
		for(int i=1;i<body.size();i++){
			if(body.get(i).equals(h)){
				return true;
			}
			if(head.x>600){
				head.x=0;
			}
			if(head.x<0){
				head.x=600;
			}
			if(head.y<0){
				head.y=450;
			}
			if(head.y>450){
				head.y=0;
			}
		}
		return false;
	}
	
	public boolean Eat(){
		if((head.x == Apple.x)&&(head.y == Apple.y)){
			score+=3;
			details = "score : "+score+"";
			panel.x=285;
			if(velocity>7){
				velocity-=change;
				return true;
				}
			else
				JOptionPane.showMessageDialog(null, "You Won!!");
			game=false;
			return true;
		}
		else return false;
	}
	
	public void Grow(int dir)
	  {
		tail=body.get(body.size()-1);
		
		if(dir==UP){
			tail=new Point(tail.x,tail.y-SIZE);
			body.add(tail);
		}
		if(dir==DOWN){
			tail=new Point(tail.x,tail.y+SIZE);
			body.add(tail);
		}
		if(dir==RIGHT){
			tail=new Point(tail.x+SIZE,tail.y);
			body.add(tail);
		}
		if(dir==LEFT){
			tail=new Point(tail.x-SIZE,tail.y);
			body.add(tail);
		}

	}
	public void playSound(boolean pause,boolean game) 
	{
	    try {
	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/MUSIKA/Timmy2.wav").getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        if(!game)
		    {
		    	clip.close();
		    }
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	    
		}
		
	public void run()
	{	
		//playSound(pause,game);
		while(isAlive())
		{
			while(!pause)
			{
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
			if(Eat()){
				Apple.x=r.nextInt(60)*SIZE;
				Apple.y=r.nextInt(45)*SIZE;
				Grow(direction);
			}
			panel.repaint();
			try {
				sleep(velocity);
				} catch (InterruptedException e) {
			
				e.printStackTrace();
												}
				}
		}
		game=false;
		
	}
}

