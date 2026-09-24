package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Intake extends SubsystemBase {
   DcMotor intakeM;

    public Intake(HardwareMap hwMap){
        if (hwMap.dcMotor.contains("intakeM")) {
            intakeM = hwMap.get(DcMotor.class, "intakeM");
            intakeM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            intakeM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public void intake(boolean buttonIsPressed)
    {
        if (buttonIsPressed) {
            intakeM.setPower(1);
        }
    }
}
