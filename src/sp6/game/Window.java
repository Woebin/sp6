package sp6.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {
	private int x = 0;
	private int y = 0;
	private int dx = 0;
	private int dy = 0;
	private boolean isRunning = true;
	private int fps = 60;
	//private final Image image;
	private AnimationController ac = new AnimationController();
	private final BufferStrategy bufferStrategy;
	private final CircleAnimation circleAnimation;

	public Window() {
		setSize(640, 480);
		setBackground(Color.black);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// setBackground(Color.BLACK);
		addKeyListener(new KeyAdapterListener(this));
		createBufferStrategy(2);
      	bufferStrategy = getBufferStrategy();
        x = 40;
        y = 60; 
        circleAnimation = new CircleAnimation(x, y);
	}

	public void start() {
		Long time = 0l;
		long previousTime = System.currentTimeMillis();

		while(isRunning) { 
	        time = System.currentTimeMillis(); 

	        update(); 
	        draw(time - previousTime); 


	        time = (1000 / fps) - (System.currentTimeMillis() - time); 
	        previousTime = System.currentTimeMillis();
	        if (time > 0) { 
                try { 
                    Thread.sleep(time); 
                } 
                catch(Exception e){} 
	        } 
        }
	}


	private void update() {
		//System.out.println("update");
	}

	private void draw(double deltaTime) {

		//System.out.println("draw");
		//Graphics g = getGraphics();
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, 640, 480);
	//	g.drawImage(ac.getAnimatedSprite("std", 8, deltaTime), x += dx, y += dy, null);
		x += dx;
		y += dy;
		if (dx == -1)
			ac.playRepeat(g, "alien1", -1, x, y);
		if (dx == 1)
			ac.playRepeat(g, "alien1", 1, x, y);
		if (dx == 0)
			ac.playStatic(g, "alien1", 1, x, y, 5);

		ac.playRepeat(g, "flamingo", -1, 320, 240);
		ac.playRepeat(g, "flamingo", 1, 400, 240);
		circleAnimation.setCenterX(x)
						.setCenterY(y);

//		System.out.println(circleAnimation.getCoordinate().getX() + ", " + circleAnimation.getCoordinate().getY());
		ac.playRepeat(g, "piranha", -1, circleAnimation);
			
	//	g.drawImage(ac.getPlayRepeat("alien1"), x + 48, y, -48, 64, null);
	//	g.drawImage(ac.getPlayRepeat("flamingo"), 320, 240, null);
		bufferStrategy.show();
		g.dispose();
	}

	private class CircleAnimation implements AnimationMovement {
		private int x;
		private int centerX;
		private int y;
		private int centerY;
		private float angle = 0; //init angle
		private double radius = 30;

		public CircleAnimation(int xStart, int yStart) {
			x = xStart;
			y = yStart; 
		}
		
		public CircleAnimation setCenterX(int x) {
			this.centerX = centerX;
			return this;
		}
		public CircleAnimation setCenterY(int y) {
			this.centerY = centerY;
			return this;
		}

		@Override
		public Coordinate getCoordinate() {

			//call in an update
			x = (centerX -= radius * Math.cos(angle));
			//y = (centerY += radius * Math.sin(angle));
			y = 100;
			angle -= 0.001;

			return new Coordinate(x, y);
		}
	}

	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		 	case KeyEvent.VK_LEFT:
            	dx = -1;
            	break;
            case KeyEvent.VK_RIGHT:
            	dx = 1;
            	break;
            case KeyEvent.VK_UP:
            	dy = -1;
            	break;
            case KeyEvent.VK_DOWN:
            	dy = 1;
    	    	break;
        }
		//System.out.println("keypressed: " + event.getKeyCode());
	}

	public void keyReleased(KeyEvent event) {
		//System.out.println(event.)
		switch (event.getKeyCode()) {
		 	case KeyEvent.VK_LEFT:
            	dx = 0;
            	break;
            case KeyEvent.VK_RIGHT:
            	dx = 0;
            	break;
            case KeyEvent.VK_UP:
            	dy = 0;
            	break;
            case KeyEvent.VK_DOWN:
            	dy = 0;
    	    	break;
        }
		//System.out.println("keyreleased: " + event.getKeyCode());
		 
	}

	private class Panel extends JPanel implements ActionListener {
		public Panel() {

		}

		@Override
		public void paintComponent(Graphics graphics) {
			System.out.println("paintComponent");
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("actionPerformed");
			
		}
	 
	}
	private class KeyAdapterListener extends KeyAdapter {
		private final Window delegateKeyPressed;

		public KeyAdapterListener(Window window) {
			delegateKeyPressed = window;
		}

	   	@Override
	    public void keyReleased(KeyEvent e) {
	        delegateKeyPressed.keyReleased(e);
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        delegateKeyPressed.keyPressed(e);
	    }
	}
}