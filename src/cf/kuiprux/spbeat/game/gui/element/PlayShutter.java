package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.gui.PlayScreen;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;

public class PlayShutter extends FixedContainer {

    private PlayScreen playScreen;

    public PlayShutter(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }
}