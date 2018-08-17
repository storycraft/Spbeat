package cf.kuiprux.spbeat.gui.container;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class FixedContainer extends Container {

    private float width;
    private float height;

    public FixedContainer(){
        this.width = 0;
        this.height = 0;
    }

    public FixedContainer(float x, float y, float width, float height){
        setLocation(x, y);
        setSize(width, height);
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
        Shape shape = new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight()).transform(getTransformData().getSlickTransform());
        return new Rectangle(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
    }
}
