//package edu.trinity.webapps
//
//import org.scalajs.dom
//import org.querki.jquery._
//
//object GlossaryFunctions {
//  
//  
//  
//  def switchTabs(evt:Event, tabs: String) {
//    var i = 0;
//    var tabcontent = "";
//    var tablinks = "";
//    tabcontent = dom.document.getElementsByClassName("tabcontent").toString();
//    for ( i <- i until tabcontent.length) {
//      tabcontent(i).style.display = "none";
//    }
//    tablinks = dom.document.getElementsByClassName("tablinks").toString();
//    for (i <- i until tablinks.length) {
//      tablinks(i).className = tablinks[i].className.replace(" active", "");
//    }
//    document.getElementById(tabs).style.display = "block";
//    evt.currentTarget.className += " active";
//  }
//  ////////////////////////////////////////////
//  //sets new page be the default open screen//
//  ////////////////////////////////////////////
//  
//  //dom.document.getElementById("defaultOpen").click();
//  
//  $("#defaultOpen").click()
//  
//  
//	
//  //dom.document.getElementById("tank").addEventListener("click", {
//   $("#tank").click(() => tank())
//   
//  def tank(){   
//  dom.document.getElementById("characterName").innerHTML = "Tank"
//  dom.document.getElementById("attack").innerHTML = "300"	  
//  dom.document.getElementById("hp").innerHTML = "200"
//  dom.document.getElementById("speed").innerHTML = "100"
//  }
//
//
//}