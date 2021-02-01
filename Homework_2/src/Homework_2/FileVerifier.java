package Homework_2;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileVerifier {
    public static File getFile(){
        Path currentRelativePath = Paths.get("");
        String currPath = currentRelativePath.toAbsolutePath().toString();
        System.out.print("Current working path is: " + currPath + "\nEnter full or relative InputFilePath: ");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        File enteredPathFile = null;
        try{ // possible exceptions from File(), .exists, .nextLine()
            while (true){
                enteredPathFile = new File(currPath + "//" + filePath);
                if(enteredPathFile.exists()){
                    break;
                }
                else {
                    enteredPathFile = new File(filePath);
                    if(enteredPathFile.exists()){
                        break;
                    }
                }
                System.out.print("No such file. Enter full or relative InputFilePath: ");
                filePath = sc.nextLine();
            }

        }catch (NullPointerException|SecurityException|java.util.NoSuchElementException|IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
        return enteredPathFile;
    }
}
