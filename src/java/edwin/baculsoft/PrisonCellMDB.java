package edwin.baculsoft;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;


@MessageDriven(name = "prisonCellMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination",propertyValue = "prisonQueue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Auto-acknowledge")
})
@Path("/prison")
public class PrisonCellMDB implements MessageListener {

  @Override
  public void onMessage(Message message){
    TextMessage tm = (TextMessage) message;
    try {
      System.out.println("message received: " + tm.getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
  
  @GET
  @Produces("text/plain")
  public String getData(){
      System.out.println("hitting rest endpoint");
      return "i feel i like it ease";
  }
}
