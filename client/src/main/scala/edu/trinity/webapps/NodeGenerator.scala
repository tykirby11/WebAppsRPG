package edu.trinity.webapps

class NodeGenerator {
  
  //boss 5%
  //shop 10%
  //enemy 45% 
  //event 40%
   
  def generateNode: Node = {
    val randNum = scala.util.Random.nextInt(100)
    if(randNum < 5) {
      //generate boss
    } else if(randNum < 15) {
      //generate shop
    } else if(randNum < 55) {
      //generate event
    } else if(randNum < 100) {
      //generate enemy
    }
    
    return new ShopNode(NodeState.yellow); //Placeholder, should return generated Node
  }
}

object NodeState extends Enumeration {
  type NodeState = Value
  val red, yellow, purple, black = Value
}