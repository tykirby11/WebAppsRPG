import sbtcrossproject.{crossProject, CrossType}

lazy val server = (project in file("server")).settings(commonSettings).settings(
	name := "CSCI3345-S19-Server-RPG",
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline),
  pipelineStages := Seq(digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies ++= Seq(
    "com.vmunier" %% "scalajs-scripts" % "1.1.2",
"com.typesafe.play" %% "play-slick" % "4.0.0",
		"com.typesafe.slick" %% "slick-codegen" % "3.3.0",
		"mysql" % "mysql-connector-java" % "6.0.6",
		"com.typesafe.play" %% "play-json" % "2.7.0",
    guice,
    specs2 % Test
  ),
  // Compile the project before generating Eclipse files, so that generated .scala or .class files for views and routes are present
  EclipseKeys.preTasks := Seq(compile in Compile)
).enablePlugins(PlayScala).
  dependsOn(sharedJvm)

lazy val client = (project in file("client")).settings(commonSettings).settings(
	name := "CSCI3345-S19-Client-RPG",
  scalaJSUseMainModuleInitializer := true,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.5",
		"org.querki" %%% "jquery-facade" % "1.2",
//		"com.github.japgolly.scalajs-react" %%% "core" % "1.4.1"
		"me.shadaj" %%% "slinky-core" % "0.6.0",
		"me.shadaj" %%% "slinky-web" % "0.6.0",
		"me.shadaj" %%% "slinky-scalajsreact-interop" % "0.6.0"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSWeb).
  dependsOn(sharedJs)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(
		name := "CSCI3345-S19-Shared-RPG",
		commonSettings)
lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val commonSettings = Seq(
  scalaVersion := "2.12.5",
  organization := "edu.trinity"
)

// loads the server project at sbt startup
onLoad in Global := (onLoad in Global).value andThen {s: State => "project server" :: s}
