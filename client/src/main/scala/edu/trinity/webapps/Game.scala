package edu.trinity.webapps

import org.scalajs.dom
import org.querki.jquery._
import org.scalajs.dom.raw._
import org.scalajs.dom.raw.HTMLImageElement
import scala.scalajs.js.timers._

object CanvasDrawing {
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
    
    
    private var numOfBut = 0;
    private var gold = 100;
    
    DBClient.getEnemyList()

    println("ffffffffffffffffffffffffffffff")
    
    //val gremlin = enemies.filter(_.enemyid == 1).apply(0)
    
    //test battle system
    
    //boolean to see if player has attacked
    private var hasAttacked = false;
    
    //test health

    private var enHealth = 30;
    private var pHealth = 40;
    
    //test speed
    private var enSpeed = 30;
    private var pSpeed = 40;
    
    //test damage
    private var pDamage = 10;
    private var enDamage = 5;
    
    private val glitch = dom.document.getElementById("glitch")

    private val bw = 125
    private val bh = 50

    private var arenaExists = false;
    private var itemVisible = false;
    
    def drawArena(): Unit = {
  
      drawBattleWindow();
      
      drawUserArea();
      
      drawAttackButton();
      
      drawItemButton();

      drawHealthDivider();
       
      drawUIDivider();
        
      drawBossName();
        
      drawPlayerHealth();
        
      drawEnemy();
        
      arenaExists = true;
        
    }
    
    def drawBattleWindow(): Unit = {
        //battle window
        context.rect(20,20,760,390);
        context.stroke();
    }
    
    def drawUserArea(): Unit = {         context.font = "25px Arial";
          context.fillText("Inventory Collapsed", 23, 432);
          setTimeout(1500)(context.clearRect(23,412, 250,50));
        //user area
        context.rect(20,325, 760,85);
        context.stroke();
    }
    
    def drawAttackButton(): Unit = {
      //attack button
        context.rect(45,340,bw,bh);
        context.stroke();
        context.font = "30px Arial";
        context.fillText("Attack",65,375);
    }
    
    def drawItemButton(): Unit = {
        //item button
        context.rect(190,340,bw,bh);
        context.stroke();
        context.font = "30px Arial";
        context.fillText("Item", 220, 375);
    }
    
    def drawHealthDivider(): Unit = {
        //health divider
        context.beginPath();
        context.moveTo(20,85);
        context.lineTo(780,85);
        context.stroke();
    }
    
    def drawUIDivider(): Unit = {
        //ui divider line  
        context.beginPath();
        context.moveTo(20,325);
        context.lineTo(780,325);
        context.stroke();
    }
    
    def drawBossName(): Unit = {
        //Boss Name
        context.font = "30px Arial"
        context.fillText("GLITCH GREMLIN", 255, 60);
    }
    
    def drawPlayerHealth(): Unit = {
       //player health
        context.font = "20px Arial";
        context.fillText("Player Health: " + pHealth, 40, 50);
        
    }
    
    def drawEnemyInitial() : Unit = {
      context.drawImage(glitch, 300,100,192,192);
      
      compareSpeed();
      
      drawEnemyHealth();
    }
    
    def compareSpeed(): Unit = {
      if(enSpeed < pSpeed){
        hasAttacked = false;
      } else {
        hasAttacked = true;
      }
    }
    
    def drawEnemyHealth(): Unit = {
      context.clearRect(560,30,215,50); 
      context.font = "20px Arial";
      context.fillText("Enemy Health: " + enHealth, 600, 50);
    }
    
    def drawEnemy() : Unit = {
      context.drawImage(glitch, 300,100,192,192);
      
      drawEnemyHealth();
    }
    
    def drawZeroHealth() : Unit = {
      context.clearRect(560,30,215,50); 
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
      if (enHealth <= 0){
        enHealth = 0;
        context.clearRect(300,100,192,192);
        gold += 50;
        
        //context.font = "20px Arial";
      }
    }
    
