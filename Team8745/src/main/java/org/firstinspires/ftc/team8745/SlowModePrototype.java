package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/*
created by rose on 12/14
 */

@Disabled

@TeleOp(name="8K_Tank_Shooter")

public class SlowModePrototype extends OpMode{

    DcMotor left_f;
    DcMotor right_f;
    DcMotor left_b;
    DcMotor right_b;
    DcMotor shooterLeft;
    DcMotor shooterRight;
    Servo shooterServo;

    long lastTime = System.currentTimeMillis();
    long lsTicks = 0;
    long rsTicks = 0;
    public static final double full = 1.0;
    public static final double slow = 0.333333;

    // speed factor
    double speedFactor = full;

    final double kServoNullPosition = 0.8;
    final double kServoRange = 0.6;
    double kShootPower = 0.65;

    public void init() {
        //Front Motors
        left_f = hardwareMap.dcMotor.get("motor-left");
        right_f = hardwareMap.dcMotor.get("motor-right");

        //Back Motors
        left_b = hardwareMap.dcMotor.get("motor-left_b");
        right_b = hardwareMap.dcMotor.get("motor-right_b");

        //Shooters
        shooterRight = hardwareMap.dcMotor.get("shooter-right");
        shooterLeft = hardwareMap.dcMotor.get("shooter-left");
        //Servos
        shooterServo = hardwareMap.servo.get("shooter-servo");


        //Reverse Mode
        left_f.setDirection(DcMotorSimple.Direction.REVERSE);
        left_b.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    @Override
    public void loop() {

        float r_stick = gamepad1.right_stick_y;
        float l_stick = gamepad1.left_stick_y;

        float rightTrigger = gamepad2.right_trigger;
        boolean rightBumperPressed = gamepad2.right_bumper;
        float leftTrigger = gamepad2.left_trigger;
        boolean up = gamepad2.dpad_up;
        boolean down = gamepad2.dpad_down;

        //shooterServo.setPosition(0.1);
        shooterServo.setPosition((leftTrigger * (-kServoRange)) + kServoNullPosition);
        telemetry.addData("Servo Position", shooterServo.getPosition());

        left_f.setPower(l_stick*speedFactor);
        right_f.setPower(r_stick*speedFactor);
        left_b.setPower(l_stick*speedFactor);
        right_b.setPower(r_stick*speedFactor);


        telemetry.addData("Up",up);
        telemetry.addData("Down",down);
        if (up){
            kShootPower = Range.clip( kShootPower+ .05, .2, 1);
        }
        if (down){
            kShootPower = Range.clip( kShootPower- .05, .2, 1);
        }

        shooterRight.setPower( rightTrigger * kShootPower);
        shooterLeft.setPower( rightTrigger* kShootPower);

        long currentTime = System.currentTimeMillis();
        double deltat = Math.max(1,currentTime-lastTime);
        lastTime = currentTime;

        double lcps = (shooterLeft.getCurrentPosition() - lsTicks)/deltat;
        lsTicks = shooterLeft.getCurrentPosition();

        double rcps = (shooterRight.getCurrentPosition() - rsTicks)/deltat;
        rsTicks = shooterRight.getCurrentPosition();

        telemetry.addData("leftShooterSpeed", String.format("%.2f", lcps));
        telemetry.addData("rightShooterSpeed", String.format("%.2f", rcps));
        telemetry.addData("shooterPower",  kShootPower);
        telemetry.addData("deltaT", deltat);
        telemetry.update();

    }
}





