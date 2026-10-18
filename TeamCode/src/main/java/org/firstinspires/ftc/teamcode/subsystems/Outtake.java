package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robotCore.robot;


public class Outtake {
    //Implement pidf here later, for now will be manual power
    private final DcMotorEx outtakeM;
    private final Telemetry telemetry;
    private double targetPower = 1;
    private boolean on = false;

    public Outtake(robot robot){
        outtakeM = robot.hardwareMap.get(DcMotorEx.class,"outtakeM");
        outtakeM.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        telemetry = robot.telemetry;
    }

    public void setPower(double power){
       outtakeM.setPower(power);
    }

    public void On(){
        on = true;
    }
    public void Off(){
        on = false;
    }

    public void toggle(){
        on = !on;
        if (on) On();
        else Off();
    }

    public Command periodic() {
        return infinite(()->{
            if (on) {
                setPower(targetPower);
            } else {
                setPower(0);
            }
        });
    }

}
