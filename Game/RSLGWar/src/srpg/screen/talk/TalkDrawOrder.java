package srpg.screen.talk;

import game.DrawOrder;
import game.ZOrder;

enum TalkDrawOrder implements ZOrder {

	BG("BG", 0), PERSON("PERSON", 1), TALK("TALK", 2), QUESTION("QUESTION", 3), Q_CURSOR(
			"Q_CURSOR", 4);

	private TalkDrawOrder(String var1, int var2) {
	}

	public double z() {
		return DrawOrder.TALK.z() + (double) this.ordinal() / 255.0D;
	}
}
