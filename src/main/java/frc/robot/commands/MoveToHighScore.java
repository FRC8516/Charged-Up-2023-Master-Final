// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants.RobotArmPos;
import frc.robot.subsystems.ArmStage1;
import frc.robot.subsystems.ArmStage2;
import frc.robot.subsystems.Elevator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MoveToHighScore extends ParallelCommandGroup {
  /** Creates a new MoveToHighScore. */
  public MoveToHighScore(Elevator mElevator, ArmStage2 mArmStage2, ArmStage1 mArmStage1) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new MoveElevatorToPosition(mElevator, RobotArmPos.ScoreHigh),
                new MoveArmStage2(mArmStage2, RobotArmPos.ScoreHigh),
                new MoveArmStage1(mArmStage1, RobotArmPos.ScoreHigh));

  }
} 