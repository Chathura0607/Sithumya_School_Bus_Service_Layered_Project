package lk.ijse.sithumya.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFiledValid(TextField textField, String text){
        String field = "";

        switch (textField){
            case NIC:
                field = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";
                break;

            case EMAIL:
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;

            case NAME:
                field = "^[a-zA-Z]+(?: [a-zA-Z]+)*$";
                break;

            case DATE:
                field = "^(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])/(?:[0-9]{2})?[0-9]{2}$";
                break;

            case ADDRESS:
                field = "^([a-zA-Z0-9/\\\\''(),\\\\s-]{2,255})$";
                break;

            case CONTACT:
                field = "^(\\+?94)?(0)?(77|71|72|75|76|78|79|91|11|74)\\d{7}$";
                break;

            case STATIONId:
                field = "^[0-9]{9}$";
                break;

            case COST:
                field = "^(\\d+(\\.\\d{1,2})?)$";
                break;

            case QTY:
                field = "^(?:[1-9][0-9]*|0)(?:\\.[0-9]+)?$";
                break;

            case MAINTENANCEId:
                field = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=])[A-Za-z0-9!@#$%^&*()-_+=]{8,}$";
                break;

            case COUNT:
                field = "^[1-9][0-9]*$";
                break;

            case PLANId:
                field = "^P\\d{3}$";
                break;

            case DISTANCE:
                field = "^\\d+(\\.\\d)?$";
                break;

            case STUDENTId:
                field = "^S\\d{3,4}$";
                break;

            case ITEMId:
                field = "^[a-zA-Z0-9]+$";
                break;
        }

        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField field){
        if (Regex.isTextFiledValid(location,field.getText())){
            field.setStyle("-fx-border-color: green; -fx-border-width: 2 2 2 2; -fx-background-color: white;");
            return true;
        }else {
            field.setStyle("-fx-border-color: red; -fx-border-width: 2 2 2 2; -fx-background-color: white;");
            return false;
        }
    }
}
