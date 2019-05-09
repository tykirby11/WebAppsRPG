package edu.trinity.webapps

import edu.trinity.webapps.shared.DBShared._

class EnemyNode(val state: NodeState.Value, val dbList: List[EnemyEntry]) extends Node {
  def rollSelection: EnemyEntry = {
    var currentEvent = dbList.apply(scala.util.Random.nextInt(dbList.length))
    currentEvent
  }
}