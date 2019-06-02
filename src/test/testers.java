package test;

import static VectorDesignTool.vecRead.fileClass.resetDefault;
import static org.junit.jupiter.api.Assertions.*;

import VectorDesignTool.VectorDesignController;
import VectorDesignTool.vecRead.fileClass;
import VectorDesignTool.vecRead.vecLoad;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class testers {

    @BeforeEach
    public void newSame() {
        resetDefault();
    }

    @Test
    public void noInput() {

    }

    @Test
    public void testDrawing1() {
        String workingDir = "";
        ArrayList[][] command = vecLoad.LoadVecFile(System.getProperty("user.dir") + "/src/vecFiles/example1 (1).vec");
        fileClass.setCommandList(command);
    }

    @Test
    public void testDrawing2() {
        String workingDir = "";
        fileClass.setFileName(workingDir + "/src/vecFiles/Line.vec");
        ArrayList[][] command = vecLoad.LoadVecFile(System.getProperty("user.dir") + "/src/vecFiles/example2 (1).vec");
        fileClass.setCommandList(command);
    }

    @Test
    public void testDrawing3() {
        String workingDir = "";
        fileClass.setFileName(workingDir + "/src/vecFiles/Line.vec");
        ArrayList[][] command = vecLoad.LoadVecFile(System.getProperty("user.dir") + "/src/vecFiles/example3 (1).vec");
        fileClass.setCommandList(command);
    }
}
