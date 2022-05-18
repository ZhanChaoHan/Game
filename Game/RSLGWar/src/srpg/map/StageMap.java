package srpg.map;

import game.Draw;
import game.GameObject;
import game.GameObjectManager;
import game.Motionable;
import game.Plane;
import game.Sprite;
import game.UserIF;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import myutil.Coord;
import myutil.Queue;
import srpg.Capability;
import srpg.Field;
import srpg.FieldData;
import srpg.Player;
import srpg.SRPGGameObjectManager;
import srpg.UnitStatus;
import srpg.map.Direction;
import srpg.map.MapDrawOrder;
import srpg.map.UnitType;
import srpg.map.obj.Enemy;
import srpg.map.obj.Friend;
import srpg.map.obj.HorizonMap;
import srpg.map.obj.Obstacle;
import srpg.map.obj.Unit;
import srpg.map.ui.TurnCounter;
import srpg.map.ui.TurnInfo;
import srpg.map.ui.info.ActionInfo;
import srpg.map.ui.info.GeoInfo;
import srpg.map.ui.info.TargetInfo;
import srpg.map.ui.info.UnitInfo;
import srpg.screen.Result;

public class StageMap extends UserIF implements Motionable {

	final String designedFont = "PMingLiU";
	private final int hexSize = 48;
	public final double depthRate = 0.75D;
	public final int hexW = 48;
	public final int hexH = (int) Math.round(36.0D);
	public final int hexHeight;
	private boolean destract;
	private int[][] hexNum;
	private int[][] height;
	public Field[][] hex;
	private final int OBSTACLE;
	public static final double YZRate = 0.00392156862745098D;
	public Unit[][] unitMap;
	public Unit current;
	private Coord cursored;
	private TurnInfo ti;
	private Queue actionQ;
	private final int actionUnitCount;
	private TurnCounter tc;
	private int turnCount;
	private int turnMax;
	private List gos;
	private Coord moveTarget;
	private Player player;
	private int income;
	private Queue unitQ;
	private Queue actionableQ;

	public StageMap(GameObjectManager ggom, Sprite sp, int plnNo, int x, int y) {
		super(ggom, sp, plnNo, x, y);
		this.hexHeight = this.hexH / 4;
		this.destract = false;
		this.OBSTACLE = -10;
		this.actionQ = new Queue();
		this.actionUnitCount = 8;
		this.turnCount = 0;
		this.turnMax = 0;
		this.income = 0;
		this.unitQ = new Queue();
		this.actionableQ = new Queue();
		this.cursored = new Coord(Integer.valueOf(0), Integer.valueOf(0));
		sp.setPos(plnNo, MapDrawOrder.MAP.z());
		sp.setDraw(plnNo, new Draw() {

			BufferedImage bi = null;

			public void drawing(Graphics g, Plane pln) {
				if (this.bi == null) {
					this.bi = new BufferedImage(StageMap
							.access$0(StageMap.this).width, StageMap
							.access$0(StageMap.this).height, 1);
					Graphics2D g2 = this.bi.createGraphics();
					g2.setPaint(new GradientPaint(0.0F, 5.0F, new Color(83,
							170, 234), 0.0F, (float) (StageMap
							.access$0(StageMap.this).height - 60), new Color(
							244, 246, 231)));
					g2.fill(new Double(0.0D, 0.0D, (double) StageMap
							.access$0(StageMap.this).width, (double) StageMap
							.access$0(StageMap.this).height));
					g2.dispose();
				}

				g.drawImage(this.bi, 0, 0, (ImageObserver) null);
			}
		});
		this.gom.addMotionable(this);
		this.ti = (TurnInfo) this.createMapObject(TurnInfo.class, 0, 0);
		this.ti.setElementCount(8);
		this.tc = (TurnCounter) this.createMapObject(TurnCounter.class, 0, 0);
		this.tc.setDenom(this.turnMax);
		this.gos = new LinkedList();
		this.gos.add(this.createMapObject(UnitInfo.class, 0, 0));
		this.gos.add(this.createMapObject(GeoInfo.class, 0, 0));
		this.gos.add(this.createMapObject(ActionInfo.class, 0, 0));
		this.gos.add(this.createMapObject(TargetInfo.class, 0, 0));
	}

