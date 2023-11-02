package com.trivago.reader

import com.trivago.exception.FileParsingExeception
import com.trivago.logger.Logger
import com.trivago.model.{Accomodation, Advertiser, CurrencyType, Price}
import play.api.libs.json.{JsArray, JsObject, Json}
class JsonReader(override val filePath:String) extends Reader with Logger{
  override def readContent: Advertiser = {
    val json = Json.parse(fileContent).asOpt[JsObject]
    json match {
      case Some(jsObject) =>{
        val name = (jsObject\"name").as[String]
        val advertiserId = (jsObject\"id").as[Long]
        val accomodations = (jsObject\"accommodation").asOpt[JsArray].map{
          jsArray =>
            jsArray.value.map{
              jsonValue =>
                val accomodationId = (jsonValue\"id").as[Long]
                val prices = (jsonValue\"prices").as[JsArray].value.map{
                  priceSeq =>
                    val currency = (priceSeq\"currency").as[String]
                    val price = (priceSeq\"price").asOpt[Double]
                    Price(CurrencyType.fromString(currency), price)
                }
                Accomodation(accomodationId,prices)
            }
        }

        accomodations match {
          case Some(seq) => Advertiser(advertiserId, name, seq)
          case _ => throw new FileParsingExeception
        }}
        case _ => throw new FileParsingExeception
      }
  }
}
