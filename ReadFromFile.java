package calorieintake;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ReadFromFile {
    
    public String readFromFile(String path) throws FileNotFoundException, IOException{
        
        BufferedReader read = new BufferedReader(new FileReader(path));
        String text = read.readLine();
        return text;
        
    }

    //read data from text file into table, used on program run to re-populate table
    public void importTable(JTable table, String path) throws IOException{

        File file = new File(path);

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] lines = br.lines().toArray();

            for (int i = 0; i < lines.length; i++){
                //split by comma
                String[] row = lines[i].toString().split(",");
                //add time, name and count data from text file into table
                model.addRow(row);
            }

        } catch (FileNotFoundException ex){}

    }

}
