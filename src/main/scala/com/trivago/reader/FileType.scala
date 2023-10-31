package com.trivago.reader

import com.trivago.exception.UnsupportedFileFormatException

sealed trait FileType
case object YAML extends FileType
case object JSON extends FileType
object FileType {
  def supportedFileTypes: String ={
    Seq(JSON,YAML).mkString(",")
  }
  def from(fileType:String):FileType={
    if(fileType.toLowerCase.contains("yaml")) YAML else JSON
  }
}
