# ENSF 409 Course Registration System Milestone 3
---
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

## Bonus Feature:
The bonus feature for this project is the ability to login to existing users. The name of the student will behave like the username, and the id of the student will behave like the password. To sercure this data, the decision was made so that the id of users will never be displayed on the client application. If a name and id is entered, and it is not in the databse, a new student will be created with that name and id.

