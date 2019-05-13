package VectorDesignTool.vecRead;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class vecLoad {

    /**
     *
     * @param fileName
     */
    public static void LoadVecFile(String fileName) {
        BufferedReader br = null;
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        try {
            br = new BufferedReader(new FileReader(workingDir + "/src/vecFiles/" + fileName));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
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
    }

}
