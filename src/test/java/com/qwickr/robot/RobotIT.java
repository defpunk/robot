package com.qwickr.robot;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests to demonstrate this implementation satisfies the example test cases.
 */
public class RobotIT {

    private Robot robot;

    @Before
    public void setUp() {
        robot = new Robot();
    }

    @Test
    public void testCase1() {
        robot.execute("place 0,0,NORTH");
        robot.execute("move");
        String report = robot.execute("report");
        assertThat(report, is("0, 1, NORTH"));
    }

    @Test
    public void testCase2() {
        robot.execute("place 0, 0, NORTH");
        robot.execute("left");
        String report = robot.execute("report");
        assertThat(report, is("0, 0, WEST"));
    }

    @Test
    public void testCase3() {
        robot.execute("place 1,2,EAST");
        robot.execute("move");
        robot.execute("move");
        robot.execute("left");
        robot.execute("move");
        String report = robot.execute("report");
        assertThat(report, is("3, 3, NORTH"));
    }
}
