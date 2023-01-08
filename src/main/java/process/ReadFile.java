package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.max;


public class ReadFile {
    private final static Set<String> allNames = new HashSet<>();
    private static final Map<String, List<String>> parAndKids = new HashMap<>();
    private static final Map<String, List<String>> kidsAndPar = new HashMap<>();

    /** Функция, которая передает список всех файлов. */
    public static Set<String> getAllNames() {
        return allNames;
    }

    /** Функция, которая передает словарь {ребенок, список всех его родителей}. */
    public static Map<String, List<String>> getMapKidsAndPar() {
        return kidsAndPar;
    }

    /**
     * Функция, которая вызывает вспомогательную функцию, которая считывает файлы с их содержимым
     * @param file - файл, который нужно прочитать
     */
    public void readFile(File file) throws FileNotFoundException {
        readFile1(file);
    }

    /**
     * Функция которая считывает файлы с их содержимым
     * @param file - файл6 который нужно прочитать
     */
    private void readFile1(File file) throws FileNotFoundException {
        String sName = file.getName();
        if (sName.indexOf(".") > 0) {
            sName = sName.substring(0, sName.lastIndexOf("."));
        }
        allNames.add(sName);
        List<String> inFile = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            inFile.add(scanner.nextLine());
        }
        int fl = 0;
        for (String str : inFile) {
            if (str.contains("require")) {
                fl = 1;
                String file1;
                int ind1 = str.indexOf("/");
                int ind2 = str.indexOf("'");
                file1 = str.substring(max(ind2, ind1) + 1, str.length() - 1);
                if (file1.indexOf(".") > 0) {
                    file1 = file1.substring(0, file1.lastIndexOf("."));
                }
                allNames.add(file1);

                if (!kidsAndPar.containsKey(sName)) {
                    kidsAndPar.put(sName, new ArrayList<>());
                }
                kidsAndPar.get(sName).add(file1);

                if (!parAndKids.containsKey(file1)) {
                    parAndKids.put(file1, new ArrayList<>());
                }
                parAndKids.get(file1).add(sName);
            }
        }
        if (fl == 0) {
            parAndKids.put(sName, new ArrayList<>());
            kidsAndPar.put(sName, new ArrayList<>());
        }
    }
}
