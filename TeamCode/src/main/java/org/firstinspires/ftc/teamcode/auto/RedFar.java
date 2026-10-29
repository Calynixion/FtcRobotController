package org.firstinspires.ftc.teamcode.auto;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.paths.interpolator.Interpolator.constant;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;

@Autonomous(name = "Red Far",group = "Red Autos")
public class RedFar extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();


    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(22.4739,133.1469,270));
        //init paths object
    }


    @Override
    public void start(){
        schedule (
                sequential(
                        robot.drivetrain.followPath(paths.path1()),
                        waitMs(12000),
                        robotOpMode.shoot(),
                        robot.drivetrain.followPath(paths.path2())

                )
        );
    }


    private static class AutoPaths {
        //paths from visualizer go here
        public Path path1(){
            return Paths.line(
                poseFactory.of(22.4739,133.1469,270),
                poseFactory.of(57.7919,118.2961,270)
            ).constant(270);
        };
        public Path path2(){
            return Paths.line(
                poseFactory.of(57.7919, 118.2961, 270),
                poseFactory.of(10.4251, 118.9416, 180)
            ).constant(180);
        }
    }



}
