package edu.trinity.webapps

trait Node[A] {
  val state: NodeState.Value
  val dbList: List[A]
}