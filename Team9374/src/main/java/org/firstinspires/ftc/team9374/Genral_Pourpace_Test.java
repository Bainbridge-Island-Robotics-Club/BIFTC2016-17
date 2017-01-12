package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.I2cAddr;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by darwin on 1/8/17.
 */
//The idea is to rename what is is regestered as a
@TeleOp(name = "Testing Clints Turn Code.")
public class Genral_Pourpace_Test extends LinearOpMode {
    Hardware9374 robot = new Hardware9374();
    public void runOpMode() throws InterruptedException{

        robot.init(hardwareMap,telemetry);
        robot.init_imu(hardwareMap);

        robot.TurnBeta(90);
    }
}
