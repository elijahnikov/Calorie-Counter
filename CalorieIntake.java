package calorieintake;

public class CalorieIntake {
    
    //using the Mifflin - St Jeor formula to calculate BMR and total energy expenditure
    public String calorie(double age, String gender, double heightFeet, double heightInches,
            double weight, int activityLevel, String goal){

        int BMR;
        int calorieIntake;
        int constant = 0;
        double activityCoefficient = 0;
        int goalCoefficient = 0;

        //converting height from feet and inches to cm
        int tempHeight = (int) ((heightFeet * 12) + heightInches);
        float heightCm = (float) (tempHeight * 2.54);

        //BMR, weight, height, age for males and females.
        if (gender == "Male"){
            constant = 5;
        } else if (gender == "Female"){
            constant = -161;
        }

        switch (activityLevel){
            case 0:
                activityCoefficient = 1.2;
                break;
            case 1:
                activityCoefficient = 1.375;
                break;
            case 2:
                activityCoefficient = 1.55;
                break;
            case 3:
                activityCoefficient = 1.725;
                break;
            case 4:
                activityCoefficient = 1.9;
                break;
        }
        
        switch(goal){
            case "Fat Loss":
                goalCoefficient = -300;
                break;
            case "Maintenance":
                goalCoefficient = 0;
                break;
            case "Muscle Gain":
                goalCoefficient = 300;
                break;
        }
        
        BMR = (int) ((10 * weight) + (6.25 * heightCm) - (5 * age) + constant);
        
        calorieIntake = (int) Math.round(((BMR * activityCoefficient) + goalCoefficient)/100.0) * 100;
        
        return String.valueOf(calorieIntake);
    }
    
}
