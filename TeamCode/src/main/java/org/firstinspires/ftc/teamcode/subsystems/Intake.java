package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.robotCore.robot;


public class Intake {
   private final DcMotorEx intakeM;
   private final Telemetry telemetry;

   public static double power = 1;

   private Mode mode = Mode.OFF;

    public Intake(robot robot){
        intakeM = robot.hardwareMap.get(DcMotorEx.class,"intakeM");
        intakeM.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        telemetry = robot.telemetry;
    }

    public Command on() {
        return instant(()->mode = Mode.ON);
    }
    public Command off() {
        return instant(()->mode = Mode.OFF);
    }
    public Command reverse() {
        return instant(()->mode = Mode.REVERSE);
    }

    public Command periodic (){
        return infinite(() -> {
            switch (mode) {
                case ON:
                    intakeM.setPower(power);
                    break;
                case OFF:
                    intakeM.setPower(0);
                    break;
                case REVERSE:
                    intakeM.setPower(-power);
                    break;
            }
            telemetry.addData("Intake Mode: ",mode);
            telemetry.addData("Intake Power: ",intakeM.getCurrent(CurrentUnit.AMPS));
        });
    }

    enum Mode {
        ON,
        OFF,
        REVERSE
    }



}
