// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Gripper extends SubsystemBase {
  // DoubleSolenoid corresponds to a double solenoid.
  private final DoubleSolenoid m_doubleSolenoid =
      new DoubleSolenoid(PneumaticsModuleType.REVPH, 13, 15);
  // Create constructor for pneumatic hub.
  private PneumaticHub mPneumaticHub = new PneumaticHub();
  private int kSolenoidButton;

  /** Creates a new Gripper. */
  public Gripper() {
    //Enable the compressor...either have to use digital or analog here.  Else compressor does not run!
    mPneumaticHub.enableCompressorDigital();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void OpenGripper() {
    if(kSolenoidButton == 1) 
    m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    kSolenoidButton = 0;
  }

  public void CloseGripper() {
    if(kSolenoidButton == 0) {
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
      kSolenoidButton = 1;
    }
  }

  public void Stop(){
    return;
  }

}
