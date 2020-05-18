package calorieintake;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        
        //calorie target needs to be saved on user's pc,
        //checks if file exists and if not then creates it.
        String targetPath = System.getProperty("user.home") + "/Desktop" + "/target.txt";
        String editedTargetPath = System.getProperty("user.home") + "/Desktop" + "/edited-target.txt";
        File target = new File(targetPath);
        File editedTarget = new File(editedTargetPath);
        
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
        String tablePath = System.getProperty("user.home") + "/Desktop" + "/tableData.txt";
        File table = new File(tablePath);
        
        if (!table.exists() && !table.isDirectory()){
            try {
                table.createNewFile();
                System.out.println("Creating new text file for table data");
            } catch (IOException e){
                System.out.println("Cannot create directory/file");
            }
        }
        
        //setting target label by taking calorie count from file
        String targetNum = read.readFromFile(editedTargetPath);
        targ.setTarget(targetNum);
        
        //running program
        java.awt.EventQueue.invokeLater(() -> {
            //runs main gui
            MainGUI mainGUI = new MainGUI();
            //sets target label on program run
            mainGUI.setTargetLabel(targetNum);
            //import table data into table on program run
            try {
                read.importTable(MainGUI.getTable(), tablePath);
            } catch (IOException ex){}
        });
    }
    
    private static ReadFromFile read = new ReadFromFile();
    private static SaveToFile save = new SaveToFile();
    private static Target targ = new Target();
}
