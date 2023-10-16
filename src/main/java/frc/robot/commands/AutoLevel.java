package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoLevel extends CommandBase {
    private final DriveTrain m_drive;
    private double m_deg;
    private double m_xSpeed;
    private Pose2d m_startPose;
    private long m_lastTime;
    private double m_lastPitch;
    private double m_lastVel = 0;
    private boolean m_end = false;

    private enum SignChange {
        POSITIVE,
        NEGATIVE
    };

    private SignChange m_sign = null;

    private boolean m_onStation = false;

    public AutoLevel(double xSpeed, DriveTrain drive) {
        m_xSpeed = xSpeed;
        m_drive = drive;
        addRequirements(m_drive);
    }

    @Override
    public void initialize() {
        m_onStation = false;
        m_lastVel = 0;
        m_end = false;
        m_sign = null;
        m_deg = m_drive.getRobotPitch().getDegrees();
        m_lastTime = RobotController.getFPGATime();
        m_lastPitch = m_drive.getRobotPitch().getDegrees();
    }

    @Override
    public void execute() {
        if (Math.abs(m_drive.getRobotPitch().getDegrees() - 2) > 15 && !m_onStation) {
            System.out.println("on station");
            m_onStation = true;
        }
        if (Math.abs(m_drive.getRobotPitch().getDegrees() - 2) < 14 && m_onStation) {
            System.out.println("end");
            end(false);
        } else if (m_onStation) {
          //  m_drive.drive(m_xSpeed, 0);
            System.out.println(m_drive.getRobotPitch().getDegrees());
        } else {
           // m_drive.drive(m_xSpeed * 2, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
       // m_drive.drive(0, 0);
        m_end = true;
    }

    @Override
    public boolean isFinished() {
        return m_end;
    }
}