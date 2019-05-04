package edu.trinity.webapps

import edu.trinity.webapps.shared.SharedMessages
import org.scalajs.dom

object ScalaJSExample {

  def main(args: Array[String]): Unit = {

    if(dom.document.getElementById("gameCanvas") != null) CanvasDrawing.drawToCanvas()
  }
  
  case class NotAString(i: Int, s: String)
  val a: Any = NotAString(5, "hi")
  a.asInstanceOf[NotAString].i*2 
}

