package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp
public class PedroTeleOp extends OpMode {
    private Follower follower;

    private GamepadEx driveOp;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        follower.setPose(new Pose(0,0,0));
        driveOp = new GamepadEx(gamepad1);

    }

    @Override
    public void loop() {
        follower.manual(driveOp.getLeftY(), driveOp.getLeftX(), driveOp.getRightX());
        follower.update();

    }
}
