package com.bluewalrus.chartmanager.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.bluewalrus.chartmanager.ColorChooserDialog;

public class ColorChooserPanel extends JPanel {
	
	
	public Color chosenColor = Color.WHITE;
	
	JButton chooseButton = new JButton("choose");
	
	public ColorChooserPanel(String string) {
		
		this.add(new JLabel(" " + string));
		
		this.add(chooseButton);
	}
	
	public ColorChooserPanel(String name, final ActionListener onColorChange) {
		
		this.add(new JLabel(" " + name));
		
		this.add(chooseButton);
		
		this.setOnColorChangeListener(onColorChange);
	}
	




	public void setOnColorChangeListener(final ActionListener onColorChange) {
		
		final JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		chooseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final ColorChooserDialog d2 = new ColorChooserDialog(topFrame);
				
				d2.getOkButton().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						chosenColor = d2.getChosenColor();
						
						onColorChange.actionPerformed(null);
					}
				});
				
				d2.setSize(300,300);
				d2.setLocationRelativeTo(topFrame);
				d2.setModal(true);
				d2.setVisible(true);
			}
		});
		
	}

	public Color getChosenColor() {
		return chosenColor;
	}


}
