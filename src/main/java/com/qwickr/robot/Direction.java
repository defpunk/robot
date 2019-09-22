package com.qwickr.robot;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Enum of the directions that the {@link Robot} could be facing
 */
public enum Direction {

    NORTH, SOUTH, EAST, WEST;

    /**
     * @return returns the {@link Direction} to the left of this enum value
     */
    public Direction left() {
        return LEFT_VALUES.get(this);
    }

    /**
     * @return returns the {@link Direction} to the right of this enum value
     */
    public Direction right() {
        return RIGHT_VALUES.get(this);
    }

    //Map of directions to the direction on the left
    private static final Map<Direction, Direction> LEFT_VALUES = ImmutableMap.<Direction, Direction>builder()
            .put(NORTH, WEST)
            .put(EAST, NORTH)
            .put(SOUTH, EAST)
            .put(WEST, SOUTH)
            .build();

    //Map of directions to the direction on the right
    private static final Map<Direction, Direction> RIGHT_VALUES = ImmutableMap.<Direction, Direction>builder()
            .put(NORTH, EAST)
            .put(EAST, SOUTH)
            .put(SOUTH, WEST)
            .put(WEST, NORTH)
            .build();

}
