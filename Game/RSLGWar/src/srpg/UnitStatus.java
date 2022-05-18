package srpg;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import srpg.Action;
import srpg.Capability;
import srpg.Damage;
import srpg.map.obj.Enemy;
import srpg.map.ui.ai.EAIActive;
import srpg.map.ui.ai.EAIPassive;

public class UnitStatus {

	protected static Random rn;
	public Capability capa;
	public int hp;
	public final int maxHP;
	public int sp;
	public final int maxSP;
	final int basicRes = 1000;
	public int resCharge;
	public double tension = 0.5D;
	public int flee;
	public int hit;
	int virtualRC = 0;

	public UnitStatus(Capability capa) {
		this.capa = capa;
		this.maxHP = this.hp = capa.vitality * 8;
		this.maxSP = this.sp = capa.intellect * 5;
		this.flee = capa.agility * 2 + capa.luck / 2;
		this.hit = capa.dexterity * 2 + capa.luck / 2;
		if (rn == null) {
			rn = new Random();
		}

		short param = 500;
		this.resCharge = (int) (rn.nextGaussian() * (double) param / 2.5D + (double) param);
		this.resCharge = Math.abs(this.resCharge) % 1000;
	}

	public List usableActionList() {
		List acts = this.capa.actionList();
		Iterator i = acts.iterator();

		while (i.hasNext()) {
			if (!((Action) i.next()).usable(this)) {
				i.remove();
			}
		}

		return acts;
	}

	private void initRes() {
		if (this.resCharge < 0) {
			this.resCharge += 1000;
		}

	}

	private void cutoff() {
		if (this.hp > this.maxHP) {
			this.hp = this.maxHP;
		}

		if (this.sp > this.maxSP) {
			this.sp = this.maxSP;
		}

		if (this.tension > 1.0D) {
			this.tension = 1.0D;
		} else if (this.tension < 0.0D) {
			this.tension = 0.0D;
		}

	}

	public boolean isAlive() {
		return this.hp > 0;
	}

	public void timeCourse() {
		this.timeCourse(false);
	}

	public void initVirtual() {
		this.virtualRC = this.resCharge;
	}

	public boolean timeCourse(boolean virtual) {
		if (virtual) {
			if (this.virtualRC < 0) {
				this.virtualRC += 1000;
			}

			this.virtualRC -= this.capa.agility;
			return this.virtualRC < 0;
		} else {
			this.initRes();
			this.resCharge -= this.capa.agility;
			this.tension += (double) ((float) this.capa.uplift / 6000.0F);
			this.cutoff();
			return false;
		}
	}

	public boolean isActionable() {
		return this.resCharge < 0;
	}

	public boolean damaging(Damage d) {
		if (rn.nextDouble() < d.hit) {
			this.hp -= d.hp;
			this.sp -= d.sp;
			this.tensionDamage((double) d.hp / (double) this.maxHP * 2.0D
					+ (double) d.sp / (double) this.maxSP * 4.0D);
			this.cutoff();
			this.initRes();
			this.resCharge += d.res;
			return true;
		} else {
			this.tensionDamage(-((double) d.hp / (double) this.maxHP + (double) d.sp
					/ (double) this.maxSP) * 2.0D);
			return false;
		}
	}

	public void endamage(Damage d) {
		this.tensionDamage(-((double) d.hp / (double) this.maxHP + (double) d.sp
				/ (double) this.maxSP) * 1.5D);
	}

	public void tensionDamage(double d) {
		if (d > 0.0D) {
			this.tension -= d * (double) (100 - this.capa.toughness) / 100.0D;
		} else {
			this.tension -= d * (double) (100 + this.capa.uplift) / 100.0D;
		}

		this.cutoff();
	}

	public static List initParty(List party) {
		party.add(Capability.newReimu());
		party.add(Capability.newMarisa());
		party.add(Capability.newFFairy());
		party.add(Capability.newFFairy());
		party.add(Capability.newFFairy());
		return party;
	}

	public static void initEnemy(int id, Enemy e) {
		switch (id) {
		case 50:
			e.initStatus(Capability.newEFairy());
			e.setStrategy(EAIPassive.class);
			break;
		case 51:
			e.initStatus(Capability.newEFairy());
			e.setStrategy(EAIActive.class);
			break;
		case 52:
			e.initStatus(Capability.newEFairySniper());
			e.setStrategy(EAIPassive.class);
			break;
		case 53:
			e.initStatus(Capability.newEFairySniper());
			e.setStrategy(EAIActive.class);
		}

	}
}
