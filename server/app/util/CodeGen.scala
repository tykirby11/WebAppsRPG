package util

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.run(
    "slick.jdbc.MySQLProfile", "com.mysql.cj.jdbc.Driver", 
    "jdbc:mysql://localhost/rpg?user=rpg&password=MunchkinKill&nullNamePatternMatchesAll=true&serverTimezone=UTC", 
    "/users/agieselm/WebApps/WebAppsRPG/server/app/", 
    "models", Option("rpg"), Option("MunchkinKill"), true, false
  )
}