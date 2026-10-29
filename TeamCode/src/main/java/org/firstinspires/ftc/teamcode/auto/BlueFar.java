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

@Autonomous(name = "Blue Far",group = "Blue Autos")

public class BlueFar extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();


    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(118.0477,8.1277,90));
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
                    poseFactory.of(118.0477,8.1277,90),
                    poseFactory.of(85.0147,23.8976,90)
            ).constant(90);
        };

        public Path path2(){
            return Paths.line(
                    poseFactory.of(118.0477, 8.1277, 90),
                    poseFactory.of(130.7735, 23.172, 0)
            ).constant(0);
        }
    }



}
