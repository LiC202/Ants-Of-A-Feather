public class Dam {
  private static boolean[] gaps = new boolean[3];
  
  public static void plugGap(int gapNo) {
    gapNo--;
    gaps[gapNo] = true;
  }
  
  public static boolean allGapsPlugged() {
    return (gaps[0] && gaps[1] && gaps[2]);
  }
  
  public static boolean[] getGapList() {
    return gaps;
  }
  
  public static void setGapList(boolean[] gapList) {
    gaps = gapList;
  }
}
