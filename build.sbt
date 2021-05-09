
name := "TypesafeConfigOps"

version := "1.3.4"

description := "Useful extension and DSL created for Typesafe Config that allows to work with optional values, default values and templates."
licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://typesafeconfigops.github.io"))

organization := "io.github.typesafeconfigops"
organizationName := "typesafeconfigops"
organizationHomepage := Some(url("https://typesafeconfigops.github.io"))

developers := List(
  Developer(
    id = "lashchenko",
    name = "Andrii Lashchenko",
    email = "andrew.lashchenko@gmail.com",
    url = url("https://github.com/lashchenko")
  )
)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/typesafeconfigops/TypesafeConfigOps"),
    "scm:git@github.com:typesafeconfigops/TypesafeConfigOps.git"
  )
)

publishTo := {
  // https://central.sonatype.org/news/20210223_new-users-on-s01/
  val nexus = "https://s01.oss.sonatype.org"
  if (isSnapshot.value) Some("snapshots" at nexus + "/content/repositories/snapshots")
  else Some("releases" at nexus + "/service/local/staging/deploy/maven2")
}

// https://central.sonatype.org/news/20210223_new-users-on-s01/
sonatypeCredentialHost := "https://s01.oss.sonatype.org"

publishMavenStyle := true

publishConfiguration := publishConfiguration.value.withOverwrite(true)


scalaVersion := "2.12.12"

scalacOptions ++= Seq(
  "-explaintypes",
  "-deprecation",
  "-Xlint:-missing-interpolator,_",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:params"
)

Test / parallelExecution := true
Test / fork := true

libraryDependencies += "com.typesafe" % "config" % "1.4.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.8" % Test

