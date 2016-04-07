package com.bluewalrus.chart.draw;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		
		Font font = new Font("Arial", Font.PLAIN, 60);
        g.setFont(font);
        
		FontMetrics fm = this.getFontMetrics(font);
        
		String str = "100dhgt";
        Rectangle2D rect = fm.getStringBounds(str, g);

        int x = 5;
        int y = 100; 
        
        g.drawRect(x, y - (int)rect.getHeight(), (int)rect.getWidth(), (int)rect.getHeight());
        g.drawString(str, x, y - fm.getDescent());
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		Test test = new Test();
		f.add(test);
		f.setVisible(true);
		f.setSize(400, 400);
	}

}
