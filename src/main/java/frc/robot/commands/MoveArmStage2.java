// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmStage2;

public class MoveArmStage2 extends CommandBase {
  private final ArmStage2 m_armStage2;
  private final String m_Location;

  /** Creates a new MoveArmStage2. */
  public MoveArmStage2(ArmStage2 armStage2, String mLocation) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_armStage2 = armStage2;
    m_Location = mLocation;
    addRequirements(m_armStage2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armStage2.SetArmStage2ToPosition(m_Location);
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
