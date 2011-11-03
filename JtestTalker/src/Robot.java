import org.ros.node.DefaultNodeFactory;
import org.ros.node.Node;
import org.ros.node.topic.Publisher;

import hal.HAL;
import network.robot.*;

public class Robot implements RobotInterface
{
	private Node node;
	
	public Robot()
	{
		
	}
	
	
	@Override
	public String getName()
	{
		return "robot";
	}
	
	@Override
	public HAL getHAL()
	{
		return null;
	}

	@Override
	public void sendMessage(org.ros.message.std_msgs.String str) {
		Publisher<org.ros.message.std_msgs.String> publisher =
		          node.newPublisher("chatter", "std_msgs/String");
		
	}
	
	
	
	
}
