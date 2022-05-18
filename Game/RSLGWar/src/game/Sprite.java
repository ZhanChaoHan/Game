package game;

import game.BlendMode;
import game.Draw;
import game.Plane;
import game.RunnableCanvas;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import myutil.Coord;
import myutil.SortedMap;

public class Sprite {

	private final boolean JAR = true;
	private final String imageLocation = "/res/";
	private final String imageExtention = "png";

	private RunnableCanvas canvas;
	private SortedMap plns;
	private int plnID;
	private Comparator compPln;
	private Map grps;
	private MediaTracker tracker;
	private static final String soundLocation = "/res/";
	private static final String musicLocation = "/res/";


	private String bgm;
	public static final String SHOOT = "tm2r_gun18_d";
	public static final String DAMAGE = "bashi2";
	public static final String SUPPORT = "power35";
	public static final String EXPLODE_S = "tm2_bom001";
	public static final String EXPLODE_M = "tm2r_bom31_b";
	public static final String MISS = "fin";
	public static final String LASER_S = "tm2_laser000";
	public static final String LASER_M = "tm2_laser001";
	public static final String CHARGE_S = "eco00_r";
	public static final String CHARGE_M = "eco03";
	public static final String CURE = "tm2_power000";

	public Sprite(Container con, RunnableCanvas rc) {
		this.init();

		this.canvas = rc;
		this.compPln = new Comparator() {
			public int compare(Object arg0, Object arg1) {
				return (int) Math.signum((arg0 == null ? 0.0D
						: ((Plane) arg0).z)
						- (arg1 == null ? 0.0D : ((Plane) arg1).z));
			}
		};
		this.grps = new HashMap();
		this.tracker = new MediaTracker(con);

		Sprite.Preset[] var7;
		int var6 = (var7 = Sprite.Preset.values()).length;

		int var5;
		for (var5 = 0; var5 < var6; ++var5) {
			Sprite.Preset bgm = var7[var5];
			this.addSound(bgm.getFile());
		}

		this.addSound("tm2r_gun18_d");
		this.addSound("bashi2");
		this.addSound("power35");
		this.addSound("tm2_bom001");
		this.addSound("tm2r_bom31_b");
		this.addSound("fin");
		this.addSound("tm2_laser000");
		this.addSound("tm2_laser001");
		this.addSound("eco00_r");
		this.addSound("eco03");
		this.addSound("tm2_power000");
	
		Sprite.BGM[] var9;
		var6 = (var9 = Sprite.BGM.values()).length;

		for (var5 = 0; var5 < var6; ++var5) {
			Sprite.BGM var8 = var9[var5];
			this.addMusic(var8);
		}

	}

	public void init() {
		this.plnID = 0;
		this.plns = new SortedMap(this.compPln);
	}

	public boolean addGrp(String file) {
		if (this.grps.containsKey(file)) {
			return true;
		} else {
			try {
				this.grps.put(
						file,
						ImageIO.read(Thread.currentThread()
								.getContextClassLoader()
								.getResource("res/" + file + "." + "png")));
			} catch (Exception var3) {
				System.out.println(file);
				var3.printStackTrace();
				return false;
			}

			this.tracker.addImage((Image) this.grps.get(file), 1);
			this.waitLoad();
			return true;
		}
	}

	public void switchTraceView() {
		this.canvas.traceView = !this.canvas.traceView;
	}

	public void setPaintMode(RunnableCanvas.PaintMode pm) {
		this.canvas.setPaintMode(pm);
	}

	public RunnableCanvas.PaintMode getPaintMode() {
		return this.canvas.getPaintMode();
	}

	public void switchPaintMode() {
		if (this.canvas.getPaintMode() == this.canvas.BUFFER_STRATEGY) {
			this.canvas.setPaintMode(this.canvas.SELF_BUFFER);
		} else {
			this.canvas.setPaintMode(this.canvas.BUFFER_STRATEGY);
		}

	}

	public boolean waitLoad() {
		try {
			this.tracker.waitForID(1);
			return true;
		} catch (InterruptedException var2) {
			var2.printStackTrace();
			return false;
		}
	}

	public BufferedImage getGrp(String file) {
		return (BufferedImage) this.grps.get(file);
	}

