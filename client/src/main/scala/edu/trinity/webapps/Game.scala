package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._
import org.scalajs.dom.raw._
import org.scalajs.dom.raw.HTMLImageElement
import scala.scalajs.js.timers._

import edu.trinity.webapps.shared.DBShared._

object CanvasDrawing {

  //----------------------------------Global Variables-------------------------------------//
  private var x = 100
  private var y = 100
  private val canvas = dom.document.getElementById("gameCanvas").asInstanceOf[dom.raw.HTMLCanvasElement]
  private val context = canvas.getContext("2d")

  //for images
  private val slice1 = dom.document.getElementById("slice1")
  private val slice2 = dom.document.getElementById("slice2")
  private val slice3 = dom.document.getElementById("slice3")
  private val slice4 = dom.document.getElementById("slice4")
  private val slice5 = dom.document.getElementById("slice5")
  private val slice6 = dom.document.getElementById("slice6")

  //boolean to see if player has attacked
  private var hasAttacked = false;

  //Player stats
  private var pHealth = 40;
  private var pSpeed = 40;
  private var pDamage = 10;
  private var score = 0;
  private var gold = 100;

  //Enemy stats
  private var enName = ""
  private var enHealth = 30;
  private var enSpeed = 30;
  private var enDamage = 5;
  private var reward = 50

  //Enemy sprites
  private val glitch = dom.document.getElementById("glitch")

  private val bw = 125
  private val bh = 50

  private var arenaExists = false;
  private var itemVisible = false;

  private var eventTriggered = false
  //--------------------------------end of global variables--------------------------------//

  //-------------------------------beginning of main map screen----------------------------//
  def drawMap(nodeL: Node, nodeR: Node): Unit = {

    score += 1

    handleMapInput(nodeL, nodeR);

    clearMapCanvas();

    drawMapBackground();

    drawLeftMapNode(nodeL);

    drawRightMapNode(nodeR);

    drawUserScoreAndGold();
  }

  def start(): Unit = {
    DBClient.generateNodes()
  }

  def handleMapInput(nodeL: Node, nodeR: Node): Unit = {
    //handling user input for map
    canvas.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)
      println(s"x: ${coords._1}, y: ${coords._2}")

