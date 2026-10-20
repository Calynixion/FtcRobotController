package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.robotCore.robot;

public class Outtake {
    //Implement pidf here later, for now will be manual power
    private final DcMotorEx outtakeM;
    private final Telemetry telemetry;
    private double targetPower = 1;

    private double targetSpeed = 1;

    private double acceptedError = 0.1;
    private boolean on = false;

    public Outtake(robot robot){
        outtakeM = robot.hardwareMap.get(DcMotorEx.class,"outtakeM");
        outtakeM.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        outtakeM.setDirection(DcMotorSimple.Direction.REVERSE);
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
            telemetry.addData("Shooter Speed: ",outtakeM.getVelocity());
            telemetry.addData("Shooter Voltage: ",outtakeM.getCurrent(CurrentUnit.AMPS));
        });
    }

}
