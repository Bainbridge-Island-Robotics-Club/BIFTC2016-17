package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/*
 * Created by darwin on 10/29/16.
 */
@Autonomous(name = "NineK_MainBotA_StillShoot",group = "null")

public class NineK_MainBotA_StillStoot extends LinearOpMode {

    Hardware9374 robot = new Hardware9374();


    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap,telemetry);

        robot.init_imu(hardwareMap);

        super.waitForStart();

        robot.moveToPosition(5,-.4);

        while (super.opModeIsActive()) {
            //Spinning the motors
            while (true) {
                robot.shooter_l.setPower(1);
                robot.shooter_r.setPower(1);

                //Shoots twice, still needs to be tested
                if (robot.runTime.time() > 5) {
                    robot.elevator.setPower(-.5);
                    if (robot.runTime.time() > 7) {
                        robot.elevator.setPower(0);
                        if (robot.runTime.time() > 10){
                            robot.elevator.setPower(-1);
                        }
                    }

                    break;
                }

            }
        }
    }
}

