// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {

    //Drive Constants
    public static final class DriveConstants {
         //left drive
        public static final int kFrontLeftChannel = 1;
        public static final int kRearLeftChannel = 2;
        //right drive
        public static final int kFrontRightChannel = 3;
        public static final int kRearRightChannel = 4;
        //pigeon IMU
        public static final int kPigeon = 1;
    }

    public static final class ManipulatorConstants {
        //Arm
        public static final int kArmMotor1 = 5;
        public static final int kArmMotor2 = 6;
        //Elevator
        public static final int kElevatorMotor = 7;
    }

    //OI Constants
    public static final class OIConstants {
        //Controllers
        public static final int kdriveJoyStick = 0;
        public static final int kactuatorJoyStick = 1;
        public static final int CANdleID = 1;
    }    
    
    public static final class EncoderConstants {
        /**
	    * Which PID slot to pull gains from. Starting 2018, you can choose from
	    * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	    * configuration.
	    */
	    public static final int kSlotIdx = 0;

	    /**
	    * Talon FX supports multiple (cascaded) PID loops. For
	    * now we just want the primary one.
	    */
	    public static final int kPIDLoopIdx = 0;

	    /**
	    * set to zero to skip waiting for confirmation, set to nonzero to wait and
	    * report to DS if action fails.
	    */
	    public static final int kTimeoutMs = 40;

	    /**
	    * Gains used in Motion Magic, to be adjusted accordingly
        * Gains(kp, ki, kd, kf, izone, peak output);
        */
        public static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.2, 0, 1.0);
        public static final Gains kStage1Gains = new Gains(0.1023,0.0,0.0,0.503,0,1.0);
        public static final Gains kStage2Gains = new Gains(0.2,0.0,0.0,0.2,0,1);
    }

    // Robot position constants
    public static final class RobotArmPos {
        //Key values for passing from commands to subsystems
        public static final String ScoreHigh = "Score_High";
        public static final String ScoreMid  = "Score_Mid";
        public static final String ScoreLow = "Score_Low";
        public static final String Default = "Default";
        public static final String Floor = "Floor";
        public static final String Load = "LoadStation";
        public static final String BumpUp = "BumpUp";
        public static final String BumpDown = "BumpDown";
    }

    //Led lights
    public static final class LedLights {
        public static final String Yellow = "Yellow";
        public static final String Purple = "Purple";
        public static final String Red = "Red";
        public static final String Blue = "Blue";
        public static final String Green = "Green";
        public static final String Orange = "Orange";
    }
}

