package cf.kuiprux.spbeat.game;

import java.util.ArrayList;
import java.util.List;

public class MainThreadExecutor {

    private static List<Runnable> queuedTask;

    static {
        queuedTask = new ArrayList<>();
    }

    public static void addTask(Runnable runnable){
        queuedTask.add(runnable);
    }

    public static void update(){
        for (Runnable runnable : new ArrayList<>(queuedTask)) {
            queuedTask.remove(runnable);
            runnable.run();
        }
    }
}
