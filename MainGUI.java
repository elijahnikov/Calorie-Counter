package calorieintake;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainGUI extends JFrame implements ActionListener{
    
    public MainGUI(){
        super("Calorie Intake");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }
    
    private void initGUI() {
        
        //button action command initialisation
        exitBtn.addActionListener(this);
        exitBtn.setActionCommand("exit");
        newTargetBtn.addActionListener(this);
        newTargetBtn.setActionCommand("newtarget");
        addBtn.addActionListener(this);
        addBtn.setActionCommand("add");
        saveBtn.addActionListener(this);
        saveBtn.setActionCommand("save");
        resetBtn.addActionListener(this);
        resetBtn.setActionCommand("reset");
        
        centerPanel.setMinimumSize(new Dimension(300, 330));
        centerPanel.setMaximumSize(centerPanel.getMinimumSize());
        centerPanel.setPreferredSize(centerPanel.getMinimumSize());
        
        rightPanel.setMinimumSize(new Dimension(100, 360));
        rightPanel.setMaximumSize(rightPanel.getMinimumSize());
        rightPanel.setPreferredSize(rightPanel.getMinimumSize());
        
        bottomPanel.setMinimumSize(new Dimension(400, 60));
        bottomPanel.setMaximumSize(bottomPanel.getMinimumSize());
        bottomPanel.setPreferredSize(bottomPanel.getMinimumSize());  
        
        //main panel layout---------------------------------------------
        mainPanel.setLayout(new GridBagLayout());
        mainc.gridx = 0;
        mainc.gridy = 0;
        mainc.fill = GridBagConstraints.BOTH;
        mainc.weightx = 1;
        mainc.weighty = 1;
        mainPanel.add(centerPanel, mainc);
        
        mainc.gridx = 1;
        mainc.gridheight = 2;
        mainc.weighty = 1;
        mainc.weightx = 0;
        mainc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(rightPanel, mainc);
        
        mainc.gridx = 0;
        mainc.gridy = 1;
        mainc.gridheight = 1;
        mainc.gridwidth = 2;
        mainc.fill = GridBagConstraints.HORIZONTAL;
        mainc.weighty = 0;
        mainc.weightx = 1;
        mainPanel.add(bottomPanel, mainc);
        //--------------------------------------------------------------
        
        //right panel layout--------------------------------------------
        rightPanel.setLayout(new BorderLayout());
        
        rightPanel.add(exitBtnPanel, BorderLayout.SOUTH);
        exitBtnPanel.setMinimumSize(new Dimension(100, 100));
        exitBtnPanel.setPreferredSize(exitBtnPanel.getMinimumSize());
        exitBtnPanel.add(saveLbl);
        exitBtnPanel.add(saveBtn);
        exitBtnPanel.add(exitBtn);
        
        rightPanel.add(targetPanel, BorderLayout.NORTH);
        targetPanel.setMinimumSize(new Dimension (130, 160));
        targetPanel.setPreferredSize(targetPanel.getMinimumSize());
        targetPanel.add(targetLbl);
        targetPanel.add(targetNum);
        targetPanel.add(newTargetBtn);
        targetPanel.add(resetBtn);
        
        targetLbl.setBorder(new EmptyBorder(0, 6, 0, 0));
        targetLbl.setFont(new Font("Lucida Grande", 0, 14));
        targetNum.setFont(new Font("Lucida Grande", 1, 24));
        //--------------------------------------------------------------
        
        //bottom panel layout-------------------------------------------
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        nameField.setPreferredSize(new Dimension(150, 30));
        countField.setPreferredSize(new Dimension(50, 30));
        nameField.setForeground(Color.GRAY);
        countField.setForeground(Color.GRAY);
        bottomPanel.add(nameField);
        bottomPanel.add(countField);
        bottomPanel.add(addBtn);
        //--------------------------------------------------------------
        
        //center panel layout-------------------------------------------
        model = new DefaultTableModel(null, column);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
        table.setRowHeight(24);
        tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(370, 400));
        centerPanel.add(tableScroll);
        //--------------------------------------------------------------
        
        //creating placeholder text for jtextfields
        nameField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                if (nameField.getText().equals("Name")){
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                if (nameField.getText().isEmpty()){
                    nameField.setForeground(Color.GRAY);
                    nameField.setText("Name");
                }
            }
        });
        
        countField.addFocusListener(new FocusListener(){
           @Override
           public void focusGained(FocusEvent e){
               if (countField.getText().equals("Count")){
                   countField.setText("");
                   countField.setForeground(Color.BLACK);
               }
           }
           @Override
           public void focusLost(FocusEvent e){
               if (countField.getText().isEmpty()){
                   countField.setForeground(Color.GRAY);
                   countField.setText("Count");
               }
           }
        });
        
        //jframe initialisation
        add(mainPanel);
        setMinimumSize(new Dimension(500, 460));
        setPreferredSize(new Dimension(500, 460));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
       
    }
    
    //button functionality
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if("exit".equals(e.getActionCommand())){
            dispose();
        }
        
        if("newtarget".equals(e.getActionCommand())){
            CalorieGoalGUI calorieGoalGUI = new CalorieGoalGUI();
        }
        
        if("add".equals(e.getActionCommand())){
            addData();
        }
        
        if("reset".equals(e.getActionCommand())){
            resetData();
        }
        
        if("save".equals(e.getActionCommand())){
            
            try {
                save.exportTable(table, tablePath);
            } catch (IOException ex) {}
            
        }
    }
    
    //method to add to table
    public void addToTable(String name, String count){
        model.addRow(new Object[]{name, count});
    }
    
    //setter to set label text
    public static void setTargetLabel(String text){
        targetNum.setText(text);
    }
    
    //getter to get table
    public static JTable getTable(){
        return table;
    }
    
    //method to add data to table
    public void addData(){
        
        int count = 0;
        int calorieTarget = 0; 
        int newTarget = 0;
            
        //validation to ensure both fields are not empty when attempting to input data into table
        if(!nameField.getForeground().equals(Color.GRAY) && !countField.getForeground().equals(Color.GRAY)){
                
            //adding name and count input into jtable 
            addToTable(nameField.getText(), countField.getText());

            //setting label to new calorie count read from text file
            try {
                setTargetLabel(read.readFromFile(editedTargetPath));
            } catch (IOException ex){}
                
            //getting input from count field and using it to substract from target
            count = Integer.parseInt(countField.getText());
            calorieTarget = Integer.parseInt(targetNum.getText());
            newTarget = calorieTarget - count;
                
            //saving new calorie target value to edited calorie text file
            //and displaying it
            save.saveToFile(String.valueOf(newTarget), editedTargetPath);
            try {
                setTargetLabel(read.readFromFile(editedTargetPath));
            } catch (IOException ex){} 
                
            //resetting fields back to original
            nameField.setForeground(Color.GRAY);
            nameField.setText("Name");
            countField.setForeground(Color.GRAY);
            countField.setText("Count");
                
        } 
        
    }

    //method to reset data in table and reset target label
    public void resetData(){

        try {

            //reads original target set by the formula and saves it to other text file
            String originalTarget = read.readFromFile(targetPath);
            save.saveToFile(originalTarget, editedTargetPath);

            //"resets" label by reading from original text file
            setTargetLabel(originalTarget);

            //clears the table of data
            model.setRowCount(0);

            //clear the text file that holds table data
            PrintWriter writer = new PrintWriter(new File(tablePath));
            writer.print("");
            writer.close();

        } catch (IOException ex){}

    }

    //variable declarations
    private GridBagConstraints mainc = new GridBagConstraints();
    private Target target = new Target();
    private ReadFromFile read = new ReadFromFile();
    private SaveToFile save = new SaveToFile();
    
    private JPanel mainPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel exitBtnPanel = new JPanel();
    private JPanel targetPanel = new JPanel();
    private JScrollPane tableScroll;
    
    private JButton exitBtn = new JButton("Exit");
    private JButton saveBtn = new JButton("Save");
    private JButton newTargetBtn = new JButton("New Target");
    private JButton addBtn = new JButton("Add");
    private JButton resetBtn = new JButton("Reset");
    
    private JLabel targetLbl = new JLabel("Target:");
    private static JLabel targetNum = new JLabel();
    private JLabel saveLbl = new JLabel("Save to CSV");
    
    private JTextField nameField = new JTextField("Name");
    private JTextField countField = new JTextField("Count");
    
    public static JTable table;
    public DefaultTableModel model;
    private String[] column = {"Name", "Calorie count"};
    
    private String targetPath = System.getProperty("user.home") + "/Desktop" + "/target.txt";
    private String editedTargetPath = System.getProperty("user.home") + "/Desktop" + "/edited-target.txt";
    private String tablePath = System.getProperty("user.home") + "/Desktop" + "/tableData.txt";

}
