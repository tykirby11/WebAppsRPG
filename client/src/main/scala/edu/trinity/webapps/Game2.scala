package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._
import org.scalajs.dom.raw._
import org.scalajs.dom.raw.HTMLImageElement
import scala.scalajs.js.timers._

object CanvasMapDrawing {

  private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
  private val context = canvas.getContext("2d")

  //handling user input
    dom.document.onmousedown = (e: dom.MouseEvent) => {
    val coords = (e.clientX, e.clientY)
      
    println(s"x: ${coords._1}, y: ${coords._2}")
      
    //if left node is pressed, trigger event
    if((e.clientX > 289 && e.clientX < 463) && (e.clientY > 380 && e.clientY < 454))
      {
      println("clicked left node, this should do something!")
      println("Enemy Encounter triggered")
      CanvasDrawing.drawArena()
      }
    //if right node is pressed, trigger event 
    else if ((e.clientX > 589 && e.clientX < 764) && (e.clientY > 380 && e.clientY < 453))
      {
      println("clicked right node, this should do something!")
      }
    }  
  
  def drawMap(): Unit = {
    
    //background image for map view
    val mapBackground = dom.document.getElementById("mapBackground")
    context.drawImage(mapBackground, 0, 0, canvas.width, 600)
    
    //left node
    context.rect(175, 240, 175, 75)
    context.stroke()
    context.lineWidth = "3"
    context.fillStyle = "white"
    context.font = "20px Arial"
    context.fillText("Go Left For:", 180, 271)
    context.fillText("Enemy Encounter", 180, 300);
    
    //right node
    context.rect(475, 240, 175, 75)
    context.stroke()
    context.fillStyle = "white"
    context.font = "20px Arial"
    context.fillText("Go Right For:", 480, 271)
    context.fillText("Mystery Event", 480, 300);
    
    //user score
    context.fillText("Nodes Visited: 69", 620, 30)
    
    //gold
    context.fillText("Gold: 100", 30, 30)
  }
}