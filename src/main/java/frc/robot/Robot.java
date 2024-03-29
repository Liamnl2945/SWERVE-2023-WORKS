// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix6.Orchestra;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

//import com.ctre.phoenix6.Orchestra;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	private final SendableChooser<String> autonomousSelector = new SendableChooser<>();
	public static CTREConfigs ctreConfigs;
	private Command m_autonomousCommand;
	private RobotContainer m_robotContainer;
	


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
	@Override
	public void robotInit() {
		autonomousSelector.setDefaultOption("Liam smokes Crack(Place & BackUp)", "Default");
		autonomousSelector.addOption("Balance", "Balance");
		autonomousSelector.addOption("The Meth head is a munch (SHoot and Balance", "THE GOAT");
		
		SmartDashboard.putData(autonomousSelector);
		
		ctreConfigs = new CTREConfigs();
		// Instantiate our RobotContainer. This will perform all our button bindings,
		// and put our
		// autonomous chooser on the dashboard.
		m_robotContainer = new RobotContainer();
		PortForwarder.add(5800, "10.29.45.10", 5800);

		// Camera 
		UsbCamera camera = CameraServer.startAutomaticCapture();
		// Optionally set camera properties like resolution and FPS
		// camera.setResolution(320, 240);
		// camera.setFPS(30);
		ShuffleboardTab tab = Shuffleboard.getTab("My Camera Tab");
		tab.add("Camera Feed", camera).withWidget("CameraStream");

	}


  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
	@Override
	public void autonomousInit() {

	}
	

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when teleop starts running. 
		// If you want the autonomous to continue until interrupted by another command, remove this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
			
		}
	}

	/** This function is called periodically during operator control. */
	@Override
	public void teleopPeriodic() {

	}

	@Override
	public void testInit() {
		// Cancels all running commands at the start of test mode.
		CommandScheduler.getInstance().cancelAll();
	}

	/** This function is called periodically during test mode. */
	@Override
	public void testPeriodic() {
		
	}
}
