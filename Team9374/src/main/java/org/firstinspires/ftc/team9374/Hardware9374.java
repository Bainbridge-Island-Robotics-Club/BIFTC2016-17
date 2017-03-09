package org.firstinspires.ftc.team9374;

import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.hardware.adafruit.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by darwin on 12/3/16.
 *
 * 9374 Robot class.
 *
 * Here is a reference for how to une this in place of other things.
 *
 *      Hardware9374 robot = new Hardware9374();     // Use a 9K' shardware
 *
 *
 *      robot.init(hardwareMap);
 *
 */

public class Hardware9374 {
    DcMotor left_f;
    DcMotor right_f;
    DcMotor left_b;
    DcMotor right_b;
    //Shooter
    DcMotor shooter_l;
    DcMotor shooter_r;
    //Raising the Fork
    DcMotor forkL;
    DcMotor forkR;
    //Speeds
    CRServo elevator;

    Servo BidentR;
    Servo BidentL;

    ColorSensor CSensorL;

    ColorSensor CSensorR;

    Telemetry telemetry;
    //Controller vaibles
    double lStickY;
    double lStickX;
    double rStickY;
    //Power varibles
    double LFpower;
    double RFpower;
    double LBpower;
    double RBpower;

    int Beacon_Col;

    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();


    final double wheelDiameterInInches = 4;
    final int tpr = 1120;
    final int Color_level = 3;

    static final double MAX_TURNING_POWER = .4; //May be changed based on robot tworke.
    static final double MIN_TURNING_POWER = .15;

    //Not yet defined, will be.

    int ticks;

    BNO055IMU imu;

    public ElapsedTime runTime = new ElapsedTime();

    /* constructor, used when ... = new Hardware9374()  */
    public Hardware9374() {
    }

    //Our init, cannot be called inside the begginning because it is finding our devices.
    public void init(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;
        //Driving motors
        telemetry.addLine("Initilizing drive motors...");
        telemetry.update();
        right_b = hardwareMap.dcMotor.get("Eng1-left");  //Was left_f
        left_b = hardwareMap.dcMotor.get("Eng1-right");//Was Right_f

        right_f = hardwareMap.dcMotor.get("Eng2-left");  //Was left_b
        left_f = hardwareMap.dcMotor.get("Eng2-right");//Was right_b
        //Shooter motors
        telemetry.addLine("Initilizing shooter motors...");
        telemetry.update();
        shooter_r = hardwareMap.dcMotor.get("Eng3-left");
        shooter_l = hardwareMap.dcMotor.get("Eng3-right");

        telemetry.addLine("Initilizing Elevator controll servo");
        telemetry.update();
        elevator = hardwareMap.crservo.get("Ser1-center");

        telemetry.addLine("Initilizing the color sensors...");
        telemetry.update();
        CSensorL = hardwareMap.colorSensor.get("Col1-left");
        CSensorR = hardwareMap.colorSensor.get("Col1-right");
        //First one(Port 1) in the config is the left motor.
        //2nd one(Port 2) In the config is the right motor.
        telemetry.addLine("Initilizing Fork raising motors...");
        telemetry.update();
        forkL = hardwareMap.dcMotor.get("ForkL");
        forkR = hardwareMap.dcMotor.get("ForkR");

        telemetry.addLine("Initilizing Fork release servos");
        telemetry.update();
        BidentL = hardwareMap.servo.get("bil");
        BidentR = hardwareMap.servo.get("bir");

        BidentR.setPosition(1);
        BidentL.setPosition(0);
        //We need to make different addressess because the Sensor are communicating on the same bus.

        //In order to set up two light sensors we need to make shure that we talk to the exact
        //Light sensor that we want. In THis case we are saying
        //"This light sensor object should be talked to with this I2C Addrecc
        telemetry.addLine("Configuring The light sensors...");
        telemetry.update();
        CSensorR.setI2cAddress(I2cAddr.create8bit(100));
        CSensorL.setI2cAddress(I2cAddr.create8bit(112));

        CSensorL.enableLed(false);
        CSensorR.enableLed(false);

        //center = hardwareMap.servo.get("Ser1-center");

        //Might be deprecated
        telemetry.addLine("Configuring The Drive Motors");
        telemetry.update();

        left_b.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_f.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_b.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_f.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left_f.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_f.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right_f.setDirection(DcMotorSimple.Direction.REVERSE);
        right_b.setDirection(DcMotorSimple.Direction.REVERSE);

        //shooter_l.setDirection(DcMotorSimple.Direction.REVERSE);

        shooter_r.setDirection(DcMotorSimple.Direction.REVERSE);
        //Giving our elevator a start position.
        //START THE CLOCK

        //left.setDirection(DcMotorSimple.Direction.REVERSE);//Or .FORWARD
        //--------------------------------------------------------------------------------------
        //End of Robot init code.
        //--------------------------------------------------------------------------------------

        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.

        //--------------------------------------------------------------------------------------
        //IMU code
        //--------------------------------------------------------------------------------------

        // Set up our telemetry dashboard
        //START THE CLOCK...Again
        telemetry.addLine("Finished init!");
        telemetry.update();
        runTime.reset();

    }

