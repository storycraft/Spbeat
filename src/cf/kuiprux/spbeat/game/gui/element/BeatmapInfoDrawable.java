package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.gui.TextureFillMode;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BeatmapInfoDrawable extends Square {
    public BeatmapInfoDrawable(Beatmap map){
        setLocation(0, 0);
        setSize(101, 101);

        try {
            setColor(Color.white);
            setTexture(new Image(MapManager.SONG_PATH.resolve(map.getJacketPath()).toAbsolutePath().toString()));
        } catch (SlickException e) {
            setColor(Color.magenta);
        }
    }
}
