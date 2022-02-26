import com.github.plokhotnyuk.jsoniter_scala.core.{JsonValueCodec, readFromArray, _}
import com.github.plokhotnyuk.jsoniter_scala.macros.{JsonCodecMaker, _}

object Jsoniter {
  case class Name(common: String, official: String)
  case class RootInterface(name: Name,
                           region: String,
                           capital: Seq[String],
                           area: Double
                          )

  implicit val codec: JsonValueCodec[List[RootInterface]] = JsonCodecMaker.make(CodecMakerConfig)
}
