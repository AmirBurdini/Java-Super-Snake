import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Menu {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		ArrayList<JFrame> ListofFrames = new ArrayList<JFrame>();
		ListofFrames.add(f);
		JButton b1=new JButton("Arcade Mode"),b2=new JButton("Tron"),b3=new JButton("Zen Mode"),b4=new JButton("Pizuzim");
		JButton label = new JButton("Main Menu");

		b1.setBackground(Color.DARK_GRAY); b1.setForeground(Color.red);
		b2.setBackground(Color.GRAY);  b2.setForeground(Color.red);
		b3.setBackground(Color.LIGHT_GRAY);  b3.setForeground(Color.red);
		b4.setBackground(Color.white);  b4.setForeground(Color.red);
		
		label.setEnabled(false);
		label.setBackground(Color.BLACK);
		
		f.setResizable(false);

		b1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JFrame fx1 = new JFrame("Arcade Mode");
				Board b = new Board(fx1,2,true);
				fx1.add(b);
				fx1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fx1.setSize(617,490);
				fx1.setResizable(false);
				fx1.setVisible(true);
				fx1.setLocation(400, 200);
		    }
		});
		b2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JFrame fx2 = new JFrame("Tron");
				Tron t = new Tron();
				fx2.add(t);
				fx2.setSize(1027,650);
				fx2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fx2.setResizable(true);
				fx2.setVisible(true);
				fx2.setLocation(150, 50);
		    }
		});
		b3.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JFrame fx3 = new JFrame("Zen");
				Board b = new Board(fx3,0,false);
				fx3.add(b);
				fx3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fx3.setSize(617,490);
				fx3.setResizable(false);
				fx3.setVisible(true);
				fx3.setLocation(400, 200);
		    }
		});
		b4.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JFrame fx4 = new JFrame("Harbe Pizuzim");
				MultiBoard b = new MultiBoard(fx4);
				fx4.add(b);
				fx4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fx4.setSize(1027,650);
				fx4.setResizable(false);
				fx4.setVisible(true);
				fx4.setLocation(150, 50);
		    }
		});
		
		GridLayout g = new GridLayout(6,6);
		f.setLayout(g);
		f.add(label);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.setSize(627,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(true);
		f.setVisible(true);
		f.setLocation(340,30);
	}
}