    def enemyAttack() : Unit = {
      pHealth -= enDamage;
      
      context.clearRect(40,25,200,50);
      drawPlayerHealth();
      hasAttacked = false;
    }
    
    
    def displayDamageDealt() : Unit = {
      context.font = "25px Arial";
      context.fillText("Damage Dealt: " + pDamage, 376, 360);
      setTimeout(1500)(context.clearRect(330, 337, 436, 30));
    }
    
    def displayDamageReceived() : Unit = {
      context.font = "25px Arial";
      context.fillText("Damage Received: " + enDamage, 376, 400);
      setTimeout(1500)(context.clearRect(330, 377, 436, 30));
    }
    
    //on mouse click, prints coordinates of mouse and sees if buttons are pressed
    canvas.onmousedown = (e: dom.MouseEvent) => {
      //val coords = (e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop)
      //println(s"x: ${coords._1}, y: ${coords._2}")
      
      //if attack is pressed and if button is showing
      if(((e.clientX - canvas.offsetLeft) > 45 && (e.clientX - canvas.offsetLeft) < 170) && ((e.clientY - canvas.offsetTop) > 342 && (e.clientY - canvas.offsetTop) < 392) && arenaExists == true){             
        if(enHealth > 0 && hasAttacked != true){
            playerAttackTurn();
        }
        if(enHealth > 0){
          setTimeout(550)(drawEnemy());
        } else {
          drawZeroHealth();
        }
      }
    
    def playerAttackTurn(): Unit = {
      attack();
      displayDamageDealt();
      setTimeout(1500)(enemyAttack());
      setTimeout(2000)(displayDamageReceived());
    }
      
      //if item button is pressed
      if(((e.clientX - canvas.offsetLeft) > 193 && (e.clientX - canvas.offsetLeft) < 316) && ((e.clientY - canvas.offsetTop) > 342 && (e.clientY - canvas.offsetTop) < 392) && arenaExists == true){
        if(itemVisible == true){
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
    
    def drawInventoryStatusExpand(): Unit = {
       context.font = "25px Arial";
       context.fillText("Inventory Expanded", 23, 432);
       setTimeout(1500)(context.clearRect(23,412, 250,50));
    }
    
    def drawInventoryStatusCollapse(): Unit = {
        context.font = "25px Arial";
        context.fillText("Inventory Collapsed", 23, 432);
        setTimeout(1500)(context.clearRect(23,412, 250,50));
    }
    
    def currencySpend(): Unit = {
      if(gold >= 50){
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
      tableHeaderBut.onclick = { (e:Event) => tableHeaderBut.parentNode.parentNode.removeChild(tableRow);}
      
      conTable.appendChild(tableRow);
      tableRow.appendChild(tableHeaderItem);
      tableRow.appendChild(tableHeaderBut);
    }
    
    def deleteInvItem()= {
      val conTable = dom.document.getElementById("consumables").asInstanceOf[HTMLElement];
      dom.window.alert("poop");
    }
    
    dom.document.getElementById("sprayPurchase").addEventListener("click", (event: Event) => {
      currencySpend();
    })
     
   
   def attack(): Unit = {
     setTimeout(250)(context.drawImage(slice1,400,150,26,110));
     setTimeout(250)(clearAnimation);
     setTimeout(250)(context.drawImage(slice2,400,150,26,110));
     setTimeout(300)(clearAnimation);
     setTimeout(300)(context.drawImage(slice3,400,150,26,110));
     setTimeout(350)(clearAnimation);
     setTimeout(350)(context.drawImage(slice4,400,150,26,110));
     setTimeout(400)(clearAnimation);
     setTimeout(400)(context.drawImage(slice5,400,150,26,110));
     setTimeout(450)(clearAnimation);
     setTimeout(450)(context.drawImage(slice6,400,150,26,110));
     setTimeout(500)(clearAnimation);
     
     hurtEnemy();
   }
   
   def clearAnimation(): Unit = {
     context.clearRect(400,150,26,110);
   }
   
   def drawBorder(xPos: Int, yPos: Int,width: Int,height: Int,thickness: Int = 1) : Unit = {
    context.fillStyle= "#000000";
    context.fillRect(xPos - (thickness), yPos - (thickness), width + (thickness * 2), height + (thickness * 2));
  }
   
}

