package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
<<<<<<< HEAD
=======
import com.qualcomm.robotcore.util.Range;
>>>>>>> 8e526624d02aa2483d95810cc66c4a6aa67d279c

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by some guy "named" Nintendo8 on 11/6/2016.
 */
<<<<<<< HEAD
//@Disabled //For now...
@TeleOp(name="8K_Tank_Shooter")

public class TankDriveShooter extends OpMode{

    DcMotor leftFRONT;
    DcMotor rightFRONT;
    DcMotor leftBACK;
    DcMotor rightBACK;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    Servo shooterServo;
    long startTime = 0;

    final double kServoNullPosition = 0.8;
    final double kServoRange = 0.6;
    final double kShootPower = 0.7;

    public void init() {
        //Front Motors
        leftFRONT = hardwareMap.dcMotor.get("motor-left");
        rightFRONT = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        leftBACK = hardwareMap.dcMotor.get("motor-leftBACK");
        rightBACK = hardwareMap.dcMotor.get("motor-rightBACK");
=======

@TeleOp(name="8K_Tank_Shooter")

public class TankDriveShooter extends OpMode {

    DcMotor left_f;
    DcMotor right_f;
    DcMotor left_b;
    DcMotor right_b;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    Servo shooterServo;
    double speedFactor;
    long lastTime = System.currentTimeMillis();
    long lsTicks = 0;
    long rsTicks = 0;


    final double kServoNullPosition = 0.8;
    final double kServoRange = 0.6;
    double kShootPower = .7;

    public void init() {
        //Front Motors
        left_f = hardwareMap.dcMotor.get("motor-left");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        left_b = hardwareMap.dcMotor.get("motor-leftBACK");
        right_b = hardwareMap.dcMotor.get("motor-rightBACK");
>>>>>>> 8e526624d02aa2483d95810cc66c4a6aa67d279c

        //Shooters
        shooterRight = hardwareMap.dcMotor.get("shooter-right");
        shooterLeft = hardwareMap.dcMotor.get("shooter-left");
        //Servos
        shooterServo = hardwareMap.servo.get("shooter-servo");


        //Reverse Mode
<<<<<<< HEAD
        leftFRONT.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBACK.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    @Override
    public void loop() {
        float leftDC = gamepad1.left_stick_y;
        float rightDC = gamepad1.right_stick_y;
        leftFRONT.setPower(leftDC);
        rightFRONT.setPower(rightDC);
        leftBACK.setPower(leftDC);
        rightBACK.setPower(rightDC);

=======
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // speed factor
        speedFactor = 1.0;
    }

    @Override
    public void loop() {
        //float leftDC = gamepad1.left_stick_y;
        //float rightDC = gamepad1.right_stick_y;
       /* leftFRONT.setPower(leftDC);
        rightFRONT.setPower(rightDC);
        leftBACK.setPower(leftDC);
        rightBACK.setPower(rightDC);
       */
>>>>>>> 8e526624d02aa2483d95810cc66c4a6aa67d279c
        float rightTrigger = gamepad2.right_trigger;
        boolean rightBumperPressed = gamepad2.right_bumper;
        float leftTrigger = gamepad2.left_trigger;

        //shooterServo.setPosition(0.1);
        shooterServo.setPosition((leftTrigger * (-kServoRange)) + kServoNullPosition);
<<<<<<< HEAD
        //telemetry.addData("Servo Position", shooterServo.getPosition());

        if (rightBumperPressed) {
            shooterLeft.setPower(1.0);
            shooterRight.setPower(1.0);
        } else {
            shooterRight.setPower(rightTrigger * kShootPower);
            shooterLeft.setPower(rightTrigger * kShootPower);
        }

            boolean up = gamepad1.dpad_up;
            boolean down = gamepad1.dpad_down;
            if (up){
                leftBACK.setPower(0.5);
                leftFRONT.setPower(0.5);
                rightBACK.setPower(0.5);
                rightFRONT.setPower(0.5);
            }
            if (down){
                leftBACK.setPower(-0.5);
                leftFRONT.setPower(-0.5);
                rightBACK.setPower(-0.5);
                rightFRONT.setPower(-0.5);
            }
            if (!up){
                if(!down){
                    leftBACK.setPower(gamepad1.left_stick_y);
                    leftFRONT.setPower(gamepad1.left_stick_y);
                    rightBACK.setPower(gamepad1.right_stick_y);
                    rightFRONT.setPower(gamepad1.right_stick_y);}}
                }
            }
=======
        telemetry.addData("Servo Position", shooterServo.getPosition());

//        if (rightBumperPressed) {
//            shooterLeft.setPower(1.0);
//            shooterRight.setPower(1.0);
//        } else {
//            shooterRight.setPower(rightTrigger * kShootPower);
//            shooterLeft.setPower(rightTrigger * kShootPower);
//        }

        boolean up = gamepad2.dpad_up;
        boolean down = gamepad2.dpad_down;

        telemetry.addData("Up", up);
        telemetry.addData("Down", down);
        if (up) {
            kShootPower = Range.clip(kShootPower + .05, .2, 1);
        }
        if (down) {
            kShootPower = Range.clip(kShootPower - .05, .2, 1);
        }

        shooterRight.setPower(rightTrigger * kShootPower);
        shooterLeft.setPower(rightTrigger * kShootPower);

        long currentTime = System.currentTimeMillis();
        double deltat = Math.max(1, currentTime - lastTime);
        lastTime = currentTime;

        double lcps = (shooterLeft.getCurrentPosition() - lsTicks) / deltat;
        lsTicks = shooterLeft.getCurrentPosition();

        double rcps = (shooterRight.getCurrentPosition() - rsTicks) / deltat;
        rsTicks = shooterRight.getCurrentPosition();

        telemetry.addData("leftShooterSpeed", String.format("%.2f", lcps));
        telemetry.addData("rightShooterSpeed", String.format("%.2f", rcps));
        telemetry.addData("shooterPower", kShootPower);
        telemetry.addData("deltaT", deltat);
        telemetry.update();
        left_b.setPower(gamepad1.left_stick_y);
        left_f.setPower(gamepad1.left_stick_y);
        right_b.setPower(gamepad1.right_stick_y);
        right_f.setPower(gamepad1.right_stick_y);

    }
}


>>>>>>> 8e526624d02aa2483d95810cc66c4a6aa67d279c




