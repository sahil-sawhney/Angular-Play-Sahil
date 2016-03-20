package services

import com.google.inject.Inject
import models.Intern
import play.api.libs.json
import play.api.libs.json._
import play.api.libs.functional.syntax._
import repositories.InternRepo
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

/**
  * Created by sahil on 3/14/16.
  */
class InternServices @Inject()(intern:InternRepo) {

  private implicit val internWrites = new Writes[Intern] {

    def writes(intern: Intern): JsValue = Json.obj(
      "name" -> intern.name,
      "email" -> intern.email,
      "mobile" -> intern.mobile,
      "address" -> intern.address,
      "alternateContact" -> intern.alternateContact,
      "id" -> intern.id
    )
  }

  private implicit val internWritesId = new Writes[Int] {

    def writes(id: Int): JsValue = Json.obj(
      "id" -> id
    )
  }

  private implicit val internReads = (
    (JsPath \ "name").read[String] and
      (JsPath \ "email").read[String] and
      (JsPath \ "mobile").read[String] and
      (JsPath \ "address").read[String] and
      (JsPath \ "alternateContact").read[String] and
      (JsPath \ "id").read[Int]
    ) (Intern.apply _)


  def getAllInterns: Future[JsValue] = intern.getAll.map(x => Json.toJson(x))

  def deleteIntern(id: Int): Future[JsValue] = intern.delete(id).map(x => Json.toJson(x))

  def getInternById(id: Int): Future[JsValue] = intern.internById(id).map(x => Json.toJson(x))

  def editIntern(internJson: JsValue): Future[JsValue] = intern.update(internJson.as[Intern]).map(x => Json.toJson(x))

  def addIntern(internJson: JsValue): Future[JsValue] = intern.insert(internJson.as[Intern]).map(x => Json.toJson(x))

}
