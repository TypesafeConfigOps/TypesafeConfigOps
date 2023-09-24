package io.github.typesafeconfigops

import scala.jdk.CollectionConverters.*

import com.typesafe.config.Config

trait ConfigOptOps:
  extension (c: Config)
    def optConfig(path: String): Option[Config] =
      if (c.hasPath(path)) Option(c.getConfig(path)) else None

    def optString(path: String): Option[String] =
      if (c.hasPath(path)) Option(c.getString(path)) else None

    def optDouble(path: String): Option[Double] =
      if (c.hasPath(path)) Option(c.getDouble(path)) else None

    def optInt(path: String): Option[Int] =
      if (c.hasPath(path)) Option(c.getInt(path)) else None

    def optLong(path: String): Option[Long] =
      if (c.hasPath(path)) Option(c.getLong(path)) else None

    def optBool(path: String): Option[Boolean] =
      if (c.hasPath(path)) Option(c.getBoolean(path)) else None

    def optBigDecimal(path: String): Option[BigDecimal] =
      if (c.hasPath(path)) Option(BigDecimal(c.getString(path))) else None

    def optIntList(path: String): Option[List[Int]] =
      if (c.hasPath(path)) Option(c.getIntList(path).asScala.map(_.toInt).toList) else None

    def optLongList(path: String): Option[List[Long]] =
      if (c.hasPath(path)) Option(c.getLongList(path).asScala.map(_.toLong).toList) else None

    def optStringList(path: String): Option[List[String]] =
      if (c.hasPath(path)) Option(c.getStringList(path).asScala.toList) else None

    def optBigDecimalList(path: String): Option[List[BigDecimal]] =
      if (c.hasPath(path)) Option(c.getStringList(path).asScala.map(x => BigDecimal(x)).toList) else None

object ConfigOptOps extends ConfigOptOps
