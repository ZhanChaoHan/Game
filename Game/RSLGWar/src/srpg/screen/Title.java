package srpg.screen;

import game.Draw;
import game.GameObject;
import game.GameObjectManager;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import srpg.screen.InterMission;
import srpg.screen.Screen;

public class Title extends Screen {

	private BufferedImage img;

	public Title(GameObjectManager ggom, Sprite sp, int plnNo, int xx, int yy) {
		super(ggom, sp, plnNo, xx, yy);
		String file = "shade_reimu";
		sp.addGrp(file);
		this.img = sp.getGrp(file);
		sp.setDraw(plnNo, new Draw() {

			int count = 0;

			public void drawing(Graphics g, Plane pln) {
				Title.this.clear(g);
				g.drawImage(Title.this.img, -40, -60, (ImageObserver) null);
				Font current = g.getFont();
				g.setFont(new Font(current.getFontName(), 3, 24));
				g.setColor(Color.BLACK);
				Title.this.drawCenterString(
						"\u6771\u65b9SRPG\u98a8\u30a2\u30d7\u30ec\u30c3\u30c8",
						((Double) pln.coord.x).intValue() + 20,
						((Double) pln.coord.y).intValue(), g);
				g.setFont(current);
				++this.count;
				this.count %= 12;
				g.setColor(new Color(0, 0, 0, 255 - this.count * 16));
				Title.this.drawCenterString("Press Enter",
						((Double) pln.coord.x).intValue() + 80,
						((Double) pln.coord.y).intValue() + 120, g);
				g.setColor(Color.BLACK);

			}
		});
		sp.setPos(plnNo, (double) (this.gom.width / 2),
				(double) (this.gom.height / 3));
		this.y = 2;
		sp.playBGM(Sprite.BGM.TITLE);
	}

	protected void mainAction() {
		if (this.selected) {
			this.sp.playSE(Sprite.Preset.SELECT);
			this.destructor();
			this.gom.addGO(InterMission.class, 0, 0, new GameObject[0]);
		}

	}

	static GameObjectManager access$1(Title var0) {
		return var0.gom;
	}
}
