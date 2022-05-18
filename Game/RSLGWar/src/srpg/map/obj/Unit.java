package srpg.map.obj;

import game.ChangeTracker;
import game.Motionable;
import game.Sprite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.List;
import myutil.Coord;
import srpg.Action;
import srpg.ActionType;
import srpg.Capability;
import srpg.Damage;
import srpg.MapSearch;
import srpg.SRPGGameObjectManager;
import srpg.UnitStatus;
import srpg.map.Direction;
import srpg.map.MapDrawOrder;
import srpg.map.MapUserIF;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.obj.Gage;
import srpg.map.ui.SelectCursor;
import srpg.map.ui.ShowDamage;

public abstract class Unit extends MapUserIF implements Motionable {

	public String unitNumber;
	public BufferedImage face;
	protected BufferedImage[] figure = new BufferedImage[3];
	private SelectCursor cursor;
	public Class operator;
	public final UnitType type;
	protected Coord current;
	protected Coord target;
	protected Coord previous;
	protected Direction dir;
	protected Direction dirPrevious;
	protected MapSearch ms;
	protected Coord[] way;
	UnitStatus status;
	protected boolean actionable;
	protected boolean movable;
	protected boolean attackable;
	protected Action currentAction;
	protected Unit.Mode mode;
	protected BufferedImage damageImg;
	protected boolean damagedFlag;
	private Gage gage;
	boolean high;
	int motionCount;
	ChangeTracker ctDir;
	ChangeTracker ctDmg;
	int count;

	public Unit(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x, int y,
			StageMap m, UnitType type) {
		super(gom, sp, plnNo, x, y, m);
		this.mode = Unit.Mode.IDLE;
		this.damagedFlag = false;
		this.high = false;
		this.motionCount = (int) (30.0D * Math.random());
		this.ctDir = new ChangeTracker();
		this.ctDmg = new ChangeTracker(Boolean.valueOf(this.damagedFlag));
		this.count = 0;
		this.type = type;
		this.dir = type.getInitDir();
		sp.setPos(plnNo, this.map.getHexCoord((double) x, (double) y));
		sp.setPos(plnNo, MapDrawOrder.UNIT.z());
		String file = "damage";
		sp.addGrp(file);
		this.damageImg = sp.getGrp(file);
		this.initImg();
		this.map.setUnit(x, y, this);
		this.current = new Coord(Integer.valueOf(x), Integer.valueOf(y));
		this.previous = new Coord(Integer.valueOf(0), Integer.valueOf(0));
		this.gage = (Gage) this.map.createMapObject(Gage.class, x, y);
		gom.removeUIF(this);
		gom.addMotionable(this);
	}

	public void destructor() {
		this.gom.delGO(this.plnNo);
		this.gom.removeMotionable(this);
		if (this.status.hp <= 0) {
			this.map.destroyUnit(this);
		}

		if (this.cursor != null) {
			this.cursor.destructor();
		}

		this.gage.destructor();
		super.destructor();
	}

	public void motion() {
		this.sp.setPos(this.plnNo, this.map.getHexCoord(
				(double) ((Integer) this.current.x).intValue(),
				(double) ((Integer) this.current.y).intValue()));
		this.sp.setMov(this.plnNo, 0.0D, (double) (-2 - this.map.hexH * 3 / 4));
		this.sp.setPos(this.plnNo, MapDrawOrder.UNIT.z()
				+ (double) ((Integer) this.current.y).intValue()
				* 0.00392156862745098D);
		this.map.setUnit(((Integer) this.current.x).intValue(),
				((Integer) this.current.y).intValue(), this);
		if ((this.motionCount %= 30) == 0) {
			this.high = !this.high;
		}

		byte offset;
		if (this.high) {
			offset = 0;
		} else {
			offset = -1;
		}

		++this.motionCount;
		this.sp.setMov(this.plnNo, 0.0D, (double) offset);
		if (this.ctDir.isChanged(this.dir)
				|| this.ctDmg.isChanged(Boolean.valueOf(this.damagedFlag))) {
			this.drawImg();
		}

	}

	public int processPriority() {
		return 0;
	}

	public String toString() {
		return this.getName();
	}

	private void initImg() {
		Sprite var10000 = this.sp;
		int var10001 = this.plnNo;
		this.map.getClass();
		var10000.initImg(var10001, 48, this.map.hexH * 2);
	}

