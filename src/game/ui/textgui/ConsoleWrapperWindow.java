package game.ui.textgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import game.StreamHelpers;
import game.ui.text.TextUserInterfaceHelpers;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConsoleWrapperWindow {

	private JFrame frmHeroesAndVillains;
	private JTextField textFieldInput;
	private JTextArea textAreaOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsoleWrapperWindow window = new ConsoleWrapperWindow();
					window.frmHeroesAndVillains.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConsoleWrapperWindow() {
		
		initialize();
		
		StreamHelpers.setupOutputStream();
		
		RunnableTextUserInterface textUi = new RunnableTextUserInterface();
		textUi.start();
		
		flushIO(false);
		
		textFieldInput.grabFocus();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHeroesAndVillains = new JFrame();
		frmHeroesAndVillains.setTitle("Heroes and Villains");
		frmHeroesAndVillains.setBounds(100, 100, 640, 480);
		frmHeroesAndVillains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHeroesAndVillains.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmHeroesAndVillains.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		textFieldInput = new JTextField();
		textFieldInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					flushIO();
				}
			}
		});
		panel.add(textFieldInput);
		textFieldInput.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				flushIO();
			}
		});
		panel.add(btnOk);
		
		textAreaOutput = new JTextArea();
		textAreaOutput.setRows(25);
		textAreaOutput.setColumns(80);
		textAreaOutput.setFont(new Font("Courier", Font.PLAIN, 14));
		textAreaOutput.setEditable(false);
		frmHeroesAndVillains.getContentPane().add(textAreaOutput, BorderLayout.CENTER);
		
	}
	
	private void flushIO() {
		flushIO(true);
	}
	
	private void flushIO(boolean sendInput) {
		
		String input = textFieldInput.getText();
		textFieldInput.setText("");
		
		if (sendInput) {
			input += "\n";
			StreamHelpers.setInputStream(input);
		}
		
		int availableBytes;
		
		do {
			availableBytes = StreamHelpers.getAvailableOutput();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} while (availableBytes != StreamHelpers.getAvailableOutput());
		
		String output = StreamHelpers.getOutputStream();
		
		if (output != null && !output.equals("")) {
			
			if (output.contains(TextUserInterfaceHelpers.repeatString("\n", TextUserInterfaceHelpers.getConsoleHeight()))) {
				
				textAreaOutput.setText(output.replaceAll(TextUserInterfaceHelpers.repeatString("\n", TextUserInterfaceHelpers.getConsoleHeight()), ""));
				
			} else if (output.contains("!!!NEWLINE~HACKY~CHARACTER!!!")) {
				
				textAreaOutput.setText(output.replaceAll("!!!NEWLINE~HACKY~CHARACTER!!!", ""));
				
			} else {
			
				textAreaOutput.setText(textAreaOutput.getText() + output);
				
			}
		}
		
		textFieldInput.grabFocus();
	}

}
