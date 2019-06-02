package VectorDesignTool.vecRead;

import VectorDesignTool.VectorDesignController;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

import static VectorDesignTool.Drawing.commandsHandler.whatToDraw;

public class fileClass {
    public static String fileName = "";
    public static ArrayList[][] commandsList;
    public static int commandlistSize;
    public static int[] lengthof2ndDimentioofCommandArray = new int[100000];
    public static String commandTextBox;
    public static int counterC = 0;

    /**
     * Resets everything to default for a new arrayList
     */
    public static void resetDefault() {
        commandsList = new ArrayList[1][500];
        commandlistSize = 0;
        lengthof2ndDimentioofCommandArray = new int[100000];
        commandTextBox = "";
        fileName = "";
    }

    /**
     *
     * @param Commands This list of the commands
     * @param a Location of the first array to start from
     * @param b Location of the second array to start from
     */
    public static void setTextArea(ArrayList[][] Commands, int a, int b) {
        String finalStringCommand = "";
        for (a = 0; a < commandlistSize; a++) {
            for (b = 0; b < lengthof2ndDimentioofCommandArray[a]; b++) {
                String temp = Commands[a][b].toString().replace("[", "");
                temp = temp.replace("]", "");
                finalStringCommand += temp + " ";
            }
            finalStringCommand = VectorDesignController.regexReplace("\\s$", finalStringCommand, "");
            finalStringCommand += "\n";
        }
        commandTextBox = finalStringCommand;
    }

    /**
     *
     * @param newFileName the file name to set to
     */
    public static void setFileName(String newFileName) {
        fileName = newFileName;
    }

    /**
     *
     * @param newCommandList the new command list
     */
    public static void  setCommandList(ArrayList[][] newCommandList) {
        commandsList = newCommandList;
        getCommandListSize();
    }

    /**
     * gets the current command list size for both of its dimentions
     */
    public static void getCommandListSize() {
        int temp = 0;

        for (ArrayList[] command : commandsList) {
            if(command[0] != null) {
                temp += 1;
            }

        }
        commandlistSize = temp;
        for (int i = 0; i < commandlistSize; i++) {
            int temp1 = 0;
            for ( int a = 0; a < 500; a++) {
                if (commandsList[i][a] != null) {
                    temp1 += 1;
                }
            }
            if (temp1 != 0) {
                lengthof2ndDimentioofCommandArray[i] = temp1;
            }
        }
    }

    /**
     *
     * @param command Current Command
     * @param gc Graphical Context
     */
    public static void addCommand(String command, GraphicsContext gc) {
        int commandsLengthNewLength = commandlistSize + 1;
        ArrayList[][] TempCommands = new ArrayList[commandsLengthNewLength][500];
        for(int a = 0; a < commandlistSize; a++) {
            TempCommands[a] = commandsList[a];
        }
        String [] words = command.split(" ");
        for (int a =0; a < words.length; a++) {
            TempCommands[commandsLengthNewLength - 1][a] = new ArrayList();
            TempCommands[commandsLengthNewLength - 1][a].add(words[a]);
        }
        setCommandList(TempCommands);
        counterC = commandsLengthNewLength;
        whatToDraw(gc, TempCommands[commandsLengthNewLength -1]);
        counterC = 0;

        setTextArea(TempCommands, commandsLengthNewLength -1, commandsLengthNewLength - 1);
    }
}
