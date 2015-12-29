
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import SCENERE.drawbackground;
import SCENERE.repaint;


public class background extends JFrame implements Runnable{
	public static void main(String[] args){
		Thread t = new Thread(new background());
		SwingUtilities.invokeLater(t);
		//t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//this.setUndecorated(true);
		this.setBounds(100, 100, 500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container c = this.getContentPane();
		drawbackground map = null;
		try {
			map = new drawbackground();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c.add(map, BorderLayout.CENTER);
		map.createBufferStrategy(2);
		repaint repaint = new repaint(map);
		new Timer(50, repaint).start();
	}
}
