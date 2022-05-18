/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte.td.joc;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author wida47645633
 */
public class Run {

    public static void main(String[] args) throws SlickException {
        AppGameContainer game = new AppGameContainer(new TDGame(
                "Wida's Defense v1.0"));
        //BoombatGame.loadOptions(game);
        game.setTargetFrameRate(60);
        game.setVSync(true);
        game.setDisplayMode(1024, 768, false);
        //game.setFullscreen(true);
        game.start();
    }
}
