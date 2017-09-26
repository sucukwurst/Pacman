package at.spengergasse.hauser;
import java.util.*;
public class Ghost {
    private Pacman pac,pac1;
    private Territory terr;
    private int a;
    public int[][]felder;
    public  Direction dir; AI ai;
    private boolean b = true;
    public Ghost(Pacman pac1, Pacman pac,Territory terr,Direction dir, AI a){
        this.pac1=pac1;
        this.pac = pac;
        this.terr = terr;
        this.dir = dir;
        this.ai=ai;
    }

    
   /*public void Hallo() {
        Runnable r = new Runnable() {
                public void run() {
                    ai.Richtung();
                }
            };
        new Thread(r).start();
        //this line will execute immediately, not waiting for your task to complete
    }*/

    public void KIPac1(){
        while(true){
            //pac1.setDirection(pac.getDirection());
            if(pac1.free()){
                while(pac1.free() /*&& pac.getDirection()==pac1.getDirection()*/){
                    if(pac.getDirection()==Direction.NORTH) {pac1.setRow(pac1.getRow()-1);}
                      
                }
                pac1.setDirection(pac.getDirection());
            }
        }

    }
}