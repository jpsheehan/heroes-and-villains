package game.ui.gui.dialogs;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
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
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class NewTeamDialog extends JDialog implements Returnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6907828618097444177L;
	private DialogResult result;
	private String teamName;
	private DefaultListModel<Hero> heroes;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTeamName;
	private JList<Hero> listHeroes;
	private JButton btnAddHero, btnEditHero, btnRemoveHero;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				updateButtons();
				
			}
		});
		heroes = new DefaultListModel<Hero>();
		
		setResizable(false);
		setModal(true);
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
			listHeroes = new JList<Hero>(heroes);
			listHeroes.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					
					updateButtons();
					
				}
			});
			contentPanel.add(listHeroes, "4, 4, fill, fill");
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "4, 5, center, center");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				btnAddHero = new JButton("Add...");
				btnAddHero.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if (heroes.size() < Settings.getHeroesMax()) {
							
							NewHeroDialog dlg = new NewHeroDialog();
							dlg.setVisible(true);
							
							if (dlg.getDialogResult() == DialogResult.OK) {
								
								Hero hero = dlg.getHero();
								
								if (hero != null) {
								
									// check that the name doesn't exist
									if (isHeroNameUnique(hero.getName())) {
										
										heroes.addElement(hero);
										
										updateButtons();
										
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
				btnEditHero = new JButton("Edit...");
				btnEditHero.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Hero originalHero = listHeroes.getSelectedValue();
						
						EditHeroDialog dlg = new EditHeroDialog(originalHero);
						dlg.setVisible(true);
						
						if (dlg.getDialogResult() == DialogResult.OK) {
							
							Hero newHero = dlg.getHero();
							
							if (!originalHero.getName().equals(newHero.getName()) || originalHero.getType() != newHero.getType()) {
								
								// update the hero
								heroes.insertElementAt(newHero, listHeroes.getSelectedIndex());
								heroes.removeElement(originalHero);
								updateButtons();
								
							} else {
								
								JOptionPane.showMessageDialog(null, "No changes were made to the hero.");
								
							}
							
						}
						
					}
				});
				panel.add(btnEditHero);
			}
			{
				btnRemoveHero = new JButton("Remove");
				btnRemoveHero.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Hero removedHero = listHeroes.getSelectedValue();
						
						int result = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to remove %s from the team?", removedHero));
						
						if (result == JOptionPane.YES_OPTION) {
							
							heroes.removeElement(removedHero);
							
						}
						
					}
				});
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
		
		for (Object obj: this.heroes.toArray()) {
			
			Hero hero = (Hero)obj;
			
			if (hero.getName().toLowerCase().equals(name.toLowerCase())) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	private void updateButtons() {
		
		btnAddHero.setEnabled(true);
		btnEditHero.setEnabled(true);
		btnRemoveHero.setEnabled(true);
		
		if (this.listHeroes.getSelectedValue() == null) {

			btnEditHero.setEnabled(false);
			btnRemoveHero.setEnabled(false);
			
		}
		
		if (this.heroes.size() >= Settings.getHeroesMax()) {
			
			btnAddHero.setEnabled(false);
			
		}
		
	}
	
	/**
	 * Returns the team.
	 * @return
	 */
	public Team getTeam() {
		
		if (isTeamNameValid() && areHeroesValid()) {
			
			Team team = new Team(this.teamName);
			
			try {
				
				for (Object obj : this.heroes.toArray()) {
					
					Hero hero = (Hero)obj;
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
