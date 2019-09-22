package com.qwickr.robot;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RobotTest {

    private Robot robot;

    @Before
    public void setUp() {
        robot = new Robot();
    }

    //helper to check report is empty ie no valid command placed
    private void assertEmptyReport() {
        String report = robot.execute("report");
        assertThat(report, is(emptyString()));
    }

    @Test
    public void shouldIgnoreReportWhenFirstCommand() {
        assertEmptyReport();
    }

    @Test
    public void shouldIgnoreReportWhenFirstPlaceCommandMissingParameters() {
        robot.execute("place");
        assertEmptyReport();
    }

    @Test
    public void shouldIgnoreReportWhenFirstPlaceCommandHasInvalidXParameter() {
        robot.execute("place X,2,NORTH");
        assertEmptyReport();
    }

    @Test
    public void shouldIgnoreReportWhenFirstPlaceCommandHasInvalidYParameter() {
        robot.execute("place 1,Y,NORTH");
        assertEmptyReport();
    }

    @Test
    public void shouldIgnoreReportWhenFirstPlaceCommandHasInvalidDirection() {
        robot.execute("place 1,2,FACING");
        assertEmptyReport();
    }

    @Test
    public void shouldIgnoreReportWhenFirstPlaceCommandIsOutOfBounds() {
        robot.execute("place 0,5,NORTH");
        assertEmptyReport();
    }

    @Test
    public void shouldOutputCurrentPositionWhenReportCommandExecutedAfterValidPlace() {
        robot.execute("place 1,2,NORTH");
        String output = robot.execute("report");
        assertThat(output, is("1, 2, NORTH"));
    }

    @Test
    public void shouldAcceptSpacesBetweenPlaceCommandCommas() {
        robot.execute("place 1, 2, NORTH");
        String output = robot.execute("report");
        assertThat(output, is("1, 2, NORTH"));
    }

    @Test
    public void shouldIgnoreMoveWhenFacingSouthAt00() {
        robot.execute("place 0,0,SOUTH");
        robot.execute("move");
        String output = robot.execute("report");
        assertThat(output, is("0, 0, SOUTH"));
    }

    @Test
    public void shouldIgnoreMoveWhenFacingWestAt00() {
        robot.execute("place 0,0,WEST");
        robot.execute("move");
        String output = robot.execute("report");
        assertThat(output, is("0, 0, WEST"));
    }

    @Test
    public void shouldIgnoreMoveWhenFacingNorthAt44() {
        robot.execute("place 4,4,NORTH");
        robot.execute("move");
        String output = robot.execute("report");
        assertThat(output, is("4, 4, NORTH"));
    }

    @Test
    public void shouldIgnoreMoveWhenFacingEastAt44() {
        robot.execute("place 4,4,EAST");
        robot.execute("move");
        String output = robot.execute("report");
        assertThat(output, is("4, 4, EAST"));
    }

    @Test
    public void shouldMoveForwardWhenMoveCalledAfterPlace00North() {
        robot.execute("place 0,0,NORTH");
        robot.execute("move");
        String output = robot.execute("report");
        assertThat(output, is("0, 1, NORTH"));
    }

    @Test
    public void shouldBeFacingWestWhenLeftCalledAfterPlace00North() {
        robot.execute("place 0,0,NORTH");
        robot.execute("left");
        String output = robot.execute("report");
        assertThat(output, is("0, 0, WEST"));
    }

    @Test
    public void shouldBeFacingEastWhenRightCalledAfterPlace00North() {
        robot.execute("place 0,0,NORTH");
        robot.execute("right");
        String output = robot.execute("report");
        assertThat(output, is("0, 0, EAST"));
    }

    @Test
    public void shouldRemainInOriginalPositionWhenInvalidPlaceCommandFollowsValidPlaceCommand() {
        robot.execute("place 0,0,NORTH");
        robot.execute("place 1,1");
        String output = robot.execute("report");
        assertThat(output, is("0, 0, NORTH"));
    }


}
