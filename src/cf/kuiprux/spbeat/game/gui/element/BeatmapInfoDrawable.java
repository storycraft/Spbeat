package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.gui.TextureFillMode;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BeatmapInfoDrawable extends Square {
    public BeatmapInfoDrawable(Beatmap map){
        setLocation(0, 0);
        setSize(101, 101);

        try (FileInputStream stream = new FileInputStream(MapManager.SONG_PATH.resolve(map.getJacketPath()).toFile())){
            setTexture(new Image(stream, map.getJacketPath(), false));
            setColor(Color.white);
        } catch (Exception e) {
            System.out.println("jacket 로드 에러 " + e.getLocalizedMessage());
            setColor(Color.magenta);
        }
    }
}
