import sbt._
import Keys._

import scala.scalajs.sbtplugin.env.nodejs.NodeJSEnv

import scala.scalajs.sbtplugin.ScalaJSPlugin.ScalaJSKeys._

object Build extends sbt.Build{
  val cross = new utest.jsrunner.JsCrossBuild(
    organization := "com.github.benhutchison",

    version := "1.0",
    scalaVersion := "2.11.2",
    name := "microjson",
    crossScalaVersions := Seq("2.10.4", "2.11.2"),

    // Sonatype
    publishArtifact in Test := false,
    publishTo <<= version { (v: String) =>
      Some("releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
    },

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

  lazy val root = cross.root

  lazy val js = cross.js.settings(
    (jsEnv in Test) := new NodeJSEnv
  )

  lazy val jvm = cross.jvm
}

