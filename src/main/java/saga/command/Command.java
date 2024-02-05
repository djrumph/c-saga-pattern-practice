package saga.command;

public interface Command {
  void execute() throws Exception;

  void rollBack();
}