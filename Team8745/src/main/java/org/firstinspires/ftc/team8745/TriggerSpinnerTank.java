package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by some guy "named" Nintendo8 on 11/6/2016.
 */

@TeleOp(name="8K_Trigger_NoShooter")
@Disabled

public class TriggerSpinnerTank extends OpMode{

    DcMotor leftFRONT;
    DcMotor rightFRONT;
    DcMotor leftBACK;
    DcMotor rightBACK;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    Servo shooterFeeder;

    long startTime = 0;
    public void init() {
        // Servo
        shooterFeeder = hardwareMap.servo.get("shooter-servo");

        //Front Motors
        leftFRONT = hardwareMap.dcMotor.get("motor-left");
        rightFRONT = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        leftBACK = hardwareMap.dcMotor.get("motor-leftBACK");
        rightBACK = hardwareMap.dcMotor.get("motor-rightBACK");

        //Shooters
        shooterRight = hardwareMap.dcMotor.get("shooter-right");
        shooterLeft = hardwareMap.dcMotor.get("shooter-left");

        //Reverse Mode
        leftFRONT.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBACK.setDirection(DcMotorSimple.Direction.REVERSE);

        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
        float leftDC = gamepad1.left_stick_y;
        float rightDC = gamepad1.right_stick_y;
        float right_trigger1 = gamepad1.right_trigger;
        float left_trigger = gamepad1.left_trigger;
        float right_trigger2 = gamepad2.right_trigger;
        boolean rightBumper2 = gamepad2.right_bumper;
        boolean leftBumper2 = gamepad2.left_bumper;



        if (rightBumper2) {
            shooterLeft.setPower(1);
            shooterRight.setPower(1);
        }
        else {
            shooterRight.setPower(right_trigger2);
            shooterLeft.setPower(right_trigger2);
        }
    if (right_trigger1>0){
        rightFRONT.setPower(right_trigger1);
        rightBACK.setPower(right_trigger1);
        leftFRONT.setPower(-right_trigger1);
        leftBACK.setPower(-right_trigger1);
    }
    else {
    if (left_trigger==0){
        leftFRONT.setPower(leftDC);
        rightFRONT.setPower(leftDC);
        leftBACK.setPower(leftDC);
        rightBACK.setPower(leftDC);}

    else {
    rightFRONT.setPower(-left_trigger);
    rightBACK.setPower(-left_trigger);
    leftBACK.setPower(left_trigger);
    leftFRONT.setPower(left_trigger);

    }
    }
        if (leftBumper2)
        {
            if (right_trigger2>0) {
                shooterFeeder.setPosition(1);
            }
        else{
        shooterFeeder.setPosition(0);    }
        }
        else {
            shooterFeeder.setPosition(0);
        }
    }
}
