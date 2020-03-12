lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ar.com.flow",
      scalaVersion := "2.13.1"
    )),
    name := "scala-money-object-model"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test
