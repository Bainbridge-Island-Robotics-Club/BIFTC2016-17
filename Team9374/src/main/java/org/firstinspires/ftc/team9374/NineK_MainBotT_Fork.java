package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * Created by darwin on 10/29/16.
 */
@TeleOp(name = "Teleop_Fork_TestV2")
//@Disabled0
public class NineK_MainBotT_Fork extends OpMode {
    //Drivin
    //public ElapsedTime runTime = new ElapsedTime();

    double gear = 1;

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

    CRServo elevator;

    public void init()  {
        //Driving motors
        right_b = hardwareMap.dcMotor.get("Eng1-left");  //Was left_f
        left_b = hardwareMap.dcMotor.get("Eng1-right");//Was Right_f

        right_f = hardwareMap.dcMotor.get("Eng2-left");  //Was left_b
        left_f = hardwareMap.dcMotor.get("Eng2-right");//Was right_b
        //Shooter motors
        shooter_r = hardwareMap.dcMotor.get("Eng3-left");
        shooter_l = hardwareMap.dcMotor.get("Eng3-right");

        elevator = hardwareMap.crservo.get("Ser1-center");


        //First one(Port 1) in the config is the left motor.
        //2nd one(Port 2) In the config is the right motor.
        forkL = hardwareMap.dcMotor.get("ForkL");
        forkR = hardwareMap.dcMotor.get("ForkR");

    }
    public void loop() {
        //All Driving code//
        //Driver

        double lStickY = -gamepad1.left_stick_y;
        double rStickY = -gamepad1.right_stick_y;

        //Operator
        double rTrigger = gamepad2.right_trigger;
        double lTrigger = gamepad2.left_trigger;
        boolean lBumper = gamepad2.left_bumper;
        boolean rBumper = gamepad2.right_bumper;

        double rStickY2 = gamepad2.right_stick_y;


        boolean A = gamepad1.a;
        boolean B = gamepad1.b;
        boolean Y = gamepad1.y;

        //left.setDirection(DcMotorSimple.Direction.REVERSE);//Or .FORWARD
        //Mecahnim wheels

        //End of Mechanim wheel
        //Controlls right side

        //RTrigger is same, 0 and 1.
        lTrigger = lTrigger*-1;
        //Should default to 0 and 1

        //Only thig is that this assumes ltrugger is less than 1, which it should be
        if (A) {            //A is back to normal speed
            gear = 1;
            //Just for ease of controll. Not have to go back and forth.
        } else if (B) {     //B is 2nd to last gear
            gear = .3;
        } else if (Y) {
            gear = .2;      //Y is last gear, or slowest strength.
        }
        //------Verible driving-------//

        left_f.setPower(lStickY*gear);
        left_b.setPower(lStickY*gear);

        right_f.setPower(rStickY*gear);
        right_b.setPower(rStickY*gear);

        //------Verible driving-------//

        //------Fork------------------//

        forkL.setPower(rStickY2);
        forkR.setPower(rStickY2);


        elevator.setPower(gamepad2.left_stick_y);
        //Shooter code

        if (lBumper)  {
            shooter_l.setPower(1);
        } else {
            shooter_l.setPower(0);
        }

        if (rBumper)   {
            shooter_r.setPower(1);
        } else {
            shooter_r.setPower(0);
        }
        telemetry.addData("Elevator", elevator.getPower());
        telemetry.addData("Right trigger", rTrigger);
        telemetry.addData("Left trigger", lTrigger);


    }
}
