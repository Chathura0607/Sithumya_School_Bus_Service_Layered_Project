package lk.ijse.sithumya.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFiledValid(TextField textField, String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        String field = "";

        switch (textField) {
            case NIC:
                field = "^([0-9]{9}[vVxX]|[0-9]{12})$";
                break;

            case EMAIL:
                field = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
                break;

            case NAME:
                field = "^[a-zA-Z.\\s'-]{2,60}$";
                break;

            case DATE:
                field = "^(?:\\d{4}-\\d{2}-\\d{2}|\\d{2}/\\d{2}/\\d{4}|\\d{1,2}/\\d{1,2}/\\d{4})$";
                break;

            case ADDRESS:
                field = "^[a-zA-Z0-9.,/\\\\()#\\s-]{2,255}$";
                break;

            case CONTACT:
                field = "^(?:0|\\+94)?[0-9]{9,10}$";
                break;

            case STATIONId:
                field = "^[A-Za-z0-9_-]{2,15}$";
                break;

            case COST:
                field = "^(?:\\d+(?:\\.\\d{1,2})?)$";
                break;

            case QTY:
                field = "^(?:[1-9]\\d*|0)(?:\\.\\d+)?$";
                break;

            case MAINTENANCEId:
                field = "^[A-Za-z0-9_-]{2,20}$";
                break;

            case COUNT:
                field = "^[1-9]\\d*$";
                break;

            case PLANId:
                field = "^P\\d{3}$";
                break;

            case DISTANCE:
                field = "^\\d+(?:\\.\\d{1,2})?$";
                break;

            case STUDENTId:
                field = "^S\\d{3,4}$";
                break;

            case ITEMId:
                field = "^[a-zA-Z0-9_-]{2,15}$";
                break;

            case TIME:
                field = "^(?:[01]?\\d|2[0-3]):[0-5]\\d(?::[0-5]\\d)?$";
                break;

            case BUSId:
                field = "^[A-Za-z0-9_-]{2,15}$";
                break;
        }

        if (field.isEmpty()) {
            return true;
        }

        Pattern pattern = Pattern.compile(field);
        Matcher matcher = pattern.matcher(text.trim());
        return matcher.matches();
    }

    public static boolean setTextColor(TextField location, javafx.scene.control.TextField field) {
        if (field == null) return false;
        if (isTextFiledValid(location, field.getText())) {
            field.setStyle("-fx-border-color: #10b981; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-background-color: #f0fdf4;");
            return true;
        } else {
            field.setStyle("-fx-border-color: #ef4444; -fx-border-width: 1.5px; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-background-color: #fef2f2;");
            return false;
        }
    }

    public static void resetColor(javafx.scene.control.TextField... fields) {
        if (fields == null) return;
        for (javafx.scene.control.TextField field : fields) {
            if (field != null) {
                field.setStyle("");
            }
        }
    }
}

