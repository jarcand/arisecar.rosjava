package network.robot;

import hal.HAL;

public interface RobotInterface {

	public String getName();
	
	public HAL getHAL();
	
	public void sendMessage(org.ros.message.std_msgs.String str);
	

}
