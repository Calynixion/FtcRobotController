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
public class BlueCloseFull extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();

    @Override
    public void init(){
        super.init();
    }
    public void start(){
        schedule (
                sequential (
                        robotOpMode.shoot(),
                        robot.drivetrain.followPath(paths.path1()),
                        robot.intake.on(),
                        robot.drivetrain.followPath(paths.path2()),
                        robot.drivetrain.followPath(paths.path3()),
                        waitMs(1000),
                        robot.drivetrain.followPath(paths.path4()),
                        robot.intake.off(),
                        robot.drivetrain.followPath(paths.path5()),
                        robotOpMode.shoot(),
                        robot.drivetrain.followPath(paths.path6())



                )
        );
    }
    private static class AutoPaths {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(886, 133.46, 90);
        private final Pose point1 = poseFactory.of(86.5771, 130, 270);
        private final Pose point2 = poseFactory.of(131.0817, 133.46, 0);

        private final Pose point3 = poseFactory.of(130.8051, 126.465, 0);

        private final Pose point4 = poseFactory.of(84.8884, 102.0359, 180);

        private final Pose point4Control1 = poseFactory.of(135.201, 101.208, 0);

        private final Pose point5 = poseFactory.of(81.2809, 8.3439, 90);

        private final Pose point6 = poseFactory.of(127.2665, 27.9616, 90);
        private final Pose point6Control1 = poseFactory.of(97.6094, 41.8505, 0);


        public Path path1() {
            return Paths.line(start, point1).constant(point1);
        }

        public Path path2() {
            return Paths.line(point1, point2).constant(point2);
        }

        public Path path3() {
            return Paths.line(point2, point3).constant(point3);
        }

        public Path path4() {
            return Paths.curve(point3, point4Control1, point4).constant(point4);
        }

        public Path path5() {
            return Paths.line(point4, point5).constant(point5);
        }

        public Path path6() {
            return Paths.curve(point5, point6Control1, point6).constant(point6);
        }
    }
}

