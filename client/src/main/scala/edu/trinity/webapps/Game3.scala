//package edu.trinity.webapps
//
//import org.scalajs.dom
//import org.querki.jquery._
//import org.scalajs.dom.raw._
//import org.scalajs.dom.raw.HTMLImageElement
//import scala.scalajs.js.timers._
//
//object CanvasDrawing {
//    private var x = 100
//    private var y = 100
//    private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
//    private val context = canvas.getContext("2d")
//    
//    private var charSelExists = false;
//    private var areYouSure = false;
//    private var selectedID = 0
//    
//    def drawArena(): Unit = {
//      //battle window
//        context.rect(20,20,760,460);
//        context.stroke();
//                
//        context.beginPath();
//        context.moveTo(260, 20);
//        context.lineTo(260, 480);
//        context.stroke();
//        
//        context.beginPath();
//        context.moveTo(520, 20);
//        context.lineTo(520, 480);
//        context.stroke();
//        
//        context.beginPath();
//        context.moveTo(20, 420);
//        context.lineTo(780, 420);
//        context.stroke();
//        
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Select", 100, 460);
//        
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Select", 350, 460);
//        
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Select", 600, 460);
//        
//        
//        
//
//
//        charSelExists = true;
//        context.rect(100,100,560,260);
//        context.stroke();
//        context.fillStyle = "rgb(169,169,169)";
//        context.fillRect(100,100,560,260);
//        context.fillStyle = "rgb(0,0,0)";
//        context.rect(120,300,100,100);
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Attack",65,375);
//        
//        
//        
//
//    }
//    
//    def select() : Unit = {
//      if(areYouSure == true){
//        
//        context.rect(100,100,560,260);
//        context.stroke();
//        context.fillStyle = "rgb(169,169,169)";
//        context.fillRect(100,100,560,260)
//      }
//      
//        
//    }
//    
//    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
//    canvas.onmousedown = (e: dom.MouseEvent) => {
//      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)
//      
//      println(s"x: ${coords._1}, y: ${coords._2}")
//      
//      //Select in col 1
//      if(((e.clientX - canvas.offsetLeft) > 20 && (e.clientX - canvas.offsetLeft) < 260) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
//        println("col 1 clicked")
//    }
//      //Select in col 2
//      if(((e.clientX - canvas.offsetLeft) > 260 && (e.clientX - canvas.offsetLeft) < 522) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
//        println("col 2 clicked")
//    }
//      //Select in col 3
//      if(((e.clientX - canvas.offsetLeft) > 522 && (e.clientX - canvas.offsetLeft) < 782) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
//        println("col 3 clicked")
//    }     
//      //Confirm for character select
//          if(((e.clientX - canvas.offsetLeft) > 522 && (e.clientX - canvas.offsetLeft) < 782) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && areYouSure == true){
//        println("col 3 clicked")
//    }
//    }
//}