package org.firstinspires.ftc.team9374;

/**
 * Created by darwin on 1/8/17.
 */

        import android.widget.TextView;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.LightSensor;
        import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

        import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;


@Autonomous(name="Auto: TB Line Following", group="3543AutoSamples")
//@Disabled
// To be used with clints Test bot.
public class Beta_ODS_LineFollow extends LinearOpMode {
    private static final double MOTOR_POWER = 0.15;
    private static final double LIGHT_THRESHOLD = 0.5;

    private OpticalDistanceSensor ODS;

    private DcMotor motorFLeft;
    private DcMotor motorFRight;
    private DcMotor motorBLeft;
    private DcMotor motorBRight;
    //private TrcDriveBase driveBase;

    //
    // Implements FtcOpMode abstract methods.
    //
    public void runOpMode() throws InterruptedException {
        motorFLeft = hardwareMap.dcMotor.get("dmfl");
        motorBLeft = hardwareMap.dcMotor.get("dmrl");
        motorBRight = hardwareMap.dcMotor.get("dmrr");
        motorFRight = hardwareMap.dcMotor.get("dmfr");

        ODS = hardwareMap.opticalDistanceSensor.get("ODS");

        waitForStart();

        while (super.opModeIsActive()) {
            telemetry.addData("Light Detected:", ODS.getLightDetected());
            telemetry.update();

            double reflection = ODS.getLightDetected();
            //
            // Following the left edge of a white line.
            //
            if (reflection < LIGHT_THRESHOLD)
            {
                //
                // We see the floor, turn right back to the line edge.
                //
                motorFLeft.setPower(MOTOR_POWER);
                motorBLeft.setPower(MOTOR_POWER);
                motorFRight.setPower(0);
                motorBRight.setPower(0);
            }
            else
            {
                //
                // We see the line, turn left back to the line edge.
                //

            }
        }

        }
    }


    //
    // Overrides TrcRobot.RobotMode methods.
    //



