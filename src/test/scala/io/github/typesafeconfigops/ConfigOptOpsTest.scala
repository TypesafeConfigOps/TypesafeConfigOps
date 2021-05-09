package io.github.typesafeconfigops

import com.typesafe.config.ConfigFactory
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ConfigOptOpsTest
  extends AnyWordSpec
  with Matchers {

  import TypesafeConfigOps.ConfigOptOps

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
      |  Doom = {
      |    tgm = IDDQD
      |  }
      |}
    """.stripMargin)

  "ConfigOptOps" should {

    "return value of existing Int" in {
      cfg.optInt("i") shouldBe Some(1)
    }

    "return None for non existing Int" in {
      cfg.optInt("ix") shouldBe None
    }

    "return value of existing Long" in {
      cfg.optLong("l") shouldBe Some(1024L)
    }

    "return None for non existing Long" in {
      cfg.optLong("lx") shouldBe None
    }

    "return value of existing quoted String" in {
      cfg.optString("s1") shouldBe Some("TypeSafeConfig")
    }

    "return value of existing unquoted String" in {
      cfg.optString("s2") shouldBe Some("TypeSafeConfig")
    }

    "return None for non existing String" in {
      cfg.optString("sx") shouldBe None
    }

    "return value of existing Boolean" in {
      cfg.optBool("b") shouldBe Some(false)
    }

    "return None for non existing Boolean" in {
      cfg.optBool("bx") shouldBe None
    }

    "return value of existing Double" in {
      cfg.optDouble("d") shouldBe Some(3.14)
    }

    "return None for non existing Double" in {
      cfg.optDouble("dx") shouldBe None
    }

    "return value of existing unquoted BigDecimal" in {
      cfg.optBigDecimal("bd1") shouldBe Some(BigDecimal("2.7000000000000000001"))
    }

    "return value of existing quoted BigDecimal" in {
      cfg.optBigDecimal("bd2") shouldBe Some(BigDecimal("2.7000000000000000001"))
    }

    "return values of existing quoted and unquoted BigDecimal" in {
      cfg.optBigDecimal("bd1") shouldBe cfg.optBigDecimal("bd2")
    }

    "return None for non existing BigDecimal" in {
      cfg.optBigDecimal("bdx") shouldBe None
    }

    "return value of existing list of Int" in {
      cfg.optIntList("iList") shouldBe Some(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0))
    }

    "return None for non existing list of Int" in {
      cfg.optIntList("iListX") shouldBe None
    }

    "return value of existing list of Long" in {
      cfg.optLongList("lList") shouldBe Some(List[Long](1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048))
    }

    "return None for non existing list of Long" in {
      cfg.optLongList("lListX") shouldBe None
    }

    "return value of existing list of String" in {
      cfg.optStringList("sList") shouldBe Some(List("a", "b", "c", "d", "e", "f"))
    }

    "return None for non existing list of String" in {
      cfg.optStringList("sListX") shouldBe None
    }

    "return value of existing list of BigDecimal" in {
      cfg.optBigDecimalList("bdList") shouldBe Some(List[BigDecimal](3.14, 2.7))
    }

    "return None for non existing list of BigDecimal" in {
      cfg.optBigDecimalList("bdListX") shouldBe None
    }

    "return value of existing config" in {
      cfg.optConfig("Doom") shouldBe Some(ConfigFactory.parseString("""{ tgm = IDDQD }"""))
    }

    "return None for non existing config" in {
      cfg.optConfig("Morrowind") shouldBe None
    }

  }

}
