package cf.kuiprux.spbeat.gui.container;

import cf.kuiprux.spbeat.SimpleGame;
import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.IDrawable;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDrawableContainer<T extends IDrawable> extends Drawable implements IContainerDrawable<T> {

    private SimpleGame game;

    private boolean masking;
    private boolean rounded;

    private List<T> children;

    public AbstractDrawableContainer() {
        this.children = new ArrayList<>();

        this.masking = false;
        this.rounded = true;
    }

    @Override
    public SimpleGame getGame() {
        return game;
    }

    @Override
    public void init(SimpleGame game) {
        this.game = game;
    }

    @Override
    public boolean isMasking() {
        return masking;
    }

    @Override
    public void setMasking(boolean flag) {
        this.masking = flag;
    }

    @Override
    public boolean isRounded() {
        return rounded;
    }

    @Override
    public void setRound(boolean flag) {
        this.rounded = flag;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {

    }

    @Override
    public List<T> getChildren() {
        return new ArrayList<>(getChildrenInternal());
    }

    protected List<T> getChildrenInternal() {
        return children;
    }

    @Override
    public void update(int delta) {

    }

    @Override
    public boolean addChild(T drawable) {
        if (containsChild(drawable))
            return false;

        addInternal(drawable);
        return true;
    }

    @Override
    public boolean removeChild(T drawable) {
        if (!containsChild(drawable))
            return false;

        removeInternal(drawable);
        return true;
    }

    protected void addInternal(T drawable) {
        getChildrenInternal().add(drawable);
        drawable.onAdded(this);

        if (drawable instanceof Container) {
            ((IContainerDrawable) drawable).init(getGame());
        }

        if (isLoaded())
            drawable.onLoaded();
    }

    protected void removeInternal(T drawable) {
        getChildrenInternal().remove(drawable);
        drawable.onRemoved();

        if (isLoaded())
            drawable.onUnloaded();
    }

    @Override
    public boolean containsChild(T drawable) {
        return getChildrenInternal().contains(drawable);
    }

}
