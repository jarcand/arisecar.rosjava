


import java.io.*;
import java.net.*;


import com.google.common.base.Preconditions;

import network.message.*;
import network.robot.RobotClient;

import org.ros.node.DefaultNodeFactory;
import org.ros.node.Node;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;

/**
 * This is a simple rosjava {@link Publisher} {@link Node}. It assumes an
 * external roscore is already running.
 * 
 * @author ethan.rublee@gmail.com (Ethan Rublee)
 * @author damonkohler@google.com (Damon Kohler)
 */
public class rosTalker implements NodeMain {

  private Node node;

  @Override
  public void main(NodeConfiguration configuration) throws Exception 
  {
    Preconditions.checkState(node == null);
    Preconditions.checkNotNull(configuration);
  
    try {
    	
    	Robot robot = new Robot();
    	RobotClient robotClient = new RobotClient("localhost", 5555, robot);
    	Movement mv = new Movement(1,2,3,4);
    	Message msg = MessageCreator.createMessage(mv, robot.getName(), true);
    	robotClient.sendToServer(mv);    	
    	robotClient.sendToServer(mv);
    	robotClient.sendToServer(mv);
    	
    	
    	
      node = new DefaultNodeFactory().newNode("talker", configuration);
      Publisher<org.ros.message.std_msgs.String> publisher =
          node.newPublisher("chatter", "std_msgs/String");
      int seq = 0;
      while (true) {
        org.ros.message.std_msgs.String str = new org.ros.message.std_msgs.String();
        str.data =  "Hello world! " + seq++;
        publisher.publish(str);
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      if (node != null) {
        node.getLog().fatal(e);
      } else {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void shutdown() {
    node.shutdown();
    node = null;
  }

}