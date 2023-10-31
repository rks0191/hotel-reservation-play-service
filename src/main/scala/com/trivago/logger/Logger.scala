package com.trivago.logger
import org.slf4j.{Logger, LoggerFactory}
trait Logger {
  def logger : org.slf4j.Logger = LoggerFactory.getLogger(getClass)
  def warn(warningString: String): Unit = logger.warn(warningString)
  def info(infoString: String): Unit = logger.info(infoString)
  def error(errorString: String): Unit = logger.error(errorString)
}
