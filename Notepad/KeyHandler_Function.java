import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler_Function implements KeyListener{
    pad p;
    KeyHandler_Function(pad p){
        this.p=p;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S){
            p.File.save();
        }else if(e.isControlDown() && e.isShiftDown() && e.getKeyCode()==KeyEvent.VK_S){
            p.File.saveas();
        }else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_N){
            p.File.newFile();
        }else if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_O){
            p.File.open();
        }
        else if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_F4){
            p.File.exit();
        }
       
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
       
    }
   
}
