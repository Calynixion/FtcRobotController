package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@TeleOp
public class SimpleTeleOp extends OpMode {
    private Motor fL,fR,bL,bR;

    private MecanumDrive drivetrain;

    private GamepadEx drivePad;

    @Override
    public void init() {
        fL = new Motor(hardwareMap,"fL");
        fR = new Motor(hardwareMap,"fR");
        bL = new Motor(hardwareMap,"bL");
        bR = new Motor(hardwareMap,"bR");

        drivetrain = new MecanumDrive(fL,fR,bL,bR);

        drivePad = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {
        drivetrain.driveRobotCentric(
                drivePad.getLeftX(),
                drivePad.getLeftY(),
                drivePad.getRightX()
        );
    }
}
