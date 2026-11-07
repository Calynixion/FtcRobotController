package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;
import static com.pedropathing.api.Paths.*;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
@Autonomous
public class RedCloseFull extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();

    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(53.5158,9.2731,90));

    }
    @Override
    public void start(){
        schedule (
           sequential (
                   robotOpMode.shoot(),
                   robot.intake.on(),
                   robot.drivetrain.followPath(paths.path1()),
                   waitMs(500),
                   robot.drivetrain.followPath(paths.path2()),
                   robot.intake.off(),
                   robot.drivetrain.followPath(paths.path3()),
                   robotOpMode.shoot(),
                   robot.drivetrain.followPath(paths.path4())
           )
        );
    }
    private static class AutoPaths {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(56, 8, 90);
        private final Pose path1 = poseFactory.of(9.1411, 7.7231, 180);
        private final Pose point2 = poseFactory.of(57.648, 43.8886, 81.8955);
        private final Pose point2Control1 = poseFactory.of(54.0667, 18.7388, 0);
        private final Pose point3Start = poseFactory.of(57.648, 43.8886, 90);
        private final Pose point3 = poseFactory.of(57.5523, 127.4003, 270);
        private final Pose point4 = poseFactory.of(8.8843, 118.4592, 270);
        private final Pose point4Control1 = poseFactory.of(48.507, 118.63, 0);

        public Path path1() {
            return Paths.line(start, path1).constant(path1);
        }

        public Path path2() {
            return Paths.curve(path1, point2Control1, point2).tangent();
        }

        public Path path3() {
            return Paths.line(point3Start, point3).linear(point3Start, point3);
        }

        public Path path4() {
            return Paths.curve(point3, point4Control1, point4).constant(point4);
        }
    }
}

