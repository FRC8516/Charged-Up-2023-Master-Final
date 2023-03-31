// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CandleControl;

public class ChangeLedLights extends CommandBase {
  private final CandleControl m_CandleControl;
  private final String m_Color;
  
  /** Creates a new ChangeLedLights. */
  public ChangeLedLights(CandleControl mControl, String mColor) {
    m_CandleControl = mControl;
    m_Color = mColor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_CandleControl);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_CandleControl.ChangeLedColor(m_Color);
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
