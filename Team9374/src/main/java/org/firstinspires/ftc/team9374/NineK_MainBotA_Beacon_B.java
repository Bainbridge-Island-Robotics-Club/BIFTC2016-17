package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by darwin on 12/17/16.
 */
@Autonomous(name = "NineK_MainBotA_Beacon_B2v")
public class NineK_MainBotA_Beacon_B extends LinearOpMode {
    Hardware9374 robot = new Hardware9374();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);

        robot.init_imu(hardwareMap);

        waitForStart();

        robot.elevator.setPower(0);

        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (super.opModeIsActive()) {
            //Move to center
            robot.moveToPosition(45, .2);

            robot.resetEncoders();
            //Turn to beacon
            //This is counter-clockwise
            robot.Turn(90);

            robot.resetEncoders();
            //Move to beacon
            robot.moveToPosition(48, .4);

            robot.resetEncoders();

            while (true) {
                telemetry.addLine("Made it to loop");
                robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.waitNSeconds(1);
                if (robot.Color_Case() == 11 | robot.Color_Case() == 1 | robot.Color_Case() == 10) {
                    telemetry.log().add("I see blue...");
                    telemetry.update();
                    robot.moveToPosition(-15, -.4);

                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;
                } else if (robot.Color_Case() == 22 | robot.Color_Case() == 20 | robot.Color_Case() == 2)  {
                    telemetry.log().add("I see red...");
                    telemetry.update();
                    robot.moveToPosition(-15, -.4);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    robot.moveToPosition(16, .3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                }

            } //End of the while (true)


            break;
        }
    }
}
