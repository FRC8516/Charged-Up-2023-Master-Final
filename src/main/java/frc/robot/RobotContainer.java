// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.LedLights;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.Auto1;
import frc.robot.commands.AutoLevel;
import frc.robot.commands.ChangeLedLights;
import frc.robot.commands.CloseGripper;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.MoveToDefault;
import frc.robot.commands.MoveToFloor;
import frc.robot.commands.MoveToHighScore;
import frc.robot.commands.MoveToLoadingStation;
import frc.robot.commands.MoveToLowScore;
import frc.robot.commands.MoveToMidScore;
import frc.robot.commands.OpenGripper;
import frc.robot.commands.SetBrakeMode;
import frc.robot.commands.SetCoastMode;
import frc.robot.subsystems.ArmStage1;
import frc.robot.subsystems.ArmStage2;
import frc.robot.subsystems.CandleControl;
import frc.robot.subsystems.DriveTrain;
//import oi.limelightvision.limelight.frc.LimeLight;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gripper;

public class RobotContainer {
 

  private final SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  //Subsystems that will be used in the rest of the file
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Elevator m_Elevator = new Elevator();
  private final ArmStage1 m_ArmStage1 = new ArmStage1();
  private final ArmStage2 m_ArmStage2 = new ArmStage2();
  private final Gripper m_Gripper = new Gripper();
  public CandleControl m_CandleControl = new CandleControl();
  //Controllers that are used on the robot
  private final CommandXboxController m_driverController = new CommandXboxController(OIConstants.kdriveJoyStick);
  private final CommandXboxController m_actuatorController = new CommandXboxController(OIConstants.kactuatorJoyStick);
  //Commands -- Move actuators to setpoint);
  private final MoveToLowScore m_MoveToLowScore = new MoveToLowScore(m_Elevator,m_ArmStage2,m_ArmStage1);
  private final MoveToMidScore m_MoveToMidScore = new MoveToMidScore(m_Elevator,m_ArmStage2,m_ArmStage1);
  private final MoveToHighScore m_MoveToHighScore = new MoveToHighScore(m_Elevator,m_ArmStage2,m_ArmStage1);
  private final MoveToDefault m_MoveToDefault = new MoveToDefault(m_Elevator, m_ArmStage1, m_ArmStage2);
  private final MoveToFloor m_MoveToFloor = new MoveToFloor(m_Elevator, m_ArmStage2, m_ArmStage1);
  private final MoveToLoadingStation m_MoveToLoadingStation = new MoveToLoadingStation(m_Elevator, m_ArmStage2, m_ArmStage1);
  private final ElevatorUp m_ElevatorUp = new ElevatorUp(m_Elevator);
  private final ElevatorDown m_ElevatorDown = new ElevatorDown(m_Elevator);
  //Commands -- Gripper controls / pneumatics
  private final CloseGripper m_CloseGripper = new CloseGripper(m_Gripper);
  private final OpenGripper m_OpenGripper = new OpenGripper(m_Gripper);
  //Commands -- Led Light controls
  private final ChangeLedLights m_ConeRequestLedLights = new ChangeLedLights(m_CandleControl, LedLights.Yellow);
  private final ChangeLedLights m_CubeRequestLedLights = new ChangeLedLights(m_CandleControl, LedLights.Purple);
  //Set brakes drive train
  private final SetBrakeMode m_SetBrakes = new SetBrakeMode();
  //Set Coast drive train
  private final SetCoastMode m_CoastMode = new SetCoastMode();

  //Autonomous
  private final Auto1 m_auto1 = new Auto1(m_Elevator, m_ArmStage1, m_ArmStage2, m_Gripper, m_driveTrain);
  private final AutoLevel m_AutoLevel = new AutoLevel(0, m_driveTrain);
 /******************************************************************************************
  ONLY used for testing!  Not to used for competition!
  private final TestElevator m_TestElevator = new TestElevator(m_ArmStage2, "Score_LL");
  private final TestElevator2 m_TestElevator2 = new TestElevator2(m_ArmStage2, "Score_ML");
 ****************************************************************************************** */
// LimeLight 
// The limelight is used a couple times here, the declaration is separated from the other subsystems
// because it is extra special
 // private final LimeLight m_limeLight = new LimeLight();

  public RobotContainer() {
    //Driving the robot with right stick
    m_driveTrain.setDefaultCommand(new RunCommand(
      () -> m_driveTrain.drive(m_driverController.getRightY(), m_driverController.getRightX()),m_driveTrain));
    //Auto selection
     m_autoChooser.addOption("AutoLevel", m_AutoLevel);
     m_autoChooser.setDefaultOption("Auto 1", m_auto1);
      configureBindings();
  }

  private void configureBindings() {
    //Configure Joysticks actuators
    m_actuatorController.a().onTrue(m_MoveToLowScore);
    m_actuatorController.x().onTrue(m_MoveToMidScore);
    m_actuatorController.y().onTrue(m_MoveToHighScore);
    m_actuatorController.b().onTrue(m_MoveToFloor);
    m_actuatorController.rightTrigger().onTrue(m_MoveToDefault);
    m_actuatorController.leftTrigger().onTrue(m_MoveToLoadingStation);
    m_actuatorController.povUp().onTrue(m_ElevatorUp);
    m_actuatorController.povDown().onTrue(m_ElevatorDown);

    //Gripper open/close
    m_driverController.rightTrigger().onTrue(m_OpenGripper);
    m_driverController.leftTrigger().onTrue(m_CloseGripper);
    //Set brakes
    m_driverController.leftBumper().onTrue(m_SetBrakes);
    m_driverController.rightBumper().onTrue(m_CoastMode);
     
    //Request game pieces to human player by changing led lights
    m_actuatorController.rightBumper().onTrue(m_ConeRequestLedLights);
    m_actuatorController.leftBumper().onTrue(m_CubeRequestLedLights);

    SmartDashboard.putData("AutoSelection",m_autoChooser);
  }
  
  public Command getAutonomousCommand() {
   return m_autoChooser.getSelected();
   //return null;
  }

}