	public void init(String file, Player player) {
		this.player = player;
		Object friend = null;
		Object enemy = null;
		Sprite.BGM[] stageBGM = new Sprite.BGM[] { null, Sprite.BGM.STAGE1,
				Sprite.BGM.STAGE2, Sprite.BGM.STAGE3 };
		StageMap.StageLoader sd = new StageMap.StageLoader(file);
		int[][] field = sd.getStage(0);
		int[][] height = sd.getStage(1);
		int[][] units = sd.getStage(2);
		int[][] direction = sd.getStage(3);
		int bgm = sd.getStage(4)[0][0];
		this.sp.playBGM(stageBGM[bgm]);

		int dirs;
		int y;
		for (dirs = 0; dirs < field.length; ++dirs) {
			for (y = 0; y < field[dirs].length; ++y) {
				if (units[dirs][y] < 0) {
					field[dirs][y] = units[dirs][y];
				}
			}
		}

		this.height = height;
		this.hexNum = field;
		UnitType.FRIEND.setInitDir((Direction) friend);
		UnitType.ENEMY.setInitDir((Direction) enemy);
		this.hex = new Field[this.hexNum.length][this.hexNum[0].length];

		for (dirs = 0; dirs < this.hexNum.length; ++dirs) {
			for (y = 0; y < this.hexNum[dirs].length; ++y) {
				FieldData x = null;
				switch (this.hexNum[dirs][y]) {
				case -10:
					x = FieldData.obstacle;
					break;
				case 1:
					x = FieldData.heichi;
					break;
				case 2:
					x = FieldData.sougen;
					break;
				case 3:
					x = FieldData.arechi;
				}

				this.hex[dirs][y] = new Field(x, height[dirs][y], this.sp);
			}
		}

		this.unitMap = new Unit[this.hexNum.length][this.hexNum[0].length];

		for (dirs = 0; dirs < this.hexNum.length; ++dirs) {
			this.gos.add(this.createMapObject(HorizonMap.class, 0, dirs));
		}

		for (dirs = 0; dirs < this.hexNum.length; ++dirs) {
			for (y = 0; y < this.hexNum[dirs].length; ++y) {
				if (this.hexNum[dirs][y] <= -10) {
					this.gos.add(this.createMapObject(Obstacle.class, y, dirs));
				}
			}
		}

		Direction[] var16 = Direction.values();

		for (y = 0; y < units.length; ++y) {
			for (int var17 = 0; var17 < units[y].length; ++var17) {
				if (units[y][var17] > 0) {
					Unit unit;
					if (units[y][var17] < 50) {
						unit = (Unit) this.createMapObject(Friend.class, var17,
								y);
						unit.initStatus((Capability) player.party
								.get(units[y][var17] - 1));
					} else {
						unit = (Unit) this.createMapObject(Enemy.class, var17,
								y);
						UnitStatus.initEnemy(units[y][var17], (Enemy) unit);
					}

					unit.setDir(var16[direction[y][var17]]);
				}
			}
		}

		this.denominateUnit();
	}

	public void action() {
		this.unitQ.clear();
		Unit[][] var4 = this.unitMap;
		int var3 = this.unitMap.length;

		for (int var2 = 0; var2 < var3; ++var2) {
			Unit[] unit = var4[var2];
			Unit[] var8 = unit;
			int var7 = unit.length;

			for (int var6 = 0; var6 < var7; ++var6) {
				Unit unit1 = var8[var6];
				if (unit1 != null) {
					this.unitQ.enqueue(unit1);
				}
			}
		}

		Iterator var10 = this.unitQ.iterator();

		Unit var9;
		while (var10.hasNext()) {
			var9 = (Unit) var10.next();
			var9.getStatus().initVirtual();
		}

		this.actionQ.clear();
		this.actionQ.enqueue(this.actionableQ);

		while (this.actionQ.size() < 8) {
			var10 = this.unitQ.iterator();

			while (var10.hasNext()) {
				var9 = (Unit) var10.next();
				if (var9.getStatus().timeCourse(true)) {
					this.actionQ.enqueue(var9);
				}
			}
		}

		this.ti.setUnits((Unit[]) this.actionQ.toArray(new Unit[0]));

		while (this.actionableQ.isEmpty()) {
			var10 = this.unitQ.iterator();

			while (var10.hasNext()) {
				var9 = (Unit) var10.next();
				var9.timeCourse();
				if (var9.isActionable()) {
					this.actionableQ.enqueue(var9);
				}
			}

			++this.turnCount;
		}

		this.tc.setCount(this.turnCount);
		var9 = (Unit) this.actionableQ.dequeue();
		if (var9.getStatus().isAlive()) {
			var9.setAction();
		}

	}

