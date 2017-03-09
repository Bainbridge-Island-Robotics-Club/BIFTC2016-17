package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * Created by darwin on 9/24/16
 *
 * NOT BEEN TESTED
 * ***************
 *
 * Class that is supposed to hold all the hardware for the testbot.
 *
 * Has not been tested yet
 */

@Disabled
public class HardwareTestBot {

    /* Public OpMode members. */
    public DcMotor leftDrive = null;    //See: Paul and hardware location,(memory)
    public DcMotor rightDrive = null;
    public Servo servo1   = null;     // I dont know what this servo is or does,
    // so im giving it a undescriptive name
    public double wheelDiameterInInches;
    public short tpr = 1120;


    public final static int MOTOR_SPEED = 0;

    public void init(HardwareMap hrdMap){

        leftDrive = hrdMap.dcMotor.get("leftDrive");
        rightDrive = hrdMap.dcMotor.get("rightDrive");


        //Setting both of the moters to stop.
        leftDrive.setPower(MOTOR_SPEED);
        rightDrive.setPower(MOTOR_SPEED);
    }

    public void move_to(double dist,
                        double speed,
                        double delay) {
            /* Setting both of the motors to run to a position
             * The DcMotor goes to between 0 and 1120
             *
             * */
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //Make shure that the motors are not in an encoder mode.
    }

    private int calcClicksForInches(double distanceInInches) {
        double revlutions = distanceInInches / (wheelDiameterInInches * Math.PI);
        int clicks = (int) (revlutions * tpr);
        return clicks;


    }




}
