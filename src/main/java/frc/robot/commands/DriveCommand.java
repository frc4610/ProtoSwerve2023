package frc.robot.commands;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.subsystems.SwerveDriveMK4; //Drivebase Subsystem
import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

  private  SwerveDriveMK4 m_drivetrainSubsystem;

  private  DoubleSupplier m_translationXSupplier;
  private  DoubleSupplier m_translationYSupplier;
  private  DoubleSupplier m_rotationSupplier;

public void DefaultDriveCommand(SwerveDriveMK4 drivetrainSubsystem,
  DoubleSupplier translationXSupplier,
  DoubleSupplier translationYSupplier,
  DoubleSupplier rotationSupplier) {
this.m_drivetrainSubsystem = drivetrainSubsystem;
this.m_translationXSupplier = translationXSupplier;
this.m_translationYSupplier = translationYSupplier;
this.m_rotationSupplier = rotationSupplier;

addRequirements(drivetrainSubsystem);
}

@Override
public void execute() {
// You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
m_drivetrainSubsystem.drive(
ChassisSpeeds.fromFieldRelativeSpeeds(
m_translationXSupplier.getAsDouble(),
m_translationYSupplier.getAsDouble(),
m_rotationSupplier.getAsDouble(),
m_drivetrainSubsystem.getGyroscopeRotation()
)
);
}

@Override
public void end(boolean interrupted) {
m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
}
}
