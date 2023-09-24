package io.github.typesafeconfigops

import com.typesafe.config.Config

trait ConfigDefaultOps:

  import ConfigOptOps.*

  extension (c: Config)
    def getConfig(path: String, default: Config): Config =
      c.optConfig(path).getOrElse(default)

    def getString(path: String, default: String): String =
      c.optString(path).getOrElse(default)

    def getDouble(path: String, default: Double): Double =
      c.optDouble(path).getOrElse(default)

    def getInt(path: String, default: Int): Int =
      c.optInt(path).getOrElse(default)

    def getLong(path: String, default: Long): Long =
      c.optLong(path).getOrElse(default)

    def getBool(path: String, default: Boolean): Boolean =
      c.optBool(path).getOrElse(default)

    def getBigDecimal(path: String, default: BigDecimal): BigDecimal =
      c.optBigDecimal(path).getOrElse(default)

    def getIntList(path: String, default: List[Int]): List[Int] =
      c.optIntList(path).getOrElse(default)

    def getLongList(path: String, default: List[Long]): List[Long] =
      c.optLongList(path).getOrElse(default)

    def getStringList(path: String, default: List[String]): List[String] =
      c.optStringList(path).getOrElse(default)

    def getBigDecimalList(path: String, default: List[BigDecimal]): List[BigDecimal] =
      c.optBigDecimalList(path).getOrElse(default)

object ConfigDefaultOps extends ConfigDefaultOps
