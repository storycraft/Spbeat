package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.MainThreadExecutor;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.util.IOUtil;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.util.function.BiConsumer;

public class BeatmapInfoDrawable extends Square {
    public BeatmapInfoDrawable(Beatmap map){
        setLocation(0, 0);
        setSize(101, 101);

        IOUtil.readAsync(MapManager.SONG_PATH.resolve(map.getJacketPath())).run().whenComplete(new BiConsumer<byte[], Throwable>() {
            @Override
            public void accept(byte[] bytes, Throwable throwable) {
                if (throwable != null){
                    System.out.println("jacket 파일 로드 에러 " + throwable.getLocalizedMessage());
                    setColor(Color.magenta);

                    return;
                }

                ByteArrayInputStream stream = new ByteArrayInputStream(bytes);

                MainThreadExecutor.addTask(() -> {
                    try {
                        setTexture(new Image(stream, map.getJacketPath(), false));
                    } catch (SlickException e) {
                        System.out.println("jacket 로드 에러 " + e.getLocalizedMessage());
                    }
                    });

                setColor(Color.white);
            }
        });
    }
}
