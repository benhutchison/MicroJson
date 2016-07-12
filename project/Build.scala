import org.scalajs.jsenv.nodejs.NodeJSEnv
import sbt._
import Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import org.scalajs.sbtplugin.cross.CrossProject

import xerial.sbt.Sonatype.sonatypeSettings


object Build extends sbt.Build
{

  lazy val sharedSettings = Seq(
    organization := "com.github.benhutchison",

    version := "1.3",
    scalaVersion := "2.11.6",
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
      libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
    ).jvmSettings(
      libraryDependencies += "com.lihaoyi" %% "utest" % "0.3.1" % "test"
    )
  lazy val js = microjson.js
  lazy val jvm   = microjson.jvm




}

