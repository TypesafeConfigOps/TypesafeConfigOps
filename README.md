# ${Typesafe} %Config .Ops

[![Build Status](https://travis-ci.org/typesafeconfigops/TypesafeConfigOps.svg?branch=master)](https://travis-ci.org/typesafeconfigops/TypesafeConfigOps)
[ ![Download](https://api.bintray.com/packages/typesafeconfigops/maven/typesafeconfigops/images/download.svg) ](https://bintray.com/typesafeconfigops/maven/typesafeconfigops/_latestVersion)

### Usage
```scala
// build.sbt
resolvers += Resolver.bintrayRepo("typesafeconfigops", "maven")
libraryDependencies += "io.github.typesafeconfigops" %% "typesafeconfigops" % "0.1"
```

### ConfigOptOps
Import `TypesafeConfigOps.ConfigOptOps` allows to extract optional values from configuration.

```scala
import TypesafeConfigOps.ConfigOptOps

private val cfg = ConfigFactory.parseString("""{ "i" = 1 }""")
cfg.optInt("i") // Some(1)
cfg.optInt("ix") // None
```

### ConfigDefaultOps
Import `TypesafeConfigOps.ConfigDefaultOps` allows to use default values for non existing paths in configuration.

```scala
import TypesafeConfigOps.ConfigDefaultOps

private val cfg = ConfigFactory.parseString("""{ "i" = 1 }""")
cfg.getInt("i", 2) // 1
cfg.getInt("ix", 2) // 2
```
### ConfigTemplateOps
Import `TypesafeConfigOps.ConfigTemplateOps` allows to work with templates in configuration.

```scala
import TypesafeConfigOps.ConfigTemplateOps

val cfg = ConfigFactory.parseString(
  """
    |{
    |  code = <img ${imgSrc} ${imgHeight} ${imgWidth} ${imgBorder} />
    |}
  """.stripMargin)

val code = cfg
  .resolveTemplate(
    "imgSrc" -> "src='%s'",
    "imgHeight" -> "height='%dpx'",
    "imgWidth" -> "width='%dpx'",
    "imgBorder" -> "border='#%X%X%X'")
  .formatTemplate("code", "https://google.com/logo.png", 100, 200, 255, 255, 255)

code // "<img src='https://google.com/logo.png' height='100px' width='200px' border='#FFFFFF' />"
```
