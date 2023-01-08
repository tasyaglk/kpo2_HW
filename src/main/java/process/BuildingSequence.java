package process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;

public class BuildingSequence {
    Integer maxx = 0;
    public static Map<String, List<String>> kidsAndPar2 = ReadFile.getMapKidsAndPar();
    public static List<String> filesAns = new ArrayList<>();

    /**
     * Функция, которая передает список отсортированных файлов.
     */
    public static List<String> getAns() {
        return filesAns;
    }

    private void maxX() {
        for (List<String> val : kidsAndPar2.values()) {
            maxx = max(maxx, val.size());
        }
    }
    /** Функция, которая вызывает функцию, что строит искомую последовательность. */
    public void findSeq()
    {
        findSeq1();
    }

    /** Функция, которая строит искомую последовательность. */
    private void findSeq1() {
        maxX();
        for (Integer i = 0; i <= maxx; i++) {
            List<String> filesFixSize = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : kidsAndPar2.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                Integer size = value.size();
                if (size.equals(i)) {
                    filesFixSize.add(key);
                }
            }
            Map<String, Integer> fileCnt = new HashMap<>();
            for (int j = 0; j < filesFixSize.size(); j++) {
                int cnt = 0;
                String name = filesFixSize.get(j);
                for (int k = 0; k < filesFixSize.size(); k++) {
                    List<String> value = kidsAndPar2.get(filesFixSize.get(j));
                    if (value.contains(name)) {
                        cnt += 1;
                    }
                }
                fileCnt.put(name, cnt);
            }

            for (Integer j = 0; j < filesFixSize.size(); j++) {
                for (Map.Entry<String, Integer> entry : fileCnt.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (value.equals(j)) {
                        filesAns.add(key);
                    }
                }
            }
        }
    }
}
