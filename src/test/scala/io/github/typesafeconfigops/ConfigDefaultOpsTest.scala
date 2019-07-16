package io.github.typesafeconfigops

import com.typesafe.config.ConfigFactory
import org.scalatest.{Matchers, WordSpec}

class ConfigDefaultOpsTest  extends WordSpec with Matchers {

  import TypesafeConfigOps.ConfigDefaultOps

  private val cfg = ConfigFactory.parseString(
    """
      |{
      |  i = 1
      |  l = 1024
      |  s1 = "TypeSafeConfig"
      |  s2 = "TypeSafeConfig"
      |  b = false
      |  d = 3.14
      |  bd1 = 2.7000000000000000001
      |  bd2 = "2.7000000000000000001"
      |  iList = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
      |  lList = [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048]
      |  bdList = ["3.14", 2.7]
      |  sList = ["a", b, c, d, "e", f]
      |}
    """.stripMargin)

  "ConfigDefaultOps" should {

    "return original value of existing Int" in {
      cfg.getInt("i", 2) shouldBe 1
    }

    "return default value for non existing Int" in {
      cfg.getInt("ix", 2) shouldBe 2
    }

    "return original value of existing Long" in {
      cfg.getLong("l", 2048L) shouldBe 1024L
    }

    "return default value for non existing Long" in {
      cfg.getLong("lx", 2048L) shouldBe 2048L
    }

    "return original value of existing quoted String" in {
      cfg.getString("s1", "DEFAULT_VALUE") shouldBe "TypeSafeConfig"
    }

    "return original value of existing unquoted String" in {
      cfg.getString("s2", "DEFAULT_VALUE") shouldBe "TypeSafeConfig"
    }

    "return default value for non existing String" in {
      cfg.getString("sx", "DEFAULT_VALUE") shouldBe "DEFAULT_VALUE"
    }

    "return original value of existing Boolean" in {
      cfg.getBool("b", default = true) shouldBe false
    }

    "return default value for non existing Boolean" in {
      cfg.getBool("bx", default = true) shouldBe true
    }

    "return original value of existing Double" in {
      cfg.getDouble("d", 1.001) shouldBe 3.14
    }

    "return default value for non existing Double" in {
      cfg.getDouble("dx", 1.001) shouldBe 1.001
    }

    "return original value of existing unquoted BigDecimal" in {
      cfg.getBigDecimal("bd1", BigDecimal("123.456")) shouldBe BigDecimal("2.7000000000000000001")
    }

    "return original value of existing quoted BigDecimal" in {
      cfg.getBigDecimal("bd2", BigDecimal("123.456")) shouldBe BigDecimal("2.7000000000000000001")
    }

    "return original values of existing quoted and unquoted BigDecimal" in {
      cfg.getBigDecimal("bd1", BigDecimal("123.456")) shouldBe cfg.getBigDecimal("bd2", BigDecimal("123.456"))
    }

    "return default value for non existing BigDecimal" in {
      cfg.getBigDecimal("bdx", BigDecimal("123.456")) shouldBe BigDecimal("123.456")
    }

    "return original value of existing list of Int" in {
      cfg.getIntList("iList", List(100, 200, 300)) shouldBe List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    }

    "return default value for non existing list of Int" in {
      cfg.getIntList("iListX", List(100, 200, 300)) shouldBe List(100, 200, 300)
    }

    "return original value of existing list of Long" in {
      cfg.getLongList("lList", List[Long](1000)) shouldBe List[Long](1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048)
    }

    "return default value for non existing list of Long" in {
      cfg.getLongList("lListX", List[Long](1000, 2000)) shouldBe List[Long](1000, 2000)
    }

    "return original value of existing list of String" in {
      cfg.getStringList("sList", List("http", "https")) shouldBe List("a", "b", "c", "d", "e", "f")
    }

    "return default value for non existing list of String" in {
      cfg.getStringList("sListX", List("http", "https")) shouldBe List("http", "https")
    }

    "return original value of existing list of BigDecimal" in {
      cfg.getBigDecimalList("bdList", List[BigDecimal](1.01, 2.02)) shouldBe List[BigDecimal](3.14, 2.7)
    }

    "return default value for non existing list of BigDecimal" in {
      cfg.getBigDecimalList("bdListX", List[BigDecimal](1.01, 2.02)) shouldBe List[BigDecimal](1.01, 2.02)
    }

  }

}
