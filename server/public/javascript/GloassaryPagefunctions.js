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