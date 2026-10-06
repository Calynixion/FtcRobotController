package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

<<<<<<< Updated upstream
public class Intake extends SubsystemBase {
   DcMotor intakeM;
=======
public class Intake {
   Motor intakeM;
>>>>>>> Stashed changes

    public Intake(HardwareMap hwMap){
        intakeM = new Motor(hwMap,"intakeM");
        intakeM.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

<<<<<<< Updated upstream
    public void intake(boolean buttonIsPressed){

        if (buttonIsPressed) {
            intakeM.setPower(1);
        } else {
            intakeM.setPower(0);
        }
=======
    public void intake(double power){
        intakeM.set(power);
>>>>>>> Stashed changes
    }



}
