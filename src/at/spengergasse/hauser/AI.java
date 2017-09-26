package at.spengergasse.hauser;
public class AI
{
    private Pacman pac; Pacman pac1;
    private Territory terr;
    private PacManGame p;
    private char [] [] GameField;
    private int x=0;
    private int UO;
    private int LR; private boolean OURL;
    public AI(Pacman pac1, Pacman pac, Territory terr, PacManGame p)
    {
        this.pac1=pac1;
        this.pac=pac;
        this.terr=terr;
        this.p=p;
        GameField=new char[31][31];
        for(int x=0; x<31; x++){
            for(int y=0; y<31; y++){
                switch(terr.getFields(x, y)){
                    case -1: GameField[x] [y]='X'; break;
                    case 0: GameField[x] [y]='O'; break;
                    case 1: GameField[x] [y]='O'; break;
                    case 2: GameField[x] [y]='O'; break;
                    case 3: GameField[x] [y]='O'; break;
                    case 4: GameField[x] [y]='O'; break;
                    case 5: GameField[x] [y]='O'; break;
                    case 6: GameField[x] [y]='O'; break;
                    case 7: GameField[x] [y]='O'; break;
                    case 8: GameField[x] [y]='O'; break;
                    case 9: GameField[x] [y]='O'; break;
                    case 10: GameField[x] [y]='O'; break;
                    case 11: GameField[x] [y]='O'; break;
                    case 12: GameField[x] [y]='O'; break;
                    case 13: GameField[x] [y]='O'; break;
                    case 14: GameField[x] [y]='O'; break;
                    case 15: GameField[x] [y]='O'; break;
                }
            }
        }
        checkForPac();
    }

    private void checkForPac(){
        GameField[pac.getColumn()][pac.getRow()]='P';
    }

    private void checkForGhost(){
        GameField[pac1.getColumn()][pac1.getRow()]='G';
    }

    public boolean GhostL(){
        if(GameField[pac1.getColumn()-1][pac1.getRow()]=='O'||GameField[pac1.getColumn()-1][pac1.getRow()]=='P'){
            LR=pac1.getColumn();
            checkForPacMan();
            pac1.setColumn(pac1.getColumn()-1);
            checkForPacMan();
            return true;
        }
        return false;
    }

    public boolean GhostR(){
        if(GameField[pac1.getColumn()+1][pac1.getRow()]=='O'||GameField[pac1.getColumn()+1][pac1.getRow()]=='P'){
            LR=pac1.getColumn();
            checkForPacMan();
            pac1.setColumn(pac1.getColumn()+1);
            checkForPacMan();
            return true;
        }
        return false;
    }

