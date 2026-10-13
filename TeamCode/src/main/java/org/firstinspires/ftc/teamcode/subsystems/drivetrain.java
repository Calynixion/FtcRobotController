package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.RevIMU;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class drivetrain extends SubsystemBase {
    /*
    instantiate 4 motors

    instantiate gyro

    make drivetrain object

    create driveRobotCentric method
     */
    private Motor fL, fR, bL, bR;
    private MecanumDrive drive;

    private RevIMU imu;

    public drivetrain(HardwareMap hwMap) {
        fL = new Motor(hwMap, "lf");
        fR = new Motor(hwMap, "rf");
        bL = new Motor(hwMap, "lr");
        bR = new Motor(hwMap, "rr");
        fL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        fL.resetEncoder();
        bR.resetEncoder();
        bL.resetEncoder();
        fR.resetEncoder();

        drive = new MecanumDrive(fL, fR, bL, bR);

        imu = new RevIMU(hwMap);
        imu.init();

    }

    public void drive(double strafe,double forward,double turn){
        drive.driveRobotCentric(strafe,forward,turn);
    }

}

