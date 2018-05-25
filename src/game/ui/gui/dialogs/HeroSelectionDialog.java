package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import game.ui.gui.components.HeroHealthBar;
import game.ui.gui.windows.GameWindow;

import javax.swing.JTextArea;
import javax.swing.UIManager;

public class HeroSelectionDialog extends JDialog implements Returnable, ActionListener {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 4536836729241929715L;
	private final JPanel contentPanel = new JPanel();
	
	private JComboBox<Hero> comboBox;
	private JLabel lblHeroType, lblHeroAbility, lblHealingItem, lblPowerUpItem;
	private JTextArea lblHeroTypeFlavour, lblHeroAbilityFlavour;
	private HeroHealthBar healthBar;
	private JButton okButton;
	
	private DialogResult dialogResult;
	
	private boolean canSelectDeadHeroes;
	
	private Hero selectedHero;

	/**
	 * Create the dialog.
	 * @param heroes An ArrayList of Hero which will be displayed for the user to select from
	 */
	public HeroSelectionDialog(Hero[] heroes) {
		
		canSelectDeadHeroes = false;
		
		setTitle("Select a Hero");
		setModal(true);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				if (heroes != null && heroes.length > 0) {
					
					Hero firstNonDeadHero = null;
					
					for (Hero hero : heroes) {
						
						comboBox.addItem(hero);
						
						if (hero.isAlive() && firstNonDeadHero == null) {
							
							firstNonDeadHero = hero;
							
						}
						
					}
					
					comboBox.setSelectedItem(firstNonDeadHero);
					
					if (heroes.length == 1) {
						
						// replace the comboBox with a label
						contentPanel.remove(comboBox);
						JLabel lblHeroName = new JLabel(selectedHero.getName());
						contentPanel.add(lblHeroName, "4, 2, fill, default");
						
					}
					
				} else {
					
					comboBox.setEnabled(false);
					okButton.setEnabled(false);
					
					lblHealingItem.setText("None");
					lblHeroAbility.setText("None");
					lblHeroAbilityFlavour.setText("None");
					lblHeroType.setText("None");
					lblHeroTypeFlavour.setText("None");
					lblPowerUpItem.setText("None");
					
				}
				
			}
		});
		setBounds(100, 100, 450, 350);
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblHero = new JLabel("Hero:");
			contentPanel.add(lblHero, "2, 2, right, default");
		}
		{
			comboBox = new JComboBox<Hero>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						
						selectedHero = (Hero)comboBox.getSelectedItem();
						
						lblHeroAbility.setText(selectedHero.getAbility().getName());
						lblHeroAbilityFlavour.setText(selectedHero.getAbility().getFlavourText());
						lblHeroType.setText(selectedHero.getType().getName());
						lblHeroTypeFlavour.setText(selectedHero.getType().getFlavourText());
						
						healthBar.setHero(selectedHero);
						
						if (selectedHero.getPowerUpItem() == null) {
							
							lblPowerUpItem.setText("None");
							
						} else {
							
							lblPowerUpItem.setText(selectedHero.getPowerUpItem().getName());
							
						}
						
						if (selectedHero.getHealingItem() == null) {
							
							lblHealingItem.setText("None");
							
						} else {
							
							lblHealingItem.setText(selectedHero.getHealingItem().getName());
							
						}
						
						if (!getCanSelectDeadHeroes() && !selectedHero.isAlive()) {
							
							okButton.setEnabled(false);
							
						} else {
							
							okButton.setEnabled(true);
							
						}
						
					} catch (NullPointerException e) {
						
						okButton.setEnabled(false);
						
					}
					
				}
			});
			contentPanel.add(comboBox, "4, 2, fill, default");
		}
		{
			JLabel lblType = new JLabel("Type:");
			lblType.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblType, "2, 4");
		}
		{
			lblHeroType = new JLabel("lblHeroType");
			contentPanel.add(lblHeroType, "4, 4");
		}
		{
			lblHeroTypeFlavour = new JTextArea("lblHeroTypeFlavour");
			lblHeroTypeFlavour.setEditable(false);
			lblHeroTypeFlavour.setWrapStyleWord(true);
			lblHeroTypeFlavour.setLineWrap(true);
			lblHeroTypeFlavour.setBackground(UIManager.getColor("Label.background"));
			contentPanel.add(lblHeroTypeFlavour, "4, 6");
		}
		{
			JLabel lblAbility = new JLabel("Ability:");
			lblAbility.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblAbility, "2, 8");
		}
		{
			lblHeroAbility = new JLabel("lblHeroAbility");
			contentPanel.add(lblHeroAbility, "4, 8");
		}
		{
			lblHeroAbilityFlavour = new JTextArea("lblHeroAbilityFlavour");
			lblHeroAbilityFlavour.setEditable(false);
			lblHeroAbilityFlavour.setWrapStyleWord(true);
			lblHeroAbilityFlavour.setLineWrap(true);
			lblHeroAbilityFlavour.setBackground(UIManager.getColor("Label.background"));
			contentPanel.add(lblHeroAbilityFlavour, "4, 10");
		}
		{
			JLabel lblHealth = new JLabel("Health:");
			lblHealth.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblHealth, "2, 12");
		}
		{
			healthBar = new HeroHealthBar((Hero) null);
			contentPanel.add(healthBar, "4, 12");
		}
		{
			JLabel lblHealing = new JLabel("Healing Item:");
			lblHealing.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblHealing, "2, 14");
		}
		{
			lblHealingItem = new JLabel("lblHealingItem");
			contentPanel.add(lblHealingItem, "4, 14");
		}
		{
			JLabel lblPowerUp = new JLabel("Power Up Item:");
			lblPowerUp.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblPowerUp, "2, 16");
		}
		{
			lblPowerUpItem = new JLabel("lblPowerUpItem");
			contentPanel.add(lblPowerUpItem, "4, 16");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
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
		
		Timer timer = new Timer(500, this);
		timer.start();
		GameWindow.addTimer(timer);
		
	}
	
	private void setDialogResult(DialogResult dialogResult) {
		
		this.dialogResult = dialogResult;
		
	}
	
	public DialogResult getDialogResult() {
		
		return this.dialogResult;
		
	}
	
	public Hero getSelectedHero() {
		
		return this.selectedHero;
		
	}
	
	public void setCanSelectDeadHeroes(boolean enabled) {
		
		this.canSelectDeadHeroes = enabled;
		
	}
	
	public boolean getCanSelectDeadHeroes() {
		
		return this.canSelectDeadHeroes;
		
	}
	
	protected void makeReadOnly() {
		
		okButton.setEnabled(false);
		okButton.setVisible(false);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent args) {
		
		if (selectedHero == null || !selectedHero.isHealing()) {
			
			lblHealingItem.setText("None");
			
		} else {
			
			lblHealingItem.setText(String.format("%s (%ds remaining)", selectedHero.getHealingItem().getName(), selectedHero.getRemainingHealingTime()));
			
		}
		
	}

}
