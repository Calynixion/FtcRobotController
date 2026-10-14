package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Outtake extends SubsystemBase {

    Motor outtake;

    public Outtake(HardwareMap hwMap){
        outtake = new Motor(hwMap,"outtakeM");
        outtake.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);


    }
    public void shoot(int power){
        outtake.set(power);
    }

}
