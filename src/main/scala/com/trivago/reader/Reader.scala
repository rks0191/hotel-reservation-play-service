package com.trivago.reader

import com.trivago.model.Advertiser
import java.io.File

trait Reader {
  def filePath: String
  def readContent : Advertiser
  def file: File = new File(filePath)
  def fileContent: String ={
    scala.io.Source.fromFile(file).mkString
  }

}
