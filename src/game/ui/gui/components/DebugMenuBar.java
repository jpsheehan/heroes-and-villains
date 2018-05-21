package game.ui.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;
import game.city.CityController;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;

public class DebugMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6121526758784565183L;

	public DebugMenuBar(GameEventListener parent) {
		
		JMenu mnDebugMenu = new JMenu("Debug Menu");
		add(mnDebugMenu);
		
		JMenuItem mntmQuickStart = new JMenuItem("Quick Start");
		mntmQuickStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Team team = new Team("SENG201");
				try {
					team.addHero(new Hero("Jesse", HeroType.COMPUTER_SCIENCE_STUDENT));
					team.addHero(new Hero("Manu", HeroType.ENGINEERING_STUDENT));
					team.addHero(new Hero("Bob", HeroType.ARTS_STUDENT));
				} catch (TeamFullException e) {
					throw new AssertionError();
				}
				
				GameEnvironment env = new GameEnvironment();
				env.setTeam(team);
				env.setCityController(new CityController(3));
				
				parent.gameEventPerformed(new GameEvent(GameEventType.START_GAME, env));
			}
		});
		mnDebugMenu.add(mntmQuickStart);
		
		JMenu mnTriggerEvent = new JMenu("Trigger Event");
		add(mnTriggerEvent);
		
		JMenuItem mntmGameLost = new JMenuItem("Game Lost");
		mntmGameLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.GAME_LOST));
				
			}
		});
		
		JMenuItem mntmGameWon = new JMenuItem("Game Won");
		mntmGameWon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.GAME_WON));
				
			}
		});
		mnTriggerEvent.add(mntmGameWon);
		mnTriggerEvent.add(mntmGameLost);
		
	}
}