      //if left node is pressed, trigger event
      if (((e.clientX - canvas.offsetLeft > 177) && (e.clientX - canvas.offsetLeft < 352)) &&
        ((e.clientY - canvas.offsetTop > 243) && (e.clientY - canvas.offsetTop < 316))) {
        println("clicked left node, this should do something!")
        nodeL.state match {
          case NodeState.black => drawArena(nodeL.asInstanceOf[MapNode[EnemyEntry]].rollSelection)
          case NodeState.purple => drawEvent()
          case NodeState.yellow => println("you get a weapon!")
          case NodeState.red => println("you encounter a boss!")
        }
      } //if right node is pressed, trigger event 
      else if (((e.clientX - canvas.offsetLeft > 477) && (e.clientX - canvas.offsetLeft < 652)) &&
        ((e.clientY - canvas.offsetTop > 243) && (e.clientY - canvas.offsetTop < 316))) {
        println("clicked right node, this should do something!")
        nodeR.state match {
          case NodeState.black => drawArena(nodeR.asInstanceOf[MapNode[EnemyEntry]].rollSelection)
          case NodeState.purple => drawEvent()
          case NodeState.yellow => println("you get a weapon!")
          case NodeState.red => println("you encounter a boss!")
        }
        CanvasDrawing.drawEvent()
        eventTriggered = true
      }
    }
  }

  def clearMapCanvas(): Unit = {
    //clear canvas before drawing out map
    context.clearRect(0, 0, canvas.width, canvas.height)
  }

  def drawMapBackground(): Unit = {
    //background image for map view
    val mapBackground = dom.document.getElementById("mapBackground")
    context.drawImage(mapBackground, 0, 0, canvas.width, 600)
  }

  def drawLeftMapNode(nodeL: Node): Unit = {
    //left node
    context.rect(175, 240, 175, 75)
    context.stroke()
    context.lineWidth = "3"
    context.fillStyle = "white"
    context.font = "20px Arial"
    context.fillText("Go Left For:", 180, 271)
    nodeL.state match {
      case NodeState.black => context.fillText("Enemy Encounter", 180, 300)
      case NodeState.purple => context.fillText("Item", 180, 300)
      case NodeState.yellow => context.fillText("Weapon", 180, 300)
      case NodeState.red => context.fillText("Boss", 180, 300)
    }
  }

  def drawRightMapNode(nodeR: Node): Unit = {
    //right node
    context.rect(475, 240, 175, 75)
    context.stroke()
    context.fillStyle = "white"
    context.font = "20px Arial"
    context.fillText("Go Right For:", 480, 271)
    nodeR.state match {
      case NodeState.black => context.fillText("Enemy Encounter", 180, 300)
      case NodeState.purple => context.fillText("Item", 180, 300)
      case NodeState.yellow => context.fillText("Weapon", 180, 300)
      case NodeState.red => context.fillText("Boss", 180, 300)
    }
  }

  def drawUserScoreAndGold(): Unit = {
    //user score
    context.fillText("Nodes Visited: " + score, 620, 30)

    //gold
    context.fillText("Gold: " + gold, 30, 30)
  }

  //--------------------------------end of main map screen---------------------------------//

  //--------------------------------Random event screen------------------------------------//
  def drawEvent(): Unit = {

    handleEventInput();

    clearEventCanvas();

    drawEventElements();

  }
  def handleEventInput(): Unit = {
    //handling user input for an event
    dom.document.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)

      println(s"x: ${coords._1}, y: ${coords._2}")

      //if continue area is pressed, trigger event
      if (((e.clientX - canvas.offsetLeft > 248) && (e.clientX - canvas.offsetLeft < 457)) &&
        ((e.clientY - canvas.offsetTop > 408) && (e.clientY - canvas.offsetTop < 439))) {
        start()
      }
    }
  }

  def clearEventCanvas(): Unit = {
    //clear out map area for event
    context.clearRect(0, 0, canvas.width, canvas.height)
  }

  def drawEventElements(): Unit = {
    val eventBackground = dom.document.getElementById("eventBackground")
    context.drawImage(eventBackground, 0, 0, canvas.width, canvas.height)
    context.fillText("You've Found a Lost Treasure Chest", 250, 120)
    context.fillText("Gold +50!", 250, 285)
    context.fillText("Click Here to Continue", 250, 430)
  }
  //--------------------------------End of event screen------------------------------------//

  //combat mode
  def drawArena(enemy: EnemyEntry): Unit = {
    //Update enemy vars
    enName = enemy.name
    enHealth = enemy.hp
    enSpeed = enemy.spd
    enDamage = enemy.atk
    reward = enemy.hp

    //Draw arena
    context.clearRect(0, 0, canvas.width, canvas.height);

    handleCombatInput()

    drawBattleWindow();

    drawUserArea();

    drawAttackButton();

    drawItemButton();

    drawHealthDivider();

    drawUIDivider();

    drawBossName();

    drawPlayerHealth();

    drawEnemyInitial();

    arenaExists = true;
  }

  //------------------------------combat mode helper functions----------------------------------//

  def handleCombatInput(): Unit = {

    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
    canvas.onmousedown = (e: dom.MouseEvent) => {

      //if attack is pressed and if button is showing
      if (((e.clientX - canvas.offsetLeft) > 45 && (e.clientX - canvas.offsetLeft) < 170) &&
        ((e.clientY - canvas.offsetTop) > 342 && (e.clientY - canvas.offsetTop) < 392) && arenaExists == true) {
        if (enHealth > 0 && hasAttacked != true) {
          playerAttackTurn();
        }
        if (enHealth > 0) {
          setTimeout(550)(drawEnemy());
        } else {
          drawZeroHealth();
        }
      }

      //if item button is pressed
      if (((e.clientX - canvas.offsetLeft) > 193 && (e.clientX - canvas.offsetLeft) < 316) && ((e.clientY - canvas.offsetTop) > 342 && (e.clientY - canvas.offsetTop) < 392) && arenaExists == true) {
        if (itemVisible == true) {
          dom.document.querySelector("#inventory-wrapper").asInstanceOf[HTMLElement].style.display = "initial";
          itemVisible = false;
          drawInventoryStatusExpand();
        } else {
          dom.document.querySelector("#inventory-wrapper").asInstanceOf[HTMLElement].style.display = "none";
          itemVisible = true;
          drawInventoryStatusCollapse();
        }
      }
    }
  }

  def drawBattleWindow(): Unit = {
    //battle window
    context.rect(20, 20, 760, 390);
    context.stroke();
  }

  def drawUserArea(): Unit = {
    context.font = "25px Arial";
    context.fillText("Inventory Collapsed", 23, 432);
    setTimeout(1500)(context.clearRect(23, 412, 250, 50));
    //user area
    context.rect(20, 325, 760, 85);
    context.stroke();
  }

  def drawAttackButton(): Unit = {
    //attack button
    context.rect(45, 340, bw, bh);
    context.stroke();
    context.font = "30px Arial";
    context.fillText("Attack", 65, 375);
  }

  def drawItemButton(): Unit = {
    //item button
    context.rect(190, 340, bw, bh);
    context.stroke();
    context.font = "30px Arial";
    context.fillText("Item", 220, 375);
  }

  def drawHealthDivider(): Unit = {
    //health divider
    context.beginPath();
    context.moveTo(20, 85);
    context.lineTo(780, 85);
    context.stroke();
  }

  def drawUIDivider(): Unit = {
    //ui divider line
    context.beginPath();
    context.moveTo(20, 325);
    context.lineTo(780, 325);
    context.stroke();
  }

  def drawBossName(): Unit = {
    //Boss Name
    context.font = "30px Arial"
    context.fillText(enName, 255, 60);
  }

  def drawPlayerHealth(): Unit = {
    //player health
    context.font = "20px Arial";
    context.fillText("Player Health: " + pHealth, 40, 50);
  }

  def drawEnemyInitial(): Unit = {
    context.drawImage(glitch, 300, 100, 192, 192);
    compareSpeed();
    drawEnemyHealth();
  }

  def compareSpeed(): Unit = {
    if (enSpeed < pSpeed) {
      hasAttacked = false;
    } else {
      hasAttacked = true;
    }
  }

  def drawEnemyHealth(): Unit = {
    context.clearRect(560, 30, 215, 50);
    context.font = "20px Arial";
    context.fillText("Enemy Health: " + enHealth, 600, 50);
  }

  def drawEnemy(): Unit = {
    context.drawImage(glitch, 300, 100, 192, 192);
    drawEnemyHealth();
  }

  def drawZeroHealth(): Unit = {
    context.clearRect(560, 30, 215, 50);
    context.font = "20px Arial";
    context.fillText("Enemy Health: " + enHealth, 600, 50);
  }

  def hurtEnemy(): Unit = {
    //test player damage
    enHealth -= pDamage;
    enemyDefeat();
    hasAttacked = true;
  }

  def enemyDefeat(): Unit = {
    if (enHealth <= 0) {
      enHealth = 0;
      context.clearRect(300, 100, 192, 192);
      gold += reward;
    }
  }

  def enemyAttack(): Unit = {
    pHealth -= enDamage;
    context.clearRect(40, 25, 200, 50);
    drawPlayerHealth();
    hasAttacked = false;
  }

  def displayDamageDealt(): Unit = {
    context.font = "25px Arial";
    context.fillText("Damage Dealt: " + pDamage, 376, 360);
    setTimeout(1500)(context.clearRect(330, 337, 436, 30));
  }

  def displayDamageReceived(): Unit = {
    context.font = "25px Arial";
    context.fillText("Damage Received: " + enDamage, 376, 400);
    setTimeout(1500)(context.clearRect(330, 377, 436, 30));
  }

  def playerAttackTurn(): Unit = {
    attack();
    displayDamageDealt();
    setTimeout(1500)(enemyAttack());
    setTimeout(2000)(displayDamageReceived());
  }

  def drawInventoryStatusExpand(): Unit = {
    context.font = "25px Arial";
    context.fillText("Inventory Expanded", 23, 432);
    setTimeout(1500)(context.clearRect(23, 412, 250, 50));
  }

  def drawInventoryStatusCollapse(): Unit = {
    context.font = "25px Arial";
    context.fillText("Inventory Collapsed", 23, 432);
    setTimeout(1500)(context.clearRect(23, 412, 250, 50));
  }

  def currencySpend(): Unit = {
    if (gold >= 50) {
      gold -= 50;
      addInvItem();
    } else {
      dom.window.alert("You're broke like a sad bloke, bucko");
    }
  }

  def addInvItem(): Unit = {
    val conTable = dom.document.getElementById("consumables").asInstanceOf[HTMLElement];
    val tableHeaderItem = dom.document.createElement("th").asInstanceOf[HTMLElement];
    tableHeaderItem.innerHTML = "<p>Debugging Spray</p>";
    val tableHeaderBut = dom.document.createElement("th").asInstanceOf[HTMLElement];
    tableHeaderBut.innerHTML = "<button>Use</button>";
    tableHeaderBut.id = "useItem";
    val tableRow = dom.document.createElement("tr").asInstanceOf[HTMLElement];
    tableHeaderBut.onclick = { (e: Event) => tableHeaderBut.parentNode.parentNode.removeChild(tableRow); }

    conTable.appendChild(tableRow);
    tableRow.appendChild(tableHeaderItem);
    tableRow.appendChild(tableHeaderBut);
  }

  def deleteInvItem() = {
    val conTable = dom.document.getElementById("consumables").asInstanceOf[HTMLElement];
    dom.window.alert("poop");
  }

  dom.document.getElementById("sprayPurchase").addEventListener("click", (event: Event) => {
    currencySpend();
  })

  def attack(): Unit = {
    setTimeout(250)(context.drawImage(slice1, 400, 150, 26, 110));
    setTimeout(250)(clearAnimation);
    setTimeout(250)(context.drawImage(slice2, 400, 150, 26, 110));
    setTimeout(300)(clearAnimation);
    setTimeout(300)(context.drawImage(slice3, 400, 150, 26, 110));
    setTimeout(350)(clearAnimation);
    setTimeout(350)(context.drawImage(slice4, 400, 150, 26, 110));
    setTimeout(400)(clearAnimation);
    setTimeout(400)(context.drawImage(slice5, 400, 150, 26, 110));
    setTimeout(450)(clearAnimation);
    setTimeout(450)(context.drawImage(slice6, 400, 150, 26, 110));
    setTimeout(500)(clearAnimation);

    hurtEnemy();
  }

  def clearAnimation(): Unit = {
    context.clearRect(400, 150, 26, 110);
  }

  def drawBorder(xPos: Int, yPos: Int, width: Int, height: Int, thickness: Int = 1): Unit = {
    context.fillStyle = "#000000";
    context.fillRect(xPos - (thickness), yPos - (thickness), width + (thickness * 2), height + (thickness * 2));
  }
  //----------------------------------------end of combat mode helper functions--------------------------------------------//

}

