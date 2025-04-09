package model;
//Generics Task class
public class Task<T> {
    private T content;
    private boolean status;
    private Level level;
    public enum Level {
        LOW, MEDIUM, HIGH
    }
    public Task(T content, Level level){
        this.content=content;
        this.status=false; //not done
        this.level=level;
    }
    public T getContent(){
        return content;
    }
    public boolean getstatus(){
        return status;
    }
    public Level getLevel(){
        return level;
    }
    public void changeStatus(){
        this.status=!this.status;
    }
    @Override
    public String toString(){
        return(status ? "[completed]":"[] ")+content.toString()+ " (" + level + ")";
    }
}
