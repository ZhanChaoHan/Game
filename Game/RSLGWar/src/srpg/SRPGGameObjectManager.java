package srpg;

import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import java.util.HashMap;
import myutil.Queue;
import srpg.map.StageMap;

public class SRPGGameObjectManager extends GameObjectManager {

	private HashMap maps = new HashMap();

	public SRPGGameObjectManager(Sprite sp, Queue keyQ, int width, int height) {
		super(sp, keyQ, width, height);
	}

	public void createMap(int id) {
		this.maps.put(Integer.valueOf(id),
				(StageMap) this.addGO(StageMap.class, 0, 0, new GameObject[0]));
	}

	public GameObject createMapObject(int id, Class go, int x, int y) {
		return this.createMapObject(
				(StageMap) this.maps.get(Integer.valueOf(id)), go, x, y);
	}

	public GameObject createMapObject(StageMap map, Class go, int x, int y) {
		return this.createMapObject(map, go, x, y, (GameObject) null);
	}

	public GameObject createMapObject(StageMap map, Class go, int x, int y,
			GameObject outerGO) {
		return this.addGO(SRPGGameObjectManager.class, go, x, y,
				new GameObject[] { map, outerGO });
	}
}
