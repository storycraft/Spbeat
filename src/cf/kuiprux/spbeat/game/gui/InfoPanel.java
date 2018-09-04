package cf.kuiprux.spbeat.game.gui;

import cf.kuiprux.spbeat.gui.containers.FixedContainer;

public class InfoPanel extends FixedContainer {

    public static final int PANEL_X = 75;
    public static final int PANEL_Y = 50;

    public static final int PANEL_WIDTH = 450;
    public static final int PANEL_HEIGHT = 200;

    public InfoPanel() {
        initPanel();
    }

    private void initPanel() {
        setLocation(PANEL_X, PANEL_Y);
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
