package it.univr;

public class Launcher {
    int age;

    public int setAge(int age){
        this.age = age;
        return this.age;
    }
    public static void main(String[] args) {
        Launcher l = new Launcher();
        l.setAge(13);
        //Main.main(args);
    }
}