package edu.trinity.webapps

object NodeGenerator {

  //boss 5%
  //weapon 10%
  //event 30%
  //enemy 55%

  def getNodeType: NodeState.Value = {
    var newNode = NodeState.black
    val randNum = scala.util.Random.nextInt(100)
    
    if (randNum < 5) {
      //boss
      newNode = NodeState.red
    } else if (randNum < 15) {
      //weapon
      newNode = NodeState.yellow
    } else if (randNum < 45) {
      //item
      newNode = NodeState.purple
    }
    newNode
  }
}

object NodeState extends Enumeration {
  type NodeState = Value
  val red, yellow, purple, black = Value
}

//Call generate nodes in DBClient
//DB client call NodeGenerator to get 2 node types
//DB client creates nodes with relevant lists
//DB client calls DrawMap with nodes as parameters