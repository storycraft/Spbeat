package cf.kuiprux.spbeat.gui;

import org.newdawn.slick.Graphics;

public enum DrawMode {
    NORMAL(Graphics.MODE_NORMAL),
    ADD(Graphics.MODE_ADD),
    MULTIPLY(Graphics.MODE_COLOR_MULTIPLY),
    ALPHA_BLEND(Graphics.MODE_ALPHA_BLEND),
    SCREEN(Graphics.MODE_SCREEN),
    ALPHA_MAP(Graphics.MODE_ALPHA_MAP);

    private int mode;

    DrawMode(int mode) {
        this.mode = mode;
    }

    public int getIntMode(){
        return mode;
    }
}
