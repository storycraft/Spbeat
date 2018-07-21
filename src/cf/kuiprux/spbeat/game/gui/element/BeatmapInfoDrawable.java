package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.gui.TextureFillMode;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BeatmapInfoDrawable extends SimpleContainer {

    private Square jacketDrawable;

    //100x100의 비트맵 자켓 텍스쳐와 제목을 포함한 컨테이너
    public BeatmapInfoDrawable(Beatmap map){
        this.jacketDrawable = new Square();

        jacketDrawable.setSize(100, 100);
        jacketDrawable.setTextureFillMode(TextureFillMode.CENTER);
        try {
            jacketDrawable.setTexture(new Image(map.getJacketPath()));
        } catch (SlickException e) {
            jacketDrawable.setColor(Color.magenta);
        }
        addChild(jacketDrawable);
    }

    public Square getJacketDrawable() {
        return jacketDrawable;
    }
}
