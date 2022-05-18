// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:27
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui;

import game.Draw;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import srpg.SRPGGameObjectManager;
import srpg.map.MapObject;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;

public class TurnCounter extends MapObject {

	int count;
	int denom;

	public TurnCounter(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		sp.setPos(plnNo, (double) (gom.width - 120), 50.0D);
		sp.setPos(plnNo, StageDrawOrder.INFO.z());
		sp.setDraw(plnNo, new Draw() {
			public void drawing(Graphics g, Plane pln) {
				g.setColor(Color.BLACK);
				Sprite.drawShadedString(
						"Time : "
								+ (float) TurnCounter.this.count
								/ 100.0F
								+ " / "
								+ (TurnCounter.this.denom == 0 ? "\u221e"
										: Integer
												.valueOf(TurnCounter.this.denom)),
						((Double) pln.coord.x).intValue(),
						((Double) pln.coord.y).intValue(), g);
			}
		});
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDenom(int denom) {
		this.denom = denom;
	}
}
