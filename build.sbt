ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings {
    name := "hwOne"
  }

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "4.0.1",
  ("com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core").%("2.12.3"),
  // Use the "provided" scope instead when the "compile-internal" scope is not supported
  """com.github.plokhotnyuk.jsoniter-scala""" %% "jsoniter-scala-macros" % "2.12.3"
)

Compile / mainClass  := Some("ParseAndTransform")

assembly / mainClass  := Some("ParseAndTransform")
assembly / assemblyJarName := "finally-fat.jar"
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}