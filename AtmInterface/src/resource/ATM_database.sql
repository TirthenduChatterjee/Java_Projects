create database ATM;
use ATM;
CREATE TABLE Users (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(255),
    pin VARCHAR(20),
    amount float4(25)
);
insert into Users (name,pin,amount) values('Tirtho','1234',20000),('Biswarup','4321',11000),('Jeet','5678',15000),('Premjit','8765',25000),('Kaustab','9012',13000),('Saikat','2109',17000);
CREATE TABLE Transactions(
    operation VARCHAR(15),
    payerId INT NOT NULL,
    receipentId INT NOT NULL,
    amount DOUBLE,
    payeramt DOUBLE,
    receipentamt DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
