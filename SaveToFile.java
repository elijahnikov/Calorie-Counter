package calorieintake;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SaveToFile {
    
    //saving calorie target to text file to ensure it is available on each startup
    //should take the time to figure out another method without text files
    public void saveToFile(String target, String path){
        
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(target);
            writer.close();
            System.out.println("Printed '" + target + "' to " + path);
        } catch (IOException e){
            System.out.println("An error occured");
        }      
    }

    //save table contents to text file
    public void exportTable(JTable table, String path) throws IOException{
    
       File file = new File(path);
       
       try{
           FileWriter fw = new FileWriter(file);
           BufferedWriter bw = new BufferedWriter(fw);

           for (int i = 0; i < table.getModel().getRowCount(); i++){
               //import time when data was input into table and write to text file
               for (int j = 0; j < table.getModel().getColumnCount(); j++){
                   bw.write(table.getModel().getValueAt(i, j).toString() + ",");
               }
               bw.write(",");
               bw.newLine();
           }
           
           bw.close();
           fw.close();
       } catch (IOException ex){}
       
    }

    public void exportToCSV(JTable table, String path){

        try{

            TableModel model = table.getModel();
            FileWriter csv = new FileWriter(new File(path));

            for (int i = 0; i < model.getColumnCount(); i++){
                csv.write(model.getColumnName(i) + ",");
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++){
                for (int j = 0; j < model.getColumnCount(); j++){
                    csv.write(model.getValueAt(i, j).toString() + ",");
                }
                csv.write("\n");
            }

            csv.close();

        } catch (IOException e) {}

    }

}
