package lk.ijse.sithumya.bo.impl;

import javafx.scene.control.Alert;
import lk.ijse.sithumya.bo.custom.UserBO;
import lk.ijse.sithumya.dao.DAOFactory;
import lk.ijse.sithumya.dao.custom.UserDAO;
import lk.ijse.sithumya.dto.UserDTO;
import lk.ijse.sithumya.entity.User;
import lk.ijse.sithumya.sendMail.EmailService;
import lk.ijse.sithumya.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserBOImpl implements UserBO {

    private UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAOType(DAOFactory.DAOTypes.USER);

    @Override
    public void checkCredentialAndLogin(UserDTO userDTO) throws SQLException, IOException {
        User user = new User(null, userDTO.getUserName(), userDTO.getPassword());
        User dbUser = userDAO.findUserByName(user.getUserName());

        if (dbUser != null) {
            if (dbUser.getPassword().equals(user.getPassword())) {
                Navigation.navigateToDashboard();
                String loginTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                //EmailService.sendLoginSuccessEmail(user.getUserName(), loginTime);
            } else {
                new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "User ID not found!").show();
        }
    }

    @Override
    public boolean sendVerificationCodeByEmail(String verificationCode) {
        return EmailService.sendCodeByEmail(verificationCode);
    }

    @Override
    public void resetPassword(UserDTO userDTO) throws SQLException {
        User user = new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword());
        userDAO.resetPassword(user);
    }
}
