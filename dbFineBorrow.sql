--  --practical 5

-- Borrower Table
-- Create the Borrower table
CREATE TABLE Borrower (
    Roll_no INT PRIMARY KEY,
    Name VARCHAR(100),
    Date_of_Issue DATE,
    Name_of_Book VARCHAR(200),
    Status CHAR(1) CHECK (Status IN ('I', 'R'))
);

-- Create the Fine table
CREATE TABLE Fine (
    Roll_no INT REFERENCES Borrower(Roll_no),
    Fine_Date DATE,
    Amt DECIMAL(10, 2)
);

DELIMITER $$

CREATE PROCEDURE ReturnBook(
    IN p_roll_no INT,       -- Input: Roll number
    IN p_book_name VARCHAR(200) -- Input: Book name
)
BEGIN
    DECLARE v_date_of_issue DATE;
    DECLARE v_status CHAR(1);
    DECLARE v_days_difference INT;
    DECLARE v_fine_amount DECIMAL(10, 2) DEFAULT 0;

    -- Fetch the Date_of_Issue and Status for the specified roll number and book
    SELECT Date_of_Issue, Status
    INTO v_date_of_issue, v_status
    FROM Borrower
    WHERE Roll_no = p_roll_no AND Name_of_Book = p_book_name;

    -- Check if the book is already returned
    IF v_status = 'R' THEN
        SELECT 'Book has already been returned!' AS Message;
    END IF;

    -- Calculate the number of days since the book was issued
    SET v_days_difference = DATEDIFF(CURDATE(), v_date_of_issue);

    -- Determine the fine amount
    IF v_days_difference BETWEEN 15 AND 30 THEN
        SET v_fine_amount = v_days_difference * 5;
    ELSEIF v_days_difference > 30 THEN
        SET v_fine_amount = v_days_difference * 50;
    END IF;

    -- Update the Borrower table to mark the book as returned
    UPDATE Borrower
    SET Status = 'R'
    WHERE Roll_no = p_roll_no AND Name_of_Book = p_book_name;

    -- Insert into the Fine table if a fine is applicable
    IF v_fine_amount > 0 THEN
        INSERT INTO Fine (Roll_no, Fine_Date, Amt)
        VALUES (p_roll_no, CURDATE(), v_fine_amount);
    END IF;

    -- Output the result
    SELECT 'Book returned successfully!' AS Result;
    IF v_fine_amount > 0 THEN
        SELECT CONCAT('Fine Amount: Rs ', v_fine_amount) AS FineDetails;
    ELSE
        SELECT 'No fine applicable.' AS FineDetails;
    END IF;
END$$

DELIMITER ;
-- Insert sample data into Borrower table
INSERT INTO Borrower 
VALUES (101, 'Junaid', '2024-10-01', 'Great Indians', 'I'),
       (102, 'Ali', '2024-11-01', 'Introduction to AI', 'I'),
       (103, 'Sara', '2024-09-20', 'Machine Learning', 'I');

CALL ReturnBook(101, 'Great Indians');

SELECT * FROM Borrower;

SELECT * FROM Fine;