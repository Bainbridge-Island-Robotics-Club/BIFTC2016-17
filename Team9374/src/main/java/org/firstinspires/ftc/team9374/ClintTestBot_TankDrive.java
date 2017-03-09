package org.firstinspires.ftc.team9374;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;



@TeleOp(name="Clint Test Bot TankDrive", group="Noramal_Opmode")

public class ClintTestBot_TankDrive extends OpMode {

    private DcMotor motorFLeft;
    private DcMotor motorFRight;
    private DcMotor motorBLeft;
    private DcMotor motorBRight;
    public void init()  {
        motorFLeft = hardwareMap.dcMotor.get("dmfl");
        motorBLeft = hardwareMap.dcMotor.get("dmrl");
        motorBRight = hardwareMap.dcMotor.get("dmrr");
        motorFRight = hardwareMap.dcMotor.get("dmfr");
    }


    @Override
    public void loop() {
        float leftDC = -gamepad1.left_stick_y;
        float rightDC =  gamepad1.right_stick_y;


        motorFLeft.setPower(leftDC);
        motorBLeft.setPower(leftDC);

        motorFRight.setPower(rightDC);
        motorBRight.setPower(rightDC);
        //if(leftDCy > 0)



    }
}
