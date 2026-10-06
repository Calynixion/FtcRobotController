package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.Encoder;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.revhub.localizers.RevHubIMU;
import com.pedropathing.revhub.localizers.ThreeWheelIMUConfig;
import com.pedropathing.revhub.localizers.ThreeWheelIMULocalizer;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {


    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("lf");
        c.frontRightName.set("rf");
        c.backLeftName.set("lr");
        c.backRightName.set("rr");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.manualBrakeMode.set(true);
    });

    public static ThreeWheelIMUConfig localizerConfig = new ThreeWheelIMUConfig(c -> {
        c.leftEncoderName.set("lf");
        c.rightEncoderName.set("rr");
        c.strafeEncoderName.set("lr");
        c.imuName.set("imu");
        c.imu.set(new RevHubIMU(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        )));
        c.leftPodY.set(4.810025542329538);
        c.rightPodY.set(-4.210339054590725);
        c.strafePodX.set(-2.5644577435777776);
        c.forwardTicksToInches.set(0.0030288917433076304);
        c.strafeTicksToInches.set(0.003020903476974219);
        c.turnTicksToRadians.set(0.003024204807682208);
        c.leftEncoderDirection.set(Encoder.FORWARD);
        c.rightEncoderDirection.set(Encoder.REVERSE);
        c.strafeEncoderDirection.set(Encoder.FORWARD);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.1949324978053331);
                Controller secondaryTranslationalForward = Controller.proportional(0.07202234924679937);
                Controller primaryTranslationalLateral = Controller.proportional(0.46849144028116085);
                Controller secondaryTranslationalLateral = Controller.proportional(0.1730950688620514);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01798346784838083));
                c.brake.set(Controller.proportionalFeedforward(0.015285947671123705));

                c.headingFeedback.set(Controller.proportional(5.006062376902767));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.0424811550194479, 0.004547010325069884));

                c.linearBrakeCoefficients.set(Matrix.diag(0.032280370747378294, 0.041452013311556624));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.002595748412236714, 0.001963103775349472));

                c.maxAchievableForwardVelocity.set(59.14682778980957);
                c.maxAchievableStrafeVelocity.set(45.45077550989337);
                c.naturalForwardDeceleration.set(32.44635410193204);
                c.naturalStrafeDeceleration.set(63.96391076207179);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new ThreeWheelIMULocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}