import org.scalajs.jsenv.nodejs.NodeJSEnv
import sbt._
import Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import org.scalajs.sbtplugin.cross.CrossProject

object Build extends sbt.Build
{

  lazy val sharedSettings = Seq(
    organization := "com.github.benhutchison",

    version := "1.1",
    scalaVersion := "2.11.5",
    name := "microjson",
    crossScalaVersions := Seq("2.10.4", "2.11.5"),

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


  lazy val cross = CrossProject("microjson",new File("."),CrossType.Full).
    settings(sharedSettings: _*)
    .jsSettings(
      //(jsEnv in Test) := new NodeJSEnv,
      libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.0"
    ).jvmSettings(
      libraryDependencies += "com.lihaoyi" %% "utest" % "0.3.0"
    )
  lazy val js = cross.js
  lazy val jvm   = cross.jvm




}

