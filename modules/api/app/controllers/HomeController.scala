package controllers

import javax.inject.*
import play.api.*
import play.api.mvc.*
import play.api.libs.json.Json

/** Minimal scaffold controller. The real `/api/v1` surface (error envelope,
  * JWT, search/chat endpoints) arrives with roadmap tasks 8.x.
  */
@Singleton
class HomeController @Inject() (val controllerComponents: ControllerComponents)
    extends BaseController:

  def index(): Action[AnyContent] = Action { implicit request =>
    Ok("EDGAR Intelligence Platform API")
  }

  def health(): Action[AnyContent] = Action { implicit request =>
    Ok(Json.obj("status" -> "ok", "service" -> "edgar-api"))
  }
