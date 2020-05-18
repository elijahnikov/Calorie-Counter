package calorieintake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CalorieGoalGUI extends JFrame implements ActionListener {

    public CalorieGoalGUI(){
        super("New calorie target");
        initGUI();
    }
    
    private void initGUI() {
        
        ageField.setPreferredSize(new Dimension(60, 30));
        heightFeetField.setPreferredSize(new Dimension(60, 30));
        heightInchesField.setPreferredSize(new Dimension(60, 30));
        weightField.setPreferredSize(new Dimension(60, 30));

        heightFeetField.setForeground(Color.GRAY);
        heightInchesField.setForeground(Color.GRAY);
        
        //adding actionlistener and actioncommand to save button and activity list
        saveBtn.addActionListener(this);
        saveBtn.setActionCommand("save");
        activityList.addActionListener(this);
        activityList.setActionCommand("activity");

        //group radio buttons in ButtonGroup
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        
        goalGroup.add(fatLossBtn);
        goalGroup.add(maintainBtn);
        goalGroup.add(bulkBtn);
        
        mainPanel.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.LINE_END;
        
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(ageLbl, c);
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(genderLbl, c);
        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(heightLbl, c);
        c.gridx = 0;
        c.gridy = 3;
        mainPanel.add(weightLbl, c);
        c.gridx = 0;
        c.gridy = 4;
        mainPanel.add(activityLbl, c);
        c.gridx = 0;
        c.gridy = 5;
        mainPanel.add(goalLbl, c);
        
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 0;
        mainPanel.add(ageField, c);
        c.gridx = 1;
        c.gridy = 1;
        mainPanel.add(maleBtn, c);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(3, 3, 3, -25);
        mainPanel.add(femaleBtn, c);
        c.gridx = 1;
        c.gridy = 2;
        mainPanel.add(heightFeetField, c);
        c.gridx = 2;
        c.gridy = 2;
        mainPanel.add(heightInchesField, c);
        c.gridx = 1;
        c.gridy = 3;
        mainPanel.add(weightField, c);
        c.gridx = 1;
        c.gridy = 4;
        mainPanel.add(activityList, c);
        c.gridx = 1;
        c.gridy = 5;
        mainPanel.add(fatLossBtn, c);
        c.gridx = 1;
        c.gridy = 6;
        mainPanel.add(maintainBtn, c);
        c.gridx = 1;
        c.gridy = 7;
        mainPanel.add(bulkBtn, c);
        c.gridx = 1;
        c.gridy = 8;
        mainPanel.add(saveBtn, c);
        
        //creating placeholder text for jtextfields        
        heightFeetField.addFocusListener(new FocusListener(){
           @Override
           public void focusGained(FocusEvent e){
               if (heightFeetField.getText().equals("Feet")){
                   heightFeetField.setText("");
                   heightFeetField.setForeground(Color.BLACK);
               }
           }
           @Override
           public void focusLost(FocusEvent e){
               if (heightFeetField.getText().isEmpty()){
                   heightFeetField.setForeground(Color.GRAY);
                   heightFeetField.setText("Feet");
               }
           }
        });
        
        heightInchesField.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                if (heightInchesField.getText().equals("Inches")){
                    heightInchesField.setText("");
                    heightInchesField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                if (heightInchesField.getText().isEmpty()){
                    heightInchesField.setForeground(Color.GRAY);
                    heightInchesField.setText("Inches");
                }
            }
        });
        
        add(mainPanel);
        setMinimumSize(new Dimension(300, 340));
        setPreferredSize(new Dimension(getMinimumSize()));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //button and radio button functionality
    @Override
    public void actionPerformed(ActionEvent e) {
        
        double ageInput = 0;
        double heightFeetInput = 0;
        double heightInchesInput = 0;
        double weightInput = 0;
        String genderBtnCheck = "";
        String goalBtnCheck = "";    
        
        //button to perform calculation
        if ("save".equals(e.getActionCommand())){
            
            //check which gender is selected
            if (maleBtn.isSelected()){
                genderBtnCheck = "Male";
            } else if (femaleBtn.isSelected()){
                genderBtnCheck = "Female";
            }
            
            //check which goal is selected
            if (fatLossBtn.isSelected()){
                goalBtnCheck = "Fat Loss";
            } else if (maintainBtn.isSelected()){
                goalBtnCheck = "Maintenance";
            } else if (bulkBtn.isSelected()){
                goalBtnCheck = "Muscle Gain";
            }
            
            //validation to make sure correct input is in the fields and that
            //a radio button is selected
            if (v.checkForNumber(ageField) 
                    && v.checkForNumber(heightInchesField) 
                    && v.checkForNumber(heightFeetField) 
                    && v.checkForNumber(weightField)
                    && (maleBtn.isSelected() || femaleBtn.isSelected())
                    && (fatLossBtn.isSelected() || maintainBtn.isSelected() || bulkBtn.isSelected())){
                
                //taking input from the form
                ageInput = Double.parseDouble(ageField.getText());
                heightFeetInput = Double.parseDouble(heightFeetField.getText());
                heightInchesInput = Double.parseDouble(heightInchesField.getText());
                weightInput = Double.parseDouble(weightField.getText());
                int activitySelection = activityList.getSelectedIndex();
                
                //making a call to the calculation method with parameters
                try {
                    
                    //call to the calorie method to calculate number of calories to intake
                    String count = calculate.calorie(ageInput, genderBtnCheck, heightFeetInput, 
                        heightInchesInput, weightInput, activitySelection, goalBtnCheck);
                    
                    save.saveToFile(count, targetPath);
                    save.saveToFile(count, editedTargetPath);
                    
                    MainGUI.setTargetLabel(read.readFromFile(targetPath));
                } catch (IOException ex){
                    System.out.println("File not found.");
                } finally {
                    dispose();
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Please enter input in all fields.");
            }
        } 
    }   
    
    //variable declarations
    private JPanel mainPanel = new JPanel();
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel ageLbl = new JLabel("Age");
    private JLabel genderLbl = new JLabel("Gender");
    private JLabel heightLbl = new JLabel("Height");
    private JLabel weightLbl = new JLabel("Weight(kg)");
    private JLabel activityLbl = new JLabel("Activity");
    private JLabel goalLbl = new JLabel("Goal");
    private JTextField ageField = new JTextField();
    private JTextField heightFeetField = new JTextField("Feet");
    private JTextField heightInchesField = new JTextField("Inches");
    private JTextField weightField = new JTextField();
    private JRadioButton maleBtn = new JRadioButton("Male");
    private JRadioButton femaleBtn = new JRadioButton("Female");
    private JRadioButton fatLossBtn = new JRadioButton("Fat Loss");
    private JRadioButton maintainBtn = new JRadioButton("Maintenance");
    private JRadioButton bulkBtn = new JRadioButton("Muscle Gain");
    private ButtonGroup genderGroup = new ButtonGroup();
    private ButtonGroup goalGroup = new ButtonGroup();
    private String[] activityStrings = {"Sedentary",
                                        "Light",
                                        "Moderate",
                                        "Very Active",
                                        "Extra Active"}; 
    private JComboBox activityList = new JComboBox(activityStrings);
    private JButton saveBtn = new JButton("Save");
    private Validation v = new Validation();
    private CalorieIntake calculate = new CalorieIntake();
    private SaveToFile save = new SaveToFile();
    private ReadFromFile read = new ReadFromFile();
    private String targetPath = System.getProperty("user.home") + "/Desktop" + "/target.txt";
    private String editedTargetPath = System.getProperty("user.home") + "/Desktop" + "/edited-target.txt";

}
