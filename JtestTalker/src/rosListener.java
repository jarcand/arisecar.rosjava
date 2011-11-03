

import org.apache.commons.logging.Log;
import org.ros.message.MessageListener;
import org.ros.node.DefaultNodeFactory;
import org.ros.node.Node;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMain;
import org.ros.node.topic.Subscriber;

/**
 * This is a simple rosjava {@link Subscriber} {@link Node}. It assumes an
 * external roscore is already running.
 * 
 * @author damonkohler@google.com (Damon Kohler)
 */
public class rosListener implements NodeMain {

  private Node node;

  @Override
  public void main(NodeConfiguration configuration) {
    try {
      node = new DefaultNodeFactory().newNode("listener", configuration);
      final Log log = node.getLog();
      node.newSubscriber("chatter", "std_msgs/String",
          new MessageListener<org.ros.message.std_msgs.String>() {
            @Override
            public void onNewMessage(org.ros.message.std_msgs.String message) {
              log.info("I heard: \"" + message.data + "\"");
            }
          });
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
  }

}