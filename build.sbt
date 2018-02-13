lazy val akkaVersion    = "2.5.8"
lazy val akkaHttpVersion = "10.1.0-RC1"
lazy val freesVersion    = "0.6.3"
lazy val monixVersion    = "2.3.3"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "io.allixender",
      scalaVersion    := "2.11.12",
      version := "1.0-SNAPSHOT",
      licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
    )),
    name := "scala-swat-exec",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,

      "de.heikoseeberger" %% "akka-http-play-json"      % "1.20.0-RC1",
      "com.typesafe.play" %% "play-json"            % "2.5.12",
      "info.smart-project" %% "smart-owc-geojson" % "1.1.0",

//      "io.frees" %% "frees-core"               % freesVersion,
//      "io.frees" %% "frees-config"             % freesVersion,
//      "io.frees" %% "frees-logging"            % freesVersion,
//      "io.frees" %% "frees-akka"               % freesVersion,
//      "io.frees" %% "frees-monix"              % freesVersion,
//      "io.frees" %% "frees-effects"            % freesVersion,
//      "io.frees" %% "frees-async"              % freesVersion,
//      "io.frees" %% "frees-async-cats-effect"  % freesVersion,

      "io.monix" %% "monix" % monixVersion,
      "io.monix" %% "monix-eval" % monixVersion,
      "io.monix" %% "monix-execution" % monixVersion,
      "io.monix" %% "monix-reactive" % monixVersion,

      "ch.qos.logback"    %  "logback-classic"      % "1.2.3",

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test
    ),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-deprecation", // warning and location for usages of deprecated APIs
      "-feature", // warning and location for usages of features that should be imported explicitly
      "-unchecked", // additional warnings where generated code depends on assumptions
      "-Xlint:_", // recommended additional warnings
      "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
      "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
      "-Ywarn-unused-import", // Warn when imports are unused
      "-Ywarn-unused", // Warn when local and private vals, vars, defs, and types are unused
      "-Ywarn-numeric-widen", // Warn when numerics are widened, Int and Double, for instance
      "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
      "-Ywarn-dead-code", // Warn when dead code is identified
      "-Ywarn-infer-any", // Warn when a type argument is inferred to be `Any`
      "-Ywarn-nullary-override", //  Warn when non-nullary `def f()' overrides nullary `def f'.
      "-Ywarn-nullary-unit", // Warn when nullary methods return Unit
      "-language:reflectiveCalls",
      "-language:postfixOps" // too lazy?
    ),
    resolvers ++= Seq(
      "Typesafe Repo"           at "http://repo.typesafe.com/typesafe/releases/",
      "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases",
      Resolver.bintrayRepo("hseeberger", "maven"),
      Resolver.bintrayRepo("hseeberger", "maven")
    ),
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full)
  ).
  settings(inThisBuild(
    assemblyJarName in assembly := "scala-swat-exec.jar"
  ))
  .enablePlugins(JavaAppPackaging)

Revolver.settings

