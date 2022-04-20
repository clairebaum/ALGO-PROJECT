import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/*changed a lot: the detection of the keys pressed was only done when the thread was actualising 
hence some signals were not taken into account. Here the methods are static because there is only one KeyHandler
 -> the method does not depend on the object
*/
// implementation of the links between the keyboard anf the action

public class KeyHandler implements KeyListener {

  public static long lastUpTime, lastDownTime, lastLeftTime, lastRightTime,
                      lastZTime, lastDTime, lastSTime, lastQTime;

  public static long[] keyTimes = new long[256]; //array storing the last time the key was pressed at the position corresponding to its ascii code

  public void keyTyped(KeyEvent e) {}

  public void keyPressed(KeyEvent e) {
    keyTimes[e.getKeyCode()] = System.currentTimeMillis(); //updates the value of the time at the posiiton of the pressed key 
  }

  @Override

  public void keyReleased(KeyEvent e) {}

  private static final int LOOP_DELAY = 200; //corresponds to the actualising rate of the thread

  public static boolean isKeyPressed(int key) { //tests if the given key has just been pressed and put its time to zero
    if(System.currentTimeMillis() - keyTimes[key] <= LOOP_DELAY) {
      keyTimes[key] = 0;
      return true;
    }
    return false;
  }

  public static boolean isUpPressed() { //keyPressed is used for each key 
    return isKeyPressed(KeyEvent.VK_UP);
  }

  public static boolean isDownPressed() {
    return isKeyPressed(KeyEvent.VK_DOWN);
  }
  public static boolean isRightPressed() {
    return isKeyPressed(KeyEvent.VK_RIGHT);
  }
  public static boolean isLeftPressed() {
    return isKeyPressed(KeyEvent.VK_LEFT);
  }
  public static boolean isZPressed() {
    return isKeyPressed(KeyEvent.VK_Z);
  }

  public static boolean isSPressed() {
    return isKeyPressed(KeyEvent.VK_S);
  }

  public static boolean isQPressed() {
    return isKeyPressed(KeyEvent.VK_Q);
  }

  public static boolean isDPressed() {
    return isKeyPressed(KeyEvent.VK_D);
  }


}
