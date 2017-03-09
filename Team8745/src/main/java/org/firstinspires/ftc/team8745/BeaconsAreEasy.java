package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by rose on 11/25/16.
 */

@Disabled
@TeleOp(name="8K Slow Mode")

public class BeaconsAreEasy extends OpMode {
    DcMotor left_b;
    DcMotor left_f;
    DcMotor right_b;
    DcMotor right_f;

    public void init() {
        //right
        right_b = hardwareMap.dcMotor.get("motor-rightBACK");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //left
        left_b = hardwareMap.dcMotor.get("motor-leftBACK");
        left_f = hardwareMap.dcMotor.get("motor-left");

        //reverse mode
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);}

    @Override
    public void loop() {
        float r_stick = gamepad1.right_stick_y;
        float l_stick = gamepad1.left_stick_y;
        boolean up = gamepad1.dpad_up;
        boolean down = gamepad1.dpad_down;

        //Tank

        if (l_stick>0) {
            left_b.setPower(l_stick);
            left_f.setPower(l_stick);
            right_b.setPower(r_stick);
            right_f.setPower(r_stick);
        }
        else {if (up){
            left_b.setPower(.3333);
            left_f.setPower(.3333);
            right_b.setPower(.3333);
            right_f.setPower(.3333);
        }
            if (down){
                left_b.setPower(-.3333);
                left_f.setPower(-.3333);
                right_b.setPower(-.3333);
                right_f.setPower(-.3333);
            }
        }
        if (r_stick>0) {
            left_b.setPower(l_stick);
            left_f.setPower(l_stick);
            right_b.setPower(r_stick);
            right_f.setPower(r_stick);
        }
        else {if (up){
            left_b.setPower(.3333);
            left_f.setPower(.3333);
            right_b.setPower(.3333);
            right_f.setPower(.3333);
        }
            if (down){
                left_b.setPower(-.3333);
                left_f.setPower(-.3333);
                right_b.setPower(-.3333);
                right_f.setPower(-.3333);
            }
        }


    }


}