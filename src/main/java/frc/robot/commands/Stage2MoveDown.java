// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmStage2;

public class Stage2MoveDown extends CommandBase {
  private final ArmStage2 m_ArmStage2;
  /** Creates a new StageMoveDown. */
  public Stage2MoveDown(ArmStage2 mArmStage2) {
    m_ArmStage2 = mArmStage2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_ArmStage2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
