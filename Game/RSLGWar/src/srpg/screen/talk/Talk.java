package srpg.screen.talk;

import game.Draw;
import game.GameObject;
import game.GameObjectManager;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import myutil.Coord;
import srpg.screen.Screen;
import srpg.screen.talk.BackGround;
import srpg.screen.talk.Person;
import srpg.screen.talk.Question;
import srpg.screen.talk.TalkDrawOrder;

//该类完成度并不高，如果移植用，且需要AVG模式，直接用LGame的AVGScreen类替换即可。
public class Talk extends Screen {

	private String[] text;
	private BufferedReader br;
	private String file = "talk1.txt";
	private String speaker = "";
	private Hashtable persons;
	private BackGround bg;
	Talk.Func TALKING = new Talk.Func("talk", (Talk.Func) null) {
		void proc(String[] args) {
			Talk.this.speaker = args[0];
		}
	};
	Talk.Func IN = new Talk.Func("in", (Talk.Func) null) {
		void proc(String[] args) {
			Person p;
			if (args.length > 2) {
				p = (Person) Talk.access$1(Talk.this).addGO(Person.class,
						Integer.parseInt(args[1]), Integer.parseInt(args[2]),
						new GameObject[0]);
			} else {
				p = (Person) Talk.access$1(Talk.this).addGO(Person.class, 0, 0,
						new GameObject[0]);
			}
			p.setChara(Person.Chara.getByName(args[0]));
			Talk.this.persons.put(args[0], p);
			if (args.length == 2) {
				p.setPos(Person.Position.getByName(args[1]));
			}

		}
	};
	Talk.Func OUT = new Talk.Func("out", (Talk.Func) null) {
		void proc(String[] args) {
			Person p = (Person) Talk.this.persons.remove(args[0]);
			if (p == null) {
				System.out.println("out falure. no such person : " + args[0]);
			} else {
				p.destructor();
			}

		}
	};
	Talk.Func FLIP = new Talk.Func("flip", (Talk.Func) null) {
		void proc(String[] args) {
			((Person) Talk.this.persons.get(args[0])).flip();
		}
	};
	Talk.Func POS = new Talk.Func("pos", (Talk.Func) null) {
		void proc(String[] args) {
			Person p = (Person) Talk.this.persons.get(args[0]);
			if (args.length > 2) {
				p.setPos(new Coord(Double.valueOf((double) Integer
						.parseInt(args[1])), Double.valueOf((double) Integer
						.parseInt(args[2]))));
			} else {
				p.setPos(Person.Position.getByName(args[1]));
			}

		}
	};
	Talk.Func QUESTION = new Talk.Func("question", (Talk.Func) null) {
		void proc(String[] args) {
			Talk.this.q = (Question) Talk.access$1(Talk.this).addGO(
					Question.class, 0, 0, new GameObject[0]);
			boolean[] bs = new boolean[args.length];
			Arrays.fill(bs, true);
			Talk.this.q.init(args, bs);
		}
	};
	Talk.Func ANS = new Talk.Func("ans", (Talk.Func) null) {
		void proc(String[] args) {
			if (Integer.parseInt(args[0]) != Talk.this.q.getResult()) {
				Talk.this.skip = true;
			}

		}
	};
	Talk.Func BG = new Talk.Func("bg", (Talk.Func) null) {
		void proc(String[] args) {
			Talk.this.bg.setImg(args[0]);
		}
	};
	Talk.Func[] funcs;
	boolean end;
	boolean skip;
	Question q;

	public Talk(GameObjectManager gomm, Sprite sp, int plnNo, int x, int y) {
		super(gomm, sp, plnNo, x, y);
		System.out.println("create  Talk");
		try {
			this.funcs = new Talk.Func[] { this.TALKING, this.IN, this.OUT,
					this.FLIP, this.POS, this.QUESTION, this.ANS, this.BG };
			this.end = false;
			this.skip = false;
			this.q = null;
			sp.setPos(plnNo, TalkDrawOrder.TALK.z());
			sp.setPos(plnNo, 0.0D, 0.0D);
			sp.setDraw(plnNo, new Draw() {
				public void drawing(Graphics g, Plane pln) {
					Sprite.drawDesignedRect(0,
							Talk.access$1(Talk.this).height * 3 / 4,
							Talk.access$1(Talk.this).width,
							Talk.access$1(Talk.this).height / 4 + 10, g);
					g.setColor(Color.BLACK);
					Sprite.drawShadedString(Talk.this.speaker, 60,
							Talk.access$1(Talk.this).height * 3 / 4 + 3, g);
					for (int i = 0; i < Talk.this.text.length; ++i) {
						Sprite.drawShadedString(Talk.this.text[i], 50,
								Talk.access$1(Talk.this).height * 3 / 4 + 35
										+ i * 30, g);
					}

				}
			});
			this.br = new BufferedReader(new InputStreamReader(Thread
					.currentThread().getContextClassLoader()
					.getResourceAsStream("res/" + this.file)));
			this.persons = new Hashtable();
			this.bg = (BackGround) this.gom.addGO(BackGround.class, 0, 0,
					new GameObject[0]);
			this.text = this.nextText();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void destructor() {
		Iterator var2 = this.persons.values().iterator();

		while (var2.hasNext()) {
			Person p = (Person) var2.next();
			p.destructor();
		}

		this.bg.destructor();
		super.destructor();
	}

	protected void mainAction() {
		if (this.selected) {
			this.text = this.nextText();
			if (this.text.length < 1) {
				this.destructor();
			}
		}

	}

	private String[] nextText() {
		Object texts = new LinkedList();
		if (this.end) {
			return (String[]) ((List) texts).toArray(new String[0]);
		} else {
			String e;
			try {
				label63: while (((List) texts).size() < 3
						&& (e = this.br.readLine()) != null) {
					String[] tokens = e.split("//", -1)[0].split(" ", -1);

					for (int funcStr = 0; funcStr < tokens.length; ++funcStr) {
						tokens[funcStr] = tokens[funcStr].trim();
					}

					String var11 = tokens[0];
					String[] args = (String[]) Arrays.copyOfRange(tokens, 1,
							tokens.length);
					if (var11.equals("\\endans")) {
						this.skip = false;
					} else if (!this.skip) {
						if (var11.equals("\\endtalk")) {
							this.end = true;
							break;
						}

						if (var11.equals("\\next")) {
							this.br.mark(64);
							int var12 = this.br.read();
							this.br.reset();
							if (var12 > -1 && ((List) texts).size() < 1) {
								texts = Arrays.asList(this.nextText());
							}
							break;
						}

						Talk.Func[] var9 = this.funcs;
						int var8 = this.funcs.length;

						for (int var7 = 0; var7 < var8; ++var7) {
							Talk.Func func = var9[var7];
							if (var11.equals("\\" + func.word)) {
								func.proc(args);
								continue label63;
							}
						}

						if (!tokens[0].equals("")) {
							((List) texts).add(tokens[0]);
						}
					}
				}
			} catch (IOException var10) {
				var10.printStackTrace();
			}

			return (String[]) ((List) texts).toArray(new String[0]);
		}
	}

	static GameObjectManager access$1(Talk var0) {
		return var0.gom;
	}

	private abstract class Func {

		String word;

		private Func(String word) {
			this.word = word;
		}

		abstract void proc(String[] var1);

		Func(String var2, Talk.Func var3) {
			this(var2);
		}
	}
}
