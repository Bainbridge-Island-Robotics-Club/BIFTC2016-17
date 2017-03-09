package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Servo;


import static org.firstinspires.ftc.teamcode.R.layout.servo;

/*
 * Created by darwin on 9/20/16.
 *
 *
 *
 * ODS = Optical Distance Sensor
 */

@TeleOp(name="Basic Tank Drive!", group="Noramal_Opmode")
@Disabled

public class TankD_Odistance extends OpMode {
    DcMotor left;                   //Both of our DC motors
    DcMotor right;

    Servo front;                    //Both of our servos
    Servo back;


    OpticalDistanceSensor sensor;   //Make a optical distance sensor called 'sensor'
    TouchSensor Tsensor;            //Make a touch sensor called 'Tsensor'


    double sensor_value;            //Making varibles for both of the values of the sensors
    boolean TsensorV;               //This isn't nessary, but it allows us to manipulate it cleaner later on in the program

    public void init()  {
        left = hardwareMap.dcMotor.get("Motor-left");
        right = hardwareMap.dcMotor.get("Motor-right");

        front = hardwareMap.servo.get("");
        back = hardwareMap.servo.get("");

        sensor = hardwareMap.opticalDistanceSensor.get("dist_sensor");  //Getting both of our sensors, one names "dist_sensor"
        Tsensor = hardwareMap.touchSensor.get("touch_sensor");          //And another names "touch_sensor"

    }

    @Override
    public void loop() {                            //Do this thing over and over again
        float leftDC = gamepad1.left_stick_y;
        float rightDC =  gamepad1.right_stick_y;

        double RTrigger = gamepad1.right_trigger;

        sensor_value = sensor.getLightDetected();   //ODS value(Don't know?)
        TsensorV = Tsensor.isPressed();             //Touch sensor pressed

        front.setPosition(RTrigger);                // Setting both of our servos to position
        back.setPosition(sensor_value);

        telemetry.addData("Dist_sensor", sensor_value); //Both of the values of the sensors are outputting to telemetry
        telemetry.addData("Touch_sensor", TsensorV);


        left.setPower(leftDC);      //Setting motor power based on right_stickY
        right.setPower(rightDC);    //Setting motor power based on left_stickY
        //if(leftDCy > 0)



    }
}

