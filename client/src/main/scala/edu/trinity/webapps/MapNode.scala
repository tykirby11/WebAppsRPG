package edu.trinity.webapps


class MapNode(val state: NodeState.Value, private val eventList: List[Int]) extends Node {
  private var currentEvent = 0
  
  def rollEvent: Unit = {
    currentEvent = eventList(scala.util.Random.nextInt(eventList.length))
  }
  
  def returnEvent: Any = {
    return currentEvent
  }
}