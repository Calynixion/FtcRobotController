package org.firstinspires.ftc.teamcode.robotCore;

import com.pedropathing.controllers.PIDController;
import com.pedropathing.drivetrain.Drivetrain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain;

public class robot {
    public final HardwareMap hardwareMap;
    public final Telemetry telemetry;
    public final drivetrain drivetrain;
    public final Intake intake;
    public final Outtake outtake;

    public robot(OpMode opMode){
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;
        drivetrain = new drivetrain(this);
        intake = new Intake(this);
        outtake = new Outtake(this);
    }
}
