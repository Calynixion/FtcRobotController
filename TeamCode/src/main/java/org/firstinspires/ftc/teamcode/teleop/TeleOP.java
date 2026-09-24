package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;

@TeleOp
public class TeleOP extends OpMode {
    private Motor fL, fR, bL, bR;
    private MecanumDrive drive;
    private GamepadEx driverOp;
    Intake intakeAedric;

    Outtake outtakeMark;

    @Override
    public void init() {
        /* instantiate motors */
        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "bR");
        drive = new MecanumDrive(fL, fR, bL, bR);
        driverOp = new GamepadEx(gamepad1);
        intakeAedric = new Intake(hardwareMap); // aris dont delete this i fixed it so it
        outtakeMark = new Outtake(hardwareMap);   // wont throw an error if no hardware map
    }

    @Override
    public void loop() {

        drive.driveRobotCentric(
                -driverOp.getLeftX(),
                -driverOp.getLeftY(),
                -driverOp.getRightX()
        );

        intakeAedric.intake(gamepad1.left_bumper);
        outtakeMark.shoot(gamepad1.right_bumper);
    }
}


