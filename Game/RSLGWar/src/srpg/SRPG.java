package srpg;

import game.GameObject;
import game.RunnableCanvas;
import game.Sprite;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import srpg.SRPGGameObjectManager;
import srpg.screen.Title;

public class SRPG extends RunnableCanvas {

	private Sprite sp;
	private SRPGGameObjectManager gom;
	private SRPG.Mode mode;
	BufferedImage bg;

	public SRPG(int w, int h, Container co) {
		super(w, h, co);
		this.sp = new Sprite(co, this);
		this.gom = new SRPGGameObjectManager(this.sp, this.kb.keyQ,
				this.CANVAS_W, this.CANVAS_H);
		this.mode = SRPG.Mode.START_INIT;
		this.bg = new BufferedImage(w, h, 2);
	}

	protected void doubleBufPaint(Graphics g) {
		switch (this.mode) {
		case START_INIT:
			this.gom.addGO(Title.class, 0, 0, new GameObject[0]);
			this.mode = SRPG.Mode.PLAYING;
		case START:
		case INIT:
		default:
			break;
		case PLAYING:
			if (this.gom.isPlayable()) {
				this.gom.play();
			} else {
				this.mode = SRPG.Mode.START_INIT;
			}
		}

		Graphics2D g2d = this.bg.createGraphics();
		this.sp.paint(g2d, this.bg);
		g2d.dispose();
		g.drawImage(this.bg, 0, 0, (ImageObserver) null);
	}

	static enum Mode {

		START_INIT("START_INIT", 0), START("START", 1), INIT("INIT", 2), PLAYING(
				"PLAYING", 3);

		private Mode(String var1, int var2) {
		}
	}
}
