package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

@Autonomous
public class SimpleAuto extends LinearOpMode {

    private Motor fL, fR, bL, bR;
    private MecanumDrive drive;

    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        fL = new Motor(hardwareMap, "fL");
        fR = new Motor(hardwareMap, "fR");
        bL = new Motor(hardwareMap, "bL");
        bR = new Motor(hardwareMap, "bR");
        drive = new MecanumDrive(fL, fR, bL, bR);

        waitForStart();

        runtime.startTime();
        runtime.reset();

        while (opModeIsActive()) {
            while ((opModeIsActive())&&(runtime.seconds()<2)) {
                drive.driveRobotCentric(0,-1,0);
            }
            drive.stop();
        }
    }
}
