package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by rose on 12/7/16.
 */

@TeleOp (name="Squared Speed" )

public class Square extends OpMode {
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

    float l_square = gamepad1.left_stick_y*gamepad1.left_stick_y;
    float r_square = gamepad1.right_stick_y*gamepad1.right_stick_y;

    boolean up = gamepad1.dpad_up;
    boolean down = gamepad1.dpad_down;

    if (!down){
    left_b.setPower(l_stick);
    left_f.setPower(l_stick);
    right_b.setPower(r_stick);
    right_f.setPower(r_stick);}
    else {
        left_b.setPower(l_square);
        left_f.setPower(l_square);
        right_b.setPower(r_square);
        right_f.setPower(r_square);
    }



    }}
