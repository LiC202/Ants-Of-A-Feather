public class Command {
  private final String commandWord;
  
  private final String secondWord;
  
  public Command(String firstWord, String secondWord) {
    this.commandWord = firstWord;
    this.secondWord = secondWord;
  }
  
  public String getCommandWord() {
    return this.commandWord;
  }
  
  public String getSecondWord() {
    return this.secondWord;
  }
  
  public boolean hasSecondWord() {
    return (this.secondWord != null);
  }
}
