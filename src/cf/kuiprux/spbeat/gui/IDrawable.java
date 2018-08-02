package cf.kuiprux.spbeat.gui;

import cf.kuiprux.spbeat.gui.effect.IDrawEffect;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public interface IDrawable {
    float getX();

    float getY();

    float getDrawX();


    float getDrawY();

    float getDrawOriginX();

    float getDrawOriginY();

    float getDrawAnchorX();

    float getDrawAnchorY();

    float getOpacity();

    AlignMode getAnchor();

    AlignMode getOrigin();

    float getRotation();

    float getScaleX();

    float getScaleY();

    float getDrawScaleX();

    float getDrawScaleY();

    void setX(float x);

    void setY(float y);

    void setLocation(float x, float y);

    void setOpacity(float opacity);

    void setOrigin(AlignMode mode);

    void setAnchor(AlignMode mode);

    void setRotation(float angle);

    void setScaleX(float scaleX);

    void setScaleY(float scaleY);

    void setScale(float scaleX, float scaleY);

    boolean isVisible();

    void setVisible(boolean flag);

    int getDrawMode();

    void setDrawMode(int mode);

    float getWidth();
    float getHeight();

    float getDrawWidth();

    float getDrawHeight();

    Rectangle getBoundingBox();

    Container getParent();

    boolean isLoaded();

    void onAdded(Container container);

    void onLoaded();

    void onUnloaded();

    void onRemoved();

    void expire();

    void update(int delta);

    void draw(Graphics graphics);
}
