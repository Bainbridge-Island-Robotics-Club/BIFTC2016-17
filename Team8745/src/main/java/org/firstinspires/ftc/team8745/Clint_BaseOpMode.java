package  org.firstinspires.ftc.team8745;

import com.qualcomm.hardware.adafruit.BNO055IMU;
import com.qualcomm.hardware.adafruit.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Arrays;

public abstract class BaseLinearOp extends LinearOpMode {
    static final int TICS_PER_REV = 1120;
    static final double WHEEL_DIAMETER = 4;

    static final double MAX_TURNING_POWER = .5;
    static final double MIN_TURNING_POWER = .05;

    DcMotor right_f = null;
    DcMotor right_b = null;
    DcMotor left_f = null;
    DcMotor left_b = null;

    DcMotor right_shoot = null;
    DcMotor left_shoot = null;

    BNO055IMU imu;

    Servo servo = null;

    DcMotor[] frontMotors = null;
    DcMotor[] backMotors = null;
    DcMotor[] rightMotors = null;
    DcMotor[] leftMotors = null;
    DcMotor[] driveMotors = null;
    DcMotor[] shooterMotors = null;

    public void initHardware() {
    initDriveMotors();
    initServo();
    initShooterMotors();
    initIMU();
     }
    void initDriveMotors(){
        frontRightMotor = hardwareMap.dcMotor.get("");
        frontLeftMotor = hardwareMap.dcMotor.get("");
        backLeftMotor = hardwareMap.dcMotor.get("");
        backRightMotor = hardwareMap.dcMotor.get("");

        frontMotors = new DcMotor[]{frontLeftMotor, frontRightMotor};
        backMotors = new  DcMotor[]{backLeftMotor, backRightMotor};
        rightMotors = new DcMotor[]{frontRightMotor, backRightMotor};
        leftMotors = new  DcMotor[]{frontLeftMotor, backLeftMotor};
        driveMotors = new DcMotor[]{frontRightMotor, backRightMotor, frontLeftMotor, backLeftMotor};

        setMotorDirectiont(DcMotor.Direction.REVERSE, rightMotors);
        setMotorRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, driveMotors);
    }

void  initShooterMotors(){
    leftShooterMotor = hardwareMap.dcMotor.get("");
    rightShooterMotor = hardwareMap.dcMotor.get("");
    shooterMotors = new DcMotor[]{leftShooterMotor, rightShooterMotor};
}

void initIMU() {
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
    parameters.loggingEnabled = true;
    parameters.loggingTag = "IMU";
    parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

    imu = hardwareMap.get(BNO055IMU.class, "imu");
    imu.initialize(parameters);
}

void initServo(){
    Servo = hardwareMap.servo.get("");
}

}