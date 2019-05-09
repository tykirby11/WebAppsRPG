package edu.trinity.webapps

import org.scalajs.dom
import scala.scalajs.js
import org.querki.jquery._

import scala.scalajs.js.annotation._
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

import edu.trinity.webapps.shared.DBShared._



object DBClient {
  var enemyList: List[EnemyEntry] = null
  
 /* def drawPlayerClassToCanvas(): List[ClassEntry] = {
    var classList: List[ClassEntry] = null
    $.getJSON("/classes", success = (o, s, j) => {
    for(c <- Json.parse(js.JSON.stringify(o)).as[List[ClassEntry]] {
      val classNames = $(s"<p>${c.name}</p>")
     
    }
    })
    classList
  }
 */ 
  

  def getEnemyList(): Unit = {
    $.getJSON("/enemies", success = (o, s, j) => {
      enemyList = Json.parse(js.JSON.stringify(o)).as[List[EnemyEntry]]
      println(enemyList)
    })
  }
  
  def updateEnemyList(elist: List[EnemyEntry]): Unit = {
    println(elist)
    enemyList = elist
  }
  
  def getBossList(): List[BossEntry] = {
    var bossList: List[BossEntry] = null
    $.getJSON("/bosses", success = (o, s, j) => {
      bossList = Json.parse(js.JSON.stringify(o)).as[List[BossEntry]]
    })
    bossList
  }
  
  def getItemList(): List[ItemEntry] = {
    var itemList: List[ItemEntry] = null
    $.getJSON("/items", success = (o, s, j) => {
      itemList = Json.parse(js.JSON.stringify(o)).as[List[ItemEntry]]
    })
    itemList
  }
  
  def getWeaponList(): List[WeaponEntry] = {
    var weaponList: List[WeaponEntry] = null
    $.getJSON("/weapons", success = (o, s, j) => {
      weaponList = Json.parse(js.JSON.stringify(o)).as[List[WeaponEntry]]
    })
    weaponList
  }
  
  def getClassList(): List[ClassEntry] = {
    var classList: List[ClassEntry] = null
    $.getJSON("/classes", success = (o, s, j) => {
      classList = Json.parse(js.JSON.stringify(o)).as[List[ClassEntry]]
    })
    classList
  }
}