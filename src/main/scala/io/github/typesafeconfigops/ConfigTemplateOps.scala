package io.github.typesafeconfigops

import com.typesafe.config.Config
import com.typesafe.config.ConfigValueFactory

trait ConfigTemplateOps:
  extension (c: Config)
    def getTemplate(path: String): String =
      c.getString(path)

    def formatTemplate(path: String, args: Any*): String =
      c.getString(path).format(args: _*)

    def resolveTemplate(args: (String, Any)*): Config =
      args.foldLeft(c) { case (cfg, (k, v)) => cfg.withValue(k, ConfigValueFactory.fromAnyRef(v)) }.resolve()

object ConfigTemplateOps extends ConfigTemplateOps
