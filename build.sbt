lazy val sharedSettings = Seq(
  organization := "com.github.benhutchison",

  version := "1.4",
  scalaVersion := "2.12.0",
  name := "microjson",

  // Sonatype
  publishArtifact in Test := false,
  publishTo <<= version { (v: String) =>
    Some("releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  },

  testFrameworks += new TestFramework("utest.runner.Framework"),

  pomExtra :=
    <url>https://github.com/benhutchison/MicroJson</url>
    <licenses>
      <license>
        <name>Apache license</name>
        <url>http://opensource.org/licenses/Apache-2.0</url>
      </license>
    </licenses>
    <scm>
      <url>git://github.com/benhutchison/MicroJson.git</url>
    </scm>
    <developers>
      <developer>
        <id>benhutchison</id>
        <name>Ben Hutchison</name>
        <url>https://github.com/benhutchison</url>
      </developer>
    </developers>
)

name := "Microjson root project"

lazy val microjson = crossProject.in(file(".")).
  settings(sharedSettings: _*)
  .jsSettings(
    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.4.4" % "test"
  ).jvmSettings(
    libraryDependencies += "com.lihaoyi" %% "utest" % "0.4.4" % "test"
  )
lazy val js = microjson.js
lazy val jvm   = microjson.jvm

lazy val root = project.in(file(".")).aggregate(js, jvm).
settings(
  publishArtifact := false,
  crossScalaVersions := Seq("2.11.8", "2.12.0"),
  sonatypeProfileName := "com.github.benhutchison"
)