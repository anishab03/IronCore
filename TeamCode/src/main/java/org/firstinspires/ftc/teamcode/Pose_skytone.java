package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


public class Pose_skytone {
    HardwareMap hwmap;

    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor rotate = null;
    private DcMotor lift = null;
    private Servo clawServo = null;

    double powerFrontLeft;
    double powerFrontRight;
    double powerBackRight;
    double powerBackLeft;

    Arm arm;

    public Pose_skytone(){}

    public void init(HardwareMap hwmap){
        this.hwmap = hwmap;

        frontLeftDrive  = hwmap.get(DcMotor.class, "front_left");
        frontRightDrive = hwmap.get(DcMotor.class, "front_right");
        backLeftDrive  = hwmap.get(DcMotor.class, "back_left");
        backRightDrive = hwmap.get(DcMotor.class, "back_right");

        rotate = hwmap.get(DcMotor.class, "rotate_motor");//in config, name rotate_motor
        lift = hwmap.get(DcMotor.class, "lift_motor");//in config, name rotate_motor
        clawServo = hwmap.get(Servo.class, "claw");

        arm = new Arm(rotate, lift, clawServo);

        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }


    /**
     * drive method for a mecanum drive
     * @param forward sets how much power will be provided in the forwards direction
     * @param strafe sets how much power will be provided in the left strafe direction
     * @param rotate sets how much power will be provided to clockwise rotation
     */
    public void driveMixerMec(double forward, double strafe , double rotate){

        //reset the power of all motors
        powerBackRight = 0;
        powerFrontRight = 0;
        powerBackLeft = 0;
        powerFrontLeft = 0;

        //set power in the forward direction
        powerFrontLeft = forward;
        powerBackLeft = forward;
        powerFrontRight = forward;
        powerBackRight = forward;

        //set power in the left strafe direction
        powerFrontLeft += -strafe;
        powerFrontRight += strafe;
        powerBackLeft += strafe;
        powerBackRight += -strafe;

        //set power in the clockwise rotational direction
        powerFrontLeft += rotate;
        powerBackLeft += rotate;
        powerFrontRight += -rotate;
        powerBackRight += -rotate;

        //provide power to the motors
        frontLeftDrive.setPower(clampMotor(powerFrontLeft));
        backLeftDrive.setPower(clampMotor(powerBackLeft));
        frontRightDrive.setPower(clampMotor(powerFrontRight));
        backRightDrive.setPower(clampMotor(powerBackRight));

    }

    public double clampMotor(double power){
        double result = power;
        if (power>1){
            result = 1;
        }
        if (power <-1){
            result = -1;
        }
        return result;
    }

}
