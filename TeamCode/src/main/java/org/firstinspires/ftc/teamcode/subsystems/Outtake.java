package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Outtake extends SubsystemBase {

    DcMotor outtakeM;

    public Outtake(HardwareMap outtakem){
        if (outtakem.dcMotor.contains("outtakem"))
        {
        outtakeM = outtakem.get(DcMotor.class,"outtakem");
        outtakeM.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        outtakeM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }
    public void shoot(boolean whatever){
        if (outtakeM != null) {
            if (whatever) {
                outtakeM.setPower(1);
            }
        }
    }

}
