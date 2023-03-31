// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmStage1;

public class MoveArmStage1 extends CommandBase {

  private final ArmStage1 m_armstage1;
  private final String m_Location;

  /** Creates a new MoveArmStage1Postion. */
  public MoveArmStage1(ArmStage1 armStage1, String mLocation) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_armstage1 = armStage1;
    m_Location = mLocation;
    addRequirements(m_armstage1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_armstage1.SetArmStage1ToPosition(m_Location);
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