    //-------------------------------------------------------
    //                      Action Functions
    //-------------------------------------------------------
    public void init_imu(HardwareMap hardwareMap) {
        telemetry.addLine("Initilizing the IMU");
        telemetry.update();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;               // Defining units
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;  // Defining units
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

    }

    public void TurnBeta(int THeading, double speed) {
        //Stands for THeading
        //EX: change = 90 and speed is 0.
        /*
        I am acutally really proud of myself for this method.
        This method moves the robot a certain amount of degrees.
        //-------------------------
        //True  = Counter-Clockwise
        //False = Clockwise
        //-------------------------
        */
        //everything needs to be in integers.

        //This took a lot of time to come up with one number
        //Just saying.
        telemetry.addData("Begginning to turn to...", THeading);
        telemetry.update();

        left_b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_f.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_b.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_f.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double heading = getcurrentheading(); //38

        double target = heading + THeading;

        //Making shure that the target is not over 360
        if (target > 360) {
            target = target - 360;
        }
        //Not going to bother to do this logic
        left_b.setPower(speed);
        left_f.setPower(speed);
        right_b.setPower(-speed);
        right_f.setPower(-speed);

        while (true) {

            heading = getcurrentheading();
            telemetry.addData("Current Heading:", heading);
            telemetry.addData("Target heading", target);
            if (heading < target + 1 && heading > target - 1) { //Should be withen 10 of the target.
                break;
            }
        }

        resetEncoders();

    }

    //Unknown
    public static double calculateDelta(double targetHeading, double currentHeading) {

        double ih = normalize(targetHeading);
        double ch = normalize(currentHeading);

        return normalize(ih - ch);

    }