	public boolean addSound(String file) {

			return true;
		
	}

	public boolean addMusic(Sprite.BGM bgm) {
		return this.addMusic(bgm.getFile());
	}

	public boolean addMusic(String file) {
	
			return true;
		
	}

	public void switchBGM() {
		

	}

	public void playBGM(Sprite.BGM bgm) {
		this.playBGM(bgm.getFile());
	}

	public void playBGM(String file) {
		if (this.bgm == null || !this.bgm.equals(file)) {
			this.stopBGM();
			this.bgm = file;
			this.playBGM();
		}
	}

	public void playBGM() {
	
	}

	public void stopBGM() {


	}

	public void playSE(Sprite.Preset ps) {
		this.playSE(ps.getFile());
	}

	public void playSE(String file) {
	
	}

	public int newPln() {
		Plane p = new Plane();
		++this.plnID;
		this.plns.put(Integer.valueOf(this.plnID), p);
		return this.plnID;
	}

	public void delPln(int plnNo) {
		this.plns.remove(Integer.valueOf(plnNo));
	}

	public void initImg(int plnNo, int width, int height) {
		this.initImgs(plnNo, 1, width, height);
	}

	public void initImgs(int plnNo, int count, int width, int height) {
		Plane pln = (Plane) this.plns.get(Integer.valueOf(plnNo));

		for (int i = 0; i < count; ++i) {
			BufferedImage img = new BufferedImage(width, height, 2);
			setTransparent(img);
			pln.imgs.add(img);
		}

	}

	public void initImgs(int plnNo) {
		Plane pln = (Plane) this.plns.get(Integer.valueOf(plnNo));
		pln.imgs.clear();
		pln.animation = false;
		pln.animeCount = 0;
	}

	public void setTransparent(int plnNo) {
		this.setTransparent(plnNo, 0);
	}

	public void setTransparent(int plnNo, int index) {
		setTransparent((BufferedImage) ((Plane) this.plns.get(Integer
				.valueOf(plnNo))).imgs.get(index));
	}

	public void setImg(int plnNo, String file) {
		this.setImg(plnNo, 0, file);
		((Plane) this.plns.get(Integer.valueOf(plnNo))).animation = false;
	}

