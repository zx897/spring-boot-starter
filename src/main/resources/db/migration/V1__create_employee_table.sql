CREATE TABLE Employee(
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         age INT,
                         gender VARCHAR(10),
                         companyId INT(11)
) engine=InnoDB DEFAULT CHARSET = utf8;