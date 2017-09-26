package at.spengergasse.hauser;

public class Pacman {
    private Territory terr; PacManGame p;
    private Direction dir;
    private int cornsInMouth;
    private int column, row;
    private Pacman()
    {
    }

    public Pacman(Territory terr,int column, int row, Direction dir, int mouth, PacManGame p)
    {
        this.p=p;
        this.terr = terr;
        this.dir = dir;
        this.column = column;
        this.row = row;
        this.cornsInMouth = mouth;
        if (!terr.isFree(column, row))
            throw new IllegalArgumentException("There is a wall here: column = " + column + " row= " + row);
        terr.addPacman(this);
    }
    
     public Pacman(Territory terr,int column, int row, Direction dir, int mouth)
    {
        this.terr = terr;
        this.dir = dir;
        this.column = column;
        this.row = row;
        this.cornsInMouth = mouth;
        if (!terr.isFree(column, row))
            throw new IllegalArgumentException("There is a wall here: column = " + column + " row= " + row);
        terr.addPacman(this);
    }

    public void PacGo()
    {
        switch(dir)
        {
            case NORTH: if (free()) { row--; break;} else throw new IllegalArgumentException("can't go there");
            case WEST:  if (free()) { column--; break;} else throw new IllegalArgumentException("can't go there");
            case SOUTH: if (free()) { row++; break;} else throw new IllegalArgumentException("can't go there");
            case EAST:  if (free()) { column++; break;} else throw new IllegalArgumentException("can't go there");
            default:    throw new IllegalArgumentException("unknown direction");
        }       
        terr.draw();
    }

   

    

    public void pickUp()
    {
        if (!pointAvailable())
            throw new IllegalArgumentException("no corns here");
        cornsInMouth++;
        terr.decrementPoints(column, row);
        terr.draw();
    }

    public boolean free()
    {
        switch(dir)
        {
            case NORTH: if (row > 0 && terr.isFree(column, row-1)) return true; else return false;
            case WEST:  if (column > 0 && terr.isFree(column-1, row)) return true; else return false;
            case SOUTH: if (row < terr.getRows()-1  && terr.isFree(column, row+1)) return true; else return false;
            case EAST:  if (column < terr.getColumns()-1  && terr.isFree(column+1, row)) return true; else return false;
            default:    throw new IllegalArgumentException("unknown direction");
        }
    }

    

    public boolean pointAvailable()
    {
        return terr.getCorns(column, row) > 0;
    }   

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }

    Direction getDirection()
    {
        return dir;
    }

    int getPacPoints()
    {
        return cornsInMouth;
    }

    public void setDirection(Direction dir){
        this.dir = dir;
    }

    public void setRow(int row){
        this.row = row;
        terr.draw();
    }

    public void setColumn(int column){
        this.column = column;
        terr.draw();
    }

    public int[]getPos(){
        int[] a= new int[2];
        a[0]=getColumn();
        a[1]=getRow();

        return a;
    }
}
