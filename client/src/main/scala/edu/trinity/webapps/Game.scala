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
    
    //test stuff
    
//    $("#gameCanvas").click(() => {
//        y -= 10
//        drawToCanvas()
//    })
//
//    def drawToCanvas(): Unit = {
//        context.fillRect(x, y, 30, 30)
//    }
//    
    //working on game
    
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
    
//    def mousePos(){
//      
//      var rect = canvas.getBoundingClientRect();
//      return {
//        x: event.clientx - rect.left;
//        y: event.clienty - rect.top;
//      };
//    }
//    
//    def isInside(pos,rect){
//      return pos.x > rect.x && pos.x < rect.x + rect.width && pos.y < rect.y + rect.height && pos.y > rect.y
//        
//    }
//    
//    var rect = {
//      x:220,
//      y:375,
//      width:200,
//      height: 100
//    }
//    
//    canvas.addEventListener("click", function(evt){
//      var mousePos = mousePos(canvas, evt);
//      if(isInside(mousePos,rect)){
//        alert("clicked");
//      }
//      
//    }
    
}