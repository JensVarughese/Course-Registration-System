# ENSF 409 Course Registration System
---

## About:
A client server application that registers courses for students. Developed with Java using Java Eclipse.

## Members:

| Name              | UCID     |             Email              |
| ----------------- |:--------:|:-------------------------------:|
| Jens Varughese    | 30061226 | jens.varughese1@ucalgary.ca    |
| Zachary Lancaster | 30077019 | zachary.lancaster@ucalgary.ca  |
| Salma Salhi       | 30064196 | salma.salhi@ucalgary.ca 		|

## How to Run:

The Client and Server would run as two seperate applications. (Server Application folder and Client Application folder).

For the Server Application, the file that will be run is:
```bash
ServerCommController.java
```

For the Client Application, the file that will be run is:
```bash
ClientCommController.java
```

## Setting up the DataBase:

First, make a mySQL connection of the name "CourseData". Make sure the username of your database is "root" and the password is "ensf409". Next, you want to make a schema of the name "coursedata" and import the tables using the .csv files. You will also want to make sure to set the default values of all 6 courseN columns to be zero.

You can also make your own data tables, but be sure to keep the format:
### student
| studentid | studentname | course1 | course 2 | course3 | course4 | course5 | course6 |
|:---------:|:----------:|:--------:|:--------:|:-------:|:-------:|:-------:|:-------:|
|   "INT"   |   "TEXT"   |   "INT"  |   "INT"  | "INT"   |  "INT"  |  "INT"  |  "INT"  |
|   "INT"   |   "TEXT"   |   "INT"  |   "INT"  | "INT"   |  "INT"  |  "INT"  |  "INT"  |
|   "INT"   |   "TEXT"   |   "INT"  |   "INT"  | "INT"   |  "INT"  |  "INT"  |  "INT"  |

### courses
| idcourses | coursename | coursenum | prereq |
|:---------:|:----------:|:--------:|:--------:|
|   "INT"   |   "TEXT"   |   "INT"  |   "INT"  |
|   "INT"   |   "TEXT"   |   "INT"  |   "INT"  |
|   "INT"   |   "TEXT"   |   "INT"  |   "INT"  |

### courseoffering
| idcourseoffering | coursename | coursenum | secnum | seccap|
|:----------------:|:----------:|:---------:|:------:|:-----:|
|   "INT"          |   "TEXT"   |   "INT"   |  "INT" | "INT" |
|   "INT"          |   "TEXT"   |   "INT"   |  "INT" | "INT" |
|   "INT"          |   "TEXT"   |   "INT"   |  "INT" | "INT" |

, where all "INT"'s are defaulted to 0(zero).
