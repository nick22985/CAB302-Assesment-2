package VectorDesignTool.vecRead;

import java.util.ArrayList;

public class fileClass {
    public static String fileName;
    public static ArrayList[][] commandsList;
    public static int commandlistSize;
    public static int[] lengthof2ndDimentioofCommandArray = new int[100];

    public static void setFileName(String newFileName) {
        fileName = newFileName;
    }

    public static void  setCommandList(ArrayList[][] newCommandList) {
        commandsList = newCommandList;
        getCommandListSize();
    }

    public static void getCommandListSize() {
        int temp = 0;
        int temp1 = 0;
        for (ArrayList[] command : commandsList) {
            if(command[0] != null) {
                temp += 1;
            }
        }
        for (int i = 0; i < commandlistSize; i++) {
            for ( int a = 0; a < 500; a++) {
                if (commandsList[i][a] != null) {
                    temp1 += 1;
                }
            }
            lengthof2ndDimentioofCommandArray[i] = temp1;
        }
        commandlistSize = temp;
    }

    public static void addCommand(String command) {
        int commandsLengthNewLength = commandlistSize + 1;
        ArrayList tempComands = new ArrayList();
        ArrayList[][] TempCommands = new ArrayList[commandsLengthNewLength][500];
        for(int a = 0; a < commandlistSize; a++) {
            tempComands.add(commandsList[a]);
        }

        String [] words = command.split(" ");
        ArrayList tempArray = new ArrayList();
        for (int a =0; a < words.length; a++) {
            System.out.println(tempArray);
            tempArray.add(words[a]);
        }

        setCommandList(commandsList);

    }
}
