package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/*
 * Created by darwin on 10/29/16.
 */
@Autonomous(name = "NineK_MainBotA_Center_BR",group = "null")
//This is shoot, then center.
public class NineK_MainBotA_Center_BR extends LinearOpMode {

    Hardware9374 robot = new Hardware9374();


    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap, telemetry);

        robot.init_imu(hardwareMap);

        robot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.waitForStart();



        //Spinning the motors
        while (true) {
            robot.shooter_l.setPower(1);
            robot.shooter_r.setPower(1);
            if (robot.runTime.time() > 5) {
                robot.elevator.setPower(-1);
                if (robot.runTime.time() > 10) {
                    break;
                }
            }
        }

        //move 55 inches
        robot.moveToPosition(55,.6);




    }
}
