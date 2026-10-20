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
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.revhub.localizers.RevHubIMU;
import com.pedropathing.revhub.localizers.ThreeWheelIMUConfig;
import com.pedropathing.revhub.localizers.ThreeWheelIMULocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
    });

    public static ThreeWheelIMUConfig oldlocalizerConfig = new ThreeWheelIMUConfig(c -> {
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
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(3.999731409268117);
        c.yPodOffset.set(4.995980750857376);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.30252303377816814);
                Controller secondaryTranslationalForward = Controller.proportional(0.11177417741669345);
                Controller primaryTranslationalLateral = Controller.proportional(0.44405673585816674);
                Controller secondaryTranslationalLateral = Controller.proportional(0.164067098485082);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01917562444734989));
                c.brake.set(Controller.proportionalFeedforward(0.016299280780247404));

                c.headingFeedback.set(Controller.proportional(5.158753565884556));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.051583048222159755, 0.003475579728178239));

                c.linearBrakeCoefficients.set(Matrix.diag(0.0687861488264154, 0.02310997018048379));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0015005509153949903, 0.0021936987691387314));

                c.maxAchievableForwardVelocity.set(58.25871459326176);
                c.maxAchievableStrafeVelocity.set(46.90126623197751);
                c.naturalForwardDeceleration.set(36.64651977581779);
                c.naturalStrafeDeceleration.set(63.33112477156243);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );

    }
}