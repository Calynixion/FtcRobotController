package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;

@TeleOp
public class RedTeleOp extends robotOpMode {

    @Override
    public void init(){
        super.init();
    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){

        robot.drivetrain.drive(
                -gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );

        if (gamepad1.bWasPressed()) robot.outtake.toggle();
        if (gamepad1.leftBumperWasPressed()) robot.intake.on();
        if (gamepad1.rightBumperWasPressed()) robot.intake.reverse();
        if (gamepad1.aWasPressed()) robot.intake.off();

        super.loop();
    }

}
