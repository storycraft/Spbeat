package cf.kuiprux.spbeat.game;

import cf.kuiprux.spbeat.util.AsyncTask;
import cf.kuiprux.spbeat.util.IOUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    public static final Path RESOURCE_PATH;

    static {
        RESOURCE_PATH = Paths.get("res");
    }

    private Map<String, byte[]> memoryMap;

    public ResourceManager(){
        this.memoryMap = new HashMap<>();
    }

    public AsyncTask<Boolean> addAsync(String name, Path path){
        return new AsyncTask<>(new AsyncTask.AsyncCallable<Boolean>() {
            @Override
            public Boolean get() {
                return add(name, path);
            }
        });
    }

    public boolean add(String name, Path path){
        if (contains(name))
            return false;

        try {
            byte[] data = IOUtil.readSync(path);
            if (data == null)
                return false;

            memoryMap.put(name, data);
        } catch (IOException e) {
            System.out.println("리소스 로드 실패 " + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    public byte[] get(String name){
        return memoryMap.get(name);
    }

    public ByteArrayInputStream getStream(String name){
        if (!contains(name))
            return null;
        return new ByteArrayInputStream(get(name));
    }

    public boolean contains(String name){
        return memoryMap.containsKey(name);
    }

    public boolean remove(String name){
        return memoryMap.remove(name) != null;
    }
}
