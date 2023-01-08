package output;

import process.BuildingSequence;
import process.ReadDir;

import java.util.List;
import java.io.*;
import java.util.Scanner;

public class Output {
    public List<String> filesAns2 = BuildingSequence.getAns();
    public static List<File> fileList2 = ReadDir.getFilesList();
    private final String str = ReadDir.getDirName();

    /** Функция, которвя создает текстовый файл для ответа и записывает туда ответ. */
    public void genNewFile() {
        String strResult = str + "/result.txt";
        try (FileWriter writer = new FileWriter(strResult, false)) {
            for (String s : filesAns2) {
                for (File file : fileList2) {
                    if (file.getName().equals(".DS_Store")) {
                        continue;
                    }
                    if (file.getName().contains(s)) {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            writer.write(scanner.nextLine());
                            writer.append('\n');
                        }
                    }
                }
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}





