package com.crocro.pc.simWar;

import com.crocro.simWar.app.AppLoopSimWar;

public class App extends com.crocro.wrp.dvc.pc.App {

	public static void main() {
		main(null);
	}

	public static void main(String args[]) {
		new App(args);
	}

	public App(String args[]) {
		super(args);
	}

	public void init() {
		mAL = new AppLoopSimWar() {

			public void initGD() {
				super.initGD();
			}

		};
	}

	public static boolean mDbg = false;

}
