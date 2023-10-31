package com.trivago.exception

class CurrencyParsingException extends Exception {
  override def getMessage: String = "Unable to parse the Currency"
}
