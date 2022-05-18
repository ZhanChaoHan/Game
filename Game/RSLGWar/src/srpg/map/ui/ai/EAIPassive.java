package srpg.map.ui.ai;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.ai.EAIActive;

public class EAIPassive extends EAIActive {

	public EAIPassive(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
	}

	protected boolean activation() {
		return !this.map.searchUnits(
				this.map.current.moveAndAttackableArea(this.smode.actionType(),
						false), this.smode.targetType()).isEmpty()
				|| this.map.current.getStatus().hp < this.map.current
						.getStatus().maxHP;
	}
}
