package srpg.screen.talk;

import game.Draw;
import game.GameObject;
import game.GameObjectManager;
import game.Plane;
import game.Sprite;
import java.awt.Graphics;
import myutil.Coord;
import srpg.screen.talk.TalkDrawOrder;

public class Person extends GameObject {

	private Person.Chara chara;
	private Coord pos;

	public Person(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		sp.setPos(plnNo, TalkDrawOrder.PERSON.z());
		sp.setDraw(plnNo, new Draw() {
			public void drawing(Graphics g, Plane pln) {
			}
		});
	}

	void setChara(Person.Chara c) {
		this.chara = c;
		this.sp.addGrp(c.img);
		this.sp.setImg(this.plnNo, c.img);
	}

	void flip() {
		this.sp.flipImg(this.plnNo);
	}

	void setPos(Coord pos) {
		this.sp.setPos(this.plnNo, pos);
	}

	void setPos(Person.Position pos) {
		this.sp.setPos(
				this.plnNo,
				(double) this.gom.width
						* pos.rate
						- (double) ((float) ((Integer) this.sp
								.getImgSize(this.plnNo).x).intValue() / 2.0F),
				0.0D);
	}

	public boolean equals(Object o) {
		return this.chara == ((Person) o).chara;
	}

	public int hashCode() {
		return this.chara.hashCode();
	}

	static enum Position {

		left("left", 0, 0.2D), center("center", 1, 0.5D), right("right", 2,
				0.8D);
		final double rate;

		private Position(String var1, int var2, double rate) {
			this.rate = rate;
		}

		static Person.Position getByName(String name) {
			Person.Position[] var4;
			int var3 = (var4 = values()).length;

			for (int var2 = 0; var2 < var3; ++var2) {
				Person.Position pos = var4[var2];
				if (name.equals(pos.toString())) {
					return pos;
				}
			}

			return null;
		}
	}

	static enum Chara {

		REIMU("REIMU", 0, "reimu", "\u970a\u5922"), MARISA("MARISA", 1,
				"marisa", "\u9b54\u7406\u6c99");
		final String img;
		final String name;

		private Chara(String var1, int var2, String img, String name) {
			this.img = img;
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		static Person.Chara getByName(String name) {
			Person.Chara[] var4;
			int var3 = (var4 = values()).length;

			for (int var2 = 0; var2 < var3; ++var2) {
				Person.Chara chara = var4[var2];
				if (name.equals(chara.name)) {
					return chara;
				}
			}

			return null;
		}
	}
}
