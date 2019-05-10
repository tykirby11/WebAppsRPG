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
  
  private var eventActive = false;
  
  //Player stats defaults 
  private var pHealthDefault = 1000;
  private var pSpeedDefault = 40;
  private var pDamageDefault = 300;
  private var scoreDefault = 0;
  private var goldDefault = 100;

  //Player stats 
  private var pHealth = 1000;
  private var pSpeed = 40;
  private var pDamage = 300;
  private var score = 0;
  private var gold = 100;


  //Enemy stats
  private var enName = ""
  private var enHealth = 30;
  private var enSpeed = 30;
  private var enDamage = 5;
  private var reward = 50
  
  //private var currentLNode = new Node{val state: NodeState.Value, val dbList: List[Any], def rollSelection: Any};
  //private var currentRNode = new Node{};
  
  //Event
  private var itemName = ""
  private var itemHpMod = 0
  private var itemSpdMod = 0
  private var itemAtkMod = 0

  //Enemy sprites
  private val glitch = dom.document.getElementById("glitch")
  private val segFault = dom.document.getElementById("segFault")
  private val stinkyClass = dom.document.getElementById("stinkyClass")
  private val codeTangler = dom.document.getElementById("codeTangler")
  private var sprite = glitch

  private val bw = 125
  private val bh = 50

  private var arenaExists = false;
  private var itemVisible = false;

  private var eventTriggered = false
  
  //Character Select
  private var charSelExists = false;
  private var youChosePopup = false;
  private var selectedID = 0;
  //--------------------------------end of global variables--------------------------------//

  //-------------------------------beginning of main map screen----------------------------//
  def drawMap(nodeL: Node, nodeR: Node): Unit = {
    
    //currentLNode = nodeL;
    //currentRNode = nodeR

    println(nodeL.dbList)
    println(nodeR.dbList)
    
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
          case NodeState.purple => drawItemEvent(nodeL.asInstanceOf[MapNode[ItemEntry]].rollSelection)
          case NodeState.yellow => drawWeaponEvent(nodeL.asInstanceOf[MapNode[WeaponEntry]].rollSelection)
          case NodeState.red => drawBossArena(nodeL.asInstanceOf[MapNode[BossEntry]].rollSelection)
          
        }
      } //if right node is pressed, trigger event 
      else if (((e.clientX - canvas.offsetLeft > 477) && (e.clientX - canvas.offsetLeft < 652)) &&
        ((e.clientY - canvas.offsetTop > 243) && (e.clientY - canvas.offsetTop < 316))) {
        println("clicked right node, this should do something!")
        nodeR.state match {
          case NodeState.black => drawArena(nodeR.asInstanceOf[MapNode[EnemyEntry]].rollSelection)
          case NodeState.purple => drawItemEvent(nodeR.asInstanceOf[MapNode[ItemEntry]].rollSelection)
          case NodeState.yellow => drawWeaponEvent(nodeR.asInstanceOf[MapNode[WeaponEntry]].rollSelection)
          case NodeState.red => drawBossArena(nodeR.asInstanceOf[MapNode[BossEntry]].rollSelection)
        }
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
    //println("Left Node Printed")
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
    //println("Right Node Printed")
    context.rect(475, 240, 175, 75)
    context.stroke()
    context.lineWidth = "3"
    context.fillStyle = "white"
    context.font = "20px Arial"
    context.fillText("Go Right For:", 480, 271)
    nodeR.state match {
      case NodeState.black => context.fillText("Enemy Encounter", 480, 300)
      case NodeState.purple => context.fillText("Item", 480, 300)
      case NodeState.yellow => context.fillText("Weapon", 480, 300)
      case NodeState.red => context.fillText("Boss", 480, 300)
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
  def drawItemEvent(item: ItemEntry): Unit = {
    itemName = item.name
    itemHpMod = item.hpmod
    itemAtkMod = item.atkmod
    itemSpdMod = item.spdmod
    
    eventTriggered = true
    
    handleEventInput();

    clearEventCanvas();

    drawEventElements();

    addItem(item);
    
  }
  
  def drawWeaponEvent(weapon: WeaponEntry): Unit = {
    itemName = weapon.name
    itemHpMod = weapon.hpmod
    itemAtkMod = weapon.atkmod
    itemSpdMod = weapon.spdmod
    
    eventTriggered = true
    
    handleEventInput();

    clearEventCanvas();

    drawEventElements();
    
    addEquip(weapon);

  }
  
  def handleEventInput(): Unit = {
    //handling user input for an event
    dom.document.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)

      println(s"x: ${coords._1}, y: ${coords._2}")

      //if continue area is pressed, trigger event
      if (((e.clientX - canvas.offsetLeft > 248) && (e.clientX - canvas.offsetLeft < 457)) &&
        ((e.clientY - canvas.offsetTop > 408) && (e.clientY - canvas.offsetTop < 439)) && eventActive == true) {
        start()
        eventActive = false
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
    context.fillText("You've Found a " + itemName + " !", 250, 120)
    context.fillText("Stats: (HP + " + itemHpMod + " ATK +  " + itemAtkMod + " SPD +  " + itemSpdMod + ")", 250, 285)
    context.fillText("Click Here to Continue", 250, 430)
    eventActive = true
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
    if(enemy.enemyid == 1){
      sprite = glitch
    }
    else if(enemy.enemyid == 2){
      sprite = stinkyClass
    }
    else if(enemy.enemyid == 3){
      sprite = codeTangler
    }
    
    //Draw arena
    clearCombatCanvas();
    
    //println("Reached Rects")
    
    clearRects();

    handleCombatInput()
    
    

    drawBattleWindow();
    
    drawUserArea();
    
    drawAttackButton();
    
    drawItemButton();

    drawHealthDivider();
    
    drawUIDivider();
    
    drawBossName();
    
    drawPlayerHealth();
    
    clearRects();

    drawEnemyInitial();

    arenaExists = true;
    
    if (enSpeed >= pSpeed) {
      
      println("Enemy Faster, so attack initially")
      
      enemyAttack();
      
    }
    
  }
  
  def drawBossArena(enemy: BossEntry): Unit = {
    //Update enemy vars
    enName = enemy.name
    enHealth = enemy.hp
    enSpeed = enemy.spd
    enDamage = enemy.atk
    reward = enemy.hp
    
    if(enemy.bossid == 1){
      sprite = segFault
    }

    //Draw arena
    clearCombatCanvas();
    
    //println("Reached Rects")
    
    

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
    
    clearRects();

    arenaExists = true;
    
    if (enSpeed >= pSpeed) {
      
      println("Enemy Faster, so attack initially")
      
      enemyAttack();
      
    }
    
  }

  //------------------------------combat mode helper functions----------------------------------//

  def handleCombatInput(): Unit = {

    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
    canvas.onmousedown = (e: dom.MouseEvent) => {
      
      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)

      println(s"x: ${coords._1}, y: ${coords._2}")
      println(arenaExists)

      //if attack is pressed and if button is showing
      if (((e.clientX - canvas.offsetLeft) > 45 && (e.clientX - canvas.offsetLeft) < 170) &&
        ((e.clientY - canvas.offsetTop) > 342 && (e.clientY - canvas.offsetTop) < 392) && arenaExists == true) {
        println("Attack Pressed, hasAttacked = " + hasAttacked + " enHealth = " + enHealth)
        if (enHealth > 0 && hasAttacked != true) {
          playerAttackTurn();
          println("Attack Pressed Check")
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
    context.fillStyle = "black"
    context.rect(20, 20, 760, 390);
    context.stroke();
  }

  def drawUserArea(): Unit = {
    context.font = "25px Veranda";
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
    context.font = "30px Veranda";
    context.fillStyle = "black";
    context.fillText("Attack", 65, 375);
  }

  def drawItemButton(): Unit = {
    //item button
    context.rect(190, 340, bw, bh);
    context.stroke();
    context.font = "30px Veranda";
    context.fillText("Item", 220, 375);
  }

  def drawHealthDivider(): Unit = {
    //health divider
    context.beginPath();
    context.moveTo(20, 85);
    context.lineTo(780, 85);
    context.stroke();
    context.closePath();
  }

  def drawUIDivider(): Unit = {
    //ui divider line
    context.beginPath();
    context.moveTo(20, 325);
    context.lineTo(780, 325);
    context.stroke();
    context.closePath();
    context.beginPath();
    context.moveTo(0, 0);
    context.lineTo(0, 0);
    context.stroke();
  }

  def drawBossName(): Unit = {
    //Boss Name
    context.font = "30px Arial"
    context.fillText(enName, 255, 60);
  }

  def drawPlayerHealth(): Unit = {
    //player health
    context.font = "20px Veranda";
    context.fillText("Player Health: " + pHealth, 40, 50);
  }

  def drawEnemyInitial(): Unit = {
    context.drawImage(sprite, 300, 100, 192, 192);
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
    context.font = "20px Veranda";
    context.fillText("Enemy Health: " + enHealth, 600, 50);
  }

  def drawEnemy(): Unit = {
    context.drawImage(sprite, 300, 100, 192, 192);
    drawEnemyHealth();
  }

  def drawZeroHealth(): Unit = {
    context.clearRect(560, 30, 215, 50);
    context.font = "20px Veranda";
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
      setTimeout(4000)(start())
    }
  }

  def enemyAttack(): Unit = {
    println("Enemy Attack")
    if(enHealth > 0){
    pHealth -= enDamage;
    context.clearRect(40, 25, 200, 50);
    drawPlayerHealth();
    checkPHealth();
    hasAttacked = false;
    }
  }

  def checkPHealth(): Unit = {
    if(pHealth <= 0){
      println("checked Health");
      arenaExists = false;
      //context.clearRect(0, 0, 800, 600);
      setTimeout(4000)(gameOver());
      
      //resetStats();
    }
  }
  
  def gameOver(): Unit = {
    //context.clearRect(0, 0, canvas.width, canvas.height);
    //arenaExists = false;
    context.font = "25px Veranda";
    //context.clearRect(0, 0, 800, 600);
    setTimeout(500)(context.fillText("Game Over. You have failed Lewis", 300, 300));
    setTimeout(5000)(dom.window.location.href = "http://pandora08.cs.trinity.edu:9000/");
  }
  
  def displayDamageDealt(): Unit = {
    context.font = "25px Veranda";
    context.fillText("Damage Dealt: " + pDamage, 376, 360);
    setTimeout(1500)(context.clearRect(330, 337, 436, 30));
  }

  def displayDamageReceived(): Unit = {
    if(enHealth > 0){
    context.font = "25px Veranda";
    context.fillText("Damage Received: " + enDamage, 376, 400);
    setTimeout(1500)(context.clearRect(330, 377, 436, 30));
    }
  }

  def playerAttackTurn(): Unit = {
    println("Player Attack Turn")
    attack();
    displayDamageDealt();
    setTimeout(1500)(enemyAttack());
    setTimeout(2000)(displayDamageReceived());
  }

  def drawInventoryStatusExpand(): Unit = {
    context.font = "25px Veranda";
    context.fillText("Inventory Expanded", 23, 432);
    setTimeout(1500)(context.clearRect(23, 412, 250, 50));
  }

  def drawInventoryStatusCollapse(): Unit = {
    context.font = "25px Veranda";
    context.fillText("Inventory Collapsed", 23, 432);
    setTimeout(1500)(context.clearRect(23, 412, 250, 50));
  }

  def currencySpend(price: Int): Unit = {
    if (gold >= price) {
      gold -= price;
      addInvItem();
      //drawUserScoreAndGold;
      //drawMap(currentLNode, currentRNode);
    } else {
      dom.window.alert("You're broke like a sad bloke, bucko");
    }
  }

  def addEquip(weapon: WeaponEntry): Unit = {
    itemName = weapon.name
    itemHpMod = weapon.hpmod
    itemAtkMod = weapon.atkmod
    itemSpdMod = weapon.spdmod
    
    val equipTable = dom.document.getElementById("equipment").asInstanceOf[HTMLElement];
    val tableRow = dom.document.createElement("tr").asInstanceOf[HTMLElement];
    val tableHeaderEquip = dom.document.createElement("th").asInstanceOf[HTMLElement];
    tableHeaderEquip.innerHTML = itemName;
    println(tableHeaderEquip.innerHTML);
    
    equipTable.appendChild(tableRow);
    tableRow.appendChild(tableHeaderEquip);
    
    pHealth += itemHpMod;
    pSpeed += itemSpdMod;
    pDamage += itemAtkMod;
    
  }
  
    def addItem(item: ItemEntry): Unit = {
    itemName = item.name
    itemHpMod = item.hpmod
    itemAtkMod = item.atkmod
    itemSpdMod = item.spdmod
    
    val equipTable = dom.document.getElementById("consumables").asInstanceOf[HTMLElement];
    val tableRow = dom.document.createElement("tr").asInstanceOf[HTMLElement];
    val tableHeaderEquip = dom.document.createElement("th").asInstanceOf[HTMLElement];
    val tableHeaderBut = dom.document.createElement("th").asInstanceOf[HTMLElement];
    
     println(tableHeaderEquip.innerHTML);

    
    tableHeaderEquip.innerHTML = itemName;
    tableHeaderBut.innerHTML = "<button>Use</button>";
    tableHeaderBut.onclick = { (e: Event) => tableHeaderBut.parentNode.parentNode.removeChild(tableRow); }
    
    
    equipTable.appendChild(tableRow);
    tableRow.appendChild(tableHeaderEquip);
    tableRow.appendChild(tableHeaderBut);
    
    pHealth += itemHpMod;
    pDamage += itemAtkMod;
    pSpeed += itemSpdMod;
  }
 
    
  def addInvItem(): Unit = {
    var testid = 0;
    //var valueOfItem = dom.document.getElementById("ty2").asInstanceOf[HTMLElement];
    var result = ""//valueOfItem.textContent;//"Debugging Spray"
    val conTable = dom.document.getElementById("consumables").asInstanceOf[HTMLElement];
    val tableHeaderItem = dom.document.createElement("th").asInstanceOf[HTMLElement];
    tableHeaderItem.innerHTML = "<p>" + result + "</p>";
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

  dom.document.getElementById("purchaseSpray").addEventListener("click", (event: Event) => {
    currencySpend(50);
    println(dom.document.createElement("ty1").asInstanceOf[HTMLElement].textContent)
    
    })
  dom.document.getElementById("purchaseClock").addEventListener("click", (event: Event) => {
    currencySpend(50);
    //testid = 2;
  })
  dom.document.getElementById("purchaseRAM").addEventListener("click", (event: Event) => {
    currencySpend(50);
  })
  dom.document.getElementById("purchaseBow").addEventListener("click", (event: Event) => {
    currencySpend(50);
  })
  dom.document.getElementById("purchaseBoots").addEventListener("click", (event: Event) => {
    currencySpend(50);
  })
  dom.document.getElementById("purchaseRing").addEventListener("click", (event: Event) => {
    currencySpend(50);
  })
  dom.document.getElementById("purchaseWand").addEventListener("click", (event: Event) => {
    currencySpend(50);
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
  
  def clearCombatCanvas(): Unit = {
    //clear out map area for event
    context.clearRect(0, 0, canvas.width, canvas.height)
    context.stroke()
    context.lineWidth = "3"
    context.fillStyle = "white"
  }
  
  def clearRects(): Unit = {
    context.clearRect(165, 230, 190, 90)
    context.clearRect(465, 230, 190, 90)
    //context.lineWidth = "3"
    context.fillStyle = "black"
    println("Rect should be gone")
  }

  def drawBorder(xPos: Int, yPos: Int, width: Int, height: Int, thickness: Int = 1): Unit = {
    context.fillStyle = "#000000";
    context.fillRect(xPos - (thickness), yPos - (thickness), width + (thickness * 2), height + (thickness * 2));
  }
  //----------------------------------------end of combat mode helper functions--------------------------------------------//

  //----------------------------------------Character select---------------------------------------------------------------//

  
    def drawCharacterSelect(): Unit = {
       
        charSelect();
        tankSelect();
        warriorSelect();
        rougeSelect();

        charSelExists = true;
    }
    
    def charSelect() : Unit ={
      context.clearRect(0, 900, canvas.width, canvas.height);
       //battle window
        context.rect(20,20,760,460);
        context.stroke();
      //col 1          
        context.beginPath();
        context.moveTo(260, 20);
        context.lineTo(260, 480);
        context.stroke();
      //col 2  
        context.beginPath();
        context.moveTo(520, 20);
        context.lineTo(520, 480);
        context.stroke();
      // bottom select line
        context.beginPath();
        context.moveTo(20, 420);
        context.lineTo(780, 420);
        context.stroke();
        context.beginPath();
        context.moveTo(0, 0);
        context.lineTo(0, 0);
        context.stroke();
        //select col 1
        context.font = "30px Arial";
        context.fillText("Select", 100, 460);
        //select col 2
        context.font = "30px Arial";
        context.fillText("Select", 350, 460);
        //select col 3
        context.font = "30px Arial";
        context.fillText("Select", 600, 460);
    }
    
    def tankSelect() : Unit = {
        context.stroke();
        context.font = "50px Arial";
        context.fillText("Tank", 87, 65);
        context.font = "30px Arial";
        context.fillText("Health: 300", 65, 215);
        context.fillText("Attack: 200", 65, 265);
        context.fillText("Speed: 100", 65, 315);

    }
    
    def warriorSelect() : Unit = {
        context.stroke();
        context.font = "50px Arial";
        context.fillText("Warrior", 315, 65);
        context.font = "30px Arial";
        context.fillText("Health: 100", 315, 215);
        context.fillText("Attack: 300", 315, 265);
        context.fillText("Speed: 200", 315, 315);

    }
    def rougeSelect() : Unit = {
        context.stroke();
        context.font = "50px Arial";
        context.fillText("Rouge", 580, 65);
        context.font = "30px Arial";
        context.fillText("Health: 100", 580, 215);
        context.fillText("Attack: 200", 580, 265);
        context.fillText("Speed: 300", 580, 315);

    }
    
    def select() : Unit = {
      if(youChosePopup == true){
        //context.rect(100,100,560,260);
        context.fillStyle = "rgb(169,169,169)";
        context.fillRect(100,100,560,260);
        context.fillStyle = "rgb(0,0,0)";
        context.font = "50px Arial";
        context.fillText("You chose:",245,150);
        //cancel
        if(selectedID == 1){
        context.font = "70px Arial";
        context.fillText("Tank!",280,275);
          pHealth = 300;
          pSpeed = 100;
          pDamage = 200;
        
        }
        if(selectedID == 2){
        context.font = "70px Arial";
        context.fillText("Warrior!",280,275);
        pHealth = 100;
        pSpeed = 200;
        pDamage = 300;
        }
        if(selectedID == 3){
        context.font = "70px Arial";
        context.fillText("Rouge!",280,275);
        pHealth = 100;
        pSpeed = 300;
        pDamage = 50;
        
        }
        setTimeout(4000)(context.clearRect(0,0,810,800));
        setTimeout(4000)(start());
      }
    }
    
    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
    canvas.onmousedown = (e: dom.MouseEvent) => {
      val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)
      
      println(s"x: ${coords._1}, y: ${coords._2}")
      
      //Select in col 1
      if(((e.clientX - canvas.offsetLeft) > 20 && (e.clientX - canvas.offsetLeft) < 260) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
        println("col 1 clicked")
        selectedID = 1;
        youChosePopup = true;
        charSelExists = false;
        select();
    }
      //Select in col 2
      if(((e.clientX - canvas.offsetLeft) > 260 && (e.clientX - canvas.offsetLeft) < 522) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
        println("col 2 clicked")
        selectedID = 2;
        youChosePopup = true;
        charSelExists = false;
        select();
    }
      //Select in col 3
      if(((e.clientX - canvas.offsetLeft) > 522 && (e.clientX - canvas.offsetLeft) < 782) && ((e.clientY - canvas.offsetTop) > 422 && (e.clientY - canvas.offsetTop) < 482) && charSelExists == true){
        println("col 3 clicked")
        selectedID = 3;
        youChosePopup = true;
        charSelExists = false;
        select();
    }     
     
    }
}
