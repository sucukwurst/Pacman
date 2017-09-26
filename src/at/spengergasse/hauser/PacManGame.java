package at.spengergasse.hauser;
import javax.swing.*;
public class PacManGame extends Thread
{
    private Pacman pac; Pacman pac1; Pacman pac2; Pacman pac3; Pacman pac4;
    private int Leben=3;
    private Ghost g;
    private Territory terr; AI a; GameField at;
    public PacManGame(){
        terr = new Territory("ex1.ter");
        pac =new Pacman(terr,15, 22, Direction.EAST, 0, this);
        pac1 =new Pacman(terr,13, 1, Direction.EAST, 0, this);
        pac2 =new Pacman(terr,17, 10, Direction.EAST, 0, this);
        pac3=new Pacman(terr,15, 11, Direction.EAST, 0, this);
        pac4=new Pacman(terr,15, 9, Direction.EAST, 0, this);
        PacManListener listener = new PacManListener();
        terr.setCorns(14, 30,2); terr.setCorns(1, 29,12);
        terr.setCorns(15, 30,2); terr.setCorns(2, 29,12);
        terr.setCorns(16, 30,2); terr.setCorns(3, 29,12);
        terr.getFrame().addKeyListener(listener); 
        a = new AI(pac1, pac, terr, this);
        at=new GameField(pac1, pac, terr, this);
        g=new Ghost(pac1, pac, terr, pac.getDirection(), a);
        /*for(int x=0; x<6; x++){
        a.GhostL();
        }*/
        //a.Richtung();
        /* g.Hallo();*/
        /*p=this;
        listener.setC(p);*/
        listener.setA(pac);
        listener.setB(terr, this);
        terr.repaint();
    }

    public void play(){
        int x=14;
        /*Pacman pac1 =new Pacman(terr,13, 10, Direction.EAST, 0);
        Pacman pac2 =new Pacman(terr,17, 10, Direction.EAST, 0);
        PacManListener listener = new PacManListener();                             Test
        terr.getFrame().addKeyListener(listener);*/ 
        while(true){
            /*Score();*/
            //a.Richtung();
            if(pac.getPacPoints()%10==0){
                updateScore();
            }
            if(pac.getPacPoints()==324){
                System.out.println("DU HAST GEWONNEN!");
            }
            if(pac.pointAvailable()){
                pac.pickUp();
            }
            if(pac.free()){
                pac.PacGo();
            }
            if(pac.getColumn()==pac1.getColumn()&&pac.getRow()==pac1.getRow()){
                if(Leben==0){
                    System.out.println("DU HAST VEROREN!");
                    pac.setDirection(Direction.WEST);
                    terr.repaint();
                    break;
                }
                else{
                    terr.setCorns(x,30,3);
                    pac.setColumn(15);
                    pac.setRow(22);
                    terr.repaint();
                    Leben--;
                    x++;
                }
            }
            if(pac.getColumn()==pac2.getColumn()&&pac.getRow()==pac2.getRow()){
                if(Leben==0){
                    System.out.println("DU HAST VEROREN!");
                    pac.setDirection(Direction.EAST);
                    terr.repaint();
                    break;
                }
                else{
                    terr.setCorns(x,30,3);
                    pac.setColumn(15);
                    pac.setRow(22);
                    terr.repaint();
                    Leben--;
                    x++;
                }
            }
        }
    }//end play

    public void updateScore(){
        String Score="";
        if(pac.getPacPoints()<10){
            Score="";
            Score+=0;
            Score+=0;
        }
        else if(pac.getPacPoints()<100 && pac.getPacPoints()>=10){
            Score="";
            Score+=0;
        }
        Score+=pac.getPacPoints();
        for(int x=0; x<3; x++){
            switch(String.valueOf(Score.charAt(x))){
                case "0": terr.setCorns(x+1, 29,12); break;
                case "1": terr.setCorns(x+1, 29,5); break;
                case "2": terr.setCorns(x+1, 29,4); break;
                case "3": terr.setCorns(x+1, 29,6); break;                      //LAGS!
                case "4": terr.setCorns(x+1, 29,7); break;
                case "5": terr.setCorns(x+1, 29,8); break;
                case "6": terr.setCorns(x+1, 29,13); break;
                case "7": terr.setCorns(x+1, 29,9); break;
                case "8": terr.setCorns(x+1, 29,10); break;
                case "9": terr.setCorns(x+1, 29,11); break;
            }
        }

    } //end Score
    public Pacman getPacman(){
        return pac;
    }//end getPacman
    public int getLeben(){
        return Leben;
    }
    
    public void tryKI(){
        a.Richtung();
    }
    
    public void tryTestKI(){
        at.Richtung();
    }
    
    public static void main(String[] args) {
		new PacManGame().play();
	}
} 