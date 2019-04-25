package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._



@Singleton
class LoginController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

   


   def login = Action { implicit request =>
    Ok(views.html.login())
  }
}
