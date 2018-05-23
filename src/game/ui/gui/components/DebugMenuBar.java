package game.ui.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import game.BattleScreen;
import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;
import game.city.CityController;
import game.item.Item;
import game.minigame.MinigameType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;
import game.ui.gui.panels.MainGamePanel;

public class DebugMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6121526758784565183L;
	
	private MainGamePanel mainGamePanel;
	
	private JMenuItem mntmGoToNext;

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
					team.addHero(new Hero("Bob Loblaw", HeroType.LAW_STUDENT));
				} catch (TeamFullException e) {
					throw new AssertionError();
				}
				
				team.getInventory().add(Item.fromStrings("WinOnDraw", 1f));
				
				GameEnvironment env = new GameEnvironment();
				env.setTeam(team);
				env.setCityController(new CityController(3));
				
				parent.gameEventPerformed(new GameEvent(GameEventType.START_GAME, env));
			}
		});
		mnDebugMenu.add(mntmQuickStart);
		
		JMenuItem mntmClearSaveStates = new JMenuItem("Clear Save States");
		mntmClearSaveStates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (File file : GameEnvironment.getSaveStates()) {
					
					file.delete();
					
				}
				
				JOptionPane.showMessageDialog(getRootPane(), "Cleared all save states.", "Success", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		mnDebugMenu.add(mntmClearSaveStates);
		
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
		
		mntmGoToNext = new JMenuItem("Go to Next City");
		mntmGoToNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainGamePanel.gameEventPerformed(new GameEvent(GameEventType.GO_TO_NEXT_CITY));
			}
		});
		mnTriggerEvent.add(mntmGoToNext);
		
		JMenu mnCheats = new JMenu("Cheats");
		add(mnCheats);
		
		JMenu mnForceMinigame = new JMenu("Force Minigame");
		mnCheats.add(mnForceMinigame);
		
		JMenuItem mntmForceDiceRolls = new JMenuItem("Dice Rolls");
		mnForceMinigame.add(mntmForceDiceRolls);
		
		JMenuItem mntmForceGuessThe = new JMenuItem("Guess The Number");
		mntmForceGuessThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BattleScreen.forceMinigameType(MinigameType.GUESS_THE_NUMBER);
			}
		});
		mnForceMinigame.add(mntmForceGuessThe);
		
		JMenuItem mntmForcePaperScissorsRock = new JMenuItem("Paper, Scissors, Rock");
		mntmForcePaperScissorsRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BattleScreen.forceMinigameType(MinigameType.PAPER_SCISSORS_ROCK);
			}
		});
		mnForceMinigame.add(mntmForcePaperScissorsRock);
		
		JMenuItem mntmForceReset = new JMenuItem("Reset");
		mntmForceReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BattleScreen.forceMinigameType(null);
			}
		});
		mnForceMinigame.add(mntmForceReset);
		mntmForceDiceRolls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BattleScreen.forceMinigameType(MinigameType.DICE_ROLLS);
			}
		});
		
	}
	
	public void setMainGamePanel(MainGamePanel mainGamePanel) {
		
		this.mainGamePanel = mainGamePanel;
		update();
		
	}
	
	public void update() {
		
		if (this.mainGamePanel == null) {
			
			mntmGoToNext.setEnabled(false);
			
		} else {
			
			mntmGoToNext.setEnabled(true);
			
		}
		
	}
}
