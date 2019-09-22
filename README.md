# Robot

## Overview 
A command line application written in Java that simulates a toy robot moving around a table top using a 5 x 5 grid as its
positioning system. 


## Installation

1. Clone or download the repository
2. Using a terminal navigate to the project's root folder
2. Run `mvn clean package` 
3. To run the application run `java -jar target/robot-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Usage

When the robot application is running you can issue the commands below.  NB Commands that would cause the robot to fall
off the table top will be ignored as will invalid commands.  Additionally no command will be accepted until
a valid place command has been issued.  The application will keep running until stopped by pressing CTRL + C

### Commands

#### PLACE X,Y,FACING

Puts the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.
The origin (0,0) can be considered to be the SOUTH WEST most corner.
The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, 
including another PLACE command.     

#### MOVE

Moves the toy robot one unit forward in the direction it is currently facing.

#### LEFT

Will rotate the robot 90° anticlockwise without changing the position of the robot.

#### RIGHT

Rotate the robot 90° clockwise without changing the position of the robot.

#### REPORT

Outputs the X,Y and F of the robot to standard output.

### Example Input & Output
```
place 0,0,NORTH
move
report => Output:0, 1, NORTH
place 0, 0, NORTH
left
report => Output:0, 0, WEST
place 1,2,EAST
move
move
left
move
report => Output:3, 3, NORTH
```

The examples above are demonstrated to be working in the in the RobotIT test class.  If new examples are
added please ensure a matching test is added  

## Todo List

Improvements that could be made to the current implementation.

* Add welcome prompt when the application starts up
* Add QUIT command
* Allow the dimensions of the table top grid to be entered as arguments when the application starts 
* Introduce a class to represent the tabletop and use this to determine if the proposed position will be in bounds
* Add the inputstream and output stream the robot will use as member variables of the robot class to reduce the untested code.
* Print report output in another colour.
* Add error messages for invalid input. 
* Introduce cucumber or other BDD tool to simplify the creation of integration tests.
