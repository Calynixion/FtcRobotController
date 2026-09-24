package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import java.util.Scanner;
@TeleOp
public class TeleOP extends OpMode {
    private Motor fL, fR, bL, bR;
    private MecanumDrive drive;
    private GamepadEx driverOp;
    Intake intake;
    Outtake outtake;


    @Override
    public void init() {
        /* instantiate motors */
        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "bR");
        fL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        drive = new MecanumDrive(fL, fR, bL, bR);
        driverOp = new GamepadEx(gamepad1);
        intake = new Intake(hardwareMap); // aris dont delete this i fixed it so it
        outtake = new Outtake(hardwareMap);   // wont throw an error if no hardware map
    }

    @Override
    public void loop() {

        drive.driveRobotCentric(
                -driverOp.getLeftX(),-driverOp.getLeftY(),-driverOp.getRightX()
        );

        intake.intake(gamepad1.left_bumper);
        outtake.shoot(gamepad1.right_bumper);
    }
}


