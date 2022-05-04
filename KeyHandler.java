import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


// Implements the links between the keyboard and the action
public class KeyHandler implements KeyListener {

  public static long lastUpTime, lastDownTime, lastLeftTime, lastRightTime,
                      lastZTime, lastDTime, lastSTime, lastQTime;
  public static long[] keyTimes = new long[256]; //array storing the last time the key was pressed at the position corresponding to its ascii code
  private static final int LOOP_DELAY = 200; //corresponds to the actualising rate of the thread

	/** updates the value of the time at the position of the pressed key 
	  */
  public void keyPressed(KeyEvent e) {
    keyTimes[e.getKeyCode()] = System.currentTimeMillis(); 
  }

  @Override
  public void keyTyped(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}

	/** tests if the given key has been pressed after the last update and put its time to zero
	  */
  public static boolean isKeyPressed(int key) { 
    if(System.currentTimeMillis() - keyTimes[key] <= LOOP_DELAY) {
      keyTimes[key] = 0;
      return true;
    }
    return false;
  }

	/** keyPressed is used for each key 
	  */
  public static boolean isUpPressed() { 
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
