package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class aedricDrive {
    private DcMotor fL, fR, bL, bR;
    public final Gamepad gamepad;

    public aedricDrive(HardwareMap hwMap, Gamepad gamepad)
    {
        fL = hwMap.get(DcMotor.class, "lf");
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fL.setDirection(DcMotorSimple.Direction.REVERSE);

        fR = hwMap.get(DcMotor.class, "rf");
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bL = hwMap.get(DcMotor.class, "lr");
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);

        bR = hwMap.get(DcMotor.class, "rr");
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.gamepad = gamepad;
    }

    double flPower;
    double frPower;
    double blPower;
    double brPower;
    double forward;
    double strafe;
    double rotate;
    double maxPower = 1;
    public void drive(double speed)
    {
        this.forward = -gamepad.left_stick_y;
        this.strafe = gamepad.left_stick_x;
        this.rotate = gamepad.right_stick_x;

        this.flPower = this.forward - this.strafe - this.rotate;
        this.frPower = this.forward + this.strafe + this.rotate;
        this.blPower = this.forward + this.strafe - this.rotate;
        this.brPower = this.forward - this.strafe + this.rotate;

        this.maxPower = Math.max(this.maxPower, Math.abs(this.flPower));
        this.maxPower = Math.max(this.maxPower, Math.abs(this.frPower));
        this.maxPower = Math.max(this.maxPower, Math.abs(this.blPower));
        this.maxPower = Math.max(this.maxPower, Math.abs(this.brPower));

        this.flPower *= (speed/this.maxPower);
        this.frPower *= (speed/this.maxPower);
        this.blPower *= (speed/this.maxPower);
        this.brPower *= (speed/this.maxPower);

        this.fL.setPower(this.flPower);
        this.fR.setPower(this.frPower);
        this.bL.setPower(this.blPower);
        this.bR.setPower(this.brPower);
    }
}