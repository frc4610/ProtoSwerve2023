// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.Map;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.SwerveDriveMK4;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The enum used as keys for selecting the command to run.
  private final SwerveDriveMK4 m_drivetrainSubsystem = new SwerveDriveMK4();
  private final XboxController m_Controller = new XboxController(0);
 // private final DriveCommand m_DriveCommand = new DriveCommand(m_drivetrainSubsystem, 0.0, 0.0, 0.0);


  public RobotContainer() {
  //  CommandScheduler.getInstance().registerSubsystem(m_drivetrainSubsystem);
//CommandScheduler.getInstance().registerSubsystem(m_drivetrainSubsystem);
 
m_drivetrainSubsystem.setDefaultCommand(new DriveCommand(
    m_drivetrainSubsystem, 
     this::getForwardInput,
      this::getStrafeInput,
     this::getRotationInput
  ));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }

  private static double deadband(double value, double tolerance){
    if(Math.abs(value) < tolerance)
    return 0.0;
  
    return Math.copySign(value, (value - tolerance) / (1.0 - tolerance));
  } 
  
    private static double square(double value){
      return Math.copySign(value * value, value);
    }

  private double getForwardInput(){
    return -square(deadband(m_Controller.getLeftY(), 0.1)) * SwerveDriveMK4.MAX_VELOCITY_METERS_PER_SECOND;
  }

  private double getStrafeInput(){
    return -square(deadband(m_Controller.getLeftX(), 0.1)) * SwerveDriveMK4.MAX_VELOCITY_METERS_PER_SECOND;
  }
  private double getRotationInput(){
    return -square(deadband(m_Controller.getRightX(), 0.1)) * SwerveDriveMK4.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   */
  /*public Command getAutonomousCommand() {
    return null;
  
  }

  public static void startDrive(){

  }*/


}
