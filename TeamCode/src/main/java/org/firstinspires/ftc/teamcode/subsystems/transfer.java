package org.firstinspires.ftc.teamcode.subsystems;


import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.robotCore.robot;

public class transfer{
    private final DcMotorEx transferM;

    private final Telemetry telemetry;

    private Mode mode = Mode.OFF;

    public static double power = 1;



    public transfer(robot robot){
        transferM = robot.hardwareMap.get(DcMotorEx.class, "transferM");
        telemetry = robot.telemetry;
        transferM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    public Command on(){return instant (()->mode = Mode.ON);}

    public Command off(){return  instant (()->mode = Mode.OFF);}

    public Command reverse(){return instant(()->mode = Mode.REVERSE);}

    public Command periodic (){
        return infinite(() -> {
            switch (mode) {
                case ON:
                    transferM.setPower(power);
                    break;
                case OFF:
                    transferM.setPower(0);
                    break;
                case REVERSE:
                    transferM.setPower(-power);
                    break;
            }
            telemetry.addData("transfer Mode: ",mode);
            telemetry.addData("transfer Power: ",transferM.getCurrent(CurrentUnit.AMPS));
        });
    }




    enum Mode {
        ON,
        OFF,
        REVERSE
    }
}



