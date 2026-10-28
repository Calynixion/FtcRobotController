package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.controllers.Controller;
import com.pedropathing.controllers.PIDController;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.robotCore.robot;
@Configurable
public class Outtake {
    //Implement pidf here later, for now will be manual power
    private final DcMotorEx outtakeM;
    private final Telemetry telemetry;

    private ColorSensor sensor=null;
    private double targetPower = 0.8;

    public color nextBallColor = color.NULL;


    //speed will be in ticks/sec
    //max is 2800 ticks/sec
    private double targetSpeed = 2800*0.8;
    private double bigBallSpeed = 2800*0.8;

    private double smallBallSpeed = 2800*0.5;

    public static double kP = 0.01;
    public static double kI = 0.01;
    public static double kD = 0.02;
    private Controller flywheelController = Controller.pid(kP,kI,kD);


    //accepted error in percent
    private double tolerance = 150;
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
    public void setTargetSpeed(double speed){
        targetSpeed = speed;
    }
    public double getVelocity() {
        return outtakeM.getVelocity();
    }
    public boolean atTarget() {
        return Math.abs(targetSpeed-getVelocity()) <= tolerance;
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
                    setTargetSpeed(bigBallSpeed);
                    break;
                case YELLOW:
                    setTargetSpeed(smallBallSpeed);
                    break;
                case NULL:
                    setTargetPower(2240);
                    break;
            }


            if (on) {
                //replace with pid
                flywheelController = Controller.pid(kP,kI,kD);

                outtakeM.setPower(flywheelController.calculate(targetSpeed,targetSpeed-getVelocity()));

                //outtakeM.setPower(flywheelController.calculate(targetSpeed,targetSpeed-outtakeM.getVelocity()));
            } else {
                outtakeM.setPower(0);
            }
            telemetry.addData("Outtake State",on?"On":"Off");
            telemetry.addData("Target Speed",targetSpeed);
            telemetry.addData("Current Speed",getVelocity());
            telemetry.addData("At Target",atTarget());
            telemetry.addData("Shooter Voltage",outtakeM.getCurrent(CurrentUnit.AMPS));

            telemetry.addData("Color Sensor Alpha",sensor.alpha());
            telemetry.addData("Color Sensor Red",sensor.red());
            telemetry.addData("Color Sensor Green",sensor.green());
            telemetry.addData("Color Sensor Blue",sensor.blue());
            telemetry.addData("Next Ball Color",nextBallColor);



        });
    }


    public enum color{
        RED,
        YELLOW,
        BLUE,
        NULL
    }





}
