package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Module;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;

public class RobotTemplate extends SimpleRobot {

    RobotDrive chassis = new RobotDrive(1, 2); //drive function for the robot (contorls robots motors)
    //joystick
    Joystick leftstick = new Joystick(1); //Joystick 1 (driving stick)
    Joystick rightstick = new Joystick(2); //Joystick 2 (robotstick; motors and pnumatics)

    JoystickButton J1BTrig = new JoystickButton(leftstick, 1);
    JoystickButton J1B2 = new JoystickButton(leftstick, 2);
    JoystickButton J1B3 = new JoystickButton(leftstick, 3);
    JoystickButton J1B4 = new JoystickButton(leftstick, 4);
    JoystickButton J1B5 = new JoystickButton(leftstick, 5);
    JoystickButton J1B6 = new JoystickButton(leftstick, 6);
    JoystickButton J1B7 = new JoystickButton(leftstick, 7);
    JoystickButton J1B8 = new JoystickButton(leftstick, 8);
    JoystickButton J1B9 = new JoystickButton(leftstick, 9);
    JoystickButton J1B10 = new JoystickButton(leftstick, 10);
    JoystickButton J1B11 = new JoystickButton(leftstick, 11);
    JoystickButton J1B12 = new JoystickButton(leftstick, 12);

    Jaguar JaguarL = new Jaguar(5);  // Pnumatics
    Talon TalonUp = new Talon(3);   // Compressors [arms]
    Talon TalonDown = new Talon(4);  //Compressors [arms]
    Solenoid SolenoidLaunch = new Solenoid(1, 1);  // Pnumatics
    Compressor Comp2 = new Compressor(1, 1);  //Pnumatics

    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void robotInit() {

    }

    public RobotTemplate() {

    }

    public void autonomous() {
        boolean runauto;

        chassis.setSafetyEnabled(false);
        runauto = true;
        while (runauto == true) {

            //Drives foward, turns right, continues forward, spins around, then stops [TEST]
            chassis.drive(0.0, 0.0);
            Timer.delay(0.5);
            chassis.drive(1.0, 0.0);
            Timer.delay(3.0);
            chassis.drive(1.0, 0.56);
            Timer.delay(0.75);
            chassis.drive(1.0, 0.0);
            Timer.delay(5.0);
            chassis.drive(1.0, 1.0);
            Timer.delay(2.0);
            chassis.drive(0.0, 0.0);
            runauto = false;

        }

    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        boolean J1BTrigPressed;
        boolean J1B2Pressed;
        boolean J1B3Pressed;
        boolean J1B4Pressed;
        boolean J1B5Pressed;
        boolean J1B6Pressed;
        boolean J1B7Pressed;
        boolean J1B8Pressed;
        boolean J1B9Pressed;
        boolean J1B10Pressed;
        boolean J1B11Pressed;
        boolean J1B12Pressed;

        boolean J1B1State = false;
        boolean J1B2State = false;
        boolean J1B3State = false;
        boolean J1B4State = false;
        boolean J1B5State = false;
        boolean J1B6State = false;
        boolean J1B7State = false;
        boolean J1B8State = false;
        boolean J1B9State = false;
        boolean J1B10State = false;
        boolean J1B11State = false;
        boolean J1B12State = false;

        Comp2.start();
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        chassis.setMaxOutput(1.0);

        chassis.setSafetyEnabled(false);

        while (isOperatorControl() && isEnabled()) {
            J1BTrigPressed = J1BTrig.get();
            J1B2Pressed = J1B2.get();
            J1B3Pressed = J1B3.get();
            J1B4Pressed = J1B4.get();
            J1B5Pressed = J1B5.get();
            J1B6Pressed = J1B6.get();
            J1B7Pressed = J1B7.get();
            J1B8Pressed = J1B8.get();
            J1B9Pressed = J1B9.get();
            J1B10Pressed = J1B10.get();
            J1B11Pressed = J1B11.get();
            J1B12Pressed = J1B12.get();

            chassis.arcadeDrive(leftstick);
            System.out.println(J1BTrig.get() + " || " + J1B2.get());
            System.out.println(J1B3.get() + " || " + J1B4.get());
            System.out.println(J1B5.get() + " || " + J1B6.get());
            System.out.println(J1B7.get() + " || " + J1B8.get());
            System.out.println(J1B9.get() + " || " + J1B10.get());
            System.out.println(J1B11.get() + " || " + J1B12.get());

            //Arm Down
            if (J1BTrigPressed && J1B1State == false) {
                JaguarL.set(-1.0);
                J1B1State = true;

            }
            if (J1BTrigPressed == false && J1B1State == true) {
                JaguarL.set(0.0);
                J1B1State = false;

            }

            //Arm Up
            if (J1B3Pressed && J1B3State == false) {
                JaguarL.set(1.0);
                J1B3State = true;

            }
            if (J1B3Pressed == false && J1B3State == true) {
                JaguarL.set(0.0);
                J1B3State = false;

            }
            if (J1B4Pressed) {
                //no function    
            }
            if (J1B5Pressed) {
                //no function
            }
            //UpperLauncher Shooter (outwards)
            if (J1B6Pressed && J1B6State == false) {
                TalonUp.set(-1.0);
                J1B6State = true;
            } else if (J1B6Pressed == false && J1B6State == true) {
                TalonUp.set(0.0);
                J1B6State = false;
            }
            //LowerLauncher Shooter (outwards)
            if (J1B7Pressed && J1B7State == false) {
                TalonDown.set(1.0);
                J1B7State = true;

            } else if (J1B7Pressed == false && J1B7State == true) {
                TalonDown.set(0.0);
                J1B7State = false;

            }
            //UpperLevel Retriever (Inwards)
            if (J1B8Pressed && J1B8State == false) {
                TalonUp.set(1.0);
                J1B8State = true;
            } else if (J1B8Pressed == false && J1B8State == true) {
                TalonUp.set(0.0);
                J1B8State = false;
            }
            //LowerLevel Retriever (Inwards)
            if (J1B9Pressed && J1B9State == false) {
                TalonDown.set(-1.0);
                J1B9State = true;
            } else if (J1B9Pressed == false && J1B9State == true) {
                TalonDown.set(0.0);
                J1B9State = false;
            }
            //Solenoid (Arms)
            if (J1B10Pressed && J1B10State == false) {
                SolenoidLaunch.set(true);
                J1B10State = true;

            } else if (J1B10Pressed == false && J1B10State == true) {
                SolenoidLaunch.set(false);
                J1B10State = false;

            }
            if (J1B11Pressed) {
                //no function
            }
            if (J1B12Pressed) {
                //no function
            }
        }
    }

    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {

    }
}
