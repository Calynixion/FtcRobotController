package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.Outtake;


@Autonomous
public class RedFirst extends OpMode {

    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 9, 90);
    private final Pose path1 = poseFactory.of(10, 97, 89.6454);
    private final Pose path1Control1 = poseFactory.of(9.4697, 49.2975, 0);

    private Outtake shooter = new Outtake(hardwareMap);

    private Path path1() {
        return curve(start, path1Control1, path1).tangent();
    }
    private Command shoot() {
        return sequential(
            startShooter,
            waitMs(4000),
            stopShooter
        );
    }
        private Command startShooter = Command.build()
            .setStart(()->shooter.shoot(1))
            .setExecute(()->shooter.shoot(1)
    );
    private Command stopShooter = Command.build()
            .setStart(()->shooter.shoot(0))
            .setExecute(()->shooter.shoot(0)
            );

    public Command autoRoutine() {
        return sequential(
                shoot(),
                follow(follower, path1())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();

        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
    }
    @Override
    public void start(){
        schedule(autoRoutine());
    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
    @Override
    public void stop(){
        Scheduler.cancel(autoRoutine());
    }
}


