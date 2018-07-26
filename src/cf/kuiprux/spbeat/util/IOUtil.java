package cf.kuiprux.spbeat.util;

import java.io.*;
import java.nio.file.Path;

public class IOUtil {
    public static AsyncTask<byte[]> readAsync(Path path){
        return new AsyncTask<>(new AsyncTask.AsyncCallable<byte[]>() {
            @Override
            public byte[] get() {
                try {
                    return readSync(path);
                } catch (IOException e) {
                    System.out.println("파일 읽기 실패 " + e.getLocalizedMessage());
                }

                return null;
            }
        });
    }

    public static byte[] readSync(Path path) throws IOException {
        try (FileInputStream input = new FileInputStream(path.toFile())){
            try (ByteArrayOutputStream memoryStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[32767];
                int readed = 0;
                while (readed < input.read(buffer)){
                    memoryStream.write(buffer, readed, 32767);
                }

                return memoryStream.toByteArray();
            }
        }
    }
}
