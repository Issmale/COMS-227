package hw1;

/**
 * 
 * @author Issmale Bekri
 *
 */
public class CameraBattery{
	/**
	 * Number of external charger settings. Settings are numbered between 0 inclusive and 4 exclusive. 
	 */
	public static final int NUM_CHARGER_SETTINGS = 4; 
	/**
	 * A constant used in calculating the charge rate of the external charger. 
	 */
	public static final double CHARGE_RATE = 2.0; 
	/**
	 * Default power consumption of the camera at the start of simulation. 
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0; 
	
	/**
	 * level of battery charge
	 */
	
	// Instance variables
	private double chargeOfBattery;
	private double cameraBatteryCharge;
	private double amountOfDrain;
	private double totalAmountDrained;
	private double maxBattery;
	private double cameraChargerAmount;
	private double cameraPowerConsumption;
	private double chargeRate;
	private double externalCharge;
	private double batteryChargeBeforeCameraCharge;
	private double batteryChargeWhenLastDrained;
	private double initialBatteryExternalCharge;
	private int chagerSetting;
	
	
	/**
	 * constructor 
	 * @param batteryStartingCharge The starting battery charge 
	 * @param batteryCapacity maximum charge capacity are given.
	 */
	public CameraBattery(double batteryStartingCharge,double batteryCapacity) {
		
		/*
		 * Constructs a new camera battery simulation
		 */
		chargeOfBattery = Math.min(batteryStartingCharge, batteryCapacity);
		cameraBatteryCharge = 0;
		totalAmountDrained = 0;
		amountOfDrain = 0;
		chagerSetting = 0;
		externalCharge = 0;
		maxBattery = batteryCapacity;
		cameraPowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
		chargeRate = 0;
		batteryChargeWhenLastDrained = 0;
		chagerSetting = 0;
	}
	
	/**
	 * accessor
	 * @return Get the battery's current charge. 
	 */
	public double getBatteryCharge() {
		
		return chargeOfBattery;
	}
	/**
	 * accessor
	 * @return Get the current charge of the camera's battery. 
	 */
	public double getCameraCharge() {
		
		return cameraBatteryCharge;
	}

	/**
	 * mutator 
	 */
	public void moveBatteryCamera() {
		
		cameraBatteryCharge = chargeOfBattery;
		cameraPowerConsumption = DEFAULT_CAMERA_POWER_CONSUMPTION;
		chargeRate = CHARGE_RATE;
		batteryChargeWhenLastDrained = chargeOfBattery;

		
		
	}

	/**
	 * accessor
	 * @return Get the total amount of power drained from the battery since the last time the battery monitor 
		was started or reset. 
	 */
	public double getTotalDrain() {
		
		return totalAmountDrained;
	}

	/**
	 * accessor
	 * @return Get the power consumption of the camera. 
	 */
	public double getCameraPowerConsumption() {
		
		return cameraPowerConsumption;
	}

	/**
	 * accessor
	 * @param minutes Drains the battery connected to the camera (assuming it is connected) for a given number of 
		minutes.
	 * @return the actual amount drain from the battery. 
	 */
	public double drain(double minutes) {
			
		amountOfDrain = Math.min(minutes * cameraPowerConsumption, batteryChargeWhenLastDrained);
		chargeOfBattery -=amountOfDrain;
		batteryChargeWhenLastDrained = chargeOfBattery;
		cameraBatteryCharge -= amountOfDrain;
		totalAmountDrained = Math.min(totalAmountDrained + amountOfDrain, maxBattery);
			
		return amountOfDrain;
	}

	/**
	 * accessor
	 * @param minutes Charges the battery connected to the camera (assuming it is connected) for a given number of 
		minutes. 
	 * @return the actual amount the battery has been charged. 
	 */
	public double cameraCharge(double minutes) {
		
		
		batteryChargeBeforeCameraCharge = chargeOfBattery;	
		chargeOfBattery = Math.min((chargeOfBattery + (chargeRate * minutes)),maxBattery);
		cameraBatteryCharge = Math.min((cameraBatteryCharge + chargeRate * minutes),maxBattery);
		cameraChargerAmount = Math.max(0,chargeOfBattery - batteryChargeBeforeCameraCharge);
		
		return cameraChargerAmount;
	}

	/**
	 * mutator 
	 */
	public void removeBattery() {

		cameraBatteryCharge = 0;
		cameraPowerConsumption = 0;
		chargeRate = 0;
		
	}

	/**
	 * mutator 
	 */
	public void moveBatteryExternal() {
		
		chargeRate = CHARGE_RATE;
		cameraBatteryCharge = 0;
		
	}

	/**
	 * accessor
	 * @param minutes Charges the battery connected to the external charger (assuming it is connected) for a given 
		number of minutes. 
	 * @return the actual amount the battery has been charged. 
	 */
	public double externalCharge(double minutes) {
		
		initialBatteryExternalCharge = chargeOfBattery;
		externalCharge = Math.min(minutes * chagerSetting * chargeRate, maxBattery);
		chargeOfBattery = Math.min(externalCharge + chargeOfBattery,maxBattery);
		externalCharge = Math.max(0, chargeOfBattery - initialBatteryExternalCharge);
		return externalCharge;
	}

	/**
	 * accessor
	 * @return Get the external charger setting. 
	 */
	public int getChargerSetting() {
		
		return chagerSetting;
	}

	/**
	 * mutator 
	 */
	public void buttonPress() {
		
		chagerSetting +=1;
		chagerSetting = chagerSetting % NUM_CHARGER_SETTINGS;
		
		
	}
	/**
	 * 
	 * @param CameraPowerConsumption Set the power consumption of the camera. 
	 */
	public void setCameraPowerConsumption(double CameraPowerConsumption)  {
		cameraPowerConsumption = CameraPowerConsumption;
		
	}
	/**
	 * 
	 */
	public void resetBatteryMonitor() {
		
		totalAmountDrained = 0;
	}
	/**
	 * 
	 * @return Get the battery's capacity. 
	 */
	public double getBatteryCapacity() {
		
		return maxBattery;
	}

}
