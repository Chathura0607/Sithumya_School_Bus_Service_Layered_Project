package lk.ijse.sithumya.bo.custom;

import lk.ijse.sithumya.bo.SuperBO;
import lk.ijse.sithumya.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface UserBO extends SuperBO {
    void checkCredentialAndLogin(UserDTO userDTO) throws SQLException, IOException;

    boolean sendVerificationCodeByEmail(String verificationCode);

    void resetPassword(UserDTO userDTO) throws SQLException;
}
