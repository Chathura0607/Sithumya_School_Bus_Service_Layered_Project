CREATE DATABASE SCHOOL_BUS_SERVICE_MANAGEMENT_SYSTEM;

-- Switch to the created database
USE SCHOOL_BUS_SERVICE_MANAGEMENT_SYSTEM;

-- Create the tables
CREATE TABLE Driver (
                        Driver_ID VARCHAR(12) PRIMARY KEY,
                        Name VARCHAR(50) NOT NULL,
                        Address VARCHAR(150),
                        Email VARCHAR(100),
                        Contact_Number VARCHAR(10),
                        Monthly_Salary DECIMAL(10, 2),
                        Payment_Amount DECIMAL(10, 2),
                        Payment_Due DECIMAL(10, 2)
);

CREATE TABLE Bus (
                     Bus_ID VARCHAR(08) PRIMARY KEY,
                     Name VARCHAR(50) NOT NULL,
                     Driver_ID VARCHAR(12),
                     Driver_Assigned_Date DATE NOT NULL,
                     Debt_Amount DECIMAL(10, 2),
                     FOREIGN KEY (Driver_ID) REFERENCES Driver(Driver_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE BusSchedule (
                             Schedule_ID INT AUTO_INCREMENT PRIMARY KEY,
                             Bus_ID VARCHAR(08),
                             Date DATE NOT NULL,
                             Time_Type ENUM('Arrival', 'Return') NOT NULL,
                             Schedule_Time TIME NOT NULL,
                             FOREIGN KEY (Bus_ID) REFERENCES Bus(Bus_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Payment_Plan (
                              Payment_Plan_ID VARCHAR(25) PRIMARY KEY,
                              Name VARCHAR(50) NOT NULL,
                              Number_Of_Installments INT(1) NOT NULL
);

CREATE TABLE Student (
                         Student_ID VARCHAR(12) PRIMARY KEY,
                         Name VARCHAR(50) NOT NULL,
                         Date_Of_Birth DATE NOT NULL,
                         School_Name VARCHAR(50) NOT NULL,
                         Distance_To_School DECIMAL(10, 2) NOT NULL,
                         Bus_ID VARCHAR(08),
                         Payment_Plan_ID VARCHAR(25),
                         FOREIGN KEY (Bus_ID) REFERENCES Bus(Bus_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Payment_Plan_ID) REFERENCES Payment_Plan(Payment_Plan_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Guardian (
                          Guardian_ID VARCHAR(12) PRIMARY KEY,
                          Name VARCHAR(50) NOT NULL,
                          Relation VARCHAR(30) NOT NULL,
                          Contact_Number VARCHAR(10) NOT NULL,
                          Email VARCHAR(100),
                          Address VARCHAR(150) NOT NULL
);

CREATE TABLE Guardianship (
                              Student_ID VARCHAR(12) NOT NULL,
                              Guardian_ID VARCHAR(12) NOT NULL,
                              PRIMARY KEY (Student_ID, Guardian_ID), -- Combined primary key
                              Emergency_Contact VARCHAR(10) NOT NULL,
                              FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                              FOREIGN KEY (Guardian_ID) REFERENCES Guardian(Guardian_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Fee (
                     Fee_ID INT AUTO_INCREMENT PRIMARY KEY,
                     Student_ID VARCHAR(12),
                     Payment_Plan_ID VARCHAR(25),
                     Monthly_Fee DECIMAL(10, 2) NOT NULL,
                     Discount DECIMAL(10, 2),
                     Total_Amount DECIMAL(10, 2),
                     Due_Date DATE,
                     FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                     FOREIGN KEY (Payment_Plan_ID) REFERENCES Payment_Plan(Payment_Plan_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Penalty (
                         Penalty_ID INT AUTO_INCREMENT PRIMARY KEY,
                         Fee_ID INT,
                         Amount DECIMAL(10, 2) NOT NULL,
                         FOREIGN KEY (Fee_ID) REFERENCES Fee(Fee_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Payment (
                         Payment_ID INT AUTO_INCREMENT PRIMARY KEY,
                         Payment_Plan_ID VARCHAR(25),
                         Student_ID VARCHAR(12),
                         Amount DECIMAL(10, 2) NOT NULL,
                         Payment_Date DATE NOT NULL,
                         Is_Completed VARCHAR(5) NOT NULL,
                         FOREIGN KEY (Payment_Plan_ID) REFERENCES Payment_Plan(Payment_Plan_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                         FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Maintenance_Record (
                                    Maintenance_ID VARCHAR(25) PRIMARY KEY,
                                    Bus_ID VARCHAR(08),
                                    Description VARCHAR(150),
                                    Cost DECIMAL(10, 2) NOT NULL,
                                    Date DATE NOT NULL,
                                    FOREIGN KEY (Bus_ID) REFERENCES Bus(Bus_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Inventory_Item (
                                ItemID VARCHAR(08) PRIMARY KEY,
                                Maintenance_ID VARCHAR(25),
                                Name VARCHAR(50) NOT NULL,
                                Quantity DECIMAL NOT NULL,
                                Purchase_Cost DECIMAL(10, 2),
                                FOREIGN KEY (Maintenance_ID) REFERENCES Maintenance_Record(Maintenance_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Filling_Station (
                                 Station_ID VARCHAR(10) PRIMARY KEY ,
                                 Name VARCHAR(50) NOT NULL,
                                 Location VARCHAR(50) NOT NULL,
                                 Contact_Number VARCHAR(10) NOT NULL
);

CREATE TABLE Fueling_Record (
                                Record_ID INT AUTO_INCREMENT PRIMARY KEY,
                                Bus_ID VARCHAR(08),
                                Station_ID VARCHAR(10),
                                Total_Cost DECIMAL(10, 2) NOT NULL,
                                Payment_Amount DECIMAL(10, 2),
                                Debt_Amount DECIMAL(10, 2),
                                FOREIGN KEY (Bus_ID) REFERENCES Bus(Bus_ID) ON UPDATE CASCADE ON DELETE CASCADE,
                                FOREIGN KEY (Station_ID) REFERENCES Filling_Station(Station_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE User (
                      User_ID VARCHAR(12) PRIMARY KEY,
                      Name VARCHAR(50) NOT NULL,
                      Password VARCHAR(50) NOT NULL
);

INSERT INTO User (User_ID, Name, Password) VALUES
    ('200215903066', 'Chathura', 'Chathu0607');

DELIMITER //

CREATE TRIGGER calculate_discount BEFORE INSERT ON Fee
    FOR EACH ROW
BEGIN
    DECLARE discount_rate DECIMAL(10, 2);

    -- Get the number of installments for the associated payment plan
    SELECT Number_Of_Installments INTO discount_rate
    FROM Payment_Plan
    WHERE Payment_Plan_ID = NEW.Payment_Plan_ID;

    -- Calculate the discount rate based on the number of installments
    CASE
        WHEN discount_rate = 4 THEN SET NEW.Discount = 0.00;
        WHEN discount_rate = 3 THEN SET NEW.Discount = NEW.Monthly_Fee * 0.03;
        WHEN discount_rate = 2 THEN SET NEW.Discount = NEW.Monthly_Fee * 0.05;
        WHEN discount_rate = 1 THEN SET NEW.Discount = NEW.Monthly_Fee * 0.07;
        ELSE SET NEW.Discount = 0.00; -- Default discount if Payment_Plan_ID is not found
        END CASE;

    -- Calculate the total amount and update the NEW.Total_Amount column
    SET NEW.Total_Amount = NEW.Monthly_Fee - NEW.Discount;
END;
//

CREATE TRIGGER calculate_discount_before_update BEFORE UPDATE ON Fee
    FOR EACH ROW
BEGIN
    DECLARE discount_rate DECIMAL(10, 2);

    -- Get the number of installments for the associated payment plan
    SELECT Number_Of_Installments INTO discount_rate
    FROM Payment_Plan
    WHERE Payment_Plan_ID = NEW.Payment_Plan_ID;

    -- Calculate the discount rate based on the number of installments
    CASE
        WHEN discount_rate = 4 THEN SET NEW.Discount = 0.00;
        WHEN discount_rate = 3 THEN SET NEW.Discount = NEW.Monthly_Fee * 0.03;
        WHEN discount_rate = 2 THEN SET NEW.Discount = NEW.Monthly_Fee * 0.05;
        WHEN discount_rate = 1 THEN SET NEW.Discount = NEW.Monthly_Fee * 0.07;
        ELSE SET NEW.Discount = 0.00; -- Default discount if Payment_Plan_ID is not found
        END CASE;

    -- Calculate the total amount and update the NEW.Total_Amount column
    SET NEW.Total_Amount = NEW.Monthly_Fee - NEW.Discount;
END;
//

DELIMITER ;

DELIMITER //

CREATE TRIGGER add_penalty_before_due_date
    BEFORE UPDATE ON Fee
    FOR EACH ROW
BEGIN
    DECLARE penalty DECIMAL(10, 2);
    DECLARE days_late INT;

    -- Calculate the days late
    SET days_late = DATEDIFF(CURDATE(), NEW.Due_Date);

    -- If the payment is late, calculate the penalty
    IF days_late > 0 THEN
        SET penalty = NEW.Total_Amount * (0.0025 * days_late); -- 0.25% penalty per day
        -- Add penalty to the total amount
        SET NEW.Total_Amount = NEW.Total_Amount + penalty;
        -- Insert penalty record into Penalty table
        INSERT INTO Penalty (Fee_ID, Amount) VALUES (NEW.Fee_ID, penalty);
    END IF;
END;
//

DELIMITER ;
