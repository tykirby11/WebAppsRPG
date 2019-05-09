package edu.trinity.webapps

class MapNode[A](val state: NodeState.Value, val eventList: List[A]) extends Node[A] {
  def rollSelection: A = {
    var currentEvent = eventList.apply(scala.util.Random.nextInt(eventList.length))
    currentEvent
  }
}