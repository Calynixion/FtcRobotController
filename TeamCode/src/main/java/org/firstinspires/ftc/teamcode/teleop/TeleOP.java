package org.firstinspires.ftc.teamcode.teleop;

import static com.google.blocks.ftcrobotcontroller.hardware.HardwareType.IMU;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.seattlesolvers.solverslib.hardware.RevIMU;
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
    private RevIMU imu;


    @Override
    public void init() {
        /* instantiate motors */
        fL = new Motor(hardwareMap, "lf");
        fR = new Motor(hardwareMap, "rf");
        bL = new Motor(hardwareMap, "lr");
        bR = new Motor(hardwareMap, "rr");
        fL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        imu = new RevIMU(hardwareMap);
        imu.init();


        fL.resetEncoder();
        bR.resetEncoder();
        bL.resetEncoder();


        drive = new MecanumDrive(fL, fR, bL, bR);
        driverOp = new GamepadEx(gamepad1);
        intake = new Intake(hardwareMap); // aris dont delete this i fixed it so it
        //outtake = new Outtake(hardwareMap);   // wont throw an error if no hardware map

        //intake = new Intake(hardwareMap); // aris dont delete this i fixed it so it
        //outtake = new Outtake(hardwareMap);   // wont throw an error if no hardware map
        //driveBetter = new aedricDrive(hardwareMap, gamepad1);
    }

    @Override
    public void loop() {

        drive.driveRobotCentric(
                -driverOp.getLeftX(),-driverOp.getLeftY(),-driverOp.getRightX(),false
        );
        //intake.intake(gamepad1.left_bumper);
        //outtake.shoot(gamepad1.right_bumper);
        telemetry.addData("Left Odom: ",fL.getCurrentPosition());
        telemetry.addData("Right Odom: ",bR.getCurrentPosition());
        telemetry.addData("Strafe Odom: ",fR.getCurrentPosition());



        telemetry.addData("Strafe Odom: ",bL.getCurrentPosition());

        if (driverOp.getButton(GamepadKeys.Button.A)){
            intake.intake(1);
        } else {
            intake.intake(0);
        }
        //driveBetter.drive(1);
        //intake.intake(gamepad1.left_bumper);
    }
}


