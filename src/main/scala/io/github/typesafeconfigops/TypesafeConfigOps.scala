package io.github.typesafeconfigops

import com.typesafe.config.{Config, ConfigValueFactory}

import scala.collection.JavaConverters._

trait TypesafeConfigOps {

  implicit class ConfigOptOps(c: Config) {
    def optConfig(path: String): Option[Config] = if (c.hasPath(path)) Option(c.getConfig(path)) else None
    def optString(path: String): Option[String] = if (c.hasPath(path)) Option(c.getString(path)) else None
    def optDouble(path: String): Option[Double] = if (c.hasPath(path)) Option(c.getDouble(path)) else None
    def optInt(path: String): Option[Int] = if (c.hasPath(path)) Option(c.getInt(path)) else None
    def optLong(path: String): Option[Long] = if (c.hasPath(path)) Option(c.getLong(path)) else None
    def optBool(path: String): Option[Boolean] = if (c.hasPath(path)) Option(c.getBoolean(path)) else None
    def optBigDecimal(path: String): Option[BigDecimal] = if (c.hasPath(path)) Option(BigDecimal(c.getString(path))) else None

    def optIntList(path: String): Option[List[Int]] = if (c.hasPath(path)) Option(c.getIntList(path).asScala.map(_.toInt).toList) else None
    def optLongList(path: String): Option[List[Long]] = if (c.hasPath(path)) Option(c.getLongList(path).asScala.map(_.toLong).toList) else None
    def optStringList(path: String): Option[List[String]] = if (c.hasPath(path)) Option(c.getStringList(path).asScala.toList) else None
    def optBigDecimalList(path: String): Option[List[BigDecimal]] = if (c.hasPath(path)) Option(c.getStringList(path).asScala.map(x => BigDecimal(x)).toList) else None
  }

  implicit class ConfigDefaultOps(c: Config) {
    def getConfig(path: String, default: Config): Config = c.optConfig(path).getOrElse(default)
    def getString(path: String, default: String): String = c.optString(path).getOrElse(default)
    def getDouble(path: String, default: Double): Double = c.optDouble(path).getOrElse(default)
    def getInt(path: String, default: Int): Int = c.optInt(path).getOrElse(default)
    def getLong(path: String, default: Long): Long = c.optLong(path).getOrElse(default)
    def getBool(path: String, default: Boolean): Boolean = c.optBool(path).getOrElse(default)
    def getBigDecimal(path: String, default: BigDecimal): BigDecimal = c.optBigDecimal(path).getOrElse(default)

    def getIntList(path: String, default: List[Int]): List[Int] = c.optIntList(path).getOrElse(default)
    def getLongList(path: String, default: List[Long]): List[Long] = c.optLongList(path).getOrElse(default)
    def getStringList(path: String, default: List[String]): List[String] = c.optStringList(path).getOrElse(default)
    def getBigDecimalList(path: String, default: List[BigDecimal]): List[BigDecimal] = c.optBigDecimalList(path).getOrElse(default)
  }


  implicit class ConfigTemplateOps(c: Config) {
    def getTemplate(path: String): String = c.getString(path)
    def formatTemplate(path: String, args: Any*): String = c.getString(path).format(args: _*)
    def resolveTemplate(args: (String, Any)*): Config = {
      (c /: args) { case (cfg, (k, v)) => cfg.withValue(k, ConfigValueFactory.fromAnyRef(v)) }.resolve()
    }
  }

}

object TypesafeConfigOps extends TypesafeConfigOps
