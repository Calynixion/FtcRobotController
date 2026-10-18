package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.robotCore.robot;

public class drivetrain {
    /*
    instantiate 4 motors

    instantiate gyro

    make drivetrain object

    create driveRobotCentric method
     */
    public final DcMotorEx fL, fR, bL, bR;
    public final Follower follower;
    private final Telemetry telemetry;


    public drivetrain(robot robot) {
        follower = Constants.create(robot.hardwareMap);
        fL = robot.hardwareMap.get(DcMotorEx.class, "lf");
        fR = robot.hardwareMap.get(DcMotorEx.class, "rf");
        bL = robot.hardwareMap.get(DcMotorEx.class, "lr");
        bR = robot.hardwareMap.get(DcMotorEx.class, "rr");

        fL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        telemetry = robot.telemetry;
    }

    public void drive(double strafe,double forward,double turn){
        follower.manual(strafe,forward,turn);
    }

    public Command periodic() {
        return infinite(()->{
                follower.update();
            }
        );
    }

}

