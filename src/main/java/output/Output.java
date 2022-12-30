package output;

import process.BuildingSequence;
import process.Work;

import java.util.List;
import java.io.*;
import java.util.Scanner;

public class Output {
    public List<String> filesAns2 = BuildingSequence.getAns();
    public static List<File> fileList2 = Work.getFilesList();
    private String str = Work.getDirName();

    public void genNewFile() {
        String strResult = str + "/result.txt";
        try (FileWriter writer = new FileWriter(strResult, false)) {
            for (Integer i = 0; i < filesAns2.size(); i++) {
                for (Integer j = 0; j < fileList2.size(); j++) {
                    if (fileList2.get(j).getName().equals(filesAns2.get(i))) {
                        Scanner scanner = new Scanner(fileList2.get(j));
                        while (scanner.hasNext()) {
                            writer.write(scanner.nextLine());
                            writer.append('\n');
                            //inFile.add(scanner.nextLine());
                        }
                    }
                }

            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //FileOutputStream fos = new FileOutputStream("D:\\example.txt");
//            File file = new File(strResult);
//            if (!file.createNewFile())
//            {
//                System.out.println("oh no, there is already a file with that name. Bye!");
//                System.exit(1);
//            }
//            for(Integer i = 0; i < filesAns2.size(); i++)
//            {
//                for(Integer j = 0; j < fileList2.size(); j++)
//                {
//                    if(fileList2.get(j).getName().equals(filesAns2.get(i)))
//                    {
//                        Scanner scanner = new Scanner(fileList2.get(j));
//                        while (scanner.hasNext()) {
//                            file.write(scanner.nextLine().getBytes());
//                            //inFile.add(scanner.nextLine());
//                        }
//                    }
//                }
//            }

    }
//        catch (Exception e) {
//            System.err.println(e);
//        }

}





