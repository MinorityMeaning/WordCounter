package ru.mardaunt

import scala.collection.mutable
import scala.io.Source
import scala.util.{Failure, Success, Using}

object WordCounter extends App {

  def createWordMap(filePath: String): Map[String, Int] =
    Using(Source.fromFile(filePath))(
      _.getLines
        .flatMap(_.split("\\s+"))
        .map(_.filter(_.isLetter).toLowerCase)
        .filterNot(_.isEmpty)
        .foldLeft(mutable.Map[String, Int]().withDefault(_ => 0)) {
          (accMap, word) =>
            accMap(word) += 1
            accMap
        }.toMap
    ) match {
      case Success(resultMap) => resultMap
      case Failure(exception) => throw exception
    }

}
