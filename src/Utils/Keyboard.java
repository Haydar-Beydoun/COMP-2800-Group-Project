package Utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
    private boolean[] keyPressed = new boolean[KeyEvent.KEY_LAST + 1];

    @Override
    public void keyPressed(KeyEvent e) {
        if(isValidKeyCode(e.getKeyCode()))
            keyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(isValidKeyCode(e.getKeyCode()))
            keyPressed[e.getKeyCode()] = false;
    }

    public boolean isPressed(int keyCode){
        return keyPressed[keyCode];
    }

    public boolean isPressed(int[] keyCodes){
        for(int keyCode : keyCodes){
            if(keyPressed[keyCode]){
                return true;
            }
        }
        return false;
    }

    private boolean isValidKeyCode(int keyCode){
        return keyCode >= 0 && keyCode < keyPressed.length;
    }

}
