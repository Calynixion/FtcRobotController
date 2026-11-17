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

@Autonomous(name = "Blue Far",group = "Blue Autos")
public class BlueFar extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();

    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(80.4682, 8.2308, 90));
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

        private final Pose blueFar = poseFactory.of(80.4682, 8.2308, 90);
        private final Pose topark = poseFactory.of(126.8654, 20.4188, 90);
        private final Pose toparkControl1 = poseFactory.of(78.0397, 42.0516, 0);

        public Path toPark() {
            return Paths.curve(blueFar, toparkControl1, topark).constant(topark);
        }
    }
}






