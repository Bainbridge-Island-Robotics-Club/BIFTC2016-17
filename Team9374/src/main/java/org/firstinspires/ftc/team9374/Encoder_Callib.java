package org.firstinspires.ftc.team9374;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by darwin on 12/4/16.
 */
@TeleOp(name = "encoder calib")
@Disabled
public class Encoder_Callib extends OpMode {

    Hardware9374 robot = new Hardware9374();

    public void init() {
        robot.init(hardwareMap, telemetry);
    }

    public void loop(){
        double lStickY = gamepad1.left_stick_y;
        double rSticky = gamepad1.right_stick_y;

        robot.left_b.setPower(lStickY);
        robot.left_f.setPower(lStickY);

        robot.right_f.setPower(rSticky);
        robot.right_b.setPower(rSticky);

        if (gamepad1.a) {
            robot.resetEncoders();
        }
        if (gamepad1.y){
            robot.reset_imu();
        }

        telemetry.addData("Color sensor value (R)", robot.CSensorR.red());
        telemetry.addData("Color sensor value (B)", robot.CSensorR.blue());
    }

}
