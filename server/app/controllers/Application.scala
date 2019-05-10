package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

@Singleton
class mainMenuController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def game = Action { implicit request =>
    //Ok(views.html.gamePage())
    if(request.session.get("username").nonEmpty){
      Ok(views.html.gamePage())
      
    }
    else{
      Redirect(routes.LoginController.loginPage)
    }
  }
  
  def mainmenu = Action { implicit request =>
    Ok(views.html.menu())
  }
  
    def glossary = Action { implicit request =>
    Ok(views.html.glossary())
  }
    
    def leaderboard = Action { implicit request =>
    Ok(views.html.leaderboards())
  }  
}
