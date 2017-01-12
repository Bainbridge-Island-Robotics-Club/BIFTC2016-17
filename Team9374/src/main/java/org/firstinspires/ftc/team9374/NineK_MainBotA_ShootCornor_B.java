package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Locale;

/**
 * Created by darwin on 1/9/17.
 */
@Autonomous(name = "NineK_MainBotA_ShootCornor_B", group = "BlueA")
public class NineK_MainBotA_ShootCornor_B extends LinearOpMode{

    // Uncomment this to add to the opmode list

    //----------------------------------------------------------------------------------------------
    // State Varibles
    //----------------------------------------------------------------------------------------------

    // The IMU sensor object
    Hardware9374 robot = new Hardware9374();
    // Please note that this needs to be changed for any wheel size that we decide to use


    //------------------------------------------------------------------------------------------
    // Main logic
    //------------------------------------------------------------------------------------------

    @Override public void runOpMode() throws InterruptedException {
        //--------------------------------------------------------------------------------------
        //Bunch of Robot Initilaztion code
        //--------------------------------------------------------------------------------------
        robot.init(hardwareMap,telemetry);

        robot.init_imu(hardwareMap);

        // Wait until we're told to go
        waitForStart();

        // Start the logging of measured acceleration

        // Wait until we're told to go

        // Loop and update the dashboard
        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while (opModeIsActive()) {
            //Getting our heading into telemetry

            robot.Still_shoot(10);

            //Changed to a wacky num because 55/1.6 due to wheel faisco

            robot.moveToPosition(12.5,.5);

            robot.resetEncoders();

            robot.Turn(290,.3);

            robot.resetEncoders();
            //Changed to a wacky num because 55/1.6 due to wheel faisco

            robot.moveToPosition(6.25,.5);
            //robot.Turn(180,.4);

            break;



        }

    }
    //imu.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX)
    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

}
