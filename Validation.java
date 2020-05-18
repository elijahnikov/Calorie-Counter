package calorieintake;

import javax.swing.JTextField;

public class Validation {
    
    //class to verify input in text fields throughout the program
    public boolean checkForNumber(JTextField field){
        
        String numberRegex = "^([+-]?\\d*\\.?\\d*)$";
        
        if (!field.getText().matches(numberRegex)){
            return false;
        } else {
            return true;
        }
    } 
    
}
