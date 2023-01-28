package frc.robot.commands;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.SwerveDriveMK4; //Drivebase Subsystem

public class DriveCommand extends CommandBase {

  public  SwerveDriveMK4 m_drivetrainSubsystem;

  private  Double m_translationXSupplier;
  private  Double m_translationYSupplier;
  private  Double m_rotationSupplier;

  
public DriveCommand(SwerveDriveMK4 drivetrainSubsystem,
  Double translationXSupplier,
  Double translationYSupplier,
  Double rotationSupplier) {
this.m_drivetrainSubsystem = drivetrainSubsystem;
this.m_translationXSupplier = translationXSupplier;
this.m_translationYSupplier = translationYSupplier;
this.m_rotationSupplier = rotationSupplier;

addRequirements(drivetrainSubsystem);
}


@Override
public  void execute() {
// You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
m_drivetrainSubsystem.drive( ChassisSpeeds.fromFieldRelativeSpeeds(
m_translationXSupplier,
m_translationYSupplier,
m_rotationSupplier,
m_drivetrainSubsystem.getGyroscopeRotation()
));
}

public  boolean isFinished(){
 return false;
}

@Override
public  void end(boolean interrupted) {
m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
}
}
