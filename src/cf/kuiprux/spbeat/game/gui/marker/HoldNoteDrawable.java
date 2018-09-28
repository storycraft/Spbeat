package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.game.play.PlayScreen;
import cf.kuiprux.spbeat.game.gui.marker.hit.HoldNoteHitStatement;
import cf.kuiprux.spbeat.game.gui.marker.hit.IHitStatement;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.DrawMode;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Triangle;
import javafx.scene.control.Button;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class HoldNoteDrawable extends Container implements INoteDrawable {

    private HoldNote note;
    private IHitStatement hitStatement;

    private Square sliderArrow;
    private Square sliderLine;
    private Square sliderEnd;

    private PlayManager playManager;

    public HoldNoteDrawable(HoldNote note, PlayManager playManager){
        this.note = note;
        this.hitStatement = new HoldNoteHitStatement(note);
        this.playManager = playManager;

        setLocation(0, 0);

        this.sliderArrow = new Square(getSliderX(0), getSliderY(0), ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);
        this.sliderArrow.setRotation(getSliderRotation() - 90);
        this.sliderArrow.setOrigin(AlignMode.CENTRE);

        this.sliderEnd = new Square(ButtonPanel.BUTTON_WIDTH / 2, ButtonPanel.BUTTON_HEIGHT / 2, ButtonPanel.BUTTON_WIDTH - 1, ButtonPanel.BUTTON_HEIGHT - 1);
        this.sliderEnd.setAnchor(AlignMode.CENTRE);
        this.sliderEnd.setOrigin(AlignMode.CENTRE);
        this.sliderEnd.setRotation(getSliderRotation() - 90);
        this.sliderEnd.setDrawMode(DrawMode.ADD);

        this.sliderLine = new Square(getLineCenterX(0), getLineCenterY(0), getSliderWidth(), ButtonPanel.BUTTON_HEIGHT);
        this.sliderLine.setRotation(getSliderRotation());
        this.sliderLine.setAnchor(AlignMode.CENTRE);
        this.sliderLine.setOrigin(AlignMode.CENTRE);

        this.sliderArrow.setColor(Color.white);
        this.sliderLine.setColor(Color.white);
        this.sliderEnd.setColor(Color.white);

        addChild(sliderLine);
        addChild(sliderEnd);
        addChild(sliderArrow);
    }

    @Override
    public float getWidth() {
        return getEndX() - getStartX();
    }

    @Override
    public float getHeight() {
        return getEndY() - getStartY();
    }

    @Override
    public Rectangle getBoundingBox() {
        Shape shape = new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight()).transform(getTransformData().getSlickTransform());
        return new Rectangle(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public void onLoaded(){
        super.onLoaded();

        try {
            ResourceManager resourceManager = playManager.getGame().getResourceManager();

            this.sliderArrow.setTexture(new Image(resourceManager.getStream("texture.marker.hold.arrow"), "texture.marker.hold.arrow", false));
            this.sliderLine.setTexture(new Image(resourceManager.getStream("texture.marker.hold.line"), "texture.marker.hold.line", false));
            this.sliderEnd.setTexture(new Image(resourceManager.getStream("texture.marker.hold.end"), "texture.marker.hold.end", false));
        } catch (SlickException e) {
            System.out.println("텍스쳐 texture.marker 로딩이 실패했습니다.");
        }
    }

    @Override
    protected void updateInternal(int delta) {
        long time = playManager.getCurrentTime();

        this.sliderArrow.setLocation(getSliderX(time), getSliderY(time));
        this.sliderLine.setLocation(getLineCenterX(time), getLineCenterY(time));
        this.sliderLine.setWidth(getSliderLineWidth(time));
    }

    @Override
    protected void drawInternal(Graphics graphics) {

    }

    public float getSliderX(long time){
        float timing = getNote().getExactTime() - time;
        float length = getNote().getLength();

        if (timing < 0)
            return 0;
        else if (timing > length)
            return getStartX() - getEndX();

        return -getWidth() * (timing / length);
    }

    public float getSliderY(long time){
        float timing = getNote().getExactTime() - time;
        float length = getNote().getLength();

        if (timing < 0)
            return 0;
        else if (timing > length)
            return getStartY() - getEndY();

        return -getHeight() * (timing / length);
    }

    public int getStartTileX() {
        return note.getStartIndex() % ButtonPanel.COLUMN;
    }

    public int getStartTileY() {
        return note.getStartIndex() / ButtonPanel.ROW;
    }

    public int getEndTileX() {
        return note.getNoteIndex() % ButtonPanel.COLUMN;
    }

    public int getEndTileY() {
        return note.getNoteIndex() / ButtonPanel.ROW;
    }

    public float getEndX() {
        return getEndTileX() * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);
    }

    public float getEndY() {
        return getEndTileY() * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);
    }

    public float getStartX() {
        return getStartTileX() * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);
    }

    public float getStartY() {
        return getStartTileY() * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);
    }

    public float getLineCenterX(long time) {
        float lineX = getLineX(time);
        return lineX + (-lineX + ButtonPanel.BUTTON_WIDTH) / 2;
    }

    public float getLineCenterY(long time) {
        float lineY = getLineY(time);
        return lineY + (-lineY + ButtonPanel.BUTTON_HEIGHT) / 2;
    }

    public float getLineX(long time) {
        return getSliderX(time);
    }

    public float getLineY(long time) {
        return getSliderY(time);
    }

    protected float getDigonalLength(float width, float height){
        return (float) Math.hypot(width, height);
    }

    protected float getSliderWidth(){
        return getDigonalLength(getWidth(), getHeight());
    }

    protected float getSliderLineWidth(long time) {
        if (time >= getNote().getExactTime())
            return 0;
        else if (time <= getNote().getStartTime()) {
            return getSliderWidth();
        }

        return getDigonalLength(-getLineX(time), -getLineY(time));
    }

    public float getSliderRotation(){
        return (float) Math.toDegrees(Math.atan2(-getHeight(), -getWidth()));
    }

    @Override
    public HoldNote getNote() {
        return note;
    }

    @Override
    public IHitStatement getHitStatement() {
        return hitStatement;
    }

    @Override
    public boolean isOnScreen(long time) {
        return getNote().isOnScreen(time);
    }

    @Override
    public void onkeyDown(long time) {
        HoldNoteHitStatement hitStatement = (HoldNoteHitStatement) getHitStatement();
        if (!hitStatement.isClicked()) {
            hitStatement.setClicked(true);
            hitStatement.setClickStarted(time);
            playManager.getPlayScreen().getPlayStatus().setCurrentCombo(playManager.getPlayScreen().getPlayStatus().getCurrentCombo() + 1);
        }
    }

    @Override
    public void onkeyUp(long time) {
        HoldNoteHitStatement hitStatement = (HoldNoteHitStatement) getHitStatement();
        if (hitStatement.isClicked())
            getHitStatement().calculateState(time);
    }
}
