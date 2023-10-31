package com.trivago.model

import play.api.libs.json.{Json, Writes}


case class Advertiser(advertiserId:Long,advertiserName:String, accomodations: Seq[Accomodation])
object Advertiser{
  implicit val advertiserWrite: Writes[Advertiser] = (advertiser: Advertiser) => {
    Json.obj(
      "id" -> advertiser.advertiserId,
      "name" -> advertiser.advertiserName,
      "accomodations" -> Json.arr(advertiser.accomodations.mkString(""))
    )
  }
  def toJson(advertiser: Advertiser): String={
    Json.toJson(advertiser).as[String]
  }
}