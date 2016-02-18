package sp6.engine;

import sp6.engine.controller.InputController;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * 
 * Hela spelets f�nster. Har en/flera menyer. Hostar GameWorld
 *
 */
public class GameWindow extends JFrame {
    private final BufferStrategy bufferStrategy;


    public GameWindow() {
        setSize(1024, 768);
        setBackground(Color.black);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().setLayout(new FlowLayout());
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
    }

    public void setInputController(InputController inputController) {
        addKeyListener(inputController);
    }
    public void clear() {
        bufferStrategy.getDrawGraphics().clearRect(0, 0, 640, 480);
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) bufferStrategy.getDrawGraphics();
    }

    public void draw() {
        bufferStrategy.show();
    }

    public void setJComponent(JComponent component) {
        add(component);
    }

    public void removeJComponent(JComponent component) {
        remove(component);
    }
}




































//	private JPanel gameDisplay;
//	private JMenu mFile = new JMenu("File");
//	private JMenuItem mNewGame = new JMenuItem("New Game");
//	private JMenuItem mOptions = new JMenuItem("Options");
//	private JMenuItem mExit = new JMenuItem("Exit");
//	private JMenuBar menu = new JMenuBar();
//
//	GameWindow(){
//		setJMenuBar(this.menu);
//		this.menu.add(this.mFile);
//		this.mFile.add(this.mNewGame);
//		this.mFile.add(this.mOptions);
//		this.mFile.add(this.mExit);
//		this.mNewGame.addActionListener(new MenuListener());
//		this.mOptions.addActionListener(new MenuListener());
//		this.mExit.addActionListener(new MenuListener());
//
//		this.gameDisplay = new JPanel();
//		this.gameDisplay.setLayout(null);
//		add(this.gameDisplay, "Center");
//
//		setVisible(true);
//		pack();
//	}
//
//	class MenuListener implements java.awt.event.ActionListener{
//		MenuListener(){}
//
//		public void actionPerformed(ActionEvent ae){
//			if(ae.getSource() == GameWindow.this.mNewGame){
//				// Start a new game.
//			}
//			if(ae.getSource() == GameWindow.this.mOptions){
//				// Show options window.
//			}
//			if(ae.getSource() == GameWindow.this.mExit){
//				System.exit(0);
//			}
//		}
//	}