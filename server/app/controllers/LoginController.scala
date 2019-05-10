package controllers

import javax.inject._

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import scala.concurrent.ExecutionContext
import slick.jdbc.JdbcProfile
import scala.concurrent.Future

@Singleton
class LoginController @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext)
extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {


   def login = Action.async { implicit request =>
    val postBody = request.body.asFormUrlEncoded
    postBody.map { args =>
      
        val user = args("username").head
        val pass = args("password").head
        
        println(user)
        println(pass)
        
        val resultFuture = models.DBQueries.verifyLogin(user, pass, db)
        resultFuture.map(result =>
        if(result){
        Redirect(routes.LoginController.menu).withSession("username" -> user)
        }
        else{
          Redirect(routes.LoginController.loginPage)
        })
      
    }.getOrElse(Future.successful(Redirect(routes.LoginController.loginPage)))
  }
   
  def loginPage = Action {
    println("Got here PAGE")
    Ok(views.html.login())
  }
  
  /*def menu = Action { implicit request =>
    if(request.session.get("username").nonEmpty){
      Ok(views.html.menu())
      
    }
    else{
      Redirect(routes.LoginController.loginPage)
    }
  }*/
  
  def menu = Action {
    Ok(views.html.menu())
  }
  
  def logout = Action { implicit request =>
    Redirect(routes.LoginController.loginPage).withSession()
  }
  
  def createUser = Action.async { implicit request =>
    println("Got here CREATE")
    val postBody = request.body.asFormUrlEncoded
    println("Got here CREATE BODY " + postBody)
    postBody.map { args =>
      
      
        val user = args("usernameC").head
        val pass = args("passwordC").head
        
        models.DBQueries.addLoginInfo(user, pass, db)
        Future.successful(Redirect(routes.LoginController.loginPage))
      
    }.getOrElse(Future.successful(Redirect(routes.LoginController.loginPage)))
  }
}
