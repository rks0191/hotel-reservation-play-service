package com.trivago.exception

class FileParsingExeception extends Exception{
  override def getMessage: String = "Unable to parse the File"

}
