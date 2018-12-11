# Diabetes Supplies Project

**This is the myDiabetesSupplies project with embedded Derby DB integration instead of MySQL**.

This project was in the back of my mind for a long time. It was previously developed in C, but did not have a GUI or database.
It was very rudimental. I wanted to take a step ahead and improve it (as well as my knowledge with Java, JavaFX, and Apache Derby DB). It is a very dear project to me, as I've been diagnosed with type 1 Diabetes since February 2004. I love to apply the skills that I possess to solve real life problems.

This project was incentivated by Professor Vanselow, and his COP 3003 class. 
The files contained in this repository are used for grading purposes in the COP 3003 class of the fall semester 2018.

The languages used are **Java**, **JavaFX**, and **SQL**.
This project is going to be under constant development. Whenever something will need to be added to the program, it will also  be displayed under the **ToDo** section of this README.

The JavaDoc for this project is available as well, get to it by clicking [here](https://lollators.github.io/DiabetesSupplies_DERBY/javadoc/program/package-summary.html).

## Project Description

This project is aimed towards creating a software to manage diabetes supplies. There can be multiple users added. These accounts will be stored in a database along with a table reserved for the supplies. The primary key of the account is going to be its username (does not allow duplicates). The primary key of a product is going to be its serial number. The product table will also include a username attribute, used as a foreign key that references the username attribute in the account table. In this way it is possible to link a product to its owner.

## Apache Derby Setup

The user shouldn't have troubles running the project straight away, as Derby is configured in embedded mode and everything should be already linked together. The only issue that can arise is the ```//``` or ```\\``` based on Windows or Mac OS, in the Database URL variable. This variable is used to effectively connect to the database. In order to overcome the issue, I personally included two executable JAR files, one for each operating system. 

## Installation setup

- Download this repository as a .zip file.
- Extract all the content in a folder.
- Double click on one of the .jar files, based on your Operating System. Alternatively, using the command prompt utilize: ```java -jar filename.jar``` while being in the same directory as the file.

Note: if your Java Environment Path is not set up correctly, you may encounter issues while trying to open the file from the command prompt.

## GUI Design Principles

This project adheres to some GUI Design Principles, as:

- It provides meaningful contrast between screen elements.
- It uses colors and graphics effectively and simply.
- The interface is visually, conceptually and linguistically clear.
- The user can control interactions effectively.
- The effect of actions on objects are visible (especially alterations to the database).
- It is very responsive.

## Login Info

In order to log in, the user will need a username and password. For the purpose of this project you can use:

username  |password
----------|----------
Lollators |test

## Project Sample GIF
![GIF Demo](docs/simpledemo.gif)

## ToDo:

- [X] Add invalid username check when trying to delete a user.
- [X] Add account already exist check when trying to add a new user.

## Class Diagram

![Project Class Diagram](docs/ClassDiagram.png)

## Database Schema
![Database Schema](docs/DBSchema.png)

## Built With

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE of choice
* [SceneBuilder](https://gluonhq.com/products/scene-builder) - Great software to create GUI screens with ease
* [Apache Derby](https://db.apache.org/derby/) - Embedded database

## Plugins Used

* [FindBugs](http://findbugs.sourceforge.net/)
* [CheckStyle](http://checkstyle.sourceforge.net/config_naming.html#PackageName) - Google Checks used

## Authors

* **Luca Missaglia** - *Project Developer* - [Lollators](https://github.com/Lollators)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
