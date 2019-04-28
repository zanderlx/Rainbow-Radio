package csulb.cecs327.DFS;
import com.google.gson.*;
import com.google.gson.stream.*;
import csulb.cecs327.Client.Models.MusicEntry;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class InitDFS {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (RemoteInputFileStream in = new RemoteInputFileStream("src/csulb/cecs327/Server/Files/music.json", false)){

            in.connect();
            Reader targetReader = new InputStreamReader(in);
            JsonReader jreader = new JsonReader(targetReader);
            MusicEntry[] music = gson.fromJson(jreader, MusicEntry[].class);
            MusicEntry[] partial= new MusicEntry[1000];

            int start = 0;
            Charset utf8 = StandardCharsets.UTF_8;
            for (int i = 0; i < music.length / 1000; i++) {
                try (Writer writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream("music" + (i + 1) + ".json"), utf8)
                )) {
                    System.arraycopy(music, start, partial, 0, 1000);
                    start += 1000;

                    writer.write(gson.toJson(partial));
                }
            }
            System.out.println("Finished partitioning music.json!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
