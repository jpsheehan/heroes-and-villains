package game.ui.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicProgressBarUI;

import game.ui.gui.windows.GameWindow;

public abstract class HealthBar extends JProgressBar implements ActionListener {

	private int max, value;
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -7004751552534381921L;
	
	public HealthBar(int updateDelay) {
		
		super();
		
		this.max = 100;
		this.value = -1;
		
		setStringPainted(true);
		
		setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.white; }
		    protected Color getSelectionForeground() { return Color.black; }
		});

		setMinimum(0);
		
		// Start a timer to update the healthbar automatically
		Timer timer = new Timer(updateDelay, this);
		timer.start();
		GameWindow.addTimer(timer);
		
	}
	
	public void update() {

		setBackground(Color.BLACK);
		
		this.setString(null);
		
		if (value < 0 || value > max) {
			
			setIndeterminate(true);
			
		} else {
			
			setIndeterminate(false);
			
			setMaximum(this.max);
			setValue(this.value);
			
			float percent = (float)this.value / (float)this.max * 100f;
			
			// update the color of the healthBar
			if (percent > 70) {
				
				setForeground(Color.GREEN);
				
			} else if (percent > 40) {
				
				setForeground(Color.YELLOW);
				
			} else if (percent > 10) {
				
				setForeground(Color.ORANGE);
				
			} else if (percent > 0) {
				
				setForeground(Color.RED);
				
			} else {
				
				setBackground(Color.GRAY);
				setString("Dead");
				
			}
			
		}
		
	}
	
	protected void setCurrentValue(int value) {
		
		this.value = value;
		
	}
	
	protected void setMaxValue(int maxValue) {
		
		this.max = maxValue;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		update();
		
	}

}
