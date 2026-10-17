package org.firstinspires.ftc.teamcode.auto;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;


public class BlueFirst {
    @Autonomous(name = "AutoPath", group = "Autonomous")
    public class AutoPath extends LinearOpMode {

        private Follower follower;

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(84.3619, 132.5593, 270);
        private final Pose point1 = poseFactory.of(130.5789, 51.5213, 270);

        // Autonomous routine
        public Command autoRoutine() {
            return sequential(
                    follow(follower, path1())
            );
        }

        @Override
        public void runOpMode() {
            Scheduler.reset();
            follower = Constants.create(hardwareMap);
            follower.setPose(start);
            follower.update();

            waitForStart();
            schedule(autoRoutine());

            while (opModeIsActive()) {
                follower.update();
                Scheduler.execute();

                telemetry.addData("x", follower.pose().x());
                telemetry.addData("y", follower.pose().y());
                telemetry.addData("heading", follower.pose().heading());

                if (follower.currentPath() != null) {
                    telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                    telemetry.addData("Path number", follower.pathIndex());
                }

                telemetry.update();
            }
        }

        public Path path1() {
            return Paths.line(start, point1).linear(start, point1);
        }
    }
//    private class random {
//
//        private final PoseFactory poseFactory = PoseFactory.degrees();
//
//        private final Pose start = poseFactory.of(84.3619, 132.5593, 270);
//        private final Pose point1 = poseFactory.of(130.5789, 51.5213, 270);
//
//        public Path path1() {
//            return Paths.line(start, point1).linear(start, point1);
//        }
//    }



}


