// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:33
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public enum BlendMode {

	NORMAL("NORMAL", 0, (BlendMode) null) {
		void drawImage(BufferedImage img, int x, int y, Graphics2D g) {
			g.drawImage(img, x, y, (ImageObserver) null);
		}

		int blend(int alpha, int d, int s) {
			return 0;
		}
	},
	ADDITIVE("ADDITIVE", 1, (BlendMode) null) {
		protected int blend(int alpha, int d, int s) {
			d &= 255;
			int r = d + (255 & s) * alpha / 255;
			r = this.overflow(r);
			return r;
		}
	},
	SCREEN("SCREEN", 2, (BlendMode) null) {
		int blend(int alpha, int d, int s) {
			d &= 255;
			int r = d + (255 & s) * d * alpha / 255 / 255;
			r = this.overflow(r);
			return r;
		}
	},
	DODGE("DODGE", 3, (BlendMode) null) {
		int blend(int alpha, int d, int s) {
			d &= 255;
			s &= 255;
			int r = d * 256 / (255 - s * alpha / 255 + 1);
			r = this.overflow(r);
			return r;
		}
	};
	protected static BufferedImage dimg;

	private BlendMode(String var1, int var2) {
	}

	static void setBaseImg(BufferedImage img) {
		dimg = img;
	}

	void drawImage(BufferedImage img, int offX, int offY, Graphics2D graphics) {
		int width = img.getWidth();
		int height = img.getHeight();
		if (offX + width >= 0 && dimg.getWidth() >= offX && offY + height >= 0
				&& dimg.getHeight() >= offY) {
			int clipOffX = offX < 0 ? 0 : offX;
			int clipOffY = offY < 0 ? 0 : offY;
			int clipW = offX < 0 ? width + offX : width;
			int clipH = offY < 0 ? height + offY : height;
			clipW = offX + width > dimg.getWidth() ? dimg.getWidth() - clipOffX
					: clipW;
			clipH = offY + height > dimg.getHeight() ? dimg.getHeight()
					- clipOffY : clipH;
			clipW = Math.min(dimg.getWidth(), clipW);
			clipH = Math.min(dimg.getHeight(), clipH);
			offX = offX < 0 ? -offX : 0;
			offY = offY < 0 ? -offY : 0;
			width -= offX;
			height -= offY;
			int[] srcRGBs = new int[clipW * clipH];
			img.getRGB(offX, offY, clipW, clipH, srcRGBs, 0, clipW);
			int[] destRGBs = new int[clipW * clipH];
			dimg.getRGB(clipOffX, clipOffY, clipW, clipH, destRGBs, 0, clipW);

			for (int i = 0; i < clipW * clipH; ++i) {
				int srcRGB = srcRGBs[i];
				int alpha = srcRGB >>> 24 & 255;
				int r = this.blend(alpha, destRGBs[i] >>> 16, srcRGB >>> 16);
				int g = this.blend(alpha, destRGBs[i] >>> 8, srcRGB >>> 8);
				int b = this.blend(alpha, destRGBs[i], srcRGB);
				destRGBs[i] = -16777216 | r << 16 | g << 8 | b;
			}

			dimg.setRGB(clipOffX, clipOffY, clipW, clipH, destRGBs, 0, clipW);
		}
	}

	protected int overflow(int param) {
		return param > 255 ? 255 : param;
	}

	abstract int blend(int var1, int var2, int var3);

	BlendMode(String var1, int var2, BlendMode var3) {
		this(var1, var2);
	}
}
