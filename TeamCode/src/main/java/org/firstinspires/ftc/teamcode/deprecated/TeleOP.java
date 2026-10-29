//package org.firstinspires.ftc.teamcode.teleop;
//
//import static com.google.blocks.ftcrobotcontroller.hardware.HardwareType.IMU;
//
//import com.pedropathing.drivetrain.Drivetrain;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.IMU;
//import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
//import com.seattlesolvers.solverslib.gamepad.GamepadEx;
//import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
//import com.seattlesolvers.solverslib.hardware.RevIMU;
//import com.seattlesolvers.solverslib.hardware.motors.Motor;
//
//import org.firstinspires.ftc.teamcode.subsystems.Intake;
//import org.firstinspires.ftc.teamcode.subsystems.Outtake;
//import org.firstinspires.ftc.teamcode.subsystems.drivetrain;
//
//import java.util.Scanner;
//@TeleOp
//public class TeleOP extends OpMode {
//    private GamepadEx driverOp;
//    Intake intake;
//
//    drivetrain drivetrain;
//
//    @Override
//    public void init() {
//        /* instantiate motors */
//
//
//
//        drivetrain = new drivetrain(hardwareMap);
//        driverOp = new GamepadEx(gamepad1);
//        intake = new Intake(hardwareMap); // aris dont delete this i fixed it so it
//        //outtake = new Outtake(hardwareMap);   // wont throw an error if no hardware map
//
//        //intake = new Intake(hardwareMap); // aris dont delete this i fixed it so it
//        //outtake = new Outtake(hardwareMap);   // wont throw an error if no hardware map
//        //driveBetter = new aedricDrive(hardwareMap, gamepad1);
//    }
//
//    @Override
//    public void loop() {
//
//        drivetrain.drive(-driverOp.getLeftX(),-driverOp.getLeftY(),-driverOp.getRightX());
////intake.intake(gamepad1.left_bumper);
//        //outtake.shoot(gamepad1.right_bumper);
////        telemetry.addData("Left Odom: ",fL.getCurrentPosition());
////        telemetry.addData("Right Odom: ",bR.getCurrentPosition());
////        telemetry.addData("Strafe Odom: ",bL.getCurrentPosition());
//
//        if (driverOp.getButton(GamepadKeys.Button.A)){
//            intake.intake(1);
//        } else {
//            intake.intake(0);
//        }
//        //driveBetter.drive(1);
//        //intake.intake(gamepad1.left_bumper);
//    }
//}
//
//
