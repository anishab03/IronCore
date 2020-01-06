package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;



public class Arm {
    DcMotor rotate = null;
    DcMotor lift = null;
    Servo claw = null;

    int openPosition=0;//maxium open position
    int closePosition=0;//closed position

    public Arm(DcMotor rotate, DcMotor lift, Servo claw){
        this.rotate = rotate;
        this.lift = lift;
        this.claw = claw;
    }

    public void raiseBar(){
        rotate.setPower(0.25);
    }

    public void lowerBar(){
        rotate.setPower(-0.25);
    }

    public void stopBar(){
        rotate.setPower(0);
    }

    public void raiseLift(){
        lift.setPower(0.25);
    }

    public void lowerLift(){
        lift.setPower(-0.25);
    }

    public void stopLift(){
        lift.setPower(0);
    }
    public void openClaw(){
        claw.setPosition(openPosition);
    }
    public void closeClaw(){
        claw.setPosition(closePosition);
    }
    //don't run this code yet: use servo tester
}

