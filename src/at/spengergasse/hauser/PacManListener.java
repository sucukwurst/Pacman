package at.spengergasse.hauser;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class PacManListener extends Thread implements KeyListener{
    /*private PacManGame p;*/
    private Pacman h;
    private Territory terr;
    private PacManGame p;
    /*private boolean running = false;*/
    public void setA(Pacman h){
      this.h = h;
    }
    public void setB(Territory terr, PacManGame p){
      this.terr = terr;
      this.p=p;
    }
    /*public void setC(PacManGame p){
        this.p=p;
    }*/
    @Override
    public void keyTyped(KeyEvent e){
    }
    
    /**
     * Method keyPressed
     *
     * @param e A parameter
     */
    @Override
    public void keyPressed(KeyEvent e){
        /*if(!running && e.getKeyCode()== KeyEvent.VK_ENTER){
            running=true;
            p.play();          
        }*/
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            h.setDirection(Direction.EAST);
            terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_LEFT){
           h.setDirection(Direction.WEST);
           terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            h.setDirection(Direction.SOUTH);
            terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_UP){
            h.setDirection(Direction.NORTH);
            terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_A){
            h.setDirection(Direction.WEST);
            terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_S){
            h.setDirection(Direction.SOUTH);
            terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_W){
            h.setDirection(Direction.NORTH);
            terr.repaint();
        }
        else if(e.getKeyCode()== KeyEvent.VK_D){
            h.setDirection(Direction.EAST);
            terr.repaint();
        }
        else if(e.getKeyCode()==KeyEvent.VK_M){
            p.updateScore();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
    }
}
