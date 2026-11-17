package org.firstinspires.ftc.teamcode.auto;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Command;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;

@Autonomous(name = "RedFar",group = "Red Autos")
public class RedFar extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();

    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(62.2325, 133.3418, 270));
    }


    @Override
    public void start(){
        schedule (
                sequential(
                        waitMs(12000),
                        robotOpMode.shoot(),
                        robot.drivetrain.followPath(paths.toPark())
                )
        );
    }

    public class AutoPaths {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(62.2325, 133.3418, 270);
        private final Pose toPark = poseFactory.of(11.6803, 122.562, 270);
        private final Pose toParkControl1 = poseFactory.of(56.289, 101.7209, 0);

        public Path toPark() {
            return Paths.curve(start, toParkControl1, toPark).constant(toPark);
        }
    }





}
