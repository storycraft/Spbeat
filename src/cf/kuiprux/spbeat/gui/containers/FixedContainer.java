package cf.kuiprux.spbeat.gui.containers;

import cf.kuiprux.spbeat.gui.Container;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class FixedContainer extends Container {

    private float width;
    private float height;

    public FixedContainer(){
        this.width = 0;
        this.height = 0;
    }

    public FixedContainer(float x, float y, float width, float height){
        this();
        setLocation(x, y);
    }

    @Override
    protected void updateInternal(int delta) {

    }

    @Override
    protected void drawInternal(Graphics graphics) {

    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
        sendParentUpdate();
    }

    public void setHeight(float height) {
        this.height = height;
        sendParentUpdate();
    }

    public void setSize(float width, float height){
        this.width = width;
        this.height = height;

        sendParentUpdate();
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight());
    }
}
