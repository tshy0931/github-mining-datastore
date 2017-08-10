package com.gitmining.datastore.dao

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

trait JsonFormats extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val dateTimeFormat = DateTimeFormat
  implicit val userFormat = jsonFormat13(User.apply)
  implicit val repoFormat = jsonFormat17(Repo.apply)

}

object DateTimeFormat extends RootJsonFormat[DateTime] {

  private val parseISO:DateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis()

  override def write(obj: DateTime): JsValue = JsString(parseISO.print(obj))

  override def read(json: JsValue): DateTime = json match {
    case JsString(dateTime) => parseISO.parseDateTime(dateTime)
    case els => throw DeserializationException(s"Error parsing DateTime $els")
  }
}
