package process;

import checkErrors.CheckErrors;
import output.Output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Work {
    public static Map<String, List<String>> parAndKids = new HashMap<>();
    public static Map<String, List<String>> kidsAndPar = new HashMap<>();
    private static List<File> filesList = new ArrayList<>();
    private static HashSet<String> allNames = new HashSet<>();
    public static String dirName;

    public static HashSet<String> getAllNames() {
        return allNames;
    }

    public static List<File> getFilesList() {
        return filesList;
    }

    public static String getDirName() {
        return dirName;
    }

    public static Map<String, List<String>> getMapKidsAndPar() {
        return kidsAndPar;
    }

    public void readDir() throws FileNotFoundException {
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
            filesList = Files.walk(Paths.get(dirName))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File file : filesList) {
            readFile(file);
        }
        CheckErrors ifErrors = new CheckErrors();
        ifErrors.allFilesCorrect();
        ifErrors.ifCycleCheck();
        ifErrors.ifEmpty();
        BuildingSequence build = new BuildingSequence();
        build.findSeq();
        Output newFile = new Output();
        newFile.genNewFile();
    }

    void readFile(File file) throws FileNotFoundException {
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
                String file1 = new String();
                Integer ind1 = str.indexOf("/");
                Integer ind2 = str.indexOf("'");
                file1 = str.substring(max(ind2, ind1) + 1, str.length() - 1);
                if (file1.indexOf(".") > 0) {
                    file1 = file1.substring(0, file1.lastIndexOf("."));
                }
                allNames.add(file1);

                if (!kidsAndPar.containsKey(sName)) {
                    kidsAndPar.put(sName, new ArrayList<String>());
                }
                kidsAndPar.get(sName).add(file1);

                if (!parAndKids.containsKey(file1)) {
                    parAndKids.put(file1, new ArrayList<String>());
                }
                parAndKids.get(file1).add(sName);
            }
        }
        if (fl == 0) {
            parAndKids.put(sName, new ArrayList<String>());
            kidsAndPar.put(sName, new ArrayList<String>());
        }
    }

}
