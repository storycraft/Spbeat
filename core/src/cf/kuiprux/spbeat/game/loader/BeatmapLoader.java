package cf.kuiprux.spbeat.game.loader;

import cf.kuiprux.spbeat.game.MapManager;
import cf.kuiprux.spbeat.game.beatmap.Beatmap;
import cf.kuiprux.spbeat.game.beatmap.parsing.legacy.LegacyMapParser;
import cf.kuiprux.spbeat.util.AsyncTask;
import cf.kuiprux.spbeat.util.Parallel;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeatmapLoader implements Loader {

    //shift-jis 인데 여기다 비표준 합치면 저런 혼종이 나옵니다.
    private static final Charset ENCODING = Charset.forName("windows-932");

    private MapManager mapManager;

    public BeatmapLoader(MapManager mapManager){
        this.mapManager = mapManager;
    }

    public AsyncTask<Void> loadAll(Path path) throws Exception {
        File folder = path.toFile();

        if (!folder.exists())
            folder.mkdirs();

        if (!folder.isDirectory())
            throw new NotDirectoryException(folder.getName() + " is not directory");

        return new AsyncTask<>(new AsyncTask.AsyncCallable<Void>() {
            @Override
            public Void get() {
                File[] fileList = folder.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".txt");
                    }
                });

                Parallel.forEach(Arrays.asList(fileList), new Parallel.Operation<File>() {
                    @Override
                    public void run(File file) {
                        Beatmap map = loadBeatmapSync(file);

                        if (map == null)
                            return;
                        mapManager.addBeatmap(map);
                    }
                });

                return null;
            }
        });
    }

    public AsyncTask<Beatmap> loadBeatmap(File file) throws Exception {
        return new AsyncTask<>(new AsyncTask.AsyncCallable<Beatmap>() {
            @Override
            public Beatmap get() {
                return loadBeatmapSync(file);
            }
        });
    }

    public Beatmap loadBeatmapSync(File file) {
        try {
            return new LegacyMapParser().parseRawMap(new String(Files.readAllBytes(file.toPath()), ENCODING));
        } catch (Exception e) {
            System.out.println(file.getName() + " 채보 파싱 실패 " + e.getLocalizedMessage());
        }

        return null;
    }
}
