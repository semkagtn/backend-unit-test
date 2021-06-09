name := "backend-unit-test"

organization := "com.semkagtn"

version := "0.1"

scalaVersion := "2.13.6"

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-explaintypes",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-language:implicitConversions"
)

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.1.1",
  "org.scalacheck" %% "scalacheck" % "1.14.3",
  "org.scalatestplus" %% "scalacheck-1-14" % "3.1.1.1",
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion,
  "org.scalamock" %% "scalamock" % "5.1.0"
).map(_ % Test)
