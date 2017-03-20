name := "scala-swat-exec"

organization := "io.allixender"

version := "1.0-SNAPSHOT"

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.10.6", "2.11.8")

val CommonsIO       = "2.4"
val ScalaCheck      = "1.12.5"
val ScalaTest       = "2.2.6"

val CassandraDriver = "3.0.2"
val Spark           = "2.1.0"
val Connector       = "2.0.0"

val geotoolsVersion = "13.1" // 13.6, 14.5, 15.4, 16.2

libraryDependencies ++= Seq(
  "org.scalatest"       %%  "scalatest"      % ScalaTest % "test",
  "org.scalacheck"      %% "scalacheck"      % ScalaCheck % "test",
  "com.typesafe"        % "config"           % "1.3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "commons-io" % "commons-io" % CommonsIO,
  "com.vividsolutions" % "jts" % "1.13",
  "org.geotools" % "gt-epsg-hsql" % geotoolsVersion,
  "org.geotools" % "gt-referencing" % geotoolsVersion
)

resolvers ++= Seq(
  "Typesafe Repo"           at "http://repo.typesafe.com/typesafe/releases/",
  "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases",
  "52North Releases" at "http://52north.org/maven/repo/releases/"
)


// bintrayPackageLabels := Seq("SWAT", "modelling", "hydrology")

// com.typesafe.sbt.SbtGit.versionWithGit


scalacOptions in ThisBuild ++= Seq(
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "-deprecation", // warning and location for usages of deprecated APIs
  "-feature", // warning and location for usages of features that should be imported explicitly
  "-unchecked", // additional warnings where generated code depends on assumptions
  "-Xlint", // recommended additional warnings
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
  "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code",
  "-language:reflectiveCalls"
)

javacOptions in Compile ++= Seq(
  "-encoding", "UTF-8",
  "-source", "1.7",
  "-target", "1.7",
  "-g",
  "-Xlint:-path",
  "-Xlint:deprecation",
  "-Xlint:unchecked"
)
