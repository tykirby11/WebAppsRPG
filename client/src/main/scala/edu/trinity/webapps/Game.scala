package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._
import org.scalajs.dom.raw._
import org.scalajs.dom.raw.HTMLImageElement
import scala.scalajs.js.timers._

object CanvasDrawing {
    private var x = 100
    private var y = 100
    private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
    private val context = canvas.getContext("2d")
    
    //for images
    private val slice1 = dom.document.getElementById("slice1")
    private val slice2 = dom.document.getElementById("slice2")
    private val slice3 = dom.document.getElementById("slice3")
    private val slice4 = dom.document.getElementById("slice4")
    private val slice5 = dom.document.getElementById("slice5")
    private val slice6 = dom.document.getElementById("slice6")
    
    //test battle system
    
    //boolean to see if player has attacked
    private var hasAttacked = false;
    
    //test health
    private var enHealth = 30;
    private var pHealth = 40;
    
    //test speed
    private var enSpeed = 30;
    private var pSpeed = 40;
    
    //test damage
    private var pDamage = 10;
    private var enDamage = 5;
    
    private val glitch = dom.document.getElementById("glitch")

    private val bw = 125
    private val bh = 50

    private var arenaExists = false;
    
    def drawArena(): Unit = {
      //battle window
        context.rect(20,20,760,390);
        context.stroke();
        
      //user area
        context.rect(20,325, 760,85);
        context.stroke();
        
      //attack button
        context.rect(45,340,bw,bh);
        context.stroke();
        context.font = "30px Arial";
        context.fillText("Attack",65,375);
        
      //item button
        context.rect(190,340,bw,bh);
        context.stroke();
        context.font = "30px Arial";
        context.fillText("Item", 220, 375);
        
      //ui divider line  
        context.beginPath();
        context.moveTo(20,325);
        context.lineTo(780,325);
        context.stroke();
        
      //player health
        context.font = "20px Arial";
        context.fillText("Player Health: " + pHealth, 40, 50);
        
        
        drawEnemy();
        
        arenaExists = true;
        
    }
    
    def drawEnemyInitial() : Unit = {
      context.drawImage(glitch, 350,100,96,96);
      
      if(enSpeed < pSpeed){
        hasAttacked = false;
      } else {
        hasAttacked = true;
      }
      
      context.clearRect(560,30,215,50); 
      context.font = "20px Arial";
      context.fillText("Enemy Health: " + enHealth, 600, 50);
    }
    
    def drawEnemy() : Unit = {
      context.drawImage(glitch, 350,100,96,96);
      
      context.clearRect(560,30,215,50); 
      context.font = "20px Arial";
      context.fillText("Enemy Health: " + enHealth, 600, 50);
    }
    
    def drawZeroHealth() : Unit = {
      context.clearRect(560,30,215,50); 
      context.font = "20px Arial";
      context.fillText("Enemy Health: " + enHealth, 600, 50);
    }
    
    def hurtEnemy(): Unit = {
      //test player damage
      enHealth -= 10;
      
      if (enHealth <= 0){
        enHealth = 0;
        context.clearRect(350,100,96,96);
      }
      hasAttacked = true;
    }
    
    def enemyAttack() : Unit = {
      pHealth -= enDamage;
      
      context.clearRect(40,25,200,50);
      context.font = "20px Arial";
      context.fillText("Player Health: " + pHealth, 40, 50);
      hasAttacked = false;
    }
    
    def displayDamageDealt() : Unit = {
      context.font = "35px Arial";
      context.fillText("Damage Dealt: " + pDamage, 376, 375);
      setTimeout(1500)(context.clearRect(330, 339, 436, 60));
    }
    
    def displayDamageReceived() : Unit = {
      context.font = "35px Arial";
      context.fillText("Damage Received: " + enDamage, 376, 375);
      setTimeout(1500)(context.clearRect(330, 339, 436, 60));
    }
    
    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
    canvas.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)
      
      println(s"x: ${coords._1}, y: ${coords._2}")
      
      //if attack is pressed and if button is showing
      if(((e.clientX - canvas.offsetLeft) > 45 && (e.clientX - canvas.offsetLeft) < 170) && ((e.clientY - canvas.offsetTop) > 342 && (e.clientY - canvas.offsetTop) < 392) && arenaExists == true){             
        if(enHealth > 0 && hasAttacked != true){
          println("should attack");
          attack();
          displayDamageDealt();
          setTimeout(1500)(enemyAttack());
          setTimeout(2000)(displayDamageReceived());
        }
        println(enHealth);
        if(enHealth > 0){
          setTimeout(550)(drawEnemy());
        } else {
          drawZeroHealth();
        }
      }
    }

    
   def attack(): Unit = {
     setTimeout(250)(context.drawImage(slice1,400,100,26,110));
     setTimeout(250)(clearAnimation);
     setTimeout(250)(context.drawImage(slice2,400,100,26,110));
     setTimeout(300)(clearAnimation);
     setTimeout(300)(context.drawImage(slice3,400,100,26,110));
     setTimeout(350)(clearAnimation);
     setTimeout(350)(context.drawImage(slice4,400,100,26,110));
     setTimeout(400)(clearAnimation);
     setTimeout(400)(context.drawImage(slice5,400,100,26,110));
     setTimeout(450)(clearAnimation);
     setTimeout(450)(context.drawImage(slice6,400,100,26,110));
     setTimeout(500)(clearAnimation);
     
     hurtEnemy();
   }
   
   def clearAnimation(): Unit = {
     context.clearRect(400,100,26,110);
   }
   
   def drawBorder(xPos: Int, yPos: Int,width: Int,height: Int,thickness: Int = 1) : Unit = {
    context.fillStyle= "#000000";
    context.fillRect(xPos - (thickness), yPos - (thickness), width + (thickness * 2), height + (thickness * 2));
  }
   
}

