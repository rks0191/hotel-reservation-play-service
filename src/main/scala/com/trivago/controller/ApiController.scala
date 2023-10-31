package com.trivago.controller

import com.trivago.reader.ReaderFactory
import play.api.libs.json.Json
import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.Inject

class ApiController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def getAllResources = Action{
    Ok(Json.toJson(ReaderFactory.readContent()))
  }
}
