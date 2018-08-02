package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.game.gui.PlayScreen;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.EasingType;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Text;
import org.newdawn.slick.Color;

public class PlayShutter extends FixedContainer {

    private PlayScreen playScreen;

    private Square shutterTop;
    private Square shutterBottom;
    private Text comboText;

    private int lastCombo;

    public PlayShutter(PlayScreen playScreen) {
        this.playScreen = playScreen;

        this.shutterTop = new Square(0, 0);
        this.shutterBottom = new Square(0, 0);

        this.comboText = new Text();

        comboText.setOpacity(0.8f);
        comboText.setAnchor(AlignMode.CENTRE);
        comboText.setOrigin(AlignMode.CENTRE);
        comboText.setColor(Color.gray);

        addChild(shutterTop);
        addChild(shutterBottom);

        addChild(comboText);
    }

    @Override
    public void onLoaded(){
        super.onLoaded();

        SpBeAt game = getPlayScreen().getGame();

        shutterTop.setLocation(0, 0);
        shutterTop.setSize(getWidth(), getHeight() / 2);
        shutterBottom.setLocation(0, getHeight() / 2);
        shutterBottom.setSize(getWidth(), getHeight() / 2);

        comboText.setLocation(getWidth() / 2, getHeight() / 2);
        comboText.setText("0");
        comboText.setFont(game.getFontManager().getFontByName("나눔바른고딕"));
        comboText.setFontSize(32f);
    }

    @Override
    protected void updateInternal(int delta){
        super.updateInternal(delta);

        updateComboText();
        updateShutter();
    }

    protected void updateShutter() {

    }

    protected void updateComboText(){
        int combo = getPlayScreen().getCurrentCombo();

        if (combo != lastCombo)
            comboText.setText(combo + "");

        if (combo > lastCombo){
            comboText.scaleTo(1.25f, 1.25f, EasingType.EASE_OUT_QUAD, 150).scaleTo(1f, 1f, EasingType.EASE_OUT_QUAD, 100);
        }

        lastCombo = combo;
    }

    public PlayScreen getPlayScreen() {
        return playScreen;
    }
}