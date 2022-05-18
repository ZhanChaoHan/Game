package srpg.screen;

import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import java.util.Arrays;
import java.util.List;
import srpg.Capability;
import srpg.UnitStatus;
import srpg.screen.Information;
import srpg.screen.MenuInformation;
import srpg.screen.ScreenMenu;

public class Status extends ScreenMenu {

	private List party;
	private int cursored;
	private final Information info;

	public Status(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		this.info = (Information) gom.addGO(SInfo.class, 0, 0,
				new GameObject[] { this });
	}

	void initParty(List party) {
		this.party = party;
		this.menuStr = this.arr2String(party.toArray());
		this.activeMenu = new boolean[party.size()];
		Arrays.fill(this.activeMenu, true);
		this.menuCursor = Status.SCursor.class;
		this.init();
	}

	public void destructor() {
		this.info.destructor();
		super.destructor();
	}

	protected class SInfo extends MenuInformation {

		private int realHeight;

		public SInfo(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
			super(gom, sp, plnNo, x, y);
			this.init(gom.width * 2 / 5, this.height = 100);
			this.realHeight = 100;
			this.title = "";
			sp.setPos(plnNo, (double) (gom.width - this.width), 60.0D);
		}

		protected Information.StrData[] initSds() {
			return new Information.StrData[] {
					new Information.StrData("", this.width / 8,
							this.realHeight / 4 + 5),
					new Information.StrData("HP : ", this.width / 8,
							this.realHeight * 2 / 4 + 5),
					new Information.StrData("SP : ", this.width / 8,
							this.realHeight * 3 / 4 + 5),
					new Information.StrData("Str : ", this.width * 8 / 16 - 6,
							this.realHeight * 2 / 4 + 5),
					new Information.StrData("Agi : ", this.width * 8 / 16 - 6,
							this.realHeight * 3 / 4 + 5),
					new Information.StrData("Hit : ", this.width * 11 / 16,
							this.realHeight * 2 / 4 + 5),
					new Information.StrData("Flee : ", this.width * 11 / 16,
							this.realHeight * 3 / 4 + 5) };
		}

		protected void setData(Capability cap) {
			UnitStatus st = new UnitStatus(cap);
			this.sds[0].str = this.sds[0].str + cap.name;
			this.sds[1].str = this.sds[1].str + st.hp + " / " + st.maxHP;
			this.sds[2].str = this.sds[2].str + st.sp + " / " + st.maxSP;
			this.sds[3].str = this.sds[3].str + st.capa.strength;
			this.sds[4].str = this.sds[4].str + st.capa.agility;
			this.sds[5].str = this.sds[5].str + st.hit;
			this.sds[6].str = this.sds[6].str + st.flee;
		}

		public void mainMotion() {
			this.sp.setView(this.plnNo, true);
			this.sds = this.initSds();
			this.setData((Capability) Status.this.party
					.get(Status.this.cursored));
		}

		protected void update() {
			this.current.y = Integer.valueOf(Status.this.cursored);
		}
	}

	protected class SCursor extends ScreenMenu.SMCursor {

		public SCursor(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
			super(gom, sp, plnNo, x, y);
		}

		protected void MCAction() {
			this.setView(true);
			Status.this.cursored = this.y;
			if (this.canceled) {
				this.destructor();
			}

		}
	}
}
