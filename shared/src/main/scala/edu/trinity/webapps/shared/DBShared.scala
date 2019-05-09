package edu.trinity.webapps.shared

import play.api.libs.json._
import play.api.libs.functional.syntax._

object DBShared {
  //bosses
  //items
  //weapon
  //classes

  case class BossEntry(bossid: Int, name: String, hp: Int, atk: Int, spd: Int)

  case class EnemyEntry(enemyid: Int, name: String, hp: Int, atk: Int, spd: Int)

  case class ItemEntry(itemid: Int, name: String, hpmod: Int, atkmod: Int, spdmod: Int, uses: Int)

  case class WeaponEntry(weaponid: Int, name: String, hpmod: Int, atkmod: Int, spdmod: Int)

  case class ClassEntry(classid: Int, name: String, hp: Int, atk: Int, spd: Int)

  implicit val bossEntryWrites: Writes[BossEntry] = (
    (JsPath \ "bossid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hp").write[Int] and
    (JsPath \ "atk").write[Int] and
    (JsPath \ "spd").write[Int])(unlift(BossEntry.unapply))

  implicit val bossEntryReads: Reads[BossEntry] = (
    (JsPath \ "bossid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hp").read[Int] and
    (JsPath \ "atk").read[Int] and
    (JsPath \ "spd").read[Int])(BossEntry.apply _)

  implicit val enemyEntryWrites: Writes[EnemyEntry] = (
    (JsPath \ "enemyid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hp").write[Int] and
    (JsPath \ "atk").write[Int] and
    (JsPath \ "spd").write[Int])(unlift(EnemyEntry.unapply))

  implicit val enemyEntryReads: Reads[EnemyEntry] = (
    (JsPath \ "enemyid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hp").read[Int] and
    (JsPath \ "atk").read[Int] and
    (JsPath \ "spd").read[Int])(EnemyEntry.apply _)

  implicit val itemEntryWrites: Writes[ItemEntry] = (
    (JsPath \ "itemid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hpmod").write[Int] and
    (JsPath \ "atkmod").write[Int] and
    (JsPath \ "spdmod").write[Int] and
    (JsPath \ "uses").write[Int])(unlift(ItemEntry.unapply))

  implicit val itemEntryReads: Reads[ItemEntry] = (
    (JsPath \ "itemid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hpmod").read[Int] and
    (JsPath \ "atkmod").read[Int] and
    (JsPath \ "spdmod").read[Int] and
    (JsPath \ "uses").read[Int])(ItemEntry.apply _)

  implicit val weaponEntryWrites: Writes[WeaponEntry] = (
    (JsPath \ "weaponid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hpmod").write[Int] and
    (JsPath \ "atkmod").write[Int] and
    (JsPath \ "spdmod").write[Int])(unlift(WeaponEntry.unapply))

  implicit val weaponEntryReads: Reads[WeaponEntry] = (
    (JsPath \ "weaponid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hpmod").read[Int] and
    (JsPath \ "atkmod").read[Int] and
    (JsPath \ "spdmod").read[Int])(WeaponEntry.apply _)

  implicit val classEntryWrites: Writes[ClassEntry] = (
    (JsPath \ "classid").write[Int] and
    (JsPath \ "name").write[String] and
    (JsPath \ "hp").write[Int] and
    (JsPath \ "atk").write[Int] and
    (JsPath \ "spd").write[Int])(unlift(ClassEntry.unapply))

  implicit val classEntryReads: Reads[ClassEntry] = (
    (JsPath \ "classid").read[Int] and
    (JsPath \ "name").read[String] and
    (JsPath \ "hp").read[Int] and
    (JsPath \ "atk").read[Int] and
    (JsPath \ "spd").read[Int])(ClassEntry.apply _)
}