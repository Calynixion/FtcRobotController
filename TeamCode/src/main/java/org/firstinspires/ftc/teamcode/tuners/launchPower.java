package org.firstinspires.ftc.teamcode.tuners;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;

@TeleOp(name="Launch Power Tuner",group="Tuners")
public class launchPower extends robotOpMode {
    private double power=1;

    @Override
    public void init() {
        super.init();
    }
    @Override
    public void start(){

    }
    @Override
    public void loop() {
        if (gamepad1.leftBumperWasPressed()) {
            power-=0.01;
        } else if (gamepad1.rightBumperWasPressed()) {
            power+=0.01;
        }
        if (power<=0) {
            power = 0;
        } else if (power>=1){
            power = 1;
        }

        robot.outtake.setTargetPower(power);
        if (gamepad1.bWasPressed()) robot.outtake.toggle();

        super.loop();
    }
}
