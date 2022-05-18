package game;

import game.RunnableCanvas;
import javax.swing.JApplet;
import srpg.SRPG;

public class RunnableApplet extends JApplet {

	RunnableCanvas rc;

	public void init() {
		this.setSize(640, 460);
		this.rc = new SRPG(640, 460, this);
		this.getContentPane().add(this.rc);
	}

	public void start() {
		this.rc.start();
	}

	public void stop() {
		this.rc.stop();
	}
}
