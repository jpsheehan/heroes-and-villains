package game.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import game.character.Hero;
import game.ui.gui.dialogs.NewHeroDialog;
import game.ui.gui.dialogs.NewTeamDialog;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	private JFrame frame;
	private final JButton btnNewHero = new JButton("New Hero");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewHero_1 = new JButton("New Hero");
		btnNewHero_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NewHeroDialog dlg = new NewHeroDialog();
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					Hero hero = dlg.getHero();
					
					if (hero == null) {
					
						System.out.println("Couldn't create hero.");
						
					} else {
						System.out.println(hero.getName());
						System.out.println(hero.getType().getName());
					}
				}
			}
		});
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(btnNewHero_1);
		
		JButton btnNewTeam = new JButton("New Team");
		btnNewTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new NewTeamDialog()).setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewTeam);
	}

}
