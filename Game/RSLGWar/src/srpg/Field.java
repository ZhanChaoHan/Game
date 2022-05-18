// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg;

import game.Sprite;
import java.awt.image.BufferedImage;
import srpg.FieldData;

public class Field {

	public final FieldData fd;
	public final int height;
	public final BufferedImage[] graphic;

	public Field(FieldData fd, int height, Sprite sp) {
		this.fd = fd;
		this.height = height;
		this.graphic = new BufferedImage[3];

		for (int i = 0; i < this.graphic.length; ++i) {
			sp.addGrp(fd.graphicName[i]);
			this.graphic[i] = sp.getGrp(fd.graphicName[i]);
		}

	}
}
