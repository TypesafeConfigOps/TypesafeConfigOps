name := "TypesafeConfigOps"
organization := "io.github.typesafeconfigops"

version := "0.1.1"

scalaVersion := "2.12.8"

scalacOptions ++= Seq(
  "-explaintypes",
  "-deprecation",
  "-Xlint:-missing-interpolator,_",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:params"
)

parallelExecution in Test := true

libraryDependencies += "com.typesafe" % "config" % "1.3.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

resolvers += Resolver.jcenterRepo

bintrayOrganization := Some("typesafeconfigops")

licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0"))