	public void motion() {
		if (this.moveTarget != null) {
			Coord current = this.sp.getPos(this.plnNo);
			double distX = ((java.lang.Double) this.moveTarget.x).doubleValue()
					- ((java.lang.Double) current.x).doubleValue();
			double distY = ((java.lang.Double) this.moveTarget.y).doubleValue()
					- ((java.lang.Double) current.y).doubleValue();
			Coord move = new Coord(java.lang.Double.valueOf(distX * 0.3D),
					java.lang.Double.valueOf(distY * 0.3D));
			this.sp.setMov(this.plnNo, move);
			current = this.sp.getPos(this.plnNo);
			this.x = (int) Math.round(((java.lang.Double) current.x)
					.doubleValue());
			this.y = (int) Math.round(((java.lang.Double) current.y)
					.doubleValue());
			if (Math.abs(((java.lang.Double) this.moveTarget.x).doubleValue()
					- ((java.lang.Double) current.x).doubleValue()) < 1.0D
					&& Math.abs(((java.lang.Double) this.moveTarget.y)
							.doubleValue()
							- ((java.lang.Double) current.y).doubleValue()) < 1.0D) {
				this.moveTarget = null;
			}
		}

	}

	public int processPriority() {
		return 1;
	}

	public void destructor() {
		this.destract = true;
		this.ti.destructor();
		this.tc.destructor();
		Unit[][] var4 = this.unitMap;
		int var3 = this.unitMap.length;

		for (int var2 = 0; var2 < var3; ++var2) {
			Unit[] go = var4[var2];
			Unit[] var8 = go;
			int var7 = go.length;

			for (int var6 = 0; var6 < var7; ++var6) {
				Unit unit = var8[var6];
				if (unit != null) {
					unit.destructor();
				}
			}
		}

		Iterator var9 = this.gos.iterator();

		while (var9.hasNext()) {
			GameObject var10 = (GameObject) var9.next();
			var10.destructor();
		}

		super.destructor();
		this.gom.removeMotionable(this);
	}

	public void setMoney(int m) {
		this.income += m;
	}

	public boolean isDestracted() {
		return this.destract;
	}

	private boolean fullDestroyed(UnitType type) {
		Unit[][] var5 = this.unitMap;
		int var4 = this.unitMap.length;

		for (int var3 = 0; var3 < var4; ++var3) {
			Unit[] units = var5[var3];
			Unit[] var9 = units;
			int var8 = units.length;

			for (int var7 = 0; var7 < var8; ++var7) {
				Unit unit = var9[var7];
				if (unit != null && unit.type == type) {
					return false;
				}
			}
		}

		return true;
	}

	private void denominateUnit() {
		HashMap hm = new HashMap();
		Unit[][] var5 = this.unitMap;
		int var4 = this.unitMap.length;

		int var8;
		for (int units = 0; units < var4; ++units) {
			Unit[] hm2 = var5[units];
			Unit[] var9 = hm2;
			var8 = hm2.length;

			for (int unit1 = 0; unit1 < var8; ++unit1) {
				Unit unit = var9[unit1];
				if (unit != null) {
					if (!hm.containsKey(unit.getStatus().capa.name)) {
						hm.put(unit.getStatus().capa.name, Integer.valueOf(0));
					}

					hm.put(unit.getStatus().capa.name,
							Integer.valueOf(((Integer) hm.get(unit.getStatus().capa.name))
									.intValue() + 1));
				}
			}
		}

		HashMap var12 = new HashMap();
		Unit[][] var15 = this.unitMap;
		int var13 = this.unitMap.length;

		for (var4 = 0; var4 < var13; ++var4) {
			Unit[] var14 = var15[var4];
			Unit[] var10 = var14;
			int var11 = var14.length;

			for (var8 = 0; var8 < var11; ++var8) {
				Unit var16 = var10[var8];
				if (var16 != null
						&& ((Integer) hm.get(var16.getStatus().capa.name))
								.intValue() > 1) {
					if (!var12.containsKey(var16.getStatus().capa.name)) {
						var12.put(var16.getStatus().capa.name,
								Integer.valueOf(0));
					}

					var16.unitNumber = ""
							+ (char) (65 + ((Integer) var12.get(var16
									.getStatus().capa.name)).intValue());
					var12.put(var16.getStatus().capa.name,
							Integer.valueOf(((Integer) var12.get(var16
									.getStatus().capa.name)).intValue() + 1));
				}
			}
		}

	}

