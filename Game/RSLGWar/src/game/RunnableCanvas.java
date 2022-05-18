package game;

import game.Key;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.net.URL;
import myutil.Queue;

public abstract class RunnableCanvas extends Canvas implements Runnable {

	protected RunnableCanvas.Keyboard kb;
	protected int SPEED = 33;
	protected int CANVAS_W;
	protected int CANVAS_H;
	private Thread thread = null;

	final RunnableCanvas.PaintMode BUFFER_STRATEGY = new RunnableCanvas.PaintMode() {

		private static final int NUM_BUFFERS = 2;
		BufferStrategy bs = null;

		void init() {
			if (this.bs == null) {
				this.initBS();
			}

		}

		void paint(Graphics g) {
			RunnableCanvas.this.superPaint(g);
		}

		void painting() {
			this.render();
			this.screenUpdate();
		}

		void update(Graphics g) {
			RunnableCanvas.this.superUpdate(g);
		}

		void destructor() {
			this.bs = null;
		}

		private void initBS() {
			RunnableCanvas.this.createBufferStrategy(2);
			this.bs = RunnableCanvas.this.getBufferStrategy();
		}

		private void render() {
			Graphics g = this.bs.getDrawGraphics();
			RunnableCanvas.this.doubleBufPaint(g);
			RunnableCanvas.this.drawTrace(g);
			g.dispose();
		}

		private void screenUpdate() {
			if (!this.bs.contentsLost()) {
				this.bs.show();
			} else {
				System.out.println("BufferStrategy : Contents Lost");
				this.initBS();
			}

			Toolkit.getDefaultToolkit().sync();
		}

		public String toString() {
			return "BufferStrategy";
		}
	};
	final RunnableCanvas.PaintMode SELF_BUFFER = new RunnableCanvas.PaintMode() {
		void init() {
		}

		void paint(Graphics g) {
			this.update(g);
		}

		void painting() {
			RunnableCanvas.this.repaint();
		}

		void update(Graphics g) {
			RunnableCanvas.this.doubleBufPaint(g);
			RunnableCanvas.this.drawTrace(g);
		}

		void destructor() {
		}

		public String toString() {
			return "SelfBuffer";
		}
	};
	RunnableCanvas.PaintMode pm;
	boolean traceView = false;
	long sleepTime = 0L;
	long pt = 0L;
	int count = 0;

	public RunnableCanvas(int w, int h, Container co) {
		this.CANVAS_W = w;
		this.CANVAS_H = h;
		this.pm = this.SELF_BUFFER;
		this.kb = new RunnableCanvas.Keyboard();
	}

	public void setTraceView(boolean b) {
		this.traceView = b;
	}

	public void setPaintMode(RunnableCanvas.PaintMode pm) {
		pm.init();
		this.pm = pm;
	}

	public RunnableCanvas.PaintMode getPaintMode() {
		return this.pm;
	}

	public void start() {
		if (this.thread == null) {
			this.thread = new Thread(this);
			this.thread.start();
		}

	}

	public void stop() {
		this.thread = null;
	}

	public void run() {
		long processTime = 0L;

		while (this.thread != null) {
			this.pm.init();
			processTime = System.currentTimeMillis();
			this.pm.painting();
			this.pt = processTime = System.currentTimeMillis() - processTime;
			this.sleepTime = (long) this.SPEED - processTime;
			if (this.sleepTime >= 0L) {
				try {
					Thread.sleep(this.sleepTime);
				} catch (InterruptedException var4) {
					var4.printStackTrace();
				}

				this.count = 0;
			} else if (processTime > 200L) {
				++this.count;
				if (this.count > 10) {
					this.pm = this.SELF_BUFFER;
				}
			}
		}

	}

	public void update(Graphics g) {
		this.pm.update(g);
	}

	private void superUpdate(Graphics g) {
		super.update(g);
	}

	public void paint(Graphics g) {
		this.pm.paint(g);
		this.requestFocus();
	}

	private void superPaint(Graphics g) {
		super.paint(g);
	}

	protected void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.CANVAS_W, this.CANVAS_H);
		g.setColor(Color.BLACK);
	}

	private void drawTrace(Graphics g) {
		if (this.traceView) {
			g.setColor(Color.BLACK);
			g.drawString("sleepTime : " + this.sleepTime + "\n", 0, 10);
			g.drawString("processTime : " + this.pt + "\n", 120, 10);
			g.drawString("PaintMode : " + this.pm, 240, 10);
		}

	}

	protected abstract void doubleBufPaint(Graphics var1);

	public class Keyboard implements KeyListener {

		public final Queue keyQ;
		private final int KEY_DELAY = 2;
		private final int KEY_SPEED = 100;
		boolean up;
		boolean down;
		boolean left;
		boolean right;
		private RunnableCanvas.Keyboard.KeyRepeater kr;

		Keyboard() {
			RunnableCanvas.this.addKeyListener(this);
			this.keyQ = new Queue();
			this.kr = new RunnableCanvas.Keyboard.KeyRepeater();
			this.kr.start();
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case 37:
				if (!this.left) {
					this.keyQ.enqueue(Key.LEFT);
				}

				this.left = true;
				break;
			case 38:
				if (!this.up) {
					this.keyQ.enqueue(Key.UP);
				}

				this.up = true;
				break;
			case 39:
				if (!this.right) {
					this.keyQ.enqueue(Key.RIGHT);
				}

				this.right = true;
				break;
			case 40:
				if (!this.down) {
					this.keyQ.enqueue(Key.DOWN);
				}

				this.down = true;
			}

		}

		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case 8:
			case 27:
			case 127:
				this.keyQ.enqueue(Key.CANCEL);
				break;
			case 10:
			case 32:
				this.keyQ.enqueue(Key.SELECT);
				break;
			case 37:
			case 226:
				this.left = false;
				break;
			case 38:
			case 224:
				this.up = false;
				break;
			case 39:
			case 227:
				this.right = false;
				break;
			case 40:
			case 225:
				this.down = false;
			}

		}

		class KeyRepeater extends Thread {

			public void run() {
				long rightCount = 0L;
				long leftCount = 0L;
				long downCount = 0L;
				long upCount = 0L;

				while (true) {
					long processTime = System.currentTimeMillis();
					if (Keyboard.this.up) {
						if (upCount > 2L) {
							Keyboard.this.keyQ.enqueue(Key.UP);
						} else {
							++upCount;
						}
					} else {
						upCount = 0L;
					}

					if (Keyboard.this.down) {
						if (downCount > 2L) {
							Keyboard.this.keyQ.enqueue(Key.DOWN);
						} else {
							++downCount;
						}
					} else {
						downCount = 0L;
					}

					if (Keyboard.this.left) {
						if (leftCount > 2L) {
							Keyboard.this.keyQ.enqueue(Key.LEFT);
						} else {
							++leftCount;
						}
					} else {
						leftCount = 0L;
					}

					if (Keyboard.this.right) {
						if (rightCount > 2L) {
							Keyboard.this.keyQ.enqueue(Key.RIGHT);
						} else {
							++rightCount;
						}
					} else {
						rightCount = 0L;
					}

					processTime = System.currentTimeMillis() - processTime;
					if (100L - processTime > 0L) {
						try {
							sleep(100L - processTime);
						} catch (InterruptedException var12) {
							var12.printStackTrace();
						}
					}
				}
			}
		}
	}

	abstract class PaintMode {

		abstract void init();

		abstract void destructor();

		abstract void paint(Graphics var1);

		abstract void update(Graphics var1);

		abstract void painting();
	}
}
