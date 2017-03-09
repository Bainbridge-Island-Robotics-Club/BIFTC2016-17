package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * Created by darwin on 9/20/16.
 *
 *
  *  This OpMode is made to drive all 4 of the test bots.
 */

@TeleOp(name="Basic Tank Drive!", group="TeleOp_Opmode")

public class Tank_Drive extends OpMode {
    DcMotor left;   //Getting both of our motors
    DcMotor right;

    Servo serv;     //Getting the one servo
    public void init()  {
        left = hardwareMap.dcMotor.get("Motor-left");
        right = hardwareMap.dcMotor.get("Motor-right");
        //serv = hardwareMap.servo.get("Servo");
    }

    @Override
    public void loop() {
        float leftDC = gamepad1.left_stick_y;
        float rightDC =  gamepad1.right_stick_y;

        float RTrigger = gamepad1.right_trigger;

        //serv.setPosition(RTrigger); //Setting the servo to the right trigger
        left.setPower(-leftDC);      //Setting the left motor to the left joystick

        right.setPower(rightDC);    //Setting the right motor to the right joystick



    }


}
