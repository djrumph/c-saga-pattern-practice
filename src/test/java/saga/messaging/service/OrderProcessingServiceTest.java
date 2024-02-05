package saga.messaging.service;

import java.util.Stack;
import org.junit.jupiter.api.Test;


class OrderProcessingServiceTest {

  @Test
  void  stackPractice(){

    TV tv = new TV();
    Stereo stereo = new Stereo();

    Command turnOnTVCommand = new TVCommand(tv);
    Command turnOnTVCommand2 = new TVCommand(tv);
    Command adjustVolumeStereoCommand = new StereoCommand(stereo);

    Stack<Command> commandStack = new Stack<>();
    try{
      turnOnTVCommand.execute();
      commandStack.push(turnOnTVCommand);

      turnOnTVCommand.execute();
      commandStack.push(turnOnTVCommand);

      turnOnTVCommand2.execute();
      commandStack.push(turnOnTVCommand2);

      adjustVolumeStereoCommand.execute();
      commandStack.push(adjustVolumeStereoCommand);


    } catch (Exception exception){
      while(!commandStack.empty()){
        commandStack.pop().rollBack();
      }
    }
  }
}

 interface Command {
  void execute() throws Exception;

  void rollBack();
}

class TVCommand implements Command{
  private Device device;

  public TVCommand(Device device){
    this.device = device;
  }

  @Override
  public void execute() throws Exception {
    device.turnOn();
  }

  @Override
  public void rollBack(){
    device.turnOff();
  }
 }

 class StereoCommand implements Command {
  private Stereo stereo;

  public StereoCommand(Stereo stereo){
    this.stereo = stereo;
  }

  @Override
   public void execute() throws Exception {
    stereo.turnOn();
  }

   @Override
   public void rollBack(){
     stereo.turnOff();
   }
 }

 interface Device{
  void turnOff();
  void turnOn() throws Exception;
   void rollBack();
 }

 class TV implements Device{
  @Override
   public void turnOn(){
    System.out.println("TV ONNNN!");
  }

   @Override
   public void turnOff(){
     System.out.println("TV OFFFF!");
   }

   @Override
   public void rollBack(){
     System.out.println("Rollback DEvice");
   }

   public void changeChannel(){
     System.out.println("Channel changed");
   }

 }

 class Stereo implements Device {

  @Override
  public void turnOn() throws Exception {
    System.out.println("Stereo ON");
//    throw new Exception("Exception message");

    }

    @Override
   public void turnOff(){
      System.out.println("Stereo OFF");
    }

   @Override
   public void rollBack(){
     System.out.println("Rollback DEvice");
   }
}