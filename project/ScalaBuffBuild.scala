import sbt._
import Keys._
import java.io.File
import com.typesafe.sbt.osgi.SbtOsgi._

/**
 * ScalaBuff SBT build file.
 *
 * Useful SBT commands:
 *
 *      run (arguments)             Runs ScalaBuff inside SBT with the specified arguments.
 *      test                        Runs the tests.
 *      package                     Generates the main ScalaBuff compiler .JAR.
 *      update-test-resources       Regenerates the test resources using ScalaBuff.
 *
 *      project compiler            Switches to the compiler project (default).
 *      project runtime             Switches to the runtime project.
 *
 */
object ScalaBuffBuild extends Build {

	lazy val buildSettings = Seq(
		name := "ScalaBuff",
		organization := "net.sandrogrzicic",
		version := "1.3.9",
		scalaVersion := "2.10.4",
		logLevel := Level.Info
	)

	object sonatype extends PublishToSonatype(ScalaBuffBuild) {
		def projectUrl    = "https://github.com/SandroGrzicic/ScalaBuff"
		def developerId   = "sandrogrzicic"
		def developerName = "Sandro Grzicic"
	}

	override lazy val settings = super.settings ++ buildSettings

	lazy val defaultSettings = Defaults.defaultSettings ++ Seq(

		resolvers ++= Seq(
			"Akka Maven Repository" at "http://akka.io/repository",
			"Typesafe Maven Repository" at "http://repo.typesafe.com/typesafe/releases/",
			"Sonatype OSS Repository" at "https://oss.sonatype.org/content/groups/public/"
		),
		
		libraryDependencies ++= Seq(
			"com.google.protobuf" % "protobuf-java" % "2.5.0"
		) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, scalaMajor)) if scalaMajor >= 10 =>
        Seq("org.scalatest" %% "scalatest" % "2.1.5" % "test") ++ (
          if (scalaMajor >= 11) Seq("org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1") else Seq()
        )
      case Some((2, scalaMajor)) if scalaMajor == 9 =>
        Seq("org.scalatest" %% "scalatest" % "1.9.2" % "test")
      case _ =>
        Seq()
    }),

		crossScalaVersions ++= Seq("2.10.4", "2.11.2"),
		
		scalacOptions ++= Seq("-encoding", "utf8", "-unchecked", "-deprecation", "-Xlint"),
		scalacOptions ++=
		  (CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, scalaMajor)) if scalaMajor >= 10 =>
          Seq("-Xlog-reflective-calls")
        case _ =>
         Seq()
       }),

		javacOptions ++= Seq("-encoding", "utf8", "-Xlint:unchecked", "-Xlint:deprecation"),

		parallelExecution in GlobalScope := true,

		scalaSource in Compile <<= baseDirectory(_ / "src/main"),
		scalaSource in Test <<= baseDirectory(_ / "src/test"),

		javaSource in Compile <<= baseDirectory(_ / "src/main"),
		javaSource in Test <<= baseDirectory(_ / "src/test"),

		compileOrder := CompileOrder.Mixed,
		
		credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
	) ++ sonatype.settings

	lazy val compilerProject = Project(
		id = "scalabuff-compiler",
		base = file("scalabuff-compiler"),
		dependencies = Seq(runtimeProject % "test->compile"),
		settings = defaultSettings ++ Seq(
			mainClass in (Compile, run) := Some("net.sandrogrzicic.scalabuff.compiler.ScalaBuff"),
			mainClass in (Compile, packageBin) := Some("net.sandrogrzicic.scalabuff.compiler.ScalaBuff"),
			fullRunTask(TaskKey[Unit]("update-test-resources"), Compile, "net.sandrogrzicic.scalabuff.test.UpdateTestResources")
		) // ++ osgiSettings
	)

	lazy val runtimeProject = Project(
		id = "scalabuff-runtime",
		base = file("scalabuff-runtime"),
		settings = defaultSettings
	)

  override val rootProject = Option(compilerProject)

}

/** 
 * Source:  https://github.com/paulp/scala-improving/blob/master/project/Publishing.scala
 * License: https://github.com/paulp/scala-improving/blob/master/LICENSE.txt
 */
abstract class PublishToSonatype(build: Build) {
  import build._

  val ossSnapshots = "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
  val ossStaging   = "Sonatype OSS Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2/"

  def projectUrl: String
  def developerId: String
  def developerName: String
  
  def licenseName         = "Apache"
  def licenseUrl          = "http://www.apache.org/licenses/LICENSE-2.0"
  def licenseDistribution = "repo"
  def scmUrl              = projectUrl
  def scmConnection       = "scm:git:" + scmUrl

  def generatePomExtra(scalaVersion: String): xml.NodeSeq = {
    <url>{ projectUrl }</url>
      <licenses>
        <license>
          <name>{ licenseName }</name>
          <url>{ licenseUrl }</url>
          <distribution>{ licenseDistribution }</distribution>
        </license>
      </licenses>
    <scm>
      <url>{ scmUrl }</url>
      <connection>{ scmConnection }</connection>
    </scm>
    <developers>
      <developer>
        <id>{ developerId }</id>
        <name>{ developerName }</name>
      </developer>
    </developers>
  }

  def settings: Seq[Setting[_]] = Seq(
    publishMavenStyle := true,
    publishTo <<= version((v: String) => Some( if (v.trim endsWith "SNAPSHOT") ossSnapshots else ossStaging)),
    publishArtifact in Test := false,
    pomIncludeRepository := (_ => false),
    pomExtra <<= (scalaVersion)(generatePomExtra)
  )
}
