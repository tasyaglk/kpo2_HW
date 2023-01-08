package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ReadDir {
    public static String dirName;
    private static List<File> filesList = new ArrayList<>();

    /**
     * Функция, которая передает имя директории.
     */
    public static String getDirName() {
        return dirName;
    }

    /**
     * Функция, которая передает список файлов.
     */
    public static List<File> getFilesList() {
        return filesList;
    }

    /**
     * Функция, которая вызывает функцию, что считывает файлы с содержимым.
     */
    public final void readDir() throws FileNotFoundException {
        readDir1();
    }

    /**
     * Функция, что считывает файлы с содержимым.
     */
    private void readDir1() throws FileNotFoundException {
        dirName = null;
        boolean isInputWorking = true;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the directory to search for please:\n");
                dirName = scanner.nextLine();
                if (dirName.equals("Exit")) {
                    System.out.println("Good bye!!!");
                    System.exit(1);
                }

                File file = new File(dirName);
                if (!file.exists()) {
                    throw new FileNotFoundException();
                }
                isInputWorking = false;
            } catch (Exception ex) {
                if (ex.getClass().getSimpleName().equals("FileNotFoundException")) {
                    System.out.println("There is no such folder. If you want to leave, enter Exit");
                    dirName = null;
                } else ex.printStackTrace();
            }
        } while (isInputWorking);
        try {
            filesList = Files.walk(Paths.get(dirName)).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File file : filesList) {
            ReadFile readFileCnt = new ReadFile();
            readFileCnt.readFile(file);
        }

        Work workMain = new Work();
        workMain.work();
    }
}
