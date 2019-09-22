package com.qwickr.robot;

import org.junit.Test;

import static com.qwickr.robot.Direction.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PositionTest {

    @Test
    public void shouldReturnCommaSeparatedStringWhenToStringCalled() {
        Position position = new Position(0, 0, SOUTH);
        assertThat(position.toString(), is("0, 0, SOUTH"));
    }

    @Test
    public void shouldReduceYValueWhenFacingSouthAndMoveCalled() {
        Position position = new Position(0, 0, SOUTH);
        Position updatedPosition = position.move();
        assertThat(updatedPosition, is(new Position(0, -1, SOUTH)));
    }

    @Test
    public void shouldIncreaseYValueWhenFacingNorthAndMoveCalled() {
        Position position = new Position(0, 0, NORTH);
        Position updatedPosition = position.move();
        assertThat(updatedPosition, is(new Position(0, 1, NORTH)));
    }

    @Test
    public void shouldReduceXValueWhenFacingWestAndMoveCalled() {
        Position position = new Position(0, 0, WEST);
        Position updatedPosition = position.move();
        assertThat(updatedPosition, is(new Position(-1, 0, WEST)));
    }

    @Test
    public void shouldIncreaseXValueWhenFacingEastAndMoveCalled() {
        Position position = new Position(0, 0, EAST);
        Position updatedPosition = position.move();
        assertThat(updatedPosition, is(new Position(1, 0, EAST)));
    }

    @Test
    public void shouldBeFacingWestWhenCallingLeftWhenNorthFacing() {
        Position position = new Position(0, 0, NORTH);
        Position updatedPosition = position.left();
        assertThat(updatedPosition, is(new Position(0, 0, WEST)));
    }

    @Test
    public void shouldBeFacingEastWhenCallingRightWhenNorthFacing() {
        Position position = new Position(0, 0, NORTH);
        Position updatedPosition = position.right();
        assertThat(updatedPosition, is(new Position(0, 0, EAST)));
    }
}
