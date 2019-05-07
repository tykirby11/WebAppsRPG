package edu.trinity.webapps.shared

import play.api.libs.json._
import play.api.libs.functional.syntax._

object DBShared {
  //bosses
  //items
  //weapon
  //classes
  
  /** Entity class storing rows of table Bosses
   *  @param bossid Database column bossid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hp Database column hp SqlType(INT)
   *  @param atk Database column atk SqlType(INT)
   *  @param spd Database column spd SqlType(INT) */
  case class BossesRow(bossid: Int, name: String, hp: Int, atk: Int, spd: Int)
  
  /** Entity class storing rows of table Enemies
   *  @param enemyid Database column enemyid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hp Database column hp SqlType(INT)
   *  @param atk Database column atk SqlType(INT)
   *  @param spd Database column spd SqlType(INT) */
  case class EnemiesRow(enemyid: Int, name: String, hp: Int, atk: Int, spd: Int)
  
  /** Entity class storing rows of table Items
   *  @param itemid Database column itemid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hpmod Database column hpmod SqlType(INT)
   *  @param atkmod Database column atkmod SqlType(INT)
   *  @param spdmod Database column spdmod SqlType(INT)
   *  @param uses Database column uses SqlType(INT) */
  case class ItemsRow(itemid: Int, name: String, hpmod: Int, atkmod: Int, spdmod: Int, uses: Int)
  
  /** Entity class storing rows of table Weapons
   *  @param weaponid Database column weaponid SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column name SqlType(VARCHAR), Length(30,true)
   *  @param hpmod Database column hpmod SqlType(INT)
   *  @param atkmod Database column atkmod SqlType(INT)
   *  @param spdmod Database column spdmod SqlType(INT) */
  case class WeaponsRow(weaponid: Int, name: String, hpmod: Int, atkmod: Int, spdmod: Int)
  
  implicit val bossesRowWrites: Writes[BossesRow] = (
    (JsPath \ "bossid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hp").write[Int] and
    (JsPath \ "atk").write[Int] and
    (JsPath \ "spd").write[Int])(unlift(BossesRow.unapply))
    
  implicit val bossesRowReads: Reads[BossesRow] = (
    (JsPath \ "bossid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hp").read[Int] and
    (JsPath \ "atk").read[Int] and
    (JsPath \ "spd").read[Int])(BossesRow.apply _)
  
  implicit val enemiesRowWrites: Writes[EnemiesRow] = (
    (JsPath \ "enemyid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hp").write[Int] and
    (JsPath \ "atk").write[Int] and
    (JsPath \ "spd").write[Int])(unlift(EnemiesRow.unapply))
    
  implicit val enemiesRowReads: Reads[EnemiesRow] = (
    (JsPath \ "enemyid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hp").read[Int] and
    (JsPath \ "atk").read[Int] and
    (JsPath \ "spd").read[Int])(EnemiesRow.apply _)
    
  implicit val itemsRowWrites: Writes[ItemsRow] = (
    (JsPath \ "itemid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hpmod").write[Int] and
    (JsPath \ "atkmod").write[Int] and
    (JsPath \ "spdmod").write[Int] and
    (JsPath \ "uses").write[Int])(unlift(ItemsRow.unapply))
    
  implicit val itemsRowReads: Reads[ItemsRow] = (
    (JsPath \ "itemid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hpmod").read[Int] and
    (JsPath \ "atkmod").read[Int] and
    (JsPath \ "spdmod").read[Int] and
    (JsPath \ "uses").read[Int])(ItemsRow.apply _)
    
  implicit val weaponsRowWrites: Writes[WeaponsRow] = (
    (JsPath \ "weaponid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hpmod").write[Int] and
    (JsPath \ "atkmod").write[Int] and
    (JsPath \ "spdmod").write[Int])(unlift(WeaponsRow.unapply))
    
  implicit val weaponsRowReads: Reads[WeaponsRow] = (
    (JsPath \ "weaponid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hpmod").read[Int] and
    (JsPath \ "atkmod").read[Int] and
    (JsPath \ "spdmod").read[Int])(WeaponsRow.apply _)
  
}