package game;

import game.GameObject;
import game.Motionable;
import game.Sprite;
import game.UserIF;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import myutil.Queue;
import myutil.Stack;

public class GameObjectManager {

	private Sprite sp;
	private Map gos;
	private Stack uifs;
	private List mos;
	private Comparator mosComp;
	public final int height;
	public final int width;
	final int FPS = 30;
	final int KEY_REPEAT_PS = 10;

	protected GameObjectManager(Sprite sp, Queue keyQ, int width, int height) {
		this.sp = sp;
		this.width = width;
		this.height = height;
		this.init();
		UserIF.setKeyQ(keyQ);
		this.mosComp = new Comparator() {
			public int compare(Object arg0, Object arg1) {
				return ((Motionable) arg1).processPriority()
						- ((Motionable) arg0).processPriority();
			}
		};
	}

	void init() {
		this.sp.init();
		this.gos = new HashMap();
		this.uifs = new Stack();
		this.mos = new LinkedList();
	}

	public GameObject addGO(Class goClass, int x, int y, GameObject... goArg) {
		return this.addGO(GameObjectManager.class, goClass, x, y, goArg);
	}

	public GameObject addGO(Class gom, Class goClass, int x, int y,
			GameObject... goArg) {
		int plnNo = this.sp.newPln();
		GameObject outerGO = goArg != null && goArg.length != 0 ? goArg[goArg.length - 1]
				: null;
		LinkedList argClass = new LinkedList();
		LinkedList initArgs = new LinkedList();
		argClass.add(gom);
		initArgs.add(this);
		argClass.add(Sprite.class);
		initArgs.add(this.sp);
		argClass.add(Integer.TYPE);
		initArgs.add(Integer.valueOf(plnNo));
		argClass.add(Integer.TYPE);
		initArgs.add(Integer.valueOf(x));
		argClass.add(Integer.TYPE);
		initArgs.add(Integer.valueOf(y));
		if (goArg != null) {
			for (int go = 0; go < goArg.length - 1; ++go) {
				argClass.add(goArg[go].getClass());
				initArgs.add(goArg[go]);
			}
		}

		if (outerGO != null) {
			argClass.addFirst(outerGO.getClass());
			initArgs.addFirst(outerGO);
		}

		GameObject var14 = null;

		try {
			var14 = (GameObject) goClass.getConstructor(
					(Class[]) argClass.toArray(new Class[0])).newInstance(
					initArgs.toArray(new Object[0]));
		} catch (Exception ex) {
			System.err.println(goClass.getName());
			System.err.println(gom.getName());
			ex.printStackTrace();
		}

		this.gos.put(Integer.valueOf(plnNo), var14);
		return var14;
	}

	public void delGO(int plnNo) {
		this.gos.remove(Integer.valueOf(plnNo));
		this.sp.delPln(plnNo);
	}

	public boolean isPlayable() {
		return !this.uifs.isEmpty();
	}

	public void play() {
		Iterator var2 = this.mos.iterator();

		while (var2.hasNext()) {
			Motionable mo = (Motionable) var2.next();
			mo.motion();
		}

		((UserIF) this.uifs.peek()).action();
	}

	public void setUIF(UserIF uif) {
		this.uifs.push(uif);
	}

	public void removeUIF() {
		this.uifs.pop();
	}

	public void removeUIF(UserIF uif) {
		this.uifs.remove(uif);
	}

	public void addMotionable(Motionable m) {
		this.mos.add(m);
		Collections.sort(this.mos, this.mosComp);
	}

	public void removeMotionable(Motionable m) {
		this.mos.remove(m);
	}
}
