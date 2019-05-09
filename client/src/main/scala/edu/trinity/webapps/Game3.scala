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
//    private var youChosePopup = false;
//    private var selectedID = 0
//    
//    def drawCharacterSelect(): Unit = {
//       
//        charSelect();
//        tankSelect();
//        warriorSelect();
//        rougeSelect();
//
//        charSelExists = true;
//    }
//    
//    def charSelect() : Unit ={
//      context.clearRect(0, 900, canvas.width, canvas.height);
//       //battle window
//        context.rect(20,20,760,460);
//        context.stroke();
//      //col 1          
//        context.beginPath();
//        context.moveTo(260, 20);
//        context.lineTo(260, 480);
//        context.stroke();
//      //col 2  
//        context.beginPath();
//        context.moveTo(520, 20);
//        context.lineTo(520, 480);
//        context.stroke();
//      // bottom select line
//        context.beginPath();
//        context.moveTo(20, 420);
//        context.lineTo(780, 420);
//        context.stroke();
//        //select col 1
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Select", 100, 460);
//        //select col 2
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Select", 350, 460);
//        //select col 3
//        context.stroke();
//        context.font = "30px Arial";
//        context.fillText("Select", 600, 460);
//    }
//    
//    def tankSelect() : Unit = {
//        context.stroke();
//        context.font = "50px Arial";
//        context.fillText("Tank", 87, 65);
//        context.font = "30px Arial";
//        context.fillText("Health: 300", 65, 215);
//        context.fillText("Attack: 200", 65, 265);
//        context.fillText("Speed: 100", 65, 315);
//
//    }
//    
//    def warriorSelect() : Unit = {
//        context.stroke();
//        context.font = "50px Arial";
//        context.fillText("Warrior", 315, 65);
//        context.font = "30px Arial";
//        context.fillText("Health: 100", 315, 215);
//        context.fillText("Attack: 300", 315, 265);
//        context.fillText("Speed: 200", 315, 315);
//
//    }
//    def rougeSelect() : Unit = {
//        context.stroke();
//        context.font = "50px Arial";
//        context.fillText("Rouge", 580, 65);
//        context.font = "30px Arial";
//        context.fillText("Health: 100", 580, 215);
//        context.fillText("Attack: 200", 580, 265);
//        context.fillText("Speed: 300", 580, 315);
//
//    }
//    
//    def select() : Unit = {
//      if(youChosePopup == true){
//        context.rect(100,100,560,260);
//        context.stroke();
//        context.fillStyle = "rgb(169,169,169)";
//        context.fillRect(100,100,560,260);
//        context.fillStyle = "rgb(0,0,0)";
//        context.font = "50px Arial";
//        context.fillText("You chose:",245,150);
//        //cancel
//
//        if(selectedID == 1){
//        context.stroke();
//        context.font = "70px Arial";
//        context.fillText("Tank!",280,275);
//        }
//        if(selectedID == 2){
//        context.stroke();
//        context.font = "70px Arial";
//        context.fillText("Warrior!",280,275);
//        }
//        if(selectedID == 3){
//        context.stroke();
//        context.font = "70px Arial";
//        context.fillText("Rouge!",280,275);
//        }
//        setTimeout(5000)(context.clearRect(0,0,canvas.width,canvas.height));
//      }
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
//        selectedID = 1;
//        youChosePopup = true;
//        charSelExists = false;
//        select();
//    }
//      //Select in col 2
//      if(((e.clientX - canvas.offsetLeft) > 260 && (e.clientX - canvas.offsetLeft) < 522) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
//        println("col 2 clicked")
//        selectedID = 2;
//        youChosePopup = true;
//        charSelExists = false;
//        select();
//    }
//      //Select in col 3
//      if(((e.clientX - canvas.offsetLeft) > 522 && (e.clientX - canvas.offsetLeft) < 782) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
//        println("col 3 clicked")
//        selectedID = 3;
//        youChosePopup = true;
//        charSelExists = false;
//        select();
//    }     
////      //Cancel for character select
////      if(((e.clientX - canvas.offsetLeft) > 202 && (e.clientX - canvas.offsetLeft) < 342) && ((e.clientY - canvas.offsetTop) > 292 && (e.clientY - canvas.offsetTop) < 343) && areYouSure == true){
////        println("Cancel Clicked")
////        areYouSure=false;
////        select();
////    }
////          
////      //Confirm for character select
////      if(((e.clientX - canvas.offsetLeft) > 421 && (e.clientX - canvas.offsetLeft) < 562) && ((e.clientY - canvas.offsetTop) > 292 && (e.clientY - canvas.offsetTop) < 343) && areYouSure == true){
////        println("Confirm Clicked")
////    }      
//    }
//}