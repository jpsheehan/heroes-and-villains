package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import game.Settings;
import game.character.Hero;
import game.character.HeroType;
import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class NewHeroDialog extends JDialog implements Returnable {

	private HeroType heroType;
	private String heroName;
	private DialogResult dialogResult;
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 79073142309546932L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldHeroName;
	private JLabel lblHeroAbilityName;
	private JComboBox<String> comboBoxHeroType;
	private Color defaultTextFieldBackgroundColor;
	private JTextArea textAreaHeroTypeFlavourText;
	private JTextArea textAreaHeroAbilityFlavourText;
	private JProgressBar progressBarMaxHealth, progressBarRecoveryRate;

	/**
	 * Create the dialog.
	 */
	public NewHeroDialog() {
		setResizable(false);
		setModal(true);
		setTitle("Create New Hero");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				// Populate the combo box with HeroTypes
				for (HeroType type : HeroType.values()) {
					
					comboBoxHeroType.addItem(type.getName());
					
				}
				
				comboBoxHeroType.setSelectedItem(HeroType.values()[0]);
				defaultTextFieldBackgroundColor = textFieldHeroName.getBackground();
				
				heroType = HeroType.values()[0];
				updateLabels();
				
			}
		});
		setBounds(100, 100, 450, 400);
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblHeroName = new JLabel("Hero Name:");
			contentPanel.add(lblHeroName, "2, 2, left, default");
		}
		{
			textFieldHeroName = new JTextField();
			textFieldHeroName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					
					String name = textFieldHeroName.getText();
					if (isHeroNameValid(name)) {
						
						heroName = name.trim();
						textFieldHeroName.setBackground(defaultTextFieldBackgroundColor);
						
					} else {
						
						textFieldHeroName.setBackground(Color.RED);
						
					}
					
				}
			});
			contentPanel.add(textFieldHeroName, "4, 2, fill, default");
			textFieldHeroName.setColumns(10);
		}
		{
			JLabel lblHeroType = new JLabel("Hero Type:");
			contentPanel.add(lblHeroType, "2, 4, left, default");
		}
		{
			comboBoxHeroType = new JComboBox<String>();
			comboBoxHeroType.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					int index = comboBoxHeroType.getSelectedIndex();
					heroType = HeroType.values()[index];
					updateLabels();
					
				}
				
			});
			contentPanel.add(comboBoxHeroType, "4, 4, fill, default");
			
		}
		{
			JLabel lblDescription = new JLabel("Description:");
			contentPanel.add(lblDescription, "2, 6, default, top");
		}
		{
			textAreaHeroTypeFlavourText = new JTextArea();
			textAreaHeroTypeFlavourText.setBackground(UIManager.getColor("Label.background"));
			textAreaHeroTypeFlavourText.setWrapStyleWord(true);
			textAreaHeroTypeFlavourText.setLineWrap(true);
			textAreaHeroTypeFlavourText.setEditable(false);
			contentPanel.add(textAreaHeroTypeFlavourText, "4, 6, 1, 5, fill, fill");
		}
		{
			JLabel lblAbility = new JLabel("Ability:");
			contentPanel.add(lblAbility, "2, 12");
		}
		{
			lblHeroAbilityName = new JLabel("<lblHeroAbilityName>");
			contentPanel.add(lblHeroAbilityName, "4, 12");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						heroName = textFieldHeroName.getText().trim();
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
		{
			textAreaHeroAbilityFlavourText = new JTextArea();
			textAreaHeroAbilityFlavourText.setBackground(UIManager.getColor("Label.background"));
			textAreaHeroAbilityFlavourText.setWrapStyleWord(true);
			textAreaHeroAbilityFlavourText.setLineWrap(true);
			textAreaHeroAbilityFlavourText.setEditable(false);
			contentPanel.add(textAreaHeroAbilityFlavourText, "4, 14, 1, 5, fill, fill");
		}
		{
			JLabel lblMaxHealth = new JLabel("Max Health:");
			contentPanel.add(lblMaxHealth, "2, 20");
		}
		{
			progressBarMaxHealth = new JProgressBar();
			progressBarMaxHealth.setMaximum(200);
			contentPanel.add(progressBarMaxHealth, "4, 20");
		}
		{
			JLabel lblRecoveryRate = new JLabel("Recovery Rate:");
			contentPanel.add(lblRecoveryRate, "2, 22");
		}
		{
			progressBarRecoveryRate = new JProgressBar();
			progressBarRecoveryRate.setMaximum(200);
			contentPanel.add(progressBarRecoveryRate, "4, 22");
		}

	}
	
	/**
	 * Returns whether the hero name is valid.
	 * @param heroName
	 * @return
	 */
	private boolean isHeroNameValid(String heroName) {
		
		if (heroName == null || heroName.isEmpty())
			return false;
		
		heroName = heroName.trim();
		
		return (
			heroName.length() >= Settings.getHeroNameMin() &&
			heroName.length() <= Settings.getHeroNameMax()
		);
		
	}
	
	/**
	 * @return The Hero object if all attributes are valid, otherwise null.
	 */
	public Hero getHero() {
		
		if (isHeroNameValid(this.heroName) && this.heroType != null) {
			
			return new Hero(this.heroName, this.heroType);
			
		} else {
			
			return null;
			
		}
		
	}
	
	/**
	 * @return The result of this dialog.
	 */
	public DialogResult getDialogResult() {
		
		return this.dialogResult;
		
	}
	
	/**
	 * Sets the result of this dialog.
	 * @param dialogResult The result of the dialog.
	 */
	private void setDialogResult(DialogResult dialogResult) {
		
		this.dialogResult = dialogResult;
		
	}
	
	/**
	 * Simply updates the labels relating to the chosen hero type.
	 */
	private void updateLabels() {
		
		if (this.heroType == null)
			return;
		
		textAreaHeroTypeFlavourText.setText(this.heroType.getFlavourText());
		lblHeroAbilityName.setText(this.heroType.getAbility().getName());
		textAreaHeroAbilityFlavourText.setText(this.heroType.getAbility().getFlavourText());
		
		progressBarMaxHealth.setValue(heroType.getMaxHealth());
		progressBarRecoveryRate.setValue((int) (100 * heroType.getRecoveryRate())); 
		
	}
	
	protected void setHeroName(String name) {
		
		name = name.trim();
		this.heroName = name;
		textFieldHeroName.setText(name);
		
	}
	
	protected void setHeroType(HeroType type) {
		
		comboBoxHeroType.setSelectedItem(type.toString());
		
	}
}
