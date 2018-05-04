package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.Settings;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class NewTeamDialog extends JDialog implements Returnable {

	private DialogResult result;
	private String teamName;
	private ArrayList<Hero> heroes;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTeamName;
	private JList listHeroes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewTeamDialog dialog = new NewTeamDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewTeamDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblTeamName = new JLabel("Team Name:");
			contentPanel.add(lblTeamName, "2, 2, right, default");
		}
		{
			textFieldTeamName = new JTextField();
			contentPanel.add(textFieldTeamName, "4, 2, fill, default");
			textFieldTeamName.setColumns(10);
		}
		{
			JLabel lblHeroes = new JLabel("Heroes:");
			contentPanel.add(lblHeroes, "2, 4, default, top");
		}
		{
			listHeroes = new JList();
			contentPanel.add(listHeroes, "4, 4, fill, fill");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "4, 5, center, center");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton btnAddHero = new JButton("Add...");
				btnAddHero.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if (heroes.size() + 1 < Settings.getHeroesMax()) {
							
							NewHeroDialog dlg = new NewHeroDialog();
							dlg.setVisible(true);
							
							if (dlg.getDialogResult() == DialogResult.OK) {
								
								Hero hero = dlg.getHero();
								
								if (hero != null) {
								
									// check that the name doesn't exist
									if (isHeroNameUnique(hero.getName())) {
										
										heroes.add(hero);
										updateListAndButtons();
										
									} else {
										
										// a hero with that name exists
										JOptionPane.showMessageDialog(null, "A hero already exists with that name.");
										
									}
								
								} else {
									
									// the hero name is not valid
									JOptionPane.showMessageDialog(null, String.format(
											"The hero's name is not valid. It must contain between %d and %d characters.",
											Settings.getHeroNameMin(), Settings.getHeroNameMax()));
									
								}
								
							}
							
						} else {
							
							// you have too many heroes in your team
							JOptionPane.showMessageDialog(null,
									String.format("You have too many heroes in your team. You can have, at most, %d.",
											Settings.getHeroesMax()));
							
						}
						
					}
				});
				panel.add(btnAddHero);
			}
			{
				JButton btnEditHero = new JButton("Edit...");
				panel.add(btnEditHero);
			}
			{
				JButton btnRemoveHero = new JButton("Remove");
				panel.add(btnRemoveHero);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setDialogResult(DialogResult.OK);
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setDialogResult(DialogResult.CANCEL);
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		this.heroes = new ArrayList<Hero>();
		this.teamName = "";
	}

	@Override
	public DialogResult getDialogResult() {
		
		return this.result;
		
	}
	
	private void setDialogResult(DialogResult result) {
		
		this.result = result;
		
	}
	
	private boolean isTeamNameValid() {
		
		if (this.teamName == null) {
			
			return false;
			
		}
		
		return (
			this.teamName.length() >= Settings.getTeamNameMin() &&
			this.teamName.length() <= Settings.getTeamNameMax()
		);
		
	}
	
	private boolean areHeroesValid() {
		
		return (
			this.heroes.size() >= Settings.getHeroesMin() &&
			this.heroes.size() <= Settings.getHeroesMax()
		);
		
	}
	
	private boolean isHeroNameUnique(String name) {
		
		for (Hero hero: this.heroes) {
			
			if (hero.getName().equals(name)) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	private void updateListAndButtons() {
		
		
		
	}
	
	/**
	 * Returns the team.
	 * @return
	 */
	public Team getTeam() {
		
		if (isTeamNameValid() && areHeroesValid()) {
			
			Team team = new Team(this.teamName);
			
			try {
				
				for (Hero hero : this.heroes) {
					
					team.addHero(hero);
					
				}
				
			} catch (TeamFullException e) {
				
				throw new AssertionError(e);
				
			}
			
			return team;
			
		} else {
			
			return null;
			
		}
		
	}

}
