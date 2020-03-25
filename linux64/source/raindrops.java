import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.ArrayList; 
import java.time.Clock; 
import java.lang.Math; 
import java.util.Random; 
import java.util.ListIterator; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class raindrops extends PApplet {







ArrayList<Droplet> droplets;
ArrayList<Boolean> lazy;
int MasterTime;

public void setup(){
    Random r = new Random();
    MasterTime = 0;
    
    
    droplets = new ArrayList<Droplet>();
    lazy = new ArrayList<Boolean>();
    for(int i = 0; i < 250; i++){
        droplets.add(new Droplet(r.nextFloat() * 1000, r.nextFloat() * 1250));  
    }
}

public void mainloop(){}

public void draw(){
  Random r = new Random();
  clear();
  for(Droplet d : droplets){
    d.draw();
    d.fall();
  }
  
  int removed = 0;
  ListIterator<Droplet> iter = droplets.listIterator();
  while(iter.hasNext()){
    Droplet d = iter.next();
    if(d.y > 1000){
        iter.remove();
        removed++;
    }
  }
  
  for(int i = 0; i < removed; i++){
    droplets.add(new Droplet(r.nextFloat() * 1000, r.nextFloat() * 1250));
  }
  
  
  MasterTime++;
}

class Droplet {
    private float x, y;
    private int time; 
    
    public Droplet(){
        x = 0;
        y = 0;
        time = MasterTime;
     }
     
     public Droplet(float x){
       this.x = x;
       this.y = 0;
       time = MasterTime;
     }
     
     public Droplet(float x, float y){
       this.x = x;
       this.y = y;
       time = MasterTime;
     }
     
     public void draw(){
        stroke(51, 51, 255);
        fill(102, 255, 255);
        rect(x, y, 4, 12);
     }
     
     public void fall(){
        this.y += (0.05f) * (float) Math.pow(MasterTime - time, 2) ;
     }
     
     public int getTime(){
       return time;
     }
}
  public void settings() {  size(1000, 1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "raindrops" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