	private void drawImg() {
		this.sp.setTransparent(this.plnNo);
		Graphics2D g = this.sp.createGraphics(this.plnNo);
		BufferedImage img = null;
		switch (this.dir) {
		case UPRIGHT:
		case UPLEFT:
			img = this.figure[2];
			break;
		case RIGHT:
		case LEFT:
			img = this.figure[1];
			break;
		case DOWNRIGHT:
		case DOWNLEFT:
			img = this.figure[0];
			break;
		default:
			System.out.println("drawingError : illegal direction");
		}

		switch (this.dir) {
		case UPRIGHT:
		case RIGHT:
		case DOWNRIGHT:
			break;
		case DOWNLEFT:
		case LEFT:
		case UPLEFT:
			AffineTransform at = AffineTransform.getScaleInstance(-1.0D, 1.0D);
			at.translate((double) (-img.getWidth()), 0.0D);
			img = (new AffineTransformOp(at, (RenderingHints) null)).filter(
					img, (BufferedImage) null);
			break;
		default:
			System.err.println("drawingError : illegal direction");
		}

		g.drawImage(img, 0, 0, (ImageObserver) null);
		this.drawDamage(g, 0.0D, 0.0D);
		g.dispose();
		int var10000 = this.face.getWidth();
		this.map.getClass();
		if (var10000 < 48 + 5) {
			this.face = img;
		}

	}

	void drawDamage(Graphics g, double x, double y) {
		if (this.damagedFlag) {
			g.drawImage(this.damageImg, (int) x, (int) y - this.map.hexH * 1
					/ 4, (ImageObserver) null);
			this.damagedFlag = false;
		}

	}

	public void initStatus(Capability capa) {
		this.status = new UnitStatus(capa);

		for (int i = 0; i < 3; ++i) {
			String grp = capa.figure + (i + 1);
			this.sp.addGrp(grp);
			this.figure[i] = this.sp.getGrp(grp);
		}

		if (capa.face.equals("")) {
			this.face = this.figure[0];
		} else {
			this.sp.addGrp(capa.face);
			this.face = this.sp.getGrp(capa.face);
		}

	}

