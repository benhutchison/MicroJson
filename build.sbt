lazy val sharedSettings = Seq(
  organization := "com.github.benhutchison",

  version := "1.5",
  scalaVersion := "2.12.4",
  name := "microjson",

  // Sonatype
  publishArtifact in Test := false,


  testFrameworks += new TestFramework("utest.runner.Framework"),

  licenses += ("MIT license", url("http://opensource.org/licenses/Apache-2.0")),
  homepage := Some(url("https://github.com/benhutchison/microjson")),
  developers := List(Developer("benhutchison", "Ben Hutchison", "brhutchison@gmail.com", url = url("https://github.com/benhutchison"))),
  scmInfo := Some(ScmInfo(url("https://github.com/benhutchison/microjson"), "scm:git:https://github.com/benhutchison/microjson.git")),

)

lazy val microjson = crossProject.in(file(".")).
  settings(sharedSettings: _*)
  .jsSettings(
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.0" % "test"
  ).jvmSettings(
    libraryDependencies += "com.lihaoyi" %% "utest" % "0.6.0" % "test"
  )
lazy val js = microjson.js
lazy val jvm   = microjson.jvm

lazy val root = project.in(file(".")).aggregate(js, jvm).
settings(
  skip in publish := true,
  crossScalaVersions := Seq("2.11.11", "2.12.4"),
  sonatypeProfileName := "com.github.benhutchison"
)