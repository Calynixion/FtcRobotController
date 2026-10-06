package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Intake extends SubsystemBase {
   Motor intakeM;

    public Intake(HardwareMap hwMap){
        intakeM = new Motor(hwMap,"intakeM");
        intakeM.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }


    public void intake(double power){
        intakeM.set(power);
    }



}
