// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:31
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.screen;

import game.DrawOrder;
import game.GameObjectManager;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import srpg.screen.Information;

abstract class MenuInformation extends Information {

	public MenuInformation(GameObjectManager gom, Sprite sp, int plnNo, int x,
			int y) {
		super(gom, sp, plnNo, x, y);
		sp.setPos(plnNo, DrawOrder.INFO.z());
	}

	protected void drawBGRect(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
}