    //Function to return the heading in a 180 to -180 range.
    public static double getCurrentHeading_180(BNO055IMU imu) {
        return AngleUnit.normalizeDegrees(imu.getAngularOrientation()
                .toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZXY).firstAngle);
    }

    //Keep in mind that this is in -180 to 180, not
    //The  360 system that me and cory have done.
    public void Turn(double degreesRequest) {
        //-------------------CLINTS TURN---------------------------//
        //Obdious
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Begginning to turn to...", degreesRequest);

        double initialHeading = getCurrentHeading_180(imu);
        double targetHeading = normalize(initialHeading + normalize(degreesRequest));

        while (true) {
            double delta = calculateDelta(targetHeading, getCurrentHeading_180(imu));
            telemetry.addData("Current heading is... ", getCurrentHeading_180(imu));
            telemetry.update();
            if (Math.abs(delta) < 1) {
                setALLpower(0);
                resetEncoders();
                break;
            }

            double power = Math.abs(delta / degreesRequest);
            power = power * power;
            power = Range.clip(power, MIN_TURNING_POWER, MAX_TURNING_POWER);
            //setALLpower(power * Math.signum(delta));
            left_b.setPower(-power * Math.signum(delta));
            left_f.setPower(-power * Math.signum(delta));
            //setALLpower(power * Math.signum(delta));
            right_b.setPower(power * Math.signum(delta));
            right_f.setPower(power * Math.signum(delta));

            telemetry.addData("Current heading is... ", getCurrentHeading_180(imu));
            telemetry.addData("target heading is... ", targetHeading);
            telemetry.addData("power", power);
            telemetry.update();
        }
    }

    //Takes The Wheel diamater and distance. Turns it into tick
    public int calcClicksForInches(double distanceInInches) {
        //Currently there are 1120 different positions on any given wheel
        double revlutions = distanceInInches / (wheelDiameterInInches * Math.PI); //Find out how many revolutations
        int clicks = (int) (revlutions * tpr); //This is a pretty big number, gonna be in the 1,000's
        return clicks; //The position to set the wheels to.
    }

    public void moveToPosition(double distanceInIN, double power) {
        telemetry.addLine("Moving to position...");
        telemetry.update();
        setALLposition(calcClicksForInches(distanceInIN));
        setALLpower(power);
        while (true) {
            if (calcClicksForInches(distanceInIN) < 0) {
                telemetry.addData("Waiting untill we get to...", calcClicksForInches(distanceInIN));
                telemetry.update();
                if (left_f.getCurrentPosition() < calcClicksForInches(distanceInIN)) {
                    //Stop
                    resetEncoders();
                    setALLpower(0);

                    break;
                }
            } else if (calcClicksForInches(distanceInIN) > 0) {
                telemetry.addData("Waiting untill we get to...", calcClicksForInches(distanceInIN));
                telemetry.update();
                if (left_f.getCurrentPosition() > calcClicksForInches(distanceInIN)) {
                    resetEncoders();
                    setALLpower(0);

                    break;
                }
            }
        }
    }


    public void setALLpower(double power) {
        left_b.setPower(power);
        left_f.setPower(power);
        right_b.setPower(power);
        right_f.setPower(power);
    }

    public void setALLposition(int position) {
        left_b.setTargetPosition(position);
        left_f.setTargetPosition(position);
        right_b.setTargetPosition(position);
        right_f.setTargetPosition(position);

    }

    public void resetEncoders() {
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public double getcurrentheading() {
        double angles;
        angles = AngleUnit.DEGREES.normalize(imu.getAngularOrientation()
                .toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).firstAngle);
        //If its negetive then subtract
        if (angles < 0) {
            angles = 360 + angles;
        }
        return angles;

    }

    public void setMode(DcMotor.RunMode mode) {
        left_b.setMode(mode);
        left_f.setMode(mode);
        right_b.setMode(mode);
        right_f.setMode(mode);
    }

    public void waitNSeconds(int secondsToWait) {
        double startTime = runTime.time();
        while (runTime.time() - startTime < secondsToWait) {

        }
    }

    public void reset_imu() {
        //Should reset the encoder. Has not been tested.
        imu.initialize(parameters);
    }

    //Coach might have done some sort of enum thing with this
    /*
    public int Color_Case() {
        //Im going to do this by a number basis.
        // 2 = red
        // 1 = blue
        // 0 =  unknown
        if ((CSensorL.red() > Color_level) &
                (CSensorR.red() > Color_level) &
                (CSensorR.blue() < Color_level) &
                (CSensorL.blue() < Color_level)) {
            return 22;
        } else if (CSensorL.red() > Color_level & CSensorL.blue() < Color_level & CSensorR.red() < Color_level & CSensorR.blue() > Color_level) {
            return 21;
        } else if (CSensorL.blue() > Color_level & CSensorR.blue() > Color_level) {
            return 11;
        } else if (CSensorL.blue() < Color_level & CSensorL.red() < Color_level & CSensorR.blue() < Color_level) {
            return 00;
        } else if (CSensorL.blue() > Color_level & CSensorR.blue() < Color_level & CSensorR.red() < Color_level) {
            return 10;
        } else if (CSensorL.blue() < Color_level & CSensorL.red() < Color_level & CSensorR.blue() > Color_level) {
            return 01;
        } else if (CSensorL.red() > Color_level & CSensorR.red() < Color_level & CSensorR.blue() < Color_level) {
            return 20;
        } else if (CSensorL.blue() > Color_level & CSensorR.red() > Color_level) {
            return 12;
        } else if (CSensorL.blue() < Color_level & CSensorL.red() < Color_level & CSensorR.red() > Color_level & CSensorR.blue() < Color_level) {
            return 02;
        } else {
            return 0;
        }

    }
    */
    public void Still_shoot(int delay) {
        waitNSeconds(delay);
        while (true) {
            shooter_l.setPower(1);
            shooter_r.setPower(1);
            if (runTime.time() > 5) {
                elevator.setPower(-1);
                if (runTime.time() > 15) {
                    break;
                }
            }
        }
    }

    public static double normalize(double angle) {
        return AngleUnit.normalizeDegrees(angle);
    }

    public int Color_Case() {
        //Im going to do this by a number basis.
        // 2 = red
        // 1 = blue
        // 0 =  unknown
        Beacon_Col = 0;
            //Left side
        if (CSensorL.red() > Color_level) {
            Beacon_Col += 20;
        }
        if (CSensorL.blue() > Color_level) {
            Beacon_Col += 10;
            //Right side.
        }
        if (CSensorR.red() > Color_level) {
            Beacon_Col += 2;
        }
        if (CSensorR.blue() > Color_level) {
            Beacon_Col += 1;
        }
        return Beacon_Col;
    }
}


