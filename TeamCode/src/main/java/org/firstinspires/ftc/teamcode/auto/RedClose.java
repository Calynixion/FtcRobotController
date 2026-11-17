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

@Autonomous(name = "Red Close",group = "Red Autos")
public class RedClose extends robotOpMode {
    private AutoPaths paths;
    private static final PoseFactory poseFactory = PoseFactory.degrees();


    @Override
    public void init(){
        super.init();
        paths = new AutoPaths();
        robot.drivetrain.setPose(poseFactory.of(56.2308, 7.8846, 90));

        //init paths object
    }


    @Override
    public void start(){
        schedule (
                sequential(
                        robotOpMode.shoot(),
                        robot.drivetrain.followPath(paths.toPark())
                )
        );
    }



    public class AutoPaths {

        private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose start = poseFactory.of(56.2308, 7.8846, 90);
        private final Pose toPark = poseFactory.of(13.2961, 92.6692, 90);
        private final Pose toParkControl1 = poseFactory.of(13.6196, 42.5953, 0);

        public Path toPark() {
            return Paths.curve(start, toParkControl1, toPark).constant(toPark);
        }
    }



}
