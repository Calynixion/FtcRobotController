package org.firstinspires.ftc.teamcode.robotCore;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.controllers.PIDController;
import com.pedropathing.drivetrain.Drivetrain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.transfer;

public class robot {
    public final HardwareMap hardwareMap;
    public final JoinedTelemetry telemetry;
    public final drivetrain drivetrain;
    public final Intake intake;
    public final Outtake outtake;

    public final transfer transfer;

    public robot(OpMode opMode){
        hardwareMap = opMode.hardwareMap;
        telemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(),opMode.telemetry);
        drivetrain = new drivetrain(this);
        intake = new Intake(this);
        outtake = new Outtake(this);
        transfer = new transfer(this);


    }


}
