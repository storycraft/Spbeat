package cf.kuiprux.spbeat.game.loader;

import cf.kuiprux.spbeat.util.AsyncTask;

import java.nio.file.Path;

public interface Loader {
    AsyncTask<Void> loadAll(Path path) throws Throwable;
}
