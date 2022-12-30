package cycleCheck;

import process.Work;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CycleCheck {

    public static Map<String, List<String>> kidsAndPar2 = Work.getMapKidsAndPar();
    public static Map<String, List<String>> parAndKids2 = Work.getMapParAndKids();
    public static HashSet<String> allNames = Work.getAllNames();
    public static List<File> allFiles = Work.getFilesList(); //

    public void allFilesCorrect() {
        for (String s : allNames) {
            if (!s.endsWith(".txt")) {
                continue;
            }
            int fl = 0;
            for (File file : allFiles) {
                if (s.equals(file.getName())) {
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

    public void ifCycleCheck() {
        for (File file : allFiles) {
            int fl = 0;
            String name = file.getName();
            if (check(name, name) == 1) {
                System.out.println("Cycle found");
                System.out.println("Good bye!!!");
                System.exit(1);
            }
        }
    }

    private int check(String name, String beg) {
        List<String> kk = parAndKids2.get(name);
        if (parAndKids2.get(name).size() == 0) {
            return 0;
        }
        if (!parAndKids2.get(name).contains(beg)) {
            for (String str : parAndKids2.get(name)) {
                int k = check(str, beg);
                if (k == 1) {
                    return 1;
                }
            }
            return 0;
        } else {
            return 1;
        }
    }

}
