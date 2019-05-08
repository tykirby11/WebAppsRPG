package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._
import org.scalajs.dom.raw._
import org.scalajs.dom.raw.HTMLImageElement
import scala.scalajs.js.timers._

object CanvasMapDrawing {

  private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
  private val context = canvas.getContext("2d") 
  
  private var eventTriggered = false

  def drawMap(): Unit = {
    
    //handling user input for map
    dom.document.onmousedown = (e: dom.MouseEvent) => {
    val coords = (e.clientX, e.clientY)
      
    println(s"x: ${coords._1}, y: ${coords._2}")
      
    //if left node is pressed, trigger event
    if((e.clientX > 728 && e.clientX < 904) && (e.clientY > 454 && e.clientY < 529))
      {
      println("clicked left node, this should do something!")
      println("Enemy Encounter triggered")
      }
    //if right node is pressed, trigger event 
    else if ((e.clientX > 1030 && e.clientX < 1204) && (e.clientY > 454 && e.clientY < 529))
      {
      println("clicked right node, this should do something!")
      CanvasMapDrawing.drawEvent()
      eventTriggered = true
      }
    }  
    
    //clear canvas before drawing out map
    context.clearRect(0, 0, canvas.width, canvas.height)
    
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
  
  def drawEvent(): Unit = {
    
    //handling user input for an event
    dom.document.onmousedown = (e: dom.MouseEvent) => {
    val coords = (e.clientX, e.clientY)
      
    println(s"x: ${coords._1}, y: ${coords._2}")
      
    //if continue area is pressed, trigger event
    if((e.clientX > 789 && e.clientX < 1013) && (e.clientY > 613 && e.clientY < 658))
      {
      println("clicked continue, this should return you to map!")
      CanvasMapDrawing.drawMap()
      }
    }
    
    //clear out map area for event
    context.clearRect(0, 0, canvas.width, canvas.height)
    val eventBackground = dom.document.getElementById("eventBackground")
    context.drawImage(eventBackground, 0, 0, canvas.width, canvas.height)
    context.fillText("You've Found a Lost Treasure Chest", 250, 120)
    context.fillText("Gold +50!", 250, 285)
    context.fillText("Click Here to Continue", 250, 430)
  }
}
