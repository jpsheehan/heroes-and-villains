package game.ui.gui.dialogs;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import game.GeneralHelpers;
import game.ImageManager;

import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class LoadingResourcesDialog extends JDialog implements ActionListener {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 4643099426304136218L;
	private final JPanel contentPanel = new JPanel();
	private Timer timer;
	private JLabel lblInformation;
	private JProgressBar loadingBar;
	private long openTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoadingResourcesDialog dialog = new LoadingResourcesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoadingResourcesDialog() {
		timer = new Timer(100, this);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				openTime = (new Date()).getTime();
				
				timer.start();
				
			}
		});
		setTitle("Loading Resources");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 260, 130);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblLoading = new JLabel("Loading...");
				lblLoading.setFont(new Font("Dialog", Font.BOLD, 14));
				panel.add(lblLoading);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				loadingBar = new JProgressBar();
				loadingBar.setStringPainted(false);
				panel.add(loadingBar);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				lblInformation = new JLabel();
				panel.add(lblInformation);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		ImageManager im = GeneralHelpers.imageManager;
		
		try {
			
			im.loadNextImage();
			
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		loadingBar.setMaximum(im.size());
		loadingBar.setValue(im.getNumberOfLoadedImages());
		
		long currentTime = (new Date()).getTime();
		
		// only close if the window stays open for at least a second
		// makes for a better ux
		if (im.getLoaded() && (currentTime - openTime) > 1000) {

			dispose();
			timer.stop();
			
		} else {
			
			String name = im.getLastLoaded();
			
			if (name == null) {
				
				lblInformation.setText("");
				
			} else {
				
				lblInformation.setText(String.format("Loaded %s", name));
				
			}
			
		}
		
	}

}
