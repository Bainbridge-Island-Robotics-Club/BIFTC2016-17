package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/*
 * Created by darwin on 10/29/16.
 */
@TeleOp(name = "9KT_Mech", group = "null")
//@Disabled0
public class NineK_MainBotT_Arcade extends OpMode {
    //Drivin
    Hardware9374 robot = new Hardware9374();
    public ElapsedTime runTime = new ElapsedTime();

    public void init()  {
        //Driving motors
    robot.init(hardwareMap, telemetry);


    }

    @Override
    public void loop() {
        //All Driving code//
        //Driver

        double lStickY = -gamepad1.left_stick_y;
        double lStickX = gamepad1.left_stick_x;
        double rStickY = gamepad1.right_stick_y;
        double rStickX = gamepad1.right_stick_x;

        //Operator
        double rTrigger = gamepad2.right_trigger;
        double lTrigger = gamepad2.left_trigger;
        boolean lBumper = gamepad2.left_bumper;
        boolean rBumper = gamepad2.right_bumper;

        double LFpower;
        double LBpower;
        double RFpower;
        double RBpower;

        //left.setDirection(DcMotorSimple.Direction.REVERSE);//Or .FORWARD
        //Mecahnim wheels



        LFpower = lStickY - rStickX;
        LBpower = lStickY - rStickX;

        RFpower = lStickY + rStickX;
        RBpower = lStickY + rStickX;

        /*
         LFpower = lStickY;
        LBpower = lStickY;

        RFpower = rStickY;
        RBpower = rStickY;
         */
        robot.left_f.setPower(Range.clip(LFpower,-1,1));
        robot.right_f.setPower(Range.clip(RFpower,-1,1));
        robot.left_b.setPower(Range.clip(LBpower,-1,1));
        robot.right_b.setPower(Range.clip(RBpower,-1,1));

        //End of Mechanim wheel
        //Controlls right side

        //RTrigger is same, 0 and 1.
        lTrigger = lTrigger*-1;
        //Should default to 0 and 1

        robot.elevator.setPower(rTrigger+lTrigger);
        //Only thig is that this assumes ltrugger is less than 1, which it should be
        if (rTrigger != 0){
            robot.elevator.setPower(rTrigger);
        } else if (lTrigger != 0 ){
            robot.elevator.setPower(lTrigger);
        }

        robot.elevator.setPower(gamepad2.left_stick_y);
        //Shooter code

        if (lBumper)  {
            robot.shooter_l.setPower(1);
        } else {
            robot.shooter_l.setPower(0);
        }

        if (rBumper)   {
            robot.shooter_r.setPower(-1);
        } else {
            robot.shooter_r.setPower(0);
        }

        telemetry.addData("Robot Red:",robot.CSensorR.red());
        telemetry.addData("Robot blue",robot.CSensorR.blue());


    }
}
