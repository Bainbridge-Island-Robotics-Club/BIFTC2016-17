package org.firstinspires.ftc.team8745;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by some guy "named" Nintendo8 on 11/15/2016.
 */
@TeleOp(name="Servo Test")
public class Servo extends OpMode{
    com.qualcomm.robotcore.hardware.Servo shooterFeeder;
    public void init() {
        // Servo
        shooterFeeder = hardwareMap.servo.get("shooter-servo");}
    @Override
    public void loop() {
        float right_trigger2 = gamepad2.right_trigger;
        shooterFeeder.setPosition(right_trigger2);
    }
}
