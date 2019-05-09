package edu.trinity.webapps

class MapNode[A](val state: NodeState.Value, val dbList: List[A]) extends Node {
  def rollSelection: A = {
    var currentEvent = dbList.apply(scala.util.Random.nextInt(dbList.length))
    currentEvent
  }
}