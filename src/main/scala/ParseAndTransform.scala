import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import scala.io.BufferedSource
import scala.io.Source.fromURL
import java.nio.file._

object ParseAndTransform {

  def main(args: Array[String]): Unit = {

    // -s https://raw.githubusercontent.com/mledoze/countries/master/countries.json -d fileName
    val config = Config.parseArgs(args)

    val parsedData = {
      val source: BufferedSource = fromURL(config.source)
      try readFromString(source.mkString)(Jsoniter.codec)
      finally source.close()
    }

    val africanCountries = parsedData.filter(p => p.region.contains("Africa"))
    val sortedAfricanCountries = africanCountries.groupBy(_.area).toList.sortWith(_._1 > _._1).map(_._2)
    val top10 = sortedAfricanCountries.take(10).flatten

    Files.write(Paths.get("src/main/out/" + config.destination + ".json"), writeToArray(top10))

  }
}
