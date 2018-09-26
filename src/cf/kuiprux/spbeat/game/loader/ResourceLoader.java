package cf.kuiprux.spbeat.game.loader;

import cf.kuiprux.spbeat.game.ResourceManager;
import cf.kuiprux.spbeat.util.AsyncTask;

import java.nio.file.Path;

public class ResourceLoader implements Loader {

    private ResourceManager manager;

    public ResourceLoader(ResourceManager manager){
        this.manager = manager;
    }

    @Override
    public AsyncTask<Void> loadAll(Path path) {
        return new AsyncTask<>(new AsyncTask.AsyncCallable<Void>() {
            @Override
            public Void get() {
                //manager.add("", path.resolve(""));

                Path texturePath = path.resolve("texture");

                manager.add("texture.btn.settings", texturePath.resolve("button").resolve("settings.png"));
                manager.add("texture.btn.previous", texturePath.resolve("button").resolve("previous.png"));
                manager.add("texture.btn.next", texturePath.resolve("button").resolve("next.png"));
                manager.add("texture.btn.play", texturePath.resolve("button").resolve("play.png"));
                manager.add("texture.marker.default", texturePath.resolve("marker_square.png"));
                manager.add("texture.marker.hold.arrow", texturePath.resolve("hold_note").resolve("holdarrow.png"));
                manager.add("texture.marker.hold.line", texturePath.resolve("hold_note").resolve("line.png"));
                manager.add("texture.marker.hold.end", texturePath.resolve("hold_note").resolve("holdarrow.png"));
                manager.add("texture.marker.start", texturePath.resolve("kokokara.png"));
                manager.add("texture.marker.effect.default", texturePath.resolve("marker3.jpg"));
                manager.add("texture.marker.hit.perfect", texturePath.resolve("effect4.jpg"));
                manager.add("texture.marker.hit.great", texturePath.resolve("effect3.jpg"));
                manager.add("texture.marker.hit.poor", texturePath.resolve("effect2.jpg"));
                manager.add("texture.marker.hit.miss", texturePath.resolve("effect1.jpg"));
                manager.add("texture.jacket.default", texturePath.resolve("defaultjacket.jpg"));
                manager.add("texture.shutter.top", texturePath.resolve("bg_shutter_top.jpg"));
                manager.add("texture.shutter.bottom", texturePath.resolve("bg_shutter_bottom.jpg"));

                return null;
            }
        });
    }
}
