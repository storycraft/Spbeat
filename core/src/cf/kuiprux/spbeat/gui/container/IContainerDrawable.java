package cf.kuiprux.spbeat.gui.container;

import cf.kuiprux.spbeat.SimpleGame;
import cf.kuiprux.spbeat.gui.IDrawable;
import com.badlogic.gdx.graphics.g2d.Batch;

public interface IContainerDrawable<T extends IDrawable> extends IContainer<T> {

    SimpleGame getGame();

    boolean isMasking();
    void setMasking(boolean flag);

    //위치값 소수점을 버릴지 결정
    boolean isRounded();
    void setRound(boolean flag);

    void draw(Batch batch, float x, float y, float width, float height);

    void init(SimpleGame game);
}
