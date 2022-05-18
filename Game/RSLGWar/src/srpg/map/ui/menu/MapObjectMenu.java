package srpg.map.ui.menu;

import game.Sprite;
import java.awt.Graphics;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;
import srpg.screen.Menu;

public class MapObjectMenu extends Menu {

	protected final StageMap map;
	protected final SRPGGameObjectManager gom;

	public MapObjectMenu(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y);
		this.gom = gom;
		this.map = m;
		sp.setPos(plnNo, StageDrawOrder.MENU.z());
	}

	protected void createCursor(Coord current) {
		this.gom.createMapObject(
				this.map,
				this.menuCursor,
				((Double) current.x).intValue()
						+ ((Integer) this.offset.x).intValue()
						+ this.commandHeight, ((Double) current.y).intValue()
						+ ((Integer) this.offset.y).intValue() + this.span,
				this);
	}

	protected void drawBGRect(int x, int y, int width, int height, Graphics g) {
		Sprite.drawDesignedRect(x, y, width, height, g);
	}

	protected abstract class MOMenuCursor extends Menu.MenuCursor {

		protected final StageMap map;
		protected final SRPGGameObjectManager gom;

		public MOMenuCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int y, StageMap m) {
			super(gom, sp, plnNo, x, y);
			this.gom = gom;
			this.map = m;
			sp.setPos(plnNo, StageDrawOrder.MENU_CURSOR.z());
		}
	}
}
