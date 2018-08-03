package cf.kuiprux.spbeat.game.gui.marker;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.game.PlayManager;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.HoldNote;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.game.gui.marker.hit.HitStatement;
import cf.kuiprux.spbeat.gui.element.Square;

public class HoldMarkerDrawable extends MarkerDrawable {

    private HoldNote note;
    
    private SpriteSheet holdSheet;

    private Square holdSquare;
    
    int sx, sy, ex, ey;    
    
    float sX, sY, eX, eY;
    
    public HoldMarkerDrawable(HoldNote note, PlayManager playManager){
    	super(note, playManager);
    	
    	this.note = note;

        int sx = note.getStartIndex() % ButtonPanel.COLUMN;
        int sy = note.getStartIndex() / ButtonPanel.ROW;

        int ex = note.getNoteIndex() % ButtonPanel.COLUMN;
        int ey = note.getNoteIndex() / ButtonPanel.ROW;

        this.holdSquare = new Square(0, 0);
        holdSquare.setSize(390, 390);
        holdSquare.setColor(Color.white);
        
        setLocation(sx - ex, sy - ey);
        setSize((sx - ex + 1) * ButtonPanel.BUTTON_WIDTH, (sx - ex + 1) * ButtonPanel.BUTTON_HEIGHT);
    }

    @Override
    public void onLoaded(){
        super.onLoaded();

        try {
            ResourceManager resourceManager = playManager.getGame().getResourceManager();
            this.holdSheet = new SpriteSheet(new Image(resourceManager.getStream("texture.marker.hold"), "texture.marker.hold", false), ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT);

        	ButtonPanel buttonPanel = playManager.getPlayScreen().getButtonPanel();
        	this.sX = buttonPanel.getButtonPosX(sx);
        	this.sY = buttonPanel.getButtonPosX(sy); 
        	this.eX = buttonPanel.getButtonPosX(ex); 
        	this.eY = buttonPanel.getButtonPosX(ey); 
        	
            int x = getArrowIndex(sx, sy, ex, ey);
            holdSquare.setTexture(holdSheet.getSprite(x, 0));
        } catch (SlickException e) {
            System.out.println("텍스쳐 texture.marker.hold 로딩이 실패했습니다.");
        }
    }

    @Override
    public void update(int delta) {
    	super.update(delta);
    }

    @Override
    protected void drawInternal(Graphics graphics) {
    	super.drawInternal(graphics);
    	long time = playManager.getCurrentTime();
    	System.out.println(getSliderX(time) + " " + getSliderY(time));
    	graphics.drawImage(holdSquare.getTexture(), getSliderX(time), getSliderY(time));
    }

    public float getSliderX(long time){
        return sX+(eX-sX)*note.getStartTime()/note.getExactTime();
    }

    public float getSliderY(long time){
    	return sY+(eY-sY)*note.getStartTime()/note.getExactTime();
    }

    /*@Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public Rectangle getBoundingBox() {
        return null;
    }*/

    @Override
    public HoldNote getNote() {
        return note;
    }

    @Override
    public HitStatement getHitStatement() {
        return hitStatement;
    }

    @Override
    public boolean isOnScreen(long time) {
        return getNote().isOnScreen(time);
    }
    
    private static int getArrowIndex(float sx, float sy, float ex, float ey) {
    	int index;
    	
    	float xDif = ex-sx;
    	float yDif = ey-sy;
    	int addAmount = (xDif == 0) ? 2 : 1;
    	
    	if(xDif > 0) index = 0;
    	else {
    		index = 4;
    		addAmount = -addAmount;
    	}
    	
    	if(yDif > 0) index += addAmount;
    	else if(yDif < 0) index -= addAmount;
    	
    	if(index < 0) index += 8;
    	
    	return index;
    }
}
