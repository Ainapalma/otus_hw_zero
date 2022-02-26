import scopt.OParser

case class Config(
                   source: String = "",
                   destination: String = ""
                 )

object Config {
  val builder = OParser.builder[Config]

  val parser1 = {
    import builder._
    OParser.sequence(
      programName("scopt"),
      head("scopt", "4.x"),
      // option -f, --foo
      opt[String]('s', "source")
        .action((x, c) => c.copy(source = x)) // (value, arguments) => arguments.copy(link = value)
        .text("url processed"),
      opt[String]('d', "destination")
        .action((x, c) => c.copy(destination = x)) // (value, arguments) => arguments.copy(link = value)
        .text("file name processed")
    )
  }

  def parseArgs(args: Array[String]): Config =
    OParser
      .parse(parser1, args, Config())
      .getOrElse {
        sys.error("Could not parse arguments")
      }
}