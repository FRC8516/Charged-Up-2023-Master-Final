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
public class MoveToLowScore extends SequentialCommandGroup {
  /** Creates a new MoveToLowScore. */
  public MoveToLowScore(Elevator mElevator, ArmStage2 mArmStage2, ArmStage1 mArmStage1) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveArmStage2(mArmStage2, RobotArmPos.ScoreLow).withTimeout(0.15),
                new MoveElevatorToPosition(mElevator, RobotArmPos.ScoreLow).withTimeout(0.15),
                new MoveArmStage1(mArmStage1, RobotArmPos.ScoreLow));
  }
} 