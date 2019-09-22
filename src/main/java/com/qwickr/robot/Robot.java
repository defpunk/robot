package com.qwickr.robot;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Class representing a robot moving on a tabletop.
 */
public class Robot {

    private static final int MIN_BOUND = 0;
    private static final int MAX_BOUND = 4;

    private static final Set<String> SUPPORTED_DIRECTIONS = Stream.of(Direction.values())
            .map(Direction::name)
            .collect(toSet());


    private enum Command {report, move, left, right, place}

    private Position currentPosition;

    /**
     * The main method of the application, creates a loop that
     * allows commands to be issued to the robot on the command line/
     *
     * @param args The arguments are ignored
     */
    public static void main(String[] args) {
        Robot robot = new Robot();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String result = robot.execute(scanner.nextLine());
            if (!result.isEmpty()) {
                System.out.println(result);
            }
        }
    }

    /**
     * Instructs the robot instance to execute a command.
     * <p>
     * Currently the following commands are supported: place, report, move, left, right.  The place
     * command must have the X Y coordinates and the direction the robot is to face supplied with it
     * in the format place X,Y,FACING this puts the robot on the table in position X,Y and facing NORTH,
     * SOUTH, EAST or WEST.  The origin (0,0) can be considered to be the SOUTH WEST most corner.
     * The first valid command to the robot is a place command, after that, any sequence of commands may be issued,
     * in any order, including another place command.
     * <p>
     * NB all commands will be ignored until a valid place command has been executed.
     *
     * @param commandString String containing the command to be executed
     * @return output of the command.  Empty string is returned in cases where there is no output
     */
    String execute(final String commandString) {

        if (commandString.startsWith(Command.place.name())) {
            String parameters = commandString.substring(5).trim();
            Position position = positionFromPlaceParameter(parameters);
            if (position != null && withinBounds(position)) {
                currentPosition = position;
            }
        } else {
            //Only process other commands if a valid place command has been placed
            if (hasValidPosition()) {

                Command command = Command.valueOf(commandString);

                switch (command) {
                    case left:
                        currentPosition = currentPosition.left();
                        break;
                    case right:
                        currentPosition = currentPosition.right();
                        break;
                    case move:
                        Position updatedPosition = currentPosition.move();
                        if (withinBounds(updatedPosition)) {
                            currentPosition = updatedPosition;
                        }
                        break;
                    case report:
                        return currentPosition.toString();
                }
            }
        }

        return EMPTY;
    }

    private boolean hasValidPosition() {
        return currentPosition != null;
    }

    private Position positionFromPlaceParameter(String parameters) {
        if (!parameters.isEmpty()) {
            String[] segments = parameters.split(",");
            if (segments.length == 3) {
                Integer x = stringToCoordinate(segments[0].trim());
                Integer y = stringToCoordinate(segments[1].trim());
                String direction = segments[2].trim();
                if (SUPPORTED_DIRECTIONS.contains(direction) && x != null && y != null) {
                    Position position = new Position(x, y, Direction.valueOf(direction));
                    if (withinBounds(position)) {
                        currentPosition = position;
                    }
                }
            }
        }
        return null;
    }


    private boolean withinBounds(final Position position) {
        return position.getY() >= MIN_BOUND && position.getX() >= MIN_BOUND
                && position.getY() <= MAX_BOUND && position.getX() <= MAX_BOUND;
    }

    private Integer stringToCoordinate(final String parameter) {
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
