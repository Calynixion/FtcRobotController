package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotCore.robotOpMode;

@TeleOp(name="Red TeleOp",group="Competition")
public class RedTeleOp extends robotOpMode {
    private boolean hold = false;
    private Pose holdPose;
    @Override
    public void init(){
        super.init();
    }

    @Override
    public void start(){

    }

    @Override
    public void loop(){
        if (gamepad1.xWasPressed()){
            hold = !hold;
            holdPose = robot.drivetrain.follower.pose();
        }
        if (hold){
            robot.drivetrain.follower.hold(holdPose);
        } else {
            robot.drivetrain.drive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );
        }

        if (gamepad1.left_bumper) {
            robot.intake.on().schedule();
        } else if (gamepad1.right_bumper){
            robot.intake.reverse().schedule();
        }  else {
            robot.intake.off().schedule();
        }

        if (gamepad2.aWasPressed()) robot.outtake.toggleMode();
        if (gamepad2.left_bumper) {
            robot.outtake.On();
        } else {
            robot.outtake.Off();
        }
        if (gamepad2.right_bumper){
            robot.transfer.on().schedule();
        }  else if (gamepad2.right_trigger_pressed){
            robot.transfer.reverse().schedule();
        } else {
            robot.transfer.off().schedule();
        }


//        if (gamepad1.bWasPressed()) robot.outtake.toggle();
//        if (gamepad1.leftBumperWasPressed()) robot.intake.on().schedule();
//        if (gamepad1.rightBumperWasPressed()) robot.intake.reverse().schedule();
//        if (gamepad1.aWasPressed()) robot.intake.off().schedule();
//        if (gamepad1.leftTriggerWasPressed()) robot.transfer.on().schedule();
//        if (gamepad1.rightTriggerWasPressed()) robot.transfer.reverse().schedule();
//        if (gamepad1.xWasPressed()) robot.transfer.off().schedule();



        super.loop();
    }

}
