package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by darwin on 12/17/16.
 */
@Autonomous(name = "NineK_MainBotA_ShootBeacon_B")
public class NineK_MainBotA_ShootBeacon_B extends LinearOpMode {
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
            robot.Still_shoot(0);
            //Move to center
            robot.moveToPosition(48, .2);

            robot.resetEncoders();
            //Turn to beacon
            robot.Turn(315, .4);

            robot.resetEncoders();
            //Move to beacon
            robot.moveToPosition(48, .4);

            robot.resetEncoders();

            while (true) {
                telemetry.addLine("Made it to loop");
                robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.waitNSeconds(1);
                if (robot.CSensorR.blue() > 4) {

                    robot.moveToPosition(-15, -.4);

                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;
                } else  {
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
