package com.trivago.reader

import com.trivago.model.Advertiser

import java.io.File

object ReaderFactory {
  def listFilesInDirectory(directoryPath: String): List[File] = {
    val directory = new File(directoryPath)

    if (directory.exists() && directory.isDirectory) {
      directory.listFiles().toList
    } else {
      List.empty[File]
    }
  }

    def readContent(): Seq[Advertiser] = {
      val directoryPath = getClass.getClassLoader.getResource("prices").getPath
     listFilesInDirectory(directoryPath).foldLeft(Seq[Advertiser]()){
       (advertiser, file) =>
         FileType.from(file.getPath) match{
           case YAML => advertiser :+ new YamlReader(file.getPath).readContent
           case _ => advertiser :+ new JsonReader(file.getPath).readContent
         }
     }
    }
}
