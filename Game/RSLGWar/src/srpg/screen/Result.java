package srpg.screen;

import game.Blink;
import game.Draw;
import game.GameObjectManager;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import srpg.screen.Screen;

public class Result extends Screen {

	private String str;
	private int income = 0;

	public Result(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, 0, 0);
		if (x == 0) {
			this.str = "Mission complete.";
			sp.playBGM(Sprite.BGM.WIN);
		} else {
			this.str = "Game over.";
			sp.playBGM(Sprite.BGM.LOSE);
		}

		sp.setDraw(plnNo, new Draw() {

			Blink blink = new Blink(255, 24);

			public void drawing(Graphics g, Plane pln) {
				Result.this.clear(g);
				g.setColor(Color.BLACK);
				Result.this.drawCenterString(Result.this.str, pln.x(), pln.y(),
						g);
				g.setColor(new Color(0, 0, 0, 255 + this.blink.blinking()));
				Result.this.drawCenterString("Press SelectKey",
						((Double) pln.coord.x).intValue(),
						((Double) pln.coord.y).intValue() + 150, g);
			}
		});
		sp.setPos(plnNo, (double) (gom.width / 2 - 20),
				(double) (gom.height / 2 - 35));
	}

	protected void mainAction() {
		if (this.selected) {
			this.sp.playSE(Sprite.Preset.SELECT);
			this.destructor();
		}

	}

	public void setViewIncome(int income) {
		this.income = income;
	}
}
