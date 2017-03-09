package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by some guy "named" Nintendo8 on 11/13/2016.
 */

@TeleOp(name="9K Cap Ball Lift+Drive")

public class Cap_Ball_Lifter extends OpMode {
    Servo ballMove;
    /*DcMotor left_f;
    DcMotor left_b;
    DcMotor right_f;
    DcMotor right_b;*/
    public void init() {
        ballMove = hardwareMap.servo.get("servoBall");
        /*//Front Motors
        left_f = hardwareMap.dcMotor.get("Eng1-left");
        right_f = hardwareMap.dcMotor.get("Eng2-right");

        //Back Motors
        left_b = hardwareMap.dcMotor.get("Eng2-left");
        right_b = hardwareMap.dcMotor.get("Eng2-right");

        //Shooters
        //shooterRight = hardwareMap.dcMotor.get("shooter-right");
        //shooterLeft = hardwareMap.dcMotor.get("shooter-left");

        //Reverse Mode
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);*/
    }
    public void loop() {
        float servo = Math.abs(gamepad2.right_trigger-1);
        float rightDC = gamepad1.right_stick_y;
        float leftDC = gamepad1.left_stick_y;

    ballMove.setPosition(servo);

    //left_f.setPower(leftDC);
    //left_b.setPower(leftDC);
    //right_f.setPower(rightDC);
    //right_b.setPower(rightDC);

    }

}

