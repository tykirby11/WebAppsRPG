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

  def getEnemy(handler: EnemyEntry => Unit): Unit = {
    $.getJSON("/enemies", success = (o, s, j) => {
      val enemy = Json.parse(js.JSON.stringify(o)).as[List[EnemyEntry]]
      handler(enemy.head)
    })
  }

  def generateNodes(): Unit = {
    val node1Type = NodeGenerator.getNodeType
    val node2Type = NodeGenerator.getNodeType

    if ((node1Type == NodeState.black) && (node2Type == NodeState.black)) {
      println("BB")
      $.getJSON("/nodeBB", success = (o, s, j) => {
        val enemies = Json.parse(js.JSON.stringify(o)).as[List[EnemyEntry]]
        val node1 = new MapNode[EnemyEntry](NodeState.black, enemies)
        val node2 = new MapNode[EnemyEntry](NodeState.black, enemies)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if (((node1Type == NodeState.black) || (node2Type == NodeState.black)) && ((node1Type == NodeState.purple) || (node2Type == NodeState.purple))) {
      $.getJSON("/nodeBP", success = (o, s, j) => {
        val enemies = Json.parse(js.JSON.stringify(o)).as[List[EnemyEntry]]
        val items = Json.parse(js.JSON.stringify(o)).as[List[ItemEntry]]
        val node1 = new MapNode[EnemyEntry](NodeState.black, enemies)
        val node2 = new MapNode[ItemEntry](NodeState.purple, items)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if (((node1Type == NodeState.black) || (node2Type == NodeState.black)) && ((node1Type == NodeState.yellow) || (node2Type == NodeState.yellow))) {
      $.getJSON("/nodeBY", success = (o, s, j) => {
        val enemies = Json.parse(js.JSON.stringify(o)).as[List[EnemyEntry]]
        val weapons = Json.parse(js.JSON.stringify(o)).as[List[WeaponEntry]]
        val node1 = new MapNode[EnemyEntry](NodeState.black, enemies)
        val node2 = new MapNode[WeaponEntry](NodeState.yellow, weapons)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if (((node1Type == NodeState.black) || (node2Type == NodeState.black)) && ((node1Type == NodeState.red) || (node2Type == NodeState.red))) {
      $.getJSON("/nodeBR", success = (o, s, j) => {
        val enemies = Json.parse(js.JSON.stringify(o)).as[List[EnemyEntry]]
        val bosses = Json.parse(js.JSON.stringify(o)).as[List[BossEntry]]
        val node1 = new MapNode[EnemyEntry](NodeState.black, enemies)
        val node2 = new MapNode[BossEntry](NodeState.red, bosses)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if ((node1Type == NodeState.purple) && (node2Type == NodeState.purple)) {
      $.getJSON("/nodePP", success = (o, s, j) => {
        val items = Json.parse(js.JSON.stringify(o)).as[List[ItemEntry]]
        val node1 = new MapNode[ItemEntry](NodeState.purple, items)
        val node2 = new MapNode[ItemEntry](NodeState.purple, items)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if (((node1Type == NodeState.purple) || (node2Type == NodeState.purple)) && ((node1Type == NodeState.yellow) || (node2Type == NodeState.yellow))) {
      $.getJSON("/nodePY", success = (o, s, j) => {
        val weapons = Json.parse(js.JSON.stringify(o)).as[List[WeaponEntry]]
        val items = Json.parse(js.JSON.stringify(o)).as[List[ItemEntry]]
        val node1 = new MapNode[WeaponEntry](NodeState.yellow, weapons)
        val node2 = new MapNode[ItemEntry](NodeState.purple, items)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if (((node1Type == NodeState.purple) || (node2Type == NodeState.purple)) && ((node1Type == NodeState.red) || (node2Type == NodeState.red))) {
      $.getJSON("/nodePR", success = (o, s, j) => {
        val bosses = Json.parse(js.JSON.stringify(o)).as[List[BossEntry]]
        val items = Json.parse(js.JSON.stringify(o)).as[List[ItemEntry]]
        val node1 = new MapNode[BossEntry](NodeState.red, bosses)
        val node2 = new MapNode[ItemEntry](NodeState.purple, items)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if ((node1Type == NodeState.yellow) && (node2Type == NodeState.yellow)) {
      $.getJSON("/nodeYY", success = (o, s, j) => {
        val weapons = Json.parse(js.JSON.stringify(o)).as[List[WeaponEntry]]
        val node1 = new MapNode[WeaponEntry](NodeState.yellow, weapons)
        val node2 = new MapNode[WeaponEntry](NodeState.yellow, weapons)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if (((node1Type == NodeState.yellow) || (node2Type == NodeState.yellow)) && ((node1Type == NodeState.red) || (node2Type == NodeState.red))) {
      $.getJSON("/nodeYR", success = (o, s, j) => {
        val weapons = Json.parse(js.JSON.stringify(o)).as[List[WeaponEntry]]
        val bosses = Json.parse(js.JSON.stringify(o)).as[List[BossEntry]]
        val node1 = new MapNode[WeaponEntry](NodeState.yellow, weapons)
        val node2 = new MapNode[BossEntry](NodeState.red, bosses)
        CanvasDrawing.drawMap(node1, node2)
      })
    } else if ((node1Type == NodeState.red) && (node2Type == NodeState.red)) {
      $.getJSON("/nodeRR", success = (o, s, j) => {
        val bosses = Json.parse(js.JSON.stringify(o)).as[List[BossEntry]]
        val node1 = new MapNode[BossEntry](NodeState.red, bosses)
        val node2 = new MapNode[BossEntry](NodeState.red, bosses)
        CanvasDrawing.drawMap(node1, node2)
      })
    }
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