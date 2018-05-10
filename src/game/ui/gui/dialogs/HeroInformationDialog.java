package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeroInformationDialog extends JDialog implements Returnable {
	
	private DialogResult result;
	private Hero hero;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8771057294615620600L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HeroInformationDialog dialog = new HeroInformationDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HeroInformationDialog(Hero hero) {
		
		this.hero = hero;
		
		setResizable(false);
		setModal(true);
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setDialogResult(DialogResult.OK);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	@Override
	public DialogResult getDialogResult() {
		return result;
	}
	
	private void setDialogResult(DialogResult res) {
		result = res;
	}
}
