package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.game.play.PlayScreen;
import cf.kuiprux.spbeat.game.gui.marker.hit.HoldNoteHitStatement;
import cf.kuiprux.spbeat.game.gui.marker.hit.IHitStatement;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Triangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class HoldNoteDrawable extends Container implements INoteDrawable {

    public static final float LINE_WIDTH = 10;

    private HoldNote note;
    private IHitStatement hitStatement;

    private Triangle sliderArrow;
    private Square sliderLine;
    private Square sliderEnd;

    private PlayManager playManager;

    public HoldNoteDrawable(HoldNote note, PlayManager playManager){
        this.note = note;
        this.hitStatement = new HoldNoteHitStatement(note);
        this.playManager = playManager;

        int sx = note.getStartIndex() % ButtonPanel.COLUMN;
        int sy = note.getStartIndex() / ButtonPanel.ROW;

        int ex = note.getNoteIndex() % ButtonPanel.COLUMN;
        int ey = note.getNoteIndex() / ButtonPanel.ROW;

        setLocation(0, 0);

        this.sliderArrow = new Triangle(0, 0, ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);
        this.sliderArrow.setRotation(getSliderRotation() - 90);
        this.sliderArrow.setOrigin(AlignMode.CENTRE);
        this.sliderEnd = new Square(0, 0, ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);

        this.sliderLine = new Square( ((sx - ex) * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X) + ButtonPanel.BUTTON_WIDTH) / 2f, ((sy - ey) * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y) + ButtonPanel.BUTTON_HEIGHT) / 2f, getDigonalLength(), LINE_WIDTH);
        this.sliderLine.setRotation(getSliderRotation());
        this.sliderLine.setOrigin(AlignMode.CENTRE);
        this.sliderLine.setAnchor(AlignMode.CENTRE);

        this.sliderArrow.setColor(Color.white);
        this.sliderLine.setColor(Color.cyan);
        this.sliderEnd.setColor(Color.magenta);

        addChild(sliderLine);
        addChild(sliderEnd);
        addChild(sliderArrow);
    }

    @Override
    public float getWidth() {
        int sx = note.getStartIndex() % ButtonPanel.COLUMN;
        int ex = note.getNoteIndex() % ButtonPanel.COLUMN;

        return Math.abs(sx - ex) * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);
    }

    @Override
    public float getHeight() {
        int sy = note.getStartIndex() / ButtonPanel.ROW;
        int ey = note.getNoteIndex() / ButtonPanel.ROW;

        return Math.abs(sy - ey) * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);
    }

    @Override
    public Rectangle getBoundingBox() {
        Shape shape = new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight()).transform(getTransformData().getSlickTransform());
        return new Rectangle(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public void onLoaded(){
        super.onLoaded();
    }

    @Override
    protected void updateInternal(int delta) {
        long time = playManager.getCurrentTime();

        this.sliderArrow.setLocation(getSliderX(time), getSliderY(time));
    }

    @Override
    protected void drawInternal(Graphics graphics) {

    }

    public float getSliderX(long time){
        float timing = getNote().getExactTime() - time;
        int sx = note.getStartIndex() % ButtonPanel.COLUMN;
        int ex = note.getNoteIndex() % ButtonPanel.COLUMN;

        float startX = sx * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);
        float endX = ex * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);

        if (timing < 0)
            return 0;
        else if (timing > PlayScreen.NOTE_VISIBLE_TIME)
            return (startX - endX);

        return (startX - endX) * (timing / PlayScreen.NOTE_VISIBLE_TIME);
    }

    public float getSliderY(long time){
        float timing = getNote().getExactTime() - time;
        int sy = note.getStartIndex() / ButtonPanel.ROW;
        int ey = note.getNoteIndex() / ButtonPanel.ROW;

        float startY = sy * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);
        float endY = ey * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);

        if (timing < 0)
            return 0;
        else if (timing > PlayScreen.NOTE_VISIBLE_TIME)
            return (startY - endY);

        return (startY - endY) * (timing / PlayScreen.NOTE_VISIBLE_TIME);
    }

    public float getDigonalLength(){
        return (float) Math.hypot(getWidth(), getHeight());
    }

    public float getSliderRotation(){
        int sx = note.getStartIndex() % ButtonPanel.COLUMN;
        int sy = note.getStartIndex() / ButtonPanel.COLUMN;

        int ex = note.getNoteIndex() % ButtonPanel.ROW;
        int ey = note.getNoteIndex() / ButtonPanel.ROW;

        float startX = sx * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);
        float endX = ex * (ButtonPanel.BUTTON_WIDTH + ButtonPanel.BUTTON_GAP_X);

        float startY = sy * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);
        float endY = ey * (ButtonPanel.BUTTON_HEIGHT + ButtonPanel.BUTTON_GAP_Y);

        return (float) Math.toDegrees(Math.atan2(startY - endY, startX - endX));
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
        ((HoldNoteHitStatement) getHitStatement()).setClickStarted(time);
    }

    @Override
    public void onkeyUp(long time) {
        ((HoldNoteHitStatement) getHitStatement()).calculateState(time);
    }
}
