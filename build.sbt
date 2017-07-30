name := "github-mining-datastore"

version := "0.1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.9",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.9",
  "com.outworkers" % "phantom-dsl_2.12" % "2.9.2"
)
