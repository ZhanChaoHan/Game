// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:26
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui.info;

import game.Motionable;
import game.Sprite;
import java.awt.Graphics;
import srpg.SRPGGameObjectManager;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;
import srpg.screen.Information;

public abstract class MapInformation extends Information implements Motionable {

	protected final SRPGGameObjectManager gom;
	protected final StageMap map;

	public MapInformation(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y);
		sp.setPos(plnNo, StageDrawOrder.INFO.z());
		this.gom = gom;
		this.map = m;
	}

	public void destructor() {
		this.gom.removeMotionable(this);
		super.destructor();
	}

	protected void drawBGRect(int x, int y, int width, int height, Graphics g) {
		Sprite.drawDesignedRect(x, y, width, height, g);
	}

	protected void update() {
		this.current.assign(this.map.getCursored());
	}
}
