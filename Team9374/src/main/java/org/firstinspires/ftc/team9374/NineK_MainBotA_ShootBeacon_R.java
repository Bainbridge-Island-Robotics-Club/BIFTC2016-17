package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by darwin on 12/17/16.
 */
@Autonomous(name = "NineK_MainBotA_ShootBeacon_R")
public class NineK_MainBotA_ShootBeacon_R extends LinearOpMode {
    Hardware9374 robot = new Hardware9374();

    int foo;

    double poo = Math.random();
    int RNG = (int) poo * 2 + 1;
    @Override public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);

        robot.init_imu(hardwareMap);


        waitForStart();

        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (super.opModeIsActive()){
            robot.Still_shoot(0);
            //Move to center
            robot.moveToPosition(48,.2);

            robot.resetEncoders();
            //Turn to beacon
            robot.Turn(135,.4);

            robot.resetEncoders();
            //Move to beacon
            robot.moveToPosition(48,.3);

            robot.resetEncoders();

            while (true) {
                telemetry.addLine("Made it to loop");
                robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.waitNSeconds(1);
                if (robot.CSensorR.blue() > 4) {

                    robot.moveToPosition(-15, -.3);

                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    break;
                } else if (robot.CSensorR.red() > 4){
                    robot.moveToPosition(-15, -.3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    robot.moveToPosition(20, .3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                } else {
                    //Chaning our eading just a little bit
                    robot.Turn(320,.1);

                    robot.moveToPosition(-15, -.3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    robot.moveToPosition(20, .3);
                    robot.resetEncoders();

                    robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                }

            }

            break;
        }
    }
}
