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

@Autonomous
public class BlueClose extends robotOpMode {
    private AutoPaths paths;

    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        //set starting pose using setPose of drivetrain
        //init paths object
    }


    @Override
    public void start(){
        schedule (
              sequential(
                 shoot(),
                 robot.drivetrain.followPath(paths.path1())
              )
        );
    }

    private Command shoot() {
        return sequential(
           instant(robot.outtake::On),
           waitUntil(robot.outtake::atTarget),
           waitMs(50),
           //put transfer here
           waitMs(4000),
            //stop transfer here
           instant(robot.outtake::Off)

        );
    }

    private static class AutoPaths {
        //paths from visualizer go here
        private final PoseFactory poseFactory = PoseFactory.degrees();


        public Path path1(){
            return Paths.line(poseFactory.of(88.9643,133.3427,90),poseFactory.of(131.5285,48.6723,270)).constant(270);
        };
    }



}
