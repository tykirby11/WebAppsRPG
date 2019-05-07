package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._

object CanvasDrawing {
    private var x = 100
    private var y = 100
    private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
    private val context = canvas.getContext("2d")

    private val bw = 125
    private val bh = 50
    
    
    def drawArena(): Unit = {
      
      //battle window
        context.rect(20,20,760,390);
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
        
    }
    
    dom.document.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX, e.clientY)
      
      println(s"x: ${coords._1}, y: ${coords._2}")
      
      if((e.clientX > 55 && e.clientX < 180) && (e.clientY > 461 && e.clientY < 505)){
        context.fillRect(20,20,150,100);
      }
    }

    
    
   def attack(): Unit = {
     
   }
    
}