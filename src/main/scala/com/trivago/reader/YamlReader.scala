package com.trivago.reader
import com.trivago.logger.Logger
import com.trivago.model.{Accomodation, Advertiser, CurrencyType, Price}
import org.yaml.snakeyaml.Yaml
import scala.util.Try
import scala.collection.JavaConverters.mapAsScalaMapConverter
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

class YamlReader(override val filePath: String) extends Reader with Logger {
  override def readContent(): Advertiser = {
    val yaml = new Yaml()
    info(s"Reading from file ${filePath}")
    val loadedData = yaml.load(fileContent).asInstanceOf[java.util.Map[String, Any]].asScala
    val advertiserId = loadedData.get("id").getOrElse("")
    val advertiserName = loadedData.get("name").getOrElse("")
    val accomodations = loadedData.get("accommodation")
    val accomodationObject = accomodations match{
      case Some(accomomodationList) =>
        accomomodationList.asInstanceOf[java.util.ArrayList[java.util.Map[String, Any]]].map{
          arrayList =>
            val accomodationId = arrayList.get("id").toString.toLong
            val priceList = arrayList.get("prices").asInstanceOf[java.util.ArrayList[java.util.Map[String,Any]]].map{
             price =>
               Price(CurrencyType.fromString(price.get("currency").toString), Try(price.get("price").asInstanceOf[Double]).toOption)
           }
            Accomodation(accomodationId,priceList.toSeq)
        }
      }
    Advertiser(advertiserId.toString.toLong,advertiserName.toString,accomodationObject.toSeq)
  }
}
