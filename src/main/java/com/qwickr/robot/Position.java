package com.qwickr.robot;

import java.util.Objects;

import static java.lang.String.format;

/**
 * Class representing the current position of the robot on the table top including the
 * {@link Direction} it is facing.  The Position class is immutable, the {@link #move()},
 * {@link #left()} and {@link #right()} methods return a new Position instance based on the
 * instance that had the method call was made on.
 */
class Position {

    private final int x;
    private final int y;
    private final Direction facing;

    /**
     * Create a {@link Position} instance representing the robot on the tabletop
     * contains x y coordinate and the direction it is facing.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param facing the direction the robot is facing
     */
    Position(int x, int y, Direction facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    /**
     * Move one step forward in the direction robot is facing
     * @return a new {@link Position} instance representing the position moved too.
     */
    Position move() {
        switch (facing) {
            case SOUTH:
                return new Position(x, y - 1, facing);
            case NORTH:
                return new Position(x, y + 1, facing);
            case WEST:
                return new Position(x - 1, y, facing);
            case EAST:
                return new Position(x + 1, y, facing);
            default:
                throw new IllegalStateException("Unable to move unknown direction " + facing.name());
        }
    }

    /**
     * Updates the position so the robot is facing one step to the left
     * @return a new {@link Position} instance facing to the left.
     */
    Position left() {
        return new Position(x, y, facing.left());
    }

    /**
     * Updates the position so the robot is facing one step to the right
     * @return a new {@link Position} instance facing to the right.
     */
    Position right() {
        return new Position(x, y, facing.right());
    }

    @Override
    public String toString() {
        return format("%d, %d, %s", x, y, facing.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y &&
                facing == position.facing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, facing);
    }


}
