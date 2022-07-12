DROP DATABASE IF EXISTS ijse;
CREATE DATABASE IF NOT EXISTS ijse;

USE ijse;

DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
    student_id VARCHAR(45),
    student_name VARCHAR(45),
    email TEXT,
    contact VARCHAR(20),
    address TEXT,
    nic VARCHAR(45),
    CONSTRAINT PRIMARY KEY (student_id)
    );


DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
    teacher_id VARCHAR(45),
    name VARCHAR(45),
    nic VARCHAR(45),
    contact VARCHAR(45),
    address TEXT,
    CONSTRAINT PRIMARY KEY (teacher_id)
    );


DROP TABLE IF EXISTS `Subject`;
CREATE TABLE IF NOT EXISTS `Subject`(
    subject_id VARCHAR(45),
    subject_name VARCHAR(45),
    credit DOUBLE,
    teacher_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (subject_id),
    CONSTRAINT FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON UPDATE CASCADE ON DELETE CASCADE
    );


DROP TABLE IF EXISTS Course;
CREATE TABLE IF NOT EXISTS Course(
    course_id VARCHAR(45),
    course_name VARCHAR(45),
    cost DOUBLE,
    duration VARCHAR(45),
    subject_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (course_id),
    CONSTRAINT FOREIGN KEY (subject_id) REFERENCES Subject(subject_id) ON UPDATE CASCADE ON DELETE CASCADE
    );


DROP TABLE IF EXISTS Intake;
CREATE TABLE IF NOT EXISTS Intake(
    intake_id VARCHAR(45),
    start_date DATE,
    intakecol VARCHAR(45),
    description VARCHAR(45),
    course_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (intake_id),
    CONSTRAINT FOREIGN KEY (course_id) REFERENCES Course(course_id) ON UPDATE CASCADE ON DELETE CASCADE
    );


DROP TABLE IF EXISTS Registration;
CREATE TABLE IF NOT EXISTS Registration(
    registration_id VARCHAR(45),
    reg_date DATE,
    student_id VARCHAR(45),
    intake_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (registration_id),
    CONSTRAINT FOREIGN KEY (student_id) REFERENCES Student(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (intake_id) REFERENCES Intake(intake_id) ON UPDATE CASCADE ON DELETE CASCADE
    );


DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment(
    payment_id VARCHAR(45),
    date DATE,
    cost DOUBLE,
    registration_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (payment_id),
    CONSTRAINT FOREIGN KEY (registration_id) REFERENCES Registration(registration_id) ON UPDATE CASCADE ON DELETE CASCADE
    );
INSERT INTO Student VALUES('S001','Samitha','samith1999@gmail.com','0762121255','Ratnapura','199912154487');
INSERT INTO Student VALUES('S002','Yasith','yasith.cb2001@gmail.com','0112454555','Colombo','200121202271');
INSERT INTO Student VALUES('S003','Arunu','arunust@gmail.com','0778989546','Balangoda','212546875954');
INSERT INTO Student VALUES('S004','Anjana','anjana.fdo@gmail.com','0702154655','Colombo','123452136547');
INSERT INTO Student VALUES('S005','Nadika','nadika.nadika@gmail.com','0124545888','Galle','356478954265');

INSERT INTO Teacher VALUES ('T001','Kamala','546545875424','0458756249','Pelmadulla');
INSERT INTO Teacher VALUES ('T002','Nimal','458756542222','0112454566','Pandura');
INSERT INTO Teacher VALUES ('T003','Samantha','214565985321','0765482111','Balangoda');
INSERT INTO Teacher VALUES ('T004','Ananda','876545821654','0459868778','Ratnapura');

INSERT INTO Subject VALUES ('Sub001','PRF',10,'T002');
INSERT INTO Subject VALUES ('Sub002','DBP',10,'T001');
INSERT INTO Subject VALUES ('Sub003','DBMS',10,'T003');
INSERT INTO Subject VALUES ('Sub004','OOP',10,'T004');

INSERT INTO Course VALUES ('C001','GDSE',328000,'2 years','Sub001');
INSERT INTO Course VALUES ('C002','CMJD',60000,'6 months','Sub002');

INSERT INTO Intake VALUES ('I001','2022-07-30','','','C001');
INSERT INTO Intake VALUES ('I002','2022-08-30','','','C002');
INSERT INTO Intake VALUES ('I003','2023-01-10','','','C001');

INSERT INTO Registration VALUES ('R001','2022-05-20','S001','I001');
INSERT INTO Registration VALUES ('R002','2022-05-25','S002','I001');
INSERT INTO Registration VALUES ('R003','2022-06-12','S003','I002');
INSERT INTO Registration VALUES ('R004','2022-06-22','S004','I001');
INSERT INTO Registration VALUES ('R005','2022-07-05','S005','I002');

INSERT INTO Payment VALUES ('P001','2022-06-20',100000,'R001');
INSERT INTO Payment VALUES ('P002','2022-07-01',10000,'R002');
INSERT INTO Payment VALUES ('P003','2022-06-05',50000,'R003');