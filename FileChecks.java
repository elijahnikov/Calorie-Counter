package calorieintake;

import java.io.File;
import java.io.IOException;

public class FileChecks {

    public FileChecks(){
        check();
    }

    private void check(){

        //calorie target needs to be saved on user's pc,
        //checks if file exists and if not then creates it.
        String targetPath = System.getProperty("user.home") + "/Desktop" + "/target.txt";
        String editedTargetPath = System.getProperty("user.home") + "/Desktop" + "/edited-target.txt";
        String tablePath = System.getProperty("user.home") + "/Desktop" + "/tableData.txt";
        String datePath = System.getProperty("user.home") + "/Desktop" + "/date.txt";

        File target = new File(targetPath);
        File editedTarget = new File(editedTargetPath);
        File table = new File(tablePath);
        File dateSave = new File(datePath);

        if(!target.exists() && !target.isDirectory()){
            try {
                target.createNewFile();
                System.out.println("Creating new 'target.txt' file");
            } catch (IOException e){
                System.out.println("Cannot create directory/file");
            }
        }

        if(!editedTarget.exists() && !editedTarget.isDirectory()){
            try {
                editedTarget.createNewFile();
                System.out.println("Creating new 'edited-target.txt' file.");
            } catch (IOException e){
                System.out.println("Cannot create directory/file");
            }
        }

        //checking if  file exists for the table data
        //if not, creates it
        //necessary for keeping table data even on program close
        if (!table.exists() && !table.isDirectory()){
            try {
                table.createNewFile();
                System.out.println("Creating new text file for table data");
            } catch (IOException e){
                System.out.println("Cannot create directory/file");
            }
        }

        //table needs to be reset on new date
        //date on program run is saved to text file and compared on each run
        if (!dateSave.exists() && !dateSave.isDirectory()){
            try {
                dateSave.createNewFile();
                System.out.println("Created new text file for date saving");
            } catch (IOException e){
                System.out.println("Cannot create directory/file");
            }
        }

    }
}
