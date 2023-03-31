// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EncoderConstants;
import frc.robot.Constants.ManipulatorConstants;
import frc.robot.Constants.RobotArmPos;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

public class ArmStage1 extends SubsystemBase {
   	/* Hardware */
   	private final WPI_TalonFX m_ArmStageMotor1 = new WPI_TalonFX(ManipulatorConstants.kArmMotor1, "rio");
   	/** How much smoothing [0,8] to use during MotionMagic */
   	int _smoothing = 0;
	//backup key values not returned from perference table on shuffleboard
	 final double LowScore = 3.2;
	 final double HighScore = 1.5;
	 final double MidScore = 1.0;
	 final double Default = 0.0;
	 final double Floor = 0.5;
	 final double Load = 1.25;
	 //Use to get from the preference table
	 final String ArmStage1High = "ArmStage1 High";
	 final String ArmStage1Mid = "ArmStage1 Mid";
	 final String ArmStage1Low = "ArmStage1 Low";
	 final String ArmStage1Floor = "ArmStage1 Floor";
	 final String ArmStage1Load = "ArmStage1 Station";
	 //local setpoint for moving to position by magic motion
	 private double setPoint;
	 private double backUp;
	 private double feedforward = 0.07;
  	/** Creates a new ArmStage1. */
  public ArmStage1() {
    /* Factory default hardware to prevent unexpected behavior */
	m_ArmStageMotor1.configFactoryDefault();

	/* Configure Sensor Source for Pirmary PID */
	m_ArmStageMotor1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx,
        EncoderConstants.kTimeoutMs);

	/* set deadband to super small 0.001 (0.1 %).
	The default deadband is 0.04 (4 %) */
    m_ArmStageMotor1.configNeutralDeadband(0.001, EncoderConstants.kTimeoutMs);

	/**
	* Configure Talon FX Output and Sensor direction accordingly Invert Motor to
	* have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
	* sensor to have positive increment when driving Talon Forward (Green LED)*/
	m_ArmStageMotor1.setSensorPhase(true);
	m_ArmStageMotor1.setInverted(true);
	/*
	* Talon FX does not need sensor phase set for its integrated sensor
	* This is because it will always be correct if the selected feedback device is integrated sensor (default value)
	* and the user calls getSelectedSensor* to get the sensor's position/velocity. */

	/* Set relevant frame periods to be at least as fast as periodic rate */
	m_ArmStageMotor1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, EncoderConstants.kTimeoutMs);
    m_ArmStageMotor1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, EncoderConstants.kTimeoutMs);
	/* Set the peak and nominal outputs */
	m_ArmStageMotor1.configNominalOutputForward(0, EncoderConstants.kTimeoutMs);
	m_ArmStageMotor1.configNominalOutputReverse(0, EncoderConstants.kTimeoutMs);
	m_ArmStageMotor1.configPeakOutputForward(1, EncoderConstants.kTimeoutMs);
    m_ArmStageMotor1.configPeakOutputReverse(-1, EncoderConstants.kTimeoutMs);
	/* Set Motion Magic gains in slot0 - see documentation */
	m_ArmStageMotor1.selectProfileSlot(EncoderConstants.kSlotIdx, EncoderConstants.kPIDLoopIdx);
    m_ArmStageMotor1.config_kF(EncoderConstants.kSlotIdx, EncoderConstants.kStage1Gains.kF, EncoderConstants.kTimeoutMs);
    m_ArmStageMotor1.config_kP(EncoderConstants.kSlotIdx, EncoderConstants.kStage1Gains.kP, EncoderConstants.kTimeoutMs);
    m_ArmStageMotor1.config_kI(EncoderConstants.kSlotIdx, EncoderConstants.kStage1Gains.kI, EncoderConstants.kTimeoutMs);
    m_ArmStageMotor1.config_kD(EncoderConstants.kSlotIdx, EncoderConstants.kStage1Gains.kD, EncoderConstants.kTimeoutMs);
	/* Set acceleration and vcruise velocity - see documentation */
	m_ArmStageMotor1.configMotionCruiseVelocity(6500, EncoderConstants.kTimeoutMs);
	m_ArmStageMotor1.configMotionAcceleration(800, EncoderConstants.kTimeoutMs);
	/* Zero the sensor once on robot boot up */
	m_ArmStageMotor1.setSelectedSensorPosition(0, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
	SmartDashboard.putNumber("ArmStage1 Encoder", m_ArmStageMotor1.getSelectedSensorPosition(EncoderConstants.kPIDLoopIdx));
  }

public void SetArmStage1ToPosition(String Key) {
    //set up the grab from values at Smart Dashboard perference table
	switch (Key) {
		case RobotArmPos.ScoreLow:;
			//Score Low
		  	backUp = LowScore;
			Key = ArmStage1Low;
			break;
		case RobotArmPos.ScoreMid:;
		    //Score Mid
		    backUp = MidScore;
			Key = ArmStage1Mid;
			break;
		case RobotArmPos.ScoreHigh:;
		    //Score Mid
		    backUp = HighScore;
			Key = ArmStage1High;
			break;
		case RobotArmPos.Default:;
			//default position	
			backUp = Default;
			Key = RobotArmPos.Default;
			break;
		case RobotArmPos.Floor:;
			//Floor pickup
			backUp = Floor;
			Key = ArmStage1Floor;
			break;
		case RobotArmPos.Load:;
			//Load position
			backUp = Load;
			Key = ArmStage1Load;
			break;
		}
	   
	//gets the current value
	setPoint = getPreferencesDouble(Key, backUp);  
	
	/* Motion Magic */
	/* 2048 ticks/rev * x Rotations in either direction */
	double targetPos = setPoint * 2048;
	  
	this.MoveToPosition(targetPos);
  }

  private void MoveToPosition(double targetPos) {
	m_ArmStageMotor1.set(TalonFXControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, feedforward);
  }

	/**
    * Retrieve numbers from the preferences table. If the specified key is in
    * the preferences table, then the preference value is returned. Otherwise,
    * return the backup value, and also start a new entry in the preferences
    * table.
	 * @return 
    */
    private double getPreferencesDouble(String key, double backup) {
		if (!Preferences.containsKey(key)) {
		  Preferences.initDouble(key, backup);
		  Preferences.setDouble(key, backup);
		}
		return Preferences.getDouble(key, backup);
	  }

}
