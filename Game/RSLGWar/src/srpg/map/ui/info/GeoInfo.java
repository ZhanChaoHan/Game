// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:25
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui.info;

import game.Sprite;
import srpg.Field;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.info.MapInformation;
import srpg.screen.Information;

public class GeoInfo extends MapInformation {

	int realWidth;

	public GeoInfo(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		byte offX = -25;
		this.init(gom.width * 2 / 5 - offX, 35);
		this.realWidth = this.width + offX;
		this.title = "        GeoInfo";
		sp.setPos(plnNo, (double) offX,
				(double) (gom.height - this.height - 80));
	}

	private Information.StrData[] initSds() {
		return new Information.StrData[] {
				new Information.StrData("\u79fb\u52d5 : ",
						this.realWidth * 3 / 16, this.height * 2 / 3),
				new Information.StrData("\u9ad8\u3055 : ",
						this.realWidth * 7 / 16, this.height * 2 / 3),
				new Information.StrData("\u88ab\u30c0\u30e1 : ",
						this.realWidth * 10 / 16 + 5, this.height * 2 / 3) };
	}

	public void mainMotion() {
		this.sds = this.initSds();
		Field f = this.map.hex[((Integer) this.current.y).intValue()][((Integer) this.current.x)
				.intValue()];
		this.sds[0].str = this.sds[0].str
				+ (f.fd.cost < 0.0D ? "--" : Double.valueOf(f.fd.cost));
		this.sds[1].str = this.sds[1].str + f.height;
		this.sds[2].str = this.sds[2].str + "\u00d7" + f.fd.damageEffect;
	}
}
