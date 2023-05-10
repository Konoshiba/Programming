package console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Queue;

public class FileManager {
    public Queue<String> readCommandFile(String path) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        Queue<String> q = new ArrayDeque<>();

        String line;
        while ((line = br.readLine()) != null) q.add(line);
        isr.close(); br.close();
        return q;
    }
}
