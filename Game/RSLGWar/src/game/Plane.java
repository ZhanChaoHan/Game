package game;

import game.BlendMode;
import game.Draw;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import myutil.Coord;

public class Plane {

	boolean view = true;
	boolean animation = false;
	boolean repeat = false;
	BlendMode mode;
	LinkedList trails;
	boolean trail;
	public Coord coord;
	double z;
	public List imgs;
	int animeCount;
	Draw draw;

	public Plane() {
		this.mode = BlendMode.NORMAL;
		this.trails = new LinkedList();
		this.trail = false;
		this.coord = new Coord(Double.valueOf(0.0D), Double.valueOf(0.0D));
		this.imgs = new ArrayList();
		this.animeCount = 0;
	}

	public int x() {
		return ((Double) this.coord.x).intValue();
	}

	public int y() {
		return ((Double) this.coord.y).intValue();
	}

	BufferedImage nextImg() {
		if (this.animation
				&& (this.repeat || this.animeCount < this.imgs.size())) {
			++this.animeCount;
			if (this.repeat) {
				this.animeCount %= this.imgs.size();
			} else if (!this.trail) {
				this.animeCount = Math.min(this.animeCount,
						this.imgs.size() - 1);
			}
		}

		BufferedImage img = null;
		if (this.animeCount < this.imgs.size()) {
			img = (BufferedImage) this.imgs.get(this.animeCount);
		}

		if (this.trail) {
			this.trails.poll();
			if (img == null) {
				this.trails.add((Object) null);
				boolean var9 = false;
				if (this.trails.peekFirst() == null) {
					var9 = true;

					Plane.Trail var10;
					for (Iterator var11 = this.trails.iterator(); var11
							.hasNext(); var9 = var9 && var10 == null) {
						var10 = (Plane.Trail) var11.next();
					}
				}

				this.trail = !var9;
				return img;
			}

			this.trails.add(new Plane.Trail(this.coord.clone(), img));
			AlphaComposite clear = AlphaComposite.getInstance(1, 0.0F);
			AlphaComposite so = AlphaComposite.getInstance(3,
					1.0F - 1.0F / (float) this.trails.size());
			Iterator var5 = this.trails.iterator();

			while (var5.hasNext()) {
				Plane.Trail trail = (Plane.Trail) var5.next();
				if (trail != null) {
					BufferedImage dimg = new BufferedImage(
							trail.img.getWidth(), trail.img.getHeight(), 2);
					Graphics2D g2D = dimg.createGraphics();
					g2D.setComposite(clear);
					java.awt.geom.Rectangle2D.Double rect = new java.awt.geom.Rectangle2D.Double(
							0.0D, 0.0D, (double) dimg.getWidth(),
							(double) dimg.getHeight());
					g2D.fill(rect);
					g2D.setComposite(so);
					g2D.drawImage(trail.img, 0, 0, (ImageObserver) null);
					g2D.dispose();
					trail.img = dimg;
				}
			}
		}

		return img;
	}

	void initTrailCount(int count) {
		this.trail = true;

		for (int i = 0; i < count; ++i) {
			this.trails.add((Object) null);
		}

	}

	class Trail {

		Coord c;
		BufferedImage img;

		Trail(Coord c, BufferedImage img) {
			this.c = c;
			this.img = img;
		}
	}
}
