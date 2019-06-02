package VectorDesignTool.vecRead;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class vecLoad {

    /**
     * Return the lines in the .vec file as an array list
     * @param fileName of the .vec file
     * @return Commands as ArrayList[][]
     */
    public static ArrayList[][] LoadVecFile(String fileName) {
        BufferedReader br = null;
        ArrayList commands = new ArrayList();
        fileClass.fileName = fileName;

        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int commandsLength = commands.size();
        ArrayList[][] TempCommands = new ArrayList[commandsLength][500];
        for(int a = 0; a < commandsLength; a++) {
            String temp = commands.get(a).toString();
            String [] words = temp.split(" ");
            for (int b = 0; b < words.length; b++) {
                TempCommands[a][b] = new ArrayList();
                TempCommands[a][b].add(words[b]);
            }
        }
        return TempCommands;
    }
}