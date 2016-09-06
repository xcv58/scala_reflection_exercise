lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  organization := "com.xcv58.scala"
)

lazy val scalaReflect = Def.setting { "org.scala-lang" % "scala-reflect" % scalaVersion.value }

lazy val core = (project in file(".")).
  dependsOn(macroSub).
  settings(commonSettings: _*).
  settings(
    name := "hello",
    version := "1.0",
    // include the macro classes and resources in the main jar
    mappings in (Compile, packageBin) ++= mappings.in(macroSub, Compile, packageBin).value,
    // include the macro sources in the main source jar
    mappings in (Compile, packageSrc) ++= mappings.in(macroSub, Compile, packageSrc).value
  )

lazy val macroSub = (project in file("macro")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += scalaReflect.value,
    publish := {},
    publishLocal := {}
  )
