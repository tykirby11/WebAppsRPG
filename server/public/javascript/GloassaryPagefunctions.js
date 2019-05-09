function switchTabs(evt, tabs) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabs).style.display = "block";
    evt.currentTarget.className += " active";
  }
  ////////////////////////////////////////////
  //sets new page be the default open screen//
  ////////////////////////////////////////////
  document.getElementById("defaultOpen").click();
  
  
	
  document.getElementById("tank").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Tank"
  document.getElementById("attack").innerHTML = "Attack: 300"	  
  document.getElementById("hp").innerHTML = "Health: 200"
  document.getElementById("speed").innerHTML= "Speed: 100"
  })


  document.getElementById("warrior").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Warrior"
  document.getElementById("attack").innerHTML = "Attack: 100"	  
  document.getElementById("hp").innerHTML = "Health: 300"
  document.getElementById("speed").innerHTML= "Speed: 200"
  })
  
  document.getElementById("rouge").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Rouge"
  document.getElementById("attack").innerHTML = "Attack: 100"	  
  document.getElementById("hp").innerHTML = "Health: 200"
  document.getElementById("speed").innerHTML= "Speed: 300"
  })
  
  document.getElementById("glitchGremlin").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Glitch Gremlin"
  document.getElementById("attack").innerHTML = "Attack: 100"	  
  document.getElementById("hp").innerHTML = "Health: 50"
  document.getElementById("speed").innerHTML= "Speed: 100"
  })
  
  document.getElementById("theGreatSegfault").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "The Great Segfault"
  document.getElementById("attack").innerHTML = "Attack: 200"	  
  document.getElementById("hp").innerHTML = "Health: 700"
  document.getElementById("speed").innerHTML= "Speed: 100"
  })
  
  
  document.getElementById("smellyClass").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Smelly Class"
  document.getElementById("attack").innerHTML = "Attack: 50"	  
  document.getElementById("hp").innerHTML = "Health: 50"
  document.getElementById("speed").innerHTML= "Speed: 150"
  })
  
  
  document.getElementById("codeTangler").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Code angler"
  document.getElementById("attack").innerHTML = "Attack: 50"	  
  document.getElementById("hp").innerHTML = "Health: 150"
  document.getElementById("speed").innerHTML= "Speed: 50"
  })
  
  document.getElementById("wandOfCopyPaste").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Wand Of Copy-Paste"
  document.getElementById("attack").innerHTML = "Attack: 200"	  
  document.getElementById("hp").innerHTML = "Health: N/A"
  document.getElementById("speed").innerHTML= "Speed: N/A"
  })
  
  
  document.getElementById("ringOfConcurrency").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Ring Of Concurrency"
  document.getElementById("attack").innerHTML = "Attack: 100"	  
  document.getElementById("hp").innerHTML = "Health: 100"
  document.getElementById("speed").innerHTML= "Speed: 100"
  })
  
  
  document.getElementById("multithreadedBoots").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Multithreaded Boots"
  document.getElementById("attack").innerHTML = "Attack: N/A"	  
  document.getElementById("hp").innerHTML = "Health: N/A"
  document.getElementById("speed").innerHTML= "Speed: 300"
  })
  
  document.getElementById("embeddingCrossbow").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Embedding Crossbow"
  document.getElementById("attack").innerHTML = "Attack: 200"	  
  document.getElementById("hp").innerHTML = "Health: N/A"
  document.getElementById("speed").innerHTML= "Speed: N/A"
  document.getElementById("uses").hidden = true;
  })
  
  
  document.getElementById("debuggingSpray").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Debugging Spray"
  document.getElementById("attack").innerHTML = "Attack: 300"	  
  document.getElementById("hp").innerHTML = "Health: N/A"
  document.getElementById("speed").innerHTML= "Speed: N/A"

  })
  
  
  document.getElementById("overclocker").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Overclocker"
  document.getElementById("attack").innerHTML = "Attack: N/A"	  
  document.getElementById("hp").innerHTML = "Health: N/A"
  document.getElementById("speed").innerHTML= "Speed: 300"
  
  })
  
  document.getElementById("stickOfRam").addEventListener("click", function(){
  document.getElementById("characterName").innerHTML = "Stick Of Ram"
  document.getElementById("attack").innerHTML = "Attack: N/A"	  
  document.getElementById("hp").innerHTML = "Health: 300"
  document.getElementById("speed").innerHTML= "Speed: N/A"

  })
  
  
  