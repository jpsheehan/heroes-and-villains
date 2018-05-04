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
import javax.swing.SwingConstants;

public class NewHeroDialog extends JDialog implements Returnable {

	private HeroType heroType;
	private String heroName;
	private DialogResult dialogResult;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 79073142309546932L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldHeroName;
	private JLabel lblHeroTypeFlavourText;
	private JLabel lblHeroAbilityName;
	private JLabel lblHeroAbilityFlavourText;
	private JComboBox comboBoxHeroType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewHeroDialog dialog = new NewHeroDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
				
				heroType = HeroType.values()[0];
				updateLabels();
				
			}
		});
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
			JLabel lblHeroName = new JLabel("Hero Name:");
			contentPanel.add(lblHeroName, "2, 2, left, default");
		}
		{
			textFieldHeroName = new JTextField();
			textFieldHeroName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String name = textFieldHeroName.getText();
					if (isHeroNameValid(name)) {
						
						heroName = name.trim();
						// make it default color
						
					} else {
						
						// make it red?
						
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
			comboBoxHeroType = new JComboBox();
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
			contentPanel.add(lblDescription, "2, 6");
		}
		{
			lblHeroTypeFlavourText = new JLabel("<lblHeroTypeFlavourText>");
			contentPanel.add(lblHeroTypeFlavourText, "4, 6, 1, 5, default, top");
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
			lblHeroAbilityFlavourText = new JLabel("<lblHeroAbilityFlavourText>");
			lblHeroAbilityFlavourText.setVerticalAlignment(SwingConstants.TOP);
			contentPanel.add(lblHeroAbilityFlavourText, "4, 14, 1, 5, default, fill");
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
	 * Returns the Hero object if all attributes are valid, otherwise null.
	 * @return
	 */
	public Hero getHero() {
		
		if (isHeroNameValid(this.heroName) && this.heroType != null) {
			
			return new Hero(this.heroName, this.heroType);
			
		} else {
			
			return null;
			
		}
		
	}
	
	/**
	 * Gets the result of this dialog.
	 * @return
	 */
	public DialogResult getDialogResult() {
		
		return this.dialogResult;
		
	}
	
	/**
	 * Sets the result of this dialog.
	 * @param dialogResult
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
		
		lblHeroTypeFlavourText.setText(this.heroType.getFlavourText());
		lblHeroAbilityName.setText(this.heroType.getAbility().getName());
		lblHeroAbilityFlavourText.setText(this.heroType.getAbility().getFlavourText());
		
	}

}
