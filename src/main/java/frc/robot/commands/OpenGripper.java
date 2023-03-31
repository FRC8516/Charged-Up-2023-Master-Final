// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Gripper;

public class OpenGripper extends CommandBase {
  private final Gripper m_gripperOpen;
  
  /** Creates a new OpenGripper. */
  public OpenGripper(Gripper gripperOpen) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_gripperOpen = gripperOpen;
    addRequirements(m_gripperOpen);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_gripperOpen.OpenGripper();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
