package cf.kuiprux.spbeat.gui;

import cf.kuiprux.spbeat.gui.container.Container;
import cf.kuiprux.spbeat.gui.container.IContainerDrawable;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public interface IDrawable extends Drawable {

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

    DrawMode getDrawMode();

    void setDrawMode(DrawMode mode);

    float getWidth();
    float getHeight();

    float getDrawWidth();

    float getDrawHeight();

    Rectangle getBoundingBox();

    Container getParent();

    boolean isLoaded();

    void onAdded(IContainerDrawable container);

    void onLoaded();

    void onUnloaded();

    void onRemoved();

    void expire();

    void update(int delta);
}
