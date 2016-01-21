package sp6.engine;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * 
 * Hela spelets fönster. Har en/flera menyer. Hostar GameWorld
 *
 */
public class GameWindow extends javax.swing.JFrame {
	private JPanel gameDisplay;
	private JMenu mFile = new JMenu("File");
	private JMenuItem mNewGame = new JMenuItem("New Game");
	private JMenuItem mOptions = new JMenuItem("Options");
	private JMenuItem mExit = new JMenuItem("Exit");
	private JMenuBar menu = new JMenuBar();
	
	GameWindow(){
		setJMenuBar(this.menu);
		this.menu.add(this.mFile);
		this.mFile.add(this.mNewGame);
		this.mFile.add(this.mOptions);
		this.mFile.add(this.mExit);
		
		this.gameDisplay = new JPanel();
		this.gameDisplay.setLayout(null);
		add(this.gameDisplay, "Center");
		
		setVisible(true);
		pack();
	}
	
	class MenuListener implements java.awt.event.ActionListener{
		MenuListener(){}
		
		public void actionPerformed(ActionEvent ae){
			if(ae.getSource() == GameWindow.this.mNewGame){
				// Start a new game.
			}
			
		}
	}
	
	public static void main(String[] args){
		new GameWindow();
	}
}
