enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

name := "Outwatchd3"

version := "0.1.0"

organization := "Your organization"

scalaVersion := "2.12.2"

requiresDOM in Test := true
useYarn := true // instead of npm
scalaJSUseMainModuleInitializer := true

resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies ++= Seq(
  "io.github.outwatch" %%% "outwatch" % "0.10.1",
  "com.github.fdietze" % "scala-js-d3v4" % "master-SNAPSHOT",
  "org.scalatest" %%% "scalatest" % "3.0.1" % Test
)
