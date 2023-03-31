// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmStage2;

public class TestElevator2 extends CommandBase {

  private final ArmStage2 m_elevator;
  private final String m_Location;
  
  /** Creates a new TestElevator. */
  public TestElevator2(ArmStage2 elevator, String mLocation) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_elevator = elevator;
    m_Location = mLocation;
    addRequirements(m_elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevator.SetArmStage2ToPosition(m_Location);
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
