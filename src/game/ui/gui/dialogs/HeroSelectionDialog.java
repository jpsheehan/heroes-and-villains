package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HeroSelectionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HeroSelectionDialog dialog = new HeroSelectionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HeroSelectionDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

//Code from SelectHeroDialog
//
//package game.ui.gui.dialogs;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.FlowLayout;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//
//import game.Settings;
//import game.Team;
//import game.character.Hero;
//import game.character.HeroType;
//import game.ui.gui.DialogResult;
//import game.ui.gui.Returnable;
//
//import com.jgoodies.forms.layout.FormSpecs;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JComboBox;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import javax.swing.JTextArea;
//import javax.swing.UIManager;
//import java.awt.SystemColor;
//
//public class SelectHeroDialog extends JDialog implements Returnable {
//
//	private HeroType heroType;
//	private String heroName;
//	private DialogResult dialogResult;
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8544293703257641825L;
//	private final JPanel contentPanel = new JPanel();
//	private JLabel lblHeroAbilityName;
//	//private Color defaultTextFieldBackgroundColor;
//	private JComboBox<String> comboBoxHeroName;
//	private JTextArea textAreaHeroTypeFlavourText;
//	private JTextArea textAreaHeroAbilityFlavourText;
//	private JTextField textFieldHeroType;
//	private Team team;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		
//		try {
//			SelectHeroDialog dialog = new SelectHeroDialog(team);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Create the dialog.
//	 */
//	public SelectHeroDialog(Team team) {
//		this.team = team;
//		setResizable(false);
//		setModal(true);
//		setTitle("Select Hero");
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent arg0) {
//				
//				// Populate the combo box with names of Heros in the team
//				for (Hero name :team.getHeroes() ) {
//					
//					comboBoxHeroName.addItem(name.getName());
//					
//				}
//				
//				comboBoxHeroName.setSelectedItem(HeroType.values()[0]);
//				//defaultTextFieldBackgroundColor = textFieldHeroName.getBackground();
//				
//				heroType = HeroType.values()[0];
//				updateLabels();
//				
//			}
//		});
//		setBounds(100, 100, 452, 354);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
//				FormSpecs.RELATED_GAP_COLSPEC,
//				FormSpecs.DEFAULT_COLSPEC,
//				FormSpecs.RELATED_GAP_COLSPEC,
//				ColumnSpec.decode("default:grow"),},
//			new RowSpec[] {
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				RowSpec.decode("default:grow"),
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				RowSpec.decode("default:grow"),
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,}));
//		{
//			JLabel lblHeroName = new JLabel("Hero Name:");
//			contentPanel.add(lblHeroName, "2, 2, right, default");
//		}
//		{
//			JComboBox comboBoxHeroName = new JComboBox();
//			contentPanel.add(comboBoxHeroName, "4, 2, fill, default");
//		}
//		{
//			JLabel lblHeroType = new JLabel("Hero Type:");
//			contentPanel.add(lblHeroType, "2, 4, right, default");
//		}
//		{
//			textFieldHeroType = new JTextField();
//			contentPanel.add(textFieldHeroType, "4, 4, fill, default");
//			textFieldHeroType.setColumns(10);
//		}
//		{
//			JLabel lblDescription = new JLabel("Description:");
//			contentPanel.add(lblDescription, "2, 6, default, top");
//		}
//		{
//			textAreaHeroTypeFlavourText = new JTextArea();
//			textAreaHeroTypeFlavourText.setBackground(UIManager.getColor("Label.background"));
//			textAreaHeroTypeFlavourText.setWrapStyleWord(true);
//			textAreaHeroTypeFlavourText.setLineWrap(true);
//			textAreaHeroTypeFlavourText.setEditable(false);
//			contentPanel.add(textAreaHeroTypeFlavourText, "4, 6, 1, 3, fill, fill");
//		}
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						
//						heroName = textFieldHeroName.getText().trim();
//						setDialogResult(DialogResult.OK);
//						dispose();
//						
//					}
//				});
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						
//						setDialogResult(DialogResult.CANCEL);
//						dispose();
//						
//					}
//				});
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
//		{
//			JLabel lblAbility = new JLabel("Ability:");
//			contentPanel.add(lblAbility, "2, 10");
//		}
//		{
//			lblHeroAbilityName = new JLabel("<lblHeroAbilityName>");
//			contentPanel.add(lblHeroAbilityName, "4, 10");
//		}
//		{
//			textAreaHeroAbilityFlavourText = new JTextArea();
//			textAreaHeroAbilityFlavourText.setBackground(UIManager.getColor("Label.background"));
//			textAreaHeroAbilityFlavourText.setWrapStyleWord(true);
//			textAreaHeroAbilityFlavourText.setLineWrap(true);
//			textAreaHeroAbilityFlavourText.setEditable(false);
//			contentPanel.add(textAreaHeroAbilityFlavourText, "4, 12, fill, fill");
//		}
//		{
//			JLabel lblHealth = new JLabel("Health:");
//			contentPanel.add(lblHealth, "2, 14");
//		}
//		{
//			JLabel lblHeroHealth = new JLabel("<lblHeroHealth>");
//			contentPanel.add(lblHeroHealth, "4, 14");
//		}
//		{
//			JTextArea textAreaHeroHealth = new JTextArea();
//			textAreaHeroHealth.setBackground(UIManager.getColor("Label.background"));
//			contentPanel.add(textAreaHeroHealth, "4, 16, fill, fill");
//		}
//		{
//			JLabel lblPowerUps = new JLabel("Power Up:");
//			contentPanel.add(lblPowerUps, "2, 18");
//		}
//		{
//			JLabel lblPowerUp = new JLabel("<lblPowerUp>");
//			contentPanel.add(lblPowerUp, "4, 18");
//		}
//		{
//			JTextArea textAreaHeroPowerUp = new JTextArea();
//			textAreaHeroPowerUp.setBackground(UIManager.getColor("Label.background"));
//			textAreaHeroPowerUp.setEditable(false);
//			contentPanel.add(textAreaHeroPowerUp, "4, 20, fill, fill");
//		}
//
//	}
//	
//	/**
//	 * Returns whether the hero name is valid.
//	 * @param heroName
//	 * @return
//	 */
//	private boolean isHeroNameValid(String heroName) {
//		
//		if (heroName == null || heroName.isEmpty())
//			return false;
//		
//		heroName = heroName.trim();
//		
//		return (
//			heroName.length() >= Settings.getHeroNameMin() &&
//			heroName.length() <= Settings.getHeroNameMax()
//		);
//		
//	}
//	
//	/**
//	 * Returns the Hero object if all attributes are valid, otherwise null.
//	 * @return
//	 */
//	public Hero getHero() {
//		
//		if (isHeroNameValid(this.heroName) && this.heroType != null) {
//			
//			return new Hero(this.heroName, this.heroType);
//			
//		} else {
//			
//			return null;
//			
//		}
//		
//	}
//	
//	/**
//	 * Gets the result of this dialog.
//	 * @return
//	 */
//	public DialogResult getDialogResult() {
//		
//		return this.dialogResult;
//		
//	}
//	
//	/**
//	 * Sets the result of this dialog.
//	 * @param dialogResult
//	 */
//	private void setDialogResult(DialogResult dialogResult) {
//		
//		this.dialogResult = dialogResult;
//		
//	}
//	
//	/**
//	 * Simply updates the labels relating to the chosen hero type.
//	 */
//	private void updateLabels() {
//		
//		if (this.heroType == null)
//			return;
//		
//		
//		textAreaHeroTypeFlavourText.setText(this.heroType.getFlavourText());
//		lblHeroAbilityName.setText(this.heroType.getAbility().getName());
//		textAreaHeroAbilityFlavourText.setText(this.heroType.getAbility().getFlavourText());
//		
//	}
//	
//	protected void setHeroName(String name) {
//		
//		name = name.trim();
//		this.heroName = name;
//		textFieldHeroName.setText(name);
//		
//	}
//	
//	protected void setHeroType(HeroType type) {
//		
//		comboBoxHeroType.setSelectedItem(type.toString());
//		
//	}
//}



