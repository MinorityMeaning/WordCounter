package ru.mardaunt

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._

import java.io.FileNotFoundException

class WordCounterTests extends AnyFunSuite {


  test("Попробовать создать карту слов") {

    val filePath: String = getClass.getResource("test.txt").getFile
    val expectedMap = Map(
      "program" -> 2,
      "this" -> 2,
      "is" -> 1,
      "try" -> 1,
      "i" -> 1,
      "work" -> 2,
      "моя" -> 2,
      "can" -> 2,
      "how" -> 1)
    val actualMap = WordCounter.createWordMap(filePath)

    expectedMap should contain theSameElementsAs actualMap
  }

  test("Попробовать прочитать из кривого пути") {
    assertThrows[FileNotFoundException](WordCounter.createWordMap("local/broken_path.txt"))
  }

  test("Попробовать прочитать пустой файл") {
    val filePath: String = getClass.getResource("empty.txt").getFile
    val expectedMap = Map.empty[String, Int]
    val actualMap = WordCounter.createWordMap(filePath)

    expectedMap should contain theSameElementsAs actualMap
  }

  test("Попробовать прочитать файл со спецсимволами и одним словом") {
    val filePath: String = getClass.getResource("dirty.txt").getFile
    val expectedMap = Map("hello" -> 5)
    val actualMap = WordCounter.createWordMap(filePath)

    expectedMap should contain theSameElementsAs actualMap
  }

}
