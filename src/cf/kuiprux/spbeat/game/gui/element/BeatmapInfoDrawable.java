package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.MainThreadExecutor;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.util.AsyncTask;
import cf.kuiprux.spbeat.util.IOUtil;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.nio.file.Path;
import java.util.function.BiConsumer;

public class BeatmapInfoDrawable extends Square {

    private Image defaultJacket;

    public BeatmapInfoDrawable(Beatmap map, Image defaultJacket){
        this.defaultJacket = defaultJacket;

        setLocation(0, 0);
        setSize(101, 101);

        setColor(Color.white);

        Path path = MapManager.SONG_PATH.resolve(map.getJacketPath());

        new AsyncTask<>(new AsyncTask.AsyncCallable<Void>() {
            @Override
            public Void get() {
                if (!path.toFile().isFile()){
                    setDefault();
                    return null;
                }

                try {
                    byte[] bytes = await(IOUtil.readAsync(MapManager.SONG_PATH.resolve(map.getJacketPath())));

                    ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
                    MainThreadExecutor.addTask(() -> {
                        try {
                            setTexture(new Image(stream, map.getJacketPath(), false));
                        } catch (SlickException e) {
                            System.out.println(map.getJacketPath() + " 자켓 로드 오류 " + e.getLocalizedMessage());
                            setDefault();
                        }
                    });
                } catch (Exception e) {
                    System.out.println(map.getJacketPath() + " 자켓 로드 오류 " + e.getLocalizedMessage());
                    setDefault();
                }

                return null;
            }
        }).run();
    }

    private void setDefault(){
        if (defaultJacket != null)
            setTexture(defaultJacket);
        else
            setColor(Color.magenta);
    }
}
