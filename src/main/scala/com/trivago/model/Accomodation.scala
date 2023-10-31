package com.trivago.model

import com.trivago.exception.CurrencyParsingException
import com.trivago.logger.Logger

sealed trait CurrencyType
case object Eur extends CurrencyType
case object Dollar extends CurrencyType
object CurrencyType extends Logger{
  def fromString(string: String): Option[CurrencyType] = {
    string.toLowerCase match{
      case "eur"  => Some(Eur)
      case "dollar" => Some(Dollar)
      case _ => error("Unable to parse the currency type")
        None
    }
  }
  def toString(currencyType: CurrencyType): String ={
    currencyType match{
      case Eur => toString(Eur)
      case Dollar => toString(Dollar)
      case _ => error("Wrong value to parse"); throw new CurrencyParsingException()
    }
  }
}
case class Price(currencyType: Option[CurrencyType], amount: Option[Double])
case class Accomodation(id:Long, prices: Seq[Price])