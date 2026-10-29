package org.firstinspires.ftc.teamcode.auto;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.ivy.Command;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;

@Autonomous(name = "Red Close",group = "Red Autos")
public class RedClose extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();


    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(53.5158,9.2731,90));

        //init paths object
    }


    @Override
    public void start(){
        schedule (
                sequential(
                        robotOpMode.shoot(),
                        robot.drivetrain.followPath(paths.path1())
                )
        );
    }



    private static class AutoPaths {
        //paths from visualizer go here
        public Path path1(){
            return Paths.line(
                poseFactory.of(53.5158,9.2731,90),
                poseFactory.of(9.3195,95.8716,90)
            ).constant(90);
        };
    }



}
