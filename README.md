# redplanetrobotics
A sample cli spring application that takes coordinates for a robot on a grid along with a set of instructions for movements and outputs the resulting coordinates of the robot after following the instructions.

## Running the application
This application can be run by using maven:
`mvn spring-boot:run`
Alternatively, building the jar with maven `mvn clean install` and then running
the jar file located in target directory `java -jar red-planet-robotics-1.0-SNAPSHOT.jar`

## Usage
Interaction with the application is done through standard input on the command line.

At the first step application will as for upper right cell coordinates. 
This will establish the size of the grid. 

After this a series of value pairs can be provided. 

The first value of a pair is
the robot's initial location in format \[X Y D\] where X and Y are the x and y coordinates
on the grid and D is the direction robot is facing. The direction can be one of the following:
* N - for North
* E - for East
* S - for South
* W - for West

The second value of the pair is the list of instructions, this is a string containing
single letter representations of valid commands:
* F - move one cell forward in the current direction
* L - turn left 90 degrees but stay in the same cell
* R - turn right 90 degrees but stay in the same cell

The application will keep expecting these value pairs until it's terminated. 

# Tests
Tests can be run using `mvn clean test` 