	public String getName() {
		return this.status == null ? super.toString() : this.status.capa.name
				+ (this.unitNumber == null ? "" : "::" + this.unitNumber);
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public Direction getDir() {
		return this.dir;
	}

	public void timeCourse() {
		this.status.timeCourse();
		this.actionable = this.status.isActionable();
	}

	public boolean isActionable() {
		return this.actionable;
	}

	public void setActionable(boolean actionable) {
		this.actionable = actionable;
		if (!actionable) {
			this.cursor.destructor();
			this.cursor = null;
		}

	}

	public boolean isMovable() {
		return this.movable;
	}

	public boolean isAttackable() {
		return this.attackable;
	}

	public MapSearch initMS(MapSearch ms, boolean practical) {
		ms.setCurrentPos(this.current);
		Unit[][] var6 = this.map.unitMap;
		int var5 = this.map.unitMap.length;

		for (int var4 = 0; var4 < var5; ++var4) {
			Unit[] units = var6[var4];
			Unit[] var10 = units;
			int var9 = units.length;

			for (int var8 = 0; var8 < var9; ++var8) {
				Unit unit = var10[var8];
				if (unit != null && (practical || unit.getType() != this.type)) {
					ms.plotObject(((Integer) unit.getPos().x).intValue(),
							((Integer) unit.getPos().y).intValue());
					boolean enemy = false;
					UnitType[] var15;
					int var14 = (var15 = unit.getType().attackable(
							ActionType.ATTACK)).length;

					int var13;
					for (var13 = 0; var13 < var14; ++var13) {
						UnitType c = var15[var13];
						enemy = enemy || c == this.type;
					}

					if (enemy) {
						Coord[] var17;
						var14 = (var17 = this.map.neighbors(
								((Integer) unit.getPos().x).intValue(),
								((Integer) unit.getPos().y).intValue())).length;

						for (var13 = 0; var13 < var14; ++var13) {
							Coord var16 = var17[var13];
							if (var16 != null) {
								ms.plotZOC(var16);
							}
						}
					}
				}
			}
		}

		return ms;
	}

	public boolean[][] getMovableArea() {
		return this.getMovableArea(false);
	}

	public boolean[][] getMovableArea(boolean practical) {
		boolean[][] availables = new boolean[this.map.hex[0].length][this.map.hex.length];
		this.ms = this.initMS(new MapSearch(this.map.hex), practical);

		for (int y = 0; y < this.map.hex.length; ++y) {
			for (int x = 0; x < this.map.hex[y].length; ++x) {
				availables[x][y] = this.ms.search(new Coord(Integer.valueOf(x),
						Integer.valueOf(y)), this.status.capa.move,
						this.status.capa.jump);
			}
		}

		return availables;
	}

	public void setCurrentAction(Action action) {
		this.currentAction = action;
	}

	public Action getCurrentAction() {
		return this.currentAction;
	}

	public boolean[][] getAttackableArea() {
		return this.getAttackableArea(this.current, false);
	}

	public boolean[][] getAttackableArea(Coord current, boolean virtual) {
		return this.getAttackableArea(current, this.currentAction, virtual);
	}

	public boolean[][] getAttackableArea(Coord current, Action act,
			boolean virtual) {
		MapSearch ms = new MapSearch(this.map.hex);
		if (!virtual) {
			this.ms = ms;
		}

		ms.setCurrentPos(current);
		Unit[][] var8 = this.map.unitMap;
		int var7 = this.map.unitMap.length;

		for (int var6 = 0; var6 < var7; ++var6) {
			Unit[] units = var8[var6];
			Unit[] var12 = units;
			int var11 = units.length;

			for (int var10 = 0; var10 < var11; ++var10) {
				Unit unit = var12[var10];
				if (unit != null) {
					ms.plotObject(((Integer) unit.getPos().x).intValue(),
							((Integer) unit.getPos().y).intValue());
				}
			}
		}

		return ms.search(act.range, (Coord) null);
	}

	public boolean[][] getAreaAttackableArea(Coord current) {
		MapSearch ms = new MapSearch(this.map.hex);
		ms.setCurrentPos(current);
		Unit[][] var6 = this.map.unitMap;
		int var5 = this.map.unitMap.length;

		for (int var4 = 0; var4 < var5; ++var4) {
			Unit[] units = var6[var4];
			Unit[] var10 = units;
			int var9 = units.length;

			for (int var8 = 0; var8 < var9; ++var8) {
				Unit unit = var10[var8];
				if (unit != null) {
					ms.plotObject(((Integer) unit.getPos().x).intValue(),
							((Integer) unit.getPos().y).intValue());
				}
			}
		}

		return ms.search(this.currentAction.area, this.current);
	}

	public boolean[][] moveAndAttackableArea() {
		return this.moveAndAttackableArea(ActionType.ATTACK, true);
	}

	public boolean[][] moveAndAttackableArea(ActionType at, boolean areaAttack) {
		boolean[][] area = this.map.newBoolArea();
		Action ca = this.currentAction;
		List actList = this.status.usableActionList();
		Iterator movable = actList.iterator();

		while (movable.hasNext()) {
			if (((Action) movable.next()).actType != at) {
				movable.remove();
			}
		}

		boolean[][] movable1 = this.getMovableArea();
		Coord m = new Coord(Integer.valueOf(0), Integer.valueOf(0));

		for (m.x = Integer.valueOf(0); ((Integer) m.x).intValue() < movable1.length; m.x = Integer
				.valueOf(((Integer) m.x).intValue() + 1)) {
			for (m.y = Integer.valueOf(0); ((Integer) m.y).intValue() < movable1[((Integer) m.x)
					.intValue()].length; m.y = Integer.valueOf(((Integer) m.y)
					.intValue() + 1)) {
				Action act;
				if (movable1[((Integer) m.x).intValue()][((Integer) m.y)
						.intValue()]) {
					for (Iterator var9 = actList.iterator(); var9.hasNext(); area = or(
							area, this.attackAndAreaAttackableArea(m, act))) {
						act = (Action) var9.next();
					}
				}
			}
		}

		this.currentAction = ca;
		return area;
	}

	public boolean[][] attackAndAreaAttackableArea(Action act) {
		return this.attackAndAreaAttackableArea(this.current, act);
	}

	public boolean[][] attackAndAreaAttackableArea(Coord current, Action act) {
		this.currentAction = act;
		boolean[][] attackable = this.getAttackableArea(current, true);
		boolean[][] areaAttack = this.map.newBoolArea();
		Coord a = new Coord(Integer.valueOf(0), Integer.valueOf(0));

		for (a.x = Integer.valueOf(0); ((Integer) a.x).intValue() < attackable.length; a.x = Integer
				.valueOf(((Integer) a.x).intValue() + 1)) {
			for (a.y = Integer.valueOf(0); ((Integer) a.y).intValue() < attackable[((Integer) a.x)
					.intValue()].length; a.y = Integer.valueOf(((Integer) a.y)
					.intValue() + 1)) {
				if (attackable[((Integer) a.x).intValue()][((Integer) a.y)
						.intValue()]) {
					areaAttack = or(areaAttack, this.getAreaAttackableArea(a));
				}
			}
		}

		return areaAttack;
	}

	private static boolean[][] or(boolean[][] b1, boolean[][] b2) {
		for (int x = 0; x < b1.length; ++x) {
			for (int y = 0; y < b1[x].length; ++y) {
				b1[x][y] = b1[x][y] || b2[x][y];
			}
		}

		return b1;
	}

	boolean isBehind(int x, int y) {
		return this.isBehind(new Coord(Integer.valueOf(x), Integer.valueOf(y)));
	}

	boolean isBehind(Coord next) {
		return this.dir.isBehind(this.current, next);
	}

	public void setAction() {
		this.mode = Unit.Mode.IDLE;
		this.gom.setUIF(this);
		this.movable = this.attackable = true;
	}

	public void setMove(Coord c) {
		this.setMove(((Integer) c.x).intValue(), ((Integer) c.y).intValue());
	}

	public void setMove(int x, int y) {
		this.movable = false;
		this.previous.assign(this.current);
		this.dirPrevious = this.dir;
		this.way = this.ms.getWay(x, y);
		this.mode = Unit.Mode.MOVE;
		this.gom.setUIF(this);
	}

	public void returnPrevious() {
		this.current.assign(this.previous);
		this.dir = this.dirPrevious;
		this.movable = true;
	}

	public void setAttack(int x, int y) {
		this.attackable = false;
		this.actionable = false;
		this.target = new Coord(Integer.valueOf(x), Integer.valueOf(y));
		this.mode = Unit.Mode.ATTACK;
		this.gom.setUIF(this);
	}

	public Damage calcDamage(Damage damage) {
		int c = this.isBehind(this.map.current.getPos()) ? 2 : 1;
		return damage
				.effect((double) c)
				.effect(this.map.hex[((Integer) this.current.y).intValue()][((Integer) this.current.x)
						.intValue()].fd.damageEffect)
				.hitEffect(c > 1 ? 1.5D : 1.0D)
				.hitEffect(
						1.0D + Math.atan((double) this.map.current.getStatus().hit
								/ (double) this.status.flee) / 3.141592653589793D / 2.0D);
	}

	void enemyDamage(Damage damage) {
		this.showDamage(this.calcDamage(damage));
	}

	void showDamage(Damage damage) {
		this.damagedFlag = this.status.damaging(damage);
		ShowDamage sd = (ShowDamage) this.gom.createMapObject(this.map,
				ShowDamage.class, ((Integer) this.current.x).intValue(),
				((Integer) this.current.y).intValue());
		sd.setDamage(damage, this.damagedFlag);
		this.damagedFlag = this.damagedFlag && damage.hp > 0;
		if (this.damagedFlag) {
			this.map.current.damageReport(damage);
		}

	}

	void damageReport(Damage damage) {
		this.status.endamage(damage);
	}

	public void damaging() {
		if (!this.status.isAlive()) {
			this.destructor();
		}

	}

	public Coord getPos() {
		return this.current;
	}

	public UnitType getType() {
		return this.type;
	}

	public UnitStatus getStatus() {
		return this.status;
	}

	public void action() {
		switch (this.mode) {
		case IDLE:
			this.gom.removeUIF();
			this.actionable = true;
			this.map.current = this;
			this.cursor = (SelectCursor) this.gom.createMapObject(this.map,
					SelectCursor.class, ((Integer) this.current.x).intValue(),
					((Integer) this.current.y).intValue());
			break;
		case MOVE:
			if (this.count == this.way.length) {
				this.count = 0;
				this.mode = Unit.Mode.IDLE;
				this.gom.removeUIF();
				return;
			}

			if (this.count >= 0) {
				this.dir = null;
				Direction[] var4;
				int var3 = (var4 = Direction.values()).length;

				for (int var2 = 0; var2 < var3; ++var2) {
					Direction d = var4[var2];
					if (d.isFront(this.current, this.way[this.count])) {
						this.dir = d;
					}
				}
			}

			this.current = this.way[this.count];
			++this.count;
			break;
		case ATTACK:
			this.mode = Unit.Mode.IDLE;
			this.gom.removeUIF();
			this.currentAction.use(this.status);
			this.gom.createMapObject(this.map, this.currentAction.effect,
					((Integer) this.target.x).intValue(),
					((Integer) this.target.y).intValue());
		}

	}

	static enum Mode {
		IDLE, MOVE, ATTACK;
	}
}
