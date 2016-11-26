import com.typesafe.config.ConfigFactory

name := """billets-en-concert"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
  .settings(
    slick <<= slickCodeGenTask // register manual sbt command
  )

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  filters,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.twitter4j" % "twitter4j-core" % "4.0.5",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.slick" % "slick_2.11" % "3.1.1",
  "mysql" % "mysql-connector-java" % "6.0.5",
//  "mysql" % "mysql-connector-java" % "5.1.20",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.1",
  "org.scalaz" %% "scalaz-core" % "7.2.7"
)

//scalacOptions := Seq("-feature")

//sourceGenerators in Compile <+= slickCodeGenTask // register automatic code generation on every compile, remove for only manual use

lazy val config = ConfigFactory.parseFile(new File("./conf/application.conf"))
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = (dir / "slick").getPath // place generated files in sbt's managed sources folder
  val username = config.getString("slick.dbs.default.username")
  val password = config.getString("slick.dbs.default.password")
  val url = config.getString("slick.dbs.default.db.url") // connection info for a pre-populated throw-away, in-memory db for this demo, which is freshly initialized on every run
  val jdbcDriver = config.getString("slick.dbs.default.driver")
  val slickDriver = "slick.driver.MySQLDriver"
  val pkg = "infrastructure.repository"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password), s.log))
  val fname = outputDir + "/slick/Tables.scala"
  Seq(file(fname))
}
