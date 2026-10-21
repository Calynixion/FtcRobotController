package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.robotCore.robot;

public class Outtake {
    //Implement pidf here later, for now will be manual power
    private final DcMotorEx outtakeM;
    private final Telemetry telemetry;

    private final ColorSensor sensor;
    private double targetPower = 0.8;

    public color nextBallColor = color.NULL;

    private double targetSpeed = 1;
    private double bigBallPower = 0.75;

    private double smallBallPower = 0.5;

    private double acceptedError = 0.1;
    private boolean on = false;

    public Outtake(robot robot){
        sensor = robot.hardwareMap.get(ColorSensor.class,"colorSensor");
        outtakeM = robot.hardwareMap.get(DcMotorEx.class,"outtakeM");
        outtakeM.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        outtakeM.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry = robot.telemetry;
    }

    public void setTargetPower(double power){
       targetPower = power;
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

    private void calculateColor(int r,int g,int b, int a){
        if (a>100) {
            if ((g > r) && (r > b) && (r>(0.5*g))) {
                nextBallColor = color.YELLOW;
            } else if ((r > b) && (r > g)) {
                nextBallColor = color.RED;
            } else if ((b > r) && (b > g)) {
                nextBallColor = color.BLUE;
            }
        }  else {
            nextBallColor = color.NULL;
        }


    }

    public Command periodic() {
        return infinite(()->{
            calculateColor(sensor.red(), sensor.green(), sensor.blue(), sensor.alpha());
            switch (nextBallColor) {
                case RED:
                case BLUE:
                    setTargetPower(bigBallPower);
                    break;
                case YELLOW:
                    setTargetPower(smallBallPower);
                    break;
                case NULL:
                    setTargetPower(0.8);
                    break;
            }
            if (on) {
                //replace with pid
                outtakeM.setPower(targetPower);
            } else {
                outtakeM.setPower(0);
            }
            telemetry.addData("Target Power: ",targetPower);
            telemetry.addData("Shooter Speed: ",outtakeM.getVelocity());
            telemetry.addData("Shooter Voltage: ",outtakeM.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("Color Sensor Alpha: ",sensor.alpha());
            telemetry.addData("Color Sensor Red: ",sensor.red());
            telemetry.addData("Color Sensor Green: ",sensor.green());
            telemetry.addData("Color Sensor Blue: ",sensor.blue());   
            telemetry.addData("Next Ball Color: ",nextBallColor);

        });
    }

    public enum color{
        RED,
        YELLOW,
        BLUE,
        NULL
    }





}
