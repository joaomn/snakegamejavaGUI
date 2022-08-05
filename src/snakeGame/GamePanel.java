package snakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


import javax.swing.JPanel;
import javax.swing.Timer;



public class GamePanel extends JPanel implements ActionListener {

	static final int SCHREEN_WIDTH = 600;
	static final int SCHREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCHREEN_WIDTH * SCHREEN_HEIGHT) / UNIT_SIZE;
	int DELAY = 300;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 3;
	private int applesEaten;
	int appleX;
	int appleY;
	char direction = 'D';
	boolean running = false;
	Timer time;
	Random random;

	public GamePanel() {

		random = new Random();
		this.setPreferredSize(new Dimension(SCHREEN_WIDTH, SCHREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();

	}

	public void startGame() {
		newApple();
		running = true;
		time = new Timer(DELAY, this);
		time.start();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);

	}

	public void draw(Graphics g) {

		if (running) {

			for (int i = 0; i < SCHREEN_HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCHREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCHREEN_WIDTH, i * UNIT_SIZE);
			}

			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(Color.ORANGE);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(255, 215, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten, (SCHREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
					g.getFont().getSize());
		} else {
			gameOver(g);
		}

	}

	public void newApple() {
		appleX = random.nextInt((int) (SCHREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCHREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;

		}
	}

	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			DELAY = DELAY + 30;
			newApple();
		}

	}

	public void colisao() {
		// colisao cabeça com corpo
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// colisao cabeça com borda esquerda
		if (x[0] < 0) {
			running = false;

		}
		// colisao cabeça com borda direita
		if (x[0] > SCHREEN_WIDTH) {
			running = false;

		}
		// colisao cabeça com borda superior
		if (y[0] < 0) {
			running = false;

		}
		// colisao cabeça com borda inferior
		if (y[0] > SCHREEN_HEIGHT) {
			running = false;
		}

		if (!running) {
			time.stop();
		}

	}

	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Comic Sans", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten, (SCHREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2,
				g.getFont().getSize());
		// Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("Comic Sans", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCHREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCHREEN_HEIGHT / 2);
	}

	public int getApplesEaten(int appleEaten) {
		this.applesEaten = appleEaten;
		return  appleEaten;
	}

	public int setApplesEaten( int appleEaten) {
		this.applesEaten = appleEaten;
		return appleEaten;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			colisao();

		}
		repaint();

	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}

	}

	

	
}
