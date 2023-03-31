// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveTrain;
import oi.limelightvision.limelight.frc.LimeLight;

public class Drive_With_Limelight extends CommandBase {
  private final DriveTrain m_drivetrain;
  private LimeLight m_limelight;
  private CommandXboxController m_joystick;


  /** Creates a new Drive_With_Limelight. */
  public Drive_With_Limelight(CommandXboxController joystick, LimeLight limelight, DriveTrain drivetrain) {
    m_drivetrain = drivetrain;
    m_joystick = joystick;
    m_limelight = limelight;
    addRequirements(m_drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_limelight.setPipeline(1);
    SmartDashboard.putNumber("kp", 0.03);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double zRotation;
   // double kp = .005;
    double kp = SmartDashboard.getNumber("kp", .005);

    if(m_limelight.getIsTargetFound()){
      zRotation = m_limelight.getdegRotationToTarget() * kp;
    }else{
      zRotation = 0.0;
    }
    /* SmartDashboard.putNumber("zRotation", zRotation);
    SmartDashboard.putNumber("Joy zRotation", m_joystick.getZ());
    zRotation = 0; */

    m_drivetrain.drive(m_joystick.getRightY(), zRotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_limelight.setPipeline(0);
    m_drivetrain.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}