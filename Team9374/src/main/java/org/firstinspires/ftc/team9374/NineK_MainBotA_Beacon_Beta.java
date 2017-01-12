package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by darwin on 12/17/16.
 */
@Autonomous(name = "NineK_MainBotA_Beacon_Beta")
public class NineK_MainBotA_Beacon_Beta extends LinearOpMode {
    Hardware9374 robot = new Hardware9374();

    int foo;


    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);

        robot.init_imu(hardwareMap);

        waitForStart();

        robot.elevator.setPower(0);

        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (super.opModeIsActive()) {
            //Move to center
            robot.moveToPosition(48, .2);

            robot.resetEncoders();
            //Turn to beacon
            robot.Turn(315, .4);

            robot.resetEncoders();
            //Move to beacon
            robot.moveToPosition(48, .3);

            robot.resetEncoders();

            while (true) {
                telemetry.addLine("Made it to loop");
                robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.waitNSeconds(1);
                if (robot.Color_Case() == 11 | robot.Color_Case() == 01 | robot.Color_Case() == 10) {
                    //This is almost all the cases where we see blue
                    robot.moveToPosition(-15, -.3);

                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;
                } else if (robot.Color_Case() == 22 | robot.Color_Case() == 20 | robot.Color_Case() == 02) {
                    robot.moveToPosition(-15, -.3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    robot.moveToPosition(16, .3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                } else {
                    //If we dont see anything. Just abort.
                    stop();
                }

            } //End of the while (true)

            robot.reset_imu();
            //moving us in front of the 2nd beacon
            robot.Turn(90,.1);
            robot.resetEncoders();

            robot.moveToPosition(29,.3);
            robot.resetEncoders();

            robot.Turn(0,.1);
            robot.resetEncoders();

            robot.moveToPosition(16, .2);
            robot.resetEncoders();
            //Shouldnt Get to the beacon, but be in front of it.
            break;
        }
    }
}
