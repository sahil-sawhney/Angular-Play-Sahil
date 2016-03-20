
name := """demoAngularPlay"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  specs2 % Test,
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc4",
  "com.typesafe.play" %% "play-slick" % "1.1.1",
  "com.h2database" % "h2" % "1.4.187" % "test",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "mysql" % "mysql-connector-java" % "5.1.36"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

routesGenerator := InjectedRoutesGenerator

coverageExcludedPackages := "<empty>;router\\..*;"

javaOptions in Test += "-Dconfig.file=conf/test.conf"
