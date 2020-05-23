package calorieintake;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        //performs file checks to ensure necessary files exist
        FileChecks fileChecks = new FileChecks();

        //runs main GUI class
        MainGUI mainGUI = new MainGUI();

        //date section to compare date saved on text file to today's date
        //if today's date is after saved data (signifying new day) table and target data is reset
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        String savedDateStr = read.readFromFile(datePath);

        try {

            Date todaysDate = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse(dateStr);
            Date savedDate = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse(savedDateStr);

            if (todaysDate.compareTo(savedDate) > 0){
                mainGUI.resetData();
            }

        } catch (ParseException e){}

        //print date that program is run
        save.saveToFile(dateFormat.format(date), datePath);

        //setting target label setter by taking calorie count from file
        String targetNum = read.readFromFile(editedTargetPath);
        targ.setTarget(targetNum);


        //sets target label on program run
        mainGUI.setTargetLabel(targetNum);
        //import table data into table on program run
        try {
            read.importTable(MainGUI.getTable(), tablePath);
        } catch (IOException ex){}
    }

    private static String editedTargetPath = System.getProperty("user.home") + "/Desktop" + "/edited-target.txt";
    private static String tablePath = System.getProperty("user.home") + "/Desktop" + "/tableData.txt";
    private static String datePath = System.getProperty("user.home") + "/Desktop" + "/date.txt";
    private static ReadFromFile read = new ReadFromFile();
    private static SaveToFile save = new SaveToFile();
    private static Target targ = new Target();

}
