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
        
        context.beginPath();
        context.moveTo(20,325);
        context.lineTo(780,325);
        context.stroke();
        
        drawEnemy()
        
        arenaExists = true;
        
    }
    
    def drawEnemy() : Unit = {
      context.drawImage(glitch, 350,100,96,96);
    }
    
    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
    dom.document.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX, e.clientY)
      
      println(s"x: ${coords._1}, y: ${coords._2}")
      
      //if attack is pressed and if button is showing
      if((e.clientX > 55 && e.clientX < 180) && (e.clientY > 454 && e.clientY < 505) && arenaExists == true){
        attack();
        setTimeout(550)(drawEnemy());
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
   }
   
   def clearAnimation(): Unit = {
     context.clearRect(400,100,26,110);
   }
   
   def drawBorder(xPos: Int, yPos: Int,width: Int,height: Int,thickness: Int = 1) : Unit = {
    context.fillStyle= "#000000";
    context.fillRect(xPos - (thickness), yPos - (thickness), width + (thickness * 2), height + (thickness * 2));
  }
   
}