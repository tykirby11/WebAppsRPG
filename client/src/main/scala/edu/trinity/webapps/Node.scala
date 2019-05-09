package edu.trinity.webapps

trait Node {
  val state: NodeState.Value
  val dbList: List[Any]
  def rollSelection: Any
}