package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.play.PlayScreen;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Text;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PlayShutter extends FixedContainer {

    public static final float SHUTTER_VIB = 1f;
    public static final float COMBO_SCALE = 1.125f;

    private PlayScreen playScreen;

    private Square shutterTop;
    private Square shutterBottom;
    private Text comboText;

    private int lastCombo;
    private int maxCombo;

    public PlayShutter(PlayScreen playScreen) {
        this.playScreen = playScreen;

        this.shutterTop = new Square(0, 0);
        this.shutterBottom = new Square(0, 0);

        this.comboText = new Text();

        comboText.setOpacity(0.8f);
        comboText.setAnchor(AlignMode.CENTRE);
        comboText.setOrigin(AlignMode.CENTRE);
        comboText.setColor(Color.lightGray);

        shutterTop.setColor(Color.white);
        shutterBottom.setColor(Color.white);

        shutterTop.setOrigin(AlignMode.CENTRE);
        shutterBottom.setOrigin(AlignMode.CENTRE);

        addChild(shutterBottom);
        addChild(shutterTop);

        addChild(comboText);
    }

    @Override
    public void onLoaded(){
        super.onLoaded();

        SpBeAt game = getPlayScreen().getGame();

        try {
            shutterTop.setTexture(new Image(game.getResourceManager().getStream("texture.shutter.top"), "texture.shutter.top" , false));
            shutterBottom.setTexture(new Image(game.getResourceManager().getStream("texture.shutter.bottom"), "texture.shutter.bottom" , false));
        } catch (SlickException e) {
            System.out.println("택스쳐 texture.shutter 로드가 실패 했습니다");
        }

        this.maxCombo = getPlayScreen().getBeatmap().getNoteCount();

        //픽셀 오차값 1 더함
        shutterTop.setLocation(0, -SHUTTER_VIB - 1);
        shutterTop.setSize(getWidth(), getHeight() / 2 + SHUTTER_VIB * 2 + 2);
        shutterBottom.setLocation(0, getHeight() / 2 - SHUTTER_VIB - 1);
        shutterBottom.setSize(getWidth(), getHeight() / 2 + SHUTTER_VIB * 2 + 2);

        comboText.setLocation(getWidth() / 2, getHeight() / 2);
        comboText.setText("");
        comboText.setFont(game.getFontManager().getFontByName("나눔바른고딕"));
        comboText.setFontSize(128f);
    }

    @Override
    protected void updateInternal(int delta){
        super.updateInternal(delta);

        updateComboText();
        updateShutter();
    }

    protected void updateShutter() {
        float shutterPercent = (float) getPlayScreen().getPlayStatus().getShutterPercent();
        float beatInterval = (60 / getPlayScreen().getBeatmap().getBeatTime()) * 666;
        float shutterBeat = (float) Math.sin((((getPlayScreen().getPlayManager().getCurrentTime()) % beatInterval) / beatInterval) * Math.PI * 2) * SHUTTER_VIB;

        shutterTop.moveToY(-shutterTop.getHeight() * shutterPercent + shutterBeat - SHUTTER_VIB, EasingType.LINEAR, 10);
        shutterBottom.moveToY(shutterBottom.getHeight() * (1 + shutterPercent) - shutterBeat, EasingType.LINEAR, 10);
    }

    protected void updateComboText(){
        int combo = getPlayScreen().getPlayStatus().getCurrentCombo();

        if (combo <= 0){
            comboText.setText("");
        }
        else if (combo != lastCombo)
            comboText.setText(combo + "");

        if (combo > lastCombo){
            comboText.scaleTo(COMBO_SCALE, COMBO_SCALE, EasingType.EASE_OUT_QUAD, 100).scaleTo(1f, 1f, EasingType.EASE_IN_QUAD, 75);
        }

        lastCombo = combo;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }
}