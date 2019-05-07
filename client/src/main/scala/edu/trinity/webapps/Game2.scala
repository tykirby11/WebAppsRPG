//package edu.trinity.webapps
//
//import org.scalajs.dom
//import org.querki.jquery._
//import org.scalajs.dom.raw._
//import org.scalajs.dom.raw.HTMLImageElement
//import scala.scalajs.js.timers._
//
//object CanvasDrawing2 {
//
//  private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
//  private val context = canvas.getContext("2d")
//
//
//  def drawArena(): Unit = {
//    val mapBackground = dom.document.getElementById("mapBackground")
//    println(mapBackground) 
//    context.drawImage(mapBackground, 0, 0, canvas.width, 600)
//  }
//}