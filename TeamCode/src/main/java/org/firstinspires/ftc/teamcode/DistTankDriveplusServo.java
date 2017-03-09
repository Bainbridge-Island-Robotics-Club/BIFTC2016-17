package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static android.R.attr.left;

/*
 * Created by darwin on 9/20/16.
 */

@TeleOp(name="Basic Tank Drive!", group="Noramal_Opmode")
@Disabled


public class DistTankDriveplusServo extends OpMode {
    DcMotor left;
    DcMotor right;

    ColorSensor sensor;

    Servo becon_pusher;

    public void init()  {
        left = hardwareMap.dcMotor.get("Motor-left");
        right = hardwareMap.dcMotor.get("Motor-right");
    }

    @Override             //method in opmode that I am implementing
    public void loop() {  //Do this thing over and over again
        float leftDC = gamepad1.left_stick_y;
        float rightDC =  gamepad1.right_stick_y;

        left.setPower(leftDC);
        right.setPower(rightDC);

        becon_pusher.setPosition(gamepad1.left_trigger);

        telemetry.addData("Color_sensor",sensor);


    }
}

