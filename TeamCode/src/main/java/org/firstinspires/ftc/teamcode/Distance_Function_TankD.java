package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * Created by darwin on 9/20/16.
 */

@TeleOp(name="Basic Tank Drive!", group="Noramal_Opmode")
@Disabled

public class Distance_Function_TankD extends OpMode {
    DcMotor left;
    DcMotor right;

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
        //if(leftDCy > 0)



    }
}

