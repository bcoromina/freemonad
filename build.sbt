ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "freemonad"
  )
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.11"
libraryDependencies += "org.typelevel" %% "cats-free" % "2.7.0"
addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)