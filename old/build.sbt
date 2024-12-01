
lazy val proto = (project in file("."))
  .settings(
    name := "Hello"
  )
libraryDependencies += "com.google.protobuf" % "protobuf-java" % "4.29.0"