	public GameObject createMapObject(Class go, int x, int y) {
		return this.gom.addGO(SRPGGameObjectManager.class, go, x, y,
				new GameObject[] { this, null });
	}

	public void moveMap(double x, double y) {
		this.moveTarget = new Coord(java.lang.Double.valueOf((double) this.x
				+ x), java.lang.Double.valueOf((double) this.y + y));
	}

	public void setCursorPos(Coord pos) {
		this.setCursorPos(((Integer) pos.x).intValue(),
				((Integer) pos.y).intValue());
	}

	public void setCursorPos(int x, int y) {
		this.cursored.x = Integer.valueOf(x);
		this.cursored.y = Integer.valueOf(y);
	}

	public Coord getCursored() {
		return this.cursored;
	}

	public void setUnit(int x, int y, Unit unit) {
		if (x >= 0 && this.unitMap[0].length > x && y >= 0
				&& this.unitMap.length > y) {
			this.unitMap[y][x] = unit;

			for (int i = 0; i < this.unitMap.length; ++i) {
				for (int j = 0; j < this.unitMap[i].length; ++j) {
					if ((i != y || j != x) && this.unitMap[i][j] == unit) {
						this.unitMap[i][j] = null;
					}
				}
			}

		} else {
			System.out.println("error : out of map in setUnit()");
		}
	}

	public void destroyUnit(Unit unit) {
		this.unitMap[((Integer) unit.getPos().y).intValue()][((Integer) unit
				.getPos().x).intValue()] = null;

		while (this.actionQ.remove(unit)) {
			;
		}

		if (this.fullDestroyed(unit.type)) {
			this.destructor();
			Result r = null;
			if (unit.type == UnitType.ENEMY) {
				r = (Result) this.gom.addGO(Result.class, 0, 0,
						new GameObject[0]);
				this.player.capital += this.income;
				r.setViewIncome(this.income);
			} else if (unit.type == UnitType.FRIEND) {
				r = (Result) this.gom.addGO(Result.class, -1, 0,
						new GameObject[0]);
			}
		}

	}

	void drawHex(Graphics g, Coord coord, int width, int height) {
		this.drawHex(g, ((java.lang.Double) coord.x).intValue(),
				((java.lang.Double) coord.y).intValue(), width, height);
	}

	public void drawHex(Graphics g, int x, int y, int width, int height) {
		Polygon po = this.makeHex(width, height);
		po.translate(x, y);
		g.drawPolygon(po);
	}

	void fillHex(Graphics g, Coord coord, int width, int height) {
		this.fillHex(g, ((java.lang.Double) coord.x).intValue(),
				((java.lang.Double) coord.y).intValue(), width, height);
	}

	public void fillHex(Graphics g, int x, int y, int width, int height) {
		this.fillHex(g, x, y, width, height, 1.0F);
	}

	public void fillHex(Graphics g, int x, int y, int width, int height,
			float alpha) {
		Graphics2D g2D = (Graphics2D) g;
		Polygon po = this.makeHex(width, height);
		po.translate(x, y);
		g2D.setComposite(AlphaComposite.getInstance(3, alpha));
		g2D.fill(po);
		g2D.setPaintMode();
	}

	private Polygon makeHex(int width, int height) {
		return new Polygon(
				new int[] { width / 2, width, width, width / 2, 0, 0 },
				new int[] { 0, height / 4,
						Math.round((float) (height * 3) / 4.0F), height,
						Math.round((float) (height * 3) / 4.0F), height / 4 },
				6);
	}

	public Coord getHexCoord(Coord c) {
		return this.getHexCoord(c.x.doubleValue(), c.y.doubleValue());
	}

	public Coord getHexCoord(double x, double y) {
		return this.getHexCoord(x, y, true);
	}

	Coord getHexCoord(Coord c, boolean translate) {
		return this
				.getHexCoord(c.x.doubleValue(), c.y.doubleValue(), translate);
	}

	public Coord getHexCoord(double hexX, double hexY, boolean translate) {
		double x = hexX * 48.0D + (double) (hexY % 2.0D == 0.0D ? 0 : 24);
		double y = hexY * (double) this.hexH * 3.0D / 4.0D;
		if (translate) {
			x += (double) this.x;
			y += (double) this.y;
		}

		y -= (double) (this.height[(int) hexY][(int) hexX] * this.hexHeight);
		return new Coord(java.lang.Double.valueOf(x),
				java.lang.Double.valueOf(y));
	}

