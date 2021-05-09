package io.github.typesafeconfigops

import com.typesafe.config.ConfigFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ConfigTemplateOpsTest
  extends AnyWordSpec
  with Matchers {

  import TypesafeConfigOps.ConfigTemplateOps

  "ConfigTemplateOps" should {

    "format template" in {
      val cfg = ConfigFactory.parseString(
        """
          |{
          |  code = "<img src='%s' height=%d width=%d border='#%X%X%X' />"
          |}
        """.stripMargin)

      val code = cfg.formatTemplate("code", "https://google.com/logo.png", 100, 200, 255, 255, 255)

      code shouldBe "<img src='https://google.com/logo.png' height=100 width=200 border='#FFFFFF' />"
    }

    "resolve template" in {
      val cfg = ConfigFactory.parseString(
        """
          |{
          |  code = <img ${imgSrc} ${imgHeight} ${imgWidth} ${imgBorder} />
          |}
        """.stripMargin)

      val code = cfg
        .resolveTemplate(
          "imgSrc" -> "src='https://google.com/logo.png'",
          "imgHeight" -> "height='100px'",
          "imgWidth" -> "width='200px'",
          "imgBorder" -> "border='#FFFFFF'")
        .getTemplate("code")

      code shouldBe "<img src='https://google.com/logo.png' height='100px' width='200px' border='#FFFFFF' />"
    }

    "resolve and format template" in {
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

      code shouldBe "<img src='https://google.com/logo.png' height='100px' width='200px' border='#FFFFFF' />"
    }

  }

}