	public void setImg(int plnNo, int index, String file) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("setImg()");
		} else {
			if (file == null) {
				((Plane) this.plns.get(Integer.valueOf(plnNo))).imgs.add(index,
						(Object) null);
			} else {
				((Plane) this.plns.get(Integer.valueOf(plnNo))).imgs.add(index,
						(BufferedImage) this.grps.get(file));
			}

		}
	}

	public void setAnimeCount(int plnNo, int count) {
		((Plane) this.plns.get(Integer.valueOf(plnNo))).animeCount = count;
	}

	public void setAnimeImg(int plnNo, String file, int rowCount) {
		this.setAnimeImg(plnNo, file, rowCount, 5);
	}

	public void setAnimeImg(int plnNo, String file, int rowCount, int colCount) {
		Plane pln = (Plane) this.plns.get(Integer.valueOf(plnNo));
		this.setAnime(plnNo, true);
		this.addGrp(file);
		BufferedImage img = (BufferedImage) this.grps.get(file);
		int w = img.getWidth();
		int h = img.getHeight();
		int ew = w / colCount;
		int eh = h / rowCount;

		for (int row = 0; row < rowCount; ++row) {
			for (int col = 0; col < colCount; ++col) {
				pln.imgs.add(img.getSubimage(col * ew, row * eh, ew, eh));
			}
		}

	}

	public void setAnime(int plnNo, boolean anime) {
		((Plane) this.plns.get(Integer.valueOf(plnNo))).animation = anime;
	}

	public void setPos(int plnNo, Coord cood) {
		this.setPos(plnNo, ((Double) cood.x).doubleValue(),
				((Double) cood.y).doubleValue());
	}

	public void setPos(int plnNo, double x, double y) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("setPos(x, y)");
		} else {
			((Plane) this.plns.get(Integer.valueOf(plnNo))).coord.x = Double
					.valueOf(x);
			((Plane) this.plns.get(Integer.valueOf(plnNo))).coord.y = Double
					.valueOf(y);
		}
	}

	public void setPos(int plnNo, double z) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("setPos(z)");
		} else {
			((Plane) this.plns.get(Integer.valueOf(plnNo))).z = z;
			this.plns.update(Integer.valueOf(plnNo));
		}
	}

	public void setMov(int plnNo, Coord cood) {
		this.setMov(plnNo, ((Double) cood.x).doubleValue(),
				((Double) cood.y).doubleValue());
	}

	public void setMov(int plnNo, double x, double y) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("setMove()");
		} else {
			Coord var10000 = ((Plane) this.plns.get(Integer.valueOf(plnNo))).coord;
			var10000.x = Double
					.valueOf(((Double) var10000.x).doubleValue() + x);
			var10000 = ((Plane) this.plns.get(Integer.valueOf(plnNo))).coord;
			var10000.y = Double
					.valueOf(((Double) var10000.y).doubleValue() + y);
		}
	}

	public void setDraw(int plnNo, Draw draw) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("setDraw()");
		} else {
			((Plane) this.plns.get(Integer.valueOf(plnNo))).draw = draw;
			((Plane) this.plns.get(Integer.valueOf(plnNo))).view = true;
		}
	}

	public void setView(int plnNo, boolean view) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("setView()");
		} else {
			((Plane) this.plns.get(Integer.valueOf(plnNo))).view = view;
		}
	}

	public void setBlendMode(int plnNo, BlendMode bm) {
		((Plane) this.plns.get(Integer.valueOf(plnNo))).mode = bm;
	}

	public void setTrailCount(int plnNo, int count) {
		((Plane) this.plns.get(Integer.valueOf(plnNo))).initTrailCount(count);
	}

	public Coord getPos(int plnNo) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("getPos()");
			throw new AssertionError();
		} else {
			return ((Plane) this.plns.get(Integer.valueOf(plnNo))).coord
					.clone();
		}
	}

	public Draw getDraw(int plnNo) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("getDraw()");
			return null;
		} else {
			return ((Plane) this.plns.get(Integer.valueOf(plnNo))).draw;
		}
	}

	public boolean getView(int plnNo) {
		if (!this.plns.containsKey(Integer.valueOf(plnNo))) {
			this.printError("getView()");
			return false;
		} else {
			return ((Plane) this.plns.get(Integer.valueOf(plnNo))).view;
		}
	}

	public boolean isAnimeClosed(int plnNo) {
		Plane pln = (Plane) this.plns.get(Integer.valueOf(plnNo));
		return (pln.animeCount >= pln.imgs.size() - 1 || !pln.animation)
				&& !pln.trail;
	}

	public Coord getImgSize(int plnNo) {
		return this.getImgSize(plnNo, 0);
	}

	public Coord getImgSize(int plnNo, int index) {
		BufferedImage img = (BufferedImage) ((Plane) this.plns.get(Integer
				.valueOf(plnNo))).imgs.get(index);
		return new Coord(Integer.valueOf(img.getWidth()), Integer.valueOf(img
				.getHeight()));
	}

	public Graphics2D createGraphics(int plnNo) {
		return this.createGraphics(plnNo, 0);
	}

	public Graphics2D createGraphics(int plnNo, int index) {
		return ((BufferedImage) ((Plane) this.plns.get(Integer.valueOf(plnNo))).imgs
				.get(index)).createGraphics();
	}

	public void resizeImg(int plnNo, int width, int height) {
		int size = ((Plane) this.plns.get(Integer.valueOf(plnNo))).imgs.size();

		for (int i = 0; i < size; ++i) {
			this.resizeImg(plnNo, i, width, height);
		}

	}

	public void resizeImg(int plnNo, int index, int width, int height) {
		Plane pln = (Plane) this.plns.get(Integer.valueOf(plnNo));
		BufferedImage img = (BufferedImage) pln.imgs.get(index);
		if (width <= 0) {
			width = img.getWidth() * height / img.getHeight();
		} else if (height <= 0) {
			height = img.getHeight() * width / img.getWidth();
		}

		BufferedImage newImg = new BufferedImage(width, height, 2);
		Graphics2D g = newImg.createGraphics();
		g.drawImage(img, 0, 0, width, height, (ImageObserver) null);
		g.dispose();
		pln.imgs.set(index, newImg);
	}

	public void flipImg(int plnNo) {
		this.flipImg(plnNo, 0);
	}

	public void flipImg(int plnNo, int index) {
		Plane pln = (Plane) this.plns.get(Integer.valueOf(plnNo));
		BufferedImage img = (BufferedImage) pln.imgs.get(index);
		BufferedImage newImg = new BufferedImage(img.getWidth(),
				img.getHeight(), 2);
		Graphics2D g = newImg.createGraphics();
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), img.getWidth(),
				0, 0, img.getHeight(), (ImageObserver) null);
		g.dispose();
		pln.imgs.set(index, newImg);
	}

	public void paint(Graphics2D g, BufferedImage bg) {
		BlendMode.setBaseImg(bg);
		Iterator var4 = this.plns.getSorted().iterator();

		while (var4.hasNext()) {
			Plane pln = (Plane) var4.next();
			if (pln != null && pln.view) {
				if (!pln.imgs.isEmpty()) {
					BufferedImage trail = pln.nextImg();
					if (trail != null) {
						pln.mode.drawImage(trail, pln.x(), pln.y(), g);
					}
				}

				if (pln.trail) {
					Iterator var6 = pln.trails.iterator();

					while (var6.hasNext()) {
						Plane.Trail trail1 = (Plane.Trail) var6.next();
						if (trail1 != null) {
							pln.mode.drawImage(trail1.img,
									((Double) trail1.c.x).intValue(),
									((Double) trail1.c.y).intValue(), g);
						}
					}
				}

				if (pln.draw != null) {
					pln.draw.drawing(g, pln);
				}
			}
		}

	}

	public static void drawShadedString(String str, int x, int y, Graphics g) {
		drawShadedString(str, x, y, Color.LIGHT_GRAY, g);
	}

	public static void drawShadedString(String str, int x, int y, Color shade,
			Graphics g) {
		Color c = g.getColor();
		g.setColor(shade);
		g.drawString(str, x + 1, y + 1);
		g.setColor(c);
		g.drawString(str, x, y);
	}

	public static void drawDesignedRect(int x, int y, int width, int height,
			Graphics g) {
		byte offX = 20;
		byte offY = 8;
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(new Color(-1714631425, true));
		g2D.fillRoundRect(x, y, width, height, 40, 20);
		g.setColor(new Color(-1146443026, true));
		g2D.drawRoundRect(x, y, width, height, 40, 20);
		g2D.drawRoundRect(x + offX, y + offY, width - offX * 2, height - offY
				* 2, 8, 8);
		g2D.setColor(new Color(1157627903, true));
		g2D.fillRoundRect(x + offX, y + offY, width - offX * 2, height - offY
				* 2, 8, 8);
	}

	private void printError(String str) {
		System.out.println("error : no Plane in " + str);
	}

	public static void setTransparent(BufferedImage img) {
		Graphics2D g2D = img.createGraphics();
		g2D.setComposite(AlphaComposite.getInstance(1, 0.0F));
		java.awt.geom.Rectangle2D.Double rect = new java.awt.geom.Rectangle2D.Double(
				0.0D, 0.0D, (double) img.getWidth(), (double) img.getHeight());
		g2D.fill(rect);
		g2D.dispose();
	}

	public static enum Preset {

		SELECT("SELECT", 0, "poi"), CANCEL("CANCEL", 1, "poire"), FAILURE(
				"FAILURE", 2, "po2"), MOVE("MOVE", 3, "mokin");

		private final String file;

		private Preset(String var1, int var2, String file) {
			this.file = file;
		}

		public String getFile() {
			return this.file;
		}
	}

	public static enum BGM {

		TITLE("TITLE", 0, "free0341"), STAGE1("STAGE1", 1, "free17"), STAGE2(
				"STAGE2", 2, "free0563"), STAGE3("STAGE3", 3, "free0320"), WIN(
				"WIN", 4, "free0227"), LOSE("LOSE", 5, "free0303");

		private final String file;

		private BGM(String var1, int var2, String file) {
			this.file = file;
		}

		public String getFile() {
			return this.file;
		}
	}


	
}
