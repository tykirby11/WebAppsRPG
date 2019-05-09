package controllers

import javax.inject._
import play.api.mvc._
import scala.concurrent.Future
import models._

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

import slick.jdbc.MySQLProfile.api._ // This line determines what type of database you are connecting to.
import play.api.libs.json.Json

import edu.trinity.webapps.shared.DBShared._

@Singleton
class DBController @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  def enemies = Action.async { implicit request =>
    val enemies = DBQueries.fetchEnemies(db)
    enemies.map(e => Ok(Json.toJson(e)))
  }

  def bosses = Action.async { implicit request =>
    val bosses = DBQueries.fetchBosses(db)
    bosses.map(e => Ok(Json.toJson(e)))
  }

  def items = Action.async { implicit request =>
    val items = DBQueries.fetchItems(db)
    items.map(e => Ok(Json.toJson(e)))
  }

  def weapons = Action.async { implicit request =>
    val weapons = DBQueries.fetchWeapons(db)
    weapons.map(e => Ok(Json.toJson(e)))
  }

  def classes = Action.async { implicit request =>
    val classes = DBQueries.fetchClasses(db)
    classes.map(e => Ok(Json.toJson(e)))
  }

  def nodeBB = Action.async { implicit request =>
    val enemies = DBQueries.fetchEnemies(db)
    enemies.map(e => Ok(Json.toJson(e)))
  }

  def nodeBP = Action.async { implicit request =>
    val enemies = DBQueries.fetchEnemies(db)
    val items = DBQueries.fetchItems(db)
    enemies.map(e => Ok(Json.toJson(e)))
    items.map(e => Ok(Json.toJson(e)))
  }

  def nodeBY = Action.async { implicit request =>
    val enemies = DBQueries.fetchEnemies(db)
    val weapons = DBQueries.fetchWeapons(db)
    enemies.map(e => Ok(Json.toJson(e)))
    weapons.map(e => Ok(Json.toJson(e)))
  }

  def nodeBR = Action.async { implicit request =>
    val enemies = DBQueries.fetchEnemies(db)
    val bosses = DBQueries.fetchBosses(db)
    enemies.map(e => Ok(Json.toJson(e)))
    bosses.map(e => Ok(Json.toJson(e)))
  }

  def nodePP = Action.async { implicit request =>
    val items = DBQueries.fetchItems(db)
    items.map(e => Ok(Json.toJson(e)))
  }

  def nodePY = Action.async { implicit request =>
    val weapons = DBQueries.fetchWeapons(db)
    val items = DBQueries.fetchItems(db)
    weapons.map(e => Ok(Json.toJson(e)))
    items.map(e => Ok(Json.toJson(e)))
  }

  def nodePR = Action.async { implicit request =>
    val bosses = DBQueries.fetchBosses(db)
    val items = DBQueries.fetchItems(db)
    bosses.map(e => Ok(Json.toJson(e)))
    items.map(e => Ok(Json.toJson(e)))
  }

  def nodeYY = Action.async { implicit request =>
    val weapons = DBQueries.fetchWeapons(db)
    weapons.map(e => Ok(Json.toJson(e)))
  }

  def nodeYR = Action.async { implicit request =>
    val weapons = DBQueries.fetchWeapons(db)
    val bosses = DBQueries.fetchBosses(db)
    weapons.map(e => Ok(Json.toJson(e)))
    bosses.map(e => Ok(Json.toJson(e)))
  }

  def nodeRR = Action.async { implicit request =>
    val bosses = DBQueries.fetchBosses(db)
    bosses.map(e => Ok(Json.toJson(e)))
  }

}