	boolean isInsideMap(Coord c) {
		return this.isInsideMap(((Integer) c.x).intValue(),
				((Integer) c.y).intValue());
	}

	boolean isInsideMap(int x, int y) {
		return x >= 0 && x < this.hexNum[0].length && y >= 0
				&& y < this.hexNum.length;
	}

	public Coord[] neighbors(Coord c) {
		return this.neighbors(((Integer) c.x).intValue(),
				((Integer) c.y).intValue());
	}

	public Coord[] neighbors(int x, int y) {
		int neighborX = y % 2 == 0 ? x - 1 : x + 1;
		Coord[] all = new Coord[] {
				new Coord(Integer.valueOf(x + 1), Integer.valueOf(y)),
				new Coord(Integer.valueOf(x - 1), Integer.valueOf(y)),
				new Coord(Integer.valueOf(x), Integer.valueOf(y + 1)),
				new Coord(Integer.valueOf(x), Integer.valueOf(y - 1)),
				new Coord(Integer.valueOf(neighborX), Integer.valueOf(y + 1)),
				new Coord(Integer.valueOf(neighborX), Integer.valueOf(y - 1)) };

		for (int i = 0; i < all.length; ++i) {
			if (!this.isInsideMap(all[i])) {
				all[i] = null;
			}
		}

		return all;
	}

	public ArrayList searchUnits(boolean[][] area, UnitType ut) {
		ArrayList sUnits = new ArrayList();
		Unit[][] var7 = this.unitMap;
		int var6 = this.unitMap.length;

		for (int var5 = 0; var5 < var6; ++var5) {
			Unit[] units = var7[var5];
			Unit[] var11 = units;
			int var10 = units.length;

			for (int var9 = 0; var9 < var10; ++var9) {
				Unit unit = var11[var9];
				if (unit != null
						&& unit.getType() == ut
						&& area[((Integer) unit.getPos().x).intValue()][((Integer) unit
								.getPos().y).intValue()]) {
					sUnits.add(unit);
				}
			}
		}

		return sUnits;
	}

	public boolean[][] newBoolArea() {
		return new boolean[this.hex[0].length][this.hex.length];
	}

	public static Coord toVectorCoord(Coord c) {
		return new Coord(Integer.valueOf(toVectorCoord(
				((Integer) c.x).intValue(), ((Integer) c.y).intValue())),
				(Integer) c.y);
	}

	static int toVectorCoord(int x, int y) {
		return x - y / 2;
	}

	// $FF: synthetic method
	static GameObjectManager access$0(StageMap var0) {
		return var0.gom;
	}

	private class StageLoader {

		private static final int stageLen = 5;
		private final int[][][] stage = new int[5][][];
		private final String location = "/res/";

		StageLoader(String file) {
			try {
				BufferedReader e = new BufferedReader(new InputStreamReader(
						this.getClass().getResourceAsStream("/res/" + file)));
				LinkedList bufY = new LinkedList();
				int i = 0;

				String line;
				for (int y = 0; (line = e.readLine()) != null; ++y) {
					if (line.indexOf(42) == 0) {
						this.stage[i] = this.list2Array(bufY);
						bufY = new LinkedList();
						y = 0;
						++i;
					} else {
						String[] nums = line.split("//", -1)[0].split("\t");
						LinkedList bufX = new LinkedList();
						String[] var13 = nums;
						int var12 = nums.length;

						for (int var11 = 0; var11 < var12; ++var11) {
							String num = var13[var11];
							if (num.length() > 0) {
								bufX.add(Integer.valueOf(Integer.parseInt(num
										.trim())));
							}
						}

						if (bufX.size() > 0) {
							bufY.add((Integer[]) bufX.toArray(new Integer[0]));
						}
					}
				}

				e.close();
				this.stage[i] = this.list2Array(bufY);
			} catch (Exception var14) {
				var14.printStackTrace();
			}

		}

		private int[][] list2Array(List l) {
			int[][] ret = new int[l.size()][((Integer[]) l.get(0)).length];
			int y = 0;

			for (Iterator var5 = l.iterator(); var5.hasNext(); ++y) {
				Integer[] is = (Integer[]) var5.next();
				int x = 0;
				Integer[] var10 = is;
				int var9 = is.length;

				for (int var8 = 0; var8 < var9; ++var8) {
					int i = var10[var8].intValue();
					ret[y][x] = i;
					++x;
				}
			}

			return ret;
		}

		int[][] getStage(int i) {
			return this.stage[i];
		}
	}
}
