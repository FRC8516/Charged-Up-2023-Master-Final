// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmStage1;
import frc.robot.subsystems.ArmStage2;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gripper;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoBalence extends SequentialCommandGroup {
  /** Creates a new Auto1. */
  public AutoBalence(Elevator m_Elevator, ArmStage1 m_ArmStage1, ArmStage2 m_ArmStage2, Gripper m_Gripper, DriveTrain m_driveTrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
     // new CloseGripper(m_Gripper).withTimeout(0.5),
     // new MoveToHighScore(m_Elevator, m_ArmStage2, m_ArmStage1).withTimeout(3),
    //  new MoveToPosition(m_driveTrain, .15).withTimeout(1.25),
     // new OpenGripper(m_Gripper).withTimeout(1.5),
     // new MoveToPosition(m_driveTrain, -0.20).withTimeout(0.75),
      new MoveToDefault(m_Elevator, m_ArmStage1, m_ArmStage2).withTimeout(2),
      new SetBrakeMode(),
      new AutoLevel(0.5, m_driveTrain)
    );
  }
}
