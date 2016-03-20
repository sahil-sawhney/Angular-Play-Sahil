package controllers

import javax.inject._
import models.Intern
import play.api._
import play.api.libs.json.{Json, Writes, JsLookupResult, JsValue}
import play.api.mvc._
import services.InternServices

import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(service:InternServices) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def internJsonList = Action.async {

    implicit request =>
      service.getAllInterns.map(x => Ok(x).withHeaders("Access-Control-Allow-Origin" -> "*"))
  }

  def deleteIntern(id:Int) =Action.async{

    implicit request =>
      service.deleteIntern(id).map(x => Ok(x).withHeaders("Access-Control-Allow-Origin" -> "*"))
  }

  def getIntern(id:Int) =Action.async{

    implicit request =>
      service.getInternById(id).map(x => Ok(x).withHeaders("Access-Control-Allow-Origin" -> "*"))
  }

  def editIntern(intern:String)=Action.async{

     implicit request =>
      service.editIntern(Json.parse(intern)).map(x => Ok(x).withHeaders("Access-Control-Allow-Origin" -> "*"))
  }

  def addIntern(intern:String)=Action.async{

    implicit request =>
      service.addIntern(Json.parse(intern)).map(x => Ok(x).withHeaders("Access-Control-Allow-Origin" -> "*"))
  }

}