    public void GhostUO(){
        char U=' '; char O=' '; char L=' '; char R=' '; char UL=' '; char UR= ' '; char OL=' ';char OR=' '; 
        int u=0; int o=0; int l=0; int r=0;
        OURL=false;
        checkIfPacInRow();
        checkIfPacInColumn();
        if(pac1.getRow()-1 <31 && pac1.getRow()-1>0){
            if(GameField[pac1.getColumn()][pac1.getRow()-1]=='X')O='X';
        }
        else O='X';
        if(pac1.getRow()+1 <31 && pac1.getRow()+1>0){
            if(GameField[pac1.getColumn()][pac1.getRow()+1]=='X')U='X';
        }
        else U='X';
        if(UO==0){
            if(U=='X' && O=='X'){GhostLR();}
            else if(U=='X' && O==' '){GhostO();}
            else if(U==' ' && O=='X'){GhostU();}
            else if(U==' ' && O==' '){
                if(pac.getRow()<pac1.getRow()){GhostO();}
                else{ GhostU();}
            }
        }
        else if(UO!=0){
            if(U=='X' && O=='X'){GhostLR();}
            else if(U=='X' && O==' '){
                if(pac1.getRow()-1==UO){
                    if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                        if(GameField[pac1.getColumn()-1][pac1.getRow()]=='X')L='X';
                    }
                    else L='X';
                    if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                        if(GameField[pac1.getColumn()+1][pac1.getRow()]=='X')R='X';
                    }
                    else R='X';
                    if(L==' '){
                        if(pac1.getRow()+1 <31 && pac1.getRow()+1>0 && pac1.getColumn()-1 <31 && pac1.getColumn()-1>0 ){
                            if(GameField[pac1.getColumn()-1][pac1.getRow()+1]=='X')UL='X';
                        }
                        else UL='X';
                    }
                    if(R==' '){
                        if(pac1.getRow()+1 <31 && pac1.getRow()+1>0&& pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                            if(GameField[pac1.getColumn()+1][pac1.getRow()+1]=='X')UR='X';
                        }
                        else UR='X';
                    }
                    if(UR=='X' && UL=='X'){
                        if(L==' '){
                            if(pac1.getRow()+1 <31 && pac1.getRow()+1>0&& pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                                if(GameField[pac1.getColumn()-1][pac1.getRow()+1]=='X')OL='X';
                            }
                            else OL='X';
                        }
                        if(R==' '){
                            if(pac1.getRow()+1 <31 && pac1.getRow()+1>0&& pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                                if(GameField[pac1.getColumn()+1][pac1.getRow()+1]=='X')OR='X';
                            }
                            else OR='X';
                        }
                        if(OR=='X' && OL=='X'){
                            GhostO(); 
                        }
                        else if(OR=='X' && OL==' '){
                            GhostL();
                            for(int x=0; x<1; x++){
                                GhostO();
                            }

                        }
                        else if(OR==' ' && OL=='X'){
                            GhostR();
                            for(int x=0; x<1; x++){
                                GhostO();
                            }

                        }
                        if(OR==' ' && OL==' '){
                            /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                            /*else  GhostR();*/
                            findPathToPacLR();
                            for(int x=0; x<1; x++){
                                GhostO();
                            }

                        }
                    }
                    else if(UR=='X' && UL==' '){
                        GhostL();
                        for(int x=0; x<1; x++){
                            GhostU();
                        }

                    }
                    else if(UR==' ' && UL=='X'){
                        GhostR();
                        for(int x=0; x<1; x++){
                            GhostU();
                        }

                    }
                    else if(UR==' ' && UL==' '){
                        /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                        /*else  GhostR();*/
                        findPathToPacLR();
                        for(int x=0; x<1; x++){
                            GhostU();
                        }

                    }
                }
                else{ GhostO();}
            }
            else if(U==' ' && O=='X'){
                if(pac1.getRow()+1==UO){
                    if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                        if(GameField[pac1.getColumn()-1][pac1.getRow()]=='X')L='X';
                    }
                    else L='X';
                    if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                        if(GameField[pac1.getColumn()+1][pac1.getRow()]=='X')R='X';
                    }
                    else R='X';
                    if(L==' '){
                        if(pac1.getRow()+1 <31 && pac1.getRow()+1>0&& pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                            if(GameField[pac1.getColumn()-1][pac1.getRow()+1]=='X')OL='X';
                        }
                        else OL='X';
                    }
                    if(R==' '){
                        if(pac1.getRow()+1 <31 && pac1.getRow()+1>0&& pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                            if(GameField[pac1.getColumn()+1][pac1.getRow()+1]=='X')OR='X';
                        }
                        else OR='X';
                    }
                    if(OR=='X' && OL=='X'){
                        if(L==' '){
                            if(pac1.getRow()+1 <31 && pac1.getRow()+1>0 && pac1.getColumn()-1 <31 && pac1.getColumn()-1>0 ){
                                if(GameField[pac1.getColumn()-1][pac1.getRow()+1]=='X')UL='X';
                            }
                            else UL='X';
                        }
                        if(R==' '){
                            if(pac1.getRow()+1 <31 && pac1.getRow()+1>0&& pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                                if(GameField[pac1.getColumn()+1][pac1.getRow()+1]=='X')UR='X';
                            }
                            else UR='X';
                        }
                        if(UR=='X' && UL=='X'){
                            GhostU();
                        }
                        else if(UR=='X' && UL==' '){
                            GhostL();
                            for(int x=0; x<1&&pac1.free(); x++){
                                GhostU();
                            }

                        }
                        else if(UR==' ' && UL=='X'){
                            GhostR();
                            for(int x=0; x<1&&pac1.free(); x++){
                                GhostU();
                            }

                        }
                        else if(UR==' ' && UL==' '){
                            /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                            /*else  GhostR();*/
                            findPathToPacLR();
                            for(int x=0; x<1&&pac1.free(); x++){
                                GhostU();
                            }

                        }
                    }
                    else if(OR=='X' && OL==' '){
                        GhostL();
                        for(int x=0; x<1&&pac1.free(); x++){
                            GhostO();
                        }

                    }
                    else if(OR==' ' && OL=='X'){
                        GhostR();
                        for(int x=0; x<1&&pac1.free(); x++){
                            GhostO();
                        }

                    }
                    if(OR==' ' && OL==' '){
                        /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                        /*else  GhostR();*/
                        findPathToPacLR();
                        for(int x=0; x<1&&pac1.free(); x++){
                            GhostO();
                        }

                    }                    
                }
                else{ GhostU(); }
            }
            else if(U==' ' && O==' '){
                if(pac1.getRow()+1==UO && pac1.getRow()-1!=UO){
                    GhostO(); 
                }
                else if(pac1.getRow()-1==UO && pac1.getRow()+1!=UO){
                    GhostU(); 
                }
                else if((pac1.getRow()-1==UO && pac1.getRow()+1==UO)||(pac1.getRow()-1!=UO && pac1.getRow()+1!=UO)){
                    /* if(pac.getRow()<pac1.getRow()){GhostO();}
                    else{  GhostU();}*/
                    findPathToPacUO();
                }
            }
        }

    }

    public void GhostLR(){
        char L=' '; char R=' '; char U=' '; char O=' ';  char OL=' '; char OR=' '; char UL=' '; char UR=' ';
        int ol=0; int or=0; int ul=0; int ur=0; boolean go=false;
        OURL=true;
        checkIfPacInColumn();
        checkIfPacInRow();
        if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
            if(GameField[pac1.getColumn()-1][pac1.getRow()]=='X')L='X';
        }
        else L='X';
        if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
            if(GameField[pac1.getColumn()+1][pac1.getRow()]=='X')R='X';
        }
        else R='X';
        if(LR==0){
            if(L=='X' && R=='X')GhostUO();  
            else if(L=='X' && R==' ')GhostR(); 
            else if(L==' ' && R=='X')GhostL();
            else if(L==' ' && R==' '){
                /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                /* else  GhostR();*/
                findPathToPacLR();
            }
        }
        else if(LR!=0){
            if(L=='X' && R=='X')GhostUO();  
            else if(L=='X' && R==' '){
                if(pac1.getColumn()+1==LR){
                    if(pac1.getRow()-1 <31 && pac1.getRow()-1>0){
                        if(GameField[pac1.getColumn()][pac1.getRow()-1]=='X')O='X';
                    }
                    else O='X';
                    if(pac1.getRow()+1 <31 && pac1.getRow()+1>0){
                        if(GameField[pac1.getColumn()][pac1.getRow()+1]=='X')U='X';
                    }
                    else U='X'; 
                    if(O==' '){
                        for(int x=pac1.getRow(); x>=0; x--){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                                if(GameField[pac1.getColumn()-1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else ol++;
                            }
                            else break;
                        }
                        if(!go)OL='X';
                        go=false;
                        for(int x=pac1.getRow(); x>=0; x--){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                                if(GameField[pac1.getColumn()+1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else or++;
                            }
                            else break;
                        }
                        if(!go)OR='X';
                        go=false;
                        /*if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0 && pac1.getRow()-1 <31 && pac1.getRow()-1>0){
                        if(GameField[pac1.getColumn()-1][pac1.getRow()-1]=='X')OL='X';
                        }
                        else OL='X';*/

                        /*if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0 && pac1.getRow()-1 <31 && pac1.getRow()-1>0){
                        if(GameField[pac1.getColumn()+1][pac1.getRow()-1]=='X')OR='X';
                        }
                        else OR='X';*/
                    }
                    if(U==' '){
                        for(int x=pac1.getRow()+1; x<31 && x>=0; x++){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                                if(GameField[pac1.getColumn()-1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else ul++;
                            }
                        }
                        if(!go) UL='X';
                        go=false;
                        for(int x=pac1.getRow()+1; x<31 && x>=0; x++){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                                if(GameField[pac1.getColumn()+1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else ur++;
                            }
                        }
                        if(!go)UR='X';
                        go=false;
                        /*if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0&&pac1.getRow()+1 <31 && pac1.getRow()+1>0){
                        if(GameField[pac1.getColumn()-1][pac1.getRow()+1]=='X')UL='X';
                        }
                        else UL='X';

                        if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0&&pac1.getRow()+1 <31 && pac1.getRow()+1>0){
                        if(GameField[pac1.getColumn()+1][pac1.getRow()+1]=='X')UR='X';
                        }
                        else UR='X';*/
                    }
                    if(UL=='X' && UR=='X'){
                        if(OR=='X' && OL=='X'){
                            GhostR();
                        }
                        else if(OR=='X' && OL==' '){
                            for(int x=0; x<ol; x++){
                                GhostO();
                            }
                            for(int x=0; x<1; x++){
                                GhostL();
                            }
                        }
                        else if(OR==' ' && OL=='X'){
                            for(int x=0; x<or; x++){
                                GhostO();
                            }
                            for(int x=0; x<1; x++){
                                GhostR();
                            }
                        }
                        else if(OR==' '&&OL==' '){
                            GhostO();
                            /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                            /*else  GhostR();*/
                            findPathToPacLR();
                        }
                    }
                    else if(UL==' '&&UR=='X'){
                        for(int x=0; x<ul; x++){
                            GhostU();
                        }
                        for(int x=0; x<1; x++){
                            GhostL();
                        }
                    }
                    else if(UL=='X' && UR==' '){
                        for(int x=0; x<ur; x++){
                            GhostU();
                        }
                        for(int x=0; x<1; x++){
                            GhostR();
                        }
                    }
                    else if(UL==' '&&UR==' '){
                        GhostU();
                        /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                        /*else  GhostR();*/
                        findPathToPacLR();
                    }
                }
                else  GhostR(); 
            }
            else if(L==' ' && R=='X'){
                if(pac1.getColumn()-1==LR){
                    if(pac1.getRow()-1 <31 && pac1.getRow()-1>0){
                        if(GameField[pac1.getColumn()][pac1.getRow()-1]=='X')O='X';
                    }
                    else O='X';
                    if(pac1.getRow()+1 <31 && pac1.getRow()+1>0){
                        if(GameField[pac1.getColumn()][pac1.getRow()+1]=='X')U='X';
                    }
                    else U='X'; 
                    if(O==' '){
                        for(int x=pac1.getRow(); x>=0; x--){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                                if(GameField[pac1.getColumn()-1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else ol++;
                            }
                            else break;
                        }
                        if(!go)OL='X';
                        go=false;
                        for(int x=pac1.getRow(); x>=0; x--){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                                if(GameField[pac1.getColumn()+1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else or++;
                            }
                            else break;
                        }
                        if(!go)OR='X';
                        go=false;
                        /*if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0 && pac1.getRow()-1 <31 && pac1.getRow()-1>0){
                        if(GameField[pac1.getColumn()-1][pac1.getRow()-1]=='X')OL='X';
                        }
                        else OL='X';*/

                        /*if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0 && pac1.getRow()-1 <31 && pac1.getRow()-1>0){
                        if(GameField[pac1.getColumn()+1][pac1.getRow()-1]=='X')OR='X';
                        }
                        else OR='X';*/
                    }
                    if(U==' '){
                        for(int x=pac1.getRow()+1; x<31 && x>=0; x++){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0){
                                if(GameField[pac1.getColumn()-1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else ul++;
                            }
                        }
                        if(!go) UL='X';
                        go=false;
                        for(int x=pac1.getRow()+1; x<31 && x>=0; x++){
                            if(GameField[pac1.getColumn()][x]=='X'){
                                break;
                            }
                            if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0){
                                if(GameField[pac1.getColumn()+1][x]=='O'){
                                    go=true;
                                    break;
                                }
                                else ur++;
                            }
                        }
                        if(!go)UR='X';
                        go=false;
                        /*if(pac1.getColumn()-1 <31 && pac1.getColumn()-1>0&&pac1.getRow()+1 <31 && pac1.getRow()+1>0){
                        if(GameField[pac1.getColumn()-1][pac1.getRow()+1]=='X')UL='X';
                        }
                        else UL='X';

                        if(pac1.getColumn()+1 <31 && pac1.getColumn()+1>0&&pac1.getRow()+1 <31 && pac1.getRow()+1>0){
                        if(GameField[pac1.getColumn()+1][pac1.getRow()+1]=='X')UR='X';
                        }
                        else UR='X';*/
                    }
                    if(UL=='X' && UR=='X'){
                        if(OR=='X' && OL=='X'){
                            GhostL();
                        }
                        else if(OR=='X' && OL==' '){
                            for(int x=0; x<ol; x++){
                                GhostO();
                            }
                            for(int x=0; x<1; x++){
                                GhostL();
                            }
                        }
                        else if(OR==' ' && OL=='X'){
                            for(int x=0; x<or; x++){
                                GhostO();
                            }
                            for(int x=0; x<1; x++){
                                GhostR();
                            }
                        }
                        else if(OR==' '&&OL==' '){
                            GhostO();
                            /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                            /* else  GhostR();*/
                            findPathToPacLR();
                        }
                    }
                    else if(UL==' '&&UR=='X'){
                        for(int x=0; x<ul; x++){
                            GhostU();
                        }
                        for(int x=0; x<1; x++){
                            GhostL();
                        }
                    }
                    else if(UL=='X' && UR==' '){
                        for(int x=0; x<ur; x++){
                            GhostU();
                        }
                        for(int x=0; x<1; x++){
                            GhostR();
                        }
                    }
                    else if(UL==' '&&UR==' '){
                        GhostU();
                        /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                        /*else  GhostR(); */
                        findPathToPacLR();
                    }
                }
                else GhostL();
            }

            else if(L==' ' && R==' '){
                if(pac1.getColumn()-1==LR&&pac1.getColumn()+1!=LR){
                    GhostR();
                }
                else if(pac1.getColumn()-1!=LR&&pac1.getColumn()+1==LR){
                    GhostL();
                }
                else if((pac1.getColumn()-1==LR&&pac1.getColumn()+1==LR)||(pac1.getColumn()-1!=LR&&pac1.getColumn()+1!=LR)){
                    /* /*if(pac.getColumn()<pac1.getColumn()) GhostL();*/
                    /*else  GhostR(); */
                    findPathToPacLR();
                }
            }
        }
    }

    public boolean GhostO(){
        if(GameField[pac1.getColumn()][pac1.getRow()-1]=='O'||GameField[pac1.getColumn()][pac1.getRow()-1]=='P'){
            UO=pac1.getRow();
            checkForPacMan();
            pac1.setRow(pac1.getRow()-1);
            checkForPacMan();
            return true;
        }
        return false;
    }

    public boolean GhostU(){
        if(GameField[pac1.getColumn()][pac1.getRow()+1]=='O'||GameField[pac1.getColumn()][pac1.getRow()+1]=='P'){
            UO=pac1.getRow();
            checkForPacMan();
            pac1.setRow(pac1.getRow()+1);
            checkForPacMan(); return true;
        }
        return false;
    }

    /*public void startGhost(){
    while(p.getLeben()>0){
    checkForPac();
    checkForGhost();
    if(Richtung()=='L'){
    GhostL();
    }
    else if(Richtung()=='R'){
    GhostR();
    }
    }
    setX();
    }*/
    public String Richtung(){
        while(true){
            if(OURL){
                if(p.getLeben()<3){
                    break;
                }
                GhostUO();
                checkIfPacInColumn();
                checkIfPacInRow();
            }
            else{
                if(p.getLeben()<3){
                    break;
                }
                GhostLR();
                checkIfPacInColumn();
                checkIfPacInRow();
            }
        }
        return "VERLOREN";
    }

    public void checkIfPacInRow(){
        for(int x=pac1.getColumn(); x<31; x++){
            if(GameField[x][pac1.getRow()]=='X'){
                break;
            }
            else if(GameField[x][pac1.getRow()]=='P'){
                for(int y=x; y<31; y++){
                    GhostR();
                }
            }
        }
        for(int x=pac1.getColumn(); x>=0; x--){
            if(GameField[x][pac1.getRow()]=='X'){
                break;
            }
            else if(GameField[x][pac1.getRow()]=='P'){
                for(int y=x; y>=0; y--){
                    GhostL();
                }
            }
        }
    }

    public void checkIfPacInColumn(){
        for(int x=pac1.getRow(); x<31 && x>=0; x++){
            if(GameField[pac1.getColumn()][x]=='X'){
                break;
            }
            else if(GameField[pac1.getColumn()][x]=='P'){
                for(int y=x; y<31 && y>=0; y++){
                    GhostU();
                }
            }
        }
        for(int x=pac1.getRow(); x>=0; x--){
            if(GameField[pac1.getColumn()][x]=='X'){
                break;
            }
            else if(GameField[pac1.getColumn()][x]=='P'){
                for(int y=x; y>=0; y--){
                    GhostO();
                }
            }
        }
    }

    public void checkForPacMan(){
        if((pac.getRow()==pac1.getRow())&&(pac.getColumn()==pac1.getColumn())){
            System.out.println("VERLOREN!!");
        }
    }

    public void findPathToPacLR(){
        if(pac.getColumn()<pac1.getColumn() && GhostL())GhostL();
        else if(pac.getColumn()>pac1.getColumn() && GhostR())GhostR();
        else findFree();
    }

    public void findFree(){
        if(GhostU()) GhostU();
        else if(GhostO()) GhostO();
        else if(GhostL()) GhostL();
        else if(GhostR()) GhostR();
    }

    public void findPathToPacUO(){
        if(pac.getRow()<pac1.getRow() && GhostO()){GhostO();}
        else if(pac.getRow()>pac1.getRow() && GhostU()){  GhostU();}  
        else findFree();
    }
}