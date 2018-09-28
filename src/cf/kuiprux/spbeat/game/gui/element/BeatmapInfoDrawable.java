package cf.kuiprux.spbeat.game.gui.element;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.MainThreadExecutor;
import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.gui.ButtonPanel;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.util.AsyncTask;
import cf.kuiprux.spbeat.util.IOUtil;
import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.*;
import java.nio.file.Path;

public class BeatmapInfoDrawable extends FixedContainer {

    private Beatmap map;
    private ResourceManager resourceManager;

    private Image defaultJacket;

    private Square beatmapJacket;
    private Square holdNoteNotify;

    public BeatmapInfoDrawable(Beatmap map, ResourceManager resourceManager, Image defaultJacket){
        this.map = map;
        this.resourceManager = resourceManager;
        this.defaultJacket = defaultJacket;
        this.beatmapJacket = new Square(0, 0, ButtonPanel.BUTTON_WIDTH + 1, ButtonPanel.BUTTON_HEIGHT + 1);
        this.holdNoteNotify = new Square(ButtonPanel.BUTTON_WIDTH, ButtonPanel.BUTTON_HEIGHT, 15, 15);

        beatmapJacket.setColor(Color.white);

        holdNoteNotify.setCornerRadius(2);
        holdNoteNotify.setColor(Color.white);
        holdNoteNotify.setAnchor(AlignMode.RIGHT_BOTTOM);

        setDefault();

        setLocation(0, 0);
        setSize(ButtonPanel.BUTTON_WIDTH + 1, ButtonPanel.BUTTON_HEIGHT + 1);

        Path path = MapManager.SONG_PATH.resolve(map.getJacketPath());

        addChild(beatmapJacket);

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
                            beatmapJacket.setTexture(new Image(stream, map.getJacketPath(), false));
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
            beatmapJacket.setTexture(defaultJacket);
        else
            beatmapJacket.setColor(Color.magenta);
    }

    @Override
    public void onLoaded(){
        try {
            if (map.hasHoldMarker()) {
                holdNoteNotify.setTexture(new Image(resourceManager.getStream("texture.jacket.hold_notify"), "texture.jacket.hold_notify", false));
                addChild(holdNoteNotify);
            }
        } catch (SlickException e) {
            System.out.println("texture.jacket.hold_notify 택스쳐 로드 실패 " + e.getLocalizedMessage());
        }
    }
}
