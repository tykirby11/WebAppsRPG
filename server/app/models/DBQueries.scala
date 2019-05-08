package models

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext
import Tables._

object DBQueries {
  def verifyLogin(username: String, password: String, db: Database)(implicit ec: ExecutionContext): Future[Boolean] = {
    println("Got here VERIFY")
    db.run {
      (for (u <- User; if u.password === password && u.username === username) yield {
        u.id
      }).exists.result
    }
  }
  
  def addLoginInfo(username: String, password: String, db: Database)(implicit ec: ExecutionContext): Future[Int] = {
    println("Got here ADD")
    db.run {
      User += UserRow(0, username, password) //0 is ignored, auto increment
    }
  }
  
  def fetchEnemies(db: Database)(implicit ec: ExecutionContext): Future[Seq[EnemiesRow]] = {
    db.run {
      (for (e <- Enemies) yield {
        e
      }).result
    }
  }
  
  def fetchBosses(db: Database)(implicit ec: ExecutionContext): Future[Seq[BossesRow]] = {
    db.run {
      (for (b <- Bosses) yield {
        b
      }).result
    }
  }
  
  def fetchItems(db: Database)(implicit ec: ExecutionContext): Future[Seq[ItemsRow]] = {
    db.run {
      (for (i <- Items) yield {
        i
      }).result
    }
  }
  
  def fetchWeapons(db: Database)(implicit ec: ExecutionContext): Future[Seq[WeaponsRow]] = {
    db.run {
      (for (w <- Weapons) yield {
        w
      }).result
    }
  }
  
  def fetchPlayers(db: Database)(implicit ec: ExecutionContext): Future[Seq[PlayerclassRow]] = {
    db.run {
      (for (p <- Playerclass) yield {
        p
      }).result
    }
  }
  
  def fetchUsers(db: Database)(implicit ec: ExecutionContext): Future[Seq[UserRow]] = {
    db.run {
      (for (u <- User) yield {
        u
      }).result
    }
  }
}