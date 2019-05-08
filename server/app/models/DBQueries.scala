package models

import scala.concurrent.Future
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext
import Tables._

import edu.trinity.webapps.shared.DBShared._

object DBQueries {
  def verifyLogin(username: String, password: String, db: Database)(implicit ec: ExecutionContext): Future[Boolean] = {
    db.run {
      (for (u <- User; if u.password === password && u.username === username) yield {
        u.id
      }).exists.result
    }
  }
  
  def addLoginInfo(username: String, password: String, db: Database)(implicit ec: ExecutionContext): Future[Int] = {
    db.run {
      User += UserRow(0, username, password) //0 is ignored, auto increment
    }
  }
  
  def fetchEnemies(db: Database)(implicit ec: ExecutionContext): Future[Seq[EnemyEntry]] = {
    val enemyList = db.run {
      (for (e <- Enemies) yield {
        e
      }).result
    }
    enemyList.map(enemies => enemies.map(rowToEnemy))
  }
  
  def rowToEnemy(er: EnemiesRow): EnemyEntry = {
    EnemyEntry(er.enemyid, er.name, er.hp, er.atk, er.spd)
  }
  
  def fetchBosses(db: Database)(implicit ec: ExecutionContext): Future[Seq[BossEntry]] = {
    val bossList = db.run {
      (for (b <- Bosses) yield {
        b
      }).result
    }
    bossList.map(bosses => bosses.map(rowToBoss))
  }
  
  def rowToBoss(er: BossesRow): BossEntry = {
    BossEntry(er.bossid, er.name, er.hp, er.atk, er.spd)
  }
  
  def fetchItems(db: Database)(implicit ec: ExecutionContext): Future[Seq[ItemEntry]] = {
    val itemList = db.run {
      (for (i <- Items) yield {
        i
      }).result
    }
    itemList.map(items => items.map(rowToItem))
  }
  
  def rowToItem(er: ItemsRow): ItemEntry = {
    ItemEntry(er.itemid, er.name, er.hpmod, er.atkmod, er.spdmod, er.uses)
  }
  
  def fetchWeapons(db: Database)(implicit ec: ExecutionContext): Future[Seq[WeaponEntry]] = {
    val weaponList = db.run {
      (for (w <- Weapons) yield {
        w
      }).result
    }
    weaponList.map(weapons => weapons.map(rowToWeapon))
  }
  
  def rowToWeapon(er: WeaponsRow): WeaponEntry = {
    WeaponEntry(er.weaponid, er.name, er.hpmod, er.atkmod, er.spdmod)
  }
  
  def fetchClasses(db: Database)(implicit ec: ExecutionContext): Future[Seq[ClassEntry]] = {
    val classList = db.run {
      (for (p <- Playerclass) yield {
        p
      }).result
    }
    classList.map(pclass => pclass.map(rowToClass))
  }
  
  def rowToClass(er: PlayerclassRow): ClassEntry = {
    ClassEntry(er.id, er.name, er.hp, er.atk, er.spd)
  }
  
  def fetchUsers(db: Database)(implicit ec: ExecutionContext): Future[Seq[UserRow]] = {
    db.run {
      (for (u <- User) yield {
        u
      }).result
    }
  }
}