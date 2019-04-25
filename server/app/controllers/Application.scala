package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

@Singleton
class mainMenuController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  
 def mainmenu = Action{ implicit request =>
   Ok(views.html.menu())
 }
 
}