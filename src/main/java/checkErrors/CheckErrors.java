package checkErrors;

import process.Work;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CheckErrors {

    public static Map<String, List<String>> kidsAndPar2 = Work.getMapKidsAndPar();
    public static HashSet<String> allNames = Work.getAllNames();
    public static List<File> allFiles = Work.getFilesList();

    public void allFilesCorrect() {
        for (String s : allNames) {
            int fl = 0;
            if (s.equals(".DS_Store")) {
                continue;
            }
            for (File file : allFiles) {
                String sName = file.getName();
                if (sName.indexOf(".") > 0) {
                    sName = sName.substring(0, sName.lastIndexOf("."));
                }
                if (s.equals(sName)) {
                    fl = 1;
                    break;
                }
            }
            if (fl == 0) {
                System.out.println("Incorrect dependency entry. Some files are missing");
                System.out.println("Good bye!!!");
                System.exit(1);
            }
        }
    }

    public void ifEmpty() {
        if (allFiles.size() == 0) {
            System.out.println("There is no files");
            System.out.println("Good bye!!!");
            System.exit(1);
        }
    }

    public void ifCycleCheck() {
        for (String name : allNames) {
            check(name, name);
        }
    }

    private int check(String name, String beg) {
        if (kidsAndPar2.get(name).size() == 0) {
            return 0;
        }
        if (!kidsAndPar2.get(name).contains(beg)) {
            for (String str : kidsAndPar2.get(name)) {
                int k = check(str, beg);
            }
            return 0;
        } else {
            System.out.println("Dependency between the file " + beg + " and " + name);
            System.out.println("Cycle found, so good bye!!!");
            System.exit(1);
            return 1;
        }
    }
}
