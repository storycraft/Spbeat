package cf.kuiprux.spbeat.game.gui.marker;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.Note;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.game.gui.PlayScreen;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.DrawMode;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.*;

public class MarkerDrawable extends FixedContainer implements IMarkerDrawable {

    private HitStatement hitStatement;
    private Note note;

    private Square markerSquare;
    private Square effectSquare;

    private SpriteSheet markerSheet;
    private SpriteSheet effectSheet;

    private PlayManager playManager;

    public int NOTE_SPRITE_ROW = 5;
    public int NOTE_SPRITE_COLUM = 5;

    public MarkerDrawable(Note note, PlayManager playManager){
        super(0, 0, 100, 100);
        this.note = note;
        this.hitStatement = new HitStatement(note);
        this.playManager = playManager;

        this.markerSquare = new Square(0, 0);
        this.effectSquare = new Square(0, 0);

        markerSquare.setSize(100, 100);
        effectSquare.setSize(100, 100);

        markerSquare.setColor(Color.white);
        effectSquare.setColor(Color.white);

        effectSquare.setDrawMode(DrawMode.ADD);

        addChild(markerSquare);
        addChild(effectSquare);
    }

    public Note getNote() {
        return note;
    }

    @Override
    public void onLoaded(){
        super.onLoaded();

        try {
            ResourceManager resourceManager = playManager.getGame().getResourceManager();
            this.markerSheet = new SpriteSheet(new Image(resourceManager.getStream("texture.marker.default"), "texture.marker.default", false), ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);
            this.effectSheet = new SpriteSheet(new Image(resourceManager.getStream("texture.marker.effect.default"), "texture.marker.effect.default", false), ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);
        } catch (SlickException e) {
            System.out.println("텍스쳐 texture.marker.default 로딩이 실패했습니다.");
        }
    }

    @Override
    public HitStatement getHitStatement() {
        return hitStatement;
    }

    @Override
    public boolean isOnScreen(long time) {
        return getNote().isOnScreen(time);
    }

    @Override
    public void click(long time) {

    }

    public int getTextureIndex(long time){
        int totaltime = PlayScreen.AFTER_VISIBLE_TIME + PlayScreen.NOTE_VISIBLE_TIME;
        float startTime = getNote().getExactTime() - PlayScreen.NOTE_VISIBLE_TIME;
        float timing = (getNote().getExactTime() + PlayScreen.AFTER_VISIBLE_TIME) - time;

        int maxIndex = NOTE_SPRITE_ROW * NOTE_SPRITE_COLUM - 1;

        if (timing < -PlayScreen.AFTER_VISIBLE_TIME)
            return maxIndex;

        return (int) Math.min(Math.floor((Math.max(time - startTime, 0) / totaltime) * maxIndex), maxIndex);
    }

    @Override
    public void update(int delta){
        super.update(delta);

        int textureIndex = getTextureIndex(playManager.getCurrentTime());

        int textureX = textureIndex % NOTE_SPRITE_COLUM;
        int textureY = Math.floorDiv(textureIndex, NOTE_SPRITE_ROW);

        markerSquare.setTexture(markerSheet.getSprite(textureX, textureY));
        effectSquare.setTexture(effectSheet.getSprite(textureX, textureY));
    }
}
