
resolvers += DefaultMavenRepository

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "bintray-allixender-packages" at "https://dl.bintray.com/allixender/maven/"

resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"

resolvers += "Osgeo Repo" at "http://download.osgeo.org/webdav/geotools/"

resolvers += "Boundless" at "http://repo.boundlessgeo.com/main"

resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
    Resolver.ivyStylePatterns)

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")

//addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

addSbtPlugin("org.typelevel" % "sbt-typelevel" % "0.3.1")

addSbtPlugin("com.scalapenos" % "sbt-prompt" % "1.0.0")

//addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

//addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.0.4")
