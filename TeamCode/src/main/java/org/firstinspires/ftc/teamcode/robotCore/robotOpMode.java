package org.firstinspires.ftc.teamcode.robotCore;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robot.Robot;

public class robotOpMode extends OpMode {
    protected robot robot;

    @Override
    public void init() {
        Scheduler.reset();
        robot = new robot(this);

        schedule(
            robot.drivetrain.periodic(),
            robot.intake.periodic(),
            robot.outtake.periodic()
        );
    }

    @Override
    public void init_loop() {
        Scheduler.execute();
    }

    @Override
    public void loop() {
        Scheduler.execute();
        robot.telemetry.update();
    }
}
