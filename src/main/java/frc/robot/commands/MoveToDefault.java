// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.RobotArmPos;
import frc.robot.subsystems.ArmStage1;
import frc.robot.subsystems.ArmStage2;
import frc.robot.subsystems.Elevator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MoveToDefault extends SequentialCommandGroup {
  /** Creates a new MoveToDefault. */
  public MoveToDefault(Elevator m_Elevator, ArmStage1 m_ArmStage1, ArmStage2 m_ArmStage2) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveArmStage2(m_ArmStage2,RobotArmPos.Default).withTimeout(0.15),
              new MoveArmStage1(m_ArmStage1,RobotArmPos.Default).withTimeout(0.15),
              new MoveElevatorToPosition(m_Elevator, RobotArmPos.Default));
  }
}
