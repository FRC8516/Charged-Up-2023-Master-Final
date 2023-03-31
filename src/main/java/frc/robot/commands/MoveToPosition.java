package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveToPosition extends CommandBase {
    private final DriveTrain m_driveTrain;
    private final double m_speed;
      
    public MoveToPosition(DriveTrain driveTrain, double speed) {
      m_driveTrain = driveTrain;
      m_speed = speed;
      addRequirements(m_driveTrain);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_driveTrain.autoDrive(m_speed);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      m_driveTrain.stopMotion();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
    
}
