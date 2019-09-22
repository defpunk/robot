package com.qwickr.robot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static com.qwickr.robot.Direction.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class DirectionTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {NORTH, WEST, EAST},
                {EAST, NORTH, SOUTH},
                {SOUTH, EAST, WEST},
                {WEST, SOUTH, NORTH}
        });
    }

    private Direction input;
    private Direction expectedLeft;
    private Direction expectedRight;

    /**
     * Creates a new instance of the Parameterised test
     *
     * @param input         The direction enum under test
     * @param expectedLeft  The expected result of a call to the left method
     * @param expectedRight The expected result of a call to the right method
     */
    public DirectionTest(Direction input, Direction expectedLeft, Direction expectedRight) {
        this.input = input;
        this.expectedLeft = expectedLeft;
        this.expectedRight = expectedRight;
    }

    @Test
    public void shouldReturnExpectedLeft() {
        assertThat(input.left(), is(expectedLeft));
    }

    @Test
    public void shouldReturnExpectedRight() {
        assertThat(input.right(), is(expectedRight));
    }
}